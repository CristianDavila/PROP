/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modules;


import Controllers.CjtElementsController;
import java.util.ArrayList;
import java.util.SortedMap;
import java.util.TreeMap;
import main.Sequence;


/**
 *
 * @author 11.2
 */
public class CjtElements {    
    
    private SortedMap<Integer, Element> cjtElements;   
    private Sequence sequence;  
    private ArrayList<Pack> pack;
    private CjtElementsController cjtElementsController;
    
    /** 
     * Crea un conjunto de elementos.     
     */
    public CjtElements(){        
        this.cjtElements = new TreeMap();
        this.sequence = new Sequence(3, 0, 1, false);   
        pack = new ArrayList();
    }
    
    /** 
     * Modificadora del enlace al controlador del conjunto de elementos. 
     * @param newCjtElementsController Enlace al controlador.
     */
    public void setCjtElementsController(CjtElementsController newCjtElementsController){
        this.cjtElementsController = newCjtElementsController;
    }
    
    /** 
     * Consultora de la existencia de un elemento en el conjunto.
     * @param id Representa el Id del elemento. 
     * @return 'true' si existe, 'false' en caso contrario.
     */
    public boolean existsKey(int id){
        Integer idObj = new Integer(id);
        boolean exist = false;
        if(this.cjtElements.containsKey(idObj)){
            exist = true;
        }
        return exist;
    }
    
    /** 
     * Añade un pack al conjunto de packs.
     * @param x Representa el Id del mueble. 
     * @param y Representa el Id del item. 
     * @param id Representa el Id del pack. 
     * @return 'true' si existe, 'false' en caso contrario.
     */
    public void setPack(int x, int y, int id){
        Pack aux = new Pack(x,y, id);
        pack.add(aux);
    }
    
    /** 
     * Consultora del 'size' del conjunto de packs.     
     * @return Size del pack.
     */
    public int getPackSize(){
        return pack.size();
    }
    
    /** 
     * Consultora de un pack del conjunto de packs. 
     * @param id Representa el Id del pack.
     * @return Pack solicitado.
     */
    public Pack getPack(int id){
        return pack.get(id);
    }
    
    /** 
     * Modificadora de un elemento del conjunto. 
     * @param id Representa el Id del elemento.
     * @param elem Representa el elemento sustituto..
     * @return Pack solicitado.
     */
    public void update(int id, Element elem){
        this.cjtElements.put(id, elem);
    }
    
    /** 
     * Consultora del siguiente Id disponible del conjunto.
     * @return El siguiente id disponible del conjunto.
     */
    public int getCount(){
        return this.sequence.getValue();
    }
    
    /** 
     * Consultora de un elemento del conjunto de elementos. 
     * @param id Representa el Id del elemento.
     * @return Elemento solicitado.
     */
    public Element getElement(int id){        
        Integer idObj = new Integer(id);
        return this.cjtElements.get(idObj);        
    }
    
    /** 
     * Consultora del 'size' del conjunto de elementos.     
     * @return Size del conjunto de elementos.
     */
    public int size(){
        return this.cjtElements.size();
    }    
    
    /** 
     * Elimina un elemento del pack.     
     * @param elem Elimina el elemento con id 'idTarget' y los posibles packs que ha formado.
     */
    public void remove(int idTarget){
        Integer key = new Integer(idTarget);       
       
        if(!cjtElements.get(key).getType().equals("Pack")){            
            for(int i = 0; i < pack.size(); ++i){                
                if(pack.get(i).getIdM() == key || pack.get(i).getIdIt() == key){
                    this.cjtElementsController.delete(pack.get(i).getId());                    
                }
            }
        }
        this.cjtElements.remove(key);        
    }   
    
    /** 
     * Incorpora un nuevo elemento al conjunto.    
     * @param idTarget Representa el nuevo elemento.
     */
    public void set(Element elem){
        Integer key = this.sequence.getValue();   
        this.sequence.increase();
        this.cjtElements.put(key, elem);
    }
    
    /** 
     * Incorpora un nuevo elemento al conjunto en una posicion exacta. 
     * @param Key Representa la posicion del elemento.
     * @param elem Representa el nuevo elemento.
     */
    public void setExact(Integer key, Element elem){
        this.cjtElements.put(key, elem);
    }
    
    /** 
     * Actualiza el ultimo Id disponible.    
     * @param key Representa el Id valido.
     */
    public void setCounter(Integer key){                     
        this.sequence.setCounter(key);
    }
}
