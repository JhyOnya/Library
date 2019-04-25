package excelOperate;

import java.io.File;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;


//��ȡcsv�ļ����ӵ����п�ʼ��ȡ�γ��ܱ�����Ϣ111
public class readFile {
	static String sourceFile = "�γ��ܱ�.xls"; // Դ�ļ�
	// ��ȡԴ�ļ�

	public static void readFile(String fileNameString) {
		try {
			Workbook book = Workbook.getWorkbook(new File(fileNameString));
			// ��ȡ��һ��������
			Sheet sheet = book.getSheet(0);
			int rows = sheet.getRows();
			int cols = sheet.getColumns();
			System.out.println("��������" + rows + "    ��������" + cols);
			// Cell cell=sheet.getCell(5,5);
			// System.out.print("�����ǣ�"+cell.getContents()+" ");

			for (int i = 4; i < sheet.getRows() / 2; i++) {
				for (int j = 1; j < sheet.getColumns() / 2; j++) {
					Cell cell = sheet.getCell(j, i);
					if (cell.getContents().isEmpty())
						System.out.println("��" + i + "��" + j + " ���");
					else {
						System.out.println("��" + i + "��" + j + "����Ϊ��");
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
