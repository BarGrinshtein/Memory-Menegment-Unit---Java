package com.hit.dm;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.hit.algorithm.IAlgoCache;
import com.hit.algorithm.LRUAlgoCacheLmpl;
import com.hit.dao.DaoFileImpl;
import com.hit.dao.IDao;
import com.hit.memory.CacheUnit;

import junit.framework.TestCase;

class DaoFileTest1 extends TestCase
{

	@Test
	void test() throws IOException {
		IAlgoCache<Long, DataModel<String>> algoTest = new LRUAlgoCacheLmpl<>(5);
		IDao<Long, DataModel<String>> daoTest = new DaoFileImpl<>("src\\main\\resources\\data.txt");
		CacheUnit<String> cacheUnitTest = new CacheUnit<String>(algoTest, daoTest);
		Long[] ids = {(long)1,(long)2,(long)3,(long)4,(long)5,(long)6};
		DataModel<String> data1 = new DataModel<String>((long)1,"1");
		DataModel<String> data2 = new DataModel<String>((long)2,"2");
		DataModel<String> data3 = new DataModel<String>((long)3,"3");
		DataModel<String> data4 = new DataModel<String>((long)4,"4");
		DataModel<String> data5 = new DataModel<String>((long)5,"5");
		DataModel<String> data6 = new DataModel<String>((long)6,"6");
		
		DataModel<String>[] dataArray = new DataModel[6];
		
		dataArray[0] = data1;
		dataArray[1] = data2;
		dataArray[2] = data3;
		dataArray[3] = data4;
		dataArray[4] = data5;
		dataArray[5] = data6;
		
		cacheUnitTest.putDataModels(dataArray);
		DataModel<String>[] dataModelArr = null;
		try {
			dataModelArr = cacheUnitTest.getDataModels(ids);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertEquals(data1.getId(),dataModelArr[0].getId());
		Assert.assertEquals(data1.getContent(),dataModelArr[0].getContent());
		
		
	}

}
