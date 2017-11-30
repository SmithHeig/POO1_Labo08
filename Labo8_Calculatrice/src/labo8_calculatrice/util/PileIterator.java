package labo8_calculatrice.util;

import java.util.EmptyStackException;

/**
 * Classe Représentant un itérateur sur la classe Pile
 * @author Adrien Alleman et James Smith
 */
public class PileIterator{
    private PileElement currentEl;
    
    /**
     * Constructeur d'itérateur
     * @param element élément ou doit commencé l'itérateur
     */
    PileIterator(PileElement element){
        currentEl = element;
    }
    
    
    
    /**
     * Permet d'aller à l'élément suivant et de retourner le data de l'element courant.
     * @return l'élément suivant
     */
    public Object next() {
        if(!hasNext()){
            throw new EmptyStackException();
        }
        PileElement temp = currentEl;
        currentEl = currentEl.next;
        return temp.data;
    }
    
    /**
     * Fonction testant si il y a encore des éléments après l'élément courant
     * @return true si il y a un élément suivant et false dans le cas contraire
     */
    public boolean hasNext(){
        return (currentEl != null);
    }
}
