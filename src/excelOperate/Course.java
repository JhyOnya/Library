package excelOperate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Course {

	public static Map<String, String[]> courseMap;
	// רҵ�� �ܼ� ��ʼ��/������/�Ͽ��� ȫ�����ַ���
	public static String[] classWeek;
	// �γ�ʱ����Ϣ ÿ������ ��A��ÿ�ڵ��Ͽ���
	public static String majorNum; // רҵ��

	public static void init(List<String> aString) { // ��ʼ������
		courseMap = new TreeMap<String, String[]>();
		classWeek = new String[25];
		getMajorNum(aString.get(0)); // 0Ϊ��һ�� 1-26Ϊ�γ�
		//System.out.println("רҵ��Ϊ��" + majorNum);
 		for (int i = 1; i < aString.size() ; i++) { // size��СΪ27
			classWeek[i-1] = solveOneClass(aString.get(i));
			addCourseMap(majorNum, classWeek);// ��רҵ��A������x��������������һ��Ŀ�
		}
	}

	public static String solveOneClass(String classStrings) { // ���ĳһ���ӵĿγ���Ϣ
		String aString = "";
		if (classStrings.isEmpty()) { // ���û��
			aString = "��";
			return aString;
		} else {
			String[] aStrings = classStrings.split("\\n");
			// 5 10 15
			// 4 9 14 [1-15,17-18��,����][1-2��]
			for (int j = 1; j <= (aStrings.length) / 5; j++) {
				String a = aStrings[j * 5 - 1];
				// System.out.println("�ָ�������Ϊ��" + a); // [1-15,17-18��,����][1-2��]
				String[] aa = a.split("\\]");
				// System.out.println("�ڶ��ηָ������" + aa[0]+" "+aa[1]);
				String b = aa[0].substring(1, aa[0].length());
				aString = addOneClass(b) + aString;
			}
			aString = aString.substring(0, aString.length() - 1);
			// System.out.println("�����пε�����Ϊ��"+aString);//�����пε�����Ϊ��1,3,5,7,9,11,2,4,6,8,10,12,14,16,18
			return aString;
		}

	}

	public static String addOneClass(String a) { // ���"1-15,17-18��,����"�����ַ�����ȷ����ʹ������
													// System.out.println("������ӿγ���Ϣ����");
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
		} // num�����䳤�� ������е�˫����-1�����򲻱�
		for (int i = 0; i < num; i++) { // numString 1-15 16 17-18 19�� ����
			if (numString[i].contains("-")) { // ΪA-B��ʽ
				String[] numString2 = numString[i].split("-"); // ȥ�� - ����

				if (numString2[1].contains("��")) // 3-5,7��
					numString2[1] = numString2[1].substring(0, numString2[1].length() - 1);
				List<Integer> lst1 = new ArrayList<Integer>();
				lst1 = getNum(numString2[0], numString2[1], flag);
				lst.addAll(lst1);
			} else { // ��ΪA-B��ʽ 17�� / 6 ����
				String aString = numString[i];
				if (aString.contains("��"))
					aString = aString.substring(0, aString.length() - 1);
				lst.add(changeToInt(aString));
			}
		}
		for (int j = 0; j < lst.size(); j++) {
			oneClassString = oneClassString + lst.get(j) + ',';
		}
		// oneClassString = oneClassString.substring(0, oneClassString.length()
		// - 1); //
		// ���1,3,5,7,9,11,13,15,17
		// System.out.println(oneClassString);
		// System.out.println("�γ���Ϣ������");
		return oneClassString;
	}

	// רҵ��
	public static void getMajorNum(String numString) {
		majorNum = numString.replaceAll("\\D+", "");
	}
	
	public static int getIndex(int x,int jieshu) {//��������ܼ�����Χ1-5,ת��Ϊ��СΪ25��������� ��һ��һ��  1 1����Ϊ0
		int a=0;
		if(x>5||x<1)
			System.out.println("���벻�Ϸ�");
		a=5*(x-1)+jieshu-1;
		return a;
	}
	
	public static void addCourseMap(String majorNum, String[] aStrings) { // ����course�༰��������
		if (courseMap == null)
			courseMap = new TreeMap<String, String[]>();

		if (courseMap.get(majorNum) == null) {
			courseMap.put(majorNum, aStrings);		
		}
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
	
	public static void main(String[] args) {
		// ������ ��ɾ
		List<String> aList=new ArrayList<String>();
		aList.add("������151106\\r");
		aList.add("\\������ҵս�Թ���\r\n" + 
				"��ά��\r\n" + 
				"LY231\r\n" + 
				"151105-06[66��]\r\n" + 
				"[1��][1-2��]");
		aList.add("\\������ҵս�Թ���\r\n" + 
				"��ά��\r\n" + 
				"LY231\r\n" + 
				"151105-06[66��]\r\n" + 
				"[2��][1-2��]");
		aList.add("\\������ҵս�Թ���\r\n" + 
				"��ά��\r\n" + 
				"LY231\r\n" + 
				"151105-06[66��]\r\n" + 
				"[3��][1-2��]");
		aList.add("\\������ҵս�Թ���\r\n" + 
				"��ά��\r\n" + 
				"LY231\r\n" + 
				"151105-06[66��]\r\n" + 
				"[4��][1-2��]");
		aList.add("\\������ҵս�Թ���\r\n" + 
				"��ά��\r\n" + 
				"LY231\r\n" + 
				"151105-06[66��]\r\n" + 
				"[5��][1-2��]");
		aList.add("\\������ҵս�Թ���\r\n" + 
				"��ά��\r\n" + 
				"LY231\r\n" + 
				"151105-06[66��]\r\n" + 
				"[6��][1-2��]");
		aList.add("������ҵս�Թ���\r\n" + 
				"��ά��\r\n" + 
				"LY231\r\n" + 
				"151105-06[66��]\r\n" + 
				"[7��][1-2��]\r\n" + 
				"\r\n");
		aList.add("������ҵս�Թ���\r\n" + 
				"��ά��\r\n" + 
				"LY231\r\n" + 
				"151105-06[66��]\r\n" + 
				"[8��][1-2��]\r\n" + 
				"\r\n");
		aList.add("������ҵս�Թ���\r\n" + 
				"��ά��\r\n" + 
				"LY231\r\n" + 
				"151105-06[66��]\r\n" + 
				"[9��][1-2��]\r\n" + 
				"\r\n");
		aList.add("������ҵս�Թ���\r\n" + 
				"��ά��\r\n" + 
				"LY231\r\n" + 
				"151105-06[66��]\r\n" + 
				"[10��][1-2��]\r\n" + 
				"\r\n");
		aList.add("������ҵս�Թ���\r\n" + 
				"��ά��\r\n" + 
				"LY231\r\n" + 
				"151105-06[66��]\r\n" + 
				"[11��][1-2��]\r\n" + 
				"\r\n");
		aList.add("������ҵս�Թ���\r\n" + 
				"��ά��\r\n" + 
				"LY231\r\n" + 
				"151105-06[66��]\r\n" + 
				"[12��][1-2��]\r\n" + 
				"\r\n");
		aList.add("������ҵս�Թ���\r\n" + 
				"��ά��\r\n" + 
				"LY231\r\n" + 
				"151105-06[66��]\r\n" + 
				"[13��][1-2��]\r\n" + 
				"\r\n");
		aList.add("������ҵս�Թ���\r\n" + 
				"��ά��\r\n" + 
				"LY231\r\n" + 
				"151105-06[66��]\r\n" + 
				"[14��][1-2��]\r\n" + 
				"\r\n");
		aList.add("������ҵս�Թ���\r\n" + 
				"��ά��\r\n" + 
				"LY231\r\n" + 
				"151105-06[66��]\r\n" + 
				"[15��][1-2��]\r\n" + 
				"\r\n");
		aList.add("������ҵս�Թ���\r\n" + 
				"��ά��\r\n" + 
				"LY231\r\n" + 
				"151105-06[66��]\r\n" + 
				"[16��][1-2��]\r\n" + 
				"\r\n");
		aList.add("������ҵս�Թ���\r\n" + 
				"��ά��\r\n" + 
				"LY231\r\n" + 
				"151105-06[66��]\r\n" + 
				"[17��][1-2��]\r\n" + 
				"\r\n");
		aList.add("������ҵս�Թ���\r\n" + 
				"��ά��\r\n" + 
				"LY231\r\n" + 
				"151105-06[66��]\r\n" + 
				"[18��][1-2��]\r\n" + 
				"\r\n");
		aList.add("������ҵս�Թ���\r\n" + 
				"��ά��\r\n" + 
				"LY231\r\n" + 
				"151105-06[66��]\r\n" + 
				"[19��][1-2��]\r\n" + 
				"\r\n");
		aList.add("������ҵս�Թ���\r\n" + 
				"��ά��\r\n" + 
				"LY231\r\n" + 
				"151105-06[66��]\r\n" + 
				"[20��][1-2��]\r\n" + 
				"\r\n");
		aList.add("������ҵս�Թ���\r\n" + 
				"��ά��\r\n" + 
				"LY231\r\n" + 
				"151105-06[66��]\r\n" + 
				"[21��][1-2��]\r\n" + 
				"\r\n");
		aList.add("\\������ҵս�Թ���\r\n" + 
				"��ά��\r\n" + 
				"LY231\r\n" + 
				"151105-06[66��]\r\n" + 
				"[22��][1-2��]");
		aList.add("\\������ҵս�Թ���\r\n" + 
				"��ά��\r\n" + 
				"LY231\r\n" + 
				"151105-06[66��]\r\n" + 
				"[23��][1-2��]");
		aList.add("\\������ҵս�Թ���\r\n" + 
				"��ά��\r\n" + 
				"LY231\r\n" + 
				"151105-06[66��]\r\n" + 
				"[24��][1-2��]");
		aList.add("\\������ҵս�Թ���\r\n" + 
				"��ά��\r\n" + 
				"LY231\r\n" + 
				"151105-06[66��]\r\n" + 
				"[25��][1-2��]");
		
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
