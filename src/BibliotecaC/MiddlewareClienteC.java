/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src.BibliotecaC;

import src.BibliotecaA.MiddlewareServidorA;
import src.BibliotecaA.RMIInterfaceA;
import src.BibliotecaB.MiddlewareServidorB;
import src.BibliotecaB.RMIInterfaceB;
import java.io.FileWriter;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

/**
 *
 * @author Raikjars
 */
public class MiddlewareClienteC {
    
    public MiddlewareClienteC() {
    }
 
    public String EncontrarVol(String nombre, String destino, FileWriter logs)throws MalformedURLException, RemoteException, NotBoundException, ParserConfigurationException, SAXException, IOException {
        if("B".equals(destino)) {
            RMIInterfaceB interfaceB = (RMIInterfaceB) Naming.lookup("rmi://"+src.Ips.B+"/BibliotecaB"); //ruta donde busca objeto
            MiddlewareServidorB mB = new MiddlewareServidorB();
            
            //Imprimiendo traza y escribiendo logs en txt
            generateLogMCA(logs, "B: GetTitle " , nombre );
 
            
            //LLAMADA A MIDDLEWARE SERVIDOR B EN Z39
            return mB.getTitle(interfaceB, nombre, "A",logs); }
        else {
            RMIInterfaceA interfaceC = (RMIInterfaceA) Naming.lookup("rmi://"+src.Ips.A+"/BibliotecaA"); //ruta donde busca objeto
            MiddlewareServidorA mA = new MiddlewareServidorA();
            
            //Imprimiendo traza y escribiendo logs en txt
            generateLogMCA(logs, "A: GetTitle " , nombre );
            
            //LLAMADA A MIDDLEWARE SERVIDOR C EN Z39
            return mA.getTitle(interfaceC, nombre, "A",logs); 
        }
    }
    
    public String EncontrarAutor(String nombre, String destino, FileWriter logs)throws MalformedURLException, RemoteException, NotBoundException , ParserConfigurationException, SAXException, IOException{
        if("B".equals(destino)) {
            RMIInterfaceB interfaceB = (RMIInterfaceB) Naming.lookup("rmi://"+src.Ips.B+"/BibliotecaB"); //ruta donde busca objeto
            MiddlewareServidorB mB = new MiddlewareServidorB();
            
            //Imprimiendo traza y escribiendo logs en txt
            generateLogMCA(logs, "B: GetAuthor " , nombre );
            
            //LLAMADA A MIDDLEWARE SERVIDOR B EN Z39
            return mB.getAuthor(interfaceB, nombre, "A",logs); }
        else {
            RMIInterfaceA interfaceC = (RMIInterfaceA) Naming.lookup("rmi://"+src.Ips.A+"/BibliotecaA"); //ruta donde busca objeto
            MiddlewareServidorA mA = new MiddlewareServidorA();
            
            //Imprimiendo traza y escribiendo logs en txt
            generateLogMCA(logs, "A: GetAuthor " , nombre );
            
            //LLAMADA A MIDDLEWARE SERVIDOR C EN Z39
            return mA.getAuthor(interfaceC, nombre, "A",logs); 
        }
    }
    
    public static void generateLogMCA(FileWriter logs, String letter, String data) throws MalformedURLException, RemoteException, NotBoundException , ParserConfigurationException, SAXException, IOException{
        String actualDate = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date());
        
        logs.write("[" + actualDate + "]" + " Middleware Cliente C traduciendo peticion a Z39"+ " | ");
        System.out.println("[" + actualDate + "]" + " Middleware Cliente C traduciendo peticion a Z39");
        
        logs.write("[" + actualDate + "]" + " Peticion traducida. LLamando a Middleware de Servidor " + letter + data + " | ");
        System.out.println("[" + actualDate + "]" + " Peticion traducida. LLamando a Middleware de Servidor" + letter  + data );
    }
}