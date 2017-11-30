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
public class Sign extends Operator{
    public Sign(State state){
        super(state);
    }    
    
    public void execute(){
        
        state.negateCurrentValue();
    }
}
