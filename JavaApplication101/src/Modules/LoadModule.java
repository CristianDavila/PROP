/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Modules;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import main.AuxiliarElement;

/**
 *
 * @author 11.2
 */
public class LoadModule {
    
    private CjtElements cjtElement;
    private CjtRestrictions cjtRestrictions;   
    private CjtRoomType cjtRoomType;
    private CjtMap cjtMap;
    
    /** 
     * Crea un controlador de restricciones. 
     * @param newCjtElements Enlace al conjunto de elementos.
     * @param newCjtRoomType Enlace al conjunto de tipos de habitacion.
     * @param newCjtRestrictions Enlace al conjunto de restricciones.
     * @param newCjtMap Enlace al conjunto de mapas.     
     */
    public LoadModule(CjtElements newCjtElements, CjtRestrictions newCjtRestrictions, CjtRoomType newCjtRoomType, CjtMap newCjtMap){
        this.cjtElement = newCjtElements;
        this.cjtRoomType = newCjtRoomType;
        this.cjtRestrictions = newCjtRestrictions;
        this.cjtMap = newCjtMap;
    }
    
    /** 
     * Invoca a las funciones para eliminar las cuatro estructuras de datos.       
     */
    public void deleteFiles(){        
        this.deleteFileElement();
        this.deleteFileRestrictions();
        this.deleteFileCjtRoomType();
        this.deleteFileCjtMap();
    }
    
    /** 
     * Elimina los datos del fichero "cjtroomtype.txt".       
     */
    public void deleteFileCjtRoomType(){
        FileWriter file;
        PrintWriter writer;        
        try{
            file = new FileWriter("cjtroomtype.txt");            
            writer = new PrintWriter(file);            
            writer.write("");
            writer.println(0);
            writer.println("");
            writer.println(0);
            writer.println("");
            writer.println("##### CjtRoomType  #####");
            writer.println("");            
            writer.flush();
        }catch (Exception e) { 
            System.err.println("Error");
        }
    
    }
    
    /** 
     * Elimina los datos del fichero "restrictions.txt".       
     */
    private void deleteFileRestrictions(){
        FileWriter file;
        PrintWriter writer;        
        try{
            file = new FileWriter("restrictions.txt");            
            writer = new PrintWriter(file);            
            writer.write("");
            writer.println(0);
            writer.println("");
            writer.println(0);
            writer.println("");
            writer.println("##### RestrictionDistance #####");
            writer.println("");
            writer.println("##### RestrictionRoomType #####");
            writer.println("");
            writer.flush();
        }catch (Exception e) { 
            System.err.println("Error");
        }
    }
    
    /** 
     * Elimina los datos del fichero "elements.txt".       
     */
    private void deleteFileElement(){
        FileWriter file;
        PrintWriter writer;
        try{            
            file = new FileWriter("elements.txt");            
            writer = new PrintWriter(file);                        
            writer.println("0");
            writer.println("");
            writer.println("0");
            writer.println("");
            writer.flush();
            
        }catch (Exception e) { 
            System.err.println("Error");
        }
    }
    
    /** 
     * Elimina los datos del fichero "cjtmap.txt".       
     */
    private void deleteFileCjtMap(){
        FileWriter file;
        PrintWriter writer;
        try{            
            file = new FileWriter("cjtmap.txt");            
            writer = new PrintWriter(file);                        
            writer.println("0");
            writer.println("");
            writer.println("0");
            writer.println("");
            writer.println("##### CjtMap  #####");
            writer.println("");
            writer.flush();
            
        }catch (Exception e) { 
            System.err.println("Error");
        }
    }
    
    /** 
     * Carga el conjunto de elementos del fichero "elements.txt".       
     */
    public void loadElement(){
        String parameters[] = new String[11];
        Element elem;
        int lastKey, key, dimX, dimY, dimMaxX, dimMaxY, dimMinX, dimMinY, size, aux, x, y, id;
        boolean newIsTall, newStackable;
	File file;
	FileReader fr;
	BufferedReader reader;       
	try {			
            file = new File("elements.txt");
            if(file.length() > 0){
                fr = new FileReader(file);
		reader = new BufferedReader(fr);		
                String linea;                        
                linea = reader.readLine();
                size = Integer.parseInt(linea); 
                if(size > 0){
                    reader.readLine();
                    linea = reader.readLine();
                    lastKey = Integer.parseInt(linea);
                    reader.readLine();
                    reader.readLine();
                    this.cjtElement.setCounter(lastKey);                       
                    reader.readLine();
                    for(int i = 0; i < size; ++i){                            
                        for(int j = 0; j < 11; ++j){                                
                            parameters[j] = reader.readLine();                                 
                        }     
                        key = Integer.parseInt(parameters[0]);
                        dimMinX = Integer.parseInt(parameters[2]);
                        dimMinY = Integer.parseInt(parameters[3]);
                        dimMaxX = Integer.parseInt(parameters[4]);
                        dimMaxY = Integer.parseInt(parameters[5]);
                        dimX = Integer.parseInt(parameters[6]);
                        dimY = Integer.parseInt(parameters[7]);
                        newIsTall = Boolean.parseBoolean(parameters[8]);
                        newStackable = Boolean.parseBoolean(parameters[9]);
                            
                        elem = new Element(parameters[1], dimX, dimY, dimMaxX, dimMaxY, dimMinX, dimMinY, newIsTall, newStackable, parameters[10]);
                        this.cjtElement.setExact(key, elem);                            
                        reader.readLine();                            
                    } 
                    
                    reader.readLine();
                    reader.readLine();                    
                    reader.readLine();
                    
                    reader.readLine();
                    linea = reader.readLine();
                    aux = Integer.parseInt(linea);
                    reader.readLine();

                    for(int i = 0; i < aux; ++i){
                        linea = reader.readLine();
                        x = Integer.parseInt(linea);
                        linea = reader.readLine();
                        y = Integer.parseInt(linea);
                        linea = reader.readLine();
                        id = Integer.parseInt(linea);
                        reader.readLine();
                        cjtElement.setPack(x, y, id);
                    }
                    
                }
                fr.close(); 
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        } catch (Exception e) {
            System.err.println("Error");
        } 		
    }
    
    /** 
     * Carga el conjunto de tipo de habitacion del fichero "cjtroomtype.txt".       
     */
    public void loadCjtRoomType(){        
       
        int lastKey, listSize, id, idRoom, size;
        RoomType romType;
        String linea, name;
        List<Integer> list = new ArrayList();
	File file;
	FileReader fr;
	BufferedReader reader;
       
	try {			
            file = new File("cjtroomtype.txt");
            if(file.length() > 0){
                    
		fr = new FileReader(file);
		reader = new BufferedReader(fr);
		linea = reader.readLine();
                size = Integer.parseInt(linea); 
                
                if(size > 0){
                    reader.readLine();
                    linea = reader.readLine();
                    lastKey = Integer.parseInt(linea);
                    this.cjtRoomType.setCounter(lastKey);
                    reader.readLine();
                    reader.readLine();
                    reader.readLine();
                    for(int i = 0; i < size; ++i){    
                        name = reader.readLine();
                        linea = reader.readLine();
                        idRoom = Integer.parseInt(linea);
                        linea = reader.readLine();
                        listSize = Integer.parseInt(linea);
                        reader.readLine();
                        for(int j = 0; j < listSize; ++j){
                            linea = reader.readLine();
                            id = Integer.parseInt(linea);
                            list.add(id);
                        }
                        romType = new RoomType(name, list);
                        this.cjtRoomType.setUpdate(idRoom, romType); 
                        list.clear();
                        reader.readLine();                            
                    } 
                }
                fr.close(); 
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        } catch (Exception e) {
            System.err.println("Error");
        } 		
    }    
    
    /** 
     * Carga el conjunto de restricciones del fichero "restrictions.txt".       
     */
    public void loadRestrictions(){
         
        String parameters[] = new String[4];        
        int newObject1, newObject2, newDistance, cjtResDistSize, cjtResRTSize;
        boolean newAllow;
	File file;
	FileReader fr;
	BufferedReader reader;
        String linea;
        try {			
            file = new File("restrictions.txt");
            if(file.length() > 0){
                fr = new FileReader(file);
                reader = new BufferedReader(fr);                               
                linea = reader.readLine();
                cjtResDistSize = Integer.parseInt(linea); 
                reader.readLine();            
                linea = reader.readLine();
                cjtResRTSize = Integer.parseInt(linea);
                reader.readLine();
                reader.readLine();
                reader.readLine();                        
                for(int i = 0; i < cjtResDistSize; ++i){                            
                    for(int j = 0; j < 4; ++j){                                
                        parameters[j] = reader.readLine();                                 
                    }
                    newObject1 = Integer.parseInt(parameters[1]);
                    newObject2 = Integer.parseInt(parameters[2]);
                    newDistance = Integer.parseInt(parameters[3]);
                    
                    this.cjtRestrictions.addRestrictionDistance(parameters[0], newObject1, newObject2, newDistance);
                    reader.readLine();                            
                }                
                reader.readLine();
                reader.readLine();                
                for(int i = 0; i < cjtResRTSize; ++i){                            
                    for(int j = 0; j < 4; ++j){                                
                        parameters[j] = reader.readLine();                                 
                    } 
                    newObject1 = Integer.parseInt(parameters[1]);
                    newObject2 = Integer.parseInt(parameters[2]);
                    newAllow = Boolean.parseBoolean(parameters[3]);
                    this.cjtRestrictions.addRestrictionRoomType(parameters[0], newObject1, newObject2, newAllow);
                    reader.readLine();                            
                }     
                fr.close();  
            }
	} catch (FileNotFoundException e) {
		System.err.println("File not found");
	} catch (Exception e) {
		System.err.println("Error");
	} 		
    }
    
    /** 
     * Carga el conjunto de mapas del fichero "cjtmap.txt".       
     */
    public void loadCjtMap(){        
        String nameRoom, nameElement;    
        int[][] doors, windows;
        AuxiliarElement auxiliar;
        Stack<AuxiliarElement> elements;
        int idRoom, dimRoomX, dimRoomY, posX, posY, dimAuxX, dimAuxY, idAux, flagAux, size, sizeAux, doorSize, windowSize;        
	File file;
	FileReader fr;
	BufferedReader reader;       
	try {			
            file = new File("cjtmap.txt");
            if(file.length() > 0){
                fr = new FileReader(file);
		reader = new BufferedReader(fr);		
                String linea;                        
                linea = reader.readLine();
                size = Integer.parseInt(linea); 
                if(size > 0){  
                    
                    reader.readLine();
                    reader.readLine();                    
                    reader.readLine();
                    for(int i = 0; i < size; ++i){
                        elements = new Stack();
                        linea = reader.readLine();
                        idRoom = Integer.parseInt(linea);                        
                        linea = reader.readLine();
                        dimRoomX = Integer.parseInt(linea);
                        linea = reader.readLine();
                        dimRoomY = Integer.parseInt(linea);
                        nameRoom = reader.readLine();
                        linea = reader.readLine();
                        sizeAux = Integer.parseInt(linea);
                       
                        reader.readLine();
                        for(int j = 0; j < sizeAux; ++j){    
                          
                            linea = reader.readLine();
                            idAux = Integer.parseInt(linea);
                            
                            linea = reader.readLine();
                            flagAux = Integer.parseInt(linea);
                            
                            nameElement = reader.readLine();
                            
                            linea = reader.readLine();
                            dimAuxX = Integer.parseInt(linea);
                            
                            linea = reader.readLine();
                            dimAuxY = Integer.parseInt(linea);
                            
                            linea = reader.readLine();
                            posX = Integer.parseInt(linea);
                            
                            linea = reader.readLine();
                            posY = Integer.parseInt(linea);
                            
                            auxiliar = new AuxiliarElement( posX, posY, dimAuxX, dimAuxY, idAux, flagAux, nameElement);  
                            
                            elements.push(auxiliar);
                            reader.readLine();
                        }                        
                        reader.readLine();
                        linea = reader.readLine();
                        doorSize = Integer.parseInt(linea);
                        doors = new int[doorSize][2];                    
                        for(int z=0; z<doorSize; ++z){
                            linea = reader.readLine();
                            doors[z][0] = Integer.parseInt(linea);

                            linea = reader.readLine();
                            doors[z][1] = Integer.parseInt(linea);
                        }
                        reader.readLine();
                        reader.readLine();

                        linea = reader.readLine();
                        windowSize = Integer.parseInt(linea);
                        
                        windows = new int[windowSize][2];

                        for(int z=0; z<windowSize; ++z){
                            linea = reader.readLine();
                            windows[z][0] = Integer.parseInt(linea);

                            linea = reader.readLine();
                            windows[z][1] = Integer.parseInt(linea);
                        }
                        this.cjtMap.setMap(idRoom, dimRoomX, dimRoomY, elements, nameRoom, doors, windows);
                                                    
                        reader.readLine();                            
                    } 
                }
                fr.close(); 
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found");
        } catch (Exception e) {
            System.err.println("Error");
        } 		
    }
    
    /** 
     * Guarda el conjunto de elementos en el fichero "elements.txt".       
     */
    public void saveElement(){
        int maxId = this.cjtElement.getCount();
        Element elem;
        Pack pack;
        FileWriter file;
        PrintWriter writer;
        try{            
            file = new FileWriter("elements.txt");            
            writer = new PrintWriter(file);
            
            writer.println(this.cjtElement.size());
            writer.println("");            
            writer.println(this.cjtElement.getCount());
            writer.println("");
            writer.println("##### CjtElements #####");
            writer.println("");
            for(int i = 1; i<=maxId; ++i){
                if(this.cjtElement.existsKey(i)){                    
                    elem = this.cjtElement.getElement(i);                   
                    writer.println(i);
                    writer.println(elem.getName());
                    writer.println(elem.getDimension().getX());
                    writer.println(elem.getDimension().getY());
                    writer.println(elem.getDimensionMax().getX());
                    writer.println(elem.getDimensionMax().getY());
                    writer.println(elem.getDimensionMin().getX());
                    writer.println(elem.getDimensionMin().getY());
                    writer.println(elem.getIsTall());
                    writer.println(elem.getIsStackable());
                    writer.println(elem.getType());
                    writer.println("");
                }
            }  
            
            writer.println(""); 
            writer.println("##### Pack Resum #####");
            writer.println("");
            
            writer.println("");
            writer.println(cjtElement.getPackSize());
            writer.println("");
            
            for(int i = 0; i < cjtElement.getPackSize(); ++i){
                pack = cjtElement.getPack(i);
                writer.println(pack.getIdM());
                writer.println(pack.getIdIt());
                writer.println(pack.getId());
                writer.println("");
            }
            
            writer.flush();
        }catch (Exception e) {
            System.err.println("Error");
        }
    }
    
    /** 
     * Guarda el conjunto de restricciones en el fichero "restrictions.txt".       
     */
    public void saveRestrictions(){
        int cjtResDistSize = this.cjtRestrictions.getRestrictionDistanceSize();
        int cjtResRTSize = this.cjtRestrictions.getRestrictionRoomTypeSize();
        RestrictionDistance ResDis;
        RestrictionRoomType ResRomType;
        FileWriter file;
        PrintWriter writer;
        try{ 
            file = new FileWriter("restrictions.txt");            
            writer = new PrintWriter(file);
            writer.write("");
            writer.println(cjtResDistSize);
            writer.println("");
            writer.println(cjtResRTSize);
            writer.println("");
            writer.println("##### RestrictionDistance #####");
            writer.println("");
            for(int i = 1; i<=cjtResDistSize; ++i){ 
                ResDis = this.cjtRestrictions.getRestrictionDistance(i);                 
                writer.println(ResDis.getName());
                writer.println(ResDis.getObject1());
                writer.println(ResDis.getObject2());
                writer.println(ResDis.getDistance()); 
                writer.println("");
            }
            writer.println("##### RestrictionRoomType #####");
            writer.println("");
            for(int i = 1; i<=cjtResRTSize; ++i){  
                ResRomType = this.cjtRestrictions.getRestrictionRoomType(i);                
                writer.println(ResRomType.getName());
                writer.println(ResRomType.getObject1());
                writer.println(ResRomType.getObject2());
                writer.println(ResRomType.getAllow());                    
                writer.flush();
                writer.println("");
            }
            writer.flush();
        }catch (Exception e) {
            System.err.println("Error");
        }
    }
    
    /** 
     * Guarda el conjunto de tipos de habitacion en el fichero "cjtroomtype.txt".       
     */
    public void saveCjtRoomType(){
        int cjtRoomTypeSize = this.cjtRoomType.size();
        int cjtRoomTypeMaxId = this.cjtRoomType.getCount();
        List<Integer> list;
        RoomType romType;
        FileWriter file;
        PrintWriter writer;
        try{    
            file = new FileWriter("cjtroomtype.txt");            
            writer = new PrintWriter(file);
            writer.write("");
            writer.println(cjtRoomTypeSize);
            writer.println("");   
            writer.println(cjtRoomTypeMaxId);
            writer.println(""); 
            writer.println("##### CjtRoomType #####");
            writer.println("");
            for(int i = 1; i<=cjtRoomTypeMaxId; ++i){                 
                if(this.cjtRoomType.existsKey(i)){
                    romType = this.cjtRoomType.getRoomType(i);
                    list = romType.getRelatedFurniture();
                    writer.println(romType.getName());
                    writer.println(i);                    
                    writer.println(list.size());
                    writer.println();
                    for(int j=0; j< list.size();++j){                        
                        writer.println(list.get(j));
                    }                
                    writer.println("");
                }                
            }            
            writer.flush();
        }catch (Exception e) {
            System.err.println("Error");
        }
    }    
    
    /** 
     * Guarda el conjunto de mapas en el fichero "cjtmap.txt".       
     */
    public void saveCjtMap(){
        int cjtMapSize = this.cjtMap.size();
        int cjtmapMaxId = this.cjtMap.getCount();
        int[][] doors, windows;
        List<AuxiliarElement> list;
        AuxiliarElement auxiliar;
        Map map;
        FileWriter file;
        PrintWriter writer;
        try{    
            file = new FileWriter("cjtmap.txt");            
            writer = new PrintWriter(file);
            writer.write("");
            writer.println(cjtMapSize);            
            writer.println(""); 
            writer.println("##### CjtMap #####");
            writer.println("");
            for(int i = 1; i<=cjtmapMaxId; ++i){                 
                if(this.cjtMap.existsKey(i)){
                    map = this.cjtMap.getMap(i);
                    list = map.getCjtElement();
                    writer.println(map.getIdRoom());     
                    writer.println(map.getDimensionMap().getX());
                    writer.println(map.getDimensionMap().getY());
                    writer.println(map.getNameRoom());
                    writer.println(list.size());
                    writer.println();
                    for(int j=0; j< list.size();++j){                        
                        auxiliar = list.get(j);
                        writer.println(auxiliar.getIdObject());
                        writer.println(auxiliar.getFlag());
                        writer.println(auxiliar.getName());
                        writer.println(auxiliar.getDim().getX());
                        writer.println(auxiliar.getDim().getY());
                        writer.println(auxiliar.getFinalX());
                        writer.println(auxiliar.getFinalY());                        
                        
                        writer.println("");
                    }
                    writer.println("Doors");
                    doors = map.getDoors();
                    writer.println(doors.length);
                    for(int z=0; z<doors.length; ++z){
                        writer.println(doors[z][0]);
                        writer.println(doors[z][1]);
                    }
                    writer.println("");
                    writer.println("Windows");
                    windows = map.getWindows();
                    writer.println(windows.length);
                    for(int z=0; z<windows.length; ++z){
                        writer.println(windows[z][0]);
                        writer.println(windows[z][1]);
                    }
                }
                writer.println("");
            }            
            writer.flush();
        }catch (Exception e) {
            System.err.println("Error");
        } 
    }
}
