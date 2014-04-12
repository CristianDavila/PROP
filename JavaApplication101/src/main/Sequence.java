/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

/**
 *
 * @author 11.2
 */
public class Sequence {
    int counter;
    int initialValue;
    int finalValue;
    int strideValue;
    boolean isCyclic;
    
    /** 
     * Crea una nueva sequencia con los parametros pasados.
     * @param newInitialValue Valor inicial.
     * @param newFinalValue Valor final.
     * @param newStrideValue Valor del incremento.
     * @param newIsCyclic Booleando que indica si es ciclico.     
     */
    public Sequence (int newInitialValue, int newFinalValue, int newStrideValue, boolean newIsCyclic){
        initialValue = newInitialValue;
        finalValue = newFinalValue;
        strideValue = newStrideValue;
        isCyclic = newIsCyclic;
        counter = initialValue;
    }
    
    /** 
     * Consulta el ultimo valor de la secuencia.
     * @return Valor de la secuencia.   
     */
    public Integer getValue(){
        return (Integer) counter;
    }
    
    /** 
     * Incrementa el ultimo valor de la secuencia.      
     */
    public void increase(){
        counter += strideValue;
        if(isCyclic && counter > finalValue){
            counter = initialValue;
        }
    }
    
    /** 
     * Modifica el ultimo valor de la secuencia.      
     */
    public void setCounter(int key){
        this.counter = key;
    }
}
