/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;


import Controllers.MapController;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/**
 *
 * @author 11.2
 */
public class CjtMapPanel extends JPanel implements ActionListener{

    private JList tabla;
    private JScrollPane panelScroll;
    private Button b1, b2, b3;
    private MapController controller;

    /** 
     * Crea el panel de visualizacion del conjunto de mapas.
     * @param newMapController Enlace al controlador del conjunto de elementos.    
     */
    public CjtMapPanel(MapController newMapController){
        controller = newMapController;
    }
    
    /** 
     * Visualiza el menu del conjunto de mapas.
     * @param list Lista de nombres de tipo de habitaciones.    
     */
    public void visualitzation(String list[] ){
    
       Label et = new Label("Bienvenido, escoja una de las siguientes habitaciones");       
       setLayout(null);
       et.setBounds(225, 150, 500, 30);
       et.setFont(new Font("Arial", 0, 20));
       add(et);
      
       b1 = new Button("Mostrar (2D)");
       b1.setBounds(380,600,140,40);
       b1.addActionListener(this);
       add(b1);
       
       b2 = new Button("Volver");
       b2.setBounds(210,600,140,40);
       b2.addActionListener(this);
       add(b2);
       
       b3 = new Button("Eliminar");
       b3.setBounds(550,600,140,40);
       b3.addActionListener(this);
       add(b3);
       
       setLayout( null );
       tabla = new JList ( list );
       tabla.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
         
       panelScroll = new JScrollPane( tabla );   
       panelScroll.setBounds(375,250,150,200); 
       add( panelScroll);
       tabla.setSelectionBackground( Color.GREEN );
    } 
    
    /** 
     * Conjunto de acciones para cada boton del panel.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==b1) {            
            int indices = tabla.getSelectedIndex();
            if(indices == -1){               
              JOptionPane.showMessageDialog(null,"Seleccione una habitacion!","Error",JOptionPane.ERROR_MESSAGE);
           
            }
            else{
                ++indices;
                controller.showMap(indices, "2D");
            }            
        }
        else if (e.getSource()==b2){
            controller.goBack();
        } 
        else if (e.getSource()==b3){
            int indices = tabla.getSelectedIndex();
            if(indices == -1){               
              JOptionPane.showMessageDialog(null,"Seleccione una habitacion!","Error",JOptionPane.ERROR_MESSAGE);
            }
            else{
                ++indices;
                controller.removeMap(indices);                 
                controller.goBack();
            }
        }
    }
}
