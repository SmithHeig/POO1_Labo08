/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package labo8_calculatrice.view;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Scanner;
import labo8_calculatrice.controller.*;
import labo8_calculatrice.model.State;
import labo8_calculatrice.util.Pair;

/**
 *
 * @author dname
 */
public class Calculator {
    
    private static State state = new State();
    
    //private final static Backspace bs = new Backspace(state);
    
    private LinkedList<Pair> operators = new LinkedList<Pair>();
    private LinkedList<Pair> digits = new LinkedList<Pair>();
    
    //private static Digit[] digits = new Digit[10];
     
    //private static List<Pair<String,Operator>> operators = {};
    
    
    public Calculator(){
        operators.add(new Pair("c", new Clear(state)));
        operators.add(new Pair("ce", new ClearError(state)));
        operators.add(new Pair("/", new Division(state)));
        //operators.add(new Pair(".", new Dot(state));
        //operators.add(new Pair("enter", new Enter(state));
        operators.add(new Pair("inv", new Inversed(state)));
        operators.add(new Pair("mr", new MemoryRecall(state)));
        operators.add(new Pair("ms", new MemoryStore(state)));
        operators.add(new Pair("-", new Minus(state)));
        operators.add(new Pair("*", new Multiply(state)));
        operators.add(new Pair("+", new Plus(state)));
        //operators.add(new Pair("-", new Sign(state)));
        operators.add(new Pair("^", new Square(state)));
        operators.add(new Pair("sqrt", new SquareRoot(state)));
        
        for(int i = 0; i < 10; ++i){
            String s = String.format("%d",i);
            digits.add(new Pair(s,new Digit(state,i)));
        }
        digits.add(new Pair(".",new Dot(state)));
        
        Iterator it = digits.iterator();
        while(it.hasNext()){
            Pair p = (Pair)it.next();
        }
    
        run();
    }
    
    private void run(){
        boolean exit = false;
        Operator enter = new Enter(state);
        System.out.println("java Calculator");
        while (!exit){
            Scanner sc = new Scanner(System.in);
            String s = sc.nextLine();
            Operator op = findOperator(s,operators);
            if(op != null){
                System.out.println("On a un opérateur ! op: " + s);
                op.execute();
            } else{
                for(int i = 0; i < s.length(); ++i){
                    op = findOperator(Character.toString(s.charAt(i)),digits);
                    if(op != null){
                        System.out.println("Ajout d'un chiffre ! chiffre: " + s.charAt(i));
                        op.execute();
                    } else {
                        System.out.println("Ce n'est pas un chiffre ceci Monsieur Frodon!");
                    }
                }
                if(op != null){
                    enter.execute();
                }
            }
            Object[] values = state.getValues();
            System.out.println("size de la pile " + state.stackSize());
            for(int i = 0; i < values.length;++i){
                double d = (double)values[i];
                System.out.print(d + " ");
            }
            System.out.print("\n");
            System.out.println("Current value: " + state.getCurrentValue());
        }
    }
    
    private Operator findOperator(String name, LinkedList l){
        Iterator it = l.iterator();
        while(it.hasNext()){
            Pair p = (Pair)it.next();
            if(p.compareTo(name)){
                return p.operator();
            }
        }
        return null;
    }
}
