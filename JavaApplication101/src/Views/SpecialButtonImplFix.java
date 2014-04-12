/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import java.awt.event.*;



public class SpecialButtonImplFix extends SpecialButtonFix implements KeyListener, ActionListener{    
   
    private int X, Y, Width, Height, xMax, yMax;
    private boolean puls;
    
    /** 
     * Crea un boton desplazable por el mapa.
     * @param mssg Nombre del boton.    
     * @param newXMin Parametro minimo del eje X.    
     * @param newXMax Parametro maximo del eje X.  
     * @param newYMin Parametro minimo del eje Y.
     * @param newYMax Parametro maximo del eje Y.
     */
    public SpecialButtonImplFix(String mssg, int newXMin, int newXMax, int newYMin, int newYMax) {
        super(mssg, newXMin, newXMax, newYMin, newYMax);
        this.xMax = newXMax;
        puls = false;
        this.yMax = newYMax;
        
        addKeyListener(this);
        addActionListener(this);
    }
    
    /** 
     * Crea un enlace a su super clase.              
     */
    public void setBot(){
        setBotHelp(this);
    }
    
    /** 
     * Control de presionar tecla.   
     * Cambia de estado si la tecla deja de presionarse y impide la rotacion.
     */
    @Override
    public void keyReleased( KeyEvent e ) {       
        puls = false;
    }
    
    /** 
     * Rotacion del boton.     
     * Intercambia anchura por profundidad y rota el boton.
     */
    public void rotate(){
        
        this.X = getX();
        this.Y = getY();
        this.Width = getWidth();
        this.Height = getHeight(); 
        if((this.X + this.Height) > this.xMax){
            this.X = this.X -((this.X+this.Height)-this.xMax);
        }
        if((this.Y + this.Width) > this.yMax){
            this.Y = this.Y -((this.Y+this.Width)-this.yMax);
        }
        setBounds(X,Y,Height,Width);                
        
    }

    @Override
    public void keyTyped(KeyEvent e) {}
    
    /** 
     * Control de presionar tecla.     
     * Controla si la letra que se ha seleccionado es la 'R', para poder rotar el boton.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        String pres = KeyEvent.getKeyText(e.getKeyCode());
        if(pres.endsWith(pres) && puls == false){
            puls = true;
        }       
    }
    
    /** 
     * Rota el boton.     
     * Rota el boton si se ha presionado la tecla 'R'.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (puls){
            rotate();
        }
    }
    
}
