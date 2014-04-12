/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import Views.MainFrame;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.SortedMap;
import java.util.Stack;
import java.util.TreeMap;
import main.Algorithm;
import main.AuxiliarElement;
import main.Dimension;
import main.Sequence;
import main.Solution;

/**
 *
 * @author 11.2
 */
public class DesignController {
    
    private FrontController frontController;
    private ConfigurationController configurationController;
    List<Integer> idList;    
    int idRoom, x, y, areaOc;
    private Stack<AuxiliarElement> actualElements;    
    private MainFrame mainFrame;
    private String nameRoom;
    private int[][] posDoor, posWindow;
    
   /** 
     * Crea un controlador de mapas.
     * @param newFrontController Enlace al controlador de frontal.
     * @param newConfigurationController Enlace al controlador de configuracion. 
     * @param newMainFrame Enlace al frame. 
     */
    public DesignController(FrontController newFrontController, ConfigurationController newConfigurationController,MainFrame newMainFrame){
        this.frontController = newFrontController;
        this.configurationController = newConfigurationController;
        this.idList = new ArrayList();          
        mainFrame = newMainFrame;
    }
    
    /** 
     * Inicia el proceso de diseño de una habitacion.  
     * Reune los parametros necesarios y llama a la funcion de la primera visualizacion.
     */
    public void main(){
        idRoom=-1;
        String list[] = frontController.getCjtRoomType().getRoomTypeList();
        int size = frontController.getCjtRoomType().size();
        int maxId = frontController.getCjtRoomType().getCount();
        String dataElements[][] = frontController.getConfigurationController().getCjtRoomTypeController().getFurnitureParams();

        this.mainFrame.designView(maxId,size,null,list,null,dataElements);
        if(idRoom != -1){
            this.frontController.saveDesignedRoom(idRoom, x, y, areaOc, actualElements, this.configurationController.getCjtRoomTypeController().getNameRoomType(idRoom), posDoor, posWindow);        
        }            
    }   
    
    /** 
     * Consulta la variable X de la dimension de la habitacion.
     */
    public int getX(){
        return this.x;
    }
    
    /** 
     * Consulta la variable Y de la dimension de la habitacion.
     */    
    public int getY(){
        return this.y;
    }
    
    /** 
     * Oculta el panel de la vision principal.
     */
    public void removeView(){
        mainFrame.removeDesignView();
    }
    
    public void next(int indx, int x, int y, int idFurniture[],int idElements[]){
        frontController.getConfigurationController().getCjtRoomTypeController().getRoomTypeList();
        int id = frontController.getConfigurationController().getCjtRoomTypeController().getMap().get(indx);
        setXY(x, y);
        nameRoom = frontController.getConfigurationController().getCjtRoomTypeController().getNameRoom(id);
        setIdRoom(id);
        
        SortedMap<Integer,Integer> transRelated = new TreeMap();
        List<Integer> idAux = frontController.getConfigurationController().getCjtRoomTypeController().getFurnitureRoomType(id);
        int aux = 0;
        for(int num : idAux){
            transRelated.put(aux,num);
            ++aux;
        }
        
        for(int pos : idElements){
            int idElm = frontController.getConfigurationController().getCjtRoomTypeController().getMapElems().get(pos);
            idList.add(idElm);
        }
        
        for(int pos : idFurniture){
           int auxa = transRelated.get(pos);
            if(!idList.contains(auxa)){
                idList.add(auxa);
            }
        }
        Collections.sort(idList);
        List<Integer> idM = new ArrayList(configurationController.getCjtRestrictionsController().getRestrictionRoomTypeMandatory(id));
        
        for(int pos = 0; pos < idList.size(); ++pos){
            if(0 == configurationController.getCjtRestrictionsController().checkRestrictionRoomTypeRestricted(id, idList.get(pos))){
                if(mainFrame.skipRestrictionDelete(configurationController.getCjtElementsController().getElement(idList.get(pos)).getName()) != 0){
                    idList.remove(pos);
                }
            }
        }
        
        for(int pos = 0; pos < idM.size(); ++pos){
            if(!idList.contains(idM.get(pos))){
                if(mainFrame.skipRestrictionAdd(configurationController.getCjtElementsController().getElement(idM.get(pos)).getName()) != 0){
                    idList.add(idM.get(pos));
                }
            }
        }
        
        Collections.sort(idList);
        removeView();
        mainFrame.editAmountView(getCjtObjects4Edit());
    }
    
    public void confirmAmount(String[][] data, int numDoor, int numWindow){
        List<Integer> auxList = new ArrayList(idList);
        //auxList = idList;
        idList.clear();
        for(int i = 0; i < auxList.size(); ++i){
            int k = Integer.parseInt(data[i][10]);
            if(k > 0){
                for(int j = 0; j < k; ++j){
                    idList.add(auxList.get(i));
                }
            }
        }
        removeView();
        mainFrame.selectDoorWindow(nameRoom , x, y, numDoor, numWindow);        
    }
    
    /** 
     * Introduce el conjunto de puertas y ventanas y llama a la funcion para resolver el diseño.
     */
    public void setDoorsWindows(int[][] data, int[][] data2){
        this.posDoor = data;
        this.posWindow = data2;
        mainFrame.removeMapEditPanel();
        resolve();
    }
	
    public void resetIdList(){
        idList.clear();
    }
    
    private String[][] getCjtObjects4Edit(){
        int elemSize = configurationController.getCjtElementsController().getCjtElementSize();
        int[] ids = configurationController.getCjtElementsController().getIdPos();
        String listElements[][];
        String data[][];
        if(idList.size() < 10) {
            data = new String[10][11];
        }
        else{
            data = new String[idList.size()][11];
        }
        listElements = configurationController.getCjtElementsController().getCjtElementsInData();
        
        int i;
        int k = 0;
        for(i = 0; i < elemSize && k < idList.size(); ++i){
            if(idList.get(k) == ids[i]){
                for(int j = 0; j < 10; ++j){
                    data[k][j] = listElements[i][j];
                }
                data[k][10] = "1";
                ++k;
            }
        }
        if(k < 10){
            data[k][0] = "";
        }
        return data;
    }
    
    public void showList(int indx) {
        
        int size = frontController.getCjtRoomType().size();
        int maxId = frontController.getCjtRoomType().getCount();
        String list[] = frontController.getConfigurationController().getCjtRoomTypeController().getRoomTypeList();
        int id = frontController.getConfigurationController().getCjtRoomTypeController().getMap().get(indx);
        List<Integer> idFurniture = frontController.getConfigurationController().getCjtRoomTypeController().getFurnitureRoomType(id);
               
        int sizeid;
            
            if(idFurniture != null && idFurniture.size() > 10){
                sizeid = idFurniture.size();
            }
            else {
                sizeid = 10;
            }
            
            String data[][] = new String[sizeid][4];
            
            if(idFurniture != null){
                if(!idFurniture.isEmpty()){
                    int i = 0;
                    String listFurniture[] = new String[10];
                    for(Integer num : idFurniture){
                        frontController.getConfigurationController().getCjtElementsController().getObject(num, listFurniture);
                        data[i][0] = listFurniture[0];
                        data[i][1] = listFurniture[1];
                        data[i][2] = listFurniture[2];
                        data[i][3] = listFurniture[9];
                        ++i;
                    }
                }
            }
            String dataElements[][] = frontController.getConfigurationController().getCjtRoomTypeController().getFurnitureParams();
            mainFrame.showListRelated(idFurniture,data);
    }
    
    /** 
     * Regresa al menu principal, viniendo de la primera visualizacion.
     */
    public void goBack(){
       this.mainFrame.removeDesignView();
       frontController.main();
    }
    
    /** 
     * Regresa al menu principal, viniendo de la segunda visualizacion.
     */
    public void goBackTwo(){
       this.mainFrame.removeMapPanel();
       frontController.main();
    } 
    
    /** 
     * Regresa al menu principal, viniendo de la tercera visualizacion.
     */
    public void goBackThree(){
       this.mainFrame.removeMapEditPanel();
       frontController.main();
    }
    
    /** 
     * Establece el id del tipo de habitacion
     * @param id Representa el Id del tipo de habitacion.
     */
    public void setIdRoom(int id){
        this.idRoom = id;
    }
    
    /** 
     * Consulta la existencia de un tipo de habitacion.
     * @param id Representa el Id del tipo de habitacion.
     */
    public boolean checkId(int id){
        return this.configurationController.getCjtRoomTypeController().check(id);        
    }
    
    /** 
     * Establece los parametros 'x' y 'y' de la dimension de la habitacion.
     * @param x Representa el Id del tipo de habitacion.
     * @param y Representa el Id del tipo de habitacion.
     */
    public void setXY(int x, int y){
        this.x = x;
        this.y = y;
    }
    
    /** 
     * Prepara el inicio de la solucion del diseño
     * Prepara los parametros necesarios y los envia para que sean procesados.     
     */
    public void resolve(){
        Stack<AuxiliarElement> futureObjects;        
        futureObjects = interpretData();
        boolean solucionado = solveProblem(futureObjects);         
        if(solucionado){
            Stack<AuxiliarElement> auxiliar = (Stack)actualElements.clone();
            frontController.getMapController().createMap(idRoom, x, y, auxiliar, nameRoom, posDoor, posWindow);
            callGraphicInterface();
        } 
        else{
            this.mainFrame.notSolutionDesign();
        }
    }
    
    /** 
     * Visualiza la habitacion diseñada
     * Procesa los parametros necesarios para visualizar la habitacion.     
     */
    public void callGraphicInterface(){
        AuxiliarElement elem;
        List<String[]> dat = new ArrayList();        
        Stack<AuxiliarElement> auxiliar = (Stack)actualElements.clone();
        String [] list; 
        while(!auxiliar.isEmpty()){
            elem = auxiliar.peek();
            auxiliar.pop();
            list = new String[6];           
            list[0] = this.configurationController.getCjtElementsController().getElement(elem.getIdObject()).getName();
            list[1] = String.valueOf(elem.getDim().getX());
            list[2] = String.valueOf(elem.getDim().getY());            
            list[5] = String.valueOf(elem.getId());
            switch (elem.getFlag()){
                    case 0:
                        list[3] = String.valueOf(elem.getFinalX());
                        list[4] = String.valueOf(elem.getFinalY());
                        break;
                    case 1:            
                        list[3] = String.valueOf(elem.getFinalX());
                        list[4] = String.valueOf(elem.getFinalY()-elem.getDim().getY()+10);                        
                        break;
                    case 2:
                        list[3] = String.valueOf(elem.getFinalX()-elem.getDim().getX()+10);
                        list[4] = String.valueOf(elem.getFinalY()-elem.getDim().getY()+10);
                        break;
                    case 3:
                        list[3] = String.valueOf(elem.getFinalX()-elem.getDim().getX()+10);
                        list[4] = String.valueOf(elem.getFinalY());
                        break;
            } 
            dat.add(list);            
        } 
        mainFrame.removeDesignView();
        mainFrame.showDesignedRoom(x, y, dat, nameRoom, posDoor, posWindow);
    }
    
    /** 
     * Inicia la solucion del diseño
     * Prepara los parametros necesarios y los introduce al algoritmo para obtener una solucion.     
     */
    public boolean solveProblem(Stack<AuxiliarElement> futureObjects){
        Stack<AuxiliarElement> auxiliar;
        Algorithm alg;
        Solution sol;        
        int ret;
        auxiliar = (Stack)futureObjects.clone();
        if((x*y) < (areaOc*2)/3){            
            return false;
        }
        alg = new Algorithm();
        sol = new Solution(x, y, auxiliar);
        sol.setIdRoom(idRoom);
        int ndoors = 0;
        for(int i = 0; i < posDoor.length; i++){
            if(posDoor[i][0]==0){
                sol.placeDoor(posDoor[i][1]+20, 0, 50, 50, auxiliar.size()+(i+1));
            }else if(posDoor[i][1]==0){
                int stride = posDoor[i][0]+50;
                sol.placeDoor(stride, 3, 50, 50, auxiliar.size()+(i+1));
            }else if((posDoor[i][1]+50)==y){
                sol.placeDoor(posDoor[i][0]+20, 1, 50, 50, auxiliar.size()+(i+1));
            }else if((posDoor[i][0]+50)==x){
                int stride = posDoor[i][1]+50;
                sol.placeDoor(stride+20, 2, 50, 50, auxiliar.size()+(i+1));
            }
            ndoors = (i+1);
        }
        for(int i = 0; i < posWindow.length; i++){
            if(posWindow[i][0]==0){
                sol.placeWindow(posWindow[i][1], 0, 50, 50, auxiliar.size()+(i+ndoors+1));
            }else if(posWindow[i][1]==0){
                int stride = posWindow[i][0]+50;
                sol.placeWindow(stride, 3, 50, 50, auxiliar.size()+(i+ndoors+1));
            }else if((posWindow[i][1]+50)==y){
                sol.placeWindow(posWindow[i][0], 1, 50, 50, auxiliar.size()+(i+ndoors+1));
            }else if((posWindow[i][0]+50)==x){
                int stride = posWindow[i][1]+50;
                sol.placeWindow(stride, 2, 50, 50, auxiliar.size()+(i+ndoors+1));
            }
        }
        ret = alg.forwardCheck(futureObjects, sol,this.configurationController.getCjtRestrictionsController());
        futureObjects = (Stack)auxiliar.clone();        
        if (ret == 0){            
            alg.backtracking(futureObjects, sol, this.configurationController.getCjtRestrictionsController());
        }        
        actualElements = (Stack)auxiliar.clone();         
        
        return !sol.isFail();
    }
    
    /** 
     * Interprete de los elementos
     * Interpreta y prepara los elementos seleccionados para que el algoritmo pueda utilizarlos.     
     */
    public Stack<AuxiliarElement> interpretData(){
        Stack<AuxiliarElement> queue = new Stack();
        AuxiliarElement elem;
        Dimension dim;
        Sequence seq;
        areaOc = 0;
        seq = new Sequence(1,0,1,false);        
        for(int id : idList){            
            dim = this.configurationController.getCjtElementsController().getElement(id).getDimension();
            elem = new AuxiliarElement();
            elem.setObjectId(id);
            elem.setDim(dim);
            elem.setName(this.configurationController.getCjtElementsController().getElement(id).getName());
            elem.setId(seq.getValue());
            seq.increase();
            areaOc = areaOc + (dim.getX() * dim.getY());
            queue.push(elem);
        }        
        return queue;
    }
    
    
    public boolean checkFurniture(int idTarget){
        return this.configurationController.getCjtRoomTypeController().checkRelated(idRoom, idTarget);  
    }
    
    public boolean validation(int[][] posEl, String names[], int[] id){
        boolean valid = true;
        Stack<AuxiliarElement> queueElements = new Stack();
        AuxiliarElement elem;
        
        int xMin = (900-x)/2;
        int yMin = 50 + (900-y)/4;
        
      
        int size = posEl.length;        
        for(int i=0; i<size; ++i){
          
            posEl[i][0]-=xMin;posEl[i][1]-=yMin;
            elem = new AuxiliarElement(posEl[i][0], posEl[i][1], posEl[i][2], posEl[i][3], id[i], 0, configurationController.getCjtElementsController().getElement(idList.get(i)).getName());
            queueElements.push(elem);
        }
     
        
        frontController.saveDesignedRoom(idRoom, x, y, areaOc, queueElements, nameRoom, posDoor, posWindow);
        
        return valid;
    }
}
