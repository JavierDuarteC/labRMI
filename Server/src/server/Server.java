/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package server;

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
        listaEstaciones.add(new Estacion("Provenza",8f));
        listaEstaciones.add(new Estacion("Cañaveral",5f));
        listaEstaciones.add(new Estacion("Lagos", 10f));
        listaEstaciones.add(new Estacion("La estancia", 5f));
        listaEstaciones.add(new Estacion("Palmichal", 4f));
        listaEstaciones.add(new Estacion("Españolita", 32f));
        
        try {
            Server obj = new Server();
            Interface1 stub = (Interface1) UnicastRemoteObject.exportObject(obj, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.bind("Interface1", stub);
            
            
            
            System.out.println("Server ready");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public float calcularTiempo(String parada) throws RemoteException {
       return 1f;
    }

    @Override
    public void reportarParada(String parada) throws RemoteException {
        int index = 0;
        for (int i = 0; i < listaEstaciones.size(); i++) {
            if(listaEstaciones.get(i).getNombre()==parada){
                index = i;
                break;
            }
        }
        listaEstaciones.get(index).setUltimoReporte(Calendar.getInstance().getTime());
    }

}
