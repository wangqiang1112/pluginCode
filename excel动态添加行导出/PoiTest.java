package net.htjs.action.cxtj.zdsy;

/**
 * Created by John on 2017/11/8.
 */
import java.io.*;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


public class PoiTest
{
    //将生成好的Excel文件，放到硬盘上
    public void writeToDisk(List<User> list){

        int end;
        HSSFRow row;
        HSSFSheet sheet;
        HSSFCell cell;
        HSSFWorkbook wb;
        OutputStream fos  = null;
        byte[] content;
        ByteArrayOutputStream os = new ByteArrayOutputStream();

        File file = new File("D://text.xls");//Excel文件生成后存储的位置
        try {
            if (file.exists()){
                wb = new HSSFWorkbook(new FileInputStream("D://text.xls"));
                sheet = wb.getSheet("sheet1");
                end = sheet.getLastRowNum();
                addData(list,end,sheet);
            }else{
                wb = new HSSFWorkbook();
                sheet = wb.createSheet("sheet1");
                row = sheet.createRow(0);

                //第一行的第一个单元格的值为  ‘序号’
                cell = row.createCell((short)0);
                cell.setEncoding(HSSFCell.ENCODING_UTF_16); //设置编码
                cell.setCellValue("序号");

                cell = row.createCell((short)1);
                cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                cell.setCellValue("姓");

                cell = row.createCell((short)2);
                cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                cell.setCellValue("名");

                cell = row.createCell((short)3);
                cell.setEncoding(HSSFCell.ENCODING_UTF_16);
                cell.setCellValue("年龄");

                addData(list,0,sheet);
            }

            wb.write(os);
            content = os.toByteArray();

            fos = new FileOutputStream(file);

            fos.write(content);
            os.close();
            fos.close();
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void addData(List<User> list,int endRow,HSSFSheet sheet){
        int count = 0;
        HSSFCell cell;
        HSSFRow row;
        int end = endRow + (list==null?0:list.size());

        //获得List中的数据，并将数据放到Excel中
        for(;endRow<end;endRow++){
            User user = list.get(count);
            count++;

            row = sheet.createRow(endRow+1);

            cell = row.createCell((short)0);
            cell.setEncoding(HSSFCell.ENCODING_UTF_16);
            cell.setCellValue(endRow+1);

            cell = row.createCell((short)1);
            cell.setEncoding(HSSFCell.ENCODING_UTF_16);
            cell.setCellValue(user.getFirstname());

            cell = row.createCell((short)2);
            cell.setEncoding(HSSFCell.ENCODING_UTF_16);
            cell.setCellValue(user.getLastname());

            cell = row.createCell((short)3);
            cell.setEncoding(HSSFCell.ENCODING_UTF_16);
            cell.setCellValue(user.getAge());

        }

    }

    public static void main(String[] args)
    {

        PoiTest pt = new PoiTest();
        User u = new User();
        List<User> list = u.findAllUser();
        pt.writeToDisk(list);

    }
}