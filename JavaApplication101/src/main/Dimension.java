package main;

/** clase Dimension, define una Dimension o tamaño de un objeto
 * 
 * @author 11.2
 */
public class Dimension implements Comparable {
    private int x;
    private int y;
    
    /** creadora de una Dimension
     crea una Dimension vacia por defecto
     */
    public Dimension() {
        x = 0;
        y = 0;
    }
    
    /** creadora de una Dimension
     *  La dimension es copia de los parametros dados.
     *  @param newX Nuevo valor de X.
     * @param newX Nuevo valor de Y.
     */    
    public Dimension(int newX, int newY) {
        x = newX;
        y = newY;
    }
    

    /** 
     * Consultora del parametro X de la dimension.
     * @return Parametro X de la dimension.
     */
    public int getX() {
        return x;
    }
    
    /** 
     * Consultora del parametro Y de la dimension.
     * @return Parametro Y de la dimension.
     */
    public int getY() {
        return y;
    }
    
    /** define la Dimension X del objeto
     *@param a  tamaño x del objeto
     *@return   devuelve true si se ha asignado la Dimension
                falso si la Dimension no es valida o ha habido un error
     */
    public void setX(int a){
        if (a > 0) {
            x = a;
            
        }else{
            x=0;
        }
    }
    
    /** define la Dimension Y del objeto
     *@param a  tamaño y del objeto
     *@return   devuelve true si se ha asignado la Dimension
                falso si la Dimension no es valida o ha habido un error
     */
    public void setY(int a){
        if (a > 0) {
            y = a;
            
        }else{
            x=0;
        }
    }
    
    /** define las dimensiones X,Y del objeto
     *@param a  tamaño x del objeto
     *@param b  tamaño y del objeto
     *@return   devuelve 1 si se han asignado las dimensiones
                -1 si la Dimension Y no es valida
                -2 si la Dimension X no es valida
     */
    public int setDimensiones(int a, int b){
        if (a > 0) {
            x = a;
        }else {
            return -2;
        }
        if (b > 0) {
            y = b;
        }else {
            return -1;
        }
        return 1;
    }  
   
    
    @Override
    public int compareTo(Object other) {
        Dimension otherDim = (Dimension) other;
        return ((this.x * this.y) - (otherDim.x * otherDim.y));
    }

}