/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;


import java.util.Random;
import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.List;

 
 public class MapEditPanel extends JPanel{
 
    private SortedMap<String, Color> cjtColor = new TreeMap();
    private int xMin, yMin, xMax, yMax, width, length;    
    private List<String[]> dat = new ArrayList();    
    Random rand = new Random();   
    private int[][] doors;
    private int[][] windows;
    private boolean edit;
    
    /** 
     * Crea el panel de edicion del mapa.     
     */
    public MapEditPanel(){        
    }
    
    
    @Override
    public void paint( Graphics g )
    {
       super.paint( g );
       cover(g);  
       if(edit){
        printDoor(g);
        printWindow(g);
       }
    }
    
    /** 
     * Pinta sobre el panel las ventanas.     
     */
    public void printWindow(Graphics g){
        int x, y;        
        g.setColor(Color.LIGHT_GRAY);
        for(int i=0; i< this.windows.length; ++i){
            x=this.windows[i][0];
            y = this.windows[i][1]; 
            if(x == 0){
                g.fillRect(xMin-4, (y+yMin), 8, 50);
            }
            else if(y == 0){
                g.fillRect(xMin+x, (yMin-4), 50, 8);
            }
            else if((x+50) == this.width){
                g.fillRect(xMax-4, (y+yMin), 8, 50);
            }
            else if((y+50) == this.length){
                g.fillRect(xMin+x, (yMax-4), 50, 8);
            }
        }
    }
    
    /** 
     * Pinta sobre el panel las puertas.     
     */
    public void printDoor(Graphics g){
        int x, y;        
        g.setColor(Color.BLUE);
        for(int i=0; i< this.doors.length; ++i){
            x=this.doors[i][0];
            y = this.doors[i][1];            
            if(x == 0){
                if(y < (yMax-yMin)/2){
                    //superior                    
                    g.fillRect(xMin+1, yMin+y+1, 55, 5);
                    g.drawArc(xMin-50, yMin+y+1-45, 105, 100, 268, 91);
                    // (xMin-50, yMin+y+1-40) -> posicion de la esquina superior izquierda del cuadrado que contiene al circulo
                    // 275 -> grado donde empieza el circulo
                    // 90 -> grados del arco, empezando en 275
                }
                else{
                    y = y+50;
                    //inferior                    
                    g.fillRect(xMin+x+1, yMin+y+1, 55, 5);
                    g.drawArc(xMin-50, yMin+y+1-45, 105, 100, 92, -90);
                }
            }
            else if(y == 0){
                if(x < (xMax-xMin)/2){
                    //izquierda                   
                    g.fillRect(xMin+x+1, yMin+y+1, 5, 55);
                    g.drawArc(xMin+x-45, yMin+1-46, 105, 100, 268, 96);                    
                }
                else{                    
                    x = x+50;                  
                    g.fillRect(xMin+x+1, yMin+y+1, 5, 55);
                    g.drawArc(xMin+x-45, yMin+1-46, 105, 100, 268, -91);
                }
            }
            
            if((x+50) == this.width){
                if(y < (yMax-yMin)/2){
                    //superior   
                    
                    g.fillRect(xMax+1, yMin+y+1,-55, 5);
                    g.drawArc(xMax-55, yMin+y+1-45, 105, 100, 173, 100);                    
                }
                
                else{
                    //inferior                     
                    if(y==this.length) {
                        y=y-5;
                    }
                    y=y+50;
                    g.fillRect(xMax+1, yMin+y+1,-55, 5);
                    g.drawArc(xMax-55, yMin+y+1-45, 105, 100, 173, -89);
                }
            }
            else if((y+50) == this.length){
                if(x < (xMax-xMin)/2){
                    //izquierda                   
                    g.fillRect(xMin+x+1, yMax+1, 5, -55);
                    g.drawArc(xMin+x-45, yMax+1-55, 105, 100, 356, 96);                    
                }
                else{                    
                    x=x+50;
                    g.fillRect(xMin+x+1, yMax+1, 5, -55);
                    g.drawArc(xMin+x-45, yMax+1-55, 105, 100, 185, -91); 
                }
            }            
        }
    }        
    
    /** 
     * Pinta sobre el panel las paredes de la habitacion.    
     */
    public void cover(Graphics g){
        g.setColor(Color.BLACK);
       int i;
       
       for(i=0; i<3; ++i)
            g.drawLine(xMin+i, yMin-i, xMax-i, yMin-i); //x    
       
       for(i=0; i<3; ++i)
            g.drawLine(xMin-i, yMin+i, xMin-i, yMax-i); //y       
      
       for(i=0; i<3; ++i)
            g.drawLine(xMin+i, yMax+i, xMax-i, yMax+i); //x
       
       for(i=0; i<3; ++i)
            g.drawLine(xMax+i, yMin+i, xMax+i, yMax-i); //y
       
       g.setColor(Color.LIGHT_GRAY);
       for(int y = yMin+5; y<yMax; y=y+5)
            g.drawLine(xMin+1, y, xMax-1, y);
       
       for(int x = xMin+5; x < xMax; x = x+5)
            g.drawLine(x, yMin+1, x, yMax-1);
    }    
    
 
   /** 
     * Establece los parametros del mapa. 
     * @param width Ancho de la habitacion.
     * @param length Largo de la habitacion.
     * @param xMin Posicion minima en el eje de las X.
     * @param yMin Posicion minima en el eje de las Y.
     * @param xMax Posicion maxima en el eje de las X.
     * @param yMax Posicion maxima en el eje de las Y.
     * @param dat  Conjunto de elementos.
     * @param door Conjunto de puertas.
     * @param window Conjunto de ventanas.
     * @param edit Establece si edita con muebles o sin muebles.
     */
    public void dibujar(int width, int length, int xMin, int yMin, int xMax, int yMax, List<String[]> dat, int[][] door, int[][] window, boolean edit){      
       this.xMin = xMin;
       this.yMin = yMin;
       this.xMax = xMax;
       this.yMax = yMax;
       this.dat = dat;
       this.width = width;
       this.length = length;
       this.doors = door;
       this.windows = window;
       this.edit = edit;
    } 
 } 
