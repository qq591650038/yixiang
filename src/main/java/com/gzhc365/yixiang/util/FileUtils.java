package com.gzhc365.yixiang.util;

import cn.hutool.core.util.StrUtil;

import java.io.BufferedWriter;
import java.util.List;
import java.util.Map;

public class FileUtils {

	public static void writeFile(List<Map<String, Object>> list, BufferedWriter writer, boolean isFirst) throws Exception {
		if (list.size() == 0) {
			return;
		}
		StringBuilder title = new StringBuilder();
		boolean headIsPrint = false;
		for (Map<String, Object> map : list) {
			if (isFirst && !headIsPrint) {
				for (Map.Entry<String, Object> entry : map.entrySet()) {
					title.append(entry.getKey() + ",");
				}
				title.delete(title.length() - 1, title.length());
				title.append(System.lineSeparator());
				headIsPrint = true;
			}
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				if(null == entry.getValue()) {
					title.append("\\N" + ",");
				}else{
					title.append(StrUtil.isBlank(String.valueOf(entry.getValue())) ? "\\N,":entry.getValue() + ",");	
				}
			}
			title.delete(title.length() - 1, title.length());
			title.append(System.lineSeparator());
		}
		writer.write(title.toString());
	}

	public static void main(String[] args) {
		StringBuffer title = new StringBuffer();
		title.append("33,11,11,");
		title.delete(title.length() - 1, title.length());
		System.out.println(title);
	}

}