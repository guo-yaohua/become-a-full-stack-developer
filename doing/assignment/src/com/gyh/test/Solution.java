package com.gyh.test;

class Solution {
    public boolean isValid(String s) {
        if (s.length() == 0) return true;

        int num1 = 0;
        int num2 = 0;
        int num3 = 0;
        for (int i = 0; i < s.length(); i++) {
            switch (s.charAt(i)) {
                case '(':
                    num1++;
                    break;
                case ')':
                    num1--;
                    if (num1 < 0) return false;
                    break;
                case '[':
                    num2++;
                    break;
                case ']':
                    num2--;
                    if (num2 < 0) return false;
                    break;
                case '{':
                    num3++;
                    break;
                case '}':
                    num3--;
                    if (num3 < 0) return false;
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + s.charAt(i));
            }
        }
        if (num1 == 0 && num1 == 0 && num3 == 0) return true;
        return false;
    }
}
