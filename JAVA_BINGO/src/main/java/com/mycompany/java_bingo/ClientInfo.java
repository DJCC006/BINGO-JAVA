/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java_bingo;

import java.net.InetAddress;

/**
 *
 * @author David
 */
public class ClientInfo {
    private InetAddress address;
    private int port;
    private String username;
    
    
    public ClientInfo(InetAddress address, int port, String username){
        this.address= address;
        this.port= port;
        this.username = username;
    }
    
    public InetAddress getAddress(){
        return address;
    }
    
    public int getPort(){
        return port;
    }
    
    public String getUsername(){
        return username;
    }
    
    @Override
    public boolean equals(Object o){
        if(this == o) return true;
        if(o == null || getClass() != o.getClass()) return false;
        ClientInfo that = (ClientInfo) o;
        return port == that.port && address.equals(that.address);
    }
    
    @Override
    public int hashCode(){
        return 31 * address.hashCode() + port;
    }
            
}
