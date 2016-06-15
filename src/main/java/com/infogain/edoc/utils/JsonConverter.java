package com.infogain.edoc.utils;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonConverter 
{
	public static String getJson(final Object object)
    {
    	String jsonText = null;
    	final ObjectMapper mapper = new ObjectMapper();
    	try
    	{
    		jsonText = mapper.writeValueAsString(object);
    	}
    	catch(Exception e)
    	{
    		e.printStackTrace();
    	}
    	return jsonText;
    }
}
