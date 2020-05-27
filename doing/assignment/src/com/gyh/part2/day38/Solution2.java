package com.gyh.part2.day38;

import java.util.HashSet;

public class Solution2 {
    public static void main(String[] args) {
        String s = "abcd";
        String t = "baedc";
        HashSet<Character> set1 = stringToSet(s);
        HashSet<Character> set2 = stringToSet(t);
        for (Character ch : set2) {
            if (!set1.contains(ch)) {
                System.out.println(ch);
                break;
            }
        }

    }

    public static HashSet<Character> stringToSet(String st) {
        HashSet<Character> set = new HashSet<>();
        for (int i = 0; i < st.length(); i++) {
            set.add(st.charAt(i));
        }
        return set;
    }
}
