/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labo8_calculatrice.util;

import labo8_calculatrice.controller.Operator;

/**
 *
 * @author dname
 */
public class Pair {
    private String meaning;
    private Operator operator;
    
    public Pair(String m, Operator op){
        meaning = m;
        operator = op;
    }
    
    public String meaning(){return meaning;}
    public Operator operator(){return operator;}
    
    public boolean compareTo(String s){
        return s.equals(meaning);
    }
}
