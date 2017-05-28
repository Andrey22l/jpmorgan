/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jp.morgan.model;

/**
 *
 * @author Andrey
 */
public enum InstructionFlag {
    
    B("outgoing"),  //Buy – outgoing
    S("incoming");   //Sell – incoming
    
    private final String name;
    InstructionFlag(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    
}
