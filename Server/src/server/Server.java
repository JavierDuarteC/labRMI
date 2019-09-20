/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Calendar;
import server.model.Estacion;

/**
 *
 * @author ALAPTOP
 */
public class Server extends UnicastRemoteObject implements Interface1 {

    private static final long serialVersionUID = 1L;
    private static final ArrayList<Estacion> listaEstaciones = new ArrayList();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        listaEstaciones.add(new Estacion("Provenza",8));
        listaEstaciones.add(new Estacion("Cañaveral",5));
        listaEstaciones.add(new Estacion("Lagos", 10));
        listaEstaciones.add(new Estacion("La estancia", 5));
        listaEstaciones.add(new Estacion("Palmichal", 4));
        listaEstaciones.add(new Estacion("Españolita", 32));
        
        try {
            Server obj = new Server();
            Interface1 stub = (Interface1) UnicastRemoteObject.exportObject(obj, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("Interface1", stub);
            
            
            
            System.out.println("Server ready");
        } catch (AlreadyBoundException | RemoteException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public String calcularTiempo(String parada) throws RemoteException {
        int index = 0;
        for (int i = 0; i < listaEstaciones.size(); i++) {
            if(listaEstaciones.get(i).getNombre().equals(parada)){
                index = i;
                break;
            }
        }
        if(listaEstaciones.get(index).getUltimoReporte() != null){
            return "Perdio el bus lok";
        }else{
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(listaEstaciones.get(index).getUltimoReporte());
            calendar.add(Calendar.MINUTE, listaEstaciones.get(index).getTiempoSiguiente());
            return calendar.getTime().toString();
        }
        
       
    }

    @Override
    public void reportarParada(String parada) throws RemoteException {
        int index = 0;
        for (int i = 0; i < listaEstaciones.size(); i++) {
            if(listaEstaciones.get(i).getNombre().equals(parada)){
                index = i;
                break;
            }
        }
        listaEstaciones.get(index).setUltimoReporte(Calendar.getInstance().getTime());
        if(index == listaEstaciones.size()-1){
            resetTimes();
        }
    }
    
    private void resetTimes(){
        for (int i = 0; i < listaEstaciones.size(); i++) {
            listaEstaciones.get(i).setUltimoReporte(null);
        }
    }

}
