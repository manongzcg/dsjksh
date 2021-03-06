package cn.epi.util.excel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Test;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class ShowCSVUtil {
	private String fileName = null;
	private BufferedReader br = null;
	private List<String> list = new ArrayList<String>();

	public ShowCSVUtil() {

	}

	public ShowCSVUtil(String fileName) throws Exception {
		this.fileName = fileName;
		br = new BufferedReader(new FileReader(fileName));
		String stemp;
		while ((stemp = br.readLine()) != null) {
			list.add(stemp);
		}
	}

	public List getList() {
		return list;
	}

	/**
	 * 获取行数
	 * 
	 * @return
	 */
	public int getRowNum() {
		return list.size();
	}

	/**
	 * 获取列数
	 * 
	 * @return
	 */
	public int getColNum() {
		if (!list.toString().equals("[]")) {
			if (list.get(0).toString().contains(",")) {// csv为逗号分隔文件
				return list.get(0).toString().split(",").length;
			} else if (list.get(0).toString().trim().length() != 0) {
				return 1;
			} else {
				return 0;
			}
		} else {
			return 0;
		}
	}

	/**
	 * 获取制定行
	 * 
	 * @param index
	 * @return
	 */
	public String getRow(int index) {
		if (this.list.size() != 0) {
			return (String) list.get(index);
		} else {
			return null;
		}
	}

	/**
	 * 获取指定列
	 * 
	 * @param index
	 * @return
	 */
	public String getCol(int index) {
		if (this.getColNum() == 0) {
			return null;
		}
		StringBuffer sb = new StringBuffer();
		String tmp = null;
		int colnum = this.getColNum();
		if (colnum > 1) {
			for (Iterator it = list.iterator(); it.hasNext();) {
				tmp = it.next().toString();
				sb = sb.append(tmp.split(",")[index] + ",");
			}
		} else {
			for (Iterator it = list.iterator(); it.hasNext();) {
				tmp = it.next().toString();
				sb = sb.append(tmp + ",");
			}
		}
		String str = new String(sb.toString());
		str = str.substring(0, str.length() - 1);
		return str;
	}

	/**
	 * 获取某个单元格
	 * 
	 * @param row
	 * @param col
	 * @return
	 */
	public String getString(int row, int col) {
		String temp = null;
		int colnum = this.getColNum();
		if (colnum > 1) {
			temp = list.get(row).toString().split(",")[col];
		} else if (colnum == 1) {
			temp = list.get(row).toString();
		} else {
			temp = null;
		}
		return temp;
	}

	public void CsvClose() throws Exception {
		this.br.close();
	}

	/**
	 * 去表头
	 **/
	public String removehead(String str) {
		String[] str_1 = str.split(",");
		String sb = new String();
		for (int i = 1; i < str_1.length; i++) {
			sb = sb + str_1[i] + ",";
		}
		return sb;
	}

	public JSONArray readcsv(String path) {
		JSONArray array = new JSONArray();

		ShowCSVUtil util;

		try {
			util = new ShowCSVUtil(path);
			int row = util.getRowNum();
			int col = util.getColNum();
			for (int i = 0; i < col; i++) {
				JSONObject jsonobject = new JSONObject();
				JSONObject jsonobject1 = new JSONObject();
				String value = util.getCol(i);
				jsonobject.put(util.getString(0, i), util.removehead(value));
				jsonobject1.put(util.getString(0, i), util.isNum(value));
				array.add(jsonobject);
				array.add(jsonobject1);
				System.out.println(array);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return array;
	}

	public String isNum(String str) {
		String[] str_1 = str.split(",");
		String sb = new String();
		for (int i = 1; i < str_1.length; i++) {
			Pattern pattern = Pattern.compile("[0-9]*");
			Matcher isNum = pattern.matcher(str_1[i]);
			if (!isNum.matches()) {
				sb = "String";
				break;
			}
			sb = "num";
		}
		return sb;
	}

	public static void main(String[] args) {
		ShowCSVUtil util = new ShowCSVUtil();
		JSONArray array = util
				.readcsv("C:/Users/Administrator/Desktop/test.csv");
		System.out.println(array);
	}
}
