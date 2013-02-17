package ir.ac.tums.mail.util;

import java.util.*;

public class PDate
{
	// public int[]  MonthDay={0,31,28,31,30,31,30,31,31,30,31,30,31};
	static private int[] MonthDayF=
		{
		0,31,31,31,31,31,31,30,30,30,30,30,29};
	static private int[] MonthDay=
		{
		0,31,28,31,30,31,30,31,31,30,31,30,31};

	public PDate()
	{

	}

	/**
	 * @param dayOfWeek int arguments
	 * @return String
	 */
	public static String getDayofWeek(int dayOfWeek)
	{
		String ret;
		switch(dayOfWeek)
		{
			case 1:
				ret="\u064A\u06A9\u0634\u0646\u0628\u0647";
				break;
			case 2:
				ret="\u062F\u0648\u0634\u0646\u0628\u0647";
				break;
			case 3:
				ret="\u0633\u0647 \u0634\u0646\u0628\u0647";
				break;
			case 4:
				ret="\u0686\u0647\u0627\u0631\u0634\u0646\u0628\u0647";
				break;
			case 5:
				ret="\u067E\u0646\u062C\u0634\u0646\u0628\u0647";
				break;
			case 6:
				ret="\u062C\u0645\u0639\u0647";
				break;
			case 7:
				ret="\u0634\u0646\u0628\u0647";
				break;
			default:
				ret="unknown:"+dayOfWeek;
		}
		return ret;
	}

	/**
	 *
	 */
	public static String getMonthName(int MonthF)
	{
		String name;
		switch(MonthF)
		{
			case 1:
				name="\u0641\u0631\u0648\u0631\u062F\u064A\u0646";
				break;
			case 2:
				name="\u0627\u0631\u062F\u064A\u0628\u0647\u0634\u062A";
				break;
			case 3:
				name="\u062E\u0631\u062F\u0627\u062F";
				break;
			case 4:
				name="\u062A\u064A\u0631";
				break;
			case 5:
				name="\u0645\u0631\u062F\u0627\u062F";
				break;
			case 6:
				name="\u0634\u0647\u0631\u064A\u0648\u0631";
				break;
			case 7:
				name="\u0645\u0647\u0631";
				break;
			case 8:
				name="\u0622\u0628\u0627\u0646";
				break;
			case 9:
				name="\u0622\u0630\u0631";
				break;
			case 10:
				name="\u062F\u064A";
				break;
			case 11:
				name="\u0628\u0647\u0645\u0646";
				break;
			case 12:
				name="\u0627\u0633\u0641\u0646\u062F";
				break;
			default:
				name="unknown:"+MonthF;
		}
		return name;
	}

	public static String toShamsiString(Date gDate) //input is MILADI date
	{
		if(gDate==null)
		{
			return "-";
		}
		GregorianCalendar gCal=new GregorianCalendar();
		gCal.setTime(gDate);
		int DayM=gCal.get(GregorianCalendar.DAY_OF_MONTH);
		int MonthM=gCal.get(GregorianCalendar.MONTH)+1;
		int YearM=gCal.get(GregorianCalendar.YEAR);
		String dM;
		String mM;
		String yM;
		int YearF=0;
		int MonthF=0;
		int DayF=0;
		int Index;
		boolean Kabise;
		String targetDate;
		Integer count=new Integer(0);
		if(YearM%4>0)
		{
			Kabise=false;
		}
		else
		{
			Kabise=true;
		}
		if(((YearM-1)%4)==0)
		{
			MonthDayF[12]=30;
			Index=1;
		}
		else
		{
			Index=0;
		}
		YearF=1379+(YearM-2000);
		switch(MonthM)
		{
			case 1:
				MonthF=10;
				DayF=DayM+10+Index;
				YearF=YearF-1;
				break;
			case 2:
				MonthF=11;
				DayF=DayM+11+Index;
				YearF=YearF-1;
				break;
			case 3:
				MonthF=12;
				DayF=DayM+9+Index;
				YearF=YearF-1;
				break;
			case 4:
				MonthF=1;
				DayF=DayM+11;
				break;
			case 5:
				MonthF=2;
				DayF=DayM+10;
				break;
			case 6:
				MonthF=3;
				DayF=DayM+10;
				break;
			case 7:
				MonthF=4;
				DayF=DayM+9;
				break;
			case 8:
				MonthF=5;
				DayF=DayM+9;
				break;
			case 9:
				MonthF=6;
				DayF=DayM+9;
				break;
			case 10:
				MonthF=7;
				DayF=DayM+8;
				break;
			case 11:
				MonthF=8;
				DayF=DayM+9;
				break;
			case 12:
				MonthF=9;
				DayF=DayM+9;
				break;
		}
		if(Kabise)
		{
			DayF++;
		}
		if((DayF>MonthDayF[MonthF])||((DayF>MonthDayF[12])&&(MonthF==12)))
		{
			if(MonthF==12)
			{
				YearF=YearF+1;
			}
			DayF=DayF-MonthDayF[MonthF];
			MonthF=(MonthF%12)+1;
		}
		targetDate=YearF+"/";
		if(MonthF<10)
		{
			targetDate=targetDate+"0";
		}
		targetDate=targetDate+MonthF+"/";
		if(DayF<10)
		{
			targetDate=targetDate+"0";
		}
		targetDate=targetDate+DayF;
		return(targetDate);
	}

	public static String toGregorianString(int DayF,int MonthF,int YearF) //input is SHAMSI date
	{
		// int YearF;int MonthF;int DayF;
		int YearM=0;
		int MonthM=0;
		int DayM=0;
		int Index;
		boolean Kabise;
		String targetDate;
		//CALL  DateInitial()
		if((YearF+1)%4>0)
		{
			Kabise=false;
		}
		else
		{
			Kabise=true;
		}
		Index=0;
		if(((YearF+2)%4)==0)
		{
			MonthDay[2]=29;
			Index=1;
		}
		YearM=2000+(YearF-1379);
		switch(MonthF)
		{
			case 1:
				MonthM=4;
				DayM=DayF-11;
				break;
			case 2:
				MonthM=5;
				DayM=DayF-10;
				break;
			case 3:
				MonthM=6;
				DayM=DayF-10;
				break;
			case 4:
				MonthM=7;
				DayM=DayF-9;
				break;
			case 5:
				MonthM=8;
				DayM=DayF-9;
				break;
			case 6:
				MonthM=9;
				DayM=DayF-9;
				break;
			case 7:
				MonthM=10;
				DayM=DayF-8;
				break;
			case 8:
				MonthM=11;
				DayM=DayF-9;
				break;
			case 9:
				MonthM=12;
				DayM=DayF-9;
				break;
			case 10:
				MonthM=1;
				DayM=DayF-10-Index;
				YearM=YearM+1;
				break;
			case 11:
				MonthM=2;
				DayM=DayF-11-Index;
				YearM=YearM+1;
				break;
			case 12:
				MonthM=3;
				DayM=DayF-9-Index;
				YearM=YearM+1;
				break;
		}
		if(Kabise)
		{
			DayM=DayM-1;
		}
		if(DayM<1)
		{
			if(MonthM==1)
			{
				YearM=YearM-1;
			}
			MonthM=((MonthM+10)%12)+1;
			DayM=DayM+MonthDay[MonthM];
		}
		if((Index==1)&&(MonthF==10)&&(1<=DayF)&&(DayF<=10))
		{
			DayM=DayM+1;
		}
		if((Index==1)&&(MonthF==12)&&(1<=DayF)&&(DayF<=10))
		{
			DayM=DayM-1;
		}
		MonthDay[2]=28;
		targetDate=YearM+"/";
		if(MonthM<10)
		{
			targetDate=targetDate+"0";
		}
		targetDate=targetDate+MonthM+"/";
		if(DayM<10)
		{
			targetDate=targetDate+"0";
		}
		targetDate=targetDate+DayM;
		return(targetDate);
	}

	public static Date toGregorianDate(int DayF,int MonthF,int YearF)
	{
		// int YearF;int MonthF;int DayF;
		int YearM=0;
		int MonthM=0;
		int DayM=0;
		Date date=new Date(1);
		int Index;
		boolean Kabise;
		String targetDate;
		if((YearF+1)%4>0)
		{
			Kabise=false;
		}
		else
		{
			Kabise=true;
		}
		Index=0;
		if(((YearF+2)%4)==0)
		{
			MonthDay[2]=29;
			Index=1;
		}
		YearM=2000+(YearF-1379);
		switch(MonthF)
		{
			case 1:
				MonthM=4;
				DayM=DayF-11;
				break;
			case 2:
				MonthM=5;
				DayM=DayF-10;
				break;
			case 3:
				MonthM=6;
				DayM=DayF-10;
				break;
			case 4:
				MonthM=7;
				DayM=DayF-9;
				break;
			case 5:
				MonthM=8;
				DayM=DayF-9;
				break;
			case 6:
				MonthM=9;
				DayM=DayF-9;
				break;
			case 7:
				MonthM=10;
				DayM=DayF-8;
				break;
			case 8:
				MonthM=11;
				DayM=DayF-9;
				break;
			case 9:
				MonthM=12;
				DayM=DayF-9;
				break;
			case 10:
				MonthM=1;
				DayM=DayF-10-Index;
				YearM=YearM+1;
				break;
			case 11:
				MonthM=2;
				DayM=DayF-11-Index;
				YearM=YearM+1;
				break;
			case 12:
				MonthM=3;
				DayM=DayF-9-Index;
				YearM=YearM+1;
				break;
		}
		if(Kabise)
		{
			DayM=DayM-1;
		}
		if(DayM<1)
		{
			if(MonthM==1)
			{
				YearM=YearM-1;
			}
			MonthM=((MonthM+10)%12)+1;
			DayM=DayM+MonthDay[MonthM];
		}
		if((Index==1)&&(MonthF==10)&&(1<=DayF)&&(DayF<=10))
		{
			DayM=DayM+1;
		}
		if((Index==1)&&(MonthF==12)&&(1<=DayF)&&(DayF<=10))
		{
			DayM=DayM-1;
		}
		MonthDay[2]=28;
		targetDate=YearM+"/";
		if(MonthM<10)
		{
			targetDate=targetDate+"0";
		}
		targetDate=targetDate+MonthM+"/";
		if(DayM<10)
		{
			targetDate=targetDate+"0";
		}
		targetDate=targetDate+DayM;
		GregorianCalendar gCalendar=new GregorianCalendar();
		gCalendar.set(gCalendar.DAY_OF_MONTH,DayM);
		gCalendar.set(gCalendar.MONTH,MonthM);
		gCalendar.set(gCalendar.YEAR,YearM);
		date=gCalendar.getTime();
		return date;
	}
	/*
		public static void main(String[] args) {
	  Date date = new Date(1);
	  PDate pdate = new PDate();
	  String str = pdate.setShamsi(1,1,1360);
	  date = pdate.setShamsiDate (1,1,1360);
	  java.sql.Date sdate = new java.sql.Date(1);
	  sdate.setTime(date.getTime());
	  System.out.println (sdate.toString() + "    " + str);
	 }
	 */

}