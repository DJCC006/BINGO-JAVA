/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java_bingo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author David
 */
public class VisualTablero {
    
    public VisualTablero(){
        
        //Creacion de JFrame
        JFrame screen = new JFrame();
        screen.setSize(1205, 700);  //1205, 700
        screen.setResizable(false);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setLocationRelativeTo(null);
        screen.setLayout(null);
        
        
        //Creacion de JPanel
        JPanel panel = new JPanel(new GridLayout(5,5));
        for(int i=0; i<25; i++){
            panel.add(new JButton("P"+i));
        }
        panel.setBounds(603, 90, 525,530);
        panel.setBackground(Color.gray);
        
        screen.add(panel);
        //screen.pack();
        
        
        
        
        
        
        
        
        
        screen.setVisible(true);
    }
    
    
    public static void main(String[] args) {
        VisualTablero ventana = new VisualTablero();
    }
    
    
    
}
