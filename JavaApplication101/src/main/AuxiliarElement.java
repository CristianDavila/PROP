/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.util.SortedMap;
import java.util.TreeMap;

/**
 *
 * @author 11.2
 */
public class AuxiliarElement {
    
    private Dimension dimensions;
    private int id, x, y, k, flag, m;
    private int posXFinal, posYFinal;
    private int idObject;
    private final int SCALE = 10;
    private boolean placed;
    private String name;
    private SortedMap<Integer, Integer> idDistance;
    
    /**
     * Creadora vacia de AuxiliarElement
     */
    public AuxiliarElement(){        
        x=y=k=flag=m=0;
        placed = false;
        idDistance = new TreeMap();
    }
    
    /**
     * Creadora con parametros de AuxiliarElement
     * @param posX      posicionX del AuxiliarElement
     * @param posY      posicionY del AuxiliarElement
     * @param dimAuxX   dimensionX del AuxiliarElement
     * @param dimAuxY   dimensionY del AuxiliarElement
     * @param idAux     identificador de objeto del AuxiliarElement
     * @param flagAux   flag del AuxiliarElement
     * @param newName   nombre del AuxiliarElement
     */
    public AuxiliarElement(int posX, int posY, int dimAuxX, int dimAuxY, int idAux, int flagAux, String newName){
        idDistance = new TreeMap();
        placed = true;
        this.idObject = idAux;
        this.posXFinal = posX;
        this.posYFinal = posY;
        this.dimensions = new Dimension(dimAuxX, dimAuxY);
        this.flag = flagAux;
        this.name = newName;
    }
    
    /**
     * Asigna la posicion al AuxiliarElement
     * @param a posicion X del AuxiliarElement
     * @param b posicion Y AuxiliarElement
     * @param c valor de K del AuxiliarElement
     * @param d flag del AuxiliarElement
     */
    public void setPos(int a, int b, int c, int d){
        x=a;y=b;k=c;flag=d;      
    }
    
    /**
     * Asigna la posicion final del AuxiliarElement, la que se mostrara en la interfaz
     * @param x posicion final X del AuxiliarElement
     * @param y posicion final Y del AuxiliarElement
     */
    public void setPosFinal(int x, int y){
        this.posXFinal = x*SCALE;
        this.posYFinal = y*SCALE;
    }
    
    /**
     * Asigna el valor de dim a las dimensiones del AuxiliarElement
     * @param dim dimensiones nuevas para AuxiliarElement
     */
    public void setDim(Dimension dim){
        this.dimensions = dim;
    }
    
    /**
     * Modificadora del valor de id de solucion de AuxiliarElement
     * @param id nuevo id para AuxiliarElement
     */
    public void setId(int id){
        this.id = id;
    }
    
    /**
     * Modificadora del valor de id de objeto de AuxiliarElement
     * @param id nuevo id de objeto para AuxiliarElement
     */
    public void setObjectId(int id){
        this.idObject = id;
    }
    
    /**
     * Modificadora del valor de M de AuxiliarElement, para la recolocacion de muebles
     * @param a nuevo valor de M para AuxiliarElement
     */
    public void setM(int a){
        this.m = a;
    }
    
    /**
     * Modificadora del mapa de distancias de AuxiliarElement
     * @param idDistance nuevo map para AuxiliarElement
     */
    public void setDistances(SortedMap idDistance){
        this.idDistance = idDistance;
    }
    
    /**
     * Añade la distancia para id en el map
     * @param id        indentificador de solucion del objeto
     * @param distance  distancia entre AuxiliarElement y el objeto identificado por id
     */
    public void putDistance(int id, int distance){
        idDistance.put(id,distance);
    }
    
    /**
     * Elimina la distancia identificada por id
     * @param id identificador de la distancia a eliminar
     */
    public void removeDistance(int id){
        idDistance.remove(id);
    }
    
    /**
     * Comprueba si existe una distancia identificada por id
     * @param id identificador de la distancia a consultar
     * @return   devuelve si contiene la distancia identificada por id  
     */
    public boolean containsDistance(int id){
        return idDistance.containsKey(id);
    }
    
    /**
     * Modificadora del nombre de AuxiliarElement
     * @param name nuevo nombre para AuxiliarElement
     */
    public void setName(String name){
        this.name = name;
    }
    
    /**
     * Modificadora del valor placed de AuxiliarElement que identifica si ese objeto esta colocado o no
     * @param placed nuevo valor de placed para AuxiliarElement
     */
    public void setPlaced(boolean placed){
        this.placed = placed;
    }
        
    /**
     * Consultora de la Dimension de AuxiliarElement
     * @return Dimension de AuxiliarElement
     */
    public Dimension getDim(){
        return this.dimensions;
    }
    
    /**
     * Consultora del identificador de solucion de AuxiliarElement
     * @return identificador de solucion de AuxiliarElement
     */
    public int getId(){
        return this.id;
    }
    
    /**
     * Consultora del identificador de objeto de AuxiliarElement
     * @return identificador de objeto de AuxiliarElement
     */
    public int getIdObject(){
        return this.idObject;
    }
    
    /**
     * Consultora de la posicion X de AuxiliarElement
     * @return posicion X de AuxiliarElement
     */
    public int getX(){
        return this.x;
    }
    
    /**
     * Consultora de la posicion Y de AuxiliarElement
     * @return posicion Y de AuxiliarElement
     */
    public int getY(){
        return this.y;
    }
    
    /**
     * Consultora de la K de AuxiliarElement para recolocar el auxiliarElement
     * @return valor de K de AuxiliarElement
     */
    public int getK(){
        return this.k;
    }
    
    /**
     * Consultora del flag de AuxiliarElement (0:Arriba, 1:Izquierda, 2:Abajo, 3:Derecha)
     * @return valor del flag de AuxiliarElement
     */
    public int getFlag(){
        return this.flag;
    }
    
    /**
     * Consultora del valor de K de AuxiliarElement para recolocar el AuxiliarElement
     * @return valor de M de AuxiliarElement
     */
    public int getM(){
        return this.m;
    }
    
    /**
     * Consultora de la posicion final de X del AuxiliarElement para la interfaz
     * @return valor de posFinalX
     */
    public int getFinalX(){
        return this.posXFinal;
    }
    
    /**
     * Consultora de la posicion final de Y del AuxiliarElement para la interfaz
     * @return valor de posFinalY
     */
    public int getFinalY(){
        return this.posYFinal;
    }
    
    /**
     * Consultora de la posicion final de X del AuxiliarElement para la solucion, escalado a la matriz de solution
     * @return valor de posFinalX
     */    
    public int getFinalXScaled(){
        return this.posXFinal/SCALE;
    }

     /**
     * Consultora de la posicion final de Y del del AuxiliarElement para la solucion, escalado a la matriz de solution
     * @return valor de posFinalY
     */
    public int getFinalYScaled(){
        return this.posYFinal/SCALE;
    }
    
    /**
     * rota el AuxiliarElement
     */
    public void rotate(){
        int aux = dimensions.getX();
        dimensions.setX(dimensions.getY());
        dimensions.setY(aux);
    }
    
    /**
     * Consultora del nombre del AuxiliarElement
     * @return 
     */
    public String getName(){
        return this.name;
    }
    
    /**
     * Consultora de si el AuxiliarElement esta colocado
     * @return valor de placed
     */
    public boolean isPlaced(){
        return placed;
    }
   
    /**
     * Consultora del map de distancias de AuxiliarElement
     * @return map de distancias de AuxiliarElement
     */
    public SortedMap getDistances(){
        return idDistance;
    }
    
    /**
     * Consultora de la distancia con el objeto identificado por id
     * @param id    identificador de solucion del mueble a consultar la distancia
     * @return      devuelve la distancia entre AuxiliarElement y el objeto identificado por id
     */
    public int getDistance(int id){
        return idDistance.get(id);
    }
}
