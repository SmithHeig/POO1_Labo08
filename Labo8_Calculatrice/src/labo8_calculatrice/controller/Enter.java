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
public class Enter extends Operator{
    public Enter(State state){
        super(state);
    }
    
    
    public void execute() {
        state.empile();
    }
    
    public String help() {
        return "Commit la current value sur la pile puis la remplace par 0";
    }
}
