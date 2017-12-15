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
public class Division extends Operator{
    public Division (State state){
        super(state);
    }   
    
    public void execute(){
        
        
        if(state.stackSize() > 0){
            state.empile();
        
            double denominateur = state.desempile();
            double numerateur;
            if(denominateur == 0){
                state.flagError("No division by 0 !");
                state.empile(denominateur);
            }
            else {
                numerateur = state.desempile();
                state.setCurrentValue(numerateur / denominateur);
            }
        } else {
            state.flagError();
        }
    }
    
    public String help() {
        return "Divise le premier nombre de la pile par la current value";
    }
}
