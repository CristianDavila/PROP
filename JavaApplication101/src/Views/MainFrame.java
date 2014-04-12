/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Views;


import Controllers.FrontController;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;


/**
 *
 * @author 11.2
 */
public class MainFrame extends JFrame {
    
        private FrontPanel frontPanel;
        private ConfigurationPanel configPanel;
        private CjtMapPanel cjtMapPanel;
        private DesignPanel designPanel;       
        private CjtRestrictionsPanel cjtRestrictionsPanel;
        private CjtRoomTypePanel cjtRoomTypePanel;
        private FrontController controller;
        private CjtElementsPanel cjtElementsPanel;
        private MapPanel mapPanel;
        private MapEditPanel mapEditPanel;            
    
        private int x, y, xMinBot, yMinBot,  xMaxBot, yMaxBot;
        private List<String[]> dat;
        private String nameRoom;
        
        private int[][] doors, windows;
        private SpecialButtonImpl[] buttons;
        
        private SpecialButtonImplFix[] buttonsDoor;
        private SpecialButtonImplFix[] buttonsWindow; 
 
	/** 
         * Crea un main frame. 
         * @param newController Enlace al controlador frontal.
         */
    	public MainFrame(FrontController newController){           
            controller = newController;
            this.setResizable(false);            
            
            frontPanel = new FrontPanel(controller);
            frontPanel.setBackground(Color.WHITE);            
            add(frontPanel);
            frontPanel.firstVisual();            
            
            setSize(900,900);   //size                
            setVisible(true);
            setLocationRelativeTo(null);                      
            addWindowListener ( new WindowAdapter ( ) {@Override
            public void windowClosing (WindowEvent e) {dispose();}});               
 	}  
        
        //Funciones FrontPanel
        
        /** 
         * Vista del menu principal del programa. 
         */
        public void frontView(){
            frontPanel = new FrontPanel(controller);
            frontPanel.setBackground(Color.WHITE);            
            add(frontPanel);
            frontPanel.firstVisual();            
        }   
        
        /** 
         * Elimina la vista del menu principal del programa. 
         */
        public void removeFrontView(){
            frontPanel.setVisible(false);
        }
        //<---END--->\\
        
        //Funciones DesignPanel
        
        /** 
         * Vista principal de diseno. 
         */
        public void designView(int maxId, int size,List<Integer> idFurniture,String list[],String data[][],String dataElements[][]){
            designPanel = new DesignPanel(controller.getDesignController());
            designPanel.setBackground(Color.WHITE);   
            designPanel.setVisible(true);
            add( designPanel );
            designPanel.visualization(maxId,size,idFurniture,list,data,dataElements);           
        }
        
        /** 
         * Vista de la edicion de cantidades de los muebles a colocar en la habitacion. 
         */
        public void editAmountView(String data[][]){
            designPanel = new DesignPanel(controller.getDesignController());
            designPanel.setBackground(Color.WHITE);   
            designPanel.setVisible(true);
            add( designPanel );
            designPanel.editAmount(data);           
        }        
        
        /** 
         * Mostrar los mubles relacionados de la sala seleccionada en el apartado de diseno. 
         */
        public void showListRelated(List<Integer> idFurniture, String[][] data){
            designPanel.showListRelated(idFurniture, data);
        }
        
        /** 
         * Vista del mensaje de no-solution. 
         */
        public void notSolutionDesign(){
            designPanel = new DesignPanel(controller.getDesignController());
            designPanel.setBackground(Color.WHITE);   
            designPanel.setVisible(true);
            add( designPanel );
            designPanel.notSolution();
        }
        
        public int skipRestrictionDelete(String name){
            return designPanel.skipRestrictionDelete(name);
        }
        
        public int skipRestrictionAdd(String name){
            return designPanel.skipRestrictionAdd(name);
        }
        
        /** 
         * Elimina la vista principal de diseno. 
         */
        public void removeDesignView(){
            designPanel.setVisible(false);
        }    
        
        /** 
         * Vista de la habitacion disenada. 
         */
        public void showDesignedRoom(int x, int y, List<String[]> dat, String nameRoom, int[][] door, int[][] window){
            Button b1, b2;
            this.dat = dat;
            this.nameRoom = nameRoom;
            this.x = x;
            this.y = y;
            this.doors = door;
            this.windows = window;
            int xMin, yMin, xMax, yMax;                
            xMin = (900-x)/2;
            yMin = 50 + (900-y)/4;
            xMax = x + xMin;
            yMax = y + yMin;
            
            b1 = new Button("Volver");
            if(y > 600){
                b1.setBounds(280,820,140,40);
            }
            else{
                b1.setBounds(280,750,140,40);
            }
            b1.addActionListener(
			new ActionListener()
			{
                                @Override
				public void actionPerformed( ActionEvent evento )
				{   
                                    controller.getDesignController().goBackTwo();
				}
			} 
 		);
            b2 = new Button("Editar");
            if(y > 600){
                b2.setBounds(480,820,140,40);
            }
            else{
                b2.setBounds(480,750,140,40);
            }
            b2.addActionListener(
			new ActionListener()
			{
                                @Override
				public void actionPerformed( ActionEvent evento )
				{     
                                    editDesignedMap();                                    
				}
			} 
 		);            
            mapPanel = new MapPanel();
            mapPanel.setLayout(null);
            mapPanel.setBackground(Color.WHITE);   
            mapPanel.setVisible(true);
            mapPanel.add(b1);
            mapPanel.add(b2);
            
            Label et = new Label("Tipo de habitacion: "+nameRoom);
            et.setBounds(100, 30, 500, 30);
            et.setFont(new Font("Arial", 0, 14));            
            mapPanel.add(et);
            
            et = new Label("Dimensiones: "+x+"x"+y);
            et.setBounds(100, 60, 500, 30);
            et.setFont(new Font("Arial", 0, 14));            
            mapPanel.add(et);
            
            add( mapPanel );            
            mapPanel.dibujar(x, y, xMin,yMin,xMax,yMax, dat, door, window);  
        }
        
        /** 
         * Vista de la seleccion de puerta/ventana. 
         */
        public void selectDoorWindow(String nameRoom, int x, int y, int numDoor, int numWindow){
            Button b1, b2;
            int xMin, yMin, xMax, yMax;     
            buttonsDoor = new SpecialButtonImplFix[numDoor];
            buttonsWindow = new SpecialButtonImplFix[numWindow];
            xMinBot = xMin = (900-x)/2;
            yMinBot = yMin = 50 + (900-y)/4;
            xMaxBot = xMax = x + xMin;
            yMaxBot = yMax = y + yMin; 
         
            mapEditPanel = new MapEditPanel();
            mapEditPanel.setLayout(null);
            mapEditPanel.setBackground(Color.WHITE);   
            mapEditPanel.setVisible(true);
            int posX, posY;
            posX = xMin;
            posY = yMin;
            String flag = "E";
            for(int i=0; i<numDoor; ++i){
                buttonsDoor[i] = new SpecialButtonImplFix("P", xMin, xMax, yMin, yMax); //Name
                buttonsDoor[i].setBounds(posX,posY,50,50);
                buttonsDoor[i].setBot();
                if(flag.equals("E")){
                    posX += 50;
                    if((xMax-posX)<50){
                        posX = xMax-50;
                        posY = yMin + 50;
                        flag = "S";
                    }
                }
                else if(flag.equals("S")){
                    posY += 50;
                    if((yMax-posY)<50){
                        posX = xMax-100;
                        posY = yMax - 50;
                        flag = "O";
                    }
                }                
                mapEditPanel.add(buttonsDoor[i]);               
            } 
            for(int i=0; i<numWindow; ++i){
                buttonsWindow[i] = new SpecialButtonImplFix("V", xMin, xMax, yMin, yMax); //Name
                buttonsWindow[i].setBounds(posX,posY,50,20);                
                buttonsWindow[i].setBot();
                if(flag.equals("E")){                    
                    posX += 50;
                    if((xMax-posX)<50){
                        posX = xMax-20;
                        posY = yMin + 20;
                        flag = "S";                        
                    }
                }
                else if(flag.equals("S")){
                    buttonsWindow[i].setSize(20, 50);
                    posY += 50;
                    if((yMax-posY)<50){
                        posX = xMax-100;
                        posY = yMax - 50;
                        flag = "O";
                    }
                } 
                mapEditPanel.add(buttonsWindow[i]);               
            } 
            
            Label et = new Label("Tipo de habitacion: "+nameRoom);
            et.setBounds(100, 30, 300, 30);
            et.setFont(new Font("Arial", 0, 14));            
            mapEditPanel.add(et);
            
            et = new Label("Dimensiones: "+x+"x"+y);
            et.setBounds(100, 60, 300, 30);
            et.setFont(new Font("Arial", 0, 14));            
            mapEditPanel.add(et);
            
            add( mapEditPanel ); 
            
            et = new Label("Nota: Para rotar los muebles, mantenga presionada la letra R y presione sobre");
            if(y > 600){
                et.setBounds(400,30, 500, 30);
            }
            else{
                et.setBounds(270,790, 500, 30);
            }            
            et.setFont(new Font("Arial", 0, 11));            
            mapEditPanel.add(et);
            
            add( mapEditPanel );
            
            et = new Label("el mueble que desea rotar.");
            if(y > 600){
                et.setBounds(430,50, 500, 30);
            }
            else{
                et.setBounds(300,810, 500, 30);
            } 
            
            et.setFont(new Font("Arial", 0, 11));            
            mapEditPanel.add(et);
            
            add( mapEditPanel );
            
            b1 = new Button("Cancelar diseño");
            if(y > 600){
                b1.setBounds(280,820,140,40);
            }
            else{
                b1.setBounds(280,750,140,40);
            }
            b1.addActionListener(
			new ActionListener()
			{
                                @Override
				public void actionPerformed( ActionEvent evento )
				{                                       
                                    controller.getDesignController().goBackThree();
				}
			} 
 		);
            b2 = new Button("Diseñar");
            if(y > 600){
                b2.setBounds(480,820,140,40);
            }
            else{
                b2.setBounds(480,750,140,40);
            }
            b2.addActionListener(
			new ActionListener()
			{
                                @Override
				public void actionPerformed( ActionEvent evento )
				{                                    
                                    int numDoor = buttonsDoor.length;
                                    int numWindow= buttonsWindow.length;
                                    int x, y, width, height;
                                    int[][] data = new int[numDoor][2];
                                    int[][] data2 = new int[numWindow][2];
                                    for(int i=0; i<numDoor; ++i){                                        
                                        x=buttonsDoor[i].getX()-xMinBot;
                                        y=buttonsDoor[i].getY()-yMinBot;                                        
                                        data[i][0] = x;
                                        data[i][1] = y;
                                        if(x>50 && x<(xMaxBot-50) && y>50 && (yMaxBot-50)> y){
                                            x=0;
                                            y=100;                                                    
                                        }
                                    } 
                                    
                                    for(int i=0; i<numWindow; ++i){                                        
                                        x=buttonsWindow[i].getX()-xMinBot;
                                        y=buttonsWindow[i].getY()-yMinBot;
                                        width=buttonsWindow[i].getWidth();
                                        height=buttonsWindow[i].getHeight();                                         
                                        if((x+20) == (xMaxBot-xMinBot)){                                            
                                            data2[i][0] = (x-30);
                                            data2[i][1] = (y); 
                                        }
                                        else if((y+20) == (yMaxBot-yMinBot)){                                          
                                            data2[i][0] = (x);
                                            data2[i][1] = (y-30); 
                                        }
                                        else{
                                            data2[i][0] = (x);
                                            data2[i][1] = (y);
                                        }
                                        if(x>50 && x<(xMaxBot-50) && y>50 && (yMaxBot-50)> y){
                                            x=150;
                                            y=0;
                                        }                                        
                                    }                                    
                                    controller.getDesignController().setDoorsWindows(data, data2);
				}
			} 
 		);    
            
            mapEditPanel.add(b1);
            mapEditPanel.add(b2);
            mapEditPanel.dibujar(x, y, xMin,yMin,xMax,yMax, dat, null, null, false);
        }        
        
        /** 
         * Vista de la edicion de habitaciones disenadas. 
         */
        public void editDesignedMap(){
            removeMapPanel(); 
            Button b1, b2;
            int xMin, yMin, xMax, yMax;     
            buttons = new SpecialButtonImpl[dat.size()];
            xMin = (900-x)/2;
            yMin = 50 + (900-y)/4;
            xMax = x + xMin;
            yMax = y + yMin; 
                        
            mapEditPanel = new MapEditPanel();
            mapEditPanel.setLayout(null);
            mapEditPanel.setBackground(Color.WHITE);   
            mapEditPanel.setVisible(true);
            for(int i=0; i<dat.size(); ++i){
                buttons[i] = new SpecialButtonImpl(dat.get(i)[0], xMin, xMax, yMin, yMax); //Name
                buttons[i].setBounds(xMin+Integer.parseInt(dat.get(i)[3]),yMin+Integer.parseInt(dat.get(i)[4]),Integer.parseInt(dat.get(i)[1]),Integer.parseInt(dat.get(i)[2]));
                mapEditPanel.add(buttons[i]);               
            } 
            
            Label et = new Label("Tipo de habitacion: "+nameRoom);
            et.setBounds(100, 30, 300, 30);
            et.setFont(new Font("Arial", 0, 14));            
            mapEditPanel.add(et);
            
            et = new Label("Dimensiones: "+x+"x"+y);
            et.setBounds(100, 60, 300, 30);
            et.setFont(new Font("Arial", 0, 14));            
            mapEditPanel.add(et);
            
            add( mapEditPanel ); 
            
            et = new Label("Nota: Para rotar los muebles, mantenga presionada la letra R y presione sobre");
            if(y > 600){
                et.setBounds(400,30, 500, 30);
            }
            else{
                et.setBounds(270,790, 500, 30);
            }            
            et.setFont(new Font("Arial", 0, 11));            
            mapEditPanel.add(et);
            
            add( mapEditPanel );
            
            et = new Label("el mueble que desea rotar.");
            if(y > 600){
                et.setBounds(430,50, 500, 30);
            }
            else{
                et.setBounds(300,810, 500, 30);
            } 
            
            et.setFont(new Font("Arial", 0, 11));            
            mapEditPanel.add(et);
            
            add( mapEditPanel );
            
            b1 = new Button("Volver");
            if(y > 600){
                b1.setBounds(280,820,140,40);
            }
            else{
                b1.setBounds(280,750,140,40);
            }
            b1.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed( ActionEvent evento )
                    {                                    
                        controller.getDesignController().goBackThree();
                    }
                } 
            );
            b2 = new Button("Validar");
            if(y > 600){
                b2.setBounds(480,820,140,40);
            }
            else{
                b2.setBounds(480,750,140,40);
            }
            b2.addActionListener(
                new ActionListener()
                {
                    @Override
                    public void actionPerformed( ActionEvent evento )
                    {           
                        int size = buttons.length;
                        int position[][] = new int[size][4];
                        int ids[] = new int[size];
                        String names[] = new String[10];
                        for(int i=0; i<size; ++i){
                            position[i][0] = buttons[i].getX();
                            position[i][1] = buttons[i].getY();
                            position[i][2] = buttons[i].getWidth();
                            position[i][3] = buttons[i].getHeight(); 
                            ids[i] = Integer.parseInt(dat.get(i)[5]);
                            names[i] = buttons[i].getName();
                        }
                       boolean valid = controller.getDesignController().validation(position, names, ids);
                        if(!valid){
                            designPanel.notSolution();
                        }
                        else{
                            designPanel.validSolution();
                        }
                    }
                } 
            );    
            
            mapEditPanel.add(b1);
            mapEditPanel.add(b2);
            mapEditPanel.dibujar(x, y, xMin,yMin,xMax,yMax, dat, doors, windows, true);
        }
        //<---END--->\\
        
        //Funciones CjtMapPanel (Carga de habitaciones)
        
        /** 
         * Vista de la cuadricula de la habitacion. 
         */
        public void cjtMapView(String list[]){     
            cjtMapPanel = new CjtMapPanel(controller.getMapController());
            cjtMapPanel.setBackground(Color.WHITE);   
            cjtMapPanel.setVisible(true);
            add( cjtMapPanel );
            cjtMapPanel.visualitzation(list);            
        }
        
        /** 
         * Vista de edicion de la cuadricula de la habitacion. 
         */
        public void editMap(){            
            removeMapPanel(); 
            Button b1, b2;
            int xMin, yMin, xMax, yMax;     
            SpecialButtonImpl[] buttons = new SpecialButtonImpl[dat.size()];
            xMin = (900-x)/2;
            yMin = 50 + (900-y)/4;
            xMax = x + xMin;
            yMax = y + yMin; 
                        
            mapEditPanel = new MapEditPanel();
            mapEditPanel.setLayout(null);
            mapEditPanel.setBackground(Color.WHITE);   
            mapEditPanel.setVisible(true);
            for(int i=0; i<dat.size(); ++i){
                buttons[i] = new SpecialButtonImpl(dat.get(i)[0], xMin, xMax, yMin, yMax); //Name
                buttons[i].setBounds(xMin+Integer.parseInt(dat.get(i)[3]),yMin+Integer.parseInt(dat.get(i)[4]),Integer.parseInt(dat.get(i)[1]),Integer.parseInt(dat.get(i)[2]));
                mapEditPanel.add(buttons[i]);               
            } 
            
            Label et = new Label("Tipo de habitacion: "+nameRoom);
            et.setBounds(100, 30, 300, 30);
            et.setFont(new Font("Arial", 0, 14));            
            mapEditPanel.add(et);
            
            et = new Label("Dimensiones: "+x+"x"+y);
            et.setBounds(100, 60, 300, 30);
            et.setFont(new Font("Arial", 0, 14));            
            mapEditPanel.add(et);
            
            add( mapEditPanel ); 
            
            et = new Label("Nota: Para rotar los muebles, mantenga presionada la letra R y presione sobre");
            if(y > 600){
                et.setBounds(400,30, 500, 30);
            }
            else{
                et.setBounds(270,790, 500, 30);
            }            
            et.setFont(new Font("Arial", 0, 11));            
            mapEditPanel.add(et);
            
            add( mapEditPanel );
            
            et = new Label("el mueble que desea rotar.");
            if(y > 600){
                et.setBounds(430,50, 500, 30);
            }
            else{
                et.setBounds(300,810, 500, 30);
            } 
            
            et.setFont(new Font("Arial", 0, 11));            
            mapEditPanel.add(et);
            
            add( mapEditPanel );
            
            b1 = new Button("Volver");
            if(y > 600){
                b1.setBounds(280,820,140,40);
            }
            else{
                b1.setBounds(280,750,140,40);
            }
            b1.addActionListener(
			new ActionListener()
			{
                                @Override
				public void actionPerformed( ActionEvent evento )
				{                                    
                                    controller.getMapController().goBackEdit();
				}
			} 
 		);
            b2 = new Button("Validar");
            if(y > 600){
                b2.setBounds(480,820,140,40);
            }
            else{
                b2.setBounds(480,750,140,40);
            }
            b2.addActionListener(
			new ActionListener()
			{
                                @Override
				public void actionPerformed( ActionEvent evento )
				{                                    
                                    System.out.println("Ahora se validaria y tal");   
				}
			} 
 		);    
            
            mapEditPanel.add(b1);
            mapEditPanel.add(b2);
            mapEditPanel.dibujar(x, y, xMin,yMin,xMax,yMax, dat, doors, windows, true);            
        }    
        
        /** 
         * Vista de la habitacion. 
         */
        public void showMap2D(int x, int y, List<String[]> dat, String nameRoom, int[][] door, int[][] window){
            this.doors = door;
            this.windows = window;
            Button b1, b2;
            this.dat = dat;
            this.nameRoom = nameRoom;
            this.x = x;
            this.y = y;
            int xMin, yMin, xMax, yMax;                
            xMin = (900-x)/2;
            yMin = 50 + (900-y)/4;
            xMax = x + xMin;
            yMax = y + yMin;
            
            b1 = new Button("Volver");
            if(y > 600){
                b1.setBounds(380,820,140,40);
            }
            else{
                b1.setBounds(380,750,140,40);
            }
            b1.addActionListener(
			new ActionListener()
			{
                                @Override
				public void actionPerformed( ActionEvent evento )
				{                                    
                                    controller.getMapController().goBackTwo();
				}
			} 
 		);
                       
            mapPanel = new MapPanel();
            mapPanel.setLayout(null);
            mapPanel.setBackground(Color.WHITE);   
            mapPanel.setVisible(true);
            mapPanel.add(b1);
           
            
            Label et = new Label("Tipo de habitacion: "+nameRoom);
            et.setBounds(100, 30, 500, 30);
            et.setFont(new Font("Arial", 0, 14));            
            mapPanel.add(et);
            
            et = new Label("Dimensiones: "+x+"x"+y);
            et.setBounds(100, 60, 500, 30);
            et.setFont(new Font("Arial", 0, 14));            
            mapPanel.add(et);
            
            add( mapPanel );            
            mapPanel.dibujar(x, y, xMin,yMin,xMax,yMax, dat, door, window);            
        }      
        
        /** 
         * Elimina la vista de la cuadricula de la habitacion. 
         */
        public void removeCjtMapView(){
            cjtMapPanel.setVisible(false);
        }
        
        /** 
         * Elimina la vista de la habitacion. 
         */
        public void removeMapPanel(){
            mapPanel.setVisible(false);
        }  
        
        /** 
         * Elimina la vista de la edicion de la habitacion. 
         */
        public void removeMapEditPanel(){
            mapEditPanel.setVisible(false);
        }
        //<---END--->\\
        
        //Funciones ConfigurationPanel   
        
        /** 
         * Vista del menu de configuracion. 
         */
        public void configView(){     
            configPanel = new ConfigurationPanel(this, controller.getConfigurationController());
            configPanel.setBackground(Color.WHITE);   
            configPanel.setVisible(true);
            add( configPanel );
            configPanel.visualMenuConfig();            
        }
        
        /** 
         * Eliminar vista del menu de configuracion. 
         */
        public void removeConfigView(){
            configPanel.setVisible(false);
        }
        
        /** 
         * Mensaje de confirmacion de reset.
         */
        public Boolean validation(){    
            Boolean decision = false;
            int respuesta =JOptionPane.showConfirmDialog(this,"¿Realmente desea resetear los valores?","",JOptionPane.YES_NO_OPTION);
            if (respuesta == 0){ decision = true;}
            return decision;
        } 
        //<---END--->\\
        
        //Funciones RestrictionPanel
        
        /** 
         * Vista del menu de restricciones. 
         */
        public void cjtRestrictionsPanel(){     
            cjtRestrictionsPanel = new CjtRestrictionsPanel((controller.getConfigurationController()).getCjtRestrictionsController(), (controller.getConfigurationController()).getCjtElementsController(), (controller.getConfigurationController()).getCjtRoomTypeController());
            cjtRestrictionsPanel.setBackground(Color.WHITE);   
            cjtRestrictionsPanel.setVisible(true);
            add( cjtRestrictionsPanel );
            cjtRestrictionsPanel.mainVisualization();            
        }
        
        /** 
         * Vista del menu de listar restricciones. 
         */
        public void showRestrictionsPanel(){     
            cjtRestrictionsPanel = new CjtRestrictionsPanel((controller.getConfigurationController()).getCjtRestrictionsController(), (controller.getConfigurationController()).getCjtElementsController(), (controller.getConfigurationController()).getCjtRoomTypeController());
            cjtRestrictionsPanel.setBackground(Color.WHITE);   
            cjtRestrictionsPanel.setVisible(true);
            add( cjtRestrictionsPanel );
            cjtRestrictionsPanel.showVisualization();            
        }
        
        /** 
         * Vista de listar restricciones de distancia. 
         */
        public void showRestrictionsDistancePanel(String [][] data){     
            cjtRestrictionsPanel = new CjtRestrictionsPanel((controller.getConfigurationController()).getCjtRestrictionsController(), (controller.getConfigurationController()).getCjtElementsController(), (controller.getConfigurationController()).getCjtRoomTypeController());
            cjtRestrictionsPanel.setBackground(Color.WHITE);   
            cjtRestrictionsPanel.setVisible(true);
            add( cjtRestrictionsPanel );
            cjtRestrictionsPanel.showRestrictionDistance(data);
        }
        
        /** 
         * Vista de listar restricciones de sala-objeto. 
         */
        public void showRestrictionsRoomTypePanel(String [][] data){     
            cjtRestrictionsPanel = new CjtRestrictionsPanel((controller.getConfigurationController()).getCjtRestrictionsController(), (controller.getConfigurationController()).getCjtElementsController(), (controller.getConfigurationController()).getCjtRoomTypeController());
            cjtRestrictionsPanel.setBackground(Color.WHITE);   
            cjtRestrictionsPanel.setVisible(true);
            add( cjtRestrictionsPanel );
            cjtRestrictionsPanel.showRestrictionRoomType(data);
        }
        
        /** 
         * Vista del menu de eliminar restricciones. 
         */
        public void removeRestrictionsPanel(){     
            cjtRestrictionsPanel = new CjtRestrictionsPanel((controller.getConfigurationController()).getCjtRestrictionsController(), (controller.getConfigurationController()).getCjtElementsController(), (controller.getConfigurationController()).getCjtRoomTypeController());
            cjtRestrictionsPanel.setBackground(Color.WHITE);   
            cjtRestrictionsPanel.setVisible(true);
            add( cjtRestrictionsPanel );
            cjtRestrictionsPanel.removeVisualization();            
        }  
        
        /** 
         * Vista de eliminar restricciones de distancia. 
         */
        public void removeRestrictionDistancePanel(String [][] data){     
            cjtRestrictionsPanel = new CjtRestrictionsPanel((controller.getConfigurationController()).getCjtRestrictionsController(), (controller.getConfigurationController()).getCjtElementsController(), (controller.getConfigurationController()).getCjtRoomTypeController());
            cjtRestrictionsPanel.setBackground(Color.WHITE);   
            cjtRestrictionsPanel.setVisible(true);
            add( cjtRestrictionsPanel );
            cjtRestrictionsPanel.removeRestrictionDistance(data);
        }
        
        /** 
         * Vista de eliminar restricciones de sala-objeto. 
         */
        public void removeRestrictionRoomTypePanel(String [][] data){     
            cjtRestrictionsPanel = new CjtRestrictionsPanel((controller.getConfigurationController()).getCjtRestrictionsController(), (controller.getConfigurationController()).getCjtElementsController(), (controller.getConfigurationController()).getCjtRoomTypeController());
            cjtRestrictionsPanel.setBackground(Color.WHITE);   
            cjtRestrictionsPanel.setVisible(true);
            add( cjtRestrictionsPanel );
            cjtRestrictionsPanel.removeRestrictionRoomType(data);
        }	
        
        /** 
         * Vista del menu de agregar restricciones. 
         */
        public void addRestrictionsPanel(){     
            cjtRestrictionsPanel = new CjtRestrictionsPanel((controller.getConfigurationController()).getCjtRestrictionsController(), (controller.getConfigurationController()).getCjtElementsController(), (controller.getConfigurationController()).getCjtRoomTypeController());
            cjtRestrictionsPanel.setBackground(Color.WHITE);   
            cjtRestrictionsPanel.setVisible(true);
            add( cjtRestrictionsPanel );
            cjtRestrictionsPanel.addVisualization();            
        }
        
        /** 
         * Vista de agregar restricciones de distancia. 
         */
        public void addRestrictionDistancePanel(String [][] data){     
            cjtRestrictionsPanel = new CjtRestrictionsPanel((controller.getConfigurationController()).getCjtRestrictionsController(), (controller.getConfigurationController()).getCjtElementsController(), (controller.getConfigurationController()).getCjtRoomTypeController());
            cjtRestrictionsPanel.setBackground(Color.WHITE);   
            cjtRestrictionsPanel.setVisible(true);
            add( cjtRestrictionsPanel );
            cjtRestrictionsPanel.addRestrictionDistance(data);
        }
        
        /** 
         * Vista de agregar restricciones de sala-objeto. 
         */
        public void addRestrictionRoomTypePanel(String [] roomList, String [][] data){     
            cjtRestrictionsPanel = new CjtRestrictionsPanel((controller.getConfigurationController()).getCjtRestrictionsController(), (controller.getConfigurationController()).getCjtElementsController(), (controller.getConfigurationController()).getCjtRoomTypeController());
            cjtRestrictionsPanel.setBackground(Color.WHITE);   
            cjtRestrictionsPanel.setVisible(true);
            add( cjtRestrictionsPanel );
            cjtRestrictionsPanel.addRestrictionRoomType(roomList, data);
        }
        
        /** 
         * Eliminar vista de restricciones. 
         */
        public void removeCjtRestrictionsView(){
            cjtRestrictionsPanel.setVisible(false);
        }
        //<---END--->\\
        
        //Funciones RoomPanel
        
        /** 
         * Vista del menu de salas. 
         */
         public void cjtRoomTypePanel(){     
            cjtRoomTypePanel = new CjtRoomTypePanel((controller.getConfigurationController()).getCjtRoomTypeController(), (controller.getConfigurationController()).getCjtElementsController());
            cjtRoomTypePanel.setBackground(Color.WHITE);   
            cjtRoomTypePanel.setVisible(true);
            add( cjtRoomTypePanel );
            cjtRoomTypePanel.mainVisualization();            
        }
        
         /** 
         * Vista de listar salas. 
         */
        public void showRoomTypePanel(int maxId,int size,List<Integer> idFurniture,String list[],String data[][],int indx){     
            cjtRoomTypePanel = new CjtRoomTypePanel((controller.getConfigurationController()).getCjtRoomTypeController(), (controller.getConfigurationController()).getCjtElementsController());
            cjtRoomTypePanel.setBackground(Color.WHITE);   
            cjtRoomTypePanel.setVisible(true);
            add( cjtRoomTypePanel );
            cjtRoomTypePanel.showVisualization(maxId,size,idFurniture,list,data,indx);            
        }
        
        /** 
         * Vista de eliminar salas. 
         */
        public void removeRoomTypePanel(int maxId, int size, String list[]){     
            cjtRoomTypePanel = new CjtRoomTypePanel((controller.getConfigurationController()).getCjtRoomTypeController(), (controller.getConfigurationController()).getCjtElementsController());
            cjtRoomTypePanel.setBackground(Color.WHITE);   
            cjtRoomTypePanel.setVisible(true);
            add( cjtRoomTypePanel );
            cjtRoomTypePanel.removeVisualization(maxId,size,list);            
        }
        
        /** 
         * Vista de agregar salas. 
         */
        public void addRoomTypePanel(int maxId, int size, String list[],String data[][]){     
            cjtRoomTypePanel = new CjtRoomTypePanel((controller.getConfigurationController()).getCjtRoomTypeController(), (controller.getConfigurationController()).getCjtElementsController());
            cjtRoomTypePanel.setBackground(Color.WHITE);   
            cjtRoomTypePanel.setVisible(true);
            add( cjtRoomTypePanel );
            cjtRoomTypePanel.addVisualization(maxId,size,list,data);            
        }
        
        /** 
         * Vista del menu de editar salas. 
         */
        public void editRoomTypePanel(){
            cjtRoomTypePanel = new CjtRoomTypePanel((controller.getConfigurationController()).getCjtRoomTypeController(), (controller.getConfigurationController()).getCjtElementsController());
            cjtRoomTypePanel.setBackground(Color.WHITE);   
            cjtRoomTypePanel.setVisible(true);
            add( cjtRoomTypePanel );
            cjtRoomTypePanel.editVisualization();            
        }
        
        /** 
         * Vista de agregar muebles a salas. 
         */
        public void addRelatedRoomTypePanel(int maxId, int size,String list[],String data[][]){
            cjtRoomTypePanel = new CjtRoomTypePanel((controller.getConfigurationController()).getCjtRoomTypeController(), (controller.getConfigurationController()).getCjtElementsController());
            cjtRoomTypePanel.setBackground(Color.WHITE);   
            cjtRoomTypePanel.setVisible(true);
            add( cjtRoomTypePanel );
            cjtRoomTypePanel.addRelatedVisualization(maxId,size,list,data);         
        }
        
        /** 
         * Vista de eliminar muebles de salas. 
         */
        public void removeRelatedRoomTypePanel(int maxId, int size,List<Integer> idFurniture, String list[],String data[][],int indx){
            cjtRoomTypePanel = new CjtRoomTypePanel((controller.getConfigurationController()).getCjtRoomTypeController(), (controller.getConfigurationController()).getCjtElementsController());
            cjtRoomTypePanel.setBackground(Color.WHITE);   
            cjtRoomTypePanel.setVisible(true);
            add( cjtRoomTypePanel );
            cjtRoomTypePanel.removeRelatedVisualization(maxId,size,idFurniture,list,data,indx);         
        }
        
        /** 
         * Eliminar vista de salas. 
         */
        public void removeCjtRoomTypeView(){
            cjtRoomTypePanel.setVisible(false);
        }
        //<---END--->\\
        
        //Funciones CjtElementsPanel
        
        /** 
         * Vista del menu de muebles. 
         */
        public void cjtElementsPanel(){
            cjtElementsPanel = new CjtElementsPanel(controller.getConfigurationController().getCjtElementsController(), this);
            cjtElementsPanel.setBackground(Color.WHITE);   
            cjtElementsPanel.setVisible(true);
            add( cjtElementsPanel );
            cjtElementsPanel.mainVisualization(); 
        }  
        
        /** 
         * Vista de listar muebles. 
         */
         public void showCjtElementsPanel(){             
            
            String data[][] = controller.getConfigurationController().getCjtElementsController().getCjtElementsInData();
            boolean consult = controller.getConfigurationController().getCjtElementsController().consultCjtElementsInData();
            
            cjtElementsPanel = new CjtElementsPanel(controller.getConfigurationController().getCjtElementsController(), this);
            cjtElementsPanel.setBackground(Color.WHITE);   
            cjtElementsPanel.setVisible(true);
            add( cjtElementsPanel );
            cjtElementsPanel.showVisualization(data, consult);            
        }
         
         /** 
         * Vista de eliminar muebles. 
         */
         public void deleteCjtElementsPanel(){ 
            int idPos[];            
            String data[][] = controller.getConfigurationController().getCjtElementsController().getCjtElementsInData();
            idPos = controller.getConfigurationController().getCjtElementsController().getIdPos();   
            cjtElementsPanel = new CjtElementsPanel(controller.getConfigurationController().getCjtElementsController(), this);
            cjtElementsPanel.setIdPos(idPos);
            cjtElementsPanel.setBackground(Color.WHITE);   
            cjtElementsPanel.setVisible(true);
            add( cjtElementsPanel );
            cjtElementsPanel.deleteVisualization(data);            
        }
         
         /** 
         * Vista del menu de agregar muebles. 
         */
        public void addMenuCjtElementsPanel(){ 
            cjtElementsPanel = new CjtElementsPanel(controller.getConfigurationController().getCjtElementsController(), this);
            cjtElementsPanel.setBackground(Color.WHITE);   
            cjtElementsPanel.setVisible(true);
            add( cjtElementsPanel );
            cjtElementsPanel.addMenuVisualization();            
        }
        
        /** 
         * Vista de agregar muebles. 
         */
        public void addCjtElementsPanel(){ 
            cjtElementsPanel = new CjtElementsPanel(controller.getConfigurationController().getCjtElementsController(), this);
            cjtElementsPanel.setBackground(Color.WHITE);   
            cjtElementsPanel.setVisible(true);
            add( cjtElementsPanel );
            cjtElementsPanel.addVisualization();            
        }
        
        /** 
         * Vista de agregar packs. 
         */
        public void addPackCjtElementsPanel(){
            int idPos[];            
            String data[][] = controller.getConfigurationController().getCjtElementsController().getCjtElementsInData();
            idPos = controller.getConfigurationController().getCjtElementsController().getIdPos();  
            cjtElementsPanel = new CjtElementsPanel(controller.getConfigurationController().getCjtElementsController(), this);
            cjtElementsPanel.setIdPos(idPos);
            cjtElementsPanel.setBackground(Color.WHITE);   
            cjtElementsPanel.setVisible(true);
            add( cjtElementsPanel );
            cjtElementsPanel.addPackVisualization(data);            
        }
        
        /** 
         * Vista de editar muebles. 
         */
        public void editPanel(){
            int idPos[];            
            String data[][] = controller.getConfigurationController().getCjtElementsController().getCjtElementsInData();
            idPos = controller.getConfigurationController().getCjtElementsController().getIdPos(); 
            cjtElementsPanel = new CjtElementsPanel(controller.getConfigurationController().getCjtElementsController(), this);
            cjtElementsPanel.setIdPos(idPos);
            cjtElementsPanel.setBackground(Color.WHITE);   
            cjtElementsPanel.setVisible(true);
            add( cjtElementsPanel );
            cjtElementsPanel.editVisualization(data); 
        }
        
        /** 
         * Eliminar vista de muebles. 
         */
        public void removeCjtElementsView(){
            cjtElementsPanel.setVisible(false);            
        }
        
        /** 
         * Mensaje de eliminar mueble. 
         */
        public Boolean validationTwo(){    
            Boolean decision = false;
            int respuesta =JOptionPane.showConfirmDialog(this,"¿Realmente desea eliminar este mueble?","",JOptionPane.YES_NO_OPTION);
            if (respuesta == 0){ decision = true;}
            return decision;
        }
        //<---END--->\\

        /** 
         * Cerrar programa. 
         */
        public void close(){
            dispose();
        }
            
}
 