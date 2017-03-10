
package com.example.expandablelistviewtest;

/**
 * 
 * @function Group实体类
 * @author Xzhi
 * @time 2017-3-10 上午9:55:41 
 * Copyright (c) 2017 Xzhi All Rights Reserved.
 *
 */
public class GroupInfo
{
	String name;
	int count;
	
	public GroupInfo(String name)
	{
		super();
		this.name = name;
		this.count = count;
	}
	
	public String getName()
	{
		return name;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public int getCount()
	{
		return count;
	}
	
	public void setCount(int count)
	{
		this.count = count;
	}
	
}
