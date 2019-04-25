package excelOperate;

import java.io.File;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;


//读取csv文件，从第五行开始读取课程总表中信息111
public class readFile {
	static String sourceFile = "课程总表.xls"; // 源文件
	// 读取源文件

	public static void readFile(String fileNameString) {
		try {
			Workbook book = Workbook.getWorkbook(new File(fileNameString));
			// 获取第一个工作表
			Sheet sheet = book.getSheet(0);
			int rows = sheet.getRows();
			int cols = sheet.getColumns();
			System.out.println("总行数：" + rows + "    总列数：" + cols);
			// Cell cell=sheet.getCell(5,5);
			// System.out.print("內容是："+cell.getContents()+" ");

			for (int i = 4; i < sheet.getRows() / 2; i++) {
				for (int j = 1; j < sheet.getColumns() / 2; j++) {
					Cell cell = sheet.getCell(j, i);
					if (cell.getContents().isEmpty())
						System.out.println("行" + i + "列" + j + " 為空");
					else {
						System.out.println("行" + i + "列" + j + "内容为：");
						System.out.println(cell.getContents());
					}
				}
			}
			book.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		readFile(sourceFile);
	}
}
