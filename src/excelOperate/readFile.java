package excelOperate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

//读取csv文件，从第五行开始读取课程总表中信息111
public class readFile {
	public static List<Course> courses = new ArrayList<Course>();
	static String sourceFile = "课程总表.xls"; // 源文件
	// 读取源文件

	public static void readFile(String fileNameString) {
		try {
			Workbook book = Workbook.getWorkbook(new File(fileNameString));
			// 获取第一个工作表
			Sheet sheet = book.getSheet(0);
			int rows = sheet.getRows();
			int cols = sheet.getColumns();
			// System.out.println("总行数：" + rows + " 总列数：" + cols);
			// Cell cell=sheet.getCell(5,5);
			// System.out.print("內容是："+cell.getContents()+" ");
			for (int i = 4; i < sheet.getRows() / 2; i++) {
				List<String> lineString = new ArrayList<String>();
				Course e=new Course();
				for (int j = 0; j < sheet.getColumns(); j++) {
					Cell cell = sheet.getCell(j, i);
					lineString.add(cell.getContents());
					if (cell.getContents().isEmpty())
					{}//System.out.println("行" + i + "列" + j + " 為空" + cell.getContents());// +lineString.get(i)
					else {
						// System.out.println("行" +i+ "列" + j + "内容为：");
						//System.out.println(cell.getContents());
					}
				}//加入一行的课
				
				System.out.println(lineString.size());
				e.init(lineString); //初始化某个专业的课程
				courses.add(e);
			}
			book.close();
			/*
			 * Cell cell1 = sheet.getCell(26, 4); System.out.println("行" +26+ "列" + 4 +
			 * "内容为："); System.out.println(cell1.getContents());
			 * 
			 * Cell cell2 = sheet.getCell(27, 4); System.out.println("行" +27+ "列" + 4 +
			 * "内容为："); System.out.println(cell2.getContents());
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		readFile(sourceFile);
		Course aCourse=new Course();
		
		//String[] aStrings= new String[5];
		//aCourse.printString(aCourse.returnClassWeek("英语1720102", 5));
		aCourse.returnClassWeek("英语1720102", 5);
		//System.out.println(aStrings[0]);
	}
}
