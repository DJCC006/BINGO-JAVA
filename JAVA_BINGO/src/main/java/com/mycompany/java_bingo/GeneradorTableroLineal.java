/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java_bingo;

/**
 *
 * @author David
 */
public class GeneradorTableroLineal {
    //Configuracion de distintas figuras que puede haber en 
    private boolean[][] T1= new boolean[5][5];
    private boolean[][] T2= new boolean[5][5];
    private boolean[][] T3= new boolean[5][5];
    private boolean[][] FullHouse= new boolean[5][5];
    public GeneradorTableroLineal(){
        //generacion de figura de tablero 1
        T1[0][0]=true;
        T1[1][1]=true;
        T1[2][2]=true;
        T1[3][3]=true;
        T1[4][4]=true;
        
        
        //generacion de figura de tablero 2
        T2[0][4]=true;
        T2[1][3]=true;
        T2[2][2]=true;
        T2[3][1]=true;
        T2[4][0]=true;
        
        
        //Generacionn de fitura de tablero 3
        for(int i=0; i<T3.length; i++){
            for(int j=0; j<T3.length; j++){
                if(i==0 && j==0){
                    T3[i][j]=true;
                }
                
                if(i==1 && j==1){
                    T3[i][j]=true;
                }
                
                if(i==2 && j==2){
                    T3[i][j]=true;
                }
                
                
                if(i==3 && j==3){
                    T3[i][j]=true;
                }
                
                if(i==4 && j==4){
                    T3[i][j]=true;
                }
                
                if(i==0 && j==4){
                    T3[i][j]=true;
                }
                
                if(i==1 && j==3){
                    T3[i][j]=true;
                }
                
                if(i==3 && j==1){
                    T3[i][j]=true;
                }
                
                if(i==4 && j==0){
                    T3[i][j]=true;
                }   
            }
        }
        
        //generador de tablero fullhouse
        for(int i=0; i<FullHouse.length; i++){
            for(int j=0; j<FullHouse.length; j++){
                FullHouse[i][j]=true;
            }
        }
        
        
    }
    
    public boolean[][] getT1(){
        return T1;
    }
    
    public boolean[][] getT2(){
        return T2;
    }
    
    public boolean[][] getT3(){
        return T3;
    }
    
    public boolean[][] getFullHouse(){
        return FullHouse;
    }
    
    
}
