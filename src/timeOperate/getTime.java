package timeOperate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class getTime {
	String baseDate = "2019-04-15";	//表示基准时间，第一周第一天
	public int weekNum=1;//表示第几周
	public int dayOfWeek=1;//1表示周一
	public String remainTime=null;//表示剩余的时间
	/**
	 * null表示今天一天都没有课，座位被占据
	 * */
	
	
	
	//计算本周是第几周&&星期几
	public void calWeekAndDay() throws ParseException {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
		Date date1 = new Date();//表示今天的日期
		String sdate1 = ft.format(date1);
		date1 = ft.parse(sdate1);
		//对时间进行规整
		Date date2 = ft.parse(baseDate);
		long days = (date1.getTime()-date2.getTime()) / (24*3600*1000);
		//因为返回的是毫秒级别
		System.out.println("days:" + days);
		//System.out.println(date1.getTime());
		//将相隔的天数除以7，向上取整就是第几周
		long intervalDays=days+1;
		System.out.println("intervalDays:"+intervalDays);
		weekNum=(int) Math.ceil(intervalDays/7.0);
		System.out.println("weekNum:"+weekNum);
		int TrueDayOfWeek=cal.get(Calendar.DAY_OF_WEEK);
		System.out.println("day:"+TrueDayOfWeek);
		//注意了4表示星期三，3表示星期二。
		//验证自己计算的日期是不是和程序得出来得一样
		int countDayOfWeek=(int)(intervalDays%7);
		System.out.println("countDayOfWeek:"+countDayOfWeek);
		if(countDayOfWeek!=TrueDayOfWeek-1)
			System.out.println("这个计算时间的系统怕是有问题");
		dayOfWeek=TrueDayOfWeek-1;//表示周几
	}
	
	

	//计算剩余时间
	/**
	 * 参数
	 * 1、班级：
	 * 2、第几周
	 * 3、星期几
	 * 首先查看是不是在有课的周数范围里
	 * 然后确定时间
	 * */
	public void calRemainTime(String className) {//还有两个参数不需要传递可以直接用
		/**
		 * TODO:
		 * 读表格，根据weekNum和班级确定本周有没有课，若null说明这个座位一直显示有人
		 * 若有课，根据（周数&班级&周几）确定今天的课程时间阈值，计算此时到下一个终止数值之间的时间差
		 * 时刻更新所有vip的座位情况
		 * 返回：String "小时:分:秒"，剩余可使用时间
		 * */
	}
	
	
	
	
	public static void main(String[] args) throws ParseException {
//		Date t=new Date();
//		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		System.out.println(df.format(t));
		
	
	}
	
	
	
	
}
