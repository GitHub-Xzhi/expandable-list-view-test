
package com.example.expandablelistviewtest;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MyAdapter extends BaseExpandableListAdapter
{
	/* 一级数据源 */
	List<GroupInfo> groupList;
	/* 二级数据源 */
	List<List<ChildInfo>> childList;
	Context context;
	
	public MyAdapter(List<GroupInfo> groupList, List<List<ChildInfo>> childList, Context context)
	{
		super();
		this.groupList = groupList;
		this.childList = childList;
		this.context = context;
	}
	
	class GroupView
	{
		TextView groupName;
		TextView count;
	}
	
	class ChildView
	{
		ImageView pic;
		TextView childName;
	}
	
	/************************************** Group ***************************************/
	
	public int getGroupCount()
	{
		return groupList.size();
	}
	
	public Object getGroup(int groupPosition)
	{
		return groupList.get(groupPosition);
	}
	
	public long getGroupId(int groupPosition)
	{
		return groupPosition;
	}
	
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent)
	{
		GroupView gView = null;
		if (convertView == null)
		{
			gView = new GroupView();
			convertView = LayoutInflater.from(context).inflate(R.layout.group_item, null);
			gView.groupName = (TextView) convertView.findViewById(R.id.gName);
			gView.count = (TextView) convertView.findViewById(R.id.gCount);
			convertView.setTag(gView);
		}
		else
		{
			gView = (GroupView) convertView.getTag();
		}
		GroupInfo groupInfo = groupList.get(groupPosition);
		gView.groupName.setText(groupInfo.getName());
		if(groupPosition==0)
		{
			
			gView.count.setText(""+childList.get(groupPosition).size());
		}
		else
		{
			gView.count.setText("0");
		}
		
		return convertView;
	}
	
	/**************************************** Child *******************************************/
	
	int i = 0;
	public int getChildrenCount(int groupPosition)
	{
		if(groupPosition == 0)
		{
			i = childList.get(groupPosition).size();
		}
		else
		{
			Toast.makeText(context, "长按添加好友", 0).show();
			i = 0;
		}
		return i;
	}
	
	public Object getChild(int groupPosition, int childPosition)
	{
		return childList.get(groupPosition).get(childPosition);
	}
	
	public long getChildId(int groupPosition, int childPosition)
	{
		return childPosition;
	}
	
	public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent)
	{
		ChildView cView = null;
		if (convertView == null)
		{
			cView = new ChildView();
			convertView = LayoutInflater.from(context).inflate(R.layout.child_item, null);
			cView.childName = (TextView) convertView.findViewById(R.id.cName);
			cView.pic = (ImageView) convertView.findViewById(R.id.imageView1);
			convertView.setTag(cView);
		}
		else
		{
			cView = (ChildView) convertView.getTag();
		}
		ChildInfo childInfo = childList.get(groupPosition).get(childPosition);
		cView.pic.setImageBitmap(childInfo.getChildPic());
		cView.childName.setText(childInfo.getChildName());
		return convertView;
	}
	
	public boolean hasStableIds()
	{
		return false;
	}
	
	public boolean isChildSelectable(int groupPosition, int childPosition)
	{
		return false;
	}
	
}
