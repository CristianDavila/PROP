/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modules;

import java.util.Stack;
import main.AuxiliarElement;
import main.Dimension;

/**
 *
 * @author 11.2
 */
public class Map {
    
    private Dimension dimensionMap;   
    private Stack<AuxiliarElement> elements;
    private int idRoom; //id del tipo de habitacion
    private String nameRoom;
    private int[][] doors, windows;
    
    /** 
     * Crea un map a partir de los parametros pasados.     
     * @param dimX El atributo X de la dimension del mapa.
     * @param dimY El atributo y de la dimension del mapa.
     * @param newIdRoom El atributo Id del identificador del tipo de habitacion.
     * @param newNameRoom El nombre del mapa.
     * @param data El conjunto de puertas.
     * @param data2 El conjunto de ventanas.     
     */
    public Map(int dimX, int dimY, int newIdRoom, String newNameRoom, int[][] data, int[][] data2){              
        this.dimensionMap = new Dimension(dimX, dimY); 
        this.idRoom = newIdRoom;
        this.nameRoom = newNameRoom;
        this.doors = data;
        this.windows = data2;
    }
    /** 
     * Consultora del conjunto de puertas.
     * @return Conjunto de puertas.
     */
    public int[][] getDoors(){
        return doors;
    }
    
    /** 
     * Consultora del conjunto de ventanas.
     * @return Conjunto de ventanas.
     */
    public int[][] getWindows(){
        return windows;
    }
    
    /** 
     * Incorpora un nuevo conjunto de muebles al mapa. 
     * @param elements Representa el conjunto de elementos.    
     */
    public void setCjtElement(Stack<AuxiliarElement> elements){
        this.elements = elements;
    }
    
    /** 
     * Consultora del conjunto de elementos.
     * @return Conjunto de elementos del mapa.
     */
    public Stack<AuxiliarElement> getCjtElement(){
        return this.elements;
    }
    
    /** 
     * Consultora del id del tipo de habitacion.
     * @return Id del tipo de habitacion del mapa.
     */
    public int getIdRoom(){
        return idRoom;        
    }
    
    /** 
     * Consultora de la dimension.
     * @return Dimension del mapa.
     */
    public Dimension getDimensionMap(){
        return dimensionMap;
    }
    
    /** 
     * Consultora del nombre del mapa.
     * @return Nombre del mapa.
     */
    public String getNameRoom(){
        return this.nameRoom;
    }
    
    
    
}
