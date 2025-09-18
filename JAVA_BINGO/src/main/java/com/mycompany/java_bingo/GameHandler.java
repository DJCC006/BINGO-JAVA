/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java_bingo;

/**
 *
 * @author David
 */
public class GameHandler {
    private String gameMode;
    private int[] registroNumeros = new int[75];
    private int numCantado;
    private boolean gameStatus=true;
    private boolean[][] tableroHandler = new boolean[5][5];
    
    
    //setters
    public void setGameMode(String gameMode){
        this.gameMode=gameMode;
    }
    
    public void setNumCantado(int numCantado){
        this.numCantado=numCantado;
    }
    
    
    public void setTableHandler(boolean[][] tableroHandler){
        this.tableroHandler= tableroHandler;
    }
    
    
    //getters
    public String getGameMode(){
        return gameMode;
    }
    
    public int getNumCantado(){
        return numCantado;
    }
    
    public boolean getGameStatus(){
        return gameStatus;
    }
    
    public boolean[][] getTableroH(){
        return tableroHandler;
    }
    
    
    
    
    //Metodos de clase
    public void termiteGame(){
        gameStatus=false;
    }
    
    
    
    
    
    
}
