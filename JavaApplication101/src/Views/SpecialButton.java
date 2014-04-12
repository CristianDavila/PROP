/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;


import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import javax.swing.JButton;

/**
 *
 * @author 11.2
 */
public class SpecialButton extends JButton implements MouseMotionListener{
    
    private int xMin, xMax, yMin, yMax;
    
    /** 
     * Crea un boton desplazable por el mapa.
     * @param mssg Nombre del boton.    
     * @param newXMin Parametro minimo del eje X.    
     * @param newXMax Parametro maximo del eje X.  
     * @param newYMin Parametro minimo del eje Y.
     * @param newYMax Parametro maximo del eje Y.
     */
    public SpecialButton(String mssg, int newXMin, int newXMax, int newYMin, int newYMax){  
        xMin = newXMin;
        xMax = newXMax;
        yMin = newYMin;
        yMax = newYMax;
        setLayout(new FlowLayout());            
        super.setText(mssg);            
        addMouseMotionListener(this);
        this.setBackground(Color.LIGHT_GRAY);
    }
    
     /** 
     * Accion de desplazamiento limitado al mapa.     
     */
    @Override
        public void mouseDragged(MouseEvent mme){          
            int aux = this.getX() + mme.getX() - this.getWidth() / 2;
            aux = (aux/5) * 5; //Aling 5 (al alinear asi, los numeros pasan de 5 en 5)
            int aux2 = this.getY() + mme.getY() - this.getHeight() / 2;
            aux2 = (aux2/5) *5; //Aling 5
            if(xMin <= aux && aux+this.getWidth() <= xMax && yMin <= aux2 && aux2+this.getHeight() <= yMax) setLocation(aux,aux2);              
            
        }  
    
    @Override
        public void mouseMoved(MouseEvent mme) {           
        }
        
        
    
}
