package com.hit.dm;


import java.io.Serializable;

//import java.util.*;

@SuppressWarnings("serial")
public class DataModel<T> extends Object implements Serializable
{
	private T content;
	private Long id;
	
	public DataModel (Long id, T content)
	{
		this.content = content;
		this.id = id;	
	}
	
	

	public void setContent (T content)
	{
		this.content= content;
	}
	
	public T getContent()
	{
		return content;
	}

	public void setId(Long id)
	{
		this.id = id;
	}
	
	public Long getId()
	{
		return id;
	}
	
	public boolean equals (Object obj)
	{
		if (!(obj instanceof DataModel<?>)) 
		{
			return false;
		}

		DataModel<?> otherModel = (DataModel<?>) obj;

		if (this.id != otherModel.id)
		{
			return false;
		}

		return true;
	}
	
	@Override
	public String toString()
	{
		return "DataModel [id=" + id + ", content=" + content + "]";
	}
}

