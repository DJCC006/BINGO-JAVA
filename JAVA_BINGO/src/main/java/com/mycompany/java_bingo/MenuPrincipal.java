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

/**
 *
 * @author David
 */
public class MenuPrincipal {
    public MenuPrincipal(){
          //Creacion de JFrame
        JFrame screen = new JFrame();
        screen.setSize(681, 432);  //Tamaño standard para menus
        screen.setResizable(false);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setLocationRelativeTo(null);
        screen.setLayout(null);
        
        //Creacion de titulo
        JLabel tituloGeneral = new JLabel("Menu Principal");
        tituloGeneral.setFont(new Font("Serif", Font.BOLD, 50));
        tituloGeneral.setBounds(171, 5, 500, 100);
        screen.add(tituloGeneral);
        
        //Creacion de botones
        JButton botonCrear = new JButton("Crear Partida");
        JButton botonUnirse = new JButton("Unirse a Partida");
        JButton botonregresar = new JButton("Regresar");
        botonCrear.setBounds(230, 120, 200, 50);
        botonCrear.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
              creacionPartida crearScreen = new creacionPartida();
              screen.dispose();
          }
                    
        });
        
        botonUnirse.setBounds(230, 200, 200, 50);
        botonUnirse.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
              UnirsePartida joinGame = new UnirsePartida();
              screen.dispose();
          }
                    
        });
        
        
        
        botonregresar.setBounds(230, 280, 200, 50);
        botonregresar.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
              MenuInicial menuInicial = new MenuInicial();
              screen.dispose();
          }
                    
        });
        
        screen.add(botonCrear);
        screen.add(botonUnirse);
        screen.add(botonregresar);
        
        screen.setVisible(true);
        
    }
    
    
    
    public static void main(String[] args) {
        MenuPrincipal ventana = new MenuPrincipal();
    }
}
