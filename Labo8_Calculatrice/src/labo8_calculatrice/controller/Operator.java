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
public abstract class Operator {
    State state;
    
    public Operator(State state){
        this.state = state;
    }
    
    public void execute(){};
    
}
