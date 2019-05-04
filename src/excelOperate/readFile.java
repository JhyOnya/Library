package excelOperate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

//��ȡcsv�ļ����ӵ����п�ʼ��ȡ�γ��ܱ�����Ϣ111
public class readFile
{ 
	public static Map<String, String[]> courseMap;
	// public static List<Course> courses = new ArrayList<Course>();
	static String sourceFile = "�γ��ܱ�.xls"; // Դ�ļ�
	// ��ȡԴ�ļ�

	public static void readFile(String fileNameString)
	{
		Course e = new Course();
		courseMap = new TreeMap<String, String[]>();
		try
		{
			Workbook book = Workbook.getWorkbook(new File(fileNameString));
			// ��ȡ��һ��������
			Sheet sheet = book.getSheet(0);
			int rows = sheet.getRows();
			int cols = sheet.getColumns();
			for (int i = 4; i < sheet.getRows()-1; i++)
			{
				 // ����һ�еĿ�
				List<String> lineString = new ArrayList<String>();
				for (int j = 0; j < sheet.getColumns()-1; j++)
				{
					Cell cell = sheet.getCell(j, i);
					lineString.add(cell.getContents());
				}
				e.init(lineString); // ��ʼ��ĳ��רҵ�Ŀγ� ��һ��
				courseMap.putAll(e.courseMap);		
			}
			book.close();
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		String[] aStrings= new String[25];
		readFile(sourceFile);
		Course aCourse = new Course();
		if (courseMap.containsKey("1720102"))
		{
			courseMap.get("Ӣ��1720102");
			// System.out.println(courseMap.get("Ӣ��1720102"));
			courseMap.get("1720102");
			aStrings=courseMap.get("1720101");//����������� ��һ��12345�ڿ�
			System.out.println(aStrings[Course.getIndex(1, 1)]);
			System.out.println(aStrings[Course.getIndex(1, 2)]);
			System.out.println(aStrings[Course.getIndex(1, 3)]);
			System.out.println(aStrings[Course.getIndex(1, 4)]);
			System.out.println(aStrings[Course.getIndex(1, 5)]);
		}
	}
}
