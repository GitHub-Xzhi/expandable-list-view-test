
package com.example.expandablelistviewtest;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Toast;


public class MainActivity extends Activity
{
	MyAdapter adapter;
	ExpandableListView ep;
	/* 一级数据源 */
	List<GroupInfo> groupList;
	/* 二级数据源 */
	List<List<ChildInfo>> childList;
	
	List<ChildInfo>cList;

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		ep = (ExpandableListView)findViewById(R.id.expandableListView1);
		
		init();
		epClick();
	}
	
	public void init()
	{
		groupList = new ArrayList<GroupInfo>();
		groupList.add(new GroupInfo("我的好友"));
		
		cList = new ArrayList<ChildInfo>();
		cList.add(new ChildInfo("张三", BitmapFactory.decodeResource(getResources(), R.drawable.pic6)));
		cList.add(new ChildInfo("李四", BitmapFactory.decodeResource(getResources(), R.drawable.pic5)));
		cList.add(new ChildInfo("王五", BitmapFactory.decodeResource(getResources(), R.drawable.pic3)));
		cList.add(new ChildInfo("小红", BitmapFactory.decodeResource(getResources(), R.drawable.pic4)));
		cList.add(new ChildInfo("小花", BitmapFactory.decodeResource(getResources(), R.drawable.pic1)));
		cList.add(new ChildInfo("小诗", BitmapFactory.decodeResource(getResources(), R.drawable.pic2)));
		
		childList = new ArrayList<List<ChildInfo>>();
		childList.add(cList);
		adapter = new MyAdapter(groupList, childList, this);
		ep.setAdapter(adapter);
	}

	/**
	 * ep点击事件
	 * 
	 * @time 2017-3-10 上午10:55:41
	 */
	public void epClick()
	{
		ep.setOnItemLongClickListener(new OnItemLongClickListener()
		{
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
			{
				final EditText editText = new EditText(MainActivity.this);
				editText.setHint("请输入姓名");
				new AlertDialog.Builder(MainActivity.this)
				.setTitle("添加好友")
				.setView(editText)
				.setNegativeButton("取消", new OnClickListener()
				{
					
					public void onClick(DialogInterface dialog, int which)
					{
						try
						{
							/** 利用反射技术不关闭对话框 */
							Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
							field.setAccessible(true);
							// true 为关闭
							field.set(dialog, true);
							dialog.dismiss();
						}
						catch (Exception e)
						{
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						// TODO Auto-generated method stub
						
					}
				})
				.setPositiveButton("确定", new OnClickListener()
				{
					
					public void onClick(DialogInterface dialog, int which)
					{
						
						Field field = null;
						try
						{
							field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
							field.setAccessible(true);
							String string = editText.getText().toString();
							if (!(string.equals("")))
							{
								field.set(dialog, true);
								dialog.dismiss();
//								cList.clear();
								cList = new ArrayList<ChildInfo>();
								cList.add(new ChildInfo(string, BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher)));
//								childList = new ArrayList<List<ChildInfo>>();
								childList.add(cList);
								adapter.notifyDataSetChanged();
							}
							else
							{
								Toast.makeText(MainActivity.this, "姓名不能为空", 0).show();
								field.set(dialog, false);
								dialog.dismiss();
							}
						}
						catch (Exception e)
						{
							// TODO: handle exception
						}
					}
				}).show();
				return false;
			}
		});
	}
	public void add(View v)
	{
		final EditText editText = new EditText(this);
		editText.setHint("请输入分组名");
		new AlertDialog.Builder(this)
		.setTitle("添加分组")
		.setView(editText)
		.setNegativeButton("取消", new OnClickListener()
		{
			
			public void onClick(DialogInterface dialog, int which)
			{
				try
				{
					Field field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
					field.setAccessible(true);
					// 为true，表示关闭对话框
					field.set(dialog, true);
					dialog.dismiss();
				}
				catch (Exception e)
				{
					// TODO: handle exception
				}
			}
		})
		.setPositiveButton("确定", new OnClickListener()
		{
			
			public void onClick(DialogInterface dialog, int which)
			{
				Field field = null;
				try
				{
					/** 利用反射技术不关闭对话框 */
					field = dialog.getClass().getSuperclass().getDeclaredField("mShowing");
					field.setAccessible(true);
					
					String string = editText.getText().toString();
					if (!(string.equals("")))
					{
						groupList.add(new GroupInfo(string));
						adapter.notifyDataSetChanged();
						// 为true，表示关闭对话框
						field.set(dialog, true);
						dialog.dismiss();
					}
					else
					{
						// 为false，表示不关闭对话框
						field.set(dialog, false);
						dialog.dismiss();
						Toast.makeText(MainActivity.this, "请输入分组名", 0).show();
					}
				}
				catch (Exception e)
				{
					// TODO: handle exception
				}
			}
				
		}).show();
	}
}
