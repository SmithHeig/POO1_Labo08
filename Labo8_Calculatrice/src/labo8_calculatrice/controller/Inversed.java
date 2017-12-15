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
public class Inversed extends Operator{
    public Inversed(State state){
        super(state);
    }
    
    public void execute(){
        
        state.empile();

        double denominateur = state.desempile();
        if(denominateur == 0){
            state.flagError();
            state.empile(denominateur);
        }
        else {
            state.setCurrentValue(1 / denominateur);
        }
    }
    
    public String help() {
        return "Calcule l'inverse de current value";
    }
}
