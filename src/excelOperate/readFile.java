package excelOperate;

import java.io.File;
import jxl.Sheet;
import jxl.Workbook;
//��ȡcsv�ļ����ӵ����п�ʼ��ȡ�γ��ܱ�����Ϣ
����������̵��ļ���ֻ�Ǹ�������ô����=-=�Ҹո����ľ����������°棬���buttonlog���Ҹո��ϴ���
public class readFile {
	static String sourceFile ="�Ρ����ܱ�.xls"; //Դ�ļ�
    public static void main(String[]args) 
    {
       try
       {
              Workbook book = Workbook.getWorkbook(new File("�γ��ܱ�.xls"));
              //0�����һ�����������
              Sheet sheet =book.getSheet(0);
              int rows =sheet.getRows();
              int cols =sheet.getColumns();
              String colname1 =sheet.getCell(0, 0).getContents().trim();
              String colname2 =sheet.getCell(1, 0).getContents().trim();
              String colname3 =sheet.getCell(2, 0).getContents().trim();
              System.out.println(colname1+","+colname2+","+colname3);
              for (int z = 1; z <rows; z++)
              {
                  //0����������z��������
                  String name =sheet.getCell(0, z).getContents();
                  String sex =sheet.getCell(1, z).getContents();
                  String ID =sheet.getCell(2, z).getContents();
                  System.out.println(name+","+sex+","+ID);
              } 
       }
              catch(Exception e)
              {
                  e.printStackTrace();
              }
    }
}
