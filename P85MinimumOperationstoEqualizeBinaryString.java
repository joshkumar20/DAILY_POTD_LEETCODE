
// You are given a binary string s, and an integer k.

// In one operation, you must choose exactly k different indices and flip each '0' to '1' and each '1' to '0'.

// Return the minimum number of operations required to make all characters in the string equal to '1'. If it is not possible, return -1.

 

// Example 1:

// Input: s = "110", k = 1

// Output: 1

// Explanation:

// There is one '0' in s.
// Since k = 1, we can flip it directly in one operation.
// Example 2:

// Input: s = "0101", k = 3

// Output: 2

// Explanation:

// One optimal set of operations choosing k = 3 indices in each operation is:

// Operation 1: Flip indices [0, 1, 3]. s changes from "0101" to "1000".
// Operation 2: Flip indices [1, 2, 3]. s changes from "1000" to "1111".
// Thus, the minimum number of operations is 2.

// Example 3:

// Input: s = "101", k = 2

// Output: -1

// Explanation:

// Since k = 2 and s has only one '0', it is impossible to flip exactly k indices to make all '1'. Hence, the answer is -1.

 

// Constraints:

// 1 <= s.length <= 10​​​​​​​5
// s[i] is either '0' or '1'.
// 1 <= k <= s.length
import java.util.*;

class P85MinimumOperationstoEqualizeBinaryString{

    public int minOperations(String s, int k) {
        int n = s.length();
        int m = 0;
        int[] dist = new int[n + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        List<TreeSet<Integer>> nodeSets = new ArrayList<>();
        nodeSets.add(new TreeSet<>());
        nodeSets.add(new TreeSet<>());
        for (int i = 0; i <= n; i++) {
            nodeSets.get(i % 2).add(i);
            if (i < n && s.charAt(i) == '0') {
                m++;
            }
        }
        Queue<Integer> q = new ArrayDeque<>();
        q.offer(m);
        dist[m] = 0;
        nodeSets.get(m % 2).remove(m);
        while (!q.isEmpty()) {
            m = q.poll();
            int c1 = Math.max(k - n + m, 0);
            int c2 = Math.min(m, k);
            int lnode = m + k - 2 * c2;
            int rnode = m + k - 2 * c1;
            TreeSet<Integer> nodeSet = nodeSets.get(lnode % 2);
            for (
                Integer m2 = nodeSet.ceiling(lnode);
                m2 != null && m2 <= rnode;
                m2 = nodeSet.ceiling(lnode)
            ) {
                dist[m2] = dist[m] + 1;
                q.offer(m2);
                nodeSet.remove(m2);
            }
        }
        return dist[0] == Integer.MAX_VALUE ? -1 : dist[0];
    }
}