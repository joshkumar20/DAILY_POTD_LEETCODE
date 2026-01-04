// You have a grid of size n x 3 and you want to paint each cell of the grid with exactly one of the three colors: Red, Yellow, or Green while making sure that no two adjacent cells have the same color (i.e., no two cells that share vertical or horizontal sides have the same color).

// Given n the number of rows of the grid, return the number of ways you can paint this grid. As the answer may grow large, the answer must be computed modulo 109 + 7.

 

// Example 1:


// Input: n = 1
// Output: 12
// Explanation: There are 12 possible way to paint the grid as shown.
// Example 2:

// Input: n = 5000
// Output: 30228214
 

// Constraints:

// n == grid.length
// 1 <= n <= 5000

class P30NumberOfWaysToPainNX3Grid{
    private static final int MOD = 1_000_000_007;

    public int numOfWays(int n) {
        long dpA = 6; // all 3 colors different
        long dpB = 6; // two colors

        for (int i = 2; i <= n; i++) {
            long newA = (2 * dpA + 2 * dpB) % MOD;
            long newB = (2 * dpA + 3 * dpB) % MOD;

            dpA = newA;
            dpB = newB;
        }

        return (int)((dpA + dpB) % MOD);
    }
}
