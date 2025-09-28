/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java_bingo;

import java.awt.BorderLayout;
import java.awt.Color;
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
        
        
        //Creacion de fondo de pantalla
        String rutacompleta = "src\\main\\java\\resources\\firstMENU.png";
        AplicarFondos panelFondo = new AplicarFondos(rutacompleta); 
        panelFondo.setLayout(new BorderLayout());
        
        
         //Creacion de JFrame
        JFrame screen = new JFrame();
        screen.setContentPane(panelFondo);
        screen.setSize(681, 432);  //Tamaño standard para menus
        screen.setResizable(false);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setLocationRelativeTo(null);
        screen.setLayout(null);
        
        //Creacion de titulo
        JLabel tituloGeneral = new JLabel("Unirse a Partida");
        tituloGeneral.setForeground(Color.WHITE);
        tituloGeneral.setFont(new Font("DIALOG", Font.BOLD, 50));
        tituloGeneral.setBounds(155, 25, 700, 100);
        screen.add(tituloGeneral);
        
        //Obtencion de info
        JLabel playerstxt= new JLabel("Ingrese su nombre de usuario: ");
        playerstxt.setForeground(Color.WHITE);
        playerstxt.setFont(new Font("Dialog", Font.ITALIC, 20));
        playerstxt.setBounds(200, 120, 300, 50);
        JTextField ingresoPlayers = new JTextField();
        ingresoPlayers.setBounds(191, 180, 300, 30);
        screen.add(ingresoPlayers);
        screen.add(playerstxt);
        
        
        //Creacion de botones
        JButton crearBtt = new JButton("Unirse Partida");
        crearBtt.setBackground(Color.orange);
        crearBtt.setFont(new Font("Dialog", Font.BOLD, 15));
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
        regresarBtt.setBackground(Color.orange);
        regresarBtt.setFont(new Font("Dialog", Font.BOLD, 20));
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
