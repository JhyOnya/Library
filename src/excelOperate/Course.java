package excelOperate;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class Course {

	public static Map<String, Map<Integer, String[]>> courseMap;
	//				 专业号		周几			开始节/结束节/上课周    全部是字符串		
	public static Map<Integer, String[]> timeMap;
	//					周几		开始节/结束节/上课周    全部是字符串	
	public static String[] classWeek= new String[15];	 
	//课程时间信息 				每周五天		开始节/结束节/上课周
	public static String majorNum;	//专业号
	
	public static int weekday;	//周几
	
	public static void init(List<String> aString) {	//初始化函数，未完成
		getMajorNum(aString.get(0));	//第一格  1-26为课程
		for(int i=1;i<aString.size()-1;i++) {
			getWeekDay(i);
			
		}
		//String []a=aString.split("//n");
		//int ArraySize=a.length;
		
	}
	
	
	//专业号
	public static void getMajorNum(String numString) {
		majorNum=numString.replaceAll("//D+", "");
	}
	
	//周几
 	public static void getWeekDay(int x) {
		int y=(int)(x-1)/5;
		if(y>5)
			weekday=1;
		else 
			weekday=y;
	}
	
	public int returnWeekday() {
		return this.weekday;
	}
	
	public static void getOneDayClass(String[] classStrings) {	//获得课程信息
		
		
		
		
	}
	
	public static void addCourseMap(String majorNum,int weekDay,String [] aStrings) {	//
		if(courseMap==null) {
			courseMap=new TreeMap<String, Map<Integer, String[]>>();}
		if (courseMap.get(majorNum)==null) {
			courseMap.put(majorNum,new TreeMap<Integer,String[]>());
		}		
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

public static void main(String[] args) {
	String [] aStrings=new String[] {"3D综合强化训练(Ⅰ)[试]","韩兆云","LY513","环境设计1640903[37人]" ,"[1-8,10-15,17-18周][1-2节]"};
	
	addCourseMap("环境设计1640903", 1, aStrings);
	
	System.out.println(courseMap.containsKey("环境设计1640903")+""+aStrings[1]);
	System.out.println((courseMap.get("环境设计1640903").get(1)));
}
		

}
