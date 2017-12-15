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
public class Backspace extends Operator {
    public Backspace(State state){
        super(state);
    }
    
    public void execute(){
        state.bakspace();
    }
    
    public String help() {
        return "Efface le dernier digit de la curent value";
    }
    
}
