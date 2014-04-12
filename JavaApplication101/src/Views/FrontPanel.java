/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;


import Controllers.FrontController;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JPanel;

/**
 *
 * @author 11.2
 */
public class FrontPanel extends JPanel implements ActionListener{
    
    private Button b1, b2, b3, b4;
    private FrontController controller;
 
    /** 
     * Crea el panel frontal.
     * @param newController Enlace al controlador frontal
     */
    public FrontPanel(FrontController newController){
        controller = newController;
    }
    
    /** 
     * Visualiza en el panel el menu principal del programa.     
     */
    public void firstVisual( )
    {
       Label et = new Label("Bienvenido, escoja una de las siguientes opciones");       
       setLayout(null);
       et.setBounds(230, 150, 500, 30);
       et.setFont(new Font("Arial", 0, 20));
       add(et);
                
       b1 = new Button("Diseñar habitacion");
       b1.setBounds(380,270,140,40);
       b1.addActionListener(this);
       
       b2 = new Button("Entrar en configuracion");
       b2.addActionListener(this);
       b2.setBounds(380,370,140,40);
       
       b3 = new Button("Cargar habitacion");
       b3.addActionListener(this);
       b3.setBounds(380,470,140,40);
       
       b4 = new Button("Salir del programa");
       b4.addActionListener(this);       
       b4.setBounds(380,570,140,40);
       
       add(b1);
       add(b2);
       add(b3);   
       add(b4); 
       setVisible(true);
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
        else {
            controller.selector("exit");
        }
        setVisible(false);
    }

}
