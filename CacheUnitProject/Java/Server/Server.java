package com.hit.server;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.EventListener;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.hit.services.CacheUnitController;
import com.hit.util.Statistic;


public class Server  implements PropertyChangeListener, Runnable, EventListener
{
	
	protected ServerSocket serverSocket; 
	public int port;
	private CacheUnitController<String> controller; 
	private boolean isServerRunning; 
	private  Statistic dataStats;

public Server(int port) throws IOException {
	
	this.port = port;
	isServerRunning = false;
	controller =  new CacheUnitController<String>();
	dataStats = Statistic.getInstance ();
}

	@Override
	public void run() {
		isServerRunning = true;
		try {
			serverSocket = new ServerSocket(port);
			

			System.out.println("Starting server...");
			Executor ex = Executors.newFixedThreadPool(5); 

			Socket socket = null;
			
			while(isServerRunning) 
			{
				socket = serverSocket.accept();
				dataStats.addRequest ();
				ex.execute(new HendleRequest<String>(socket,controller));
			}


		} catch (IOException e) {

			e.printStackTrace();
		}
		finally {

			if(serverSocket!=null && isServerRunning==false)
			{
				try {
					serverSocket.close();
					System.out.println("its realy close");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
	}

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		String action =(String) evt.getNewValue();

		switch(action) {
		case "start":
			if(isServerRunning == false) {
				isServerRunning = true;
				new Thread(this).start();
				break;
			}
			else 
				System.out.println("Server is already ON\n");
			break;
		case "stop":
			if(isServerRunning == false) 
				System.out.println("Server is already OFF\n");
			else {

				isServerRunning=false;

				try {
					serverSocket.close();
					System.out.println("its realy close");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			break;
		default:
			System.out.println("Not a valid command");
			break;
		}
		
	}
	
}