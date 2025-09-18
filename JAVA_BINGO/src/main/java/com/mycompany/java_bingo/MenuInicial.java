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
public class MenuInicial {
    //681, 432
    
    public MenuInicial(){
        //Creacion de JFrame
        JFrame screen = new JFrame();
        screen.setSize(681, 432);  //Tamaño standard para menus
        screen.setResizable(false);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setLocationRelativeTo(null);
        screen.setLayout(null);
        
        //Creacion de titulo
        JLabel tituloGeneral = new JLabel("JAVA BINGO");
        tituloGeneral.setFont(new Font("Serif", Font.BOLD, 50));
        tituloGeneral.setBounds(171, 5, 500, 100);
        screen.add(tituloGeneral);
        
        //Creacion de botones
        JButton botonIniciar = new JButton("Jugar");
        JButton botonSalir = new JButton("Salir");
        botonIniciar.setBounds(230, 120, 200, 70);
        botonIniciar.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
              MenuPrincipal menuP = new MenuPrincipal();
              screen.dispose();
          }
                    
        });
        
        botonSalir.setBounds(230, 200, 200, 70);
        botonSalir.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
              screen.dispose();
          }
                    
        });
        
        screen.add(botonIniciar);
        screen.add(botonSalir);
        
        
        
       
        
        
        
        
        screen.setVisible(true);
    }
    
    public static void main(String[] args) {
        MenuInicial ventana = new MenuInicial();
    }
    
    
}
