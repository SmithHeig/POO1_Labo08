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
public class Square extends Operator{
    public Square(State state){
        super(state);
    }
    
    public void execute(){
        
        state.empile();
        
        if(state.stackSize() > 0){
            double A = state.desempile();
            
            state.setCurrentValue(A * A);
        }
    }
}
