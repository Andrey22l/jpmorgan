/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jp.morgan.report;

import com.jp.morgan.model.Instruction;
import com.jp.morgan.model.InstructionFlag;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Class to print report
 * 
 * @author Andrey
 */
public class PrintReport {

    public static void printReport(Map<Date, Double> values, String name) {

        System.out.println("Amount in USD settled " + name + " everyday");

        for (Date date : values.keySet()) {
            System.out.println("Date: " + date);
            System.out.println("Amount of Trade: " + values.get(date));
        }

        System.out.println("-----------------------------------------------");

    }

    public static void printReport(List<Instruction> instructionlist) {
        int index = 1;
        System.out.println("Ranking of entities based on incoming and outgoing amount");
        InstructionFlag flag = InstructionFlag.B;
        for (Instruction instruction : instructionlist) {
            if (!flag.equals(instruction.getFlag())) {
                index = 1;
                flag = instruction.getFlag();
            }

            System.out.println("Ranking: " + index++);
            //System.out.println("Flag: " + instruction.getFlag());
            System.out.println("Entity: " + instruction.getEntity());
            System.out.println("Settlement: " + instruction.getSettlementWorkingDate());
            System.out.println("Original Settlement: " + instruction.getSettlementDate());
            System.out.println("Amount of Trade: " + instruction.getUSDAmountOfTrade());

        }

        System.out.println("-----------------------------------------------");

    }

}
