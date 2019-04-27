package excelOperate;

import java.util.Map;

public class Course {

	public static Map<String, Map<Integer, String[]>> courseMap;
	//				 专业号		周几			开始节/结束节/上课周    全部是字符串		
	public static Map<Integer, String[]> timeMap;
	//					周几		开始节/结束节/上课周    全部是字符串	
	public static String[] classWeek= new String[15];	 
	//课程时间信息 				每周五天		开始节/结束节/上课周
	public static String majorNum;	//专业号
	
	public static int weekday;
	
	public static void init(String aString) {	//初始化函数，未完成
		String []a=aString.split("//n");
		int ArraySize=a.length;
		
	}
	
	//专业号
	public void getMajorNum(String numString) {
		majorNum=numString.replaceAll("//D+", "");
	}
	
	//周几
 	public void getWeekDay(int x) {
		int y=(int)(x-1)/5;
		if(y>5)
			weekday=1;
		else 
			weekday=y;
	}
	
	public int returnWeekday() {
		return this.weekday;
	}
	
	public String returnMajorNum() {
		return this.majorNum;
	}
	
	public String[] returnClassWeek() {
		return this.classWeek;
	}
	
	public Map<Integer, String[]> returnTimeMap() {
		return this.timeMap;
	}
	
	public Map<String, Map<Integer, String[]>> returnCourseMap() {
		return this.courseMap;
	}
}
