/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import Controllers.FrontController;



public class MainClass {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
       FrontController controller = new FrontController();
       controller.loadParameters();
       controller.main();    
    }
    
    
}

