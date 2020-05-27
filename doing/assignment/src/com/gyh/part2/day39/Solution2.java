package com.gyh.part2.day39;

import java.util.TreeMap;

public class Solution2 {
    public static void main(String[] args) {
        String s = "aababcabcdabcde";
        TreeMap<Character, Integer> map = stringToMap(s);
        System.out.println(map.firstKey() + ":" + map.get(map.firstKey()));
    }

    public static TreeMap<Character, Integer> stringToMap(String st) {
        TreeMap<Character, Integer> map = new TreeMap<>();
        for (int i = 0; i < st.length(); i++) {
            Character ch = st.charAt(i);
            if (map.containsKey(ch)) {
                int num = map.get(ch);
                num++;
                map.put(ch, num);
            } else {
                map.put(ch, 1);
            }
        }
        return map;
    }
}
