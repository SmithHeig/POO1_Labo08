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
    private Sign sign;
    
    public Calculator(){
        operators.add(new Pair("c", new Clear(state)));
        operators.add(new Pair("ce", new ClearError(state)));
        operators.add(new Pair("/", new Division(state)));
        operators.add(new Pair("bs", new Backspace(state)));
        //operators.add(new Pair(".", new Dot(state));
        
        operators.add(new Pair("inv", new Inversed(state)));
        operators.add(new Pair("mr", new MemoryRecall(state)));
        operators.add(new Pair("ms", new MemoryStore(state)));
        operators.add(new Pair("-", new Minus(state)));
        operators.add(new Pair("*", new Multiply(state)));
        operators.add(new Pair("+", new Plus(state)));
        //operators.add(new Pair("-", new Sign(state)));
        operators.add(new Pair("^", new Square(state)));
        operators.add(new Pair("sqrt", new SquareRoot(state)));
        
        // l'opérateur enter est utilisé uniquement depuis le code pas en tant que commande
        sign = new Sign(state);
        
        for(int i = 0; i < 10; ++i){
            String s = String.format("%d",i);
            digits.add(new Pair(s,new Digit(state,i)));
        }
        digits.add(new Pair(".",new Dot(state)));
    
        run();
    }
    
    
    private void run(){
        
        header();
        
        boolean exit = false;
        
        Scanner sc = new Scanner(System.in);    // le lecteur de ligne de commande
        String s;                               // la ligne de "commande" entrée par l'utilisateur
        Operator op;                            // l'opérateur qui sera associé à la ligne de commande
        
        // boucle d'exécution de la calculatrice en console
        while (!exit){
            
            // lecture de la commande entrée par l'utilisateur
            System.out.printf("> ");
            s = sc.nextLine();
            
            s = s.toLowerCase();
            
            
            String[] subtable = s.split(" ");

            for(String sub : subtable) {
            
                if(sub.equals("exit")){
                    break;
                } else if (sub.equals("gui")){
                    new JCalculator().setVisible(true);
                    break;
                }  else if (sub.equals("help")){
                    help();
                    // c'est moches mais ça économise la fin de la boucle inutile
                    continue;
                }
                // association a l'opérateur correspondant
                op = findOperator(sub,operators);

                // si on a trouvé un opérateur du premier coup on va l'exécuter simplement
                if(op != null){
                    op.execute();
                } 
                // sinon c'est soit un nombre soit nimporte quoi !
                else {
                    /* NEW VERSION */
                    // check si c'est un nombre
                    try {
                        boolean negatif = false;

                        // petit ajout pour éviter qu'il considère ".6" comme "6" mais bien comme "0.6"
                        if(sub.startsWith(".") && sub.length() != 1){
                            sub = "0"+sub;
                        } else if(sub.startsWith("-") && sub.length() != 1) {
                            negatif = true;
                            sub = sub.substring(1);
                        }
                        // fait juste un test 
                        Double.parseDouble(sub);
                        // ici une exception sera levée si c'est pas un nombre donc le code suivant ne sera jamais exécuté si c'est pas un nombre correct.
                        // ça permet de gérer tout les problèmes liés au -, au points multiples etc... On ne va pas réinventer la roue pour le plaisir

                        // parcours d'exécution
                        for(int i = 0; i < sub.length(); ++i){
                            findOperator(Character.toString(sub.charAt(i)), digits).execute();
                        }

                        // mis en négatif à la fin parceque on accepte pas de mettre 0 en négatif et que si il est exécuté au début il va essayer de mettre 0 en négatif
                        if(negatif){
                            sign.execute();
                        }
                        // on le flag comme étant un résultat pour qu'il soit push si on entre un nouveau digit
                        // évite d'utiliser l'opérateur enter ici parceque il serait répété si on fait un + par exemple (qui commence par appeler enter)

                        state.flagAsResult();

                    } catch (Exception e){
                        // Ignorer
                        System.out.println("| Ignore {" + sub + "} n'est pas une commande !");
                    }
                }
            }
            

            //affichage de la pile
            Object[] values = state.getValues();
            //System.out.println("size de la pile " + state.stackSize());


            //Affichage
            System.out.print(state.getCurrentValue() + " [ ");

            if(state.stackSize() > 0) {

                // on affiche la pile
                for(int i = 0; i < values.length;++i){
                    double d = (double)values[i];
                    System.out.print(+ d + " ");
                }
            } else {
                // rien a afficher pile vide
            }

            System.out.print("]\n");
        }
    }
    
    /**
     * Cherche l'opérateur dans la liste de paire
     * @param name      le nom de l'opérateur sous forme de string
     * @param l         la liste des paire (string, operator) dans laquelle 
     * @return 
     */
    private Operator findOperator(String name, LinkedList l){
        Iterator it = l.iterator();
        while(it.hasNext()){
            Pair p = (Pair)it.next();
            if(p.equals(name)){
                return p.operator();
            }
        }
        return null;
    }
    
    private void shorthelp(){
        System.out.println("|--------------{ COMMANDES }----------------|");
        System.out.println("| help - Affiche l'aide                     |");
        System.out.println("| gui  - Lance le mode GUI                  |");
        System.out.println("| exit - Quitte la calculatrice             |");
    }
    private void help(){
        
        System.out.println(
            "|-----------------{ HELP }------------------|"
               
        );
        
        shorthelp();
        
        System.out.println(
            "|--------------{ OPERATEURS }---------------|"
        );
        for(Pair p : operators) {
            System.out.println(
                    "| " + String.format("%1$-5s", p.meaning()) + " - " + p.operator().help()
            );
        }
        
        
        System.out.println(
            "|---------------{ END HELP }----------------|"
        );
    }
    
    private void header(){
        
        System.out.println("|-------------------------------------------|");
        System.out.println("|   (        (          (         )         |\n" +
                           "|   )\\     ) )\\      (  )\\   ) ( /(    (    |\n" +
                           "| (((_) ( /(((_)(   ))\\((_| /( )\\())(  )(   |\n" +
                           "| )\\___ )(_))_  )\\ /((_)_ )(_)|_))/ )\\(()\\  |\n" +
                           "|((/ __((_)_| |((_|_))(| ((_)_| |_ ((_)((_) |\n" +
                           "| | (__/ _` | / _|| || | / _` |  _/ _ \\ '_| |\n" +
                           "|  \\___\\__,_|_\\__| \\_,_|_\\__,_|\\__\\___/_|   |");
        System.out.println("|-------------------------------------------|");
        
        shorthelp();
        
        System.out.println("|-------------------------------------------|");
    }
}
