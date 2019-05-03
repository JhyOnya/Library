package excelOperate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Course {

	public static Map<String, Map<Integer, String[]>> courseMap;
	// 专业号 周几 开始节/结束节/上课周 全部是字符串
	//public static Map<Integer, String[]> timeMap;
	// 周几 开始节/结束节/上课周 全部是字符串
	public static String[] classWeek;
	// 课程时间信息 每周五天 周A中每节的上课周
	public static String majorNum; // 专业号

	public static int weekday; // 周几

	public static void init(List<String> aString) { // 初始化函数，未完成
		courseMap=new TreeMap<String, Map<Integer, String[]>>();
		//timeMap=new TreeMap<Integer, String[]>();
		classWeek=new String[5];
		
		getMajorNum(aString.get(0)); // 0为第一格 1-26为课程
		System.out.println("专业号为："+majorNum);
		//System.out.println(aString.get(0));
		int count = 0;
		for (int i = 1; i < aString.size() - 1; i++) { // size大小为27
			getWeekDay(i);// 周几
			classWeek[weekday] = solveOneClass(aString.get(i));
			System.out.println("周" + (count / 5 + 1) + "第" + ((i - 1) % 5 + 1) + "节课上课周数:" + classWeek[weekday]);
			count++;
			if (count % 5 == 0) {
				addCourseMap(majorNum, weekday, classWeek);// 给专业号A，星期x的天数，加上其一天的课
			}
		}
	}

	public static String solveOneClass(String classStrings) { // 获得某一格子的课程信息
		String aString = "";
		if (classStrings.isEmpty()) { // 这节没课
			aString="空";
			return aString;
		} else {
			String[] aStrings = classStrings.split("\\n");
			// 5 10 15
			// 4 9 14 [1-15,17-18周,单周][1-2节]
			for (int j = 1; j <= (aStrings.length) / 5; j++) {
				String a = aStrings[j * 5 - 1];
				// System.out.println("分割后的内容为：" + a); // [1-15,17-18周,单周][1-2节]
				String[] aa = a.split("\\]");
				// System.out.println("第二次分割后结果：" + aa[0]+" "+aa[1]);
				String b = aa[0].substring(1, aa[0].length());
				aString = addOneClass(b) + aString;
			}
			aString = aString.substring(0, aString.length() - 1);
			// System.out.println("本周有课的周数为：" +aString);//本周有课的周数为：1,3,5,7,9,11,2,4,6,8,10,12,14,16,18
			return aString;
		}

	}

	public static String addOneClass(String a) { // 获得"1-15,17-18周,单周"类型字符串，确定其使用周数
		// System.out.println("正在添加课程信息……");
		String oneClassString = "";
		List<Integer> lst = new ArrayList<Integer>();
		int flag = 0;
		String[] numString = a.split(",");
		int num = numString.length;
		if (a.contains("单周")) {
			flag = 1;
			num--;
		} else if (a.contains("双周")) {
			flag = 2;
			num--;
		}								// num计数其长度 若最后有单双周则-1，否则不变
		for (int i = 0; i < num; i++) {	// numString 1-15 16 17-18 19周	 单周
			if(numString[i].contains("-")) {	//为A-B格式
			String[] numString2 = numString[i].split("-"); // 去掉 - 符号
			
				if (numString2[1].contains("周")) // 3-5,7周
					numString2[1] = numString2[1].substring(0, numString2[1].length() - 1);
				List<Integer> lst1 = new ArrayList<Integer>();
				lst1 = getNum(numString2[0], numString2[1], flag);
				lst.addAll(lst1);
			}
			else {								//不为A-B格式 17周 /  6 这种
				String aString=numString[i];
				if(aString.contains("周"))
					aString=aString.substring(0, aString.length()-1);
				lst.add(changeToInt(aString));
			}
		}
		for (int j = 0; j < lst.size(); j++) {
			oneClassString = oneClassString + lst.get(j) + ',';
		}
		// oneClassString = oneClassString.substring(0, oneClassString.length() - 1); //
		// 输出1,3,5,7,9,11,13,15,17
		//System.out.println(oneClassString);
		// System.out.println("课程信息添加完毕");
		return oneClassString;
	}

	// 专业号
	public static void getMajorNum(String numString) {
		majorNum = numString.replaceAll("\\D+", "");
	}

	// 周几
	public static void getWeekDay(int x) {
		int y = (int) (x - 1) / 5;
		if (y > 5)
			weekday = 1;
		else
			weekday = y;
	}

	// 不知道对不对
	public static void addCourseMap(String majorNum, int weekDay, String[] aStrings) { // 增加course类及其中内容

		if (courseMap == null) {
			courseMap = new TreeMap<String, Map<Integer, String[]>>();
		}

		if (courseMap.get(majorNum) == null) {
			courseMap.put(majorNum, new TreeMap<Integer, String[]>());
		}

		if (courseMap.get(majorNum).get(weekDay) == null) {
			// System.out.println("運行");
			courseMap.get(majorNum).put(weekDay, aStrings);
		}
	}

	/*
	// 不知道对不对
	public static void addTimeMap(int weekDay, String[] aStrings) {// 增加TimeMap
		if (timeMap == null) {
			timeMap = new TreeMap<Integer, String[]>();
		}
		if (timeMap.get(weekDay) == null) {
			timeMap.put(weekDay, aStrings);
		}
	}
*/
	public static List<Integer> getNum(String a, String b, int flag) { // 返回a和b之間的數字list
		String numString = "";
		List<Integer> lst = new ArrayList<Integer>();
		int numA = changeToInt(a);
		int numB = changeToInt(b);
		for (int i = numA; i <= numB; i++) {
			if (flag == 1)// 单周
			{
				if (i % 2 != 0)
					lst.add(i);
			} else if (flag == 2) {
				if (i % 2 == 0)
					lst.add(i);
			} else {
				lst.add(i);
			}
		}
		return lst;
	}

	public static int changeToInt(String a) { // 將字符串改為數字 例如： 字符串“12”變為數字12
		char[] aChars = a.toCharArray();
		int num = 0;
		int count = 0;
		while (count < aChars.length) {
			num = num * 10 + (int) aChars[count] - 48;
			count++;
		}
		return num;
	}

	/*
	public Map<Integer, String[]> returnTimeMap() {
		return timeMap;
	}
	*/

	public Map<String, Map<Integer, String[]>> returnCourseMap(String majorStrin) {
		return courseMap;
	}
	
	public String[] returnClassWeek(String majorStrin,int weekDay) {
		return courseMap.get(majorStrin).get(weekDay);
	}

	//返回某一节的上课周数
	public String returnThisClassWeeks(String majorStrin,int weekDay,int jieshu) {
		String[] aString=courseMap.get(majorStrin).get(weekDay);
		return aString[jieshu];
	}

	
	public static void printString(String[] a) {
		System.out.println(a.length);
		for(int i=0;i<a.length;i++) {
			System.out.println(a[i]);
		}
	}
	
	public static void main(String[] args) {
		//测试用 可删
	}

}
