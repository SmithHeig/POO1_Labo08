package labo8_calculatrice.util;

/**
 * Classe Représente un élément de la Pile. Contient un Objet
 * @author Adrien Alleman et James Smith
 */
class PileElement {
    Object data;
    PileElement next;
    
    /**
     * Constructeur d'élément de la Pile
     * @param data objet que la PileElement doit contenir
     * @param oldHead l'élément qui était sur le sommet de la pile
     */
    PileElement(Object data, PileElement oldHead){
        this.data = data;
        this.next = oldHead;
    }
}
