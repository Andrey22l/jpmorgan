
import com.jp.morgan.model.Instruction;
import com.jp.morgan.model.InstructionData;
import static com.jp.morgan.model.InstructionData.createInstruction;
import static com.jp.morgan.model.InstructionData.getDate;
import com.jp.morgan.model.InstructionFlag;
import com.jp.morgan.report.Report;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import static org.hamcrest.core.StringStartsWith.startsWith;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Andrey
 */
public class InstructionTest {

    @Test
    public void test_work_week_starts_Monday() {
        Instruction instruction = InstructionData.createInstruction(
                "foo",
                "B",
                1,
                "USD",
                InstructionData.getDate("26/05/2017"),
                InstructionData.getDate("27/05/2017"),
                200,
                100.25);

        Date workingDate = instruction.getSettlementWorkingDate();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(workingDate);

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        Assert.assertEquals(Calendar.MONDAY, dayOfWeek);

    }

    @Test
    public void test_usd_amount_of_trade() {
        Instruction instruction = InstructionData.createInstruction(
                "foo",
                "B",
                0.23,
                "AED",
                InstructionData.getDate("26/05/2017"),
                InstructionData.getDate("27/05/2017"),
                11,
                23.33);

        double usdAmount = instruction.getUSDAmountOfTrade();

        Assert.assertEquals(usdAmount, 59.02, 0);

    }

    @Test(expected = IllegalArgumentException.class)
    public void test_throw_exception_wrong_currency() {
        Instruction instruction = InstructionData.createInstruction(
                "foo",
                "B",
                0.23,
                "XXX",
                InstructionData.getDate("26/05/2017"),
                InstructionData.getDate("27/05/2017"),
                11,
                23.33);

    }

    @Rule
    public ExpectedException expected = ExpectedException.none();

    @Test
    public void test_throw_exception_wrong_date() {
        expected.expect(IllegalArgumentException.class);
        expected.expectMessage(startsWith("Wrong date"));

        Instruction instruction = InstructionData.createInstruction(
                "foo",
                "B",
                0.23,
                "USD",
                InstructionData.getDate("26/05/abc"),
                InstructionData.getDate("27/05/2017"),
                11,
                23.33);
    }

    /**
     * To check how the test works just comment next block in Instruction.java
     *
     * //if for USD currency day of week equals Sunday if (dayOfWeek == 0) {
     * dayOfWeek = 7; }
     *
     */
    @Test
    public void test_day_of_week_starts_Monday_if_settled_sunday() {
        Instruction instruction = createInstruction(
                "new+",
                "S",
                1,
                "USD",
                getDate("24/05/2017"),
                getDate("28/05/2017"),
                835,
                198.75);

        Date workingDate = instruction.getSettlementWorkingDate();

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(workingDate);

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        Assert.assertEquals(Calendar.MONDAY, dayOfWeek);

    }

    @Test
    public void test_report_settled_incoming() {
        List<Instruction> list = new ArrayList<>();

        list.add(createInstruction(
                "bar",
                "S",
                0.22,
                "AED",
                getDate("24/05/2017"),
                getDate("26/05/2017"),
                450,
                150.5));

        list.add(createInstruction(
                "bar+",
                "S",
                0.26,
                "SGP",
                getDate("24/05/2017"),
                getDate("28/05/2017"),
                1135,
                10.15));

        list.add(createInstruction(
                "new+",
                "S",
                1,
                "USD",
                getDate("24/05/2017"),
                getDate("28/05/2017"),
                835,
                198.75));

        Report report = new Report();

        Map<Date, Double> reportData = report.reportSettled(list, InstructionFlag.S);

        double totalPrice = 0;
        for (Double price : reportData.values()) {
            totalPrice +=price;
        }

        Assert.assertEquals(2, reportData.size());

        Assert.assertEquals(totalPrice, 17894.77 + 165956.25, 0);

    }
    
    
    @Test
    public void test_report_ranking() {
        List<Instruction> list = new ArrayList<>();

        Instruction instruction1 = createInstruction(
                "bar",
                "S",
                0.22,
                "AED",
                getDate("24/05/2017"),
                getDate("26/05/2017"),
                450,
                150.5);
        
        Instruction instruction2 = createInstruction(
                "bar+",
                "S",
                0.26,
                "SGP",
                getDate("24/05/2017"),
                getDate("28/05/2017"),
                1135,
                1135.15);
        
        Instruction instruction3 = createInstruction(
                "bar+=",
                "S",
                0.26,
                "SGP",
                getDate("24/05/2017"),
                getDate("28/05/2017"),
                1135,
                10.15);
        
        list.add(instruction1);
        list.add(instruction2);
        list.add(instruction3);

        
        Report report = new Report();
        report.reportRankingEntities(list);
        
        Assert.assertEquals(list.get(0), instruction2);
    }
}
