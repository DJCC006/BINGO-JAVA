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
    
    private GameHandler gameSetting;
    
    private GameSingleton(){};
    
    public static GameSingleton getInstancia(){
        if(instancia ==null){
            instancia = new GameSingleton();
        }
        return instancia;
    }
    
    
    public void setGameSetting(GameHandler setting){
        gameSetting = setting;
    }
    
    public GameHandler getGameSettings(){
        return gameSetting;
    }
    
    
    
}
