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
public class SquareRoot extends Operator{
    public SquareRoot(State state){
        super(state);
    }   
    
    public void execute(){
        
        state.empile();
        
        double A = state.desempile();

        if(A < 0){
            state.flagError("No sqrt of negative values !");
            state.empile(A);
        } else {
            state.setCurrentValue(Math.sqrt(A));
        }
    }
}
