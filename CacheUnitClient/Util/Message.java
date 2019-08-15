package com.hit.util;

public class Message
{
	private String sentIdentifier;
    private String messege;
    private String extra;
    
    public Message(String sentId, String msg) 
    {
    	this(sentId, msg, null);
    }

    public Message(String sentId, String msg, String extra)
    {
        this.sentIdentifier = sentId;
        this.messege = msg;
        this.extra = extra;
    }

    public String getSentIdentifier()
    {
        return sentIdentifier;
    }

    public String getMessege()
    {
        return messege;
    }

	public String getExtra() {
		return extra;
	}
}
