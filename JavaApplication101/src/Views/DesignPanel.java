/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controllers.DesignController;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;


/**
 *
 * @author 11.2
 */
public class DesignPanel extends JPanel implements ActionListener {
      
    private boolean related;
    private int last;
    private String[][] mat;
    private DesignController designController;
    private Button b1,b2,b3, b4, b5;
    private JTextField textFieldX,textFieldY, textFieldDoor,textFieldWindow;
    private JList roomList;
    private JTable selectedTable,selectedTableEl;
    private JScrollPane panelScroll,panelSelected,panelSelectedEl;
    
    /** 
     * Crea un panel de restricciones. 
     * @param newDesignController Enlace al controlador de diseno.
     */
    public DesignPanel(DesignController newDesignController){
        this.designController = newDesignController;
     }
    
    /** 
     * Mensaje de no se ha encontrado solucion valida. 
     */
    public void notSolution(){
        JOptionPane.showMessageDialog(null,"No se ha encontrado una solución válida!","Error",JOptionPane.INFORMATION_MESSAGE);
        designController.goBack();
    }
    
    /** 
     * Mensaje de se ha agregado la solucion valida al conjunto. 
     */
    public void validSolution(){
        JOptionPane.showMessageDialog(null,"Se ha añadido la nueva solución válida al conjunto!","Info",JOptionPane.INFORMATION_MESSAGE);
        designController.goBackThree();
    }
    
    /** 
     * Consulta si un string es un entero positivo.
     * @param data String a comprobar.
     * @return '1' si es un entero positivo, '0' en caso contrario.
     */
    private boolean isInt(String data){
        for(int i = 0; i < data.length(); ++i){
            if(data.charAt(i) < '0' || data.charAt(i) > '9'){
                return false;
            }
        }
        return true;
    }
    
    /** 
     * Muesta la tabla de muebles relacionados.
     * @param idFurniture Lista de identificadores por posicion de mueble.
     * @param data Matriz de muebles relacionados.
     */
    public void showListRelated(List<Integer> idFurniture, String[][] data){
        if(!idFurniture.isEmpty()){
            String header[] = new String[4];
            header[0] = "Nombre";
            header[1] = "dimX";
            header[2] = "dimY";
            header[3] = "tipo";

            selectedTable = new JTable ( data,header ){
            @Override
            public boolean isCellEditable(int rowIndex, int vColIndex) {
                return false;
            }
            };
            selectedTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            selectedTable.getTableHeader().setReorderingAllowed(false) ;
            selectedTable.setSelectionBackground( Color.GREEN );
            selectedTable.getTableHeader().setResizingAllowed(false);
            
            if(related){
                remove(panelSelected);
            }
            panelSelected = new JScrollPane( selectedTable );   
            panelSelected.setBounds(260,270,250,183); 
            add( panelSelected);
            related = true;
        }
        else{
            JOptionPane.showMessageDialog(null,"No tiene muebles!","Info",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    /** 
     * Muesta la tabla de muebles.
     * @param data Matriz de muebles.
     */
    private void createTable(String[][] data){
        String header[];
        header = new String[]{"Nombre","dimX","dimY","dimMaxX","dimMaxY","dimMinX","dimMinY","altura","apilable","Tipo","Cantidad"};
        selectedTable = new JTable(data, header){
            @Override
            public boolean isCellEditable(int rowIndex, int vColIndex) {
                return false;
            }
        };
        selectedTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        selectedTable.getTableHeader().setReorderingAllowed(false) ;
        selectedTable.setSelectionBackground( Color.GREEN );
        selectedTable.getTableHeader().setResizingAllowed(false);

        panelSelected = new JScrollPane( selectedTable );   
        panelSelected.setBounds(150, 250, 600, 183);
        add( panelSelected);
    }
    
    /** 
     * Acciones a realizar al interaccionar con cada boton. 
     * @param e Evento, determina el boton con el que se ha interactuado.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource() == b1){
            int indx = roomList.getSelectedIndex();
            if(indx == -1){
                JOptionPane.showMessageDialog(null,"Seleccione una sala!","Error",JOptionPane.ERROR_MESSAGE);
            }
            else{
                designController.showList(indx);
            }
        }
        else if(e.getSource() == b2){
            designController.resetIdList();
            String tx = textFieldX.getText();
            String ty = textFieldY.getText();
            int indx = roomList.getSelectedIndex();
            if(tx.isEmpty() || ty.isEmpty()){
                JOptionPane.showMessageDialog(null,"Ha de dar un valor a los campos de Dimensión!","Error",JOptionPane.ERROR_MESSAGE);
            }
            else if(indx == -1){
                JOptionPane.showMessageDialog(null,"Seleccione una sala!","Error",JOptionPane.ERROR_MESSAGE);
            }
            else{   
                boolean err = !isInt(textFieldX.getText()) || !isInt(textFieldY.getText());                
                if(!err){
                    if(selectedTable != null){
                        try{
                            int indxArray[] = selectedTable.getSelectedRows();
                            int x = Integer.parseInt(textFieldX.getText());
                            int y = Integer.parseInt(textFieldY.getText());
                            int indxArrayEl[] = selectedTableEl.getSelectedRows();
                            if(indxArray.length == 0 && indxArrayEl.length == 0){
                                JOptionPane.showMessageDialog(null,"Ha de seleccionar muebles!","Error",JOptionPane.ERROR_MESSAGE);  
                            }  
                            else if(x < 200 || y < 200){
                                JOptionPane.showMessageDialog(null,"La habitacion no puede medir menos de 200x200!","Error",JOptionPane.ERROR_MESSAGE);  
                            }
                            else if(x > 870 || y > 700){
                                JOptionPane.showMessageDialog(null,"La habitacion no puede medir mas de 870x700!","Error",JOptionPane.ERROR_MESSAGE);  
                            }
                            else if((x%10 != 0) || (y%10 != 0)){
                                JOptionPane.showMessageDialog(null,"La habitacion necesita medidas multiples de 10!","Error",JOptionPane.ERROR_MESSAGE);  
                            }
                            else{ 
                                designController.next(indx,x,y,indxArray,indxArrayEl);
                            }
                        }catch(Exception ex){              
                            JOptionPane.showMessageDialog(null,"Ha de seleccionar los muebles correctamente!","Error",JOptionPane.ERROR_MESSAGE);          
                        }
                    }
                    else{
                        JOptionPane.showMessageDialog(null,"Debe mostrar los muebles relacionados con la sala!","Error",JOptionPane.ERROR_MESSAGE);
                    }
                }
                if(err){
                    JOptionPane.showMessageDialog(null,"Las dimensiones tienen que ser valores enteros positivos!","Error",JOptionPane.ERROR_MESSAGE);
                    textFieldX.setText("");
                    textFieldY.setText("");
                }
            }
        }
        else if(e.getSource() == b3){
            designController.goBack();
        }
        else if(e.getSource() == b4){            
            boolean err = !isInt(textFieldWindow.getText());
            if(!err){
                try{
                    int x = designController.getX();
                    int y = designController.getY();
                    int size = 2*x + 2*y;
                    int numDoor = 1;
                    int numWindow = Integer.parseInt(textFieldWindow.getText());
                    if((numDoor*50 + 50*numWindow) > (size/3)){
                           JOptionPane.showMessageDialog(null,"Ha seleccionado demasiadas ventanas!","Error",JOptionPane.ERROR_MESSAGE);                    
                    }
                    else{
                        designController.confirmAmount(mat, numDoor, numWindow);
                    }
                }catch(Exception ex){              
                            JOptionPane.showMessageDialog(null,"Ha de seleccionar el numero de ventanas!","Error",JOptionPane.ERROR_MESSAGE);          
                        }
            }
            else{
                JOptionPane.showMessageDialog(null,"El numero de ventanas ha de ser un valor entero positivo!","Error",JOptionPane.ERROR_MESSAGE);          
            }
                    
        }
        else if(e.getSource() == b5){
            String cant = textFieldX.getText();
            if(selectedTable.getSelectedRow() == -1 || selectedTable.getSelectedRow() >= last){
                JOptionPane.showMessageDialog(null,"Ha de seleccionar un mueble de la tabla!","Error",JOptionPane.ERROR_MESSAGE);
            }
            else if(cant.isEmpty()){
                JOptionPane.showMessageDialog(null,"Ha de escribir la nueva cantidad!","Error",JOptionPane.ERROR_MESSAGE);
            }
            else if(!isInt(cant)){
                JOptionPane.showMessageDialog(null,"La cantidad debe ser un valor entero positivo!","Error",JOptionPane.ERROR_MESSAGE);
            }
            else{
                mat[selectedTable.getSelectedRow()][10] = cant;
                remove(panelSelected);
                createTable(mat);
                textFieldX.setText("");
            }
        }
    }
    
    /** 
     * Actualiza la posicion de la ultima fila de la matriz.
     * @param data Matriz de datos.
     */
    private void lastIndex(String[][] data){
        boolean b = false;
        for(last = 0; last < data.length && !b; ++last){
            if(data[last][0].isEmpty()){
                b = true;
            }
        }
        --last;
    }
    
    /** 
     * Mensaje de no existen salas.
     */
    public void notExistsRoomType() {
        try{
            JOptionPane.showMessageDialog(null,"No existen salas","???",JOptionPane.INFORMATION_MESSAGE);  
        }catch(Exception e){              
            JOptionPane.showMessageDialog(null,"Parametros incorrectos","Error",JOptionPane.ERROR_MESSAGE);          
        }
        designController.goBack();
    }
    
    /** 
     * Mensaje de saltarse restriccion de mueble prohibido.
     */
    public int skipRestrictionDelete(String name) {
        return JOptionPane.showConfirmDialog(this,"Una restriccion prohibe a "+name+" estar en esta sala. ¿Quiere saltarse esta restricción?","",JOptionPane.YES_NO_OPTION);
    }
    
    /** 
     * Mensaje de saltarse restriccion de mueble obligatorio.
     */
    public int skipRestrictionAdd(String name) {
        return JOptionPane.showConfirmDialog(this,"Una restriccion obliga a "+name+" a estar en esta sala. ¿Quiere saltarse esta restricción?","",JOptionPane.YES_NO_OPTION);
    }
    
    /** 
     * Vista de la primera pantalla de diseno.
     */
    public void visualization(int maxId,int size,List<Integer> idFurniture,String list[],String data[][],String dataElements[][]) {
        if(size>0){
            show(maxId,idFurniture,list,data,dataElements);
        }
        else {
            notExistsRoomType();
        }
    }  
    
    /** 
     * Vista de la primera pantalla de diseno, seleccion de sala y muebles.
     */
    public void show(int maxId, List<Integer> idFurniture,String list[],String data[][],String dataElements[][]) {
        related = false;
        if(maxId == 0) {
                notExistsRoomType();
        }
        
        Label rm = new Label("Configure la habitación que quiere diseñar");       
        setLayout(null);
        rm.setBounds(255, 150, 500, 30);
        rm.setFont(new Font("Arial", 0, 20));
        add(rm);
        
        Label dim = new Label("Dimensión");
        Label dim2= new Label("ancho                  largo");
        dim.setBounds(310, 225, 80, 30);
        dim2.setBounds(407, 215, 300, 15);
        dim.setFont(new Font("Arial", 0, 15));
        dim2.setFont(new Font("Arial", 0, 11));
        add(dim);
        add(dim2);
        
        b1 = new Button("Muebles relacionados");
        b1.setBounds(110,470,140,40);
        b1.addActionListener(this);
       
        b2 = new Button("Siguiente");
        b2.addActionListener(this);
        b2.setBounds(480,600,140,40);
        
        b3 = new Button("Volver");
        b3.addActionListener(this);
        b3.setBounds(280,600,140,40);
        
        Label note1 = new Label("Nota: Puede ayudarse de las teclas Ctrl y Shift para seleccionar y deseleccionar muebles");
        note1.setBounds(260, 458, 450, 15);
        note1.setFont(new Font("Arial", 0, 11));
        add(note1);
       
        add(b1);
        add(b2);
        add(b3);

        textFieldX = new JTextField(20);
        textFieldX.setBounds(390,230,70,20);
        
        add(textFieldX);
        
        textFieldY = new JTextField(20);
        textFieldY.setBounds(470,230,70,20);
        
        add(textFieldY);
        
        roomList = new JList ( list );
        roomList.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        roomList.setSelectionBackground(Color.GREEN);
            
        panelScroll = new JScrollPane( roomList );   
        panelScroll.setBounds(110,270,140,183); 
        add( panelScroll);
        
        String header[];
        String aux[][] = new String[0][0];
        if(data != aux){
            header = new String[4];
            header[0] = "Nombre";
            header[1] = "dimX";
            header[2] = "dimY";
            header[3] = "tipo";

            selectedTableEl = new JTable ( dataElements,header ){
                @Override
                public boolean isCellEditable(int rowIndex, int vColIndex) {
                    return false;
                }
            };
            selectedTableEl.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            selectedTableEl.getTableHeader().setReorderingAllowed(false) ;
            selectedTableEl.setSelectionBackground( Color.GREEN );
            selectedTableEl.getTableHeader().setResizingAllowed(false);


            panelSelectedEl = new JScrollPane( selectedTableEl );
            panelSelectedEl.setBounds(520,270,267,183);    

            add( panelSelectedEl);
        }
        else {
            header = new String[1];
            header[0] = "No existen muebles";
            String listaux[][] = new String[0][0];
            selectedTableEl = new JTable ( listaux,header ){
                @Override
                public boolean isCellEditable(int rowIndex, int vColIndex) {
                    return false;
                }
            };
            selectedTableEl.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            selectedTableEl.getTableHeader().setReorderingAllowed(false) ;
            selectedTableEl.setSelectionBackground( Color.GREEN );
            selectedTableEl.getTableHeader().setResizingAllowed(false);
            selectedTableEl.setEnabled(false);
            panelSelectedEl = new JScrollPane( selectedTableEl );
            panelSelectedEl.setBounds(505,150,260,183); 

            add( panelSelectedEl);
        }
        
    }
    
    /** 
     * Vista para editar la cantidad de muebles, ventanas y puertas que apareceran en el diseno.
     */
    public void editAmount(String data[][]){   
        Label rm = new Label("Muebles seleccionados");       
        setLayout(null);
        rm.setBounds(335, 150, 500, 30);
        rm.setFont(new Font("Arial", 0, 20));
        add(rm);
        
        mat = data;
        
        createTable(data);
        
        lastIndex(data);
        
        Label note1 = new Label("Nota: Seleccione el mueble del que desea editar su cantidad");
        note1.setBounds(150, 438, 450, 15);
        note1.setFont(new Font("Arial", 0, 11));
        add(note1);
        
        Label h0 = new Label("Cantidad");       
        h0.setBounds(350, 490, 55, 20);
        h0.setFont(new Font("Arial", 0, 13));
        add(h0);
         
        textFieldX = new JTextField(20);
        textFieldX.setBounds(420, 490, 120, 20);
        add(textFieldX);
        
        Label h2 = new Label("Cantidad de venentanas");       
        h2.setBounds(270, 640, 140, 20);
        h2.setFont(new Font("Arial", 0, 13));
        add(h2);
         
        textFieldWindow = new JTextField(20);
        textFieldWindow.setBounds(420, 640, 120, 20);
        add(textFieldWindow); 
        
        
        
        b5 = new Button("Cambiar cantidad");
        b5.addActionListener(this);
        b5.setBounds(425,530,110,30);
        add(b5);
        
        b4 = new Button("Siguiente");
        b4.addActionListener(this);
        b4.setBounds(480,750,140,40);
        add(b4);
        
        b3 = new Button("Cancelar diseño");
        b3.addActionListener(this);
        b3.setBounds(280,750,140,40);
        add(b3);
        
    }
}
