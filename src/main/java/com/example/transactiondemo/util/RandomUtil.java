package com.example.transactiondemo.util;

import java.util.Random;

public class RandomUtil {

    public static int generateRandom() {
        Random random = new Random();
        int randomInt = random.nextInt(Integer.MAX_VALUE);
        return randomInt;
    }
}
