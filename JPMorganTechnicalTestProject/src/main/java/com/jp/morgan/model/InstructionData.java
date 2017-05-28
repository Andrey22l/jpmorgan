/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jp.morgan.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class with one method which returns list of Instructions 
 * @author Andrey
 */
public class InstructionData {
    
    
    public static List<Instruction> getInstructionList(){
        List<Instruction> list = new ArrayList<>();
        
        list.add(createInstruction(
                "foo", 
                "B", 
                0.50, 
                "SGP", 
                getDate("01/01/2016"), 
                getDate("02/01/2016"), 
                200, 
                100.25));
        list.add(createInstruction(
                "foo new foo", 
                "B", 
                0.50, 
                "SGP", 
                getDate("01/01/2016"), 
                getDate("02/01/2016"), 
                360, 
                100.25));
        list.add(createInstruction(
                "foo new", 
                "B", 
                0.30, 
                "USD", 
                getDate("01/01/2016"), 
                getDate("01/01/2016"), 
                270, 
                110.25));
        
        list.add(createInstruction(
                "bar", 
                "S", 
                0.22, 
                "AED", 
                getDate("05/01/2016"), 
                getDate("07/01/2016"),
                450, 
                150.5));
        
        list.add(createInstruction(
                "normal", 
                "S", 
                1, 
                "USD", 
                getDate("01/01/2016"), 
                getDate("02/01/2016"),
                350, 
                125.5));
        
        list.add(createInstruction(
                "normal", 
                "S", 
                1, 
                "USD", 
                getDate("05/01/2016"), 
                getDate("09/01/2016"),
                50, 
                25.5));
        
        
        list.add(createInstruction(
                "normal+", 
                "S", 
                1, 
                "USD", 
                getDate("01/01/2016"), 
                getDate("04/01/2016"),
                350, 
                725.5));
        
        
        list.add(createInstruction(
                "new test", 
                "S", 
                0.4, 
                "AED", 
                getDate("24/05/2017"), 
                getDate("26/05/2017"),
                550, 
                1723.5));
        
        return list;
    }
    
    public static Date getDate(String date){
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    
        try {
            return sdf.parse(date);
        } catch (ParseException ex) {
            throw new IllegalArgumentException("Wrong date "+date, ex);
        }
    }
    
    public static Instruction createInstruction(
            String entity, 
            String flag, 
            double agreedFx, 
            String currencyValue, 
            Date instructionDate, 
            Date settlementDate, 
            int units, 
            double pricePerUnit){
        
        
        Instruction instruction = new Instruction();
        
        instruction.setEntity(entity);
        instruction.setFlag(InstructionFlag.valueOf(flag));
        instruction.setAgreedFx(agreedFx);
        instruction.setCurrency(Currency.valueOf(currencyValue));
        instruction.setInstructionDate(instructionDate);
        instruction.setSettlementDate(settlementDate);
        instruction.setUnits(units);
        instruction.setPricePerUnit(pricePerUnit);
        
        return instruction;
    }
}
