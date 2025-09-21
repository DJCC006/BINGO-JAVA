/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java_bingo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author David
 */
public class VisualTablero {
    
    
    //Variables generales para funcionamiento de programa
    private static ClientePlayer player;
    
    //Variables de recibimiento
    private int randNum;//Variable que almacena numero random 
    private int numCantTxt;//mismo num random pero con letra de donde pertenece
    private String gameModeLabel; //Variable que recibe el txt del modo de juego
    private String mensajeServer;
    JLabel juegoModetxt;
    
    
    //Variables senders
    private boolean win=false;
    private static String username;
   
    //Elementos para mini historial
    private JButton showCantado = new JButton();//Boton que muestra numero que se acaba de cantar
    private JPanel panelCantados = new JPanel(new GridLayout(1,5));//Mini historial de numeros
    private ArrayList<Integer> numerosCantados= new ArrayList();//Historial de numeros ya cantados para mostrar el mini hostiral
    private int MaxHistorial =5;
    private JButton[] historialBotones = new JButton[5];//botones como tal
    private int ultimoCantado=  0;
    int count=0;
    
    
    //Elementos tablero
    private JButton[][] botonesTablero = new JButton[5][5];//Array de botones de tablero

    
    //Cosas para funcionalidad de modalidad de juego
    private boolean[][] tableroObj = new boolean[5][5];//Variable que almacena la forma del tablero objetivo
    private boolean[][] tableroGamePlay;//tablero que va generando el usuario
    private int[] cantadosYa = new int[75];//Array que lleva control de numeros ya cantados

    
    //Elementos de testeo interno
    private Random spawner = new Random();
    
    
    
    
    public VisualTablero(String username){
        ;
        this.username=username;
        System.out.println(username);
        
        
        //creacion de player pa el server
        ClientePlayer player = new ClientePlayer(this.username, "localhost", 7775, this);
        player.start();
        this.player=player;
        
        
        
        //Generar el tablero objetivo
        tableroObj[0][0]=true;
        tableroObj[1][1]=true;
        tableroObj[3][3]=true;
        tableroObj[4][4]=true;
        tableroObj[0][4]=true;
        tableroObj[1][3]=true;
        tableroObj[3][1]=true;
        tableroObj[4][0]=true;
        tableroObj[2][2]=true;
        
        //Creacion de JFrame
        JFrame screen = new JFrame();
        screen.setSize(1420, 800);  //1205, 700
        screen.setResizable(false);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setLocationRelativeTo(null);
        screen.setLayout(null);
        
        
        //Creacion de manejador de tablero para gamePlay
        tableroGamePlay= new boolean[5][5];
        tableroGamePlay[2][2] = true; //El cuadrado del centro se manejara siempre como true;
        
        
        
        
        //Creacion de JPanel de juego
        int[][] Tablero = new int[5][5];
        GeneradorTablero tableroSpawner = new GeneradorTablero();
        Tablero = tableroSpawner.obtenerTablero();
        tableroSpawner.Imprimir();
        
        
        JPanel panel = new JPanel(new GridLayout(5,5));
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                
                botonesTablero[i][j] = new JButton();
                String numtxt = String.valueOf(Tablero[i][j]);
                botonesTablero[i][j].setText(numtxt);
                int fila=i;
                int columna=j;
                
                if(fila==2 && columna==2){
                    botonesTablero[i][j].setEnabled(false);
                }
                
                botonesTablero[i][j].addActionListener(new ActionListener(){
                    @Override 
                    public void actionPerformed(ActionEvent e){
                        if(tableroGamePlay[fila][columna]==false){
                            tableroGamePlay[fila][columna]=true;
                            botonesTablero[fila][columna].setBackground(Color.YELLOW);
                        }else if(tableroGamePlay[fila][columna]==true){
                            tableroGamePlay[fila][columna]=false;
                            botonesTablero[fila][columna].setBackground(Color.gray);
                        }
                    }

                  });
                
                
                
                panel.add(botonesTablero[i][j]);
            }
            
        }
        panel.setBounds(510, 250, 425,430);
        panel.setBackground(Color.gray);
        
        
        
        //Panel de modo de juego actual
        JPanel panelPreview = new JPanel();
        panelPreview.setBounds(1010, 300, 225, 300);
        panelPreview.setBackground(Color.GRAY);
        JLabel gameModetxt = new JLabel("Tablero Objetivo");
        gameModetxt.setBounds(1010, 235, 225, 90);
        screen.add(gameModetxt);
        
        screen.add(panelPreview);
        screen.add(panel);
        
        
        //Creacion de numeros ya cantados
        for(int j=0; j<5;j++){
            historialBotones[j]= new JButton("");
            historialBotones[j].setEnabled(false);
            panelCantados.add(historialBotones[j]);
        }
        panelCantados.setBounds(580, 0, 425, 100);
        screen.add(panelCantados);
        
        
        //Numero cantado actualmente
        showCantado.setBounds(450, 0, 100, 100);
        screen.add(showCantado);
        
        //Indicador de modo de Juego
        JLabel indicadorModo = new JLabel("MODO DE JUEGO");
        indicadorModo.setBounds(350, 235, 225, 90);
        //GameSingleton.getInstancia().getGameSettings().getGameMode()
        juegoModetxt = new JLabel("MODO HERE");
        juegoModetxt.setBounds(350, 280, 225, 90);
        screen.add(juegoModetxt);
        screen.add(indicadorModo);
        
        //boton de BINGO
        JButton bingoBtt= new JButton("BINGO");
        bingoBtt.setBounds(25, 470, 195, 74);
        bingoBtt.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
            comprobadorBingo(screen);
          }
        });
        screen.add(bingoBtt);
        
        //Boton de salida de partida
        JButton salirBtt= new JButton("Salir de Partida");
        salirBtt.setBounds(25, 670, 195, 74);
        salirBtt.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
              MenuPrincipal menu  = new MenuPrincipal();
              screen.dispose();
          }
                    
        });
        screen.add(salirBtt);
        
        //Testeo solamente
        JButton randBtt= new JButton("gen rand");
        randBtt.setBounds(25, 570, 195, 74);
        randBtt.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
              genAndShow();
          }
                    
        });
        screen.add(randBtt);
        screen.add(salirBtt);
        
        
        //Agregar el Label de Bingo
        JLabel BLetter = new JLabel("B ");//Esto se puede lograr  colocando cada letra como un label distinto
        BLetter.setFont(new Font("Serif", Font.BOLD, 50));
        BLetter.setBounds(535, 170, 200, 100);
        screen.add(BLetter);
        
        JLabel ILetter = new JLabel("I ");
        ILetter.setFont(new Font("Serif", Font.BOLD, 50));
        ILetter.setBounds(630, 170, 200, 100);
        screen.add(ILetter);
        screen.add(BLetter);
        
        JLabel NLetter = new JLabel("N ");
        NLetter.setFont(new Font("Serif", Font.BOLD, 50));
        NLetter.setBounds(705, 170, 200, 100);
        screen.add(NLetter);
        
        JLabel GLetter = new JLabel("G ");
        GLetter.setFont(new Font("Serif", Font.BOLD, 50));
        GLetter.setBounds(785, 170, 200, 100);
        screen.add(GLetter);
        
        
        JLabel OLetter = new JLabel("O ");
        OLetter.setFont(new Font("Serif", Font.BOLD, 50));
        OLetter.setBounds(870, 170, 200, 100);
        screen.add(OLetter);
        
        screen.setVisible(true);
    }
    
    
    public static void main(String[] args) {
        VisualTablero ventana = new VisualTablero(username);
    }
    
    
    
    //Testing only
    public void genAndShow(){
        boolean repetido;
        int randNum;
        do{
                randNum =spawner.nextInt((75-1)+1)+1;
                repetido=false;
                
            //Revision si ya existe en el array
            for(int i=0; i<count; i++){
                if(cantadosYa[i]==randNum){
                    repetido=true;
                    break; 
                }
            }
        }while(repetido);
        showCantado.setText(String.valueOf(randNum));
        
        
        //Agregacion al historial
        numerosCantados.add(0,randNum);
        cantadosYa[count]=randNum;
        if(numerosCantados.size()>MaxHistorial){
            numerosCantados.remove(numerosCantados.size()-1);
        }
        
        count++;
        
        actualizarHistorial();
        
        //Nota: a la hora de hacer esto con el server host, se debe recibir tanto el num en forma int como el String. el int se guardara tal y como esta la forma local
        //Lo que se puede hacer es crear otro arraylist en forma string que sirva la misma logica y trabaje paralelamente con el nums. A manera que, dependiendo el indice del array int, se establece el string
        
    }
    
    
    
    //Testing only - genera un simulacro del concepto logico que mandaria el host -caso full house
    private void generateObjArray(){
        for(int i=0; i<tableroObj.length; i++){
            for(int j=0; j<tableroObj.length; j++){
                tableroObj[i][j]=true;
            }
        }
    }
    
    private void actualizarHistorial(){
        
        for(int i=0; i< historialBotones.length; i++){
            if(i<numerosCantados.size()){
                if(i==randNum){
                    //nada xd
                }else{
                    historialBotones[i].setText(tranformadorNums(numerosCantados.get(i)));
                }
                
            }else{
                historialBotones[i].setText("");
            }
        }
    }
    
    
    
    private void comprobadorBingo(JFrame screen){
        //Condiciones para considerarse win:
        //Que coincidan con el tablero designao X
        //Que los numeros ya se hayan cantado
     
        boolean[][] arrayComprobador = new boolean [5][5];
        arrayComprobador[2][2]=true;//Casilla de centro siempre true
        
        for(int fila=0; fila<5; fila++){
            for(int columna=0; columna<5; columna++){
                //Obtiene el numero del boton actual
                int numButton = Integer.valueOf(botonesTablero[fila][columna].getText());
                
                
                //Verifica que este en la posicion del tablero objetivo
                boolean comp1=tableroObj[fila][columna]; 

                //Verifica que el numero de la casilla ya se haya cantado
                boolean comp2 =searchNum(numButton); //Verifica que el numero ya se haya cantado
                
            
                if(comp1==true && comp2==true){
                    arrayComprobador[fila][columna]=true;
                }
                
            }
        }
        
        
        
        win = Arrays.deepEquals(arrayComprobador,tableroObj);
        if(win==true){
            JOptionPane.showMessageDialog(screen, "HAS GANADO EL BINGO");
        }else{
            JOptionPane.showConfirmDialog(screen, "AUN NO PAPU");
        }
        
        
        //Print de array usuario
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                System.out.print(" "+arrayComprobador[i][j]+" ");
            }
            System.out.println("");
        }
        
        //Print de array asignado
        System.out.println("");
        for(int i=0; i<tableroObj.length; i++){
            for(int j=0; j<tableroObj.length; j++){
                System.out.print(" "+tableroObj[i][j]+" ");
            }
            System.out.println("");
        }
        
        
    }
    
    
    public boolean searchNum(int num){
        for(int i=0; i<cantadosYa.length; i++){
            if(num == cantadosYa[i]){
                return true;
            }
        }
        return false;
    }
    
    
    public void setCantado(String cantado){
        showCantado.setText(cantado);
    }
    
    
    public void setMensaje(String mensaje){
        mensajeServer=mensaje;
        depurarMensaje(mensajeServer);
    }
    
    
    public void depurarMensaje(String mensaje){
        try{
          
                //Revision si es el mensaje para gameMode
                /*
                if(mensaje.equals("")){
                    gameModeLabel=mensaje;
                    juegoModetxt.setText(gameModeLabel);
                    System.out.println("Entro a la revision de lineal o fullhouse");
                    return;
                }
                
*/
                //Verifica si es un numero entonces
                String[] parts = mensaje.split(":",2);
                String messageContent = parts.length>1 ? parts[1] : "";
                
                
                if(messageContent.equals("Lineal")|| messageContent.equals("FullHouse")){
                   gameModeLabel=messageContent;
                    juegoModetxt.setText(gameModeLabel);
                    System.out.println("Entro a la revision de lineal o fullhouse");
                    return;
                }else{
                    int num=0;
                    boolean compInt=false;
                    try{
                        num = Integer.parseInt(messageContent);
                        compInt=true;
                    }catch(NumberFormatException e){
                        compInt=false;
                    }

                    if(compInt==true){
                        randNum=num;
                        cantadosYa[count]=randNum;
                        actualizarNums(randNum);
                    }else{
                        System.out.println("Mensaje recibido"+mensaje);
                    }
                }
                
                
                
        }catch(NullPointerException e){
            e.printStackTrace();
        }
    }
    
    
    
    //Metodo que organiza todo el proceso para mostrar numeros en el historial
    private void actualizarNums(int randNum){
        //Actualizacion de controlador de ultimo numero cantado
        if(ultimoCantado!=0){
            numerosCantados.add(0,ultimoCantado);
            
            if(numerosCantados.size()>MaxHistorial){
                numerosCantados.remove(numerosCantados.size()-1);
            }
        }
        
        String showNum=tranformadorNums(randNum);//transforma numero
        setCantado(showNum);//muestra numero
        
        ultimoCantado=randNum;//Actualiza ultimo numero
        actualizarHistorial();
    }
    
    
    
    //Metodo que le pone la letra la num dependiendo del rango
    private String tranformadorNums(int randNum){
        String numLabel= String.valueOf(randNum);
        String numLabelwColum="";
            
        if(randNum>=1 && randNum<=15){
            numLabelwColum="B"+numLabel;
        }else if(randNum>=16 && randNum<=30){
            numLabelwColum="I"+numLabel;
        }else if(randNum>=31 && randNum<=45){
            numLabelwColum="N"+numLabel;
        }else if(randNum>=46 && randNum<=60){
            numLabelwColum="G"+numLabel;
        }else if(randNum>=61 && randNum<=75){
            numLabelwColum="O"+numLabel;
        }
        return numLabelwColum;
    }
    
    
    
}
