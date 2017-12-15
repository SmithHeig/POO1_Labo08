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
public class ClearError extends Operator{
    public ClearError(State state){
        super(state);
    }   
    
    public void execute(){
        state.CE();
    }
    public String help() {
        return "Supprime l'erreur et remet Pile[0] comme current value";
    }
}
