/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jp.morgan.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 *
 * @author Andrey
 */
public class Utils {

    /**
     * Function to round prices
     * @param value
     * @param places
     * @return 
    */
    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
