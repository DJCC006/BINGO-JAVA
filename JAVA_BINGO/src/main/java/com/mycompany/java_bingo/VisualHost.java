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
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
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
    
    
    //Variables receivers
    private String mensajeServer;
    
    
    //Elementos para mostrar nums en pantalla
    JLabel numGenerado = new JLabel();//label para mostrar numero generado
    int[] registeredNums= new int[75];
    
    
    
    //Creacion de tablero general
    JPanel panelNumeros = new JPanel(new GridLayout(15,5));
    private int count=0;
    private JButton[][] numerosBotones = new JButton[15][5];//referencia de todos los botones
    private String[] userArray;//Array list con los nombres de los usuarios
    private static int cantPlayers;//para que me deje crear la ventana xd
    
    //linsk con info del server con respecto a lista de jugadores
    private static Servidor server;
    List playerList;
    JTextArea visualPlayers = new JTextArea();
    private ScheduledExecutorService scheduler;
    private boolean salaLlena=false;
    
    
    //Elementos de tablero preview
    JLabel tableroshow = new JLabel("");
    
    public VisualHost(String gameMode, int cantPlayers){
        this.gameMode=gameMode;
        this.cantPlayers=cantPlayers;
        userArray = new String[cantPlayers];
        //Envia gamemode al server para ser retenido por el server y luego mandarlo cada vez que se una un nuevo usuario
        //host.sendMessages(gameMode);
        
        
        
        //Creacion de server
        server = new Servidor(this);
        server.startServer();;

        //Creacion de host as a player
        host = new Cliente("HOST", "localhost", 7775);
        host.start();
        
        
        
        //Creacion de fondo de pantalla
        String rutacompleta = "C:\\Users\\David\\Documents\\Documentos UNI\\I Jahre\\IV Period\\Prácticas Programación\\Juego Bingo\\JAVA_BINGO\\src\\main\\java\\resources\\mainBC.jpg";
        AplicarFondos panelFondo = new AplicarFondos(rutacompleta); 
        panelFondo.setLayout(new BorderLayout());

      
        
        
        
        //Creacion de JFrame
        JFrame screen = new JFrame();
        screen.setContentPane(panelFondo);
        screen.setSize(1420, 800);  
        screen.setResizable(false);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setLocationRelativeTo(null);
        screen.setLayout(null);
        
        
        
        //TextArea con jugadores conectados
        JLabel playerstxt = new JLabel("JUGADORES CONECTADOS");
        playerstxt.setFont(new Font("Dialog", Font.ITALIC, 15));
        playerstxt.setForeground(Color.white);
        playerstxt.setBounds(165, 365, 250, 50);
        
        
        visualPlayers.setBounds(140, 410, 250, 300);
        screen.add(visualPlayers);
        screen.add(playerstxt);
        
        startPlayerUpdates();
        
        
        
        
        
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
        Border countedNumBorder = BorderFactory.createLineBorder(Color.DARK_GRAY,2);
        for(int i=0; i<15; i++){
            for(int j=0; j<5; j++){
                int num=numerosTablero[i][j];
                numerosBotones[i][j] = new JButton(String.valueOf(num));
                numerosBotones[i][j].setEnabled(false);
                numerosBotones[i][j].setOpaque(true);
                numerosBotones[i][j].setBorder(countedNumBorder);
                numerosBotones[i][j].setBackground(Color.white);
                panelNumeros.add(numerosBotones[i][j]);
            }
        }
        screen.add(panelNumeros);
        panelNumeros.setBounds(510, 100, 425,600);
        
        
        //Agregar el Label de Bingo
        JLabel BLetter = new JLabel("B ");
        BLetter.setFont(new Font("Dialog", Font.BOLD, 50));
        BLetter.setForeground(Color.white);
        BLetter.setBounds(535, 25, 200, 100);
        screen.add(BLetter);
        
        JLabel ILetter = new JLabel("I ");
        ILetter.setFont(new Font("Dialog", Font.BOLD, 50));
        ILetter.setForeground(Color.white);
        ILetter.setBounds(630, 25, 200, 100);
        screen.add(ILetter);
        screen.add(BLetter);
        
        JLabel NLetter = new JLabel("N ");
        NLetter.setFont(new Font("Dialog", Font.BOLD, 50));
        NLetter.setForeground(Color.white);
        NLetter.setBounds(705, 25, 200, 100);
        screen.add(NLetter);
        
        JLabel GLetter = new JLabel("G ");
        GLetter.setFont(new Font("Dialog", Font.BOLD, 50));
        GLetter.setForeground(Color.white);
        GLetter.setBounds(785, 25, 200, 100);
        screen.add(GLetter);
        
        JLabel OLetter = new JLabel("O ");
        OLetter.setFont(new Font("Dialog", Font.BOLD, 50));
        OLetter.setForeground(Color.white);
        OLetter.setBounds(870, 25, 200, 100);
        screen.add(OLetter);
        
        
        
                
        //Panel con numero generado
        JPanel gNumPanel = new JPanel();
        gNumPanel.setBounds(145, 50, 250, 123);
        gNumPanel.setLayout(null);//Deshabilita el layout por defecto y permite mover cosas en el panel
        gNumPanel.setBackground(Color.gray);
        Border numBorder = BorderFactory.createLineBorder(Color.yellow,5);
        gNumPanel.setBorder(numBorder);
        
        
        //Label con numero generado
        numGenerado.setFont(new Font("Serif",Font.BOLD, 30));
        numGenerado.setForeground(Color.white);
        gNumPanel.add(numGenerado);
        numGenerado.setBounds(110, 50, 200, 50);
        
        JLabel tituloGenerado = new JLabel("NUEVO NUMERO:");
        tituloGenerado.setFont(new Font("dialog", Font.ITALIC, 15));
        tituloGenerado.setForeground(Color.white);
        gNumPanel.add(tituloGenerado);
        tituloGenerado.setBounds(65, 20, 200, 50);
        screen.add(gNumPanel);
        
        
        //Boton para generar nuevo numero
        Border genNumBorder = BorderFactory.createLineBorder(Color.gray,5);
        JButton generateNum = new JButton("RAND NUM");
        generateNum.setFont(new Font("Dialog", Font.BOLD, 25));
        generateNum.setBackground(Color.yellow);
        generateNum.setForeground(Color.DARK_GRAY);
        generateNum.setBorder(genNumBorder);
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
        
        
        
        
        
        //Display GameMode
        JLabel showGameMode = new JLabel("MODO DE JUEGO");
        showGameMode.setFont(new Font("Dialogue", Font.BOLD, 30));
        showGameMode.setForeground(Color.white);
        showGameMode.setBounds(1050, 50, 300, 50);
        
       
        
        
        JLabel gameModetxt = new JLabel(gameMode);
        gameModetxt.setFont(new Font("Dialogue", Font.ITALIC, 30));
        gameModetxt.setForeground(Color.white);
        gameModetxt.setBounds(1050, 80, 300, 50);
        screen.add(gameModetxt);
        screen.add(showGameMode);
        
        
        
        //Panel de modo de juego actual
        JPanel panelPreview = new JPanel();
        panelPreview.setBounds(1050, 200, 225, 300);
        panelPreview.setBackground(Color.GRAY);
        Border PanelBorder = BorderFactory.createLineBorder(Color.yellow,5);
        panelPreview.setBorder(PanelBorder);
        panelPreview.setLayout(null);
        JLabel previewtxt = new JLabel("TABLERO OBJETIVO");
        previewtxt.setFont(new Font("Dialog", Font.BOLD, 20));
        previewtxt.setForeground(Color.white);
        previewtxt.setBounds(1055, 135, 225, 90); 
        
        tableroshow.setBounds(0, 0, 225, 300);
        panelPreview.add(tableroshow );
        
        
        screen.add(previewtxt);
        screen.add(panelPreview);
        
        
        //Boton de empezar partida
        JButton empezarPartida = new JButton("Empezar Partida");
        empezarPartida.setBounds(1050, 550, 225, 74);
        empezarPartida.setBackground(Color.YELLOW);
        empezarPartida.setFont(new Font("Dialog", Font.BOLD, 20));
        empezarPartida.setForeground(Color.DARK_GRAY);
        empezarPartida.setBorder(BorderFactory.createLineBorder(Color.gray,2));
        empezarPartida.addActionListener(new ActionListener(){
        @Override 
        public void actionPerformed(ActionEvent e){
            
            host.sendMessages(gameMode);//manda el mensaje de modo de juego
            String numTablero;
            if(gameMode.equals("FullHouse")){
                host.sendMessages("T4");
                numTablero="T4";
            }else{
                int min=1;
                int max=3;
                int typTablero = spawner.nextInt((max-min)+1)+min;
                numTablero="T"+String.valueOf(typTablero);
                host.sendMessages(numTablero);
            }
            
            tableroSeleccion(numTablero);
            empezarPartida.setEnabled(false);
        }
                    
        });
        screen.add(empezarPartida);
        
        
        //Boton de terminar partida
        JButton terminarPartida = new JButton("Finalizar Partida");
        terminarPartida.setBackground(Color.YELLOW);
        terminarPartida.setForeground(Color.darkGray);
        terminarPartida.setFont(new Font("Dialog", Font.BOLD, 20));
        terminarPartida.setBounds(1050, 650, 225, 74);
           terminarPartida.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
              server.closeServerSocket();
              MenuPrincipal menu = new MenuPrincipal();
              screen.dispose();
          }
                    
        });
        screen.add(terminarPartida);
        screen.setVisible(true);
    }
        

    public static void main(String[] args) {
        VisualHost ventana = new VisualHost(gameMode, cantPlayers);
        
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
    
    
    //metodo para mostrar el tipo de tablero
    private void tableroSeleccion(String typ){
        if(gameMode.equals("FullHouse")){
            System.out.println("Se ha elegido el tablero FULLHOUSE");
            java.net.URL imageUrl = getClass().getResource("/bingoFullHouse.png");
            if(imageUrl!=null){
                ImageIcon imageIcon = new ImageIcon(imageUrl);
                tableroshow.setIcon(imageIcon);
                tableroshow.setVisible(true);
            }else{
                System.err.println("Error");
            }
            
        }else if(gameMode.equals("Lineal")){
            if(typ.equals("T1")){
                System.out.println("Se ha elegido el tablero T1");
                java.net.URL imageUrl = getClass().getResource("/bingoT1.png");
                if(imageUrl!=null){
                    ImageIcon imageIcon = new ImageIcon(imageUrl);
                    tableroshow.setIcon(imageIcon);
                    tableroshow.repaint();
                    tableroshow.setVisible(true);
                }else{
                    System.err.println("Error");
                }
                
                
            }else if(typ.equals("T2")){
                System.out.println("Se ha elegido el tablero T2");
                java.net.URL imageUrl = getClass().getResource("/bingoT2.png");
                if(imageUrl!=null){
                    ImageIcon imageIcon = new ImageIcon(imageUrl);
                    tableroshow.setIcon(imageIcon);
                    tableroshow.repaint();
                    tableroshow.setVisible(true);
                }else{
                    System.err.println("Error");
                }
                
            }else if(typ.equals("T3")){
                System.out.println("Se ha elegido el tablero T3");
                java.net.URL imageUrl = getClass().getResource("/bingoT3.png");
                if(imageUrl!=null){
                    ImageIcon imageIcon = new ImageIcon(imageUrl);
                    tableroshow.setIcon(imageIcon);
                    tableroshow.repaint();
                    tableroshow.setVisible(true);
                }else{
                    System.err.println("Error");
                }

            }
        }
    }
    
    //Metodo para actualizar la lista con los jugadores conectados en sala
    private void cargarJugadores(){
        String listaPlayers="";
        for(int i=0; i<playerList.size(); i++){
            ClientInfo playetemp= (ClientInfo) playerList.get(i);
            String playerName =playetemp.getUsername();
            if(playerName.equals("HOST")){
                playerName="";
                listaPlayers+="\n"+playerName+"\n";
                visualPlayers.setText(listaPlayers);
            }else{
                listaPlayers+="\n"+playerName+"\n";
                visualPlayers.setText(listaPlayers);
                System.out.println(listaPlayers);
            }
            
        }
    }
    
    
    //Metodo que ira actualizando la lista de jugadores en tiempo real
    public void startPlayerUpdates(){
        scheduler = Executors.newSingleThreadScheduledExecutor();
        
        scheduler.scheduleAtFixedRate(new Runnable(){
            @Override
            public void run(){
                //Obtencion de lista de jugadores
                playerList =server.mandarPlayerList();
                
                SwingUtilities.invokeLater(()->{
                    cargarJugadores();
                    verificarSala();
                });
                
            }
        }, 0, 2, TimeUnit.SECONDS);
        
    }
    
    //apaga la revision 
    public void stopPlayerUpdates(){
        if(scheduler!= null){
            scheduler.shutdown();
        }
    }
    
    
    //Metodo para comprobar el estado de la sala si llena o vacia
    public boolean verificarSala(){
        int countPlayersLive =playerList.size();
        if(countPlayersLive>=cantPlayers){
            return true;
        }else{
            return false;
        }
    }
    
    //metodo para obtener el estado de la sala
    public boolean getstatusSala(){
        return salaLlena;
    }
    
    
    public int getNumPlayers(){
        return cantPlayers;
    }
    
            
    
    
}
