/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
//import Controllers.CjtRestrictionsController;
import Controllers.CjtRestrictionsController;
import java.awt.Point;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.SortedMap;
import java.util.Stack;
import java.util.TreeMap;

/**
 * 
 * @author 11.2
 */
public class Solution {
    private static final int INFINITE_VALUE = 99999999, SCALE = 10;
    private static final int FLOOR = 0, DOOR = 1, WINDOW = 2;
    private int[][] space;
    private int idRoom, numberIds, iterations = 0;
    private boolean valid, fail, end;
    private Stack<Point> lastPositions; //Pila con las posiciones donde se han añadido los muebles, ordenado de mas reciente a menos
    private Stack<Integer> lastAdditions; //Pila de contador de addiciones
    private SortedMap<Integer, Integer> idEquivalent; //Mapa de equivalencia entre el identificador de solucion y el identificador del objeto
    private SortedMap<Integer, AuxiliarElement> objectsSol; //Mapa de los objetos de la solucion
    
    /**
     * Creadora de la clase solucion
     * @param sizeX     tamaño X de la habitacion a diseñar
     * @param sizeY     tamaño Y de la habitacion a diseñar
     * @param auxiliar  Pila de AuxiliarElement con todos los muebles que deben ser añadidos
     */
    public Solution(int sizeX, int sizeY, Stack<AuxiliarElement> auxiliar)
    {
        valid = true;
        fail = false;
        end = false;
        space = new int [sizeX/SCALE][sizeY/SCALE];
        idEquivalent = new TreeMap();
        objectsSol = new TreeMap();
        lastPositions = new Stack();
        lastAdditions = new Stack();
        Stack<AuxiliarElement> objects = (Stack) auxiliar.clone();
        numberIds = 0;
        for (int i = 0; i < space.length; i++){
            for (int j = 0; j < space[0].length; j++){
                space[i][j] = FLOOR;
            }
        }
        while(!objects.empty()){
            objectsSol.put(objects.peek().getId(),objects.peek());
            objects.pop();
        }
    }
    
    /**
     * Creadora de la clase solucion sin objetos
     * @param sizeX     tamaño X de la habitacion a diseñar
     * @param sizeY     tamaño Y de la habitacion a diseñar
     */
    public Solution(int sizeX, int sizeY)
    {
        valid = true;
        fail = false;
        end = false;
        space = new int [sizeX/SCALE][sizeY/SCALE];
        idEquivalent = new TreeMap();
        objectsSol = new TreeMap();
        lastPositions = new Stack();
        lastAdditions = new Stack();
        numberIds = 0;
        for (int i = 0; i < space.length; i++){
            for (int j = 0; j < space[0].length; j++){
                space[i][j] = FLOOR;
            }
        }
    }
    
    public boolean addValidationElement(AuxiliarElement elem){
        objectsSol.put(elem.getId(), elem);
        int i = elem.getFinalXScaled();
        int j = elem.getFinalYScaled();
        boolean fits = true;
        Dimension dim = new Dimension();
        int aux1 = elem.getDim().getX()/SCALE;
        dim.setX(aux1);
        aux1 = elem.getDim().getY()/SCALE;
        dim.setY(aux1);
        TESTprintMatrix();
        for (int m = 0; m < dim.getX() && fits; m++){
            for (int n = 0; n < dim.getY() && fits; n++){
                if ((i+m) >= space.length || (j+n) >= space[0].length){
                    fits = false;
                }
                else if (space[i+m][j+n] != FLOOR){
                    fits = false;
                }
            }
        }
        if (fits){
            int objId = elem.getId();
            AuxiliarElement auxObject;
                    for (int m = 0; m < dim.getX() && fits; m++){
                        for (int n = 0; n < dim.getY() && fits; n++){
                            space[i+m][j+n] = objId;
                        }
                    }
                    idEquivalent.put(objId, elem.getIdObject());
                    objectsSol.put(objId, elem);
                    return true;
        } 
        return fits;
    }
    
    /**
     * consultora del identificador de tipo de habitacion
     * @return devuelve el idRoom de esta solucion
     */
    public int getIdRoom (){
        return idRoom;
    }
    
    /**
     * modificadora del identificador de tipo de habitacion
     * @param newIdRoom nuevo valor para idRoom
     */
    public void setIdRoom (int newIdRoom){
        idRoom = newIdRoom;
    }
    
    /**
     * Coloca una ventana.
     * @param stride    desplazamiento en el lado donde se coloca la ventana
     * @param flag      identificador del lado donde va la ventana. 0:Arriba, 1:Izquierda, 2:Abajo, 3:Derecha
     * @param sizeX     tamaño X de la ventana (50 de estandar) con el area de desplazamiento incluida
     * @param sizeY     tamaño Y de la ventana (50 de estandar) con el area de desplazamiento incluida
     * @param windowId  identificador de la ventana para diferenciar las diferentes ventanas
     * @return          devuelve cierto si ha podido colcar la ventana, si la posicion deseada esta ocupada devuelve falso y no coloca la ventana
     */
    public boolean placeWindow(int stride, int flag, int sizeX, int sizeY, int windowId)
    {
        int i, j;
        String windowName = "Ventana";
        i = j = 0;
        Dimension dim = new Dimension();
        dim.setX(sizeX/SCALE);
        dim.setY(sizeY/SCALE);
        stride = stride/SCALE;
        boolean fits = true;
        switch(flag){
            case 0: // left - right
                i = 0;
                j = stride;
                for (int m = 0; m < dim.getX() && fits; m++){
                    for (int n = 0; n < dim.getY() && fits; n++){
                        if ((i+m) >= space.length || (j+n) >= space[0].length){
                            fits = false;
                        }
                        else if (space[i+m][j+n] != FLOOR){
                            fits = false;
                        }
                    }
                }
                break;
            case 1: // up - down
                i = stride;
                j = space[0].length-1;
                for (int n = 0; n < dim.getY() && fits; n++){
                    for (int m = 0; m < dim.getX() && fits; m++){
                        if ((j-n) < 0 || (i+m) >= space.length){
                            fits = false;
                        }
                        else if (space[i+m][j-n] != FLOOR){
                            fits = false;
                        }
                    }
                }
                break;
            case 2: // right - left
                i = space.length-1;
                j = stride;
                for (int m = 0; m < dim.getX() && fits; m++){
                    for (int n = 0; n < dim.getY() && fits; n++){
                        if ((i-m) < 0 || (j-n) < 0 || j > space[0].length){
                            fits = false;
                        }
                        else if (space[i-m][j-n] != FLOOR){
                            fits = false;
                        }
                    }
                }
                break;
            case 3: // down - up
                i = stride;
                j = 0;
                for (int n = 0; n < dim.getY() && fits; n++){
                    for (int m = 0; m < dim.getX() && fits; m++){
                        if ((i-m) < 0 || (j+n) >= space[0].length || i > space.length){
                            fits = false;
                        }
                        else if (space[i-m][j+n] != FLOOR){
                            fits = false;
                        }
                    }
                }
                break;
        }
        if (fits){
            int objId = windowId;
            AuxiliarElement auxObject;
            switch (flag){
                case 0: // left - right
                    for (int m = 0; m < dim.getX() && fits; m++){
                        for (int n = 0; n < dim.getY() && fits; n++){
                            space[i+m][j+n] = objId;
                        }
                    }
                    idEquivalent.put(objId, WINDOW);
                    auxObject = new AuxiliarElement(i, j, sizeX, sizeY, WINDOW, 0, windowName);
                    auxObject.setId(objId);
                    auxObject.setPosFinal(i, j);
                    objectsSol.put(objId, auxObject);
                    return true;
                case 1: // up - down
                    for (int n = 0; n < dim.getY() && fits; n++){
                        for (int m = 0; m < dim.getX() && fits; m++){
                            space[i+m][j-n] = objId;
                        }
                    }
                    idEquivalent.put(objId, WINDOW);
                    auxObject = new AuxiliarElement(i, j, sizeX, sizeY, WINDOW, 1, windowName);
                    auxObject.setId(objId);
                    auxObject.setPosFinal(i, j);
                    objectsSol.put(objId, auxObject);
                    return true;
                case 2: // right - left
                    for (int m = 0; m < dim.getX() && fits; m++){
                        for (int n = 0; n < dim.getY() && fits; n++){
                            space[i-m][j-n] = objId;
                        }
                    }
                    idEquivalent.put(objId, WINDOW);
                    auxObject = new AuxiliarElement(i, j, sizeX, sizeY, WINDOW, 2, windowName);
                    auxObject.setId(objId);
                    auxObject.setPosFinal(i, j);
                    objectsSol.put(objId, auxObject);
                    return true;
                case 3: // down - up
                    for (int n = 0; n < dim.getY() && fits; n++){
                        for (int m = 0; m < dim.getX() && fits; m++){
                            space[i-m][j+n] = objId;
                        }
                    }
                    idEquivalent.put(objId, WINDOW);
                    auxObject = new AuxiliarElement(i, j, sizeX, sizeY, WINDOW, 3, windowName);
                    auxObject.setId(objId);
                    auxObject.setPosFinal(i, j);
                    objectsSol.put(objId, auxObject);
                    return true;
            }
        }
        return false;
    }
    
    /**
     * Coloca una puerta.
     * @param stride    desplazamiento en el lado donde se coloca la puerta
     * @param flag      identificador del lado donde va la puerta. 0:Arriba, 1:Izquierda, 2:Abajo, 3:Derecha
     * @param sizeX     tamaño X de la puerta (50 de estandar) con el area de desplazamiento incluida
     * @param sizeY     tamaño Y de la puerta (50 de estandar) con el area de desplazamiento incluida
     * @param doorId  identificador de la puerta para diferenciar las diferentes puertas
     * @return          devuelve cierto si ha podido colcar la puerta, si la posicion deseada esta ocupada devuelve falso y no coloca la puerta
     */
    public boolean placeDoor(int stride, int flag, int sizeX, int sizeY, int doorId)
    {
        int i, j;
        String doorName = "Puerta";
        i = j = 0;
        Dimension dim = new Dimension();
        dim.setX(sizeX/SCALE);
        dim.setY(sizeY/SCALE);
        stride = stride/SCALE;
        boolean fits = true;
        switch(flag){
            case 0: // left - right
                i = 0;
                j = stride;
                for (int m = 0; m < dim.getX() && fits; m++){
                    for (int n = 0; n < dim.getY() && fits; n++){
                        if ((i+m) >= space.length || (j+n) >= space[0].length){
                            fits = false;
                        }
                        else if (space[i+m][j+n] != FLOOR){
                            fits = false;
                        }
                    }
                }
                break;
            case 1: // up - down
                i = stride;
                j = space[0].length-1;
                for (int n = 0; n < dim.getY() && fits; n++){
                    for (int m = 0; m < dim.getX() && fits; m++){
                        if ((j-n) < 0 || (i+m) >= space.length){
                            fits = false;
                        }
                        else if (space[i+m][j-n] != FLOOR){
                            fits = false;
                        }
                    }
                }
                break;
            case 2: // right - left
                i = space.length-1;
                j = stride;
                for (int m = 0; m < dim.getX() && fits; m++){
                    for (int n = 0; n < dim.getY() && fits; n++){
                        if ((i-m) < 0 || (j-n) < 0 || j > space[0].length){
                            fits = false;
                        }
                        else if (space[i-m][j-n] != FLOOR){
                            fits = false;
                        }
                    }
                }
                break;
            case 3: // down - up
                i = stride;
                j = 0;
                for (int n = 0; n < dim.getY() && fits; n++){
                    for (int m = 0; m < dim.getX() && fits; m++){
                        if ((i-m) < 0 || (j+n) >= space[0].length || i > space.length){
                            fits = false;
                        }
                        else if (space[i-m][j+n] != FLOOR){
                            fits = false;
                        }
                    }
                }
                break;
        }
        if (fits){
            int objId = doorId;
            AuxiliarElement auxObject;
            switch (flag){
                case 0: // left - right
                    for (int m = 0; m < dim.getX() && fits; m++){
                        for (int n = 0; n < dim.getY() && fits; n++){
                            space[i+m][j+n] = objId;
                        }
                    }
                    numberIds++;
                    idEquivalent.put(objId, DOOR);
                    auxObject = new AuxiliarElement(i, j, sizeX, sizeY, DOOR, flag, doorName);
                    auxObject.setId(objId);
                    auxObject.setPosFinal(i, j);
                    objectsSol.put(objId, auxObject);
                    return true;
                case 1: // up - down
                    for (int n = 0; n < dim.getY() && fits; n++){
                        for (int m = 0; m < dim.getX() && fits; m++){
                            space[i+m][j-n] = objId;
                        }
                    }
                    numberIds++;
                    idEquivalent.put(objId, DOOR);
                    auxObject = new AuxiliarElement(i, j, sizeX, sizeY, DOOR, flag, doorName);
                    auxObject.setId(objId);
                    auxObject.setPosFinal(i, j);
                    objectsSol.put(objId, auxObject);
                    return true;
                case 2: // right - left
                    for (int m = 0; m < dim.getX() && fits; m++){
                        for (int n = 0; n < dim.getY() && fits; n++){
                            space[i-m][j-n] = objId;
                        }
                    }
                    numberIds++;
                    idEquivalent.put(objId, DOOR);
                    auxObject = new AuxiliarElement(i, j, sizeX, sizeY, DOOR, flag, doorName);
                    auxObject.setId(objId);
                    auxObject.setPosFinal(i, j);
                    objectsSol.put(objId, auxObject);
                    return true;
                case 3: // down - up
                    for (int n = 0; n < dim.getY() && fits; n++){
                        for (int m = 0; m < dim.getX() && fits; m++){
                            space[i-m][j+n] = objId;
                        }
                    }
                    numberIds++;
                    idEquivalent.put(objId, DOOR);
                    auxObject = new AuxiliarElement(i, j, sizeX, sizeY, DOOR, flag, doorName);
                    auxObject.setId(objId);
                    auxObject.setPosFinal(i, j);
                    objectsSol.put(objId, auxObject);
                    return true;
            }
        }
        return false;
    }
    
    /**
     * coloca el objeto object
     * @param object    objecto a colocar
     * @param i         posicion X respecto el flag
     * @param j         posicion Y respecto el flag
     * @param cont      contador de intentos, para las rotaciones, 0 por defecto
     * @param flag      identificador del lado desde donde se coloca el objeto. 0:Arriba, 1:Izquierda, 2:Abajo, 3:Derecha
     * @return          devuelve cierto si se ha podido colocar el objeto, falso si no y no lo coloca.
     */
    private boolean place(AuxiliarElement object, int i, int j, int cont,int flag)
    {
        Dimension dim = new Dimension();
        dim.setX(object.getDim().getX()/SCALE);
        dim.setY(object.getDim().getY()/SCALE);
        boolean fits = true;
        switch(flag){
            case 0: // left - right
                for (int m = 0; m < dim.getX() && fits; m++){
                    for (int n = 0; n < dim.getY() && fits; n++){
                        if ((i+m) >= space.length || (j+n) >= space[0].length){
                            fits = false;
                        }
                        else if (space[i+m][j+n] != FLOOR){
                            fits = false;
                        }
                    }
                }
                break;
            case 1: // up - down
                for (int n = 0; n < dim.getY() && fits; n++){
                    for (int m = 0; m < dim.getX() && fits; m++){
                        if ((j-n) < 0 || (i+m) >= space.length){
                            fits = false;
                        }
                        else if (space[i+m][j-n] != FLOOR){
                            fits = false;
                        }
                    }
                }
                break;
            case 2: // right - left
                for (int m = 0; m < dim.getX() && fits; m++){
                    for (int n = 0; n < dim.getY() && fits; n++){
                        if ((i-m) < 0 || (j-n) < 0){
                            fits = false;
                        }
                        else if (space[i-m][j-n] != FLOOR){
                            fits = false;
                        }
                    }
                }
                break;
            case 3: // down - up
                for (int n = 0; n < dim.getY() && fits; n++){
                    for (int m = 0; m < dim.getX() && fits; m++){
                        if ((i-m) < 0 || (j+n) >= space[0].length){
                            fits = false;
                        }
                        else if (space[i-m][j+n] != FLOOR){
                            fits = false;
                        }
                    }
                }
                break;
        }
        if (fits){
            int objId = object.getId();
            Point p = new Point();
            int additions = 0;
            switch (flag){
                case 0: // left - right
                    for (int m = 0; m < dim.getX() && fits; m++){
                        for (int n = 0; n < dim.getY() && fits; n++){
                            space[i+m][j+n] = objId;
                            p = new Point();
                            p.setLocation((i+m),(j+n));
                            lastPositions.push(p);
                            additions++;
                        }
                    }
                    numberIds++;
                    lastAdditions.push((Integer)additions);
                    return true;
                case 1: // up - down
                    for (int n = 0; n < dim.getY() && fits; n++){
                        for (int m = 0; m < dim.getX() && fits; m++){
                            space[i+m][j-n] = objId;
                            p = new Point();
                            p.setLocation((i+m),(j-n));
                            lastPositions.push(p);
                            additions++;
                        }
                    }
                    numberIds++;
                    lastAdditions.push((Integer)additions);
                    return true;
                case 2: // right - left
                    for (int m = 0; m < dim.getX() && fits; m++){
                        for (int n = 0; n < dim.getY() && fits; n++){
                            space[i-m][j-n] = objId;
                            p = new Point();
                            p.setLocation((i-m),(j-n));
                            lastPositions.push(p);
                            additions++;
                        }
                    }
                    numberIds++;
                    lastAdditions.push((Integer)additions);
                    return true;
                case 3: // down - up
                    for (int n = 0; n < dim.getY() && fits; n++){
                        for (int m = 0; m < dim.getX() && fits; m++){
                            space[i-m][j+n] = objId;
                            p = new Point();
                            p.setLocation((i-m),(j+n));     
                            lastPositions.push(p);
                            additions++;
                        }
                    }
                    numberIds++;
                    lastAdditions.push((Integer)additions);
                    return true;
            }
        }else if(dim.getY() == dim.getX()){
            return false;
        }       
        else if(cont == 1){
            object.rotate();
            return place(object,i,j,0,flag);
        }
        else{
            return false;
        }
        return false;
    }
    
    /**
     * añade un objeto en la primera posicion disponible
     * @param object    objeto a colocar
     * @return          devuelve si hay una posicion posible para colocar el mueble
     */
    public boolean add (AuxiliarElement object)
    {
        boolean found = false; // if the object has been placed;
        if(!end){
        int size = space.length * space[0].length; // total of positions
        int id = object.getId();
        AuxiliarElement thisObject = objectsSol.get(id);
        int i = thisObject.getX(); // rows
        int j = thisObject.getY(); // columns
        int flag = thisObject.getFlag(); // direction of movement
        int k = thisObject.getK(); // number of positions seen
        boolean nextmove = false;
        while (k < size && (!found || nextmove)){
            switch (flag){
                case 0: // left - right
                    for (int m = i; (m < space[0].length - j) && (!found || nextmove); m++){
                        // if the object fits it is placed and returns true
                        if (thisObject.getM() != 0){
                            m = thisObject.getM();
                            thisObject.setM(0);
                        }
                        if (nextmove){
                            thisObject.setPos(i,j,k,flag);
                            thisObject.setM(m);
                            thisObject.setPlaced(true);
                            nextmove = false;
                            break;
                        }else{
                            found = place(thisObject,i,m,1,flag);
                        }
                        if (found){
                            thisObject.setPosFinal(i, m);
                            nextmove = true;
                        }
                        k++;
                    }
                    flag++;
                    break;
                case 1: // up - down
                    for (int m = i + 1; (m < space.length - i) && (!found || nextmove); m++){
                        // if the object fits it is placed and returns true
                        if (thisObject.getM() != 0){
                            m = thisObject.getM();
                            thisObject.setM(0);
                        }
                        if (nextmove){
                            thisObject.setPos(i,j,k,flag);
                            thisObject.setM(m);
                            thisObject.setPlaced(true);
                            nextmove = false;
                            break;
                        }else{
                            found = place(thisObject,m,(space[0].length-1-j),1,flag);
                        }
                        if (found){
                            thisObject.setPosFinal(m, (space[0].length-1-j));
                            nextmove = true;
                        }
                        k++;
                    }
                    flag++;
                    break;
                case 2: // right - left
                    for (int m = space[0].length - 2 - j; (m >= j) && (!found || nextmove); m--){
                        // if the object fits it is placed and returns true
                        if (thisObject.getM() != 0){
                            m = thisObject.getM();
                            thisObject.setM(0);
                        }
                        if (nextmove){
                            thisObject.setPos(i,j,k,flag);
                            thisObject.setM(m);
                            thisObject.setPlaced(true);
                            nextmove = false;
                            break;
                        }else{
                            found = place(thisObject,(space.length-1-i),m,1,flag);
                        }
                        if (found){
                            thisObject.setPosFinal((space.length-1-i), m);
                            nextmove = true;
                        }
                        k++;
                    }
                    flag++;
                    break;
                case 3: // down - up
                    for (int m = space.length - 2 - i; (m >= i + 1) && (!found || nextmove); m--){
                        // if the object fits it is placed and returns true
                        if (thisObject.getM() != 0){
                            m = thisObject.getM();
                            thisObject.setM(0);
                        }
                        if (nextmove){
                            thisObject.setPos(i,j,k,flag);
                            thisObject.setPlaced(true);
                            thisObject.setM(m);
                            nextmove = false;
                            break;
                        }else{
                            found = place(thisObject,m,j,1,flag);
                        }
                        if (found){
                            thisObject.setPosFinal(m,j);
                            nextmove = true;
                        }
                        k++;
                    }
                    flag=0;
                    i++;
                    j++;
                    break;
            }
        }
        }
        valid = found;
        return valid;
    }
     
    /**
     * Elimina el último mueble colocado
     * @param idKey     identificador del mueble a eliminar, para eliminar las distancias respectivas a él
     */
    public void deleteLast(int idKey)
    {
        Point p;
        int additions = (int)lastAdditions.peek();
        lastAdditions.pop();
        for(int i = 0; i < additions; i++){
            p = lastPositions.peek();
            //if (idKey != space[(int)p.getX()][(int)p.getY()]){ System.err.println("Eliminando mueble incorrecto");}
            space[(int)p.getX()][(int)p.getY()] = FLOOR;
            lastPositions.pop();
        }
        Iterator it = objectsSol.entrySet().iterator();
        while (it.hasNext()) {
            Entry thisAuxEntry = (Entry) it.next();
            AuxiliarElement thisEntry = (AuxiliarElement) thisAuxEntry.getValue();
            if (thisEntry.containsDistance(idKey)){
                thisEntry.removeDistance(idKey);
                thisEntry.putDistance(idKey, INFINITE_VALUE);
            }else if(thisEntry.getId() == idKey){
                thisEntry.setPlaced(false);
            }
        }
        numberIds--;
    }
    
    
    /**
     * Comprueba si la solucion actual sigue siendo valida
     * @param cjtRestController controlador de restricciones para comprobar las restricciones
     * @return                  devuelve si la solucion es valida o no
     */
    public boolean isValid(CjtRestrictionsController cjtRestController)
    {
        if(iterations >= 100000){
            valid = false;
            fail = true;
            end = true;
        }
        if (valid){
            iterations++;
            SortedMap<Integer, Boolean> idSeen;
            int n = 0;
            idSeen = new TreeMap();
            //TESTprintMatrix();
            if (numberIds == 1){
                return valid;
            }
            Iterator it = objectsSol.entrySet().iterator();
            while (it.hasNext() && valid){
                Entry thisAuxEntry = (Entry) it.next();
                AuxiliarElement thisElement = (AuxiliarElement) thisAuxEntry.getValue();
                //System.out.println(iterations);
                if(thisElement.isPlaced() && thisElement.getIdObject() != WINDOW){
                    SortedMap<Integer, Integer> idDistance;
                    idDistance = thisElement.getDistances();
                    int idKey = thisElement.getId();
                    boolean required = false;
                    if (idDistance.size() == (numberIds - 1)){
                        Iterator it2 = idDistance.entrySet().iterator();
                        while (it2.hasNext() && !required){
                            Entry thisEntry = (Entry) it2.next();
                            int thisValue = (int)thisEntry.getValue();
                            if (thisValue == INFINITE_VALUE){
                                int thisKey = (int)thisEntry.getKey();
                                AuxiliarElement auxElem = objectsSol.get(thisKey);
                                if (auxElem.isPlaced()){
                                    if (auxElem.containsDistance(idKey) && auxElem.getDistance(idKey) != INFINITE_VALUE){
                                        thisElement.putDistance(thisKey, auxElem.getDistance(idKey));
                                    }else{
                                        required = true;
                                    }
                                }
                            }
                        }
                    }else{
                        required = true;
                    }
                    if(required){
                        int[][] matrix = new int [space.length][space[0].length];
                        int x, y;
                        int vp[] = {-1,0,1,0};
                        LinkedList<Point> positions;
                        positions = new LinkedList();
                        idDistance = new TreeMap();
                        Point actual;
                        for (int i = 0; i < space.length; i++){
                            for (int j = 0; j < space[0].length; j++){
                                matrix[i][j] = INFINITE_VALUE;
                            }
                        }
                        matrix[(int)thisElement.getFinalXScaled()][(int)thisElement.getFinalYScaled()] = 0;
                        positions.push(new Point(thisElement.getFinalXScaled(),thisElement.getFinalYScaled()));
                        while (!positions.isEmpty()){
                            actual = positions.getFirst();
                            positions.removeFirst();
                            
                            for (int i = 0; i < 4; i++){
                                x = (int)actual.getX() + vp[i];
                                y = (int)actual.getY() + vp[3-i];
                                
                                if (x >= 0 && x < space.length && y >= 0 && y < space[0].length
                                        && matrix[x][y] == INFINITE_VALUE){
                                    if(space[x][y] == FLOOR || thisElement.getIdObject() != DOOR){
                                        if(thisElement.getIdObject() == DOOR){
                                            if(x != (int)actual.getX()){
                                                if((y-1) >= 0 && (x+1) < space[0].length && space[(int)actual.getX()][y-1] == FLOOR 
                                                        && space[(int)actual.getX()][y+1] == FLOOR){
                                                    positions.add(new Point(x,y));
                                                }
                                            }else{
                                                if((x-1) >= 0 && (x+1) < space.length && space[x-1][(int)actual.getY()] == FLOOR 
                                                        && space[x+1][(int)actual.getY()] == FLOOR){
                                                    positions.add(new Point(x,y));
                                                }
                                            }
                                        }else{
                                            positions.add(new Point(x,y));
                                        }
                                    }
                                    if (space[x][y] == idKey){
                                        matrix[x][y] = 0;
                                    }else if(space[x][y] != FLOOR){
                                        matrix[x][y] = matrix[(int)actual.getX()][(int)actual.getY()] + 1;
                                        int x1,y1;
                                        for (int j = 0; j < 4; j++){
                                            x1 = x + vp[j];
                                            y1 = y + vp[3-j];
                                            if (x1 >= 0 && x1 < space.length && y1 >= 0 && y1 < space[0].length){
                                                if (matrix[x1][y1]  < matrix[(int)actual.getX()][(int)actual.getY()]){
                                                    matrix[x][y] = matrix[x1][y1] + 1;
                                                }
                                            }
                                        }
                                        if(idDistance.containsKey(space[x][y])){
                                            if(idDistance.get(space[x][y]) > matrix[(int)actual.getX()][(int)actual.getY()]){
                                                idDistance.put(space[x][y], matrix[(int)actual.getX()][(int)actual.getY()]);
                                                thisElement.putDistance(space[x][y], matrix[(int)actual.getX()][(int)actual.getY()]);
                                                
                                            }
                                        }else{
                                            idDistance.put(space[x][y], matrix[(int)actual.getX()][(int)actual.getY()]);
                                            thisElement.putDistance(space[x][y], matrix[(int)actual.getX()][(int)actual.getY()]);
                                        }
                                    }else{
                                        matrix[x][y] = matrix[(int)actual.getX()][(int)actual.getY()] + 1;
                                        int x1,y1;
                                        for (int j = 0; j < 4; j++){
                                            x1 = x + vp[j];
                                            y1 = y + vp[3-j];
                                            if (x1 >= 0 && x1 < space.length && y1 >= 0 && y1 < space[0].length){
                                                if (matrix[x1][y1]  < matrix[(int)actual.getX()][(int)actual.getY()]){
                                                    matrix[x][y] = matrix[x1][y1] + 1;
                                                }
                                            }
                                        }
                                    }
                                }
//                            System.out.println(idKey);
//                                for(int ii = 0; ii < matrix.length; ii++){
//                                    for (int jj = 0; jj < matrix[0].length; jj++){
//                                        System.out.print(matrix[ii][jj]+" ");
//                                    }
//                                    System.out.println();
//                                }
//                                System.out.println();
                            }
                            
                        }
                                
                        if(thisElement.getIdObject() == DOOR){
                            //System.out.println(thisElement.getDistances().size()+" "+numberIds);
                            if (idDistance.size() < (numberIds - 1)){
                                valid = false;
                            }
                        }else{
                            Iterator it2 = idDistance.entrySet().iterator();
                            while(it2.hasNext() && valid){
                                Entry thisEntry = (Entry) it2.next();
                                int otherKey = objectsSol.get((int)thisEntry.getKey()).getIdObject();
                                int distance = (int)thisEntry.getValue();
                                if(otherKey != DOOR && otherKey != WINDOW){
                                    valid = valid && cjtRestController.checkRestrictionDistance(thisElement.getIdObject(),otherKey,distance*SCALE);
                                }
                                if (otherKey != WINDOW){
                                    objectsSol.get((int)thisEntry.getKey()).removeDistance(thisElement.getId());
                                    objectsSol.get((int)thisEntry.getKey()).putDistance(thisElement.getId(), distance);
                                }
                            }
                        }
                    }
                }
            }
        }
        return valid;
    }
    
    /**
     * consultora del booleano fail
     * @return devuelve si la solucion ha fallado (ha llegado al final del backtracking y no ha encontrado solucion)
     */
    public boolean isFail(){
        return fail;
    }
    
    /**
     * modificadora del booleano fail. Marca la solucion actual como fallada.
     */
    public void fail(){
        fail = true;
    }
    
    /**
     * Hace la comprobacion por avanzado de los muebles restringidos en la habitacion de tipo idRoom
     * @param object                objeto a comprobar
     * @param cjtRestController     controlador de restricciones que comprobara la restriccion
     * @return                      devuelve si el objeto object puede ir a la habitacion idRoom
     */
    public boolean forwardRoomCheckRestricted(AuxiliarElement object, CjtRestrictionsController cjtRestController){
        int valueRestriction;
        valueRestriction = cjtRestController.checkRestrictionRoomTypeRestricted(idRoom, object.getIdObject());
        if (valueRestriction == 0){
            return false;
        }
        else{
            return true;
        }
    }
    
    
    /**
     * Consultora de la lista de muebles obligatorios para la habitacion idRoom
     * @param cjtRestController     controlador de restricciones que comprobara la restriccion
     * @return                      devuelve una lista con los identificadores de objeto de los muebles obligatorios
     */
    public LinkedList<Integer> forwardRoomCheckMandatory(CjtRestrictionsController cjtRestController){
        return cjtRestController.getRestrictionRoomTypeMandatory(idRoom);
    }
    
    /**
     * Hace la comprobacion por avanzado de los muebles restringidos entre ellos, si no pueden estar a distancia infinita (INFINITE_VALUE) es que no pueden ir
     * @param idElem1               identificador de objeto del primer elemento
     * @param idElem2               identificador de objeto del segundo elemento
     * @param cjtRestController     controlador de restricciones que comprobara la restriccinn
     * @return                      devuelve si idElem1 y idElem2 pueden estar en esa misma habitacion a una distancia posible
     */
    public boolean forwardDistanceCheck(int idElem1, int idElem2, CjtRestrictionsController cjtRestController){
        boolean ret = cjtRestController.checkRestrictionDistance(idElem1,idElem2,INFINITE_VALUE);
        ret = ret || cjtRestController.checkRestrictionDistance(idElem1,idElem2,0);
        return ret;
    }
    
    public void TESTprintMatrix(){
        System.out.println();
        System.out.println();
        System.out.println();
        for (int i = 0; i < space.length; i++){
            for (int j = 0; j < space[0].length; j++){
                System.out.print(space[i][j]+" ");
            }
            System.out.println();
        }
    }
    
    public void TESTprintFinalElements(){
        Iterator it = objectsSol.entrySet().iterator();
        while(it.hasNext()){
            Entry newEntry = (Entry) it.next();
            AuxiliarElement newElem = (AuxiliarElement) newEntry.getValue();
            System.out.print("id:"+newElem.getId()+" objId:"+newElem.getIdObject()+" flag:"+newElem.getFlag()+" dims:"+newElem.getDim().getX()+","+newElem.getDim().getY()+" ");
            System.out.print("X:"+newElem.getX()+" Y:"+newElem.getY()+" FinalX:"+newElem.getFinalX()+" FinalY:"+newElem.getFinalY());
            System.out.println();
        }
    }

}
