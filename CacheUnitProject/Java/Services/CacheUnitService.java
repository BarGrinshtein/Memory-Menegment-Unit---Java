package com.hit.services;

import java.io.IOException;

import com.hit.algorithm.IAlgoCache;
import com.hit.algorithm.LRUAlgoCacheLmpl;
import com.hit.dao.DaoFileImpl;
import com.hit.dao.IDao;
import com.hit.dm.DataModel;
import com.hit.memory.CacheUnit;
import com.hit.util.Statistic;


public class CacheUnitService<T> extends Object
{

	private final IAlgoCache<Long,DataModel<T>> algo;
	private final IDao<Long,DataModel<T>> dao;
	private final CacheUnit<T> cacheUnit;
	private Statistic dataStats;
	
	public CacheUnitService() throws IOException{
		this.algo = new LRUAlgoCacheLmpl<>(5);
		this.dao = new DaoFileImpl<>("data.txt");
		
		this.cacheUnit = new CacheUnit<>(algo, dao);
		
		dataStats = Statistic.getInstance();
		dataStats.setCacheAlgo ("LRU");
        dataStats.setCapacity (5);
	}




	public boolean delete(DataModel<T>[] dataModels) throws Exception
	{
		boolean isDelete = false;

        DataModel[] returnModels = null;
        Long[] ids = new Long[dataModels.length];

        for (int i = 0; i <dataModels.length ; i++)
        {
            ids[i] = dataModels[i].getId ();
        }

        returnModels = cacheUnit.getDataModels (ids);

        for(DataModel model: returnModels)
        {
            model.setContent (null);
        }

        if(returnModels.length > 0)
        {
            isDelete = true;
        }


        return isDelete;
	}
	@SuppressWarnings("null")
	public DataModel<T>[] get(DataModel<T>[] dataModels) throws Exception
	{	
	
		 Long[] ids = new Long[dataModels.length];
	        DataModel[] models = null;

	        for (int i = 0; i < dataModels.length; i++)
	        {
	            ids[i] = dataModels[i].getId ();
	        }

	        models = cacheUnit.getDataModels (ids);


	        return models;
	}


	public boolean update(DataModel<T>[] dataModels) {

		if (dataModels!=null)
		{
			cacheUnit.putDataModels(dataModels);
			return true;
		}
		return false;
	}
	


}
