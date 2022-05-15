package com.potato.study.leetcode.util;import java.util.Arrays;

/**
 * 数组工具类
 * @author Administrator
 *
 */
public class ArrayUtil {
	
	private ArrayUtil() {
		
	}
	
	public static boolean isBlank(int[] arr) {
		if(null == arr || arr.length == 0) {
			return true;
		}
		return false;
	}
	
	public static void printMatrix(int[][] matrix) {
		if(null == matrix || matrix.length == 0) {
			return ;
		}
		System.out.println("[");
		for (int[] is : matrix) {
			System.out.println(Arrays.toString(is));
		}
		System.out.println("]");
	}
	
	public static void printMatrix(char[][] matrix) {
		if(null == matrix || matrix.length == 0) {
			return ;
		}
		System.out.println("[");
		for (char[] is : matrix) {
			System.out.println(Arrays.toString(is));
		}
		System.out.println("]");
	}



	/**
	 * [3,2,0,-4] 类似字符串 转换成数组
	 * []  -> 空数组
	 * null -> null
	 * 不校验入参合法性
	 * @param arrayStr
	 * @return
	 */
	public static int[] string2ArrayiInt(String arrayStr) {
		if (null == arrayStr || arrayStr.length() < 2) {
			return null;
		}
		if (arrayStr.length() == 2) {
			return new int[0];
		}
		String[] numParts = arrayStr.substring(1, arrayStr.length() - 1).split(",");
		int[] numArray = new int[numParts.length];
		for (int i = 0; i < numParts.length; i++) {
			numArray[i] = Integer.parseInt(numParts[i].trim());
		}
		return numArray;
	}

	public static void main(String[] args) {
		String arrayStr = "[3,2,0,-4]";
		int[] arr = string2ArrayiInt(arrayStr);
		System.out.println(Arrays.toString(arr));
	}


	/**
	 * [3,2,0,-4] 类似字符串 转换成数组
	 * []  -> 空数组
	 * null -> null
	 * 不校验入参合法性
	 * @param arrayStr
	 * @return
	 */
	public static boolean[] string2ArrayBoolean(String arrayStr) {
		if (null == arrayStr || arrayStr.length() < 2) {
			return null;
		}
		if (arrayStr.length() == 2) {
			return new boolean[0];
		}
		String[] booleanParts = arrayStr.substring(1, arrayStr.length() - 1).split(",");
		boolean[] boolArray = new boolean[booleanParts.length];
		for (int i = 0; i < booleanParts.length; i++) {
			boolArray[i] = Boolean.parseBoolean(booleanParts[i].trim());
		}
		return boolArray;
	}
	
}
