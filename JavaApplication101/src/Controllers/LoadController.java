/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Modules.CjtElements;
import Modules.CjtMap;
import Modules.CjtRestrictions;
import Modules.CjtRoomType;
import Modules.LoadModule;





/**
 *
 * @author 11.2
 */
public class LoadController {    
    
    private CjtElements cjtElement;
    private LoadModule loadModule;
    private CjtRestrictions cjtRestrictions;
    private CjtRoomType cjtRoomType;
    private CjtMap cjtMap;
    
    
    /** 
     * Crea un controlador de carga de ficheros (controlador del gestor de ficheros). 
     * @param newElementos Enlace al conjunto de elementos.     
     * @param newCjtRestrictions Enlace al conjunto de restricciones.
     * @param newCjtRoomType Enlace al conjunto de salas.
     * @param newCjtMap Enlace al conjunto de mapas.     
     */
    public LoadController(CjtElements newElementos, CjtRestrictions newCjtRestrictions, CjtRoomType newCjtRoomType, CjtMap newCjtMap){
        this.cjtRoomType = newCjtRoomType;
        this.cjtElement = newElementos;
        this.cjtRestrictions = newCjtRestrictions;
        this.cjtMap = newCjtMap;
        this.loadModule = new LoadModule(this.cjtElement, this.cjtRestrictions, this.cjtRoomType, this.cjtMap);
    } 
    
    /** 
     * Inicia la carga des de fichero de las cuatro estructuras de datos.     
     */
    public void loadParameters(){
        this.loadModule.loadElement();	
        this.loadModule.loadRestrictions();
        this.loadModule.loadCjtRoomType();
        this.loadModule.loadCjtMap();
    }
    
    /** 
     * Inicia el guardado a fichero de las cuatro estructuras de datos.     
     */
    public void saveParameters(){
        this.loadModule.saveElement();
        this.loadModule.saveRestrictions();
        this.loadModule.saveCjtRoomType();
        this.loadModule.saveCjtMap();
    }
    
    /** 
     * Inicia el borrado completo de los ficheros.     
     */
    public void deleteFiles(){
        this.loadModule.deleteFiles();
    }
    
   
    
    
    
    
}
