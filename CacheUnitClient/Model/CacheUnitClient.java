package com.hit.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class CacheUnitClient
{
	
 private Socket socket;
	    private ObjectOutputStream outputStream;
	    private ObjectInputStream inputStream;


	    public CacheUnitClient()
	    {

	    }


	    public String send(String request)
	    {

	        try
	        {
	            socket  = new Socket ("localhost", 34567);
	            outputStream = new ObjectOutputStream (socket.getOutputStream ());
	            inputStream = new ObjectInputStream (socket.getInputStream ());
	        } catch (IOException e)
	        {
	            if(e instanceof  ConnectException)
	            {
	                return ("net-crash");
	            }

	            e.printStackTrace ();
	        }


	        String o = "";


	        try
	        {
	            outputStream.writeObject (request);
	            outputStream.flush();
	            o = (String) inputStream.readObject ();

	        } catch (IOException e)
	        {
	            e.printStackTrace ();
	        } catch (ClassNotFoundException e)
	        {
	            e.printStackTrace ();
	        }

	        try
	        {
	            socket.close ();
	            inputStream.close ();
	            outputStream.close ();
	        } catch (IOException e)
	        {
	            e.printStackTrace ();
	        }

	        return o;
	    }

}
