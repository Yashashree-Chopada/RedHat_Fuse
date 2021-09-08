package com.mycompany.camel;

import java.util.List;

public class ResultHandler 
{
	public void printResult(List l)
	{
		for(int i=0;i<l.size();i++)
		{
			System.out.println(l.get(i));
		}
	}
}
