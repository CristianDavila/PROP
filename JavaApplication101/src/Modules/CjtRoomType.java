/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package Modules;

import Controllers.Observable;
import Controllers.Observer;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;
import main.Sequence;


/**
 * 
 * @author 11.2
 */
public class CjtRoomType implements Observable{
    private SortedMap<Integer, RoomType> cjtRoomType;   
    private Sequence sequence;
    private List<Controllers.Observer> observers;
     
    /** 
     * Crea un conjunto de salas.
     * @param newObservers Lista de observadores.
     */
    public CjtRoomType(List<Controllers.Observer> newObservers) {
        cjtRoomType = new TreeMap();
        sequence = new Sequence(1, 0, 1, false);
        this.observers = newObservers;
    }

    @Override
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }
    
    @Override
    public void notifyObserver(int id){
        for(Controllers.Observer observer : this.observers) {
            observer.update(id);
        }
    }
    
    /** 
     * Consultora de la lista de salas.
     * @return Lista de salas.
     */
    public String[] getRoomTypeList() {
        int maxId = getCount();
        int size = size();
        String list[] = new String[size];
        
        boolean existroomtp;
        int j = 0;
        for(int position = 0; position < maxId; ++position) { 
            existroomtp = existsKey(position);
            if(existroomtp){
                String nameRoomType = getRoomType(position).getName();
                list[j] = nameRoomType;
                ++j;
            }
        }
        return list;
    }
    
    /** 
     * Consultora del identificador de una sala.
     * @param nameTarget Nombre de la sala.
     * @return Identificador de sala.
     */
    public Integer getId(String nameTarget){
        boolean exists = false;
        int res = 0;
        for(int position = 1; (position <= size()) && !exists; ++position){
            if(existsKey(position)) {
                exists = this.getRoomType(position).getName().equals(nameTarget);
                res = position;
            }
        }
        return res;
    }
    
    /** 
     * Consultora, si hay una sala con el identificador deseado.
     * @param id Identificador de la sala.
     * @return '1' si existe una sala con ese identificador, '0' en caso contrario.
     */
    public boolean existsKey(int id) {
        Integer idRoomType = new Integer(id);
        boolean exist = false;
        if(cjtRoomType.containsKey(idRoomType)){
            exist = true;
        }
        return exist;
    }
    
    /** 
     * Consultora, si hay una sala con el nombre deseado.
     * @param nameTarget Nombre de la sala.
     * @return '1' si existe una sala con ese nombre, '0' en caso contrario.
     */
    public boolean existsName(String nameTarget) {
        boolean exists = false;
        for(int position = 1; (position <= getCount()) && !exists; ++position){
            if(existsKey(position)) {
                exists = this.getRoomType(position).getName().equals(nameTarget);
            }
        }
        return exists;
    }
    
    /** 
     * Consultora del primer identificador sin asignar.
     * @param nameTarget Nombre de la sala.
     * @return Primer identificador sin asignar.
     */
    public int getCount() {
        return this.sequence.getValue();
    }
    
    /** 
     * Consultora de sala.
     * @param id Identificador de la sala.
     * @return Sala con el identificador deseado.
     */
    public RoomType getRoomType(int id) {        
        Integer idRoomType = new Integer(id);
        return cjtRoomType.get(idRoomType);        
    }
    
    /** 
     * Consultora de 'size'.
     * @return Numero de salas existentes.
     */
    public int size() {
        return this.cjtRoomType.size();
    }
    
    /** 
     * Elimina la sala con el identificador deseado.
     * @param idTarget Identificador de la sala a eliminar.
     */
    public void remove(int idTarget) {
        Integer key = new Integer(idTarget);
        this.cjtRoomType.remove(key);
        this.notifyObserver(idTarget);
    }

    /** 
     * Actualiza la sala con el identificador deseado.
     * @param idTarget Identificador de la sala a actualizar.
     * @param newRoomType Sala.
     */
    public void setUpdate(int idTarget, RoomType newRoomType) {
        Integer key = new Integer(idTarget);
        this.cjtRoomType.remove(key);
        this.cjtRoomType.put(key, newRoomType);
    }
    
    /** 
     * Actualiza el primer identificador sin asignar.
     * @param key Primer identificador sin asignar.
     */
    public void setCounter(Integer key) {                     
        this.sequence.setCounter(key);
    }
    
    /** 
     * Agrega la sala al conjunto.
     * @param newRoomType Sala a agregar.
     */
    public void set(RoomType newRoomType) {
        Integer key = this.sequence.getValue();   
        this.sequence.increase();
        this.cjtRoomType.put(key, newRoomType);
    }
        
}
