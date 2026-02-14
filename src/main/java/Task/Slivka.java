package Task;

import java.util.Arrays;
import java.util.Collections;

/**
 *  2 строки анаграммой ?
 *
 *
 */
public class Slivka {

    static boolean checkAnagram(String  str1, String str2) {
        if (str1.trim().isBlank() || str2.trim().isEmpty()) {
            throw new IllegalArgumentException("пустые строки");
        }
        char[] charStr1 = str1.toLowerCase().trim().toCharArray();
        char[] charStr2 = str2.toLowerCase().trim().toCharArray();

        Arrays.sort(charStr1);
        Arrays.sort(charStr2);
        return Arrays.equals(charStr1,charStr2);


    }

    public static void main(String[] args) {
        System.out.println(checkAnagram("kot", "tok"));
        System.out.println(checkAnagram("kof", "tok"));
        System.out.println(checkAnagram("кот", "tok"));
        System.out.println(checkAnagram("   ", "tok"));
    }



}
