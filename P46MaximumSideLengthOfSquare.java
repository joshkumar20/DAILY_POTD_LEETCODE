// Given a m x n matrix mat and an integer threshold, return the maximum side-length of a square with a sum less than or equal to threshold or return 0 if there is no such square.

 

// Example 1:


// Input: mat = [[1,1,3,2,4,3,2],[1,1,3,2,4,3,2],[1,1,3,2,4,3,2]], threshold = 4
// Output: 2
// Explanation: The maximum side length of square with sum less than 4 is 2 as shown.
// Example 2:

// Input: mat = [[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2],[2,2,2,2,2]], threshold = 1
// Output: 0
 

// Constraints:

// m == mat.length
// n == mat[i].length
// 1 <= m, n <= 300
// 0 <= mat[i][j] <= 104
// 0 <= threshold <= 105

class P46MaximumSideLengthOfSquare{

    public int maxSideLength(int[][] mat, int threshold) {
        int m = mat.length;
        int n = mat[0].length;
        int[][] P = new int[m + 1][n + 1];
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                P[i][j] =
                    P[i - 1][j] +
                    P[i][j - 1] -
                    P[i - 1][j - 1] +
                    mat[i - 1][j - 1];
            }
        }

        int l = 1;
        int r = Math.min(m, n);
        int ans = 0;
        while (l <= r) {
            int mid = (l + r) / 2;
            boolean find = false;
            for (int i = 1; i <= m - mid + 1; i++) {
                for (int j = 1; j <= n - mid + 1; j++) {
                    int sum =
                        P[i + mid - 1][j + mid - 1] -
                        P[i - 1][j + mid - 1] -
                        P[i + mid - 1][j - 1] +
                        P[i - 1][j - 1];
                    if (sum <= threshold) {
                        find = true;
                        break;
                    }
                }
                if (find) break;
            }
            if (find) {
                ans = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return ans;
    }
}