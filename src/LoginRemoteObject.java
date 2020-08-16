
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Hashtable;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Đỗ Nam
 */
public class LoginRemoteObject extends UnicastRemoteObject implements ILogin{
    Hashtable<String,String> ht = new Hashtable<String,String>();
    
    public LoginRemoteObject() throws RemoteException{
        String str;
        try{
            BufferedReader br = new BufferedReader(new FileReader("data.txt"));
            while((str = br.readLine())!=null){
                String arr[] = str.split("@");
                ht.put(arr[0], arr[1]);
            }
        }catch (IOException ie){
            System.err.println("Exception occured - " + ie.getMessage());
        }
    }
    public boolean check(String user, String pass) throws RemoteException{
        if(ht.containsKey(user)){
            if(ht.get(user).equalsIgnoreCase(pass))
                return true;
        }
        return false;
    }
}
