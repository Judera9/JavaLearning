package LAB6;

public class LongestSubString {
    public static void main(String[] args) {

    }

    public int lengthOfLongestSubstring(String s) {
        int[][] recordMap = new int[s.length()][];
//        char[] charList = s.toCharArray();

        for (int i = 0; i < 5; i++) {
            String[] curSubstr = getSubstrWithLen(s, i + 1);
            for (int j = 0; j < s.length() - i; j++) {
                if ((i != 0) && (recordMap[i - 1][j] == 0)) {
                    recordMap[i][j] = 0;
                }
                if ((i != 0) && (i != 1) && (recordMap[i - 2][j + 1] == 0)) {
                    recordMap[i][j] = 0;
                }
                if ((i != 0) && (recordMap[i - 1][j + 1] == 0)) {
                    recordMap[i][j] = 0;
                }

                recordMap[i][j] = isDuplicatedSubstr(curSubstr[j]);
            }
        }

        return 0;
    }

    // find that if a substring contains duplicated chars
    public int isDuplicatedSubstr(String s) {
        char[] chars = s.toCharArray();
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j < s.length(); j++) {
                if (chars[i] == chars[j]) {
                    return 0;
                }
            }
        }
        return 1;
    }

    public String[] getSubstrWithLen(String s, int length) {
        int subStrLength = s.length() - length + 1;

        String[] subStr = new String[subStrLength];
        for (int i = 0; i < subStrLength; i++) {
            subStr[i] = s.substring(i, i + length);
        }
        return subStr;
    }
}
