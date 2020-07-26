package org.challenge.general;

import java.io.File;
import java.util.Random;

public class Tools {

    static public void createFolder (String path) {
        File file1 = new File(path);
        file1.mkdirs();
    }

    static public String getAbsolutePath(String path) {
        File f = new File(path);
        return f.getAbsolutePath();
    }

    static public int getRandomNumber(int from, int to) {
        Random rand = new Random();
        int size = to - from;
        int n = rand.nextInt(to);
        n += from;
        return n;
    }

    static public int getIntFromString(String number) {
        int intNumber;
        try {
            intNumber = Integer.parseInt(number);
        } catch (NumberFormatException e) {
            intNumber = 0;
        }
        return intNumber;
    }

    public static boolean containsIgnoreCase(String str, String searchStr)     {
        if(str == null || searchStr == null) return false;

        final int length = searchStr.length();
        if (length == 0)
            return true;

        for (int i = str.length() - length; i >= 0; i--) {
            if (str.regionMatches(true, i, searchStr, 0, length))
                return true;
        }
        return false;
    }
}