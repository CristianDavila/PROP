/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Modules.CjtElements;
import Modules.CjtRestrictions;
import Modules.CjtRoomType;
import Modules.RestrictionDistance;
import Modules.RestrictionRoomType;
import Views.CjtRestrictionsPanel;
import Views.MainFrame;
import java.util.LinkedList;


/** 
 * 
 * @author 11.2
 */
public class CjtRestrictionsController implements Observer{
    private ConfigurationController configController;
    private CjtRestrictions cjtRestrictions;
    private CjtRestrictionsPanel viewCjtRestrictions;
    private CjtElements cjtObjects;
    private CjtRoomType cjtRoomTypes;
    private MainFrame mainFrame;
   
    /** 
     * Crea un controlador de restricciones. 
     * @param newConfigController Enlace al controlador de configuracion.
     * @param newMainFrame Enlace al frame.
     * @param newCjtRestrictions Enlace al conjunto de restricciones.
     * @param newCjtObjects Enlace al conjunto de muebles.
     * @param newCjtRoomTypes Enlace al conjunto de salas.
     * @param cjtObjectsController Enlace al controlador de muebles.
     * @param cjtRoomTypeController Enlace al controlador de salas.
     */
    public CjtRestrictionsController(ConfigurationController newConfigController, MainFrame newMainFrame, CjtRestrictions newCjtRestrictions, CjtElements newCjtObjects, CjtRoomType newCjtRoomTypes, CjtElementsController cjtObjectsController, CjtRoomTypeController cjtRoomTypeController){         
        this.cjtRestrictions = newCjtRestrictions;
        this.cjtObjects = newCjtObjects;
        this.cjtRoomTypes = newCjtRoomTypes;
        this.viewCjtRestrictions = new CjtRestrictionsPanel(this, cjtObjectsController, cjtRoomTypeController);
        this.mainFrame = newMainFrame;
        configController = newConfigController;
    }
    
    @Override
    public void update(int id){
        int object1, object2;
        int i = 1;
        while( i <= this.cjtRestrictions.getRestrictionDistanceSize()){
            object1 = this.cjtRestrictions.getRestrictionDistance(i).getObject1();
            object2 = this.cjtRestrictions.getRestrictionDistance(i).getObject2();
            if(object1 == id || object2 == id){                
                this.cjtRestrictions.removeRestrictionDistance(i);
            }
            else {
                ++i;
            }
        }
        
        i = 1;
        while( i <= this.cjtRestrictions.getRestrictionRoomTypeSize()){
            object1 = this.cjtRestrictions.getRestrictionRoomType(i).getObject2();
            if(object1 == id ){     
                this.cjtRestrictions.removeRestrictionRoomType(i);
            }
            else {
                ++i;
            }
        }         
    }
    
    /** 
     * Muestra el menu de restricciones en la interfaz.     
     */
    public void main(){
       this.mainFrame.cjtRestrictionsPanel();       
    }
    
    /** 
     * Muestra el menu de restricciones en la interfaz.     
     */
    public void mainMenu(){
       this.mainFrame.removeCjtRestrictionsView();
       this.mainFrame.cjtRestrictionsPanel();       
    }
    
    /** 
     * Selecciona el submenu de restricciones a mostrar en la interfaz.   
     * @param op Identifica la opcion escogida.
     */
    public void mainSelector(String op){
        
            if(op.equals("1")){ 
                mainFrame.removeCjtRestrictionsView();
                mainFrame.showRestrictionsPanel();
            }
            else if(op.equals("2")){ 
                mainFrame.removeCjtRestrictionsView();
                mainFrame.removeRestrictionsPanel(); 
            }
            else if(op.equals("3")){ 
                mainFrame.removeCjtRestrictionsView();
                mainFrame.addRestrictionsPanel();
            }
            else if(op.equals("4")){ 
                mainFrame.removeCjtRestrictionsView();
                this.configController.main();
            }
    }
    
    /** 
     * Selecciona el tipo de restricciones a listar en la interfaz.   
     * @param op Identifica la opcion escogida.
     */
    public void showSelector(String op){
        if(op.equals("1")){ 
            mainFrame.removeCjtRestrictionsView();
            mainFrame.showRestrictionsDistancePanel(this.showRestrictionDistance());
        }
        else if(op.equals("2")){ 
            mainFrame.removeCjtRestrictionsView();
            mainFrame.showRestrictionsRoomTypePanel(this.showRestrictionRoomType());
        }
    }
    
    /** 
     * Selecciona el tipo de restricciones a eliminar a traves de la interfaz.   
     * @param op Identifica la opcion escogida.
     */
    public void removeSelector(String op){
        if(op.equals("1")){ 
            mainFrame.removeCjtRestrictionsView();
            mainFrame.removeRestrictionDistancePanel(this.showRestrictionDistance());
        }
        else if(op.equals("2")){ 
            mainFrame.removeCjtRestrictionsView();
            mainFrame.removeRestrictionRoomTypePanel(this.showRestrictionRoomType());
        }
    }
    
    /** 
     * Selecciona el tipo de restricciones a agregar a traves de la interfaz.   
     * @param op Identifica la opcion escogida.
     */
    public void addSelector(String op){
        if(op.equals("1")){
            mainFrame.removeCjtRestrictionsView();
            mainFrame.addRestrictionDistancePanel(configController.getCjtElementsController().getCjtElementsInData());
        }
        else if(op.equals("2")){
            mainFrame.removeCjtRestrictionsView();
            mainFrame.addRestrictionRoomTypePanel(cjtRoomTypes.getRoomTypeList(), getCjtObjectsShort());
        }
    }
    
    /** 
     * Consultora de una restriccion del tipo Distancia.   
     * @param id Identificador de la restriccion.
     * @param list Array en el que se devolveran las caracteristicas de la restriccion.
     */
    public void getRestrictionDistance(int id, String[] list){
        RestrictionDistance R = this.cjtRestrictions.getRestrictionDistance(id);

        list[0] = R.getName();
        list[1] = cjtObjects.getElement(R.getObject1()).getName();
        list[2] = cjtObjects.getElement(R.getObject2()).getName();
        list[3] = Integer.toString(R.getDistance());
    }
    
    /** 
     * Consultora de una restriccion del tipo Sala-Objeto.   
     * @param id Identificador de la restriccion.
     * @param list Array en el que se devolveran las caracteristicas de la restriccion.
     */
    public void getRestrictionRoomType(int id, String[] list){
        RestrictionRoomType R = this.cjtRestrictions.getRestrictionRoomType(id);

        list[0] = R.getName();
        list[1] = cjtRoomTypes.getRoomType(R.getObject1()).getName();
        list[2] = cjtObjects.getElement(R.getObject2()).getName();
        if(R.getAllow()){
            list[3] =  "Si";
        }
        else{
            list[3] = "No";
        }
    }
    
    /** 
     * Consultora, si existen salas.   
     * @return 'true' si no existen salas, 'false' en caso contrario.
     */
    public boolean isRTEmpty(){
        return 0 == cjtRoomTypes.size();
    }
    
    /** 
     * Consultora, si existen muebles.   
     * @return 'true' si no existen muebles, 'false' en caso contrario.
     */
    public boolean isOEmpty(){
        return 0 == cjtObjects.size();
    }
    
    /** 
     * Consultora de las restricciones del tipo Distancia.   
     * @return Matriz con las caracteristicas del todo el conjunto de restricciones de este tipo.
     */
    public String [][] showRestrictionDistance(){  
        String mat [][] = new String [cjtRestrictions.getRestrictionDistanceSize()][4];
        String [] list = new String[4]; 
        for(int id = 1; id <= this.cjtRestrictions.getRestrictionDistanceSize(); ++id){
            getRestrictionDistance(id, list);                 
            for(int i = 0; i < 4; ++i){
                mat[id-1][i] = list[i];
            }
        }
        return mat;
    }
    
    /** 
     * Consultora de las restricciones del tipo Sala-Objeto.   
     * @return Matriz con las caracteristicas del todo el conjunto de restricciones de este tipo.
     */
    public String [][] showRestrictionRoomType(){
        String mat [][] = new String [cjtRestrictions.getRestrictionRoomTypeSize()][4];
        String [] list = new String[4]; 
        for(int id = 1; id <= this.cjtRestrictions.getRestrictionRoomTypeSize(); ++id){
            getRestrictionRoomType(id, list);                 
            for(int i = 0; i < 4; ++i){
                mat[id-1][i] = list[i];
            }
        }
        return mat;
    }
    
    /** 
     * Elimina una restriccion del tipo Distancia.   
     * @param id Identificador de la restriccion.
     * @return 'true' si la restriccion ha sido eliminada, 'false' en caso contrario.
     */
    public boolean removeRestrictionDistance(int id){
        boolean done = (this.cjtRestrictions.getRestrictionDistanceSize() >= id && id > 0);
        if(done){
            this.cjtRestrictions.removeRestrictionDistance(id);
        }
        return done;
    }
    
    /** 
     * Elimina una restriccion del tipo Sala-Objeto.   
     * @param id Identificador de la restriccion.
     * @return 'true' si la restriccion ha sido eliminada, 'false' en caso contrario.
     */
    public boolean removeRestrictionRoomType(int id){
        boolean done = (this.cjtRestrictions.getRestrictionRoomTypeSize() >= id && id > 0);
        if(done){
            this.cjtRestrictions.removeRestrictionRoomType(id);
        }
        return done;
    }
    
    /** 
     * Agrega una restriccion del tipo Distancia.   
     * @param list Array con las caracteristicas de la restriccion a agregar.
     * @return '1' si la restriccion ha sido agregada al conjunto con exito, '0' en caso contrario.
     */
    public int addRestrictionDistance(String[] list){
        boolean err;
        String name = list[0];
        int id1 = Integer.parseInt(list[1]);
        int id2 = Integer.parseInt(list[2]);
        int distance = Integer.parseInt(list[3]);
        
        int idPos[] = this.configController.getCjtElementsController().getIdPos();
        id1 = idPos[id1];
        id2 = idPos[id2];
        
        err = !this.cjtRestrictions.addRestrictionDistance(name, id1, id2, distance);
        if(err){
            return 0;
        }
        else{
            return 1;
        }   
    }
    
    /** 
     * Agrega una restriccion del tipo Sala-Objeto.   
     * @param list Array con las caracteristicas de la restriccion a agregar.
     * @return '1' si la restriccion ha sido agregada al conjunto con exito, '0' en caso contrario.
     */
    public int addRestrictionRoomType(String[] list){
        boolean err, allow;
        err = allow = false;
        String name = list[0];
        int id1, id2;
        if(list[3].equals("Si")){
            allow = true;
        }
        else{
            allow = false;
        }
        
        id1 = this.cjtRoomTypes.getId(list[1]);
        int idPos[] = this.configController.getCjtElementsController().getIdPos();
        id2 = idPos[Integer.parseInt(list[2])];

        err = !this.cjtRestrictions.addRestrictionRoomType(name, id1, id2, allow);
        if(err){
            return 0;
        }
        else{
            return 1;
        }   
    }
    
    /** 
     * Consultora de la relacion entre un mueble y una sala.
     * @param idRoomType Identificador de la sala.
     * @param idObject Identificador del mueble.
     * @return '2' si el mueble debe estar obligatoriamente en la sala, '0' si el mueble tiene prohibido estar en esa sala, '1' si el mueble se puede colocar en la sala   
     */
    public int checkRestrictionRoomTypeRestricted(int idRoomType, int idObject){
        return cjtRestrictions.checkRoomTypeRestricted(idRoomType, idObject);
    }
     
    /** 
     * Consultora de la distancia entre muebles.
     * @param idObject1 Identificador del primer mueble.
     * @param idObject2 Identificador del segundo mueble.
     * @param distance Distancia a consultar.
     * @return 'true' si los muebles se pueden colocar a esta distancia, 'false' en caso contrario
     */
    public boolean checkRestrictionDistance(int idObject1, int idObject2, int distance){
        return cjtRestrictions.checkDistance(idObject1, idObject2, distance);
    }
    
    /** 
     * Consultora de los muebles obligatorios de una sala.
     * @param idRoomType Identificador de la sala.
     * @return Lista de los identificadores de los muebles que deben estar obligatoriamente en una sala.   
     */
    public LinkedList<Integer> getRestrictionRoomTypeMandatory(int idRoomType){
        return cjtRestrictions.getMandatoryObjects(idRoomType);
    }
    
    /** 
     * Consultora del conjunto de muebles.
     * @return Matriz con algunas caracteristicas del conjunto de muebles existente.   
     */
    private String[][] getCjtObjectsShort(){
        int elemSize = configController.getCjtElementsController().getCjtElementSize();
        String listElements[][];
        String data[][];
        if(elemSize<10) {
            data = new String[10][6];
        }
        else{
            data = new String[elemSize][6];
        }
        listElements = configController.getCjtElementsController().getCjtElementsInData();
        
        int i;
        for(i = 0; i < elemSize; ++i){
            data[i][0] = listElements[i][0];
            data[i][1] = listElements[i][1];
            data[i][2] = listElements[i][2];
            data[i][3] = listElements[i][7];
            data[i][4] = listElements[i][8];
            data[i][5] = listElements[i][9];
        }
        if(i < 10){
            data[i][0] = "";
        }
        return data;
        
    }
}