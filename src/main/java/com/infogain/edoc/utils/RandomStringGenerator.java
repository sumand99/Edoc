package com.infogain.edoc.utils;

import java.math.BigInteger;
import java.util.Random;

public class RandomStringGenerator 
{
	private static Random random = new Random();

	  public static String generateString() {
	    return new BigInteger(30, random).toString(32);
	  }
}
