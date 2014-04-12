/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Modules.CjtElements;
import Modules.Element;
import Views.MainFrame;
import java.util.List;
import main.Dimension;

/**
 *
 * @author 11.2
 */
public class CjtElementsController implements Observable{
    
    private CjtElements cjtElements;    
    private ConfigurationController configurationController;
    private List<Controllers.Observer> observers;    
    private MainFrame mainFrame;
    private int idPos[];
    boolean consult;
    
    /** 
     * Crea un controlador del conjunto de elementos. 
     * @param newConfigurationController Enlace al controlador de configuracion.
     * @param newCjtElements Enlace al conjunto de elementos.
     * @param newMainFrame Enlace al frame.
     * @param newObservers Lista de observadores del conjunto.     
     */
    public CjtElementsController(ConfigurationController newConfigurationController, CjtElements newCjtElements, MainFrame newMainFrame, List<Controllers.Observer> newObservers){         
        this.configurationController = newConfigurationController;
        this.cjtElements = newCjtElements;        
        this.observers = newObservers;
        this.mainFrame = newMainFrame;
        this.cjtElements.setCjtElementsController(this);
    }
    
    /** 
     * Incorpora un nuevo observador. 
     * @param observer Observador.     
     */
    @Override
    public void addObserver(Observer observer){
        observers.add(observer);
    }
    
    /** 
     * Avisa a todos los observadores del elemento eliminado. 
     * @param id Identificador del elemento eliminado.     
     */
    @Override
    public void notifyObserver(int id){
        for(Controllers.Observer observer : observers){
            observer.update(id);
        }
    }
    
    /** 
     * Visualiza el primer menu de la configuracion del conjunto de elementos.
     */
    public void main(){
       mainFrame.cjtElementsPanel(); 
    }
    
    /** 
     * Visualiza el primer menu de la configuracion del conjunto de elementos.
     */
    public void goBack(){
        mainFrame.removeCjtElementsView();
        mainFrame.cjtElementsPanel();
        
    }
    
    /** 
     * Incorpora un nuevo pack al conjunto de elementos.
     * @param namePack Nombre del pack.   
     * @param element1 Identificador del mueble.   
     * @param element2 Identificador del item.   
     */
    public void addPack(String namePack, int element1, int element2){
        Element elem = cjtElements.getElement(element1);
        Element newElement = new Element(namePack, elem.getDimension().getX(), elem.getDimension().getY(),
                                        elem.getDimensionMax().getX(), elem.getDimensionMax().getY(),
                                        elem.getDimensionMin().getX(), elem.getDimensionMin().getY(),
                                        true, false, "Pack");
        cjtElements.set(newElement);
        cjtElements.setPack(element1, element2, (cjtElements.getCount()-1));
    }
    
    /** 
     * Consultora del 'size' del conjunto.
     * @return El 'size' del conjunto.
     */
    public int getCjtElementSize(){
        return cjtElements.size();
    }
    
    /** 
     * Consultora del ultimo id valido.
     * @return El id disponible.
     */
    public int getCjtElementCount(){
        return cjtElements.getCount();
    }
    
    /** 
     * Inicia la operacion deseada.
     * @param op Operacion deseada.
     */
    public void selector(String op){        
        mainFrame.removeCjtElementsView();        
            if(op.equals("1")){                
                 mainFrame.showCjtElementsPanel();
            }
            else if(op.equals("2")){                              
                mainFrame.deleteCjtElementsPanel();
            }                     
            else if(op.equals("3")){
                mainFrame.addMenuCjtElementsPanel();
            }
            else if(op.equals("4")){
                mainFrame.editPanel();          
            }
            else if(op.equals("5")){                                
                this.configurationController.main();
            }       
    }
    
    /** Selecciona el tipo menu para crear elemento.
     * @param op Menu deseado.
     */
    public void selecAddType(String op){        
        mainFrame.removeCjtElementsView();        
            if(op.equals("1")){
                mainFrame.addCjtElementsPanel();
            }
            else if(op.equals("2")){
                mainFrame.addPackCjtElementsPanel();
            }                 
    }    
    
    /** Elimina el elemento deseado.
     * @param idTarget Identificador del elemento que se desea eliminar.
     * Elimina el elemento y avisa a sus observadores.
     */
    public void delete(int idTarget){           
        this.cjtElements.remove(idTarget);
        this.notifyObserver(idTarget);       
    }
    
    /** Consulta la existencia el elemento deseado.
     * @param id Identificador del elemento que se desea consultar su existencia.
     * @return 'true' si existe, 'false' en caso contrario.
     */
    public boolean exists(int id){
        return this.cjtElements.existsKey(id);
    }   
    
    /** Consulta el elemento deseado.
     * @param id Identificador del elemento que se desea consultar.
     * @return Elemento identificador po 'id'.
     */
    public Element getElement(int id){
        return this.cjtElements.getElement(id);
    }
    
    /** Consulta si hay elementos.     
     * @return true si hay elementos, false en caso contrario.
     */
    public boolean consultCjtElementsInData(){
        if(cjtElements.size() == 0) consult = false;
        else consult = true;
        return consult;
    }
    
    /** Consulta los elementos del conjunto.     
     * @return Retorna los elementos en una matriz de Strings, para poder ser procesados por los paneles.
     */
    public String[][] getCjtElementsInData(){
        int size = cjtElements.size();
        if(size < 10) size = 10;
        String data[][] = new String [size][10];
        int id = 0;
        int tam = data[0].length;        
        if(tam == 0){
            data = new String[0][0];
            return data;
        }
        int vec[] = new int[cjtElements.size()];
        for(int i = 1; i <= this.cjtElements.getCount(); ++i){
                    if(exists(i)){                        
                        String[] list = new String[10];
                        getObject(i, list);   
                        data[id]= list;
                        vec[id] = i;
                        ++id;                       
                    }
                }
        if(cjtElements.size() < 10 && cjtElements.size() > 0){
            data[cjtElements.size()][0] = "";
        }
        idPos = vec;        
        return data;
    }
    
    /** Consulta de los identificadores de los elementos del conjunto.     
     * @return Retorna los identificadores de los elementos del conjunto.
     */
    public int[] getIdPos(){
        return idPos;
    }
    
    /** Consulta de los identificadores de los elementos del conjunto.  
     * @param list Vector de string que contiene los parametros del nuevo elemento.
     * @return Retorna el elemento creado con los aprametros del vector de strings.
     * Comprueba que los parametros son validos y crea el elemento.
     */
    public Element checkParameters(String[] list){
        int dimX, dimY, dimMaxX, dimMaxY, dimMinX, dimMinY;        
        boolean err, newIsTall, newStackable;
        Element elem = new Element();
        newIsTall = newStackable = false;
       
        dimMinX = Integer.parseInt(list[5]);
        dimMinY = Integer.parseInt(list[6]);
        dimMaxX = Integer.parseInt(list[3]);
        dimMaxY = Integer.parseInt(list[4]);
        dimX = Integer.parseInt(list[1]);
        dimY = Integer.parseInt(list[2]);
        
        err = (dimMinX < 1) || (dimMinY < 1)
                || (dimMaxX < dimMinX) || (dimMaxY < dimMinY)
                || (dimX < dimMinX) || (dimMaxX < dimX)
                || (dimY < dimMinY) || (dimMaxY < dimY)
                || !(list[7].equals("Si") || (list[7].equals("No"))) || !(list[8].equals("Si") || (list[8].equals("No")));
        
        if(!list[9].equals("Pack") && !list[9].equals("Mueble") && !list[9].equals("Item")) {
            err=true;
        }
        if(err == false){           
            if(list[7].equals("Si")) {
                newIsTall = true;
            }
            if(list[8].equals("Si")) {
                newStackable = true;
            }            
            elem = new Element(list[0], dimX, dimY, dimMaxX, dimMaxY, dimMinX, dimMinY, newIsTall, newStackable, list[9]);           
        }
        if(err == true) {
            elem = null;
        }
        return elem;
    }
    
    /** Consulta un elemento.  
     * @param id Identificador del elemento.
     * @param list Vector de string que contendra los parametros del elemento.
     * @return Retorna 'true' si existia el elemento, 'false' en caso contrario.
     * Comprueba que existe el elemento y introduce sus parametros en 'list'.
     */
    public boolean getObject(int id, String[] list){
        Dimension dim, dimMax, dimMin;
        boolean exist = this.cjtElements.existsKey(id);
        if(exist){            
            Element ob = this.cjtElements.getElement(id);
            dim = ob.getDimension();
            dimMax = ob.getDimensionMax();
            dimMin = ob.getDimensionMin();
        
            list[0] = ob.getName();
            list[1] = Integer.toString(dim.getX());
            list[2] = Integer.toString(dim.getY());
            list[3] = Integer.toString(dimMax.getX());
            list[4] = Integer.toString(dimMax.getY());
            list[5] = Integer.toString(dimMin.getX());
            list[6] = Integer.toString(dimMin.getY());
            if(ob.getIsTall()) {
                list[7] =  "Si";
            }
            else {
                list[7] = "No";
            }
            if(ob.getIsStackable()) {
                list[8] =  "Si";
            }
            else {
                list[8] = "No";
            }
            list[9] = ob.getType();
        }        
        return exist;
    }   
    
    /** Consulta un elemento.  
     * @param id Identificador del elemento.
     * @param list Vector de string que contiene los parametros del nuevo elemento.
     * @return Retorna 'true' si el nuevo elemento es valido, 'false' en caso contrario.
     * Comprueba que el elemento es valido y actualiza un elemento con los parametros en 'list'.
     */
    public boolean update(int id,String[] list){        
        boolean err;
        Element elem = this.checkParameters(list);        
        if(elem == null) {
            err = true;
        }
        else {
            err = false;
        }        
        if(err == false){
           this.cjtElements.update(id, elem);             
        }
        return err;    
    }    
    
    /** Consulta un elemento. 
     
     * @param list Vector de string que contiene los parametros del nuevo elemento.
     * @return Retorna 'true' si el nuevo elemento es valido, 'false' en caso contrario.
     * Comprueba que el elemento es valido y forma un nuevo elemento con los parametros en 'list'.
     */
    public boolean add(String[] list){        
        boolean err;
        Element elem = this.checkParameters(list);        
        if(elem == null) {
            err = true;
        }
        else {
            err = false;
        }         
        if(err == false){            
            this.cjtElements.set(elem); 
        }
        return err;    
    }
}
