/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;
//import Controllers.CjtRestrictionsController;
import Controllers.CjtRestrictionsController;
import java.util.LinkedList;
import java.util.Stack;
/**
 *
 * @author 11.2
 */
public class Algorithm {
    private AuxiliarElement actualObject;
    
    /**
     * Creadora de la clase algorithm
     */
    public Algorithm()
    {
        
    }
    
    /** forwardCheck of all the precalculable restrictions
     *@param futureObjects      stack of all the objects that need to be added to the solution
     *@param solution           partial solution of the algorithm
     *@param cjtRestController  CjtRestrictionController
     *@return   int:    0 -> all valid 
     *                  1 -> more mandatoryElements required than the futureObjects provided
     *                  2 -> futureObjects has restricted elements for the current room
     *                  3 -> futureObjects has missing elements required for the current room
     *                  4 -> futureObjects has incompatible elements
     *                  24 -> code 2 and code 4
     */
    public int forwardCheck
            (Stack<AuxiliarElement> futureObjects, Solution solution, 
            CjtRestrictionsController cjtRestController){
        LinkedList<Integer> mandatoryElements;
                mandatoryElements = solution.forwardRoomCheckMandatory(cjtRestController);
        Stack<AuxiliarElement> auxiliar;
        if(mandatoryElements.size() > futureObjects.size()){
            return 1;
        }
        else{
            int ret = 0;
            auxiliar = (Stack)futureObjects.clone();
            if (!forwardRoomCheckRestricted(futureObjects, solution, cjtRestController)){
                ret = (ret * 10) + 2;
            }
            futureObjects = (Stack)auxiliar.clone();
            if(!forwardRoomCheckMandatory(futureObjects, solution, cjtRestController, mandatoryElements)){
                ret = (ret * 10) + 3;
            }
            futureObjects = (Stack)auxiliar.clone();
            if(!forwardDistanceCheck(futureObjects, solution, cjtRestController)){
                ret = (ret * 10) + 4;
            }
            futureObjects = (Stack)auxiliar.clone();
            return ret;
        }
    }
    
    private boolean forwardDistanceCheck
            (Stack<AuxiliarElement> futureObjects, Solution solution,
            CjtRestrictionsController cjtRestController){
        Stack<AuxiliarElement> aux1;
        int [] vectorFutureObjects;
        int n = 0;
        boolean ret = true;
        aux1 = (Stack)futureObjects.clone();
        vectorFutureObjects = new int[aux1.size()];
        while (!aux1.isEmpty()){
            actualObject = new AuxiliarElement();
            actualObject = aux1.peek();
            vectorFutureObjects[n] = actualObject.getIdObject();
            aux1.pop();
            n++;
        }
        for (int i = 0; i < vectorFutureObjects.length; i++){
            for (int j = 0; j < vectorFutureObjects.length; j++){
                if (i != j){
                    ret = ret && solution.forwardDistanceCheck(vectorFutureObjects[i],vectorFutureObjects[j], cjtRestController);
                }
            }
        }
        return ret;
    }
    
    
    private boolean forwardRoomCheckRestricted
            (Stack<AuxiliarElement> futureObjects, Solution solution,
            CjtRestrictionsController cjtRestController){
        
        if (futureObjects.isEmpty()){
            return true;
        }
        else {
            actualObject = futureObjects.peek();
            futureObjects.pop();
            boolean ret = solution.forwardRoomCheckRestricted(actualObject, cjtRestController) 
                    && forwardRoomCheckRestricted(futureObjects, solution, cjtRestController);
            return ret;
        }
    }
    
    private boolean forwardRoomCheckMandatory
            (Stack<AuxiliarElement> futureObjects, Solution solution,
            CjtRestrictionsController cjtRestController, LinkedList<Integer> mandatoryElements){
        if (futureObjects.isEmpty() && !mandatoryElements.isEmpty()){
            return false;
        }
        else if(mandatoryElements.isEmpty()){
            return true;
        }
        else {
            actualObject = new AuxiliarElement();
            actualObject = futureObjects.peek();
            futureObjects.pop();
            int n = 0;
            int id;
            while (n < mandatoryElements.size()){
                id = (int) mandatoryElements.getFirst();
                mandatoryElements.removeFirst();
                if (actualObject.getIdObject() != id){
                    mandatoryElements.add(id);
                }else{
                    n = mandatoryElements.size();
                }
                n++;
            }
            boolean ret = forwardRoomCheckMandatory(futureObjects, solution, cjtRestController, mandatoryElements);
            return ret;
        }
    }
    
    /**
     * Llamada de la funcion recursiva de backtracking
     * @param futureObjects         objetos a añadir a la solucion
     * @param solution              solucion donde añadir los objetos
     * @param cjtRestController     controlador de restricciones que comprobara la restriccion
     * @return 
     */
    public Solution backtracking(Stack<AuxiliarElement> futureObjects,Solution solution, CjtRestrictionsController cjtRestController){
        if (futureObjects.isEmpty()){
            return solution;
        }
        else
        {
            actualObject = new AuxiliarElement();
            actualObject = futureObjects.peek();
            futureObjects.pop();
            while(solution.add(actualObject)){
                if(solution.isValid(cjtRestController)){
                    solution = backtracking(futureObjects,solution, cjtRestController);
                    if(solution.isFail()){                        
                        solution.deleteLast(actualObject.getId());
                    }
                    else
                    {
                        return solution;
                    }
                }
                else{
                    solution.deleteLast(actualObject.getId());
                }
            }
            solution.fail();
            return solution;
        }
        
    }
}
