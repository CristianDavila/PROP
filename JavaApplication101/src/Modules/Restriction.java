package Modules;

/** class restriction
 * 
 * @author 11.2
 */
public abstract class Restriction {
    String name;
    int object1;
    int object2;
    
    /** 
     * Consultora del nombre de la restriccion.
     * @return Nombre de la restriccion.
     */
    public String getName() {
        return name;
    }
	
    /** 
     * Modificadora del nombre de la restriccion.
     * @param newName Representa el nuevo nombre que se asignara a la restriccion. 
     */
    public void setName(String newName) {
        name = newName;
    }
    
    /** 
     * Consultora del primer objeto.
     * @return Identificador del primer objeto.
     */
    public int getObject1() {
        return object1;
    }
    
    /** 
     * Modificadora del primer objeto.
     * @param newObject1 Identificador del nuevo primer objeto. 
     */
    public void setObject1(int newObject1) {
        object1 = newObject1;
    }
    
    /** 
     * Consultora del segundo objeto.
     * @return Identificador del segundo objeto.
     */
    public int getObject2() {
        return object2;
    }
    
    /** 
     * Modificadora del segundo objeto.
     * @param newObject2 Identificador del nuevo segundo objeto. 
     */
    public void setObject2(int newObject2) {
        object2 = newObject2;
    }
}
