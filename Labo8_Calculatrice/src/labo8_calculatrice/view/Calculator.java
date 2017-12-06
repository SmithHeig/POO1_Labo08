/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labo8_calculatrice.view;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;
import labo8_calculatrice.controller.Clear;
import labo8_calculatrice.controller.ClearError;
import labo8_calculatrice.controller.Digit;
import labo8_calculatrice.controller.Division;
import labo8_calculatrice.controller.Inversed;
import labo8_calculatrice.controller.MemoryRecall;
import labo8_calculatrice.controller.MemoryStore;
import labo8_calculatrice.controller.Minus;
import labo8_calculatrice.controller.Multiply;
import labo8_calculatrice.controller.Plus;
import labo8_calculatrice.controller.Square;
import labo8_calculatrice.controller.SquareRoot;
import labo8_calculatrice.model.State;
import labo8_calculatrice.util.Pair;

/**
 *
 * @author dname
 */
public class Calculator {
    
    private static State state = new State();
    
    //private final static Backspace bs = new Backspace(state);
    
    private List<Pair> operators = new ArrayList<Pair>();
    
    private static Digit[] digits = new Digit[10];
     
    //private static List<Pair<String,Operator>> operators = {};
    
    
    public Calculator(){
        
        operators.add(new Pair("c", new Clear(state)));
        
        operators.add(new Pair("ce", new ClearError(state)));
        operators.add(new Pair("/", new Division(state)));
        //operators.add(new Pair(".", new Dot(state);
        //operators.add(new Pair("enter", new Enter(state);
        operators.add(new Pair("inv", new Inversed(state)));
        operators.add(new Pair("mr", new MemoryRecall(state)));
        operators.add(new Pair("ms", new MemoryStore(state)));
        operators.add(new Pair("-", new Minus(state)));
        operators.add(new Pair("*", new Multiply(state)));
        operators.add(new Pair("+", new Plus(state)));
        //operators.add(new Pair("-", new Sign(state)));
        operators.add(new Pair("^", new Square(state)));
        operators.add(new Pair("sqrt", new SquareRoot(state)));
    
        run();
    }
    
    private void run(){
        boolean exit = false;
        while (!exit){
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine();
            
        }
    }
}
