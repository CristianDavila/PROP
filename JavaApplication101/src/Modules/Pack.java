/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modules;

/**
 *
 * @author 11.2
 */
public class Pack {
    
    private int idM, idIt, Id;
    
    /** 
     * Crea un pack a partir de los parametros pasados.
     * @param idM El id del mueble.
     * @param idIt El id del item.
     * @param id El id del pack formado.
     */
    public Pack(int x, int y, int id){
        idM = x;
        idIt = y;
        Id = id;
    }
    /** 
     * Consultora de la variable idM.
     * @return El id del mueble.
     */
    public int getIdM(){
        return idM;
    }
    /** 
     * Consultora de la variable idItId.
     * @return El id del item.
     */
    public int getIdIt(){
        return idIt;
    }
    /** 
     * Consultora de la variable Id.
     * @return El id del pack formado.
     */
    public int getId(){
        return Id;
    }
}
