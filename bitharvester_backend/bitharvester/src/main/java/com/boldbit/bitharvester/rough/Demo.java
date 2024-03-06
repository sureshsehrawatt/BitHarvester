package com.boldbit.bitharvester.rough;

public final class Demo {
    public static int minSteps(String s, String t) {
            int[] charCount = new int[26]; // Assuming only lowercase letters
    
            for (char c : t.toCharArray()) {
                charCount[c - 'a']++;
            }
    
            for (char c : s.toCharArray()) {
                if (charCount[c - 'a'] > 0) {
                    charCount[c - 'a']--;
                }
            }
    
            int result = 0;
            for (int count : charCount) {
                result += count;
            }
    
            return result;
    }
    public static void main(String[] args) {
        String s = "bab", t = "aba";
        Demo.minSteps(s, t);
    }
}
