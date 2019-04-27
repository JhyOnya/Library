package timeOperate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

public class getTime {
	private static String baseDate = "2019-04-15";	
	private static int weekNum=1;
	private static int dayOfWeek=1;
	private static String remainTime=null;
	private static String[] timeList=new String[10];
	private static Map<String,Map<Integer,String[]>> schedule;

	
	
	
	public static void calWeekAndDay() throws ParseException {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = new Date();
		String sdate1 = ft.format(date1);
		date1 = ft.parse(sdate1);
		Date date2 = ft.parse(baseDate);
		long days = (date1.getTime()-date2.getTime()) / (24*3600*1000);
//		System.out.println("days:" + days);
		long intervalDays=days+1;
//		System.out.println("intervalDays:"+intervalDays);
		weekNum=(int) Math.ceil(intervalDays/7.0);
//		System.out.println("weekNum:"+weekNum);
		int TrueDayOfWeek=cal.get(Calendar.DAY_OF_WEEK);
//		System.out.println("day:"+TrueDayOfWeek);
		//注意了4表示星期三，3表示星期二。
		int countDayOfWeek=(int)(intervalDays%7);
//		System.out.println("countDayOfWeek:"+countDayOfWeek);
		if(countDayOfWeek!=TrueDayOfWeek-1)
			System.out.println("时间计算错误！");
		dayOfWeek=TrueDayOfWeek-1;//表示周几
	}
	
	

	// 计算剩余时间
	public static void calRemainTime(String className) throws ParseException {
		calWeekAndDay();
		String[] time=schedule.get(className).get(dayOfWeek);
		/**
		 * 处理课表数据
		 * */
		
		
		
	}
//	public static void convertTime(String[] time) {//将当天的时间列表转换成float类型
//		float[] fTime=new float[10];
//		for(int i=0;i<time.length;i++) {
//			String[] a=time[i].split(":");
////			System.out.println(time[2]);
//			if(a.length!=1){
//				fTime[i]=Float.parseFloat(a[0]+"."+a[1]);
//				System.out.println(fTime[i]);
//			}
//			
//			
//		}
//	}
//	
	public static void timeList(Date curTime) throws ParseException {
		// args:表示当时的时间，时-分-秒
		//和列表进行比较
		SimpleDateFormat ft = new SimpleDateFormat("HH:mm:ss");
		if(timeList[0]!=null&&curTime.before(ft.parse(timeList[0]))) {
			System.out.println("占用图书馆");
		}
		else if(timeList[9]!=null&&ft.parse(timeList[0]).before(curTime)) {
			System.out.println("占用图书馆");
		}
		else {
			
			for(int i=1;i<timeList.length;i++) {
				if(timeList[i]!=null) {
					Date time2=ft.parse(timeList[i]);
					if(curTime.before(time2)) {//找到了
						System.out.println("找到了");
						if(i%2==1) {//正在上课
							System.out.println(i);
							long diff=time2.getTime()-curTime.getTime();
							long hour=(diff/(60*60*1000));
							long min=((diff-hour*60*60*1000)/(60*1000));
							long sec=((diff-hour*60*60*1000-min*60*1000)/1000);
							remainTime=hour+":"+min+":"+sec;
							System.out.println("距离下课还有:"+remainTime);
						}
						else {
							System.out.println("占用图书馆！");
							remainTime=null;
						}
						return;
					}
				}
				
			}
			System.out.println("没有找到");
			remainTime=null;
			return;
		}
		
		
		
	}
	
	
	public static void init() {//初始化上课时间
		for(int i=2;i<timeList.length;i++) {
			timeList[i]=null;
		}
		timeList[0]="08:00:00";
		timeList[1]="09:40:00";
		timeList[6]="15:30:00";
		timeList[7]="17:10:00";
		
	}
	
	
	
	//测试
	public static void main(String[] args) throws ParseException {
		init();
		SimpleDateFormat ft = new SimpleDateFormat("HH:mm:ss");
		Date date1 = new Date();
//		String sdate1 = ft.format(date1);
		String sdate1="16:00:00";
		Date curTime = ft.parse(sdate1);
		
		//转换成字符串
//		System.out.println("1".compareTo("2"));
//		System.out.println("2".compareTo("1"));
		timeList(curTime);
		
	
	}
	
	
	
	
}
