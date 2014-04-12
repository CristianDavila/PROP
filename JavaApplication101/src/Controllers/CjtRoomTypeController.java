/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Modules.CjtRoomType;
import Modules.RoomType;
import Views.CjtRoomTypePanel;
import Views.MainFrame;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedMap;
import java.util.TreeMap;

/**
 * 
 * @author 11.2
 */
public class CjtRoomTypeController implements Observer{
    private CjtRoomType cjtRoomType;
    private CjtRoomTypePanel panel;
    private CjtElementsController elemControl;
    private MainFrame mainFrame;
    private ConfigurationController configController;
    private SortedMap <Integer, Integer> translate,translateObject,transRelated;
  
    /** 
     * Crea un controlador de salas. 
     * @param newConfigController Enlace al controlador de configuracion.
     * @param newCjtRoomType Enlace al conjunto de salas.
     * @param newMainFrame Enlace al frame.
     * @param newElemControl Enlace al controlador de muebles.
     */
    public CjtRoomTypeController(ConfigurationController newConfigController,CjtRoomType newCjtRoomType, MainFrame newMainFrame, CjtElementsController newElemControl) {
        cjtRoomType = newCjtRoomType;
        elemControl = newElemControl;
        mainFrame = newMainFrame;
        configController = newConfigController;
        this.panel = new CjtRoomTypePanel(this,elemControl);
        
        
        
    }

    /** 
     * Muestra el menu de salas en la interfaz.     
     */
    public void main() {        
       this.mainFrame.cjtRoomTypePanel();
        
    }
    
    @Override
    public void update(int id) {
        
        int maxId = this.cjtRoomType.getCount();
        for(int i = 1; i <= maxId; ++i){
            if(this.cjtRoomType.existsKey(i)){
                this.cjtRoomType.getRoomType(i).deleteRelatedFurniture(id);
            }
        }
    }    
    
    /** 
     * Muestra el menu de salas en la interfaz.     
     */
    public void goBackRoomTypeMenu() {
       this.mainFrame.removeCjtRoomTypeView();
       this.mainFrame.cjtRoomTypePanel();
    }
    
    /** 
     * Consultora de la lista de salas.   
     * @return Lista de salas.
     */
    public String[] getRoomTypeList() {
        SortedMap<Integer,Integer> newTranslate = new TreeMap();
        int maxId = this.cjtRoomType.getCount();
        int size = cjtRoomType.size();
        String list[] = new String[size];
        
        boolean existroomtp;
        int j = 0;
        for(int position = 0; position < maxId; ++position) { 
            existroomtp = check(position);
            if(existroomtp){
                String nameRoomType = getNameRoomType(position);
                list[j] = nameRoomType;
                newTranslate.put(j,position);
                ++j;
            }
        }
        setMap(newTranslate);
        return list;
    }
    
    /** 
     * Actuliza la lista de muebles relacionados de una sala.   
     * @param indx Indice de la sala seleccionada.
     */
    public void refreshList(int indx){
        int size = cjtRoomType.size();
        int maxId = this.cjtRoomType.getCount();
        int id = translate.get(indx);
        List<Integer> idFurniture = getFurnitureRoomType(id);
               
        int sizeid;
            
            if(idFurniture != null && idFurniture.size() > 10){
                sizeid = idFurniture.size();
            }
            else {
                sizeid = 10;
            }
            
            String list[] = getRoomTypeList();
            String data[][] = new String[sizeid][4];
            
            if(idFurniture != null){
                if(!idFurniture.isEmpty()){
                    int i = 0;
                    String listFurniture[] = new String[10];
                    for(Integer num : idFurniture){
                        elemControl.getObject(num, listFurniture);
                        data[i][0] = listFurniture[0];
                        data[i][1] = listFurniture[1];
                        data[i][2] = listFurniture[2];
                        data[i][3] = listFurniture[9];
                        ++i;
                    }
                    if(i < 10){
                        data[i][0] = "";
                    }
                }
            }
            mainFrame.removeCjtRoomTypeView();
            mainFrame.showRoomTypePanel(maxId,size,idFurniture,list,data,indx);

    }
    
    /** 
     * Elimina un conjunto de salas.   
     * @param indxArray Indices de las salas seleccionadas a eliminar.
     */
    public void refreshDelete(int indxArray[]){
        for(int num : indxArray){
            int id = translate.get(num);
            delete(id);
        }        
        int size = cjtRoomType.size();
        int maxId = this.cjtRoomType.getCount();
        String list[] = getRoomTypeList();
        mainFrame.removeCjtRoomTypeView();
        mainFrame.removeRoomTypePanel(maxId,size,list);
    }
    
    /** 
     * Actualiza la traduccion de los identificadores.   
     * @param newMap Nuevo mapara para traducir los identificadores existentes.
     */
    public void setMap(SortedMap<Integer, Integer> newMap){
        translate = new TreeMap();
        translate.putAll(newMap);
    }
    
    /** 
     * Selecciona el submenu de salas a mostrar en la interfaz.   
     * @param op Identifica la opcion escogida.
     */
    public void selector(String op) {
        int size = cjtRoomType.size();
        int maxId = this.cjtRoomType.getCount();
        String list[] = getRoomTypeList();
             
            if(op.equals("1")){ 
                mainFrame.removeCjtRoomTypeView();
                mainFrame.showRoomTypePanel(maxId,size,null,list,null,-1); 
            }
            else if(op.equals("2")){
                mainFrame.removeCjtRoomTypeView();
                mainFrame.removeRoomTypePanel(maxId,size,list); 
            }
            else if(op.equals("3")){
                String data[][] = getFurnitureParams();
                mainFrame.removeCjtRoomTypeView();
                mainFrame.addRoomTypePanel(maxId,size,list,data); 
            }
            else if(op.equals("4")){
                mainFrame.removeCjtRoomTypeView();
                mainFrame.editRoomTypePanel();                     
            }
            else{
                mainFrame.removeCjtRoomTypeView();
                this.configController.main();
            }
       
    }
    
    /** 
     * Agrega una nueva sala.   
     * @param arrayIndx Lista de mubles relacionados.
     * @param newName Nombre de la sala.
     */
    public void refreshAdd(int arrayIndx[],String newName) {
        List<Integer> idList = new ArrayList();
        for(int num: arrayIndx){
            idList.add(translateObject.get(num));
        }
        add(newName,idList);
        String data[][] = getFurnitureParams();
        int size = cjtRoomType.size();
        int maxId = this.cjtRoomType.getCount();
        String list[] = getRoomTypeList();
        mainFrame.removeCjtRoomTypeView();
        mainFrame.addRoomTypePanel(maxId,size,list,data);
    }
    
    /** 
     * Selecciona el submenu de salas a mostrar en la interfaz.   
     * @return Matriz con algunas caracteristicas de los mubles existentes.
     */
    public String[][] getFurnitureParams(){
        translateObject = new TreeMap();
        int elemSize = elemControl.getCjtElementSize();
        String listElements[][];
        String data[][];
        if(elemSize<10) {
            data = new String[10][4];
        }
        else{
            data = new String[elemSize][4];
        }
        listElements = elemControl.getCjtElementsInData();
        int i;
        for(i = 0; i < elemSize; ++i){
            data[i][0] = listElements[i][0];
            data[i][1] = listElements[i][1];
            data[i][2] = listElements[i][2];
            data[i][3] = listElements[i][9];
        }
        
        if(i < 10){
            data[i][0] = "";
        }
        
        int num = 0; 
        for(int pos = 0; pos < elemControl.getCjtElementCount(); ++pos){
                if(elemControl.exists(pos)){
                    translateObject.put(num, pos);
                    ++num;
                }
        }
        return data;
        
    }
    
    /** 
     * Selecciona el submenu de edicion de salas a mostrar en la interfaz.   
     * @param op Identifica la opcion escogida.
     */
    public void selectorEdit(int op) {
        int size = cjtRoomType.size();
        int maxId = this.cjtRoomType.getCount();
        String list[] = getRoomTypeList();
        
        switch(op) {
            case 1: 
                String data[][] = getFurnitureParams();
                mainFrame.removeCjtRoomTypeView();
                mainFrame.addRelatedRoomTypePanel(maxId,size,list,data);
                break;
            case 2:
                mainFrame.removeCjtRoomTypeView();
                mainFrame.removeRelatedRoomTypePanel(maxId,size,null,list,null,-1);
                break;
        }
    }
    
    /** 
     * Elimina los muebles relacionados seleccionados de la sala.   
     * @param indx Indice de la sala seleccionada.
     * @param arrayIndx Indices de los muebles relacionados a eliminar.
     */
    public void refreshRemoveRelated(int indx,int arrayIndx[]){
        int id = translate.get(indx);
        transRelated = new TreeMap();
        List<Integer> idFurniture = getFurnitureRoomType(id);
        int pos = 0;
        for(int num : idFurniture){
            transRelated.put(pos,num);
            ++pos;
        }
        
        if(arrayIndx != null){
            List<Integer> deleteFurn = new ArrayList();
            String nameTarget = this.getNameRoomType(id);
            for(int num: arrayIndx){
                deleteFurn.add(transRelated.get(num));
            }
            Collections.sort(idFurniture);
            RoomType newRoomType = new RoomType(nameTarget,idFurniture);
            newRoomType.deleteRelatedList(deleteFurn);
            setUpdate(id,newRoomType);
        }
        int size = cjtRoomType.size();
        int maxId = this.cjtRoomType.getCount();
        
        int sizeid;
            
            if(idFurniture != null && idFurniture.size() > 10){
                sizeid = idFurniture.size();
            }
            else {
                sizeid = 10;
            }
            
            String list[] = getRoomTypeList();
            String data[][] = new String[sizeid][4];
            
            if(idFurniture != null){
                if(!idFurniture.isEmpty()){
                    int i = 0;
                    String listFurniture[] = new String[10];
                    for(Integer num : idFurniture){
                        elemControl.getObject(num, listFurniture);
                        data[i][0] = listFurniture[0];
                        data[i][1] = listFurniture[1];
                        data[i][2] = listFurniture[2];
                        data[i][3] = listFurniture[9];
                        ++i;
                    }
                    if(i < 10){
                        data[i][0] = "";
                    }
                }
            }
        mainFrame.removeCjtRoomTypeView();
        mainFrame.removeRelatedRoomTypePanel(maxId,size,idFurniture,list,data,indx);
    }
    
    /** 
     * Agrega muebles relacionados seleccionados a la sala.   
     * @param indx Indice de la sala seleccionada.
     * @param arrayIndx Indices de los muebles relacionados a agregar.
     */
    public void refreshAddRelated(int indx,int arrayIndx[]){
        int id = translate.get(indx);
        List<Integer> previous = this.getFurnitureRoomType(id);
        for(int num: arrayIndx){
            if(!checkRelated(id,translateObject.get(num))){
                previous.add(translateObject.get(num));
            }
        }
        Collections.sort(previous);
        String nameTarget = this.getNameRoomType(id);
        RoomType newRoomType = new RoomType(nameTarget,previous);
        setUpdate(id,newRoomType);
        
        String data[][] = getFurnitureParams();
        int size = cjtRoomType.size();
        int maxId = this.cjtRoomType.getCount();
        String list[] = getRoomTypeList();
        mainFrame.removeCjtRoomTypeView();
        mainFrame.addRelatedRoomTypePanel(maxId,size,list,data);
    }
    
    /** 
     * Consultora del nombre de una sala.   
     * @param id Identificador de la sala.
     * @return Nombre de la sala.
     */
    public String getNameRoomType(int id) {
        RoomType newRoomType = this.cjtRoomType.getRoomType(id);
        return newRoomType.getName();
    }
    
    /** 
     * Consultora de los muebles relacionados de una sala.   
     * @param id Identificador de la sala.
     * @return Lista de muebles relacionados.
     */
    public List<Integer> getFurnitureRoomType(int id) {
        RoomType newRoomType = this.cjtRoomType.getRoomType(id);
        return newRoomType.getRelatedFurniture();
    }

    /** 
     * Agrega una nueva sala.   
     * @param nameRoomType Nombre de la sala.
     * @param idList Lista de muebles relacionados.
     * @return '1' no se ha podido crear la sala, '0' se ha creado la sala con exito.
     */
    public boolean add(String nameRoomType,List<Integer> idList) {
        RoomType newRoomType;
        boolean err;
        
        err = this.checkName(nameRoomType);
        if(err == false){
            Collections.sort(idList);
            newRoomType = new RoomType(nameRoomType,idList);
            cjtRoomType.set(newRoomType);            
        }
        return err;    
    }

    /** 
     * Elimina una sala.   
     * @param id Identificador de la sala a eliminar.
     */
    public void delete(int idTarget) {
            cjtRoomType.remove(idTarget);
    }
    
    /** 
     * Consultora, si existe una sala con ese identificador.   
     * @param id Identificador de la sala.
     * @return '1' si existe la sala, '0' si no existe ninguna sala con ese identificador.
     */
    public boolean check(int idTarget) {
        return cjtRoomType.existsKey(idTarget);
    }
    
    /** 
     * Consultora, si existe una sala con ese nombre.   
     * @param id Identificador de la sala.
     * @return '1' si existe la sala, '0' si no existe ninguna sala con ese nombre.
     */
    public boolean checkName(String nameTarget) {
        return cjtRoomType.existsName(nameTarget);
    }
    
    /** 
     * Actualiza una sala.   
     * @param idTarget Identificador de la sala.
     * @param newRoomType Sala.
     */
    public void setUpdate(int idTarget,RoomType newRoomType) {
        cjtRoomType.setUpdate(idTarget,newRoomType);
    }
    
    /** 
     * Consultora, si la sala tiene el mueble relacionado.   
     * @param idTarget Identificador de la sala.
     * @param idFurn Identificador del mueble.
     * @return '1' si existe el mueble relacionado, '0' la sala no tiene este mueble relacionado.
     */
    public boolean checkRelated(int idTarget, int idFurn) {
        RoomType newRoomType = this.cjtRoomType.getRoomType(idTarget);
        List<Integer>list = newRoomType.getRelatedFurniture();
        for(Integer idRelated : list) {
            if(idRelated == idFurn){
                return true;
            }
        }
        return false;
    }
    
    /** 
     * Consultora del traductor de identificadores de sala.   
     * @return El mapa de traduccion de identificadores de sala.
     */
    public SortedMap<Integer,Integer> getMap(){
        return translate;
    }
    
    /** 
     * Consultora del traductor de identificadores de muebles relacionados.   
     * @return El mapa de traduccion de identificadores de muebles relacionados.
     */
    public SortedMap<Integer,Integer> getMapElems(){
        return translateObject;
    }
    
    /** 
     * Consultora del nombre de la sala.   
     * @param id Identificador de la sala.
     * @return Nombre de la sala.
     */
    public String getNameRoom(int id){
        return cjtRoomType.getRoomType(id).getName();
    }
}
