package Modules;

/** class restrictionRoomType
 * 
 * @author 11.2
 */
public class RestrictionRoomType extends Restriction {
    private boolean allow;
    
    public RestrictionRoomType() {}
    
    /** 
     * Crea una restriccion de tipo Sala-Objeto a partir de los parametros pasados.
     * @param newName El nombre de la restriccion.
     * @param newRoomType El identificador de la sala.
     * @param newObject El identificador del mueble.
     * @param newAllow Permitir: Booleano que indica si no se permite al mueble estar en la sala o si ha de estar obligatoriamente.
     */
    public RestrictionRoomType(String newName, int newRoomType, int newObject, boolean newAllow) {
        name = newName;
        object1 = newRoomType;
        object2 = newObject;
        allow = newAllow;
    }
    
    /** 
     * Crea una restriccon de tipo Sala-Objeto partir de la restriccion pasada.
     * @param newRestriction La restriccion de la que se quiere hacer copia.     
     */
    public RestrictionRoomType(RestrictionRoomType newRestriction) {
        name = newRestriction.getName();
        object1 = newRestriction.getObject1();
        object2 = newRestriction.getObject2();
        allow = newRestriction.getAllow();
    }
    
    /** 
     * Consultora de Permitir.
     * @return Booleano que indica si no se permite al mueble estar en la sala o si ha de estar obligatoriamente.
     */
    public boolean getAllow() {
        return allow;
    }
    
    /** 
     * Modificadora de Permitir.
     * @param newAllow Booleano que indica si no se permite al mueble estar en la sala o si ha de estar obligatoriamente.
     */
    public void setAllow(boolean newAllow) {
        allow = newAllow;
    }
}
