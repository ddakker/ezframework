/* ====================================================================
   Licensed to the Apache Software Foundation (ASF) under one or more
   contributor license agreements.  See the NOTICE file distributed with
   this work for additional information regarding copyright ownership.
   The ASF licenses this file to You under the Apache License, Version 2.0
   (the "License"); you may not use this file except in compliance with
   the License.  You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
==================================================================== */

package pe.kr.ddakker.ezframework.web.servlet.view.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.openxml4j.opc.internal.ZipHelper;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellReference;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.servlet.view.AbstractView;

/**
 * Demonstrates a workaround you can use to generate large workbooks and avoid OutOfMemory exception.
 *
 * The trick is as follows:
 * 1. create a template workbook, create sheets and global objects such as cell styles, number formats, etc.
 * 2. create an application that streams data in a text file
 * 3. Substitute the sheet in the template with the generated data
 *
 * <p>
 *      Since 3.8-beta3 POI provides a low-memory footprint SXSSF API which implementing the "BigGridDemo" strategy.
 *      XSSF is an API-compatible streaming extension of XSSF to be used when
 *      very large spreadsheets have to be produced, and heap space is limited.
 *      SXSSF achieves its low memory footprint by limiting access to the rows that
 *      are within a sliding window, while XSSF gives access to all rows in the
 *      document. Older rows that are no longer in the window become inaccessible,
 *      as they are written to the disk.
 * </p>
 * See <a "http://poi.apache.org/spreadsheet/how-to.html#sxssf">
 *     http://poi.apache.org/spreadsheet/how-to.html#sxssf</a>.

 *
 * @author Yegor Kozlov
 */
/**
 * 엑셀 대용량 다운로드
 * 약 100만건 정도까지 가능함.
 * out of memory 발생안함.
 * @author ljc727
 *
 */
public class ExcelBigGrid extends AbstractView {
    
	private static final String XML_ENCODING = "UTF-8";
   
    
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request, HttpServletResponse response) throws Exception {
		
        // Step 1. Create a template file. Setup sheets and workbook-level objects such as
        // cell styles, number formats, etc.

        XSSFWorkbook wb = new XSSFWorkbook();
        XSSFSheet sheet = wb.createSheet("Big Grid");

        Map<String, XSSFCellStyle> styles = createStyles(wb);
        //name of the zip entry holding sheet data, e.g. /xl/worksheets/sheet1.xml
        String sheetRef = sheet.getPackagePart().getPartName().getName();

        //save the template
        FileOutputStream os = new FileOutputStream("template.xlsx");
        wb.write(os);
        os.close();

        //Step 2. Generate XML file.
        File tmp = File.createTempFile("sheet", ".xml");
        Writer fw = new OutputStreamWriter(new FileOutputStream(tmp), XML_ENCODING);
        generate(model, fw, styles);
        fw.close();

        //Step 3. Substitute the template entry with the generated data
        FileOutputStream out = new FileOutputStream(tmp.getPath() + "big-grid.xlsx");
        substitute(new File("template.xlsx"), tmp, sheetRef.substring(1), out);
        //
        out.close();
        
        
        response.setContentType("application/vnd.ms-excel"); 
        response.setHeader("Content-Disposition","attachment;filename=\"" + System.currentTimeMillis() + ".xlsx\""); 
        ServletOutputStream servletOutputStream = response.getOutputStream();
        
        FileInputStream fin = new FileInputStream(tmp.getPath() + "big-grid.xlsx"); 
        byte[] chunk = new byte[1024]; 
        int count; 
        while ((count = fin.read(chunk)) >=0 ) { 
        	servletOutputStream.write(chunk,0,count); 
        } 

        fin.close();
        servletOutputStream.close();
		
	}


    @SuppressWarnings({ "unchecked", "rawtypes" })
	private static void generate(Map<String, Object> model, Writer out, Map<String, XSSFCellStyle> styles) throws Exception {

		List<Object> colValue = (List<Object>) model.get(ExcelView.EXCEL_VALUE);
		String[] colName = ((String) model.get(ExcelView.EXCEL_DATA)).split("//");
		

		List<Map<String, String>> colNameList = new ArrayList<Map<String,String>>();

		for (String v : colName) {
			String[] tmp = v.split(",");
			Map<String, String> hm = new HashMap<String, String>();
			hm.put("colMeta", tmp[0]);
			hm.put("colName", tmp[1]);
			colNameList.add(hm);
		}
		
		SpreadsheetWriter sw = new SpreadsheetWriter(out);
        sw.beginSheet();
        sw.insertRow(0);
        int styleIndex = styles.get("header").getIndex();
        for (int i = 0; i < colNameList.size(); i++) {
        	sw.createCell(i, colNameList.get(i).get("colName"), styleIndex);
        }
        sw.endRow();
        
        
		for (int i = 0; i < colValue.size(); i++) {
			// 메뉴 ROW가 있기때문에 +1
			sw.insertRow(i + 1);
			
			
			Map map = null;
			if (colValue.get(i) instanceof Map) {
				map = (Map) colValue.get(i);
			} else {
				map = ConverObjectToMap(colValue.get(i));
			}
			
			for (int j = 0; j < colNameList.size(); j++) {
				String value = String.valueOf(map.get(colNameList.get(j).get("colMeta"))).replaceAll("&", "＆");
								
				if ("null".equals(value) || "NaN".equals(value)) {
					value = "";					
				}
				
				int style = 0;
				try {
					Integer.parseInt(value);
					style = styles.get("number").getIndex();
				} catch (NumberFormatException ne) {
					style = styles.get("text").getIndex();
				}
				
				sw.createCell(j, value, style);
			}
			sw.endRow();
		}
		sw.endSheet();
    }

    
	/**
	 * 엑셀 컬럼값을 as에서 동적으로 갖고와서, Vo를 참조하기위해
	 * Map형태로 변경
	 * @param obj
	 * @return Vo객체 클래스가 매핑된 Map
	 * @author ljc727 2014. 8. 7.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static Map ConverObjectToMap(Object obj){
		try {
			List<Field> fieldsMap = new ArrayList<Field>();
			Class<?> clzParam = obj.getClass();
			while (clzParam != null && clzParam != Object.class) {
				Field[] field = clzParam.getDeclaredFields();
				for (Field f : field) {
					fieldsMap.add(f);
				}
				clzParam = clzParam.getSuperclass();
			}
			Map resultMap = new HashMap();
			for (Field field : fieldsMap) {
				field.setAccessible(true);
				resultMap.put(field.getName(), field.get(obj));
			}

			return resultMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
    }
    
    
    /**
     * Create a library of cell styles.
     */
    private static Map<String, XSSFCellStyle> createStyles(XSSFWorkbook wb){
        Map<String, XSSFCellStyle> styles = new HashMap<String, XSSFCellStyle>();
        XSSFDataFormat fmt = wb.createDataFormat();

        // 헤더
        XSSFCellStyle style1 = wb.createCellStyle();
        XSSFFont headerFont = wb.createFont();
        headerFont.setBold(true);
        style1.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style1.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
        style1.setFont(headerFont);
        style1.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        setBorder(style1, XSSFCellStyle.BORDER_THIN);
        styles.put("header", style1);
        
        // 내용 (text)
        XSSFCellStyle style2 = wb.createCellStyle();
        style2.setAlignment(XSSFCellStyle.ALIGN_CENTER);
        setBorder(style2, XSSFCellStyle.BORDER_THIN);
		styles.put("text", style2);
		
        // 내용 (숫자)
        XSSFCellStyle style3 = wb.createCellStyle();
        style3.setAlignment(XSSFCellStyle.ALIGN_RIGHT);
        setBorder(style3, XSSFCellStyle.BORDER_THIN);
        style3.setDataFormat(fmt.getFormat("0"));
		styles.put("number", style3);

        return styles;
    }
    
    
    /**
     * Set the type of border to use for the left border of the cell
     * @param border the type of border to use
     * @see org.apache.poi.ss.usermodel.CellStyle#BORDER_NONE
     * @see org.apache.poi.ss.usermodel.CellStyle#BORDER_THIN
     * @see org.apache.poi.ss.usermodel.CellStyle#BORDER_MEDIUM
     * @see org.apache.poi.ss.usermodel.CellStyle#BORDER_DASHED
     * 2번째 파람은 BORDER_NONE, BORDER_THIN 상수로 던지면 됨.
     * CellStyle 클래스 참조하면 더 있음.
     */
    private static void setBorder(XSSFCellStyle style, short thickness) {
    	style.setBorderBottom(thickness);
    	style.setBorderLeft(thickness);
    	style.setBorderRight(thickness);
    	style.setBorderTop(thickness);
    }

    
    /**
     *
     * @param zipfile the template file
     * @param tmpfile the XML file with the sheet data
     * @param entry the name of the sheet entry to substitute, e.g. xl/worksheets/sheet1.xml
     * @param out the stream to write the result to
     */
	private static void substitute(File zipfile, File tmpfile, String entry, OutputStream out) throws IOException {
        ZipFile zip = ZipHelper.openZipFile(zipfile);

        ZipOutputStream zos = new ZipOutputStream(out);

        @SuppressWarnings("unchecked")
        Enumeration<ZipEntry> en = (Enumeration<ZipEntry>) zip.entries();
        while (en.hasMoreElements()) {
            ZipEntry ze = en.nextElement();
            if(!ze.getName().equals(entry)){
                zos.putNextEntry(new ZipEntry(ze.getName()));
                InputStream is = zip.getInputStream(ze);
                copyStream(is, zos);
                is.close();
            }
        }
        zos.putNextEntry(new ZipEntry(entry));
        InputStream is = new FileInputStream(tmpfile);
        copyStream(is, zos);
        is.close();

        zos.close();
    }

	
    private static void copyStream(InputStream in, OutputStream out) throws IOException {
        byte[] chunk = new byte[1024];
        int count;
        while ((count = in.read(chunk)) >=0 ) {
          out.write(chunk,0,count);
        }
    }

    
    /**
     * Writes spreadsheet data in a Writer.
     * (YK: in future it may evolve in a full-featured API for streaming data in Excel)
     */
    public static class SpreadsheetWriter {
        private final Writer _out;
        private int _rownum;

        public SpreadsheetWriter(Writer out){
            _out = out;
        }

        public void beginSheet() throws IOException {
            _out.write("<?xml version=\"1.0\" encoding=\""+XML_ENCODING+"\"?>" +
                    "<worksheet xmlns=\"http://schemas.openxmlformats.org/spreadsheetml/2006/main\">" );
            _out.write("<sheetData>\n");
        }

        public void endSheet() throws IOException {
            _out.write("</sheetData>");
            _out.write("</worksheet>");
        }

        /**
         * Insert a new row
         *
         * @param rownum 0-based row number
         */
        public void insertRow(int rownum) throws IOException {
            _out.write("<row r=\""+(rownum+1)+"\">\n");
            this._rownum = rownum;
        }

        /**
         * Insert row end marker
         */
        public void endRow() throws IOException {
            _out.write("</row>\n");
        }

        public void createCell(int columnIndex, String value, int styleIndex) throws IOException {
            String ref = new CellReference(_rownum, columnIndex).formatAsString();
            _out.write("<c r=\""+ref+"\" t=\"inlineStr\"");
            if(styleIndex != -1) _out.write(" s=\""+styleIndex+"\"");
            _out.write(">");
            _out.write("<is><t>"+value+"</t></is>");
            _out.write("</c>");
        }

        public void createCell(int columnIndex, String value) throws IOException {
            createCell(columnIndex, value, -1);
        }

        public void createCell(int columnIndex, double value, int styleIndex) throws IOException {
            String ref = new CellReference(_rownum, columnIndex).formatAsString();
            _out.write("<c r=\""+ref+"\" t=\"n\"");
            if(styleIndex != -1) _out.write(" s=\""+styleIndex+"\"");
            _out.write(">");
            _out.write("<v>"+value+"</v>");
            _out.write("</c>");
        }

        public void createCell(int columnIndex, double value) throws IOException {
            createCell(columnIndex, value, -1);
        }

        public void createCell(int columnIndex, Calendar value, int styleIndex) throws IOException {
            createCell(columnIndex, DateUtil.getExcelDate(value, false), styleIndex);
        }
    }

}