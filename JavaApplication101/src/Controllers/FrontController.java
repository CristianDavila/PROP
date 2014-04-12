/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;
 
import Modules.CjtElements;
import Modules.CjtMap;
import Modules.CjtRestrictions;
import Modules.CjtRoomType;

import Views.MainFrame;
import java.util.ArrayList;


import java.util.Stack;
import main.AuxiliarElement;

/**
 *
 * @author 11.2
 */
public class FrontController {
   
    private ConfigurationController controllerConfiguration;    
    private CjtElements cjtElements;  
    private CjtRestrictions cjtRestrictions;
    private LoadController loadController;
    private CjtRoomType cjtRoomType;
    private ArrayList<Controllers.Observer> observers;
    private DesignController designController;
    private MapController mapController;
    private CjtMap cjtMap;
    private MainFrame mainFrame; 
   
    /** 
     * Crea un controlador frontal y incializa todas sus estructuras      
     */
    public FrontController(){        
        init();
    }  
    private void init(){
        this.cjtElements = new CjtElements();
        this.cjtMap = new CjtMap();
        this.mainFrame = new MainFrame(this);
        this.cjtRestrictions = new CjtRestrictions();
        this.observers = new ArrayList();
        this.cjtRoomType = new CjtRoomType(this.observers);
        this.controllerConfiguration = new ConfigurationController(this);  
        this.loadController = new LoadController(this.cjtElements, this.cjtRestrictions, this.cjtRoomType, this.cjtMap);
        this.cjtRoomType.addObserver(this.cjtRestrictions);
        this.designController = new DesignController(this, this.controllerConfiguration,this.mainFrame);
        this.mapController = new MapController(this, cjtMap);      
    }
   
    /** 
     * Visualiza el menu.      
     */
    public void main(){           
        mainFrame.frontView();        
    }
    
    /** 
     * Carga todas las estructuras de datos.      
     */
    public void loadParameters(){
        this.loadController.loadParameters();
    }
    
    /** 
     * Guarda todas las estructuras de datos.      
     */
    public void saveParameters(){
        this.loadController.saveParameters();        
    }
   
    /** 
     * Selecciona la operacion deseada.
     * @param op Operacion deseada.
     */
    public void selector(String op){ 
        mainFrame.removeFrontView();
        if(op.equals("1")){
                this.designController.main();                             
        }       
        else if(op.equals("2")){
                this.controllerConfiguration.main();                
        }
        else if(op.equals("3")){
                this.mapController.showMaps();
        }
        else if(op.equals("exit")){           
                this.saveParameters();
                mainFrame.close();
                System.exit(0);
        } 
    }   
    
    /** 
     * Consultora del conjunto de elementos.   
     */
    public CjtElements getCjtElements(){
        return this.cjtElements;
    }
    
    /** 
     * Consultora del conjunto de tipos de habitacion.     
     */
    public CjtRoomType getCjtRoomType(){
        return this.cjtRoomType;
    }
    
    /** 
     * Consultora del conjunto de restricciones.     
     */
    public CjtRestrictions getCjtRestrictions(){
        return this.cjtRestrictions;
    }
    
    /** 
     * Consultora del controlador de configuracion.     
     */
    public ConfigurationController getConfigurationController(){        
        return this.controllerConfiguration;
    }
    
    /** 
     * Consultora del controlador de mapas.
     */
    public MapController getMapController(){
        return this.mapController;
    }
    
    /** 
     * Consultora del controlador de diseño. 
     */
    public DesignController getDesignController(){
        return this.designController;
    }
    
    /** 
     * Resetea todos los valores de las estructuras de datos.    
     */
    public void resetValues(){              
        this.loadController.deleteFiles();
        this.init();       
    }
    
    /** 
     * Consultora del frame.    
     */
    public MainFrame getWindow(){
        return this.mainFrame;
    }
    
    /** 
     * Guarda una habitacion diseñada. 
     * @param idRoom Identificador del tipo de habitacion.
     * @param x Parametro X de la dimension del mapa.
     * @param y Parametro Y de la dimension del mapa.
     * @param areaOc Area ocupada por los elementos del mapa.
     * @param cjtElem Conjunto de elementos.
     * @param nameRoom Nombre del mapa.
     * @param doors Conjunto de puertas.
     * @param windows Conjunto de ventanas.
     */
    public void saveDesignedRoom(int idRoom, int x, int y, int areaOc, Stack<AuxiliarElement> cjtElem, String nameRoom, int[][] doors, int[][] windows){        
        this.mapController.createMap(idRoom, x, y, cjtElem, nameRoom, doors, windows);         
    }
}