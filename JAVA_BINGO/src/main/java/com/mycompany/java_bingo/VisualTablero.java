/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java_bingo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author David
 */
public class VisualTablero {
    
    public VisualTablero(){
        
        //Creacion de JFrame
        JFrame screen = new JFrame();
        screen.setSize(1420, 800);  //1205, 700
        screen.setResizable(false);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setLocationRelativeTo(null);
        screen.setLayout(null);
        
        
        //Creacion de JPanel de juego
        JPanel panel = new JPanel(new GridLayout(5,5));
        for(int i=0; i<25; i++){
            panel.add(new JButton("P"+i));
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
        JPanel panelCantados = new JPanel(new GridLayout(1,5));
        for(int j=0; j<5;j++){
            panelCantados.add(new JButton("Cantado"+j));
        }
        panelCantados.setBounds(580, 0, 425, 100);
        screen.add(panelCantados);
        
        
        //Numero cantado actualmente
        JButton showCantado = new JButton("Cantado Here");
        showCantado.setBounds(450, 0, 100, 100);
        screen.add(showCantado);
        
        //Indicador de modo de Juego
        JLabel indicadorModo = new JLabel("MODO DE JUEGO");
        indicadorModo.setBounds(350, 235, 225, 90);
        JLabel juegoModetxt = new JLabel("AQUI");
        juegoModetxt.setBounds(350, 280, 225, 90);
        screen.add(juegoModetxt);
        screen.add(indicadorModo);
        
        
        //Boton de salida de partida
        JButton salirBtt= new JButton("Salir de Partida");
        salirBtt.setBounds(25, 670, 195, 74);
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
        screen.add(GLetter);
        
        JLabel OLetter = new JLabel("O ");
        OLetter.setFont(new Font("Serif", Font.BOLD, 50));
        OLetter.setBounds(870, 170, 200, 100);
        screen.add(OLetter);
        
        
        
        
        
        
        
        
        
        screen.setVisible(true);
    }
    
    
    public static void main(String[] args) {
        VisualTablero ventana = new VisualTablero();
    }
    
    
    
}
