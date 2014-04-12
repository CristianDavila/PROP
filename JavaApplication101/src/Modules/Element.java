package Modules;
import java.io.Serializable;
import main.Dimension;

/** 
 * 
 * @author 11.2
 */
public class Element implements Serializable {
    
  
    private String name, type;
    private Dimension dimensions, dimensionMax, dimensionMin;
    private boolean isTall, stackable;    
    
    public Element(){}
    
    /** 
     * Crea un elemento a partir de los parametros pasados.
     * @param newName El nombre del elemento.
     * @param newX El atributo X de la dimension del elemento.
     * @param newY El atributo y de la dimension del elemento.
     * @param dimMaxX El atributo X de la dimension maxima del elemento.
     * @param dimMaxY El atributo y de la dimension maxima del elemento.
     * @param dimMinX El atributo X de la dimension minima del elemento.
     * @param dimMinY El atributo y de la dimension minima del elemento.
     * @param newIsTall El del elemento.
     * @param newStackable El del elemento.
     * @param newType El del elemento.
     */
    public Element(String newName, int newX, int newY, int dimMaxX, int dimMaxY, int dimMinX, int dimMinY, boolean newIsTall, boolean newStackable, String newType){
        this.name = newName;
        this.dimensions = new Dimension(newX, newY);
        this.dimensionMax = new Dimension(dimMaxX, dimMaxY);
        this.dimensionMin = new Dimension(dimMinX, dimMinY);
        this.isTall = newIsTall;       
        this.stackable = newStackable; 
        this.type = newType;        
    }
    /** 
     * Crea un elemento a partir del elemento pasado.
     * @param elem El elemento del que se quiere hacer copia.     
     */
    public Element(Element elem){        
        this.name = elem.getName();
        this.dimensions = new Dimension(elem.dimensions.getX(), elem.dimensions.getY());
        this.dimensionMax = new Dimension(elem.dimensionMax.getX(), elem.dimensionMax.getY());
        this.dimensionMin = new Dimension(elem.dimensionMin.getX(), elem.dimensionMin.getY());
        this.isTall = elem.getIsTall();
        this.stackable = elem.getIsStackable(); 
        this.type = elem.getType();
    }
    
    /** 
     * Consultora de la dimension.
     * @return Dimension del elemento.
     */
    public Dimension getDimension(){
        return this.dimensions;
    } 
    
    /** 
     * Consultora de la dimension maxima.
     * @return Dimension maxima del elemento.
     */
    public Dimension getDimensionMax(){
        return this.dimensionMax;
    }
    
    /** 
     * Consultora de la dimension minima.
     * @return Dimension minima del elemento.
     */
    public Dimension getDimensionMin(){
        return this.dimensionMin;
    }
    
    /** 
     * Modificadora de la dimension.     
     */
    public void setDim(int x, int y){
        this.dimensions = new Dimension(x, y);
    }
    
    /** 
     * Consultora de stackable .
     * @return true si puede contener elementos encima, falso en caso contrario.
     */
    public boolean getIsStackable(){
        return this.stackable;
    }
      
    /** 
     * Consultora del nombre del elemento.
     * @return Nombre del elemento.
     */
    public String getName(){
        return this.name;
    }
    
    /** 
     * Modificadora del nombre del elemento.     
     */
    public void setName(String newName){ 
       this.name = newName;        
    }
    
    /** 
     * Consultora de la altura.
     * @return  'true' si es alto, 'false' en caso cotnrario..
     */
    public boolean getIsTall(){
        return this.isTall;
    }
    
    /** 
     * Modificadora de la altura.     
     */
    public void setIsTall(boolean newIsTall){         
       this.isTall= newIsTall;        
    }
    
    /** 
     * Consultora del tipo.
     * @return Tipo de elemento.
     */
    public String getType(){
        return this.type;
    }
    
    /** 
     * Modificadora del tipo.     
     */
    public void setType(String newType){         
       this.name = newType;        
    }
    
}
    



