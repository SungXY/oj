/*
 * 类名：		GenerateAndTestHash
 * 创建日期：	2010-11-13
 * 最近修改：	2013-04-18
 * 作者：		徐犇
 */

package com.bupt.service.search.examiner.common;

import java.util.*;

public final class GenerateAndTestHash {
	
	/**
	 * 比对算法中所用到的hash值数组，在本程序的一次运行过程中，这个数组不会变化
	 */
	private static long[] hashArrays = null;

	// public static void main(String[] args) {
	// test();
	// }
	/**
	 * 测试本hash值生成算法的失败率
	 */
	public static void test() {
		long testTimes = 20000000000L; // 测试总次数
		int failTimes = 0; // 判断错误次数, 即两子树hash值相等，结点个数也相同，但结构不同的次数
		int sameNode = 0; // 子树的hash值相等且结点个数相同的次数
		int equalTimes = 0; // 测试过程中两子树hash值相等次数
		long fTreeValue; // 第一棵子树树根的hash值
		long sTreeValue; // 第二棵子树树根的hash值
		int nodeNum = 10; // 测试子树的最大结点数
		int hashnum = 68; // hash值个数，也即结点种类数, 在本程序中，C语言语法树共有68种结点
		int[] hashid1 = new int[nodeNum], hashid2 = new int[nodeNum]; // 结点的hash值编号(也即结点类型)，测试过程中随机生成
		long[] hashArrays = getHashArrays(hashnum);

		System.out.println("开始测试，现在时间是:"
				+ Calendar.getInstance().getTime().toString());

		int j1, j2; // 两棵树的结点个数(随机生成,
		for (long i = 0; i < testTimes; i++) {
			fTreeValue = 0;
			sTreeValue = 0;
			j1 = (int) (Math.random() * nodeNum) + 1;
			j2 = (int) (Math.random() * nodeNum) + 1;
			for (int j = 0; j < j1; j++) {
				hashid1[j] = (int) (Math.random() * hashnum);
				fTreeValue += hashArrays[hashid1[j]];
			}
			for (int j = 0; j < j2; j++) {
				hashid2[j] = (int) (Math.random() * hashnum);
				sTreeValue += hashArrays[hashid2[j]];
			}

			if (fTreeValue == sTreeValue) {
				equalTimes++;
				if (j1 == j2) {
					sameNode++;
					if(!isTheSame(hashid1, hashid2, j1)) {
						failTimes++;
					}
				}
			}
		}

		System.out.println("测试结束，现在时间是:"
				+ Calendar.getInstance().getTime().toString());
		System.out.println("本次测试共进行了" + testTimes + "次\n允许的结点最大个数为" + nodeNum);
		System.out.println("两子树hash值相等" + equalTimes + "次");
		System.out.println("两子树hash值相等且结点个数相同" + sameNode + "次");
		System.out.println("判断错误" + failTimes + "次");
		double rate = failTimes * 1.0 / equalTimes * 100;
		System.out.println("误判率:\t" + rate + "%");
	}

	/**
	 * 
	 * @param hashid1
	 * @param hashid2
	 * @param j1
	 * @return 两个hash数组是否完全一样
	 */
	private static boolean isTheSame(int[] hashid1, int[] hashid2, int j1) {
		Arrays.sort(hashid1, 0, j1);
		Arrays.sort(hashid2, 0, j1);
		for(int i  = 0; i < j1; i++){
			if(hashid1[i] != hashid2[i]) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 生成一个指定长度的hash值数组
	 *对方法加锁，进行多线程访问控制
	 * @param hashnum
	 * @return long型数组
	 */
	public static synchronized long[] getHashArrays(int hashnum) {
		if(hashArrays != null && hashArrays.length == hashnum) {
			return hashArrays;
		}
		hashArrays = new long[hashnum];
		long lowpart, highpart;
		for (int i = 0; i < hashnum; i++) {
			lowpart = (long) (Math.random() * 100000000) + 1;
			highpart = (long) (Math.random() * 5000 + 1000) + 1;
			hashArrays[i] = lowpart + highpart;
		}
		return hashArrays;
	}
}
