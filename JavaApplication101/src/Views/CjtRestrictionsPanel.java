/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;

import Controllers.CjtElementsController;
import Controllers.CjtRestrictionsController;
import Controllers.CjtRoomTypeController;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/**
 * 
 * @author 11.2
 */
public class CjtRestrictionsPanel extends JPanel implements ActionListener{
    
    private JScrollPane panelScroll, panelScroll4Rooms;
    private JTable tabla;
    private JList rooms;
    private JTextField t0, t1, t2, t3;
    private String m1, m2;
    private int pos1, pos2, last;
    private JRadioButton siRadio, noRadio;
    private ButtonGroup radioGroup;
    private Button b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14, b15, b16;
    private CjtRestrictionsController controller; 
    private CjtElementsController objectsController;
    private CjtRoomTypeController roomTypeController;
    
    /** 
     * Crea un panel de restricciones. 
     * @param newController Enlace al controlador de restricciones.
     * @param cjtObjectsController Enlace al controlador de muebles.
     * @param cjtRoomTypeController Enlace al controlador de salas.
     */
    public CjtRestrictionsPanel(CjtRestrictionsController newController, CjtElementsController newObjectsController, CjtRoomTypeController newRoomTypeController){
        this.controller = newController;
        objectsController = newObjectsController;
        roomTypeController = newRoomTypeController;
    }
    
    /** 
     * Acciones a realizar al interaccionar con cada boton. 
     * @param e Evento, determina el boton con el que se ha interactuado.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==b0) {            
            controller.mainMenu();
        }
        else if (e.getSource()==b1) {            
            controller.mainSelector("1");
        }
        else if (e.getSource()==b2) {
            controller.mainSelector("2");
        }
        else if (e.getSource()==b3) {
            controller.mainSelector("3");
        }
        else if (e.getSource()==b4){            
            controller.mainSelector("4");
        }
        else if (e.getSource()==b5) {
            controller.showSelector("1");
        }
        else if (e.getSource()==b6){            
            controller.showSelector("2");
        }
        else if (e.getSource()==b7) {
            controller.removeSelector("1");
        }
        else if (e.getSource()==b8){            
            controller.removeSelector("2");
        }
        else if (e.getSource()==b9) {
            if(controller.isOEmpty()){
                JOptionPane.showMessageDialog(null,"No existen muebles!","Error",JOptionPane.ERROR_MESSAGE);
            }
            else{
                controller.addSelector("1");
            }
        }
        else if (e.getSource()==b10){  
            if(controller.isRTEmpty()){
                JOptionPane.showMessageDialog(null,"No existen salas!","Error",JOptionPane.ERROR_MESSAGE);
            }
            else if(controller.isOEmpty()){
                JOptionPane.showMessageDialog(null,"No existen muebles!","Error",JOptionPane.ERROR_MESSAGE);
            }
            else{
                controller.addSelector("2");
            }
        }
        else if (e.getSource()==b11){    
            int indice = tabla.getSelectedRow();
            if(indice == -1){               
              JOptionPane.showMessageDialog(null,"Seleccione una restriccion!","Error",JOptionPane.ERROR_MESSAGE);
            }
            else{
                ++indice;                
                controller.removeRestrictionDistance(indice);
                JOptionPane.showMessageDialog(null,"Restriccion Eliminada!","Aviso",JOptionPane.INFORMATION_MESSAGE);
            }
            controller.mainMenu();
        }
        else if (e.getSource()==b12){    
            int indice = tabla.getSelectedRow();
            if(indice == -1){               
              JOptionPane.showMessageDialog(null,"Seleccione una restriccion!","Error",JOptionPane.ERROR_MESSAGE);
            }
            else{
                ++indice;                
                controller.removeRestrictionRoomType(indice);
                JOptionPane.showMessageDialog(null,"Restriccion Eliminada!","Aviso",JOptionPane.INFORMATION_MESSAGE);
            }
            controller.mainMenu();
        }
        else if (e.getSource()==b13){ 
            String name = t0.getText();
            String dist = t1.getText();
            if(name.isEmpty()){
                JOptionPane.showMessageDialog(null,"Ha de asignarle un nombre a la restriccion!","Error",JOptionPane.ERROR_MESSAGE);
            }
            else if(dist.isEmpty()){
                JOptionPane.showMessageDialog(null,"Ha de asignarle una distancia a la restriccion!","Error",JOptionPane.ERROR_MESSAGE);
            }
            else if(!isInt(dist)){
                JOptionPane.showMessageDialog(null,"La distancia debe ser un valor entero positivo!","Error",JOptionPane.ERROR_MESSAGE);
            }
            else if(m1.isEmpty() || m2.isEmpty()){
                JOptionPane.showMessageDialog(null,"Ha de seleccionar dos muebles para crear la restriccion!","Error",JOptionPane.ERROR_MESSAGE);
            }
            else{
                String[] list = new String[]{name, Integer.toString(pos1), Integer.toString(pos2), dist};
                int done = controller.addRestrictionDistance(list);
                if(done == 1){
                    JOptionPane.showMessageDialog(null,"Restriccion Agregada!","Aviso",JOptionPane.INFORMATION_MESSAGE);
                    
                    t0.setText("");
                    t1.setText("");
                    t2.setText("");
                    t3.setText("");
                    m1 = "";
                    m2 = "";
                    tabla.clearSelection();
                }
                else{
                    JOptionPane.showMessageDialog(null,"Ya existe una restriccion de este tipo entre los muebles seleccionados!","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        else if (e.getSource()==b14){ 
            int indice = tabla.getSelectedRow();
            if(indice == -1 || tabla.getSelectedRow() > last){               
              JOptionPane.showMessageDialog(null,"Ha de seleccionar un mueble de la tabla!","Error",JOptionPane.ERROR_MESSAGE);
            }
            else{              
                m1 = (String) tabla.getValueAt(indice, 0);
                pos1 = indice;
                t2.setText(m1);
            }
        }
        else if (e.getSource()==b15){ 
            int indice = tabla.getSelectedRow();
            if(indice == -1 || tabla.getSelectedRow() > last){               
              JOptionPane.showMessageDialog(null,"Ha de seleccionar un mueble de la tabla!","Error",JOptionPane.ERROR_MESSAGE);
            }
            else{              
                m2 = (String) tabla.getValueAt(indice, 0);
                pos2 = indice;
                t3.setText(m2);
            }
        }
        else if (e.getSource()==b16){ 
            String name = t0.getText();
            if(name.isEmpty()){
                JOptionPane.showMessageDialog(null,"Ha de asignarle un nombre a la restriccion!","Error",JOptionPane.ERROR_MESSAGE);
            }
            else if(rooms.getSelectedIndex() == -1){
                JOptionPane.showMessageDialog(null,"Ha de seleccionar una sala de la lista!","Error",JOptionPane.ERROR_MESSAGE);
            }
            else if(tabla.getSelectedRow() == -1 || tabla.getSelectedRow() > last){
                JOptionPane.showMessageDialog(null,"Ha de seleccionar un mueble de la tabla!","Error",JOptionPane.ERROR_MESSAGE);
            }
            else if(!siRadio.isSelected() && !noRadio.isSelected()){
                JOptionPane.showMessageDialog(null,"Ha de seleccionar una de las dos opciones de Permitir!","Error",JOptionPane.ERROR_MESSAGE);
            }
            else{
                String[] list;
                if(siRadio.isSelected()){
                    list = new String[]{name, (String)rooms.getSelectedValue(), Integer.toString(tabla.getSelectedRow()), "Si"};
                }
                else{
                    list = new String[]{name, (String)rooms.getSelectedValue(), Integer.toString(tabla.getSelectedRow()), "No"};
                }
                
                int done = controller.addRestrictionRoomType(list);
                if(done == 1){
                    JOptionPane.showMessageDialog(null,"Restriccion Agregada!","Aviso",JOptionPane.INFORMATION_MESSAGE);
                    
                    t0.setText("");
                    rooms.clearSelection();
                    tabla.clearSelection();
                    radioGroup.clearSelection();
                }
                else{
                    JOptionPane.showMessageDialog(null,"Ya existe una restriccion de este tipo entre la sala y el mueble seleccionado!","Error",JOptionPane.ERROR_MESSAGE);
                }
            }
        }
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
     * Muestra el menu de restricciones en la interfaz.
     */
    public void mainVisualization(){
        Label et = new Label("Escoja una de las siguientes opciones");       
        setLayout(null);
        et.setBounds(285, 150, 400, 30);
        et.setFont(new Font("Arial", 0, 20));
        add(et);

        b1 = new Button("Listar restricciones");
        b1.setBounds(380,250,140,40);
        b1.addActionListener(this);

        b2 = new Button("Eliminar una restriccion");
        b2.addActionListener(this);
        b2.setBounds(380,350,140,40);

        b3 = new Button("Agregar restricciones");
        b3.addActionListener(this);
        b3.setBounds(380,450,140,40);

        b4 = new Button("Volver a Configuracion");
        b4.addActionListener(this);       
        b4.setBounds(380,550,140,40);

        add(b1);
        add(b2);
        add(b3);   
        add(b4);  
    }
    
    /** 
     * Muestra el menu para escoger las restricciones a listar en la interfaz.
     */
    public void showVisualization(){  
        Label et = new Label("Escoja el tipo de restricciones a listar");       
        setLayout(null);
        et.setBounds(282, 150, 400, 30);
        et.setFont(new Font("Arial", 0, 20));
        add(et);

        b5 = new Button("Distancia");
        b5.setBounds(380,250,140,40);
        b5.addActionListener(this);

        b6 = new Button("Sala-Mueble");
        b6.addActionListener(this);
        b6.setBounds(380,350,140,40);

        b0 = new Button("Volver a Restricciones");
        b0.addActionListener(this);
        b0.setBounds(380,450,140,40);

        add(b5);
        add(b6);
        add(b0);   
    }
    
    /** 
     * Muestra el menu para escoger las restricciones a eliminar en la interfaz.
     */
    public void removeVisualization(){
        Label et = new Label("Escoja el tipo de restricciones a eliminar");       
        setLayout(null);
        et.setBounds(282, 150, 400, 30);
        et.setFont(new Font("Arial", 0, 20));
        add(et);

        b7 = new Button("Distancia");
        b7.setBounds(380,250,140,40);
        b7.addActionListener(this);

        b8 = new Button("Sala-Mueble");
        b8.addActionListener(this);
        b8.setBounds(380,350,140,40);

        b0 = new Button("Volver a Restricciones");
        b0.addActionListener(this);
        b0.setBounds(380,450,140,40);

        add(b7);
        add(b8);
        add(b0);   
    }
        
    /** 
     * Muestra el menu para escoger las restricciones a agregar en la interfaz.
     */
    public void addVisualization(){  
        Label et = new Label("Escoja el tipo de restricciones a agregar");       
        setLayout(null);
        et.setBounds(282, 150, 400, 30);
        et.setFont(new Font("Arial", 0, 20));
        add(et);

        b9 = new Button("Distancia");
        b9.setBounds(380,250,140,40);
        b9.addActionListener(this);

        b10 = new Button("Sala-Mueble");
        b10.addActionListener(this);
        b10.setBounds(380,350,140,40);

        b0 = new Button("Volver a Restricciones");
        b0.addActionListener(this);
        b0.setBounds(380,450,140,40);

        add(b9);
        add(b10);
        add(b0);  
    }
    
    /** 
     * Lista las restricciones de tipo Distancia en la interfaz.
     */
    public void showRestrictionDistance(String [][] data) {
        Label et = new Label("Lista de restricciones del tipo Distancia");       
        setLayout(null);
        et.setBounds(270, 150, 400, 30);
        et.setFont(new Font("Arial", 0, 20));
        add(et);

        b0 = new Button("Volver a Restricciones");
        b0.addActionListener(this);
        b0.setBounds(380,550,140,40);
        add(b0);  
        
        if(data.length == 0){
            Label cont = new Label("No existen restricciones de tipo Distancia");       
            setLayout(null);
            cont.setBounds(294, 250, 400, 30);
            cont.setFont(new Font("Arial", 0, 16));
            add(cont);
        }
        else{
            String [] header = new String [4];
            header[0] = "Nombre";
            header[1] = "Mueble 1";
            header[2] = "Mueble 2";
            header[3] = "Distancia";
            
            setLayout( null );
            tabla = new JTable(data, header){
                @Override
                public boolean isCellEditable(int rowIndex, int vColIndex) {
                    return false;
                }
            };
            tabla.getTableHeader().setResizingAllowed(false);
            tabla.getTableHeader().setReorderingAllowed(false);
            
            panelScroll = new JScrollPane( tabla );   
            panelScroll.setBounds(223,250,450,250);
            add( panelScroll);
        } 
    }
    
    /** 
     * Lista las restricciones de tipo Sala-Objeto en la interfaz.
     */
    public void showRestrictionRoomType(String [][] data) {
        Label et = new Label("Lista de restricciones del tipo Sala-Mueble");       
        setLayout(null);
        et.setBounds(256, 150, 400, 30);
        et.setFont(new Font("Arial", 0, 20));
        add(et);

        b0 = new Button("Volver a Restricciones");
        b0.addActionListener(this);
        b0.setBounds(380,550,140,40);
        add(b0);  
        
        if(data.length == 0){
            Label cont = new Label("No existen restricciones de tipo Sala-Mueble");       
            setLayout(null);
            cont.setBounds(290, 250, 400, 30);
            cont.setFont(new Font("Arial", 0, 16));
            add(cont);
        }
        else{
            String [] header = new String [4];
            header[0] = "Nombre";
            header[1] = "Sala";
            header[2] = "Mueble";
            header[3] = "Permitir";
            
            setLayout( null );
            tabla = new JTable(data, header){
                @Override
                public boolean isCellEditable(int rowIndex, int vColIndex) {
                    return false;
                }
            };
            tabla.getTableHeader().setResizingAllowed(false);
            tabla.getTableHeader().setReorderingAllowed(false) ;
            
            panelScroll = new JScrollPane( tabla );   
            panelScroll.setBounds(223,250,450,250);
            add( panelScroll);
        } 
    }
    
    /** 
     * Muestra la interfaz para eliminar restricciones de tipo Distancia.
     */
    public void removeRestrictionDistance(String [][] data){
        Label et = new Label("Eliminar restricciones del tipo Distancia");       
        setLayout(null);
        et.setBounds(270, 150, 400, 30);
        et.setFont(new Font("Arial", 0, 20));
        add(et);
        
        b0 = new Button("Volver a Restricciones");
        b0.addActionListener(this);
         
        if(data.length == 0){
            Label cont = new Label("No existen restricciones de tipo Distancia");       
            setLayout(null);
            cont.setBounds(294, 250, 400, 30);
            cont.setFont(new Font("Arial", 0, 16));
            add(cont);
            
            b0.setBounds(380,550,140,40);
            add(b0);
        }
        else{
            Label st = new Label("De las siguientes restricciones, seleccione la que desea eliminar");       
            st.setBounds(220, 200, 400, 30);
            st.setFont(new Font("Arial", 0, 12));
            add(st);

            b11 = new Button("Eliminar Restriccion");
            b11.addActionListener(this);
            b11.setBounds(280,550,140,40);
            add(b11);  
            
            b0.setBounds(480,550,140,40);
            add(b0);
        
            String [] header = new String []{"Nombre", "Mueble 1", "Mueble 2", "Distancia"};
            
            setLayout( null );
            tabla = new JTable(data, header){
                @Override
                public boolean isCellEditable(int rowIndex, int vColIndex) {
                    return false;
                }
            };
            tabla.getTableHeader().setResizingAllowed(false);
            tabla.getTableHeader().setReorderingAllowed(false) ;
            tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            
            panelScroll = new JScrollPane( tabla );   
            panelScroll.setBounds(223,250,450,250);
            add( panelScroll);
            tabla.setSelectionBackground( Color.green );
        } 
    }
    
    /** 
     * Muestra la interfaz para eliminar restricciones de tipo Sala-Objeto.
     */
    public void removeRestrictionRoomType(String [][] data){
        Label et = new Label("Eliminar restricciones del tipo Sala-Mueble");       
        setLayout(null);
        et.setBounds(260, 150, 400, 30);
        et.setFont(new Font("Arial", 0, 20));
        add(et);
        
        b0 = new Button("Volver a Restricciones");
        b0.addActionListener(this);
        
        if(data.length == 0){
            Label cont = new Label("No existen restricciones de tipo Sala-Mueble");       
            setLayout(null);
            cont.setBounds(294, 250, 400, 30);
            cont.setFont(new Font("Arial", 0, 16));
            add(cont);
            
            b0.setBounds(380,550,140,40);
            add(b0); 
        }
        else{
            Label st = new Label("De las siguientes restricciones, seleccione la que desea eliminar");   
            setLayout( null );
            st.setBounds(220, 200, 400, 30);
            st.setFont(new Font("Arial", 0, 12));
            add(st);

            b12 = new Button("Eliminar Restriccion");
            b12.addActionListener(this);
            b12.setBounds(280,550,140,40);
            add(b12);  
            
            b0.setBounds(480,550,140,40);
            add(b0); 
        
            String [] header = new String []{"Nombre", "Sala", "Mueble", "Permitir"};
            
            tabla = new JTable(data, header){
                @Override
                public boolean isCellEditable(int rowIndex, int vColIndex) {
                    return false;
                }
            };
            tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            tabla.getTableHeader().setResizingAllowed(false);
            tabla.getTableHeader().setReorderingAllowed(false) ;
            
            panelScroll = new JScrollPane( tabla );   
            panelScroll.setBounds(223,250,450,250);
            add( panelScroll);
            tabla.setSelectionBackground( Color.green );
        } 
    }
    
    /** 
     * Muestra la interfaz para agregar restricciones de tipo Distancia.
     */
    public void addRestrictionDistance(String [][] data) {
        Label et = new Label("Añadir restriccion del tipo Distancia");       
        setLayout(null);
        et.setBounds(290, 150, 400, 30);
        et.setFont(new Font("Arial", 0, 20));
        add(et);

        b13 = new Button("Crear Restriccion");
        b13.addActionListener(this);
        b13.setBounds(480,700,140,40);        
        add(b13);  
        
        b14 = new Button("Seleccionar Mueble 1");
        b14.addActionListener(this);
        b14.setBounds(280,530,140,30);
        add(b14);
        
        b15 = new Button("Seleccionar Mueble 2");
        b15.addActionListener(this);
        b15.setBounds(480,530,140,30);
        add(b15);
        
        b0 = new Button("Volver a Restricciones");
        b0.setBounds(280,700,140,40);
        b0.addActionListener(this);
        
        add(b0);  
        
        Label h0 = new Label("Nombre");       
        h0.setBounds(225, 220, 50, 20);
        h0.setFont(new Font("Arial", 0, 12));
        add(h0);
        
        t0 = new JTextField(20);
        t0.setBounds(295, 220, 120, 20);
        //t0.setText(Integer.toString(lastIndex(data)));
        add(t0);
        
        Label h1 = new Label("Distancia");       
        h1.setBounds(455, 220, 55, 20);
        h1.setFont(new Font("Arial", 0, 12));
        add(h1);
        
        t1 = new JTextField(20);
        t1.setBounds(525, 220, 120, 20);
        add(t1);
        
        String [] header = new String[]{"Nombre","dimX","dimY","dimMaxX","dimMaxY","dimMinX","dimMinY","altura","apilable","Tipo"};
        tabla = new JTable(data, header){
                @Override
                public boolean isCellEditable(int rowIndex, int vColIndex) {
                    return false;
                }
        };
        tabla.getTableHeader().setResizingAllowed(false);
        tabla.getTableHeader().setReorderingAllowed(false) ;
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panelScroll = new JScrollPane( tabla );          
        panelScroll.setBounds(130,285,630,183);
        add( panelScroll);
        tabla.setSelectionBackground( Color.green );
        
        lastIndex(data);
        
        m1 = new String();
        m2 = new String();
        Label h2 = new Label("Mueble 1");       
        h2.setBounds(325, 580, 55, 20);
        h2.setFont(new Font("Arial", 0, 12));
        add(h2);
        
        t2 = new JTextField(20);
        t2.setBounds(290, 600, 120, 20);
        t2.setEditable(false);
        add(t2);
        
        Label h3 = new Label("Mueble 2");       
        h3.setBounds(525, 580, 55, 20);
        h3.setFont(new Font("Arial", 0, 12));
        add(h3);
        
        t3 = new JTextField(20);
        t3.setBounds(490, 600, 120, 20);
        t3.setEditable(false);
        add(t3);
        
        Label note = new Label("Nota: Esta restriccion solo se aplicara a las habitaciones existentes si son editadas");       
        note.setBounds(240, 650, 500, 20);
        note.setFont(new Font("Arial", 0, 11));
        add(note);
    }
    
    /** 
     * Muestra la interfaz para agregar restricciones de tipo Sala-Objeto.
     */
    public void addRestrictionRoomType(String [] roomList, String [][] data) {
        Label et = new Label("Añadir restriccion del tipo Sala-Mueble");       
        setLayout(null);
        et.setBounds(290, 150, 400, 30);
        et.setFont(new Font("Arial", 0, 20));
        add(et);

        b16 = new Button("Crear Restriccion");
        b16.addActionListener(this);
        b16.setBounds(280,700,140,40);
        add(b16);  
        
        b0 = new Button("Volver a Restricciones");
        b0.addActionListener(this);
        b0.setBounds(480,700,140,40);
        add(b0);  
        
        Label h0 = new Label("Nombre");       
        h0.setBounds(350, 220, 50, 20);
        h0.setFont(new Font("Arial", 0, 12));
        add(h0);
        t0 = new JTextField(20);
        t0.setBounds(420, 220, 120, 20);
        add(t0);
        
        Label h = new Label("Seleccione una sala de la lista de la izquierda y uno de los muebles de la tabla de la derecha");       
        h.setBounds(150, 270, 550, 20);
        h.setFont(new Font("Arial", 0, 12));
        add(h);
        
        rooms = new JList(roomList);
        rooms.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panelScroll4Rooms = new JScrollPane(rooms);   
        panelScroll4Rooms.setBounds(150,300,150,183); 
        add(panelScroll4Rooms);
        rooms.setSelectionBackground(Color.green);
        
        
        String [] header = new String[]{"Nombre","dimX","dimY","altura","apilable","Tipo"};
        tabla = new JTable(data, header){
                @Override
                public boolean isCellEditable(int rowIndex, int vColIndex) {
                    return false;
                }
        };
        tabla.getTableHeader().setResizingAllowed(false);
        tabla.getTableHeader().setReorderingAllowed(false) ;
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        panelScroll = new JScrollPane( tabla );   
        panelScroll.setBounds(350,300,450,183);
        add(panelScroll);
        tabla.setSelectionBackground(Color.green);
        
        lastIndex(data);
        
        Label h1 = new Label("Permitir");       
        h1.setBounds(200, 530, 55, 20);
        h1.setFont(new Font("Arial", 0, 12));
        add(h1);
        
        siRadio = new JRadioButton("Si, el mueble seleccionado debera estar en todas las habitaciones de este tipo");
        siRadio.setBounds(200, 550, 500, 20);
        siRadio.setBackground(Color.WHITE);
        noRadio = new JRadioButton("No, el mueble seleccionado no podra estar en este tipo de habitaciones");
        noRadio.setBounds(200, 570, 500, 20);
        noRadio.setBackground(Color.WHITE);
        radioGroup = new ButtonGroup();
        radioGroup.add(siRadio);
        radioGroup.add(noRadio);
        add(siRadio);
        add(noRadio);
        
        Label note = new Label("Nota: Esta restriccion solo se aplicara a las habitaciones existentes si son editadas");       
        note.setBounds(200, 600, 500, 20);
        note.setFont(new Font("Arial", 0, 11));
        add(note);
    }
}
