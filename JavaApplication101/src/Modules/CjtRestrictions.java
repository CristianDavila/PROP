package Modules;

import Controllers.Observer;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;

/**
 * 
 * @author 11.2
 */
public class CjtRestrictions  implements Observer{
    
    private List<RestrictionDistance> cjtResDist;    
    private List<RestrictionRoomType> cjtResRT;    
    
    /** 
     * Crea un conjunto de restricciones.     
     */
    public CjtRestrictions() {
        this.cjtResDist = new ArrayList();
        this.cjtResRT = new ArrayList();        
    }
    
    @Override
    public void update(int id){
        int object1;
        int i = 0;
        while( i < this.cjtResRT.size()){            
            object1 = this.cjtResRT.get(i).getObject1();            
            if(object1 == id ){                       
                this.cjtResRT.remove(i);                
            }
            else ++i;
        }
    }
    
    /** 
     * Consultora de una restriccion de tipo Distancia.
     * @return La restriccion que se quiere obtener.   
     * @param id Identificador de la restriccion.
     */
    public RestrictionDistance getRestrictionDistance(int id){
        return this.cjtResDist.get(id-1);
    }
    
    /** 
     * Consultora de una restriccion de tipo Sala-Objeto.
     * @return La restriccion que se quiere obtener.   
     * @param id Identificador de la restriccion.
     */
    public RestrictionRoomType getRestrictionRoomType(int id){
        return this.cjtResRT.get(id-1);
    }
    
    /** 
     * Consultora del numero de restricciones de tipo Distancia.
     * @return El numero de restricciones de tipo Distancia existentes.   
     */
    public int getRestrictionDistanceSize(){
        return this.cjtResDist.size();
    }
    
    /** 
     * Consultora del numero de restricciones de tipo Sala-Objeto.
     * @return El numero de restricciones de tipo Sala-Objeto existentes.   
     */
    public int getRestrictionRoomTypeSize(){
        return this.cjtResRT.size();
    }
    
    /** 
     * Elimina una restriccion de tipo Distancia.
     * @param id Identificador de la restriccion de tipo Distancia a eliminar.   
     */
    public void removeRestrictionDistance(int id){
        this.cjtResDist.remove(id-1);
    }
    
    /** 
     * Elimina una restriccion de tipo Sala-Objeto.
     * @param id Identificador de la restriccion de tipo Sala-Objeto a eliminar.   
     */
    public void removeRestrictionRoomType(int id){
        this.cjtResRT.remove(id-1);
    }

    /** 
     * Agrega una restriccion de tipo Distancia.
     * @param name Nombre de la restriccion.   
     * @param object1 Identificador del primer mueble.   
     * @param object2 Identificador del segundo mueble.   
     * @param distance Distancia minima entre los objetos.
     * @return 'true' si se ha creado la restriccion, 'false' en caso contrario.
     */
    public boolean addRestrictionDistance(String name, int object1, int object2, int distance){   
        
        for(int i = 1; i <= cjtResDist.size(); ++i){
            if((this.getRestrictionDistance(i).getObject1() == object1 && this.getRestrictionDistance(i).getObject2() == object2)
               || (this.getRestrictionDistance(i).getObject1() == object2 && this.getRestrictionDistance(i).getObject2() == object1)){
                return false;
            }
        }
        RestrictionDistance R = new RestrictionDistance(name, object1, object2, distance);
        this.cjtResDist.add(R);
        return true;
    }
    
    /** 
     * Agrega una restriccion de tipo Sala-Objeto.
     * @param name Nombre de la restriccion.   
     * @param object1 Identificador de la sala.   
     * @param object2 Identificador del mueble.   
     * @param allow Booleano que indica si no se permite al mueble estar en la sala o si ha de estar obligatoriamente.
     * @return 'true' si se ha creado la restriccion, 'false' en caso contrario.
     */
    public boolean addRestrictionRoomType(String name, int object1, int object2, boolean allow){        
        for(int i = 1; i <= cjtResRT.size(); ++i){
            if(this.getRestrictionRoomType(i).getObject1() == object1 && this.getRestrictionRoomType(i).getObject2() == object2){
                return false;
            }
        }
        RestrictionRoomType R = new RestrictionRoomType(name, object1, object2, allow);
        this.cjtResRT.add(R);
        return true;
    }
    
    /** 
     * Consultora de los muebles obligatorios de una sala.
     * @param idRoomType Identificador de la sala.
     * @return Lista de los identificadores de los muebles que deben estar obligatoriamente en una sala.   
     */
    public LinkedList<Integer> getMandatoryObjects(int idRoomType){
        LinkedList<Integer> queue = new LinkedList();
        
        for(int i = 0; i < cjtResRT.size(); ++i){
            if(cjtResRT.get(i).getObject1() == idRoomType && cjtResRT.get(i).getAllow()){
                queue.add(cjtResRT.get(i).getObject2());
            }
        }
        return queue;
    }
    
    /** 
     * Consultora de la relacion entre un mueble y una sala.
     * @param idRoomType Identificador de la sala.
     * @param idObject Identificador del mueble.
     * @return '2' si el mueble debe estar obligatoriamente en la sala, '0' si el mueble tiene prohibido estar en esa sala, '1' si el mueble se puede colocar en la sala   
     */
    public int checkRoomTypeRestricted(int idRoomType, int idObject){
        for(int i = 0; i < cjtResRT.size(); ++i){
            if(cjtResRT.get(i).getObject1() == idRoomType && cjtResRT.get(i).getObject2() == idObject){
                if(cjtResRT.get(i).getAllow()){
                    return 2;
                }
                else{
                    return 0;
                }
            }
        }
        return 1;
    }
    
    /** 
     * Consultora de la distancia entre muebles.
     * @param idObject1 Identificador del primer mueble.
     * @param idObject2 Identificador del segundo mueble.
     * @param distance Distancia a consultar.
     * @return 'true' si los muebles se pueden colocar a esta distancia, 'false' en caso contrario
     */
    public boolean checkDistance(int idObject1, int idObject2, int distance){
        for(int i = 0; i < cjtResDist.size(); ++i){
            if((cjtResDist.get(i).getObject1() == idObject1 && cjtResDist.get(i).getObject2() == idObject2)
                || (cjtResDist.get(i).getObject1() == idObject2 && cjtResDist.get(i).getObject2() == idObject1)){
                if(cjtResDist.get(i).getDistance() > distance){
                    return false;
                }
                else{
                    return true;
                }
            }
        }
        return true;
    }
}
