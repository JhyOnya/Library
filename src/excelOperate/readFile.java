package excelOperate;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

//��ȡcsv�ļ����ӵ����п�ʼ��ȡ�γ��ܱ�����Ϣ111
public class readFile {
	public static List<Course> courses = new ArrayList<Course>();
	static String sourceFile = "�γ��ܱ�.xls"; // Դ�ļ�
	// ��ȡԴ�ļ�

	public static void readFile(String fileNameString) {
		try {
			Workbook book = Workbook.getWorkbook(new File(fileNameString));
			// ��ȡ��һ��������
			Sheet sheet = book.getSheet(0);
			int rows = sheet.getRows();
			int cols = sheet.getColumns();
			// System.out.println("��������" + rows + " ��������" + cols);
			// Cell cell=sheet.getCell(5,5);
			// System.out.print("�����ǣ�"+cell.getContents()+" ");
			for (int i = 4; i < sheet.getRows() / 2; i++) {
				List<String> lineString = new ArrayList<String>();
				Course e=new Course();
				for (int j = 0; j < sheet.getColumns(); j++) {
					Cell cell = sheet.getCell(j, i);
					lineString.add(cell.getContents());
					if (cell.getContents().isEmpty())
					{}//System.out.println("��" + i + "��" + j + " ���" + cell.getContents());// +lineString.get(i)
					else {
						// System.out.println("��" +i+ "��" + j + "����Ϊ��");
						//System.out.println(cell.getContents());
					}
				}//����һ�еĿ�
				
				System.out.println(lineString.size());
				e.init(lineString); //��ʼ��ĳ��רҵ�Ŀγ�
				courses.add(e);
			}
			book.close();
			/*
			 * Cell cell1 = sheet.getCell(26, 4); System.out.println("��" +26+ "��" + 4 +
			 * "����Ϊ��"); System.out.println(cell1.getContents());
			 * 
			 * Cell cell2 = sheet.getCell(27, 4); System.out.println("��" +27+ "��" + 4 +
			 * "����Ϊ��"); System.out.println(cell2.getContents());
			 */

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {

		readFile(sourceFile);
		Course aCourse=new Course();
		
		//String[] aStrings= new String[5];
		//aCourse.printString(aCourse.returnClassWeek("Ӣ��1720102", 5));
		aCourse.returnClassWeek("Ӣ��1720102", 5);
		//System.out.println(aStrings[0]);
	}
}
