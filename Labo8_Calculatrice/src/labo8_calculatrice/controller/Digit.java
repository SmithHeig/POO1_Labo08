/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labo8_calculatrice.controller;

import labo8_calculatrice.model.State;

/**
 *
 * @author James
 */
public class Digit extends Operator{
    
    char digit;
    public Digit (State state, int d){
        super(state);
        digit = (char)(d + '0');
    }   
    
    public void execute() {
        
        state.addDigit(digit);
    }
}
