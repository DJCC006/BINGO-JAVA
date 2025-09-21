/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java_bingo;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.util.Random;
/**
 *
 * @author David
 */
public class VisualHost {
    
    Random spawner = new Random();
    private static Cliente host;
    
    
    
    //Variables senders
    int ballNum;
    private static String gameMode;//variable que lleva control del modo de juego
    private String numLabelwColum;//Variable con numero y su letra de columna 
    
    
    
    //Elementos para mostrar nums en pantalla
    JLabel numGenerado = new JLabel();//label para mostrar numero generado
    int[] registeredNums= new int[75];
    
    
    
    //Creacion de tablero general
    JPanel panelNumeros = new JPanel(new GridLayout(15,5));
    private int count=0;
    private JButton[][] numerosBotones = new JButton[15][5];//referencia de todos los botones
    private String[] userArray;//Array list con los nombres de los usuarios
    private static int cantPlayers;//para que me deje crear la ventana xd
    
    
    
    public VisualHost(String gameMode, int cantPlayers, Cliente host){
        this.gameMode=gameMode;
        this.cantPlayers=cantPlayers;
        this.host=host;
        userArray = new String[cantPlayers];
        
        //Envia gamemode al server para ser retenido por el server y luego mandarlo cada vez que se una un nuevo usuario
        //host.sendMessages(gameMode);
        
        
        
        //Creacion de JFrame
        JFrame screen = new JFrame();
        screen.setSize(1420, 800);  
        screen.setResizable(false);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setLocationRelativeTo(null);
        screen.setLayout(null);
        
        
        
        //Generacion de numeros
        int[][] numerosTablero = new int[15][5];
        for(int j=0; j<5; j++){
            
            switch(j){
                case 0:
                    int count=1;
                    for(int i=0; i<15; i++){
                        numerosTablero[i][j]=count;
                        count++;
                    }
                    break;
                    
                case 1:
                    int count2=16;
                    for(int i=0; i<15; i++){
                        numerosTablero[i][j]=count2;
                        count2++;
                    }
                    break;
                    
                case 2:
                     int count3=31;
                    for(int i=0; i<15; i++){
                        numerosTablero[i][j]=count3;
                        count3++;
                    }
                    break;
                    
                case 3:
                     int count4=46;
                    for(int i=0; i<15; i++){
                        numerosTablero[i][j]=count4;
                        count4++;
                    }
                    break;
                    
                case 4:
                    int count5=61;
                    for(int i=0; i<15; i++){
                        numerosTablero[i][j]=count5;
                        count5++;
                    }
                    break;
                    
            }
        }
        
        //Agregado de numeros
        for(int i=0; i<15; i++){
            for(int j=0; j<5; j++){
                int num=numerosTablero[i][j];
                numerosBotones[i][j] = new JButton(String.valueOf(num));
                numerosBotones[i][j].setEnabled(false);
                numerosBotones[i][j].setOpaque(true);
                numerosBotones[i][j].setBackground(Color.white);
                panelNumeros.add(numerosBotones[i][j]);
            }
        }
        screen.add(panelNumeros);
        panelNumeros.setBounds(510, 100, 425,600);
        
        
        //Agregar el Label de Bingo
        JLabel BLetter = new JLabel("B ");
        BLetter.setFont(new Font("Serif", Font.BOLD, 50));
        BLetter.setBounds(535, 25, 200, 100);
        screen.add(BLetter);
        
        JLabel ILetter = new JLabel("I ");
        ILetter.setFont(new Font("Serif", Font.BOLD, 50));
        ILetter.setBounds(630, 25, 200, 100);
        screen.add(ILetter);
        screen.add(BLetter);
        
        JLabel NLetter = new JLabel("N ");
        NLetter.setFont(new Font("Serif", Font.BOLD, 50));
        NLetter.setBounds(705, 25, 200, 100);
        screen.add(NLetter);
        
        JLabel GLetter = new JLabel("G ");
        GLetter.setFont(new Font("Serif", Font.BOLD, 50));
        GLetter.setBounds(785, 25, 200, 100);
        screen.add(GLetter);
        
        JLabel OLetter = new JLabel("O ");
        OLetter.setFont(new Font("Serif", Font.BOLD, 50));
        OLetter.setBounds(870, 25, 200, 100);
        screen.add(OLetter);
        
        
        
                
        //Panel con numero generado
        JPanel gNumPanel = new JPanel();
        gNumPanel.setBounds(145, 50, 250, 123);
        gNumPanel.setLayout(null);//Deshabilita el layout por defecto y permite mover cosas en el panel
        gNumPanel.setBackground(Color.WHITE);
        
        
        //Label con numero generado
        numGenerado.setFont(new Font("Serif",Font.BOLD, 30));
        gNumPanel.add(numGenerado);
        numGenerado.setBounds(110, 50, 200, 50);
        
        JLabel tituloGenerado = new JLabel("NUEVO NUMERO:");
        tituloGenerado.setFont(new Font("Serif", Font.ITALIC, 15));
        gNumPanel.add(tituloGenerado);
        tituloGenerado.setBounds(65, 20, 200, 50);
        screen.add(gNumPanel);
        
        
        //Boton para generar nuevo numero
        JButton generateNum = new JButton("RAND NUM");
        generateNum.setBounds(190, 200, 150, 150);
        generateNum.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
              generaryMostrar();
              String numer= String.valueOf(ballNum);
              iluminarTablero(numer);
              imprimirNums();
              
              
              System.out.println("Cantidad de jugadores: "+cantPlayers);
          }
                    
        });
        screen.add(generateNum);
        
        
        //TextArea con jugadores conectados
        JLabel playerstxt = new JLabel("JUGADORES CONECTADOS");
        playerstxt.setFont(new Font("Serif", Font.ITALIC, 15));
        playerstxt.setBounds(165, 365, 250, 50);
        
        JTextArea visualPlayers = new JTextArea();
        visualPlayers.setBounds(140, 410, 250, 300);
        screen.add(visualPlayers);
        screen.add(playerstxt);
        
        
        //Display GameMode
        JLabel showGameMode = new JLabel("MODO DE JUEGO");
        showGameMode.setFont(new Font("Serif", Font.BOLD, 30));
        showGameMode.setBounds(1050, 50, 300, 50);
        JLabel gameModetxt = new JLabel(gameMode);
        gameModetxt.setFont(new Font("Serif", Font.ITALIC, 20));
        gameModetxt.setBounds(1050, 80, 300, 50);
        screen.add(gameModetxt);
        screen.add(showGameMode);
        
        
        
        //Panel de modo de juego actual
        JPanel panelPreview = new JPanel();
        panelPreview.setBounds(1050, 200, 225, 300);
        panelPreview.setBackground(Color.GRAY);
        JLabel previewtxt = new JLabel("Tablero Objetivo");
        previewtxt.setBounds(1050, 135, 225, 90);
        screen.add(previewtxt);
        screen.add(panelPreview);
        
        
        //Boton de empezar partida
        JButton empezarPartida = new JButton("Empezar Partida");
        empezarPartida.setBounds(1050, 550, 225, 74);
        empezarPartida.addActionListener(new ActionListener(){
        @Override 
        public void actionPerformed(ActionEvent e){
            host.sendMessages(gameMode);//manda el mensaje de modo de juego
            
            if(gameMode.equals("FullHouse")){
                host.sendMessages("T4");
            }else{
                int min=1;
                int max=3;
                int typTablero = spawner.nextInt((max-min)+1)+min;
                String numTablero="T"+String.valueOf(typTablero);
                host.sendMessages(numTablero);
            }
            
            
           
        }
                    
        });
        screen.add(empezarPartida);
        
        
        //Boton de terminar partida
        JButton terminarPartida = new JButton("Finalizar Partida");
        terminarPartida.setBounds(1050, 650, 225, 74);
           terminarPartida.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
              MenuPrincipal menu = new MenuPrincipal();
              screen.dispose();
          }
                    
        });
        screen.add(terminarPartida);
        screen.setVisible(true);
        
        
        
        
        
        
        
    }
        

    public static void main(String[] args) {
        VisualHost ventana = new VisualHost(gameMode, cantPlayers, host);
        
    }
    
    
    //Metodo que actualiza el numero generado
    public void generaryMostrar(){
        
            //Confirmacdor de conteo
            if(count>=75){
                System.out.println("Todos los numeros han sido generados. Se ha terminado el juego");
                return;
            }
            
            //Verificacion de que el numero random no se haya repetido ya
            boolean repetido;
            int randNum;
            do{
                randNum =spawner.nextInt((75-1)+1)+1;
                repetido=false;
                
                //Revision si ya existe en el array
                for(int i=0; i<count; i++){
                    if(registeredNums[i]==randNum){
                       repetido=true;
                        break; 
                    }
                }
            }while(repetido);
            
            
            //Clasificacion de columna
            numLabelwColum="";
            String numLabel= String.valueOf(randNum);
            
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
            
            
            
            //Agregado al array
            ballNum=randNum;
            registeredNums[count]=randNum;
            numGenerado.setText(numLabelwColum);
            count++;
            host.sendMessages(String.valueOf(randNum));
    }
    
    
    
    public void imprimirNums(){//Metodo solo de testeo, no brinda funcionalidad
        for(int i=0; i<registeredNums.length; i++){
            if(registeredNums[i]!=0){
                System.out.println(registeredNums[i]);
            }
            
        }
    }
    
    public void iluminarTablero(String num){
        for(int i=0; i<15; i++){
            for(int j=0; j<5; j++){
                if(num.equals(numerosBotones[i][j].getText())){
                    numerosBotones[i][j].setBackground(Color.YELLOW);
                }
            }
        }
    }
    
    
    
    private void sendGameMode(String gameMode){
        String Mode=gameMode;
        try{
            Thread.sleep(10000);
        }catch(InterruptedException e){
            e.printStackTrace();
        }
        
        host.sendMessages(Mode);
    } 
    
    
    
            
    
    
}
