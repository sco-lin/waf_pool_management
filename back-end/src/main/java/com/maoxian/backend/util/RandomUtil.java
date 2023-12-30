package com.maoxian.backend.util;

import java.util.Random;

/**
 * Random工具类
 *
 * @author Lin
 * @date 2023/10/25 23:27
 */
public class RandomUtil {

    private static final Random RANDOM = new Random();

    /**
     * 生成指定位数的随机数字字符串
     *
     * @param count 位数
     * @return 数字字符串
     */
    public static String randomNumbers(int count) {
        StringBuilder randomNumbers = new StringBuilder();
        for (int i = 0; i < count; i++) {
            randomNumbers.append(RANDOM.nextInt(10));
        }
        return randomNumbers.toString();
    }
}
