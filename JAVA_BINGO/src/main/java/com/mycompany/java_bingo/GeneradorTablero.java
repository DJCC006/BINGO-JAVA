/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java_bingo;
import java.util.Random;
/**
 *
 * @author David
 */
public class GeneradorTablero {
    int[][] Tablero = new int[5][5];
    Random spawner = new Random();
    
    
    public GeneradorTablero(){
        for(int columna =0; columna<Tablero.length; columna++){
            
            int controller =columna;
            switch(controller){
                case 0: //generar 1-15
                    int max=15;
                    int min=1;
                    for(int fila=0; fila<Tablero.length; fila++){
                       int num;
                        boolean repetido;
                                
                        do{
                            num =spawner.nextInt((max-min)+1)+min;
                            repetido=false;
                            
                            //revision de si ya existe en la columna
                            for(int i=0; i<fila; i++){
                                if(num==Tablero[i][columna]){
                                    repetido=true;
                                    break;
                                }
                            }
                        }while(repetido);
                        Tablero[fila][columna]=num;      
                        
                    }
                    
                    break;
                    
                case 1: //generar 16-30
                    int max1=30;
                    int min1=16;
                    
                    for(int fila=0; fila<Tablero.length; fila++){
                        int num;
                        boolean repetido;
                                
                        do{
                            num =spawner.nextInt((max1-min1)+1)+min1;
                            repetido=false;
                            
                            //revision de si ya existe en la columna
                            for(int i=0; i<fila; i++){
                                if(num==Tablero[i][columna]){
                                    repetido=true;
                                    break;
                                }
                            }
                        }while(repetido);
                        Tablero[fila][columna]=num;           
                    }
               
                    break;
                    
                case 2://generar 31-45
                    for(int fila =0; fila<Tablero.length; fila++){
                        if(columna == 2 && fila ==2){
                            Tablero[fila][columna]= 777;
                        }else{
                            int max2=45;
                            int min2=31;
                            int num;
                            boolean repetido;

                            do{
                                num =spawner.nextInt((max2-min2)+1)+min2;
                                repetido=false;

                                //revision de si ya existe en la columna
                                for(int i=0; i<fila; i++){
                                    if(num==Tablero[i][columna]){
                                        repetido=true;
                                        break;
                                    }
                                }
                            }while(repetido);
                            Tablero[fila][columna]=num;      
                            }
                    }
                    break;
                    
                case 3://generar 46 -60
                    int max3=60;
                    int min3=46;
                    for(int fila=0; fila<Tablero.length; fila++){
                       
                        int num;
                        boolean repetido;
                                
                        do{
                            num =spawner.nextInt((max3-min3)+1)+min3;
                            repetido=false;
                            
                            //revision de si ya existe en la columna
                            for(int i=0; i<fila; i++){
                                if(num==Tablero[i][columna]){
                                    repetido=true;
                                    break;
                                }
                            }
                        }while(repetido);
                        Tablero[fila][columna]=num;      
                    }
                    
                    break;
                    
                case 4://generar 61-75
                    int max4=75;
                    int min4=61;
                    for(int fila=0; fila<Tablero.length; fila++){
                        int num;
                        boolean repetido;
                                
                        do{
                            num =spawner.nextInt((max4-min4)+1)+min4;
                            repetido=false;
                            
                            //revision de si ya existe en la columna
                            for(int i=0; i<fila; i++){
                                if(num==Tablero[i][columna]){
                                    repetido=true;
                                    break;
                                }
                            }
                        }while(repetido);
                        Tablero[fila][columna]=num;      
                    }
                    break; 
            }
        }
    }
    
    
    public void Imprimir(){
        for(int fila =0; fila<Tablero.length; fila++){
            for(int columna=0; columna<Tablero.length; columna++){
                System.out.print("|"+Tablero[fila][columna]+"|");
            }
            System.out.println();
        }
    }
    
    
}
