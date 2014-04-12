package Modules;

/**
 * 
 * @author 11.2
 */
public class RestrictionDistance extends Restriction {
    private int distance;
    
    public RestrictionDistance() {}
    
    /** 
     * Crea una restriccion de tipo Distancia a partir de los parametros pasados.
     * @param newName El nombre de la restriccion.
     * @param newObject1 El identificador del primer mueble.
     * @param newObject2 El identificador del segundo mueble.
     * @param newDistance La distancia minima entre los muebles.
     */
    public RestrictionDistance(String newName, int newObject1, int newObject2, int newDistance) {
        name = newName;
        object1 = newObject1;
        object2 = newObject2;
        distance = newDistance;
    }
    
    /** 
     * Crea una restriccon a partir de la restriccion pasada.
     * @param newRestriction La restriccion de la que se quiere hacer copia.     
     */
    public RestrictionDistance(RestrictionDistance newRestriction) {
        name = newRestriction.getName();
        object1 = newRestriction.getObject1();
        object2 = newRestriction.getObject2();
        distance = newRestriction.getDistance();
    }
    
    /** 
     * Consultora de la distancia.
     * @return Distancia minima entre los muebles.
     */
    public int getDistance() {
        return distance;
    }
    
    /** 
     * Modificadora de la distancia.
     * @param newDistance Distancia minima entre los muebles.
     */
    public void setDistance(int newDistance) {
        distance = newDistance;
    }
}
