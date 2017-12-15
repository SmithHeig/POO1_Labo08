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
public class Minus extends Operator{
    public Minus (State state){
        super(state);
    }
    
    
    public void execute() {
        
        if(state.stackSize() > 0){
            
            state.empile();
            
            double B = state.desempile();
            double A = state.desempile();
            
            state.setCurrentValue(A - B);
        } else {
            state.flagError();
        }
    }
    
    public String help() {
        return "Soustrait la current value au premier nombre de la pile";
    }
}
