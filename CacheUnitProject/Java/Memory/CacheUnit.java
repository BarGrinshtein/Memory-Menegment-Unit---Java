package com.hit.memory;

import java.io.IOException;
import java.util.ArrayList;

import com.hit.algorithm.IAlgoCache;
import com.hit.dao.IDao;
import com.hit.dm.DataModel;
import com.hit.util.Statistic;


public class CacheUnit<T> extends Object
{
	
	private final IAlgoCache<Long,DataModel<T>> algo;
	private final IDao<Long,DataModel<T>> dao;
	private Statistic dataStats;
	
	public CacheUnit(IAlgoCache<Long,DataModel<T>> algo,
			IDao<Long,DataModel<T>> dao)
	{
		this.algo = algo;
		this.dao = dao;
		dataStats = Statistic.getInstance ();
	}

	@SuppressWarnings("null")
	public DataModel<T>[] getDataModels(Long[] ids) throws Exception
	{
		int indx=0;
		DataModel<T>[] list = new DataModel[ids.length];
		DataModel<T>tmpCache = new DataModel<T>(null, null);
		DataModel<T>tmpFile =  new DataModel<T>(null, null);;

		for(int i = 0; i<ids.length; i++)
		{
			if(ids[i] != null)
			{
				
				tmpCache = algo.getElement(ids[i]);
				if(tmpCache != null)
				{
					 
					list[indx++]= tmpCache;
				}
				else
				{
					
					tmpFile = dao.find(ids[i]);
					if(tmpFile != null)
					{
						//and put it in the hash
						algo.putElement(tmpFile.getId(),tmpFile);
						dataStats.addModelsRequest();
						list[indx++]= tmpFile;
					}
				}
			}
		}

		return list;
	}

	@SuppressWarnings("null")
	public DataModel<T>[] putDataModels(DataModel<T>[] datamodels)
	{
		DataModel<T> model;
		DataModel<T> models[] = new DataModel[datamodels.length];
		int j = 0;
		for(int i =0; i<datamodels.length; i++)
		{
			model = datamodels[i];

			if(model != null )
			{
				long dataModelID = model.getId();
				algo.putElement(dataModelID,  model);
				dao.save(model);
				dataStats.addModelSwap();
				models[j++] = model;
			}
		}
		return models;

	}

	public void removeDataModels(Long[] ids)
	{
		for(Long id : ids)
		{
			algo.removeElement(id);
		}
	}


}
