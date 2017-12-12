package cn.epi.common.utils.ds;

import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class ApiKey {
	/**
	 * * 获得随机字符串！ * @param passLength 字符串大小 * @param type * @category type 1 数字
	 * * @category type 2 大写 * @category type 3 小写 * @category type 4 大小写复制
	 * * @category type 5 默认函数 * @return
	 */
	public static String getCode(int passLength, int type) {
		StringBuffer buffer = null;
		StringBuffer sb = new StringBuffer();
		Random r = new Random();
		r.setSeed(new Date().getTime());
		switch (type) {
		case 0:
			buffer = new StringBuffer("0123456789");
			break;
		case 1:
			buffer = new StringBuffer("abcdefghijklmnopqrstuvwxyz");
			break;
		case 2:
			buffer = new StringBuffer("ABCDEFGHIJKLMNOPQRSTUVWXYZ");
			break;
		case 3:
			buffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
			break;
		case 4:
			buffer = new StringBuffer("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789-!,.?*&^$#");
			sb.append(buffer.charAt(r.nextInt(buffer.length() - 10)));
			passLength -= 1;
			break;
		case 5:
			String s = UUID.randomUUID().toString();
			sb.append(s.substring(0, 8) + s.substring(9, 13) + s.substring(14, 18) + s.substring(19, 23)
					+ s.substring(24));
		}

		if (type != 5) {
			int range = buffer.length();
			for (int i = 0; i < passLength; ++i) {
				sb.append(buffer.charAt(r.nextInt(range)));
			}
		}
		return sb.toString();
	}
}
