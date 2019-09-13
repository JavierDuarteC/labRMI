/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Date;

/**
 *
 * @author ALAPTOP
 */
public interface Interface1 extends Remote{
    
    public float calcularTiempo(String parada) throws RemoteException;
    public boolean reportarParada(String parada) throws RemoteException;
}
