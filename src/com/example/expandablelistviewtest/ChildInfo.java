
package com.example.expandablelistviewtest;

import android.graphics.Bitmap;

/**
 * 
 * @function Child实体类
 * @author Xzhi
 * @time 2017-3-10 下午5:26:22 
 * Copyright (c) 2017 Xzhi All Rights Reserved.
 *
 */
public class ChildInfo
{
	String childName;
	Bitmap childPic;
	
	public ChildInfo(String childName, Bitmap childPic)
	{
		super();
		this.childName = childName;
		this.childPic = childPic;
	}

	public String getChildName()
	{
		return childName;
	}
	
	public void setChildName(String childName)
	{
		this.childName = childName;
	}
	
	public Bitmap getChildPic()
	{
		return childPic;
	}
	
	public void setChildPic(Bitmap childPic)
	{
		this.childPic = childPic;
	}
	
}
