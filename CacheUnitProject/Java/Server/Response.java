package com.hit.server;

import java.util.Map;

public class Response<T> extends Object
{
	private Map<String, String> headers;
	private T body;
	
	public Response( Map<String,String> headers, T body) 
	{
		this.headers = headers;
		this.body = body;
	}
	
	public T getBody()
	{
		return this.body;
	}
	
	public Map<String,String> getHeaders()
	{
		return this.headers;
	}
	
	public void setBody(T body)
	{
		this.body = body;
	}
	
	public void setHeaders(Map<String,String> headers)
	{
		this.headers = headers;
	}
	
	public String toString()
	{
		return "Response: headers " + headers + ", body " + body;
	}
}
