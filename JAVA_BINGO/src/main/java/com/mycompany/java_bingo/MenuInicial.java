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

/**
 *
 * EL PROGRAMA SE INCIA DESDE AQUI
 */ 
public class MenuInicial {
    //681, 432
    
    public MenuInicial(){
        
        
        
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
        JLabel tituloGeneral = new JLabel("JAVA BINGO");
        tituloGeneral.setFont(new Font("DIALOG", Font.BOLD, 80));
        tituloGeneral.setForeground(Color.white);
        tituloGeneral.setBounds(92, 85, 500, 100);
        screen.add(tituloGeneral);
        
        //Creacion de botones
        JButton botonIniciar = new JButton("Jugar");
        botonIniciar.setFont(new Font("DIALOG", Font.BOLD, 20));
        botonIniciar.setBackground(Color.BLUE);
        botonIniciar.setForeground(Color.WHITE);
        JButton botonSalir = new JButton("Salir");
        botonSalir.setBackground(Color.red);
        botonSalir.setForeground(Color.WHITE);
        botonSalir.setFont(new Font("DIALOG", Font.BOLD, 20));
        botonIniciar.setBounds(120, 200, 180, 70);
        botonIniciar.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
              MenuPrincipal menuP = new MenuPrincipal();
              screen.dispose();
          }
                    
        });
        
        botonSalir.setBounds(360, 200, 180, 70);
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
