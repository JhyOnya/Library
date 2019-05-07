package excelOperate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Course {

	public static Map<String, String[]> courseMap;
	// 专业号 周几 开始节/结束节/上课周 全部是字符串
	public static String[] classWeek;
	// 课程时间信息 每周五天 周A中每节的上课周
	public static String majorNum; // 专业号

	public static void init(List<String> aString) { // 初始化函数
		courseMap = new TreeMap<String, String[]>();
		classWeek = new String[25];
		getMajorNum(aString.get(0)); // 0为第一格 1-26为课程
		//System.out.println("专业号为：" + majorNum);
 		for (int i = 1; i < aString.size() ; i++) { // size大小为27
			classWeek[i-1] = solveOneClass(aString.get(i));
			addCourseMap(majorNum, classWeek);// 给专业号A，星期x的天数，加上其一天的课
		}
	}

	public static String solveOneClass(String classStrings) { // 获得某一格子的课程信息
		String aString = "";
		if (classStrings.isEmpty()) { // 这节没课
			aString = null;
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
			// System.out.println("本周有课的周数为："+aString);//本周有课的周数为：1,3,5,7,9,11,2,4,6,8,10,12,14,16,18
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
		} // num计数其长度 若最后有单双周则-1，否则不变
		for (int i = 0; i < num; i++) { // numString 1-15 16 17-18 19周 单周
			if (numString[i].contains("-")) { // 为A-B格式
				String[] numString2 = numString[i].split("-"); // 去掉 - 符号

				if (numString2[1].contains("周")) // 3-5,7周
					numString2[1] = numString2[1].substring(0, numString2[1].length() - 1);
				List<Integer> lst1 = new ArrayList<Integer>();
				lst1 = getNum(numString2[0], numString2[1], flag);
				lst.addAll(lst1);
			} else { // 不为A-B格式 17周 / 6 这种
				String aString = numString[i];
				if (aString.contains("周"))
					aString = aString.substring(0, aString.length() - 1);
				lst.add(changeToInt(aString));
			}
		}
		for (int j = 0; j < lst.size(); j++) {
			oneClassString = oneClassString + lst.get(j) + ',';
		}
		// oneClassString = oneClassString.substring(0, oneClassString.length()
		// - 1); //
		// 输出1,3,5,7,9,11,13,15,17
		// System.out.println(oneClassString);
		// System.out.println("课程信息添加完毕");
		return oneClassString;
	}

	// 专业号
	public static void getMajorNum(String numString) {
		majorNum = numString.replaceAll("\\D+", "");
	}
	
	public static int getIndex(int x,int jieshu) {//输入的是周几，范围1-5,转换为大小为25数组的索引 周一第一节  1 1索引为0
		int a=0;
		if(x>5||x<1)
			System.out.println("输入不合法");
		a=5*(x-1)+jieshu-1;
		return a;
	}
	
	public static void addCourseMap(String majorNum, String[] aStrings) { // 增加course类及其中内容
		if (courseMap == null)
			courseMap = new TreeMap<String, String[]>();

		if (courseMap.get(majorNum) == null) {
			courseMap.put(majorNum, aStrings);		
		}
	}
	
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


	public String returnMajorNum() {
		return this.majorNum;
	}
	
	public static void main(String[] args) {
		// 测试用 可删
		List<String> aList=new ArrayList<String>();
		aList.add("旅行社151106\\r");
		aList.add("\\旅游企业战略管理\r\n" + 
				"李维庚\r\n" + 
				"LY231\r\n" + 
				"151105-06[66人]\r\n" + 
				"[1周][1-2节]");
		aList.add("\\旅游企业战略管理\r\n" + 
				"李维庚\r\n" + 
				"LY231\r\n" + 
				"151105-06[66人]\r\n" + 
				"[2周][1-2节]");
		aList.add("\\旅游企业战略管理\r\n" + 
				"李维庚\r\n" + 
				"LY231\r\n" + 
				"151105-06[66人]\r\n" + 
				"[3周][1-2节]");
		aList.add("\\旅游企业战略管理\r\n" + 
				"李维庚\r\n" + 
				"LY231\r\n" + 
				"151105-06[66人]\r\n" + 
				"[4周][1-2节]");
		aList.add("\\旅游企业战略管理\r\n" + 
				"李维庚\r\n" + 
				"LY231\r\n" + 
				"151105-06[66人]\r\n" + 
				"[5周][1-2节]");
		aList.add("\\旅游企业战略管理\r\n" + 
				"李维庚\r\n" + 
				"LY231\r\n" + 
				"151105-06[66人]\r\n" + 
				"[6周][1-2节]");
		aList.add("旅游企业战略管理\r\n" + 
				"李维庚\r\n" + 
				"LY231\r\n" + 
				"151105-06[66人]\r\n" + 
				"[7周][1-2节]\r\n" + 
				"\r\n");
		aList.add("旅游企业战略管理\r\n" + 
				"李维庚\r\n" + 
				"LY231\r\n" + 
				"151105-06[66人]\r\n" + 
				"[8周][1-2节]\r\n" + 
				"\r\n");
		aList.add("旅游企业战略管理\r\n" + 
				"李维庚\r\n" + 
				"LY231\r\n" + 
				"151105-06[66人]\r\n" + 
				"[9周][1-2节]\r\n" + 
				"\r\n");
		aList.add("旅游企业战略管理\r\n" + 
				"李维庚\r\n" + 
				"LY231\r\n" + 
				"151105-06[66人]\r\n" + 
				"[10周][1-2节]\r\n" + 
				"\r\n");
		aList.add("旅游企业战略管理\r\n" + 
				"李维庚\r\n" + 
				"LY231\r\n" + 
				"151105-06[66人]\r\n" + 
				"[11周][1-2节]\r\n" + 
				"\r\n");
		aList.add("旅游企业战略管理\r\n" + 
				"李维庚\r\n" + 
				"LY231\r\n" + 
				"151105-06[66人]\r\n" + 
				"[12周][1-2节]\r\n" + 
				"\r\n");
		aList.add("旅游企业战略管理\r\n" + 
				"李维庚\r\n" + 
				"LY231\r\n" + 
				"151105-06[66人]\r\n" + 
				"[13周][1-2节]\r\n" + 
				"\r\n");
		aList.add("旅游企业战略管理\r\n" + 
				"李维庚\r\n" + 
				"LY231\r\n" + 
				"151105-06[66人]\r\n" + 
				"[14周][1-2节]\r\n" + 
				"\r\n");
		aList.add("旅游企业战略管理\r\n" + 
				"李维庚\r\n" + 
				"LY231\r\n" + 
				"151105-06[66人]\r\n" + 
				"[15周][1-2节]\r\n" + 
				"\r\n");
		aList.add("旅游企业战略管理\r\n" + 
				"李维庚\r\n" + 
				"LY231\r\n" + 
				"151105-06[66人]\r\n" + 
				"[16周][1-2节]\r\n" + 
				"\r\n");
		aList.add("旅游企业战略管理\r\n" + 
				"李维庚\r\n" + 
				"LY231\r\n" + 
				"151105-06[66人]\r\n" + 
				"[17周][1-2节]\r\n" + 
				"\r\n");
		aList.add("旅游企业战略管理\r\n" + 
				"李维庚\r\n" + 
				"LY231\r\n" + 
				"151105-06[66人]\r\n" + 
				"[18周][1-2节]\r\n" + 
				"\r\n");
		aList.add("旅游企业战略管理\r\n" + 
				"李维庚\r\n" + 
				"LY231\r\n" + 
				"151105-06[66人]\r\n" + 
				"[19周][1-2节]\r\n" + 
				"\r\n");
		aList.add("旅游企业战略管理\r\n" + 
				"李维庚\r\n" + 
				"LY231\r\n" + 
				"151105-06[66人]\r\n" + 
				"[20周][1-2节]\r\n" + 
				"\r\n");
		aList.add("旅游企业战略管理\r\n" + 
				"李维庚\r\n" + 
				"LY231\r\n" + 
				"151105-06[66人]\r\n" + 
				"[21周][1-2节]\r\n" + 
				"\r\n");
		aList.add("\\旅游企业战略管理\r\n" + 
				"李维庚\r\n" + 
				"LY231\r\n" + 
				"151105-06[66人]\r\n" + 
				"[22周][1-2节]");
		aList.add("\\旅游企业战略管理\r\n" + 
				"李维庚\r\n" + 
				"LY231\r\n" + 
				"151105-06[66人]\r\n" + 
				"[23周][1-2节]");
		aList.add("\\旅游企业战略管理\r\n" + 
				"李维庚\r\n" + 
				"LY231\r\n" + 
				"151105-06[66人]\r\n" + 
				"[24周][1-2节]");
		aList.add("\\旅游企业战略管理\r\n" + 
				"李维庚\r\n" + 
				"LY231\r\n" + 
				"151105-06[66人]\r\n" + 
				"[25周][1-2节]");
		
		init(aList);
		String [] aStrings1=new String[25];
		aStrings1=courseMap.get("151106");
		//aStrings1=classWeek;
		System.out.println(aStrings1[getIndex(1, 1)]);
		System.out.println(aStrings1[getIndex(1, 2)]);
		System.out.println(aStrings1[getIndex(1, 3)]);
		System.out.println(aStrings1[getIndex(1, 4)]);
		System.out.println(aStrings1[getIndex(1, 5)]);
		System.out.println(aStrings1[getIndex(2, 1)]);
		System.out.println(aStrings1[getIndex(2, 2)]);
		System.out.println(aStrings1[getIndex(2, 3)]);
		System.out.println(aStrings1[getIndex(2, 4)]);
		System.out.println(aStrings1[getIndex(2, 5)]);
		System.out.println(aStrings1[getIndex(5, 5)]);
		
	}

}
