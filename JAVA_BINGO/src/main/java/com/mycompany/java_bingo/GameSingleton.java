/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java_bingo;

/**
 *
 * @author David
 */
public class GameSingleton {
    private static GameSingleton instancia;
    
    private VisualHost gameSetting;
    
    private GameSingleton(){};
    
    public static GameSingleton getInstancia(){
        if(instancia ==null){
            instancia = new GameSingleton();
        }
        return instancia;
    }
    
    
    public void setGameSingleton (VisualHost setting){
        gameSetting = setting;
    }
    
    public VisualHost getGameSettings(){
        return gameSetting;
    }
    
    public void removeGameSingleton(){
        gameSetting=null;
    }
    
    
    
}
