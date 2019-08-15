package com.hit.services;

import java.io.IOException;

import com.hit.dm.DataModel;

public class CacheUnitController<T> extends Object
{
	
	private final CacheUnitService<T> cacheUS;

	public CacheUnitController() throws IOException{
		this.cacheUS = new CacheUnitService<>();
	}
	 
	
	
	
	public boolean update(DataModel<T>[] dataModels)
	{	
		return cacheUS.update(dataModels);
		
	}
	
	
	

	public boolean delete(DataModel<T>[] dataModels) throws Exception
	{
		return cacheUS.delete(dataModels);
		
	}
	
	
	
	
	public  DataModel<T>[] get(DataModel<T>[] dataModels) throws Exception
	{
		return cacheUS.get(dataModels);
		
	}
	
}
