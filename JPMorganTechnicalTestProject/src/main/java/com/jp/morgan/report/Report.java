/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jp.morgan.report;

import com.jp.morgan.model.Instruction;
import com.jp.morgan.model.InstructionFlag;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class to generate report
 *
 * @author Andrey
 */
public class Report {

    /**
     * Amount in USD settled incoming everyday
     *
     * @param instructionlist
     * @param flag
     * @return 
     */
    public Map<Date, Double> reportSettled(List<Instruction> instructionlist, InstructionFlag flag) {

        
        Map<Date, Double> groupedInvestments = new HashMap<>();
        for (Instruction instruction : instructionlist) {
            if (instruction.getFlag().equals(flag)) {

                Double amount = groupedInvestments.get(instruction.getSettlementWorkingDate());
                if (amount == null) {
                    groupedInvestments.put(instruction.getSettlementWorkingDate(), instruction.getUSDAmountOfTrade());
                } else {
                    groupedInvestments.put(instruction.getSettlementWorkingDate(), instruction.getUSDAmountOfTrade() + amount);
                }
            }
        }

        return groupedInvestments;
    }

    /**
     * Ranking of entities based on incoming and outgoing amount. Eg: If entity
     * foo instructs the highest amount for a buy instruction, then foo is rank
     * 1 for outgoing
     *
     * @param instructionlist
     * @return 
     */
    public List<Instruction> reportRankingEntities(List<Instruction> instructionlist) {

        Collections.sort(instructionlist, new Comparator<Instruction>() {
            @Override
            public int compare(Instruction o1, Instruction o2) {
                return o1.getUSDAmountOfTrade() > o2.getUSDAmountOfTrade() ? -1 : 1;
            }
        });
        
        Collections.sort(instructionlist, new Comparator<Instruction>() {
            @Override
            public int compare(Instruction o1, Instruction o2) {
                return o1.getFlag().equals(o2.getFlag()) ? 1 : -1;
            }
        });

        return instructionlist;

    }
}
