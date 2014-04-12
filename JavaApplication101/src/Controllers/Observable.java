/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;



/**
 *
 * @author 11.2
 */
public interface Observable {
    void addObserver(Observer observer);
    void notifyObserver(int id);
}
