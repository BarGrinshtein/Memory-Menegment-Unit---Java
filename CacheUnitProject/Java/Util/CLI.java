package com.hit.util;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.Scanner;

public class CLI  implements Runnable
{
	private PropertyChangeSupport prpopetySupport = new PropertyChangeSupport(this);
	private StringBuilder stringBuilder = new StringBuilder("");
	
	private Scanner reader;
	
	
	private InputStream in;
	private OutputStream out;
	
	public CLI(InputStream in, OutputStream out)
	{
		reader = new Scanner(in);
		this.in = in;
		this.out = out;
	}
	

	
	@Override
	public void run() 
	{
		System.out.println("Please enter commend:");
		String userInput;
		
		while(true)
		{
			userInput = reader.nextLine().toLowerCase();
					if(userInput.equals("start"))
					{
						
						prpopetySupport.firePropertyChange("Command",null,userInput);
						
					
					}
							
					else if(userInput.equals("stop"))
					{
						prpopetySupport.firePropertyChange("Command",null,userInput);
						
					}
					else
					{
						write("not a vaild commend");
					}	
			
		
		}
		
	}
	
	public void addPropertyChangeListener(PropertyChangeListener pcl)
	{
		prpopetySupport.addPropertyChangeListener(pcl);
	}
	

	public void removePropertyChangeListener(PropertyChangeListener pcl)
	{
		prpopetySupport.removePropertyChangeListener(pcl);
	}
	
	public void write(String string)
	{
		System.out.println(stringBuilder.append(string));
		stringBuilder.delete(0, string.length());
	}
}


