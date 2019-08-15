package com.hit.controller;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;


import com.hit.model.Model;
import com.hit.util.Message;
import com.hit.view.View;



public class CacheUnitController implements Controller 
{
	
	 private Model model;
	    private View view;
	    private static String id;

	    public CacheUnitController(Model model, View view)
	    {
	        this.model = model;
	        this.view = view;
	    }

	    @Override
	    public void update(Observable o, Object arg)
	    {
	        id = null;
	        Message update = (Message) arg;

	        if(update.getSentIdentifier ().equals ("view"))
	        {
	        	model.updateModelData(update);
	        }

	        if(update.getSentIdentifier ().equals ("model-load"))
	        {
	            view.updateUIData(new Message("load","true"));
	        }

	        if(update.getSentIdentifier ().equals ("stats"))
	        {
	            view.updateUIData (update);
	        }

	        if(update.getSentIdentifier().equals("net-crash"))
	        {
	            view.updateUIData(new Message("load","false"));
	        }

	    }

}
