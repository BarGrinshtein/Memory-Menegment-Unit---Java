package com.hit.util;

import java.util.Hashtable;

public class Statistic 
{
	 private String cacheAlgo;
	    private Hashtable<String,Integer> data;

	    private static Statistic instance = null;

	    private  Statistic()
	    {
	      data = new Hashtable<> ();
	      data.put ("capacity",0);
	      data.put("totalReqs",0);
	      data.put ("modelReqs",0);
	      data.put ("modelSwap",0);

	    }
	    public static  Statistic getInstance()
	    {
	        if(instance == null)
	        {
	            instance = new  Statistic ();
	        }

	        return instance;
	    }

	    public synchronized void addRequest()
	    {
	        int temp = data.get ("totalReqs");
	        data.put ("totalReqs",temp+1);
	    }

	    public synchronized void addModelsRequest()
	    {
	        int temp = data.get ("modelReqs");
	        data.put ("modelReqs",temp+1);
	    }

	    public synchronized void addModelSwap()
	    {
	        int temp = data.get ("modelSwap");
	        data.put ("modelSwap",temp+1);
	    }

	    public synchronized void setCapacity(int capacity)
	    {
	        data.put ("capacity",capacity);
	    }


	    //getters and setters
	    public String getCacheAlgo()
	    {
	        return cacheAlgo;
	    }

	    public void setCacheAlgo(String cacheAlgo)
	    {
	        this.cacheAlgo = cacheAlgo;
	    }

	    public Hashtable<String, Integer> getData()
	    {
	        return data;
	    }

	    public void setData(Hashtable<String, Integer> data)
	    {
	        this.data = data;
	    }
}
