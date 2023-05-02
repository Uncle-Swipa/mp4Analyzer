package com.example.mp4Analyzer.util;

import java.util.Arrays;

 public class Mp4AnalyserUtil {
    // This method converts the first four bytes in the array to long using bitwise operation
    // The long that is returned is the size of the box
    public static long readBoxSize(byte[] bytes) {
        long value = 0;
        for (int i = 0; i < 4; i++) {
            value <<= 8;
            value |= bytes[i] & 0xff;
        }
        return value;
    }

    //The method works by first copying the bytes from the fourth byte to the seventh byte of the byte array.
    //The bytes that are copied are the type of the box.
    //The method then converts the copied bytes to a string.
    // The string that is returned by the method is the type of the box.
     public static String readBoxType(byte[] boxHeader) {
        return new String(Arrays.copyOfRange(boxHeader, 4, 8));
    }
}
