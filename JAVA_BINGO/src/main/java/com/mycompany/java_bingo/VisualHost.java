/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java_bingo;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

/**
 *
 * @author David
 */
public class VisualHost {
    
    public VisualHost(){
        
        //Creacion de JFrame
        JFrame screen = new JFrame();
        screen.setSize(1420, 800);  
        screen.setResizable(false);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setLocationRelativeTo(null);
        screen.setLayout(null);
        
        
        //Creacion de tablero general
        JPanel panelNumeros = new JPanel(new GridLayout(15,5));
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
                JButton botonNum = new JButton(""+num);
                botonNum.setEnabled(false);
                botonNum.setOpaque(true);
                botonNum.setBackground(Color.white);
                panelNumeros.add(botonNum);
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
        JLabel numGenerado = new JLabel("NUM HERE");
        numGenerado.setFont(new Font("Serif",Font.BOLD, 30));
        gNumPanel.add(numGenerado);
        numGenerado.setBounds(45, 50, 200, 50);
        
        JLabel tituloGenerado = new JLabel("NUEVO NUMERO:");
        tituloGenerado.setFont(new Font("Serif", Font.ITALIC, 15));
        gNumPanel.add(tituloGenerado);
        tituloGenerado.setBounds(65, 20, 200, 50);
        screen.add(gNumPanel);
        
        
        //Boton para generar nuevo numero
        JButton generateNum = new JButton("RAND NUM");
        generateNum.setBounds(190, 200, 150, 150);
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
        JLabel gameModetxt = new JLabel("MODE HERE");
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
        
        //Boton de terminar partida
        JButton terminarPartida = new JButton("Finalizar Partida");
        terminarPartida.setBounds(1050, 550, 225, 74);
        screen.add(terminarPartida);
        
        
        
        screen.setVisible(true);
        
    }
        

    public static void main(String[] args) {
        VisualHost ventana = new VisualHost();
    }
    
    
    
}
