/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;


import Controllers.CjtElementsController;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 *
 * @author 11.2
 */
public class CjtElementsPanel extends JPanel implements ActionListener{
    private Button b0, b1, b2, b3, b4, b5, b6, b7, b8, b9, b10, b11, b12, b13, b14;
    private JTextField t[] = new JTextField[10];
    private JTextField t0, t1, t2;
    private String m1, m2;
    private CjtElementsController controller;
    private JTable elementsTable;
    private int idPos[];
    private int element1, element2, idTarget;
    private MainFrame window;
    
    /** 
     * Crea el panel de visualizacion del conjunto de elementos.
     * @param newController Enlace al controlador del conjunto de elementos.
     * @param newWindow Enlace al frame.
     */
    public CjtElementsPanel(CjtElementsController newController, MainFrame newWindow){
        controller = newController;
        window = newWindow;
    }
    
    /** 
     * Visualiza el primer menu de la configuracion del conjunto de elementos.
     */
    public void mainVisualization(){
        Label et = new Label("Escoja una de las siguientes opciones");       
        setLayout(null);
        et.setBounds(280, 150, 500, 30);
        et.setFont(new Font("Arial", 0, 20));
        add(et);

        b1 = new Button("Listar muebles");
        b1.setBounds(380,250,140,40);
        b1.addActionListener(this);
       
        b2 = new Button("Eliminar un mueble");
        b2.addActionListener(this);
        b2.setBounds(380,350,140,40);
       
        b3 = new Button("Agregar un mueble");
        b3.addActionListener(this);
        b3.setBounds(380,450,140,40);
       
        b4 = new Button("Modificar un mueble");
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
     * Crea y añade al panel la lista con los elementos del conjunto.
     */
    private void showTable(){
        Label et = new Label("Muebles existentes");       
        setLayout(null);
        et.setBounds(350, 150, 500, 30);
        et.setFont(new Font("Arial", 0, 20));
        add(et);
        
        elementsTable.getTableHeader().setReorderingAllowed(false) ;
        elementsTable.getTableHeader().setResizingAllowed(false); 
        elementsTable.setSelectionBackground( Color.GREEN );
        
        
        JScrollPane scrollTable = new JScrollPane(elementsTable);
        scrollTable.setBounds(150, 250, 600, 183);
        add(scrollTable);
    }
    
    /** 
     * Prepara la tabla para poder visualizar los elementos del conjunto.
     */
    public void showVisualization(String data[][], boolean consult){    
        
        if(!consult){
            Label cont = new Label("No existen muebles!");       
            setLayout(null);
            cont.setBounds(360, 250, 400, 30);
            cont.setFont(new Font("Arial", 0, 20));
            add(cont);
        }
        else{
            String header[];
            header = new String[]{"Nombre","dimX","dimY","dimMaxX","dimMaxY","dimMinX","dimMinY","altura","apilable","Tipo"};
            elementsTable = new JTable(data, header);
            elementsTable.setEnabled(false);
            showTable();
        }
        b0 = new Button("Volver");
        b0.addActionListener(this);       
        b0.setBounds(380,650,140,40);
        add(b0); 
    }
    
    /** 
     * Visualiza el menu para eliminar un elemento del conjunto.
     */
    public void deleteVisualization(String data[][]){
        if(data.length == 0){
            Label cont = new Label("No existen muebles!");       
            setLayout(null);
            cont.setBounds(360, 250, 400, 30);
            cont.setFont(new Font("Arial", 0, 20));
            add(cont);
            
            b0 = new Button("Volver");
            b0.addActionListener(this);       
            b0.setBounds(380,650,140,40);
            add(b0);
        }
        else{
            String header[];
            header = new String[]{"Nombre","dimX","dimY","dimMaxX","dimMaxY","dimMinX","dimMinY","altura","apilable","Tipo"};
            elementsTable = new JTable(data, header){
            @Override
            public boolean isCellEditable(int rowIndex, int vColIndex) {
                return false;
            }};  
            elementsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);        
            Label et = new Label("Seleccione el mueble que desea eliminar");       
            setLayout(null);
            et.setBounds(150, 450, 500, 30);        
            et.setFont(new Font("Arial", 0, 15));
            add(et);  
            
            Label et1 = new Label("Tenga en cuenta que ambien desapareceran los usen que tengan ese mueble.");       
            setLayout(null);
            et1.setBounds(150, 480, 530, 30);        
            et1.setFont(new Font("Arial", 0, 15));
            add(et1); 
            
            showTable();
            b0 = new Button("Volver");
            b0.addActionListener(this);       
            b0.setBounds(475,650,140,40);
            add(b0); 
            b6 = new Button("Eliminar");
            b6.addActionListener(this);       
            b6.setBounds(285,650,140,40);
            add(b6); 
        }
    }
    
    /** 
     * Visualiza el menu de para añadir un mueble o item al conjunto de elementos.
     */
    public void addVisualization(){
        Label et = new Label("Añadir mueble");       
        setLayout(null);
        et.setBounds(370, 150, 400, 30);
        et.setFont(new Font("Arial", 0, 20));
        add(et);        
        
        Label h0 = new Label("Nombre");       
        h0.setBounds(150, 220, 50, 20);
        h0.setFont(new Font("Arial", 0, 12));
        add(h0);        
        t[0] = new JTextField(20);
        t[0].setBounds(230, 220, 120, 20);
        add(t[0]);
        
        Label h1 = new Label("Dim X");       
        h1.setBounds(150, 250, 50, 20);
        h1.setFont(new Font("Arial", 0, 12));
        add(h1);        
        t[1] = new JTextField(20);
        t[1].setBounds(230, 250, 120, 20);
        add(t[1]);
        
        Label h2 = new Label("Dim Y");       
        h2.setBounds(150, 280, 50, 20);
        h2.setFont(new Font("Arial", 0, 12));
        add(h2);        
        t[2] = new JTextField(20);
        t[2].setBounds(230, 280, 120, 20);
        add(t[2]);
        
        Label h3 = new Label("DimMax X");       
        h3.setBounds(150, 310, 60, 20);
        h3.setFont(new Font("Arial", 0, 12));
        add(h3);        
        t[3] = new JTextField(20);
        t[3].setBounds(230, 310, 120, 20);
        add(t[3]);
        
        Label h4 = new Label("DimMax Y");       
        h4.setBounds(150, 340, 60, 20);
        h4.setFont(new Font("Arial", 0, 12));
        add(h4);        
        t[4] = new JTextField(20);
        t[4].setBounds(230, 340, 120, 20);
        add(t[4]);
       
        Label h5 = new Label("DimMinX");       
        h5.setBounds(505, 220, 50, 20);
        h5.setFont(new Font("Arial", 0, 12));
        add(h5);        
        t[5] = new JTextField(20);
        t[5].setBounds(585, 220, 120, 20);
        add(t[5]);
        
        Label h6 = new Label("DimMinY");       
        h6.setBounds(505, 250, 50, 20);
        h6.setFont(new Font("Arial", 0, 12));
        add(h6);        
        t[6] = new JTextField(20);
        t[6].setBounds(585, 250, 120, 20);
        add(t[6]);
        
        Label h7 = new Label("Altura");       
        h7.setBounds(505, 280, 50, 20);
        h7.setFont(new Font("Arial", 0, 12));
        add(h7);        
        t[7] = new JTextField(20);
        t[7].setBounds(585, 280, 120, 20);
        add(t[7]);
        
        Label h8 = new Label("Apilable");       
        h8.setBounds(505, 310, 60, 20);
        h8.setFont(new Font("Arial", 0, 12));
        add(h8);        
        t[8] = new JTextField(20);
        t[8].setBounds(585, 310, 120, 20);
        add(t[8]);
        
        Label h9 = new Label("Tipo");       
        h9.setBounds(505, 340, 60, 20);
        h9.setFont(new Font("Arial", 0, 12));
        add(h9);        
        t[9] = new JTextField(20);
        t[9].setBounds(585, 340, 120, 20);
        add(t[9]);
        
        Label et1 = new Label("Nota: La dimension del mueble tiene que estar dentro del rango definido por distancia DimMax y DimMin");       
        setLayout(null);
        et1.setBounds(120, 450, 680, 30);
        et1.setFont(new Font("Arial", 0, 14));
        add(et1);
        
        Label et2 = new Label("         El tipo de mueble puede ser 'Mueble' o 'Item', y los campos 'Altura' y 'Apilable' pueden ser Si/No.");       
        setLayout(null);
        et2.setBounds(130, 470, 680, 30);
        et2.setFont(new Font("Arial", 0, 14));
        add(et2);
        
        Label et3 = new Label("Ejemplo 1: Mesa / 40 / 70 / 60 / 90 / 30 / 30 / No / Si / Mueble ");       
        setLayout(null);
        et3.setBounds(120, 520, 680, 30);
        et3.setFont(new Font("Arial", 0, 14));
        add(et3);
        
        Label et4 = new Label("Ejemplo 2: Tele / 20 / 50 / 30 / 70 / 20 / 40 / No / No / Item ");       
        setLayout(null);
        et4.setBounds(120, 550, 680, 30);
        et4.setFont(new Font("Arial", 0, 14));
        add(et4);
        
        b0 = new Button("Volver");
        b0.addActionListener(this);       
        b0.setBounds(475,650,140,40);
        add(b0); 
        b7 = new Button("Agregar");
        b7.addActionListener(this);       
        b7.setBounds(285,650,140,40);
        add(b7);
    }
    
    /** 
     * Visualiza el menu para añadir un pack al conjunto de elementos.
     */
    public void addPackVisualization(String data[][]){        
        if(data.length == 0){
            Label cont = new Label("No existen muebles!");       
            setLayout(null);
            cont.setBounds(360, 250, 400, 30);
            cont.setFont(new Font("Arial", 0, 20));
            add(cont);
            
            b0 = new Button("Volver");
            b0.addActionListener(this);       
            b0.setBounds(380,650,140,40);
            add(b0);
        }
        else{
            String header[];
            header = new String[]{"Nombre","dimX","dimY","dimMaxX","dimMaxY","dimMinX","dimMinY","altura","apilable","Tipo"};
            elementsTable = new JTable(data, header){
            @Override
            public boolean isCellEditable(int rowIndex, int vColIndex) {
                return false;
            }};  
            elementsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
            
            Label h0 = new Label("Nombre del Pack");       
            h0.setBounds(140, 470, 100, 20);
            h0.setFont(new Font("Arial", 0, 12));
            add(h0);

            t0 = new JTextField(20);
            t0.setBounds(300, 470, 120, 20);
            add(t0);
            
            t1 = new JTextField(20);
            t1.setBounds(290, 600, 120, 20);
            t1.setEditable(false);
            add(t1);
            
            t2 = new JTextField(20);
            t2.setBounds(490, 600, 120, 20);
            t2.setEditable(false);
            add(t2);
            
            b10 = new Button("Crear pack");
            b10.addActionListener(this);
            b10.setBounds(285,700,140,40); 
            add(b10);  

            b11 = new Button("Seleccionar Mueble");
            b11.addActionListener(this);
            b11.setBounds(280,550,140,30);
            add(b11);

            b12 = new Button("Seleccionar Item");
            b12.addActionListener(this);
            b12.setBounds(480,550,140,30);
            add(b12);
            
            m1 = new String();
            m2 = new String();
            
            showTable();
            
            b0 = new Button("Volver");
            b0.addActionListener(this);       
            b0.setBounds(480,700,140,40);       
            add(b0); 
            
        }        
    }
    
    /** 
     * Visualiza el menu para editar un mueble o item del conjunto de elementos.
     */
    public void editVisualization(String data[][]){
        if(data.length == 0){
            Label cont = new Label("No existen muebles!");       
            setLayout(null);
            cont.setBounds(360, 250, 400, 30);
            cont.setFont(new Font("Arial", 0, 20));
            add(cont);
            
            b0 = new Button("Volver");
            b0.addActionListener(this);       
            b0.setBounds(380,650,140,40);
            add(b0);
        }
        else{
            String header[];
            header = new String[]{"Nombre","dimX","dimY","dimMaxX","dimMaxY","dimMinX","dimMinY","altura","apilable","Tipo"};
            elementsTable = new JTable(data, header){
            @Override
            public boolean isCellEditable(int rowIndex, int vColIndex) {
                return false;
            }};  
            elementsTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
            showTable();
            
            Label h0 = new Label("Nombre");       
            h0.setBounds(150, 500, 50, 20);
            h0.setFont(new Font("Arial", 0, 12));
            add(h0);        
            t[0] = new JTextField(20);
            t[0].setBounds(230, 500, 120, 20);
            add(t[0]);

            Label h1 = new Label("Dim X");       
            h1.setBounds(150, 530, 50, 20);
            h1.setFont(new Font("Arial", 0, 12));
            add(h1);        
            t[1] = new JTextField(20);
            t[1].setBounds(230, 530, 120, 20);
            add(t[1]);

            Label h2 = new Label("Dim Y");       
            h2.setBounds(150, 560, 50, 20);
            h2.setFont(new Font("Arial", 0, 12));
            add(h2);        
            t[2] = new JTextField(20);
            t[2].setBounds(230, 560, 120, 20);
            add(t[2]);

            Label h3 = new Label("DimMax X");       
            h3.setBounds(150, 590, 60, 20);
            h3.setFont(new Font("Arial", 0, 12));
            add(h3);        
            t[3] = new JTextField(20);
            t[3].setBounds(230, 590, 120, 20);
            add(t[3]);

            Label h4 = new Label("DimMax Y");       
            h4.setBounds(150, 620, 60, 20);
            h4.setFont(new Font("Arial", 0, 12));
            add(h4);        
            t[4] = new JTextField(20);
            t[4].setBounds(230, 620, 120, 20);
            add(t[4]);

            Label h5 = new Label("DimMinX");       
            h5.setBounds(505, 500, 50, 20);
            h5.setFont(new Font("Arial", 0, 12));
            add(h5);        
            t[5] = new JTextField(20);
            t[5].setBounds(585, 500, 120, 20);
            add(t[5]);

            Label h6 = new Label("DimMinY");       
            h6.setBounds(505, 530, 50, 20);
            h6.setFont(new Font("Arial", 0, 12));
            add(h6);        
            t[6] = new JTextField(20);
            t[6].setBounds(585, 530, 120, 20);
            add(t[6]);

            Label h7 = new Label("Altura");       
            h7.setBounds(505, 560, 50, 20);
            h7.setFont(new Font("Arial", 0, 12));
            add(h7);        
            t[7] = new JTextField(20);
            t[7].setBounds(585, 560, 120, 20);
            add(t[7]);

            Label h8 = new Label("Apilable");       
            h8.setBounds(505, 590, 60, 20);
            h8.setFont(new Font("Arial", 0, 12));
            add(h8);        
            t[8] = new JTextField(20);
            t[8].setBounds(585, 590, 120, 20);
            add(t[8]);

            Label h9 = new Label("Tipo");       
            h9.setBounds(505, 620, 60, 20);
            h9.setFont(new Font("Arial", 0, 12));
            add(h9);        
            t[9] = new JTextField(20);
            t[9].setBounds(585, 620, 120, 20);
            add(t[9]);
            
            
            b0 = new Button("Volver");
            b0.addActionListener(this);       
            b0.setBounds(550,700,140,40);
            add(b0); 
            b13 = new Button("Editar");
            b13.addActionListener(this);       
            b13.setBounds(380,700,140,40);
            add(b13); 
            b14 = new Button("Seleccionar");
            b14.addActionListener(this);       
            b14.setBounds(210,700,140,40);
            add(b14); 
        }
    }
    
    /** 
     * Visualiza el primer menu para añadir un nuevo elemento al conjunto.
     */
    public void addMenuVisualization(){  
        Label et = new Label("Escoja la opcion de agregar que desee");       
        setLayout(null);
        et.setBounds(282, 150, 400, 30);
        et.setFont(new Font("Arial", 0, 20));
        add(et);

        b8 = new Button("Agregar un mueble");
        b8.setBounds(380,250,140,40);
        b8.addActionListener(this);

        b9 = new Button("Formar Pack");
        b9.addActionListener(this);
        b9.setBounds(380,350,140,40);

        b0 = new Button("Volver");
        b0.addActionListener(this);
        b0.setBounds(380,450,140,40);

        add(b8);
        add(b9);
        add(b0);   
    }
    
    /** 
     * Introduce una lista referenciadora a los id's de los elementos del conjunto.
     */
    public void setIdPos(int idPos[]){
        this.idPos = idPos;
    }
    
    /** 
     * Conjunto de acciones para cada boton del panel.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==b0) {            
            controller.goBack();
        }
        else if (e.getSource()==b1) {            
            controller.selector("1");
        }
        else if (e.getSource()==b2) {
            controller.selector("2");
        }
        else if (e.getSource()==b3) {
            controller.selector("3");
        }
        else if (e.getSource()==b4){            
            controller.selector("4");
        }
        else if (e.getSource()==b5) {          
            controller.selector("5");
        }
        else if (e.getSource()==b6){            
            int indice = elementsTable.getSelectedRow();
            if(indice == -1 || indice >= idPos.length){               
              JOptionPane.showMessageDialog(null,"Seleccione un mueble!","Error",JOptionPane.ERROR_MESSAGE);           
            }
            else{
                if(this.window.validationTwo()){                    
                    this.controller.delete(this.idPos[indice]);
                    JOptionPane.showMessageDialog(null,"Mueble eliminado!","Aviso",JOptionPane.INFORMATION_MESSAGE);
                    this.controller.goBack();
                }
            } 
        }
        else if (e.getSource()==b7) {
            String list[] = new String[10];
            boolean err = false;
            for(int i=0; i < 10 && !err; ++i){ 
                if(i > 0 && i < 7){
                    err = isNotNumeric(t[i].getText());                    
                }
                list[i] = t[i].getText(); 
            }
            if(t[9].getText().equals("Pack")){
                JOptionPane.showMessageDialog(null,"Introduzca Tipo valido!","Error",JOptionPane.ERROR_MESSAGE);
            }
            else{
                if(!err){
                    err = controller.add(list);                
                }
                if(err){
                    JOptionPane.showMessageDialog(null,"Introduzca paremetros validos!","Error",JOptionPane.ERROR_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(null,"Mueble creado!","Aviso",JOptionPane.INFORMATION_MESSAGE);
                    controller.goBack();
                }
            }
        }
        else if (e.getSource()==b8){            
            controller.selecAddType("1");
        }
        else if (e.getSource()==b9) {
            controller.selecAddType("2");
        }
        else if (e.getSource()==b10){
            if(t0.getText().isEmpty()){
                JOptionPane.showMessageDialog(null,"Asigne un nombre valido!","Error",JOptionPane.ERROR_MESSAGE);
            }
            else if(m1.isEmpty() || m2.isEmpty()){
                JOptionPane.showMessageDialog(null,"Ha de seleccionar un mueble y un item para crear el pack!","Error",JOptionPane.ERROR_MESSAGE);
            }            
            else{       
                JOptionPane.showMessageDialog(null,"Pack creado!","Aviso",JOptionPane.INFORMATION_MESSAGE);
                controller.addPack(t0.getText(), element1, element2);
                controller.goBack();
            }
        }
        else if (e.getSource()==b11){            
            int indice = elementsTable.getSelectedRow();
            if(indice == -1 || indice >= idPos.length){               
              JOptionPane.showMessageDialog(null,"Seleccione un mueble!","Error",JOptionPane.ERROR_MESSAGE);
            }
            else if(!elementsTable.getValueAt(indice, 9).equals("Mueble")){
                JOptionPane.showMessageDialog(null,"Seleccione un mueble!","Error",JOptionPane.ERROR_MESSAGE);
            }
            else if(!elementsTable.getValueAt(indice, 8).equals("Si")){
                JOptionPane.showMessageDialog(null,"Seleccione un mueble valido!","Error",JOptionPane.ERROR_MESSAGE);
            }
            else{              
                m1 = (String) elementsTable.getValueAt(indice, 0);
                t1.setText(m1);
                element1 = idPos[indice];
            }
        }
        else if (e.getSource()==b12){            
            int indice = elementsTable.getSelectedRow();            
            if(indice == -1 || indice >= idPos.length){               
              JOptionPane.showMessageDialog(null,"Seleccione un item!","Error",JOptionPane.ERROR_MESSAGE);
            }    
            else if(!elementsTable.getValueAt(indice, 9).equals("Item")){
                JOptionPane.showMessageDialog(null,"Seleccione un item!","Error",JOptionPane.ERROR_MESSAGE);
            }
            else{              
                m2 = (String) elementsTable.getValueAt(indice, 0);
                t2.setText(m2);
                element2 = idPos[indice];
            }
        }
        else if (e.getSource()==b13) {
            String list[] = new String[10];
            boolean err = false;
            for(int i=0; i < 10 && !err; ++i){ 
                if(i > 0 && i < 7){
                    err = isNotNumeric(t[i].getText());                    
                }
                list[i] = t[i].getText(); 
            }
            if(t[9].getText().equals("Pack")){
                JOptionPane.showMessageDialog(null,"Introduzca Tipo valido!","Error",JOptionPane.ERROR_MESSAGE);
            }
            else{
                if(!err){
                    err = controller.update(idTarget, list);
                }
                if(err){
                    JOptionPane.showMessageDialog(null,"Introduzca paremetros validos!","Error",JOptionPane.ERROR_MESSAGE);
                }
                else {
                    JOptionPane.showMessageDialog(null,"Mueble actualizado!","Aviso",JOptionPane.INFORMATION_MESSAGE);
                    controller.goBack();
                }
            }
        }
        else if (e.getSource()==b14) {
            int indice = elementsTable.getSelectedRow();
            if(indice == -1 || indice >= idPos.length){               
              JOptionPane.showMessageDialog(null,"Seleccione un mueble!","Error",JOptionPane.ERROR_MESSAGE);
            }
            else if(elementsTable.getValueAt(indice, 9).equals("Pack")){
                JOptionPane.showMessageDialog(null,"Los packs no se editan!","Error",JOptionPane.ERROR_MESSAGE);
            }            
            else{
                for(int i=0; i<10; ++i){
                    t[i].setText((String) elementsTable.getValueAt(indice, i));
                }
                idTarget = idPos[indice];
            }
        }
    }    
    
    /** 
     * Consulta si un parametro es numerico
     * @param num Representa el parametro que se desea comprobar. 
     * @return 'true' si no es numerico, 'false' en caso contrario.
     */
    private static boolean isNotNumeric(String num){
	try {           
            Integer.parseInt(num);                
            return false;
	} catch (NumberFormatException nfe){
            return true;
	}
    }    
}
