package excelOperate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.sun.corba.se.spi.orb.StringPair;
import com.sun.org.apache.bcel.internal.generic.NEW;

import sun.launcher.resources.launcher;

public class Course {

	public static Map<String, Map<Integer, String[]>> courseMap;
	// רҵ�� �ܼ� ��ʼ��/������/�Ͽ��� ȫ�����ַ���
	public static Map<Integer, String[]> timeMap;
	// �ܼ� ��ʼ��/������/�Ͽ��� ȫ�����ַ���
	public static String[] classWeek = new String[5];
	// �γ�ʱ����Ϣ ÿ������ ��A��ÿ�ڵ��Ͽ���
	public static String majorNum; // רҵ��

	public static int weekday; // �ܼ�

	public static void init(List<String> aString) { // ��ʼ��������δ���
		getMajorNum(aString.get(0)); // ��һ�� 1-26Ϊ�γ�
		System.out.println(aString.get(0));
		int count = 0;
		for (int i = 1; i < aString.size() - 1; i++) {// size��СΪ27
			getWeekDay(i);// �ܼ�
			classWeek[i % 5] = addOneClass(aString.get(i));

			// getOneDayClass(weekday, aString.get(i));
			System.out.println("��������ô");
			count++;
			if (count % 5 == 0) {
				addCourseMap(majorNum, weekday, classWeek);// ��רҵ��A������x��������������һ��Ŀ�
			}
		}
		// String []a=aString.split("//n");
		// int ArraySize=a.length;

	}

	public static void getOneDayClass(int weekDay, String classStrings) { // ���ĳһ���ӵĿγ���Ϣ  δ���
		if (classStrings.isEmpty()) { // ���û��

		} else {
			String[] aStrings = classStrings.split("\\n");
			
			// 5 10 15
			// 4 9 14 [1-15,17-18��,����][1-2��]
			for (int j = 1; j <= (aStrings.length) / 5; j++) {
				String a = aStrings[j * 5 - 1];
				System.out.println("�ָ�������Ϊ��"+a);
			}
			 
		}
	}

	// רҵ��
	public static void getMajorNum(String numString) {
		majorNum = numString.replaceAll("//D+", "");
	}

	// �ܼ�
	public static void getWeekDay(int x) {
		int y = (int) (x - 1) / 5;
		if (y > 5)
			weekday = 1;
		else
			weekday = y;
	}

	public int returnWeekday() {
		return this.weekday;
	}


	// ��֪���Բ���
	public static void addCourseMap(String majorNum, int weekDay, String[] aStrings) { // ����course�༰��������

		if (courseMap == null) {
			courseMap = new TreeMap<String, Map<Integer, String[]>>();
		}

		if (courseMap.get(majorNum) == null) {
			courseMap.put(majorNum, new TreeMap<Integer, String[]>());
		}

		if (courseMap.get(majorNum).get(weekDay) == null) {
			// System.out.println("�\��");
			courseMap.get(majorNum).put(weekDay, aStrings);
		}
	}

	// ��֪���Բ���
	public static void addTimeMap(int weekDay, String[] aStrings) {// ����TimeMap
		if (timeMap == null) {
			timeMap = new TreeMap<Integer, String[]>();
		}
		if (timeMap.get(weekDay) == null) {
			timeMap.put(weekDay, aStrings);
		}
	}

	public static String addOneClass(String a) { // ���"1-15,17-18��,����"�����ַ�����ȷ����ʹ������
		System.out.println("������ӿγ���Ϣ����");
		String oneClassString = "";
		List<Integer> lst = new ArrayList<Integer>();
		int flag = 0;
		String[] numString = a.split(",");
		int num = numString.length;

		if (a.contains("����")) {
			flag = 1;
			num--;
		} else if (a.contains("˫��")) {
			flag = 2;
			num--;
		}
		for (int i = 0; i < num; i++) {
			String[] numString2 = numString[i].split("-"); // ȥ�� - ����
			if (numString2[1].contains("��"))
				numString2[1] = numString2[1].substring(0, numString2[1].length() - 1);
			List<Integer> lst1 = new ArrayList<Integer>();
			lst1 = getNum(numString2[0], numString2[1], flag);
			lst.addAll(lst1);
		}
		for (int j = 0; j < lst.size(); j++) {
			oneClassString = oneClassString + lst.get(j) + ',';
		}
		oneClassString = oneClassString.substring(0, oneClassString.length() - 1); // ���1,3,5,7,9,11,13,15,17
		System.out.println(oneClassString);
		System.out.println("�γ���Ϣ������");
		return oneClassString;
	}

	public static List<Integer> getNum(String a, String b, int flag) { // ����a��b֮�g�Ĕ���list
		String numString = "";
		List<Integer> lst = new ArrayList<Integer>();
		int numA = changeToInt(a);
		int numB = changeToInt(b);
		for (int i = numA; i <= numB; i++) {
			if (flag == 1)// ����
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

	public static int changeToInt(String a) { // ���ַ����Ğ锵�� ���磺 �ַ�����12��׃�锵��12
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
		String[] aStrings = new String[] { "3D�ۺ�ǿ��ѵ��(��)[��]", "������", "LY513", "�������1640903[37��]",
				"[1-8,10-15,17-18��][1-2��]", "3D�ۺ�ǿ��ѵ��(��)[��]", "������", "LY513", "�������1640903[37��]",
				"[1-8,10-15,17-18��][1-2��]", "3D�ۺ�ǿ��ѵ��(��)[��]", "������", "LY513", "�������1640903[37��]",
				"[1-8,10-15,17-18��][1-2��]" };
		List<String> list = new ArrayList<String>();
		list.add("���ι���1610103");
		list.add("���ι滮ѧ[��]\r\n" + "������\r\n" + "LY208\r\n" + "1610103-04[57��]\r\n" + "[1-18��][1-2��]");
		list.add("�����г�Ӫ��[��]\r\n" + "÷����\r\n" + "LY215\r\n" + "1610103-04[57��]\r\n" + "[1-15��][3-4��]");
		// addCourseMap("�������1640903", 1, aStrings);
		// init(list);
		
		//  for(int i=0;i<aStrings3.length;i++) {
		 //System.out.println(i+"λ���ϵ�����Ϊ"+aStrings3[i]); }
		 
		 // System.out.println(m.matches());
		 
		 getOneDayClass(1, "�ִ��������[��]\r\n" + 
		 		"�����\r\n" + 
		 		"LY209\r\n" + 
		 		"�Ƶ�1710701-02[67��]\r\n" + 
		 		"[1-18��,˫��][1-2��]\r\n" + 
		 		"��ѧӢ������˵\r\n" + 
		 		"���\r\n" + 
		 		"105������(ʮ)\r\n" + 
		 		"�Ƶ����1710701[34��]\r\n" + 
		 		"[1-12��,����][1-2��]");
		

		addOneClass("1-15,17-18��,����");

	}

}
