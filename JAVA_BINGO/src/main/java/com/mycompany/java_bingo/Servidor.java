/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java_bingo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author David
 */
public class Servidor extends Thread {
    private DatagramSocket datagramSocket;
    private int PORT =7775;
    private List<ClientInfo> clients = new ArrayList<>();//Lleva el control de los usuarios conectados
    //private String almacenadorGameMode="";
    private VisualHost host;
    
    
    
    private byte[] buffer = new byte[1024];
    
    
    public Servidor(VisualHost host){
        this.host=host;
        try{
             this.datagramSocket= new DatagramSocket(PORT); //Luego ponerlo como un self-inicializadoer
                System.out.println("Servidor UDP iniciado en el puerto "+PORT);
        }catch(IOException e){
            e.printStackTrace();
        }
       
    }
    
    public void startServer(){
        Thread receiveThread = new Thread(this::receiveLoop);
        receiveThread.start();
    }
    
    
    private void receiveLoop(){
        try{
            
            while(true){
                //Creacion del almacenamiento de datos
                DatagramPacket recievePacket = new DatagramPacket(buffer, buffer.length);
                System.out.println("Esperando datos....");
                
                datagramSocket.receive(recievePacket);//Recibe datos
                
                //Extrae informacion del cliente que ha mandado los datos
                InetAddress clientAddress = recievePacket.getAddress();
                int clientPort = recievePacket.getPort();
                
                //Mensaje de cliente
                String clientMessage = new String(recievePacket.getData(), 0,recievePacket.getLength());
                
                 //Desglose de mensaje
                String[] parts = clientMessage.split(":",2);
                String username = parts[0];
                String messageContent = parts.length>1 ? parts[1] : "";
                
                
                
                ClientInfo newClient = new ClientInfo(clientAddress, clientPort, username);
                //adds nuevo jugador
                
                
                //---------
                if(messageContent.equals("SOLICITAR")){
                    boolean comprobador = verificarSala();
                    
                    if(comprobador==true){
                        String respuesta ="SERVIDOR:SALA LLENA";
                        byte[] responseData = respuesta.getBytes();
                        DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length, clientAddress, clientPort);
                        datagramSocket.send(responsePacket);
                    }else{
                        String respuesta ="SERVIDOR:OK";
                        byte[] responseData = respuesta.getBytes();
                        DatagramPacket responsePacket = new DatagramPacket(responseData, responseData.length, clientAddress, clientPort);
                        datagramSocket.send(responsePacket);
                        
                        if(!clients.contains(newClient)){
                            clients.add(newClient);
                            broadcastMessage("SERVIDOR: "+username +" ha entrado a la partida",newClient);
                        }  
                         
                    }
                    
                }
                
                
                //Filtracion de mensaje
                if(messageContent.equals("WIN")){
                    broadcastMessage("SERVIDOR:"+username+" HA GANADO LA PARTIDA",newClient);
                }
                
                
                
                
                if(messageContent.equalsIgnoreCase("quit")){
                    removeClient(newClient);
                    broadcastMessage("SERVIDOR: "+ username +" ha dejado la partida", newClient);
                    
                }else{
                    broadcastMessage(username +":"+messageContent, newClient);
                    
                }
                
                System.out.println("Mensaje de "+ username+" desde "+clientAddress.getHostAddress() + ":" +clientPort +": "+ messageContent);
                
                
                
            }
            
            
            
        }catch(IOException e){
            e.printStackTrace();
        }
        
        
        
    }
    
    
    private void broadcastMessage(String message, ClientInfo sender){
        byte[] sendData = message.getBytes();
        for(ClientInfo client : clients){
            if(!client.equals(sender)){
                try{
                    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, client.getAddress(), client.getPort());
                    datagramSocket.send(sendPacket);
                }catch(IOException e){
                    System.out.println("No se pudo enviar el mensaje al cliente "+client.getUsername());
                }
            }
          
        }
    }
    
    private void removeClient(ClientInfo clientToRemove){
        clients.remove(clientToRemove);
        System.out.println("Usuario Removido: "+ clientToRemove.getUsername());
    }

    
    public void closeServerSocket(){
        if(datagramSocket!= null && !datagramSocket.isClosed()){
            datagramSocket.close();
            System.out.println("SERVIDOR UDP CERRADO");
        }
    }
    
    public static void main(String[] args) {
        
        /*
        Servidor server = new Servidor();
        server.startServer();
*/
    }
    
    
    public List mandarPlayerList(){
        return clients;
    }
            
     public boolean verificarSala(){
        int countPlayersLive =clients.size()-1;//Menos uno ya que se decuenta el host
        int salaPlayers= host.getNumPlayers();
        if(countPlayersLive>=salaPlayers){
            return true;
        }else{
            return false;
        }
    }
    
    
    
}
