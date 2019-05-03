package excelOperate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

//读取csv文件，从第五行开始读取课程总表中信息111
public class readFile
{ 
	public static Map<String, Map<Integer, String[]>> courseMap;
	// public static List<Course> courses = new ArrayList<Course>();
	static String sourceFile = "课程总表.xls"; // 源文件
	// 读取源文件

	public static void readFile(String fileNameString)
	{
		Course e = new Course();
		courseMap = new TreeMap<String, Map<Integer, String[]>>();
		try
		{
			Workbook book = Workbook.getWorkbook(new File(fileNameString));
			// 获取第一个工作表
			Sheet sheet = book.getSheet(0);
			int rows = sheet.getRows();
			int cols = sheet.getColumns();
			// System.out.println("总行数：" + rows + " 总列数：" + cols);
			// Cell cell=sheet.getCell(5,5);
			// System.out.print("內容是："+cell.getContents()+" ");
			for (int i = 4; i < sheet.getRows()-1; i++)
			{
				List<String> lineString = new ArrayList<String>();
				for (int j = 0; j < sheet.getColumns()-1; j++)
				{
					Cell cell = sheet.getCell(j, i);
					lineString.add(cell.getContents());
					if (cell.getContents().isEmpty())
					{
					} // System.out.println("行" + i + "列" + j + " 為空" +
						// cell.getContents());// +lineString.get(i)
					else
					{
						// System.out.println("行" +i+ "列" + j + "内容为：");
						// System.out.println(cell.getContents());
					}
				} // 加入一行的课
				e.init(lineString); // 初始化某个专业的课程 即一行
				courseMap.putAll(e.courseMap);
				// courses.add(e);
				/*for(int z=0;z<sheet.getColumns();z++) {
					Cell cell = sheet.getCell(z, 5);
					lineString.add(cell.getContents());
					if (cell.getContents().isEmpty())
					{
					  System.out.println("行" + 5 + "列" + z + " 為空" +
						 cell.getContents());// +lineString.get(i)
					}
					else
					{
						 System.out.println("行" +5+ "列" + z + "内容为：");
						 System.out.println(cell.getContents());
					}
				}*/
				
				
				
			}
			book.close();
			/*
			 * Cell cell1 = sheet.getCell(26, 4); System.out.println("行" +26+
			 * "列" + 4 + "内容为："); System.out.println(cell1.getContents());
			 * 
			 * Cell cell2 = sheet.getCell(27, 4); System.out.println("行" +27+
			 * "列" + 4 + "内容为："); System.out.println(cell2.getContents());
			 */
		}
		catch (Exception e1)
		{
			e1.printStackTrace();
		}
	}

	public static void main(String[] args)
	{
		String[] aStrings= new String[5];
		readFile(sourceFile);
		Course aCourse = new Course();
		if (courseMap.containsKey("1720102"))
		{
			courseMap.get("英语1720102");
			// System.out.println(courseMap.get("英语1720102"));
			courseMap.get("1720102");
			aStrings=courseMap.get("1720102").get(3);
			System.out.println(aStrings[0]);
			System.out.println(aStrings[1]);
			System.out.println(aStrings[2]);
			System.out.println(aStrings[3]);
			System.out.println(aStrings[4]);

		}
		// for (int i = 0; i < courseMap.size(); i++) {
		// if(courseMap.get(i).majorNum.equals("英语1720102"))
		// courseMap.get(i).returnThisClassWeeks(1, 1);
		// System.out.println(courseMap.get(i).returnThisClassWeeks(0, 0));
		// }
		// String[] aStrings= new String[5];
		// aCourse.printString(aCourse.returnClassWeek("英语1720102", 5));
		// aCourse.returnClassWeek("英语1720102", 5);
		// System.out.println(aStrings[0]);
	}
}
