/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java_bingo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author David
 */
public class Cliente {
    private DatagramSocket clientSocket;
    private InetAddress serverAddress;
    private int serverport;
    private String username;
    
    private String msgalmacenado;//Valido solo para jugadores
    
    
    public Cliente(String username, String hostname, int port){
        try{
            this.username = username;
            this.serverAddress = InetAddress.getByName(hostname);
            this.serverport = port;
            this.clientSocket = new DatagramSocket();
            
        }catch(IOException e){
            e.printStackTrace();
        }
    }
    
    
    public void start(){
        sendInitialMessage();
        
        new Thread(this::receiveMessages).start();
        
        //new Thread(this::sendMessages).start();
    }
    
    
    private void sendInitialMessage(){//Mensaje inicial que se manda a la hora de conectarse al servidor
        try{
            String messageToSend = this.username + " se ha unido";
            byte[] buffer =messageToSend.getBytes();
            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, serverAddress, serverport);
            clientSocket.send(packet);
        }catch(IOException e){
            System.out.println("Error al enviar el mensaje incial");
        }
    }
            
    
    
    private void receiveMessages(){
        try{
            byte[] buffer = new byte[1024];
            while(true){
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                clientSocket.receive(packet);
                String message = new String(packet.getData(), 0, packet.getLength());
                System.out.println("\n"+message);
            }
            
        }catch(IOException e){
            System.out.println("Error al recibir mensajes");
        }
    }
    
    public void sendMessages(String message){
        
        
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            
                String userMessage = message;
                String messageToSend = this.username +":"+userMessage;
                byte[] buffer = messageToSend.getBytes();
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length,serverAddress, serverport);
                clientSocket.send(packet);
            
        }catch(IOException e){
            System.out.println("Error al enviar mensajes.");
        }
        
        
        /*
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))){
            while(true){
                String userMessage = reader.readLine();
                String messageToSend = this.username +":"+userMessage;
                byte[] buffer = messageToSend.getBytes();
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length,serverAddress, serverport);
                clientSocket.send(packet);
            }
        }catch(IOException e){
            System.out.println("Error al enviar mensajes.");
        }
        */
        
    }
    
    
    
    public void almacenarMensaje(String Message){//metodo que lamacena el mensaje para luego sacarlo y usarlo
        msgalmacenado=Message;
    }
    
    
    
    public String getMensaje(){
        return msgalmacenado;
    }
    
    
    public static void main(String[] args) {
        /*
        System.out.println("Ingrese su nombre de usuario: ");
        Scanner lea = new Scanner(System.in);
        lea.useDelimiter("\n");
        String usuario= lea.next();
        Cliente client = new Cliente(usuario, "localhost", 7775);
        client.start();
*/
    }
    
}
