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
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.Random;

/**
 *
 * @author David
 */
public class VisualTablero {
    
    private int randNum;
    private Random spawner = new Random();
    private JButton showCantado = new JButton();//Boton que muestra numero que se acaba de cantar
    private JPanel panelCantados = new JPanel(new GridLayout(1,5));//Mini historial de numeros
    private int MaxHistorial =5;
    private JButton[] historialBotones = new JButton[5];
    private ArrayList<Integer> numerosCantados= new ArrayList();//Historial de numeros ya cantados para mostrar el mini hostiral
    
    public VisualTablero(){
        
        //Creacion de JFrame
        JFrame screen = new JFrame();
        screen.setSize(1420, 800);  //1205, 700
        screen.setResizable(false);
        screen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        screen.setLocationRelativeTo(null);
        screen.setLayout(null);
        
        
        
        //Creacion de JPanel de juego
        
        int[][] Tablero = new int[5][5];
        GeneradorTablero tableroSpawner = new GeneradorTablero();
        Tablero = tableroSpawner.obtenerTablero();
        tableroSpawner.Imprimir();
        
        
        JPanel panel = new JPanel(new GridLayout(5,5));
        for(int i=0; i<5; i++){
            for(int j=0; j<5; j++){
                String numtxt = String.valueOf(Tablero[i][j]);
                panel.add(new JButton(numtxt));
            }
            
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
        for(int j=0; j<5;j++){
            historialBotones[j]= new JButton("");
            historialBotones[j].setEnabled(false);
            panelCantados.add(historialBotones[j]);
        }
        panelCantados.setBounds(580, 0, 425, 100);
        screen.add(panelCantados);
        
        
        //Numero cantado actualmente
        //String.valueOf(GameSingleton.getInstancia().getGameSettings().getNumCantado())
        
        showCantado.setBounds(450, 0, 100, 100);
        screen.add(showCantado);
        
        //Indicador de modo de Juego
        JLabel indicadorModo = new JLabel("MODO DE JUEGO");
        indicadorModo.setBounds(350, 235, 225, 90);
        //GameSingleton.getInstancia().getGameSettings().getGameMode()
        JLabel juegoModetxt = new JLabel("MODO HERE");
        juegoModetxt.setBounds(350, 280, 225, 90);
        screen.add(juegoModetxt);
        screen.add(indicadorModo);
        
        
        //Boton de salida de partida
        JButton salirBtt= new JButton("Salir de Partida");
        salirBtt.setBounds(25, 670, 195, 74);
        salirBtt.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
              MenuPrincipal menu  = new MenuPrincipal();
              screen.dispose();
          }
                    
        });
        screen.add(salirBtt);
        
        //Testeo solamente
        JButton randBtt= new JButton("gen rand");
        randBtt.setBounds(25, 570, 195, 74);
        randBtt.addActionListener(new ActionListener(){
          @Override 
          public void actionPerformed(ActionEvent e){
              genAndShow();
          }
                    
        });
        
        
        screen.add(randBtt);
        
        
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
        
        
        JLabel OLetter = new JLabel("O ");
        OLetter.setFont(new Font("Serif", Font.BOLD, 50));
        OLetter.setBounds(870, 170, 200, 100);
        screen.add(OLetter);
        

        
        
        
        
        
        
        
        
        screen.setVisible(true);
    }
    
    
    public static void main(String[] args) {
        VisualTablero ventana = new VisualTablero();
    }
    
    
    public void genAndShow(){
        randNum =spawner.nextInt((75-1)+1)+1;
        showCantado.setText(String.valueOf(randNum));
        
        
        //Agregacion al historial
        numerosCantados.add(0,randNum);
        if(numerosCantados.size()>MaxHistorial){
            numerosCantados.remove(numerosCantados.size()-1);
        }
        
        actualizarHistorial();
        
        //Nota: a la hora de hacer esto con el server host, se debe recibir tanto el num en forma int como el String. el int se guardara tal y como esta la forma local
        //Lo que se puede hacer es crear otro arraylist en forma string que sirva la misma logica y trabaje paralelamente con el nums. A manera que, dependiendo el indice del array int, se establece el string
        
    }
    
    private void actualizarHistorial(){
        for(int i=0; i< historialBotones.length; i++){
            if(i<numerosCantados.size()){
                historialBotones[i].setText(String.valueOf(numerosCantados.get(i)));
            }else{
                historialBotones[i].setText("");
            }
        }
    }
    
    
    
}
