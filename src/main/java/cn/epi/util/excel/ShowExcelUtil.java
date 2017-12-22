package cn.epi.util.excel;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFDataFormat;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import cn.epi.datasource.entity.FileSource;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class ShowExcelUtil {
	/**
	 * 解决思路：采用Apache的POI的API来操作Excel，读取内容后保存到List中，再将List转Json（推荐Linked，增删快，
	 * 与Excel表顺序保持一致）
	 * 
	 * Sheet表1 ————> List1<Map<列头，列值>> Sheet表2 ————> List2<Map<列头，列值>>
	 * 
	 * 步骤1：根据Excel版本类型创建对于的Workbook以及CellSytle 步骤2：遍历每一个表中的每一行的每一列
	 * 步骤3：一个sheet表就是一个Json，多表就多Json，对应一个 List 一个sheet表的一行数据就是一个 Map
	 * 一行中的一列，就把当前列头为key，列值为value存到该列的Map中
	 * 
	 * 
	 * @param file
	 *            SSM框架下用户上传的Excel文件
	 * @return Map
	 *         一个线性HashMap，以Excel的sheet表顺序，并以sheet表明作为key，sheet表转换json后的字符串作为value
	 * @throws IOException
	 */
	/*
	 * public LinkedHashMap<String,String> excel2json(File file) throws
	 * IOException {
	 */
	public JSONObject excel2json (File file) throws IOException{
		System.out.println("excel2json方法执行....");
		// 返回的map
	LinkedHashMap<String, String> excelMap = new LinkedHashMap<>();
         JSONObject jo = new JSONObject();
		// Excel列的样式，主要是为了解决Excel数字科学计数的问题
		CellStyle cellStyle;
		// 根据Excel构成的对象
		Workbook wb;
		// 如果是2007及以上版本，则使用想要的Workbook以及CellStyle
		if (file.getName().endsWith("xlsx")) {
			System.out.println("是2007及以上版本  xlsx");
			wb = new XSSFWorkbook(
					file.getAbsolutePath());
			XSSFDataFormat dataFormat = (XSSFDataFormat) wb.createDataFormat();
			cellStyle = wb.createCellStyle();
			// 设置Excel列的样式为文本
			cellStyle.setDataFormat(dataFormat.getFormat("@"));
		} else {
			System.out.println("是2007以下版本  xls");
			InputStream inputstream = new FileInputStream(file);
			POIFSFileSystem fs = new POIFSFileSystem(inputstream);
			wb = new HSSFWorkbook(fs);
			HSSFDataFormat dataFormat = (HSSFDataFormat) wb.createDataFormat();
			cellStyle = wb.createCellStyle();
			// 设置Excel列的样式为文本
			cellStyle.setDataFormat(dataFormat.getFormat("@"));
		}

		// sheet表个数
		int sheetsCounts = wb.getNumberOfSheets();
		// 遍历每一个sheet
		for (int i = 0; i < sheetsCounts; i++) {
			Sheet sheet = wb.getSheetAt(i);
			System.out.println("第" + i + "个sheet:" + sheet.toString());

			// 一个sheet表对于一个List
			List list = new LinkedList();
			LinkedHashMap Map = new LinkedHashMap();
			JSONObject json_all = new JSONObject();
			// 将第一行的列值作为正个json的key
			String[] cellNames;
			// 取第一行列的值作为key
			Row fisrtRow = sheet.getRow(0);
			// 如果第一行就为空，则是空sheet表，该表跳过
			if (null == fisrtRow) {
				continue;
			}
			// 得到第一行有多少列
			int curCellNum = fisrtRow.getLastCellNum();
			System.out.println("第一行的列数：" + curCellNum);
			// 根据第一行的列数来生成列头数组
			cellNames = new String[curCellNum];
			// 单独处理第一行，取出第一行的每个列值放在数组中，就得到了整张表的JSON的key
			for (int m = 0; m < curCellNum; m++) {
				Cell cell = fisrtRow.getCell(m);
				if (null == cell) {
					continue;
				}
				// 设置该列的样式是字符串
				cell.setCellStyle(cellStyle);
				cell.setCellType(Cell.CELL_TYPE_STRING);
				// 取得该列的字符串值
				cellNames[m] = cell.getStringCellValue();
			}
			for (String s : cellNames) {
				System.out.print("得到第" + i + " 张sheet表的列头： " + s + ",");
			}

			// 从第二行起遍历每一行
			int rowNum = sheet.getLastRowNum();
			System.out.println("总共有 " + rowNum + " 行");
			LinkedHashMap rowMap1 = new LinkedHashMap();
			String[] cellValues = new String[curCellNum];
			for (int j = 1; j <= rowNum; j++) {

				// 一行数据对于一个Map
				LinkedHashMap rowMap = new LinkedHashMap();

				// 取得某一行
				Row row = sheet.getRow(j);
				int cellNum = row.getLastCellNum();
				//System.out.println("总共有 " + cellNum + "列");

				// 遍历每一列

				for (int k = 0; k < curCellNum; k++) {
					Cell cell = row.getCell(k);
					if (null == cell) {
						continue;
					}
					/*
					 * cell.setCellStyle(cellStyle);
					 * cell.setCellType(Cell.CELL_TYPE_STRING);
					 */
					// 保存该单元格的数据到该行中

					if ("string".equals(cellValues[k])) {
						cellValues[k] = "string";
					} else {
						cellValues[k] = getCellTypev(cell);
					}
					// System.out.println(cellValues[k]);
					String key = cellNames[k];
					if(key == null){
						key = "f"+k;
					}
					rowMap.put(key, getCellValue(cell, true));
					
				}
				for (int l = 0; l < curCellNum; l++) {
					rowMap1.put(cellNames[l], cellValues[l]);
					// 保存该行的数据到该表的List中
				}
				list.add(rowMap);

			}
			Map.put("meta", rowMap1);
			Map.put("data", list);
			// 将该sheet表的表名为key，List转为json后的字符串为Value进行存储
			jo.put(sheet.getSheetName(), JSON.toJSONString(Map, false));
			
		}
		System.out.println(jo);
		System.out.println("excel2json方法结束....");

		 return jo; 
	}

	private static String getCellTypev(Cell cell/* , boolean treatAsStr */) {
		if (cell == null) {
			return "";
		}

		if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			return "TF";
		} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			return "num";
		} else {
			return "string";
		}
	}

	private static String getCellValue(Cell cell, boolean treatAsStr) {
		if (cell == null) {
			return "";
		}
		if (treatAsStr) {
			// 虽然excel中设置的都是文本，但是数字文本还被读错，如“1”取成“1.0”
			// 加上下面这句，临时把它当做文本来读取
			cell.setCellType(Cell.CELL_TYPE_STRING);
		}
		if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
			return cell.getStringCellValue();
		} else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
			return String.valueOf(cell.getBooleanCellValue());
		} else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
			return cell.getCellFormula();
		} else if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
			short format = cell.getCellStyle().getDataFormat();
			System.out.println("format:" + format + ";;;;;value:"
					+ cell.getNumericCellValue());
			SimpleDateFormat sdf = null;
			if (format == 14 || format == 31 || format == 57 || format == 58
					|| (176 <= format && format <= 178)
					|| (182 <= format && format <= 196)
					|| (210 <= format && format <= 213) || (208 == format)) { // 日期
				sdf = new SimpleDateFormat("yyyy-MM-dd");
			} else if (format == 20 || format == 32 || format == 183
					|| (200 <= format && format <= 209)) { // 时间
				sdf = new SimpleDateFormat("HH:mm");
			} else { // 不是日期格式
				return String.valueOf(cell.getNumericCellValue());
			}
			double value = cell.getNumericCellValue();
			Date date = org.apache.poi.ss.usermodel.DateUtil.getJavaDate(value);
			if (date == null || "".equals(date)) {
				return "";
			}
			String result = "";
			try {
				result = sdf.format(date);
			} catch (Exception e) {
				e.printStackTrace();
				return "";
			}
			return result;
		}
		return "";
	}
}
