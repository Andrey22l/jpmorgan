/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jp.morgan.model;

import com.jp.morgan.utils.Utils;
import java.util.Calendar;
import java.util.Date;
import java.util.Objects;

/**
 * Class for an instruction to buy or sell
 *
 * @author Andrey
 */
public class Instruction {

    //A financial entity whose shares are to be bought or sold
    private String entity;

    //Buy/Sell flag B – Buy – outgoing, S – Sell – incoming
    private InstructionFlag flag;

    //Date on which the instruction was sent to JP Morgan by various clients
    private Date instructionDate;

    //The date on which the client wished for the instruction to be settled with respect to Instruction Date
    private Date settlementDate;

    //Currency of instruction
    private Currency currency;

    //Number of shares to be bought or sold
    private int units;

    //Agreed Fx is the foreign exchange rate with respect to USD that was agreed
    private double agreedFx;

    //Price per unit
    private double pricePerUnit;

    public String getEntity() {
        return entity;
    }

    public void setEntity(String entity) {
        this.entity = entity;
    }

    public InstructionFlag getFlag() {
        return flag;
    }

    public void setFlag(InstructionFlag flag) {
        this.flag = flag;
    }

    public Date getInstructionDate() {
        return instructionDate;
    }

    public void setInstructionDate(Date instructionDate) {
        this.instructionDate = instructionDate;
    }

    public Date getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(Date settlementDate) {
        this.settlementDate = settlementDate;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

    public double getAgreedFx() {
        return agreedFx;
    }

    public void setAgreedFx(double agreedFx) {
        this.agreedFx = agreedFx;
    }

    public double getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(double pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }

    //USD amount of a trade = Price per unit * Units * Agreed Fx
    public double getUSDAmountOfTrade() {
        double price = pricePerUnit * units * agreedFx;
        return Utils.round(price, 2);
    }

    /**
     * If an instructed settlement date falls on a weekend, then the settlement
     * date should be changed to the next working day.
     */
    public Date getSettlementWorkingDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(settlementDate);

        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

        //work week starts Sunday and ends Thursday.
        if (currency.equals(Currency.AED) || currency.equals(Currency.SGP)) {
        } //work week starts Monday and ends Friday,
        else {
            dayOfWeek--;
        }

        //if for USD currency day of week equals Sunday
        if (dayOfWeek == 0) {
            dayOfWeek = 7;
        }

        //change settlement date to next working date
        if (dayOfWeek >= 6) {
            calendar.add(Calendar.DATE, 8 - dayOfWeek);
        }

        return calendar.getTime();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.entity);
        hash = 53 * hash + Objects.hashCode(this.flag);
        hash = 53 * hash + Objects.hashCode(this.instructionDate);
        hash = 53 * hash + Objects.hashCode(this.settlementDate);
        hash = 53 * hash + Objects.hashCode(this.currency);
        hash = 53 * hash + this.units;
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.agreedFx) ^ (Double.doubleToLongBits(this.agreedFx) >>> 32));
        hash = 53 * hash + (int) (Double.doubleToLongBits(this.pricePerUnit) ^ (Double.doubleToLongBits(this.pricePerUnit) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Instruction other = (Instruction) obj;
        if (this.units != other.units) {
            return false;
        }
        if (Double.doubleToLongBits(this.agreedFx) != Double.doubleToLongBits(other.agreedFx)) {
            return false;
        }
        if (Double.doubleToLongBits(this.pricePerUnit) != Double.doubleToLongBits(other.pricePerUnit)) {
            return false;
        }
        if (!Objects.equals(this.entity, other.entity)) {
            return false;
        }
        if (this.flag != other.flag) {
            return false;
        }
        if (!Objects.equals(this.instructionDate, other.instructionDate)) {
            return false;
        }
        if (!Objects.equals(this.settlementDate, other.settlementDate)) {
            return false;
        }
        if (this.currency != other.currency) {
            return false;
        }
        return true;
    }

}
