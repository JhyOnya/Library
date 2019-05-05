package timeOperate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;
import excelOperate.Course;
import excelOperate.readFile;
import excelOperate.readFile;

public class getTime {
	private static String baseDate = "2019-04-29";	
	private static int weekNum=1;
	private static int dayOfWeek=1;
	private static String remainTime=null;
	private static String[] timeList=new String[10];
	private static String yMd,Hms;//年月日，时分秒

	
	
	public static void calWeekAndDay(String askTime) throws ParseException {
		yMd =askTime.split(" ")[0];
		Hms=askTime.split(" ")[1];
		System.out.println(askTime);
		System.out.println(yMd);
		System.out.println(Hms);
		yMd=yMd.replace("年", "-");
		yMd=yMd.replace("月", "-");
		yMd=yMd.replace("日", "-");
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = ft.parse(yMd);
		
		Date date2 = ft.parse(baseDate);
		
		long days = (date1.getTime()-date2.getTime()) / (24*3600*1000);
		
		long intervalDays=days+1;

		weekNum=(int) Math.ceil(intervalDays/7.0);
		int countDayOfWeek=(int)(intervalDays%7);
		dayOfWeek=countDayOfWeek==0?7:countDayOfWeek;//表示周几
	}
	
	

	/**
	 * 计算每天真正的上课时间列表
	 * @param className
	 * @throws ParseException
	 */
	public static void calTimeList(String askTime) throws ParseException {
		calWeekAndDay(askTime);
		if(dayOfWeek==6||dayOfWeek==7)
			return;
		String[] weekList= new String[5];
		Course aCourse = new Course();

		
		
//		为timeList赋值
		init();
		if(dayOfWeek>5||dayOfWeek<1)
			return;
		if(contained(weekList[Course.getIndex(dayOfWeek, 1)])) {
			timeList[0]="08:00:00";
			timeList[1]="09:40:00";
		}
		if (contained(weekList[Course.getIndex(dayOfWeek, 2)])) {
			timeList[2]="10:00:00";
			timeList[3]="11:40:00";
		}
		if (contained(weekList[Course.getIndex(dayOfWeek, 3)])) {
			timeList[4]="13:30:00";
			timeList[5]="15:10:00";
		}
		if (contained(weekList[Course.getIndex(dayOfWeek, 4)])) {
			timeList[6]="15:30:00";;
			timeList[7]="17:10:00";
		}
		if (contained(weekList[Course.getIndex(dayOfWeek, 5)])) {
			timeList[8]="18:00:00";;
			timeList[9]="21:00:00";
		}
		
	}
	
	
	/**
	 * 判断第n周,周m的上课情况，周数——weekNum
	 * @param weekList
	 * @return
	 */
	public static boolean contained(String weekList) {
		 if(weekList==null)
			 return false;
		 String[] strarray=weekList.split(",");
	     for (int i = 0; i < strarray.length; i++)
	     {
	    	 if(weekNum==Integer.parseInt(strarray[i]))
	    		 return true;
	     }
		 return false;
	}
	
	
	/**
	 * 计算剩余的时间
	 * @param askTime
	 * @return
	 * @throws ParseException
	 */
	public static String calRemainTime(String askTime) throws ParseException {
		calTimeList(askTime);
		if(dayOfWeek==6||dayOfWeek==7)
			return "VIP occupied";
		SimpleDateFormat ft = new SimpleDateFormat("HH:mm:ss");
		Date askT=ft.parse(Hms);
		if(timeList[0]!=null&&askT.before(ft.parse(timeList[0]))) {
			return "VIP occupied";
		}
		else if(timeList[9]!=null&&ft.parse(timeList[0]).before(askT)) {
			return "VIP occupied";
		}
		else {
			
			for(int i=1;i<timeList.length;i++) {
				if(timeList[i]!=null) {
					Date time2=ft.parse(timeList[i]);
					if(askT.before(time2)) {//找到了
						if(i%2==1) {//正在上课
//							System.out.println(i);
							long diff=time2.getTime()-askT.getTime();
							long hour=(diff/(60*60*1000));
							long min=((diff-hour*60*60*1000)/(60*1000));
							long sec=((diff-hour*60*60*1000-min*60*1000)/1000);
							remainTime="remain:"+hour+":"+min+":"+sec;
							return remainTime;
						}
						else {
							return "VIP occupied";
						}
						
					}
				}
				
			}
			return "VIP occupied";
		}
	}
	
	
	public static void init() {//初始化上课时间
		for(int i=2;i<timeList.length;i++) {
			timeList[i]=null;
		}
	}
	
	/**
	 * 正常的上课时间：
	 * 一(0,1)："08:00:00"-"09:40:00";
	 * 二(2,3)："10:00:00"-"11:40:00";
	 * 三(4,5)："13:30:00"-"15:10:00";
	 * 四(6,7)："15:30:00"-"17:10:00";
	 * 五(8,9)："18:00:00"-"21:00:00";
	 * 
	 * */
	
	
	public static Date conv(String dateString) throws ParseException {
		SimpleDateFormat ft = new SimpleDateFormat("HH:mm:ss");
		Date curTime = ft.parse(dateString);
		return curTime;
	}
	
	//测试
	public static void main(String[] args) throws ParseException {
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date1 = new Date();
		String sdate1="2019-05-04 13:15:14";
		String className="151101";
		calRemainTime(sdate1);
	
	}

}
