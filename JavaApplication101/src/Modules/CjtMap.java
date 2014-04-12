/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modules;

import java.util.SortedMap;
import java.util.Stack;
import java.util.TreeMap;
import main.AuxiliarElement;
import main.Sequence;

/**
 *
 * @author 11.2
 */
public class CjtMap {
    
    private SortedMap<Integer, Map> cjtMap; 
    private Sequence sequence;
    
    /** 
     * Crea un conjunto de mapas.     
     */
    public CjtMap(){
        cjtMap = new TreeMap();
        sequence = new Sequence(1, 0, 1, false);
    }
    
    /** 
     * Consultora de la existencia de un mapa en el conjunto.
     * @param id Representa el Id del elemento. 
     * @return 'true' si existe, 'false' en caso contrario.
     */
    public boolean existsKey(int id) {
        return cjtMap.containsKey(id);
    }
    
    /** 
     * Incorpora al conjunto un mapa nuevo. 
     * @param idRoom Id del tipo de habitacion.
     * @param x Parametro 'x' de la dimension del mapa.
     * @param y Parametro 'y' de la dimension del mapa.
     * @param cjtElem Conjunto de elementos del mapa.
     * @param nameRoom Nombre del mapa.
     * @param doors Conjunto de puertas del mapa.
     * @param windows EConjunto de ventanas del mapa.
     */
    public void setMap(int idRoom, int x, int y, Stack<AuxiliarElement> cjtElem, String nameRoom, int[][] doors, int[][] windows){   
        int key = sequence.getValue();
        sequence.increase();
        Map map = new Map(x, y, idRoom, nameRoom, doors, windows);
        map.setCjtElement(cjtElem);
        cjtMap.put(key, map);
    }
    
    /** 
     * Consultora de un mapa del conjunto.     
     * @return Devuelve el mapa identificado por 'idRoom'.
     */
    public Map getMap(int idRoom){
        return cjtMap.get(idRoom);
    }
    
    /** 
     * Consultora del 'size' del conjunto.     
     * @return Size del conjunto.
     */
    public int size(){
        return cjtMap.size();
    }
    
    /** 
     * Consultora del siguiente Id disponible del conjunto.
     * @return El siguiente id disponible del conjunto.
     */
    public int getCount(){
        return sequence.getValue();
    }
    
     /** 
     * Elimina un elemento del pack.     
     * @param idTarget Elimina el elemento con id 'idTarget' y los posibles packs que ha formado.
     */
    public void remove(int idTarget){           
        cjtMap.remove(idTarget);        
    }   
}
