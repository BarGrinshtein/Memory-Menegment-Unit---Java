package com.hit.model;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.UnknownHostException;
import java.util.Observable;
import java.util.Scanner;

import com.hit.util.Message;



public class CacheUnitModel extends Observable implements Model
{
private	CacheUnitClient cuc = new CacheUnitClient();
	
	@Override
	public <T> void updateModelData(T t) 
	{ 
		Message requst = (Message) t;

        if(requst.getMessege().equals ("load"))
        {
            Scanner scanner = null;
            String req = null;

            try
            {
                scanner = new Scanner (new FileInputStream (requst.getExtra()));
                req = scanner.useDelimiter("\\A").next();
            } catch(Exception e) {
            	e.printStackTrace();
            }
            finally {
            	if (scanner != null) {
                    scanner.close(); 
            	}
            }
            
            if (req != null) {
                String send = cuc.send (req);
                setChanged ();

                if(send.equals("net-crash"))
                {
                    notifyObservers(new Message("net-crash","Failed"));
                }
                notifyObservers (new Message ("model-load",send));
            }
        }else if(requst.getMessege().equals ("stats"))
        {
            String dataFromServer = cuc.send ("stats");
            setChanged ();
            notifyObservers (new Message ("stats",dataFromServer));

        }

    }
}
