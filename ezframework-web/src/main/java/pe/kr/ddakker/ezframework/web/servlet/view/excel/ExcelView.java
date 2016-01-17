package pe.kr.ddakker.ezframework.web.servlet.view.excel;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.hssf.util.Region;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractExcelView;

/**
 * 엑셀 데이터 자동화 <br>
 * (타이틀 선언부, 셀 합치기)
 *
 * @version 1.0
 * @author ljc727 2014. 8. 7.
 */
@SuppressWarnings("deprecation")
@Component("excelView")
public class ExcelView extends AbstractExcelView {

	/**
	 * [필수값] <td as="컬럼네임" > 입력 (쿼리에 as 입력)
	 */
	public static final String EXCEL_DATA = "ezwel_excel_data";

	/**
	 * [필수값] 엑셀에 실제 매핑될 값, 쿼리에서 매핑된 리스트값 입력
	 */
	public static final String EXCEL_VALUE = "ezwel_excel_value";

	/**
	 * [선택값] <td area="3"> 으로 입력하면 됨 기본은 1
	 */
	public static final String EXCEL_WIDTH = "ezwel_excel_width";

	/**
	 * [선택값] <td line="left"> 로 입력하면 됨 기본은 center
	 */
	public static final String EXCEL_ALIGN = "ezwel_excel_align";

	private static final String MULTI_MERGE_KEY = "m";

	private static final String MULTI_CELL_KEY = "c";

	/**
	 * 엑셀 컬럼의 width 기본값, POI라이브러리 참조
	 */
	private static final int BASIC_COLUMN_WIDTH = 2275;


	@SuppressWarnings("rawtypes")
	@Override
	protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook,
									  HttpServletRequest request, HttpServletResponse response) throws Exception {
		String excelName = System.currentTimeMillis() + "E";
		response.setContentType("Application/Msexcel");
		response.setHeader("Content-Disposition", "Attachment; Filename=" + excelName + ".xls");

		/**
		 * js에서 파싱되어진 데이터를 기반으로 엑셀 타이틀이 row갯수를 구분
		 */
		if (((String) model.get(EXCEL_DATA)).indexOf(", //") != -1) {
			HashMap infoMap = excelMultiCreateFile( (String) model.get(EXCEL_DATA) );
			excelMultiCell(model, workbook, response, excelName, infoMap);
		} else {
			singleRowExcel(model, workbook, response, excelName);
		}

	}

	/**
	 * 엑셀 타이틀이 한줄일때 실행됨
	 * @param model
	 * @param workbook
	 * @param response
	 * @param excelName
	 * @author ljc727 2014. 8. 8.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void singleRowExcel(Map<String, Object> model, HSSFWorkbook workbook, HttpServletResponse response, String excelName) {
		List<Object> colValue = (List<Object>) model.get(EXCEL_VALUE);
		String[] colName = ((String) model.get(EXCEL_DATA)).split("//");

		List<Map<String, String>> colNameList = new ArrayList<Map<String,String>>();

		for (String v : colName) {
			String[] tmp = v.split(",");
			Map<String, String> hm = new HashMap<String, String>();
			hm.put("colMeta", tmp[0]);
			hm.put("colName", tmp[1]);
			colNameList.add(hm);
		}

		HSSFSheet sheet = workbook.createSheet(excelName);
		HSSFRow menuRow = sheet.createRow(0);
		for (int i = 0; i < colNameList.size(); i++) {
			HSSFCell cell = menuRow.createCell(i);
			cell.setCellStyle(getMenuProperty(workbook));
			cell.setCellValue(new HSSFRichTextString(colNameList.get(i).get("colName")));
		}

        setSeetWidth(model, sheet);
        String[] alignInfo = setAlign(model);

		for (int i = 0; i < colValue.size(); i++) {
			// 메뉴 ROW가 있기때문에 +1
			HSSFRow row = sheet.createRow(i + 1);
			Map map = (Map) colValue.get(i);
			for (int j = 0; j < colNameList.size(); j++) {
				HSSFCell cell = row.createCell(j);
				cell.setCellStyle(getLineProperty(workbook,
						alignInfo == null ? HSSFCellStyle.ALIGN_CENTER : Short.parseShort(alignInfo[j]) ));

				Object value = map.get(colNameList.get(j).get("colMeta"));
				if (value != null) {
					try {
						cell.setCellValue(Double.parseDouble( (value + "" ).replaceAll(",", "") ));
					} catch (Exception e) {
						cell.setCellValue(value + "");
					}
				} else {
					cell.setCellValue("");
				}
			}
		}
	}

	/**
	 * 엑셀 타이틀이 2줄 이상일때 실행됨
	 * @param model
	 * @param workbook
	 * @param response
	 * @param excelName
	 * @param infoMap
	 * @throws Exception
	 * @author ljc727 2014. 8. 8.
	 */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public void excelMultiCell(Map<String, Object> model, HSSFWorkbook workbook, HttpServletResponse response,
    						   String excelName, HashMap infoMap) throws Exception {

    	ArrayList<HeightExcel> mergeList = (ArrayList<HeightExcel>) infoMap.get(MULTI_MERGE_KEY);
    	Object[] cellTitle = (Object[]) infoMap.get(MULTI_CELL_KEY);
    	List<Object> colValue = (List<Object>) model.get(EXCEL_VALUE);
		String[] colName = ((String) model.get(EXCEL_DATA)).split("//");
		String[] cellFunction = colName[colName.length - 1].split(",");
		HSSFSheet sheet = workbook.createSheet(excelName);

        // 셀에 속성 값 넣기
        HSSFRow row = null;
        for (int i = 0; i < cellTitle.length; i++) {
        	row = sheet.createRow(i);
        	for (int j = 0; j < ((String[]) cellTitle[i]).length; j++) {
        		HSSFCell cell = row.createCell(j);
        		cell.setCellStyle(getMenuProperty(workbook));
                cell.setCellValue(new HSSFRichTextString(((String[]) cellTitle[i]) [j]));
        	}
        }

        // 셀 머지
        for (int i = 0; i < mergeList.size(); i++) {
        	HeightExcel he = mergeList.get(i);
        	sheet.addMergedRegion(new Region(he.getLeftInt(), he.getLeftShort(), he.getRightInt(), he.getRightShort()));
        }

        setSeetWidth(model, sheet);
        String[] alignInfo = setAlign(model);

        // 실제 그리드 데이터 넣기
        for (int i = 0; i < colValue.size(); i++) {
	        row = sheet.createRow((short)(i + (cellTitle.length)));
            for (int s = 0; s < cellFunction.length; s++) {
            	Map map = ConverObjectToMap(colValue.get(i));
                HSSFCell cell = row.createCell(s);

                cell.setCellStyle(getLineProperty(workbook,
                		alignInfo == null ? HSSFCellStyle.ALIGN_CENTER : Short.parseShort(alignInfo[s]) ));

                Object value = map.get(cellFunction[s]);
                if (value != null) {
                	try {
                		cell.setCellValue( Double.parseDouble( (value + "").replaceAll(",", "") ));
                	} catch (Exception e) {
                		cell.setCellValue( value + "" );
                	}
                } else {
                	cell.setCellValue("");
                }

            }
        }

	}

    /**
     * 엑셀 값입력할때 정렬기능
     * 컨트롤러에서 EXCEL_ALIGN 값에 js에서 파싱된값을 넣어야 매핑됨
     * @param model
     * @return 셀단위 정렬정보
     * @author ljc727 2014. 8. 8.
     */
	private String[] setAlign(Map<String, Object> model) {
        if (model.get(EXCEL_ALIGN) != null) {
        	String[] alignInfo = ((String) model.get(EXCEL_ALIGN)).split(",");
        	for (int i = 0; i < alignInfo.length; i++) {
        		if (StringUtils.equals(alignInfo[i], "right")) {
        			alignInfo[i] = HSSFCellStyle.ALIGN_RIGHT + "";
        		} else if (StringUtils.equals(alignInfo[i], "left")) {
        			alignInfo[i] = HSSFCellStyle.ALIGN_LEFT + "";
        		} else {
        			alignInfo[i] = HSSFCellStyle.ALIGN_CENTER + "";
        		}
        	}
        	return alignInfo;
        }
    	return null;
    }

	/**
	 * 셀 넓이 설정
	 * 컨트롤러에서 EXCEL_WIDTH 값에 js에서 파싱된값을 넣어야 매핑됨
	 * @param model
	 * @param sheet
	 * @author ljc727 2014. 8. 8.
	 */
	private void setSeetWidth(Map<String, Object> model, HSSFSheet sheet) {
        if (model.get(EXCEL_WIDTH) != null) {
        	String[] widthArr = ((String) model.get(EXCEL_WIDTH)).split(",");
        	for (int i = 0; i < widthArr.length; i++) {
        		if (!StringUtils.equals(widthArr[i], "-1")) {
        			sheet.setColumnWidth(i, BASIC_COLUMN_WIDTH * Integer.parseInt(widthArr[i]));
        		} else {
        			sheet.setColumnWidth(i, BASIC_COLUMN_WIDTH);
        		}
        	}
        }
	}

    /**
     * 엑셀 타이틀이 2줄이상일때 셀 머지 작업을 실행하함
     * @param values
     * @return 머지할 셀 좌표
     * @author ljc727 2014. 8. 7.
     */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private HashMap excelMultiCreateFile(String values) {
		try {
			String execelData = values;
			String[] execelArr = execelData.split("//");
			Object[] cellTitle = new Object[execelArr.length - 1];

			ArrayList<HeightExcel> mergeList = new ArrayList<HeightExcel>(); // 최종 머지  정보
			ArrayList<Integer> widthList = new ArrayList<Integer>();
			for (int i = 0; i < cellTitle.length; i++) {
				String[] rowData = execelArr[i].trim().split(",");
				cellTitle[i] = new String[rowData.length];
				String equalStr = "";
				for (int k = 0; k < rowData.length; k++) {
					if (equalStr.equals(rowData[k])) {
						((String[]) cellTitle[i])[k] = "";
						widthList.add(k);
						if (k != rowData.length - 1)
							continue;
					}
					if (widthList.size() != 0) {
						int firstValue = widthList.get(0);
						widthList.add(firstValue - 1);
						int[] maxMinArr = maxMinValue(widthList);
						mergeList.add(new HeightExcel(i, i, (short) maxMinArr[1], (short) maxMinArr[0]));
					}
					widthList.clear();
					((String[]) cellTitle[i])[k] = rowData[k];
					equalStr = rowData[k];
				}
			}

			Set<String> set = new HashSet<String>();
			ArrayList<String> heigthList = new ArrayList<String>();
			for (int i = 0; i < ((String[]) cellTitle[0]).length; i++) {
				String tmp = "";
				for (int j = cellTitle.length - 1; j >= 0; j--) {
					if (tmp.equals(((String[]) cellTitle[j])[i]) && !tmp.equals("")) {
						set.add(i + "," + j);
						set.add(i + "," + (j + 1));
						continue;
					}
					tmp = ((String[]) cellTitle[j])[i];
				}
				if (set.size() != 0) {
					heigthList.add(set.toString().substring(1, set.toString().length() - 1));
				}
				set.clear();
			}

			for (int i = 0; i < heigthList.size(); i++) {
				String[] str = heigthList.get(i).split(", ");
				ArrayList<Integer> tmpList = new ArrayList<Integer>();
				for (int j = 0; j < str.length; j++) {
					tmpList.add(Integer.parseInt(str[j].substring(str[j].indexOf(",") + 1)));
				}
				int[] maxMinArr = maxMinValue(tmpList);
				short s = Short.parseShort(str[0].substring(0, str[0].indexOf(",")));
				mergeList.add(new HeightExcel(maxMinArr[1], maxMinArr[0], s, s));
			}

			HashMap reutrnMap = new HashMap();
			reutrnMap.put(MULTI_MERGE_KEY, mergeList);
			reutrnMap.put(MULTI_CELL_KEY, cellTitle);
			return reutrnMap;
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 엑셀 다운로드 컨트롤러에서 셀 머지시 좌표 최대값과 최소값을 구하드 메소드
	 * @param al 최대값과 최소값을 구할 어레이리스트
	 * @return 어레이 리스트 안에 있는 최대값과 최소값을 배열로 반환 idx[0] 최대값, idx[1] 최소값
	 */
	private int[] maxMinValue(ArrayList<Integer> al) {
		int max = al.get(0);
		int min = al.get(0);

		for (int i = 1; i < al.size(); i++) {
			if (max < al.get(i)) {
				max = al.get(i);
			}
			if (min > al.get(i)) {
				min = al.get(i);
			}
		}
		return new int[]{max, min};
	}

	/**
	 * 엑셀 머지할 셀 좌표 클래스
	 * @auther ljc727 2014. 8. 7.
	 */
	public class HeightExcel {
		private int leftInt, rightInt;
		private short leftShort, rightShort;

		private HeightExcel(int il, int ir, short sl, short sr) {
			leftInt = il;
			rightInt = ir;
			leftShort = sl;
			rightShort = sr;
		}
		public int getLeftInt() {
			return leftInt;
		}
		public int getRightInt() {
			return rightInt;
		}
		public short getLeftShort() {
			return leftShort;
		}
		public short getRightShort() {
			return rightShort;
		}
	}

	/**
	 * 엑셀 컬럼값을 as에서 동적으로 갖고와서, Vo를 참조하기위해
	 * Map형태로 변경
	 * @param obj
	 * @return Vo객체 클래스가 매핑된 Map
	 * @author ljc727 2014. 8. 7.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private Map ConverObjectToMap(Object obj){
        try {
            Field[] fields = obj.getClass().getDeclaredFields();
            Map resultMap = new HashMap();
			for (int i = 0; i <= fields.length - 1; i++) {
                fields[i].setAccessible(true);
                resultMap.put(fields[i].getName(), fields[i].get(obj));
            }
            return resultMap;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

	/**
	 * 엑셀 스타일지정
	 * 정리 필요
	 * @param workbook
	 * @return 타이틀 메뉴 row
	 * @author ljc727 2014. 8. 7.
	 */
	private HSSFCellStyle getMenuProperty(HSSFWorkbook workbook) {
		HSSFCellStyle style = workbook.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 가운데 정렬
        style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER); // 중앙정렬
        style.setFillForegroundColor(HSSFColor.GREY_25_PERCENT.index); // 속성 컬러 변경
        style.setBorderTop(HSSFCellStyle.BORDER_THIN); // 테두리 윗부분 진하게
        style.setBorderRight(HSSFCellStyle.BORDER_THIN); // 테두리 오른쪽 진하게
        style.setBorderLeft(HSSFCellStyle.BORDER_THIN); // 테두리 왼쪽 진하기
        style.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 테두리 아래 진하게
        style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND); // 색패턴 (안넣으면 색이 안바뀜)
        return style;
	}

	/**
	 * 엑셀 스타일지정
	 * 정리 필요
	 * @param workbook
	 * @param align 1 왼쪽, 2는 가운데, 3은 오른쪽
	 * @return 엑셀 데이터값 부분 스타일
	 * @author ljc727 2014. 8. 8.
	 */
	private HSSFCellStyle getLineProperty(HSSFWorkbook workbook, short align) {
		HSSFCellStyle style = workbook.createCellStyle();
		style.setAlignment(align);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setDataFormat(HSSFDataFormat.getBuiltinFormat("0"));
		return style;
	}

}