/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labo8_calculatrice.model;

import labo8_calculatrice.util.Pile;

/**
 *
 * @author dname
 */
public class State {
    private Pile valeurs = new Pile();
    private String currentValue = "0";
    private boolean error = false;
    private String memoryValue = "";
    
    private boolean isAResult = false;
    
    public void flagAsResult(){
        isAResult = true;
    }
    
    public void flagAsDigit() {
        isAResult = false;
    }
    
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
        if(!error){
            if((currentValue.length() == 2 && currentValue.startsWith("-")) 
                || currentValue.length() == 1 ){
                clean();
            } else if(currentValue.length() > 1){
                currentValue = currentValue.substring(0, currentValue.length() - 1);
            }
        }
    }
    
    public void empile(){
        if(!error){
            valeurs.stack(Double.parseDouble(currentValue));
            clean();
        }
    }

    public void empile(double d){
        if(!error){
            valeurs.stack(d);
        }
    }
    
    public void setCurrentValue(double d){
        if(!error){
            flagAsResult();
            setCurrentValue(String.valueOf(d));
        }
    }
    
    public void setCurrentValue(String s){
        if(!error){
            currentValue = s;
        }
    }
    
    public void addDigit(char digit){
        // si le currentValue n'est pas un nombre entré à la main mais le 
        // résultat d'un calcul on veut qu'il aille sur la pile et que on
        // recommence à entrer un nouveau nombre !
        if(isAResult){
            flagAsDigit();
            empile();
        }
        
        if(!error){
            if(currentValue.compareTo("0") == 0){
                if(digit != '0'){
                    currentValue = "" + digit;
                }
            } else {
                currentValue += digit;
            }
        }
    }
    
    public void addDot(){
        if(!error && !currentValue.contains(".")){
            currentValue += ".";
        }
    }
    
    public void negateCurrentValue(){
        if(!error && currentValue.compareTo("0") != 0){
            if(currentValue.startsWith("-")){
                currentValue = currentValue.substring(1);
            } else {
                currentValue = "-" + currentValue;
            }
        }
    }
    
    public void memorySave(){
        if(!error){
            memoryValue = currentValue;
            clean();
            flagAsDigit();
        }
    }
    
    public void memoryLoad(){
        flagAsResult();
        if(!error && memoryValue.compareTo("") != 0){
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
        currentValue = "Error";
    }
    public void flagError(String s){
        error = true;
        currentValue = s;
    }
    
    public String getCurrentValue(){
        return currentValue;
    }
    
    public Object[] getValues(){
        return valeurs.status();
    }
}
