/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labo8_calculatrice.model;

import labo8_calculatrice.util.*;

/**
 *
 * @author dname
 */
public class State {
    private Pile valeurs = new Pile();
    private String currentValue = "0";
    private boolean error = false;
    
    private String memoryValue = "";
    
    public void CE(){
        error = false;
        clean();
    }
    
    public void C(){
        valeurs = new Pile();
        CE();
    }
    
    public void clean(){
        currentValue = "0";
    }
    
    public void bakspace(){
        if(currentValue.length() > 0){
            currentValue = currentValue.substring(0, currentValue.length() - 1);
        }
    }
    
    public void empile(){
        
        valeurs.stack(Double.parseDouble(currentValue));
        clean();
    }

    public void empile(double d){
        valeurs.stack(d);
    }
    
    public void setCurrentValue(double d){
        setCurrentValue(String.valueOf(d));
    }
    
    public void setCurrentValue(String s){
        currentValue = s;
    }
    
    public void addDigit(char digit){
        
        if(currentValue.compareTo("0") == 0){
            if(digit != '0'){
                currentValue = "" + digit;
            }
        } else {
            currentValue += digit;
        }
    }
    
    public void addDot(){
        if(!currentValue.contains(".")){
            currentValue += ".";
        }
    }
    
    public void negateCurrentValue(){
        if(currentValue.compareTo("0") != 0){
            if(currentValue.startsWith("-")){
                currentValue = currentValue.substring(1);
            } else {
                currentValue = "-" + currentValue;
            }
        }
    }
    
    public void memorySave(){
        memoryValue = currentValue;
        clean();
    }
    
    public void memoryLoad(){
        if(memoryValue.compareTo("") != 0){
            currentValue = memoryValue;
        }
    }
    
    public double desempile() {
        return (double)valeurs.unstack();
    }
    
    public int stackSize(){
        return valeurs.size();
    }
    
    public void flagError(){
        error = true;
    }
    
    public String getCurrentValue(){
        return currentValue;
    }
    
    public Object[] getValues(){
        return valeurs.status();
    }
}
