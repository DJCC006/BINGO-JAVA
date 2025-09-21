/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java_bingo;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author David
 */
public class creacionPartida {
    public creacionPartida(){
          //Creacion de JFrame
        JFrame screen = new JFrame();
        screen.setSize(681, 432);  //Tamaño standard para menus
        screen.setResizable(false);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setLocationRelativeTo(null);
        screen.setLayout(null);
        
        //Creacion de titulo
        JLabel tituloGeneral = new JLabel("Creacion de Partida");
        tituloGeneral.setFont(new Font("Serif", Font.BOLD, 50));
        tituloGeneral.setBounds(131, 5, 700, 100);
        screen.add(tituloGeneral);
        
        //Obtencion de info
        JLabel playerstxt= new JLabel("Cantidad de jugadores (Max 15): ");
        playerstxt.setFont(new Font("Serif", Font.ITALIC, 10));
        playerstxt.setBounds(150, 120, 300, 50);
        JTextField ingresoPlayers = new JTextField();
        ingresoPlayers.setBounds(150, 180, 150, 30);
        screen.add(ingresoPlayers);
        screen.add(playerstxt);
        
        
        
        
        
        
        JLabel modetxt = new JLabel("Modo de Juego:");
        modetxt.setFont(new Font("Serif", Font.ITALIC, 15));
        modetxt.setBounds(380, 130, 150, 30);
        screen.add(modetxt);
                
        String[] modosJuego = {"FullHouse", "Lineal"};
        JComboBox<String> selecMode = new JComboBox<>(modosJuego);
        selecMode.setBounds(380, 180, 160, 30);
        screen.add(selecMode);
        
        
        //Creacion de botones
        JButton crearBtt = new JButton("Crear Partida");
        crearBtt.setBounds(150, 250, 150, 50);
        crearBtt.addActionListener(new ActionListener(){
          @Override 
        public void actionPerformed(ActionEvent e){
            
              String modojuego= (String) selecMode.getSelectedItem();//variable de modo de juego
              System.out.println(modojuego);
              String catchertxt=ingresoPlayers.getText(); 
              int numPlayers;//cantidad de jugadores
              if(catchertxt.equals("")){
                    numPlayers=0;
              }else{
                    numPlayers= Integer.parseInt(catchertxt);
              }
              System.out.println(numPlayers);
              
              if(numPlayers>15){
                  JOptionPane.showMessageDialog(screen, "AVISO: Maximo de Jugadores Superado");
              }else{
                  
                  //Crea los settings del juego para usarlo entre distintas ventanas
                  GameHandler settings = new GameHandler();
                  settings.setGameMode(modojuego);
                  GameSingleton.getInstancia().setGameSetting(settings);
                  
                   
                   
                    //Creacion de server
                    Servidor server = new Servidor();
                    server.startServer();;
                    
                    //Creacion de host as a player
                    Cliente host = new Cliente("HOST", "localhost", 7775);
                    host.start();
                    
                    VisualHost pantallaHost = new VisualHost(modojuego, numPlayers, host);
                    
                    
                   screen.dispose();
              }
            }     
        });
        screen.add(crearBtt);
        
        
        JButton regresarBtt = new JButton("Regresar");
        regresarBtt.setBounds(380, 250, 150, 50);
         regresarBtt.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
              MenuPrincipal menuPrincipal = new MenuPrincipal();
              screen.dispose();
          }
                    
        });
        
        
        
        screen.add(regresarBtt);
        screen.setVisible(true);
        
    }
    
    
    
    public static void main(String[] args) {
        creacionPartida ventana = new creacionPartida();
    }
}
