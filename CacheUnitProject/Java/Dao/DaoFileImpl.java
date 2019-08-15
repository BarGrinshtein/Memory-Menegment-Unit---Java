package com.hit.dao;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Scanner;
import com.hit.dm.DataModel;




public class DaoFileImpl<T> extends Object implements IDao<Long,DataModel<T>>
{
	
	 ObjectInputStream inputStream;
	    ObjectOutputStream outputStream;

	    String fileName;

	    Hashtable<Long, DataModel<T>> hashtable;


	    public DaoFileImpl()
	    {
	        fileName = "out.txt";
	        hashtable = new Hashtable<> ();

	    }

	    public DaoFileImpl(String fileName)
	    {
	        this.fileName = fileName;
	        hashtable = new Hashtable<> ();
	    }

	    @Override
	    public void delete(DataModel<T> entity)
	    {
	        openInputStream ();
	        hashtable.remove (entity.getId ());
	        openOutputStream ();

	    }

	    @Override
	    public DataModel<T> find(Long id)
	    {
	        DataModel resultModel = null;

	        openInputStream ();

	        resultModel = hashtable.get (id);

	        openOutputStream ();
	        closeStreams ();

	        return resultModel;
	    }


	    @Override
	    public void save(DataModel<T> entity)
	    {
	        openInputStream ();
	        hashtable.put (entity.getId (),entity);

	        openOutputStream ();
	        closeStreams ();
	    }

	    private void openInputStream()
	    {
	        try
	        {
	            inputStream = new ObjectInputStream (new FileInputStream (fileName));

	            hashtable = (Hashtable<Long, DataModel<T>>)inputStream.readObject ();

	        } catch (IOException e)
	        {
	            e.printStackTrace ();
	        } catch (ClassNotFoundException e)
	        {
	            e.printStackTrace ();
	        }


	    }

	    private void openOutputStream()
	    {
	        try
	        {
	            outputStream = new ObjectOutputStream (new FileOutputStream (fileName,false));
	            outputStream.writeObject (hashtable);
	        } catch (IOException e)
	        {
	            e.printStackTrace ();
	        }


	    }

	    private void closeStreams()
	    {
	        try
	        {
	            outputStream.close ();
	            inputStream.close ();
	        } catch (IOException e)
	        {
	            e.printStackTrace ();
	        }
	    }
}
