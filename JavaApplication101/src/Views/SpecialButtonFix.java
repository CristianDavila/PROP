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
public class SpecialButtonFix extends JButton implements MouseMotionListener{
    
    private int xMin, xMax, yMin, yMax;
    private String name;
    SpecialButtonImplFix botRot;
    boolean rotX, rotY;
    
    /** 
     * Crea un boton desplazable por el mapa.
     * @param mssg Nombre del boton.    
     * @param newXMin Parametro minimo del eje X.    
     * @param newXMax Parametro maximo del eje X.  
     * @param newYMin Parametro minimo del eje Y.
     * @param newYMax Parametro maximo del eje Y.
     */
    public SpecialButtonFix( String mssg, int newXMin, int newXMax, int newYMin, int newYMax){  
            xMin = newXMin;
            xMax = newXMax;
            yMin = newYMin;
            yMax = newYMax;
            name = mssg;
            rotX = rotY = false;
            setLayout(new FlowLayout());            
            super.setText(mssg);            
            addMouseMotionListener(this);
            this.setBackground(Color.LIGHT_GRAY);
    }
    
    /** 
     * Crea un enlace a su subclase.
     * @param aux Enlace a la subclase.         
     */
    public void setBotHelp(SpecialButtonImplFix aux){
        botRot = aux;
    }
    
    /** 
     * Accion de desplazamiento limitado a los bordes del mapa.     
     */
    @Override
        public void mouseDragged(MouseEvent mme){          
            int aux = this.getX() + mme.getX() - this.getWidth() / 2;
            aux = (aux/5) * 5; //Aling 5 (al alinear asi, los numeros pasan de 5 en 5)
            int aux2 = this.getY() + mme.getY() - this.getHeight() / 2;
            aux2 = (aux2/5) *5; //Aling 5
            
            if((xMin <= aux && (aux+this.getWidth()) <= xMax && yMin <= aux2 && aux2+this.getHeight() <= yMax)){                 
                if((aux-xMin) < 70) aux = xMin;
                else if((xMax-aux) < 120){
                    if(name.equals("P"))aux = xMax-50;
                    else{ 
                        aux = xMax-(this.getWidth());
                    }
                }
                if((aux2-yMin) < 70) aux2 = yMin;
                else if((yMax-aux2) < 120){
                    if(name.equals("P"))aux2 = yMax-50;   
                    else{                        
                        aux2 = yMax-this.getHeight();
                    }   
                }
                setLocation(aux,aux2);                
            }   
        }  
    @Override
    public void mouseMoved(MouseEvent mme) {            
    }
        
        
    
}
