package excelOperate;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.sun.org.apache.bcel.internal.generic.NEW;

public class Course {

	public static Map<String, Map<Integer, String[]>> courseMap;
	//				 רҵ��		�ܼ�			��ʼ��/������/�Ͽ���    ȫ�����ַ���		
	public static Map<Integer, String[]> timeMap;
	//					�ܼ�		��ʼ��/������/�Ͽ���    ȫ�����ַ���	
	public static String[] classWeek= new String[15];	 
	//�γ�ʱ����Ϣ 				ÿ������		��ʼ��/������/�Ͽ���
	public static String majorNum;	//רҵ��
	
	public static int weekday;	//�ܼ�
	
	public static void init(List<String> aString) {	//��ʼ��������δ���
		getMajorNum(aString.get(0));	//��һ��  1-26Ϊ�γ�
		for(int i=1;i<aString.size()-1;i++) {
			getWeekDay(i);
			
		}
		//String []a=aString.split("//n");
		//int ArraySize=a.length;
		
	}
	
	
	//רҵ��
	public static void getMajorNum(String numString) {
		majorNum=numString.replaceAll("//D+", "");
	}
	
	//�ܼ�
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
	
	public static void getOneDayClass(String[] classStrings) {	//��ÿγ���Ϣ
		
		
		
		
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
	String [] aStrings=new String[] {"3D�ۺ�ǿ��ѵ��(��)[��]","������","LY513","�������1640903[37��]" ,"[1-8,10-15,17-18��][1-2��]"};
	
	addCourseMap("�������1640903", 1, aStrings);
	
	System.out.println(courseMap.containsKey("�������1640903")+""+aStrings[1]);
	System.out.println((courseMap.get("�������1640903").get(1)));
}
		

}
