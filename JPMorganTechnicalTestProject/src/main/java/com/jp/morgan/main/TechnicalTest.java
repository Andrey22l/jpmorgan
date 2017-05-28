/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jp.morgan.main;

import com.jp.morgan.model.Instruction;
import com.jp.morgan.model.InstructionData;
import com.jp.morgan.model.InstructionFlag;
import com.jp.morgan.report.PrintReport;
import com.jp.morgan.report.Report;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Andrey
 */
public class TechnicalTest {

    public static void main(String args[]) {
        List<Instruction> instructionlist = InstructionData.getInstructionList();
        Report report = new Report();
        
        Map values = report.reportSettled(instructionlist, InstructionFlag.S);
        PrintReport.printReport(values, InstructionFlag.S.getName());
        
        values = report.reportSettled(instructionlist, InstructionFlag.B);
        PrintReport.printReport(values, InstructionFlag.B.getName());
        
        List list = report.reportRankingEntities(instructionlist);
        PrintReport.printReport(list);
    }
}
