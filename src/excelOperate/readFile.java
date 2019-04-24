package excelOperate;

import java.io.File;
import jxl.Sheet;
import jxl.Workbook;
//读取csv文件，从第五行开始读取课程总表中信息
不是这个工程的文件我只是告诉你怎么更新=-=我刚刚做的就是下载最新版，这个buttonlog是我刚刚上传的
public class readFile {
	static String sourceFile ="课。程总表.xls"; //源文件
    public static void main(String[]args) 
    {
       try
       {
              Workbook book = Workbook.getWorkbook(new File("课程总表.xls"));
              //0代表第一个工作表对象
              Sheet sheet =book.getSheet(0);
              int rows =sheet.getRows();
              int cols =sheet.getColumns();
              String colname1 =sheet.getCell(0, 0).getContents().trim();
              String colname2 =sheet.getCell(1, 0).getContents().trim();
              String colname3 =sheet.getCell(2, 0).getContents().trim();
              System.out.println(colname1+","+colname2+","+colname3);
              for (int z = 1; z <rows; z++)
              {
                  //0代表列数，z代表行数
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
