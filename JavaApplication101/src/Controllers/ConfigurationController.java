/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Modules.CjtElements;
import Modules.CjtRestrictions;
import Modules.CjtRoomType;
import Views.MainFrame;
import java.util.ArrayList;



/**
 *
 * @author 11.2
 */
public class ConfigurationController {
         
    private CjtElementsController cjtElementsController;  
    private CjtElements cjtElements;
    private CjtRestrictionsController cjtRestrictionsController;  
    private CjtRestrictions cjtRestrictions;
    private CjtRoomTypeController cjtRoomTypeController;  
    private CjtRoomType cjtRoomType;    
    private ArrayList<Controllers.Observer> observers;
    private FrontController frontController;
    private MainFrame mainFrame;
    
    
    /** 
     * Crea un controlador de restricciones. 
     * @param newFrontController Enlace al controlador frontal.     
     */
    public ConfigurationController(FrontController newFrontController){
        this.frontController = newFrontController;
        this.mainFrame = newFrontController.getWindow();
        this.cjtElements = frontController.getCjtElements();
        this.observers = new ArrayList();
        this.cjtRestrictions = this.frontController.getCjtRestrictions();
        this.cjtRoomType = this.frontController.getCjtRoomType();
        this.cjtElementsController = new CjtElementsController(this, this.cjtElements, this.mainFrame, this.observers);        
        this.cjtRoomTypeController = new CjtRoomTypeController(this,this.cjtRoomType,this.mainFrame, this.cjtElementsController);
        this.cjtRestrictionsController = new CjtRestrictionsController(this, this.mainFrame, this.cjtRestrictions, this.cjtElements, this.cjtRoomType, cjtElementsController, cjtRoomTypeController);
        this.cjtElementsController.addObserver(this.cjtRestrictionsController);
        this.cjtElementsController.addObserver(this.cjtRoomTypeController);        
    }
    
    /** 
     * Visualiza el menu principal de configuracion.          
     */
    public void main(){
        this.mainFrame.configView();       
    }
    
    /** 
     * Selecciona el apartado de configuracion deseado. 
     * @param op Apartado deseado.     
     */
    public void selector(String op){   
       
        if(op.equals("1")){
            mainFrame.removeConfigView();
            this.cjtRestrictionsController.main(); 
        }   
        
        else if(op.equals("2")){
            mainFrame.removeConfigView();
            this.cjtRoomTypeController.main();             
        }
        
        else if(op.equals("3")){    
            mainFrame.removeConfigView();
            this.cjtElementsController.main();              
        }
        
        else if(op.equals("4")){
             this.resetValues();
        }
        
        else if(op.equals("5")){
            mainFrame.removeConfigView();
            this.frontController.main();
        }
    }
    
    /** 
     * Reset de todas las estructuras de datos del programa.         
     */
    public void resetValues(){         
        this.frontController.resetValues();
    }
    
    /** 
     * Consultora del controlador del conjunto de tipos de habitacion.         
     * @return Devuelve el controlador del conjunto de tipos de habitacion.
     */
    public CjtRoomTypeController getCjtRoomTypeController(){         
        return this.cjtRoomTypeController;
    }
    
    /** 
     * Consultora del controlador del conjunto de elementos.         
     * @return Devuelve el controlador del conjunto de elementos.
     */
    public CjtElementsController getCjtElementsController(){
        return this.cjtElementsController;
    }
    
    /** 
     * Consultora del controlador del conjunto de restricciones.         
     * @return Devuelve el controlador del conjunto de restricciones.
     */
    public CjtRestrictionsController getCjtRestrictionsController(){
        return this.cjtRestrictionsController;
    }
}
