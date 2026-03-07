
// You are given a binary string s. You are allowed to perform two types of operations on the string in any sequence:

// Type-1: Remove the character at the start of the string s and append it to the end of the string.
// Type-2: Pick any character in s and flip its value, i.e., if its value is '0' it becomes '1' and vice-versa.
// Return the minimum number of type-2 operations you need to perform such that s becomes alternating.

// The string is called alternating if no two adjacent characters are equal.

// For example, the strings "010" and "1010" are alternating, while the string "0100" is not.
 

// Example 1:

// Input: s = "111000"
// Output: 2
// Explanation: Use the first operation two times to make s = "100011".
// Then, use the second operation on the third and sixth elements to make s = "101010".
// Example 2:

// Input: s = "010"
// Output: 0
// Explanation: The string is already alternating.
// Example 3:

// Input: s = "1110"
// Output: 1
// Explanation: Use the second operation on the second element to make s = "1010".
 

// Constraints:

// 1 <= s.length <= 105
// s[i] is either '0' or '1'.

class P93MinimumNumberofFlipstoMaketheBinaryStringAlternating{

    public int minFlips(String s) {
        int n = s.length();
        int[][] pre = new int[n][2];

        for (int i = 0; i < n; i++) {
            char ch = s.charAt(i);
            // Compute the prefix array
            pre[i][0] = (i == 0 ? 0 : pre[i - 1][1]) + (ch == '1' ? 1 : 0);
            pre[i][1] = (i == 0 ? 0 : pre[i - 1][0]) + (ch == '0' ? 1 : 0);
        }

        int ans = Math.min(pre[n - 1][0], pre[n - 1][1]);
        // If the length is odd, consider the move operation
        if (n % 2 == 1) {
            int[][] suf = new int[n][2];
            for (int i = n - 1; i >= 0; i--) {
                char ch = s.charAt(i);
                // Suffix Array Construction
                suf[i][0] =
                    (i == n - 1 ? 0 : suf[i + 1][1]) + (ch == '1' ? 1 : 0);
                suf[i][1] =
                    (i == n - 1 ? 0 : suf[i + 1][0]) + (ch == '0' ? 1 : 0);
            }

            // Traverse all split points
            for (int i = 0; i < n - 1; i++) {
                ans = Math.min(ans, pre[i][0] + suf[i + 1][0]);
                ans = Math.min(ans, pre[i][1] + suf[i + 1][1]);
            }
        }

        return ans;
    }
}