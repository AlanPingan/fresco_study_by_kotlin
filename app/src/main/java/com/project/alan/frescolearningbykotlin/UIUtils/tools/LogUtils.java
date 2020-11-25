package com.project.alan.frescolearningbykotlin.UIUtils.tools;

import android.util.Log;

public class LogUtils
{

	public static void e(String tag, String msg)
	{
		Log.e(tag, msg);
	}

	public static void e(String tag, Throwable e)
	{
		Log.e(tag, Log.getStackTraceString(e));
	}

	public static void e(Throwable e)
	{
		Log.e("", Log.getStackTraceString(e));
	}

	public static void d(String tag, String msg)
	{
		Log.d(tag, msg);
	}

	public static void w(String tag, String msg)
	{
		Log.w(tag, msg);
	}

	public static void i(String tag, String msg)
	{
		Log.d(tag, msg);
	}

}
