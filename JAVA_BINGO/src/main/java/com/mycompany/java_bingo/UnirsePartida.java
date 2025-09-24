/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java_bingo;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

/**
 *
 * @author David
 */
public class UnirsePartida {
    public UnirsePartida(){
         //Creacion de JFrame
        JFrame screen = new JFrame();
        screen.setSize(681, 432);  //Tamaño standard para menus
        screen.setResizable(false);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setLocationRelativeTo(null);
        screen.setLayout(null);
        
        //Creacion de titulo
        JLabel tituloGeneral = new JLabel("Unirse a Partida");
        tituloGeneral.setFont(new Font("Serif", Font.BOLD, 50));
        tituloGeneral.setBounds(171, 5, 700, 100);
        screen.add(tituloGeneral);
        
        //Obtencion de info
        JLabel playerstxt= new JLabel("Ingrese su nombre de usuario: ");
        playerstxt.setFont(new Font("Serif", Font.ITALIC, 20));
        playerstxt.setBounds(200, 120, 300, 50);
        JTextField ingresoPlayers = new JTextField();
        ingresoPlayers.setBounds(191, 180, 300, 30);
        screen.add(ingresoPlayers);
        screen.add(playerstxt);
        
        
        //Creacion de botones
        JButton crearBtt = new JButton("Unirse Partida");
        crearBtt.setBounds(150, 250, 150, 50);
        crearBtt.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
              String username =ingresoPlayers.getText();
              
              VisualTablero pantallaUser = new VisualTablero(username);
              screen.dispose();
              
             
              
              
              
              
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
        UnirsePartida ventan = new UnirsePartida();
    }
}
