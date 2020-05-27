package com.gyh.part2.day38;

import java.util.HashMap;
import java.util.Map;

public class Solution3 {
    public static void main(String[] args) {
        String[] words = {"cat", "bt", "hat", "tree"};
        String chars = "atach";
        Map<Character, Integer> map = stringToMap(chars);
        int num = 0;

        for (String word : words) {
            boolean isGood = true;
            Map<Character, Integer> m = stringToMap(word);
            for (Character ch : m.keySet()) {
                if (!map.containsKey(ch) || (m.get(ch) > map.get(ch))) { // 不存在，或者字符数不够就不是好的
                    isGood = false;
                    break;
                }
            }
            if (isGood) num += word.length();
        }
        System.out.println(num);
    }

    public static Map<Character, Integer> stringToMap(String st) {
        Map<Character, Integer> map =  new HashMap<>();
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
