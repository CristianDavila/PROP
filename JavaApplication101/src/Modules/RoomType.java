package Modules;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author 11.2
 */
public class RoomType {
    private String name;
    private List<Integer> related;
    
    /** 
     * Crea una sala.     
     */
    public RoomType() {
        related = new ArrayList();
    }
    
    /** 
     * Crea una sala a partir de los parametros pasados.
     * @param typeName El nombre de la sala.
     * @param idList Los identificadores de los muebles relacionados.
     */
    public RoomType(String typeName, List<Integer> idList) {
        name = typeName;
        related = new ArrayList();
        
        for(Integer idFurniture : idList) {
            related.add(idFurniture);
        }
    }

    /** 
     * Consultora del nombre.
     * @return Nombre de la sala.
     */
    public String getName() {
        return name;
    }
    
    /** 
     * Consultora de los mubles relacionados.
     * @return Los identificadores de los muebles relacionados.
     */
    public List<Integer> getRelatedFurniture() {
        return related;
    }

    /** 
     * Elimina un mueble de la lista de relacionados.
     * @param newId El identificador del mueble a eliminar.
     */
    public void deleteRelatedFurniture(int newId) {
        int position = 0;
        for(Integer idFurniture : related) {
            if(newId == idFurniture) {
                related.remove(position);
                break;
            }
            ++position;
        }
    }
    
    /** 
     * Elimina algunos muebles de la lista de relacionados.
     * @param idList Los identificadores de los muebles a eliminar.
     */
    public void deleteRelatedList(List<Integer> idList) {
        for(Integer idFurniture : idList) {
                deleteRelatedFurniture(idFurniture);
        }
    }
    
}
