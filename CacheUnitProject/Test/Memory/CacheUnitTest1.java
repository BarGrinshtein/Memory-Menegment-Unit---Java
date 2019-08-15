package com.hit.memory;

import java.io.IOException;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.hit.algorithm.IAlgoCache;
import com.hit.algorithm.LRUAlgoCacheLmpl;
import com.hit.dao.DaoFileImpl;
import com.hit.dao.IDao;
import com.hit.dm.DataModel;

import junit.framework.TestCase;

class CacheUnitTest1 extends TestCase
{

	@Test
	void test() throws IOException
	{
		IAlgoCache<Long, DataModel<String>> algoTest = new LRUAlgoCacheLmpl<>(5);
		IDao<Long, DataModel<String>> daoTest = new DaoFileImpl<>("src\\main\\resources\\data.txt");
		CacheUnit<String> cacheUnitTest = new CacheUnit<>(algoTest, daoTest);
		Long[] ids = {(long)1,(long)2,(long)3,(long)4,(long)5,(long)6};
		DataModel<String> data1 = new DataModel<String>((long)1,"1");
		DataModel<String> data2 = new DataModel<String>((long)2,"2");
		DataModel<String> data3 = new DataModel<String>((long)3,"3");
		DataModel<String> data4 = new DataModel<String>((long)4,"4");
		DataModel<String> data5 = new DataModel<String>((long)5,"5");
		DataModel<String> data6 = new DataModel<String>((long)6,"6");
		
		@SuppressWarnings("unchecked")
		DataModel<String>[] dataArray = new DataModel[6];
		
		dataArray[0] = data1;
		dataArray[1] = data2;
		dataArray[2] = data3;
		dataArray[3] = data4;
		dataArray[4] = data5;
		dataArray[5] = data6;
		dataArray[0].setId((long)1);
		
		
		cacheUnitTest.putDataModels(dataArray);
		
		DataModel<String>[] dataModelArray = null;
		try {
			dataModelArray = cacheUnitTest.getDataModels(ids);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		Assert.assertEquals(data1.getContent(),dataModelArray[0].getContent());
		Assert.assertEquals(data2.getContent(),dataModelArray[1].getContent());
		Assert.assertEquals(data3.getContent(),dataModelArray[2].getContent());
		Assert.assertEquals(data4.getContent(),dataModelArray[3].getContent());
		Assert.assertEquals(data5.getContent(),dataModelArray[4].getContent());
		
		Assert.assertEquals(data1.getId(),dataModelArray[0].getId());
		Assert.assertEquals(data2.getId(),dataModelArray[1].getId());
		Assert.assertEquals(data3.getId(),dataModelArray[2].getId());
		Assert.assertEquals(data4.getId(),dataModelArray[3].getId());
		Assert.assertEquals(data5.getId(),dataModelArray[4].getId());
		
		Long[] ids2 = {(long)1,(long)2};
		cacheUnitTest.removeDataModels(ids2);
		
		try {
			dataModelArray = cacheUnitTest.getDataModels(ids);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Assert.assertEquals(data1.getContent(), dataModelArray[0].getContent());
		Assert.assertEquals(data2.getContent(), dataModelArray[1].getContent());
		Assert.assertEquals(data3.getContent(), dataModelArray[2].getContent());
		Assert.assertEquals(data4.getContent(), dataModelArray[3].getContent());
		Assert.assertEquals(data5.getContent(), dataModelArray[4].getContent());
	}

}
