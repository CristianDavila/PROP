/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import Modules.CjtMap;
import Modules.Map;
import Views.MainFrame;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import main.AuxiliarElement;

/**
 *
 * @author 11.2
 */
public class MapController {
    
    private CjtMap cjtMap;
    private MainFrame mainFrame;
    private FrontController frontController;
    
    
    /** 
     * Crea un controlador de mapas.
     * @param newFrontController Enlace al controlador de frontal.
     * @param newCjtMap Enlace al conjunto te mapas.     
     */
    public MapController(FrontController newFrontController, CjtMap newCjtMap){
        frontController = newFrontController;
        mainFrame = frontController.getWindow();
        cjtMap = newCjtMap;             
    }
    
    /** 
     * Visualiza el menu de mapas.     
     */
    public void main(){
        showMaps();
    }
    
    /** 
     * Reinicia el panel y vuelve a la pantalla principal.     
     */
    public void reset(){
        mainFrame.removeCjtMapView();
        main();
    }
    
    /** 
     * Crea un mapa y lo introduce en el conjunto. 
     * @param idRoom Id del tipo de habitacion.
     * @param x Parametro x de la dimension del mapa.     
     * @param y Parametro y de la dimension del mapa.
     * @param cjtElements Conjunto de elementos que posee el mapa.  
     * @param nameRoom Nombre de la habitacion.
     * @param doors Conjunto de puertas que tiene el mapa.  
     * @param windows Conjunto de ventanas que tiene el mapa.  
     */
    public void createMap(int idRoom, int x, int y,  Stack<AuxiliarElement> cjtElements, String nameRoom, int[][] doors, int[][] windows){         
        cjtMap.setMap(idRoom, x, y, cjtElements, nameRoom, doors, windows);        
    }
    
    /** 
     * Elimina un mapa del conjunto.     
     * Elimina el mapa indentificado por 'idTarget'.
     */
    public void removeMap(int idTarget){
        cjtMap.remove(idTarget);
    }
    
    /** 
     * Consultora de un mapa del conjunto. 
     * @param id Representa el Id del mapa.
     * @return Mapa solicitado.
     */
    public Map getMap(int id){
        return cjtMap.getMap(id);
    }
    
    /** 
     * Regresa al menu principal del programa.     
     */
    public void goBack(){
        mainFrame.removeCjtMapView();
        this.frontController.main();
    }
    
    /** 
     * Regresa a la visualizacion de los mapas, viniendo de la visualizacion de un mapa.     
     */
    public void goBackTwo(){
        mainFrame.removeMapPanel();
        main();
    }  
    
    /** 
     * Regresa a la visualizacion de los mapas, viniendo de la edicion de un mapa.     
     */
    public void goBackEdit(){
        mainFrame.removeMapEditPanel();
        main();
    }  
    
    /** 
     * Introduce todos los nombres de los mapas en una lista y la envia para poder visualizar el menu de mapas.     
     */
    public void showMaps(){
        try{
            
            int size = cjtMap.getCount();
            String list[] = new String[cjtMap.size()];            
            for(int i = 0; i < size; ++i){            
                if(cjtMap.existsKey(i+1)){ 
                    list[i] = cjtMap.getMap(i+1).getNameRoom();
                }
            }   
            mainFrame.cjtMapView(list);            
           
        }catch(Exception e){                
            System.err.println("Invalid parameter type!");
          
        }
    }   
    
    public void showMap(int id, String type){
        Map aux = cjtMap.getMap(id);
        AuxiliarElement elem;
        List<String[]> dat = new ArrayList();
        Stack<AuxiliarElement> auxiliar = (Stack)aux.getCjtElement().clone();
        String [] list;
        int i=0;
        while(!auxiliar.isEmpty()){            
            elem = auxiliar.peek();
            auxiliar.pop();
            list = new String[6];           
            list[0] = elem.getName();
            list[1] = String.valueOf(elem.getDim().getX());
            list[2] = String.valueOf(elem.getDim().getY());
         
            switch (elem.getFlag()){
                    case 0:
                        list[3] = String.valueOf(elem.getFinalX());
                        list[4] = String.valueOf(elem.getFinalY());
                        break;
                    case 1:            
                        list[3] = String.valueOf(elem.getFinalX());
                        list[4] = String.valueOf(elem.getFinalY()-elem.getDim().getY()+1);                        
                        break;
                    case 2:
                        list[3] = String.valueOf(elem.getFinalX()-elem.getDim().getX()+1);
                        list[4] = String.valueOf(elem.getFinalY()-elem.getDim().getY());
                        break;
                    case 3:
                        list[3] = String.valueOf(elem.getFinalX()-elem.getDim().getX());
                        list[4] = String.valueOf(elem.getFinalY());
                        break;
            }  
            
            list[5] = "S";            
            dat.add(list);            
        }
        if(type.equals("2D")){
            mainFrame.removeCjtMapView();
            mainFrame.showMap2D(aux.getDimensionMap().getX(), aux.getDimensionMap().getY(), dat, aux.getNameRoom(), aux.getDoors(), aux.getWindows());
        }       
        //window = new WindowView(aux.getDimensionMap().getX(), aux.getDimensionMap().getY(), dat, aux.getNameRoom());        
    }
}
