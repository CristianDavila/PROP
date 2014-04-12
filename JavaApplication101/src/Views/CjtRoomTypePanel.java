/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controllers.CjtElementsController;
import Controllers.CjtRoomTypeController;
import java.util.ArrayList;
import java.util.List;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**Class CjtRoomTypeView
 * 
 * @author 11.2
 */
public class CjtRoomTypePanel extends JPanel implements ActionListener{
    
    private Button b1, b2, b3, b4, b5,b6,b7,b8,b9,b10,b11,b12,b13,b14,b15,b16,b17,b18,b19;
    private CjtRoomTypeController controller;
    private CjtElementsController elemController;
    private int last;
    private JList roomList;
    private JTextField textField;
    private JTable selectedTable;
    private JScrollPane panelScroll, panelSelected;
 
    /** 
     * Crea un panel de restricciones. 
     * @param newController Enlace al controlador de salas.
     * @param newelemController Enlace al controlador de muebles.
     */
    public CjtRoomTypePanel(CjtRoomTypeController newController, CjtElementsController newelemController) {
        elemController = newelemController;
        controller = newController;
        
    }
    
    /** 
     * Muestra por la interfaz un mensaje indicano que no existen salas. 
     */
    public void notExistsRoomType() {
        try{
            JOptionPane.showMessageDialog(null,"No existen salas","???",JOptionPane.INFORMATION_MESSAGE);  
        }catch(Exception e){              
            JOptionPane.showMessageDialog(null,"Parametros incorrectos","Error",JOptionPane.ERROR_MESSAGE);          
        }
        controller.goBackRoomTypeMenu();
    }
    
    /** 
     * Muestra el menu de salas en la interfaz.
     */
    public void mainVisualization() { 
       Label et = new Label("Escoja una de las siguientes opciones");       
       setLayout(null);
       et.setBounds(280, 150, 500, 30);
       et.setFont(new Font("Arial", 0, 20));
       add(et);
       
                
       b1 = new Button("Listar salas");
       b1.setBounds(380,250,140,40);
       b1.addActionListener(this);
       
       b2 = new Button("Eliminar sala");
       b2.addActionListener(this);
       b2.setBounds(380,350,140,40);
       
       b3 = new Button("Agregar sala");
       b3.addActionListener(this);
       b3.setBounds(380,450,140,40);
       
       b4 = new Button("Editar sala existente");
       b4.addActionListener(this);       
       b4.setBounds(380,550,140,40);
       
       b5 = new Button("Volver atras");
       b5.addActionListener(this);       
       b5.setBounds(380,650,140,40);

       add(b1);
       add(b2);
       add(b3);   
       add(b4); 
       add(b5);
       
    } 
    
    /** 
     * Acciones a realizar al interaccionar con cada boton. 
     * @param e Evento, determina el boton con el que se ha interactuado.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        
        if (e.getSource()== b1) {            
            controller.selector("1");
        }
        else if (e.getSource() == b2) {            
            controller.selector("2");
        }
        else if (e.getSource() == b3) {
            controller.selector("3");
        }
        else if (e.getSource() == b4) {
            controller.selector("4");
        }
        else if (e.getSource() == b5){
            controller.selector("5");
        }
        else if (e.getSource() == b6){
            int indx = roomList.getSelectedIndex();
            if(indx != -1){
                controller.refreshList(indx);
            }
            else {
                JOptionPane.showMessageDialog(null,"Seleccione una sala!","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (e.getSource() == b8){
            int indx = roomList.getSelectedIndex();
            if(indx != -1){
                int respuesta =JOptionPane.showConfirmDialog(this,"¿Desea proceder con la eliminación?","",JOptionPane.YES_NO_OPTION);
                if (respuesta == 0){ 
                    int indxArray[] = roomList.getSelectedIndices();
                    controller.refreshDelete(indxArray);
                }
                else {
                    controller.selector("2");
                }
            }
            else {
                JOptionPane.showMessageDialog(null,"Seleccione una sala!","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        else if (e.getSource() == b10){
                int arrayIndx[] = selectedTable.getSelectedRows();
                int indxb = selectedTable.getSelectedRow();
                String name = textField.getText();
                if(name.equals("")) {
                    JOptionPane.showMessageDialog(null,"Introduzca un nombre!","Error",JOptionPane.ERROR_MESSAGE);
                }
                else if(controller.checkName(name)){
                    JOptionPane.showMessageDialog(null,"Nombre ya utilizado!","Error",JOptionPane.ERROR_MESSAGE);
                    controller.selector("3");
                }
                boolean b = false;
                for(int i = 0; i < arrayIndx.length; ++i){
                    if(arrayIndx[i] > last){
                        b = true;
                    }
                }
                if(b || (indxb == -1)){
                    JOptionPane.showMessageDialog(null,"Seleccione los muebles correctamente!","Error",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    controller.refreshAdd(arrayIndx,name);
                }
        }
        else if(e.getSource() == b12){
            controller.selectorEdit(1);
        }
        else if(e.getSource() == b13){
            controller.selectorEdit(2);
        }
        else if(e.getSource() == b15){
            try{
                int arrayIndx[] = selectedTable.getSelectedRows();
                int indx = roomList.getSelectedIndex();
                int indxb = selectedTable.getSelectedRow();
                if(indx == -1){
                    JOptionPane.showMessageDialog(null,"Seleccione una sala!","Error",JOptionPane.ERROR_MESSAGE);
                }
                boolean b = false;
                for(int i = 0; i < arrayIndx.length; ++i){
                    if(arrayIndx[i] > last){
                        b = true;
                    }
                }
                if(b || (indxb == -1)){
                    JOptionPane.showMessageDialog(null,"Seleccione los muebles correctamente!","Error",JOptionPane.ERROR_MESSAGE);
                }
                else{
                    int respuesta = JOptionPane.showConfirmDialog(this,"¿Realmente desea añadir estos muebles?","",JOptionPane.YES_NO_OPTION);
                    if(respuesta == 0){
                        controller.refreshAddRelated(indx,arrayIndx);
                    }
                }
                controller.selectorEdit(1);
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null,"Seleccione los muebles correctamente!","Error",JOptionPane.ERROR_MESSAGE);
            }
        }
        else if(e.getSource() == b17){
            int indx = roomList.getSelectedIndex();
            if(indx == -1){
                JOptionPane.showMessageDialog(null,"Seleccione una sala!","Error",JOptionPane.ERROR_MESSAGE);
            }
            else{
                controller.refreshRemoveRelated(indx,null);
            }
        }
        else if(e.getSource() == b18){
            int indx = roomList.getSelectedIndex();
            int indxb = selectedTable.getSelectedRow();
            int arrayIndx[] = selectedTable.getSelectedRows();
            if(selectedTable != null){
                if(indx == -1){
                    JOptionPane.showMessageDialog(null,"Seleccione una sala!","Error",JOptionPane.ERROR_MESSAGE);
                }
                else {
                    boolean b = false;
                    for(int i = 0; i < arrayIndx.length; ++i){
                        if(arrayIndx[i] > last){
                            b = true;
                        }
                    }
                    if(b || (indxb == -1)){
                        JOptionPane.showMessageDialog(null,"Seleccione los muebles correctamente!","Error",JOptionPane.ERROR_MESSAGE);
                    }
                    else{
                        int respuesta =JOptionPane.showConfirmDialog(this,"¿Realmente desea eliminar estos muebles?","",JOptionPane.YES_NO_OPTION);
                        if(respuesta == 0) {
                           controller.refreshRemoveRelated(indx,arrayIndx);
                        }
                        else {
                            controller.refreshRemoveRelated(indx, null);
                        }
                    }
                }
            }
            else {
                JOptionPane.showMessageDialog(null,"Debe mostrar los muebles de una sala seleccionada!","Error",JOptionPane.ERROR_MESSAGE);
                controller.selectorEdit(2);
            }
        }
        else if (e.getSource() == b7 || e.getSource() == b9 || e.getSource() == b11 || e.getSource() == b14) {
            controller.goBackRoomTypeMenu();
        }
        else if(e.getSource() == b16 || e.getSource() == b19) {
            controller.selector("4");
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
                --last;
            }
        }
        --last;
    }
    
    /** 
     * Lista las salas existentes en la interfaz.
     */
    public void listar(int maxId, List<Integer> idFurniture,String list[],String data[][],int indx) {

        
        Label ls = new Label("Salas existentes");       
        setLayout(null);
        ls.setBounds(370, 150, 500, 30);
        ls.setFont(new Font("Arial", 0, 20));
        add(ls);
        if(maxId == 0) {
            notExistsRoomType();
        }
        setLayout( null );
        roomList = new JList ( list );
        roomList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        roomList.setSelectionBackground( Color.GREEN );
        
        if(indx!=-1){
            roomList.setSelectedIndex(indx);
        }
        
        panelScroll = new JScrollPane( roomList );   
        panelScroll.setBounds(138,250,300,300); 
         
        add( panelScroll);
           
            
        b6 = new Button("Mostrar");
        b6.addActionListener(this);       
        b6.setBounds(280,650,140,40);
            
        b7 = new Button("Volver");
        b7.addActionListener(this);       
        b7.setBounds(480,650,140,40);
       
        add(b6);
        add(b7);
                  
        if(idFurniture != null){
            String header[] = new String[4];
            header[0] = "Nombre";
            header[1] = "dimX";
            header[2] = "dimY";
            header[3] = "tipo";

            selectedTable = new JTable ( data,header );
            selectedTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            selectedTable.getTableHeader().setReorderingAllowed(false) ;
            selectedTable.setSelectionBackground( Color.GREEN );
            selectedTable.getTableHeader().setResizingAllowed(false);
            selectedTable.setEnabled(false);
            
            panelSelected = new JScrollPane( selectedTable );   
            panelSelected.setBounds(458,250,300,183); 
               
            add( panelSelected);
            if(idFurniture.isEmpty()) {
                JOptionPane.showMessageDialog(null,"No tiene muebles!","Info",JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
 
    /** 
     * Muestra la interfaz para eliminar salas.
     */
    public void removeVisualization(int maxId,int size,String list[]) {
        if(size > 0) {
            this.listarRemove(maxId,size,list);
        }
        else {
            notExistsRoomType();
        }
    }
    
    /** 
     * Lista las salas existentes en la interfaz para eliminar.
     */
    public void listarRemove(int maxId, int size, String list[]) {

        if(maxId == 0) {
            notExistsRoomType();
        }
            
        Label rm = new Label("Escoja las salas a eliminar");       
        setLayout(null);
        rm.setBounds(330, 150, 500, 30);
        rm.setFont(new Font("Arial", 0, 20));
        add(rm);
            
        setLayout( null );
        roomList = new JList ( list );
        roomList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        roomList.setSelectionBackground( Color.GREEN );
        
        panelScroll = new JScrollPane( roomList );   
        panelScroll.setBounds(300,250,300,300); 
            
        add( panelScroll);
           
            
        b8 = new Button("Eliminar");
        b8.addActionListener(this);       
        b8.setBounds(280,650,140,40);
            
        b9 = new Button("Volver");
        b9.addActionListener(this);       
        b9.setBounds(480,650,140,40);
       
        add(b8);
        add(b9);
              
    }
    
    /** 
     * Muestra la interfaz para listar salas.
     */
    public void showVisualization(int maxId,int size, List<Integer> idFurniture, String list[], String data[][],int indx) {
        if(size > 0) {
            this.listar(maxId,idFurniture,list,data,indx);
        }
        else {
            notExistsRoomType();
        }
    }

    /** 
     * Muestra la interfaz para editar salas.
     */
    public void editVisualization() {
       Label ed = new Label("Escoja una de las siguientes opciones");       
       setLayout(null);
       ed.setBounds(278, 200, 500, 30);
       ed.setFont(new Font("Arial", 0, 20));
       add(ed);
       
                
       b12 = new Button("Agregar muebles");
       b12.setBounds(380,300,140,40);
       b12.addActionListener(this);
       
       b13 = new Button("Eliminar muebles");
       b13.addActionListener(this);
       b13.setBounds(380,400,140,40);
       
       b14 = new Button("Volver");
       b14.addActionListener(this);
       b14.setBounds(380,500,140,40);
       
       add(b12);
       add(b13);
       add(b14);
    }
 
    /** 
     * Muestra la interfaz para añadir muebles relacionados a las salas.
     */
    public void addRelatedVisualization(int maxId,int size, String list[], String data[][]) {
        if(size > 0) {
            this.addRelated(maxId,list,data);
        }
        else {
            notExistsRoomType();
        }
    }
    
    /** 
     * Muestra la interfaz para eliminar muebles relacionados a las salas.
     */
    public void removeRelatedVisualization(int maxId,int size, List<Integer> idFurniture,String list[],String data[][],int indx){
        if(size > 0) {
            this.removeRelated(maxId,idFurniture,list,data,indx);
        }
        else {
            notExistsRoomType();
        }
    }
    
    /** 
     * Lista las salas en la interfaz para eliminar muebles relacionados.
     */
    public void removeRelated(int maxId, List<Integer> idFurniture,String list[],String data[][], int indx){
        
        if(maxId == 0) {
            notExistsRoomType();
        }
        setLayout( null );
            
        Label ed = new Label("Eliminar mueble de relacionados");       
        ed.setBounds(305, 150, 500, 30);
        ed.setFont(new Font("Arial", 0, 20));
        add(ed);
            
        roomList = new JList ( list );
        roomList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        roomList.setSelectionBackground( Color.GREEN );
            
        if(indx!=-1 && !idFurniture.isEmpty()){
            roomList.setSelectedIndex(indx);
            roomList.setEnabled(false);
        }
          
        panelScroll = new JScrollPane( roomList );   
        panelScroll.setBounds(138,250,300,300); 
           
        add( panelScroll);
           
            
        b17 = new Button("Mostrar");
        b17.addActionListener(this);       
        b17.setBounds(138,650,140,40);
         
        b18 = new Button("Eliminar");
        b18.addActionListener(this);       
        b18.setBounds(379,650,140,40);
            
        b19 = new Button("Volver");
        b19.addActionListener(this);       
        b19.setBounds(620,650,140,40);
            
        add(b17);
        add(b18);
        add(b19);
                  
        if(idFurniture != null){
            String header[] = new String[4];
            header[0] = "Nombre";
            header[1] = "dimX";
            header[2] = "dimY";
            header[3] = "tipo";
            lastIndex(data);

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
            panelSelected = new JScrollPane( selectedTable );   
            panelSelected.setBounds(458,250,300,183);
             
            add( panelSelected );
            if(idFurniture.isEmpty()) {
                JOptionPane.showMessageDialog(null,"No tiene muebles!","Info",JOptionPane.INFORMATION_MESSAGE);
                selectedTable.setEnabled(false);
            }
               
        }
    }
    
    /** 
     * Muestra la interfaz para agregar muebles relacionados a las salas.
     */
    public void addRelated(int maxId, String list[], String data[][]){
        
        if(maxId == 0) {
            notExistsRoomType();
        }
        setLayout( null );

        Label adreltitle = new Label("Añadir mueble a relacionados");       
        adreltitle.setBounds(310, 150, 400, 30);
        adreltitle.setFont(new Font("Arial", 0, 20));
        add(adreltitle);
           
        roomList = new JList ( list );
        roomList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        roomList.setSelectionBackground(Color.GREEN);
        
        panelScroll = new JScrollPane( roomList );   
        panelScroll.setBounds(138,250,300,300); 
            
        add( panelScroll);
        String header[];
        String aux[][] = new String[0][0];
        if(data != aux){
            header = new String[4];
            header[0] = "Nombre";
            header[1] = "dimX";
            header[2] = "dimY";
            header[3] = "tipo";
            lastIndex(data);
            
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
            
           
            panelSelected = new JScrollPane( selectedTable );
            panelSelected.setBounds(458,250,300,183); 
             
            add( panelSelected);
        }
        else {
            header = new String[1];
            header[0] = "No existen muebles";
            String listaux[][] = new String[0][0];
            selectedTable = new JTable ( listaux,header ){
                @Override
                public boolean isCellEditable(int rowIndex, int vColIndex) {
                    return false;
                }
            };
            selectedTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            selectedTable.getTableHeader().setReorderingAllowed(false) ;
            selectedTable.setSelectionBackground( Color.GREEN );
            selectedTable.getTableHeader().setResizingAllowed(false);
            
            panelSelected = new JScrollPane( selectedTable );
            panelSelected.setBounds(458,200,300,23); 
               
            add( panelSelected);
        }
        b15 = new Button("Añadir");
        b15.addActionListener(this);       
        b15.setBounds(280,650,140,40);
          
        b16 = new Button("Volver");
        b16.addActionListener(this);       
        b16.setBounds(480,650,140,40);
       
        add(b15);
        add(b16);   
    }   
    
    /** 
     * Muestra la interfaz para agregar salas.
     */
    public void addVisualization(int maxId,int size, String list[], String data[][]) {
        int y = 225;
        int tam = 300;
        setLayout( null );
        if(size > 0) {
            y = 585; 
            tam = 620;
            roomList = new JList ( list );
            roomList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            roomList.setSelectionBackground(Color.WHITE);
            
            panelScroll = new JScrollPane( roomList );   
            panelScroll.setBounds(138,225,300,300); 
            
            add( panelScroll);
        }
        
        Label ad = new Label("Escriba el nombre de la sala a crear:");       
        setLayout(null);
        ad.setBounds(138, y-35, tam, 30);
        ad.setFont(new Font("Arial", 0, 15));
        add(ad);
        
        Label adtitle = new Label("Añadir Sala");       
        adtitle.setBounds(395, 150, 400, 30);
        adtitle.setFont(new Font("Arial", 0, 20));
        add(adtitle);
    
        textField = new JTextField(20);
        textField.setBounds(138,y,tam,25);
        add(textField);
            
        String header[];
        String aux[][] = new String[0][0];
        if(data != aux){
            header = new String[4];
            header[0] = "Nombre";
            header[1] = "dimX";
            header[2] = "dimY";
            header[3] = "tipo";
            lastIndex(data);
            
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
            
            
            panelSelected = new JScrollPane( selectedTable );
            panelSelected.setBounds(458,225,300,183); 
             
            add( panelSelected);
        }
        else {
            header = new String[1];
            header[0] = "No existen muebles";
            String listaux[][] = new String[0][0];
            selectedTable = new JTable ( listaux,header ){
                @Override
                public boolean isCellEditable(int rowIndex, int vColIndex) {
                    return false;
                }
            };
            selectedTable.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
            selectedTable.getTableHeader().setReorderingAllowed(false) ;
            selectedTable.setSelectionBackground( Color.GREEN );
            selectedTable.getTableHeader().setResizingAllowed(false);
        
            panelSelected = new JScrollPane( selectedTable );
            panelSelected.setBounds(458,225,300,23); 
                
            add( panelSelected);
        }
        b10 = new Button("Añadir");
        b10.addActionListener(this);       
        b10.setBounds(280,650,140,40);
          
        b11 = new Button("Volver");
        b11.addActionListener(this);       
        b11.setBounds(480,650,140,40);
      
        add(b10);
        add(b11);
}
}
