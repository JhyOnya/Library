package excelOperate;

import java.util.Map;

public class Course {

	public static Map<String, Map<Integer, String[]>> courseMap;
	//				 רҵ��		�ܼ�			��ʼ��/������/�Ͽ���    ȫ�����ַ���		
	public static Map<Integer, String[]> timeMap;
	//					�ܼ�		��ʼ��/������/�Ͽ���    ȫ�����ַ���	
	public static String[] classWeek= new String[15];	 
	//�γ�ʱ����Ϣ 				ÿ������		��ʼ��/������/�Ͽ���
	public static String majorNum;	//רҵ��
	
	public static int weekday;
	
	public static void init(String aString) {	//��ʼ��������δ���
		String []a=aString.split("//n");
		int ArraySize=a.length;
		
	}
	
	//רҵ��
	public void getMajorNum(String numString) {
		majorNum=numString.replaceAll("//D+", "");
	}
	
	//�ܼ�
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
