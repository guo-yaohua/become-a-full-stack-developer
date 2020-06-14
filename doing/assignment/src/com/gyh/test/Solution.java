package com.gyh.test;

import java.util.*;

public class Solution {
   public static String logAnonymize(String[]keys, String log) {
        // TOOO 在此补充你的代码
        // log 转 map
        LinkedHashMap<String, String> map = new LinkedHashMap<>();
        for (int i = 0; i < log.length(); i++) {
            int left;
            int right;
            while (log.charAt(i) != '[') i++;
            left = i;
            while (log.charAt(i) != ':') i++;
            right = i;
            String key = log.substring(left + 1, right);
            while (log.charAt(i) != ']') i++;
            String value = log.substring(right + 1, i);
            map.put(key, value);
        }

        // 操作 map
        for (String s : map.keySet()) {
            // 规则1
            if ("password".equals(s) || "pwd".equals(s)) {
                map.put(s, "******");
                continue;
            }

            boolean isExist = false;
            for (String key : keys) {
                if (s.equals(key)) {
                    isExist = true;
                    break;
                }
            }
            if (!isExist) continue; // 键不在 keys 中

            String val = map.get(s);

            // 规则2
            if (s.toLowerCase().endsWith("ip")) {
                int start = 0;
                int end = 0;
                int times = 0;
                for (int i = 0; i < val.length(); i++) {
                    if (val.charAt(i) == '.') {
                        times++;
                        if (times == 1) start = i;
                        if (times == 3) {
                            end = i;
                            break;
                        }

                    }
                }
                String newVal = val.substring(0, start + 1) + "***.***" + val.substring(end);
                map.put(s, newVal);
                continue;
            }

            // 规则3
            for (int i = val.length() - 1; i >=0;) {
                int end = i;
                int start = i;
                while (val.charAt(i) >= '0' && val.charAt(i) <='9') {
                    if (i > 0) {
                        i--;
                        start--;
                    } else break;
                }
                int L = end - start + 1;

                if (L >= 4) {
                    String cry = "";
                    end = end - L/4;
                    start = start + L/2 - 1;
                    for (int k = 0; k < end - start + 1; k++) cry = cry + "*";
                    String newVal = val.substring(0, start) + cry + val.substring(end + 1);
                    map.put(s, newVal);
                    break;
                }
                i = start - 1;
            }
        }

        // map 转字符串
        StringBuffer newLog = new StringBuffer();
        for (String s : map.keySet()) {
            newLog.append("[" + s + ":" + map.get(s) + "],");
        }
        newLog.deleteCharAt(newLog.length() - 1);
        return newLog.toString();
    }

   public static int unlock(String initState, String dstState) {
       Queue<Integer> queue = new LinkedList<>();
    
       queue.add(Integer.parseInt(initState));

       int[] visited = new int[10000];
       visited[Integer.parseInt(initState)] = 1;

       int count = 0;
       int dstValue = Integer.parseInt(dstState);

       while (!queue.isEmpty()) {
           List<Integer> nums = new ArrayList<>();
           
           while (!queue.isEmpty()) {
               nums.add(queue.remove());
           }

           for (Integer num : nums) {
               if (num == dstValue) return count;

               for (int i = 0; i < 4; i++) {
                   int[] numArr = intToArr(num);
                   for (int j = 0; j < 10; j++) {
                       numArr[i] = j;
                       int tmp = arrToInt(numArr);
                       if (visited[tmp] == 1) continue;

                       if (tmp == dstValue) return count + 1;

                       if (isPrime(tmp)) queue.add(tmp);

                       visited[tmp] = 1;
                   }
               }
           }

           if (!queue.isEmpty()) count++;
       }

       return -1;
    }

    private static int arrToInt(int[] arr) {
       return arr[0] * 1000 + arr[1] * 100 + arr[2] * 10 + arr[3];
    }

    private static int[] intToArr(int num) {
       int[] arr = new int[4];
       for (int i = 0; i < 4; i++) {
           arr[3 - i] = num % 10;
           num /= 10;
       }
       return arr;
    }

    private static boolean isPrime(int num) {
        if (num < 2) return false;
        int r = (int) Math.sqrt(num);
        for (int i = 2; i <= r; ++i){
            if (num % i == 0) return false;
        }
        return true;
    }
}

