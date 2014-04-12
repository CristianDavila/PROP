/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controllers.ConfigurationController;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 *
 * @author 11.2
 */
public class ConfigurationPanel extends JPanel implements ActionListener{
    
    private Button b1, b2, b3, b4, b5;
    private MainFrame window;
    private ConfigurationController controller;
 
    
    /** 
     * Crea el panel de configuracion.
     * @param newWindow Enlace al frame.
     * @param newController Enlace al controlador de configuracion.
     */
    public ConfigurationPanel(MainFrame newWindow, ConfigurationController newController){
        controller = newController;
        window = newWindow;
    }
    
    /** 
     * Visualiza en el panel el menu de configuracion del programa.     
     */
    public void visualMenuConfig( )
    {
       Label et = new Label("Escoja una de las siguientes opciones de configuracion");       
       setLayout(null);
       et.setBounds(230, 150, 500, 30);
       et.setFont(new Font("Arial", 0, 20));
       add(et);
            
       b1 = new Button("Restricciones");
       b1.setBounds(380,250,140,40);
       b1.addActionListener(this);
       
       b2 = new Button("Salas");
       b2.addActionListener(this);
       b2.setBounds(380,350,140,40);
       
       b3 = new Button("Muebles");
       b3.addActionListener(this);
       b3.setBounds(380,450,140,40);
       
       b4 = new Button("Restaurar Valores");
       b4.addActionListener(this);       
       b4.setBounds(380,550,140,40);
       
       b5 = new Button("Volver");
       b5.addActionListener(this);       
       b5.setBounds(380,650,140,40);
       
       add(b1);
       add(b2);
       add(b3);   
       add(b4);  
       add(b5);
       
    } 
    
    /** 
     * Conjunto de acciones para cada boton del panel.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource()==b1) {            
            controller.selector("1");
        }
        else if (e.getSource()==b2) {
            controller.selector("2");
        }
        else if (e.getSource()==b3) {
            controller.selector("3");
        }
        else if (e.getSource()==b4){            
            if(this.window.validation()){
                controller.selector("4");
            }            
        }
        else if (e.getSource()==b5){
            controller.selector("5");
        }
    }

}