
import java.util.*;

class Solution {
    private static final int MOD = 1_000_000_007;

    public int countPartitions(int[] nums, int k) {
        int n = nums.length;

        long[] dp = new long[n + 1];
        long[] pref = new long[n + 1];

        dp[0] = 1;
        pref[0] = 1;

        Deque<Integer> maxDeque = new ArrayDeque<>();
        Deque<Integer> minDeque = new ArrayDeque<>();

        int left = 0;

        for (int right = 0; right < n; right++) {
            while (!maxDeque.isEmpty() && nums[maxDeque.peekLast()] <= nums[right]) {
                maxDeque.pollLast();
            }
            maxDeque.addLast(right);
            while (!minDeque.isEmpty() && nums[minDeque.peekLast()] >= nums[right]) {
                minDeque.pollLast();
            }
            minDeque.addLast(right);
            while (!maxDeque.isEmpty() && !minDeque.isEmpty()
                    && nums[maxDeque.peekFirst()] - nums[minDeque.peekFirst()] > k) {

                if (maxDeque.peekFirst() == left) {
                    maxDeque.pollFirst();
                }
                if (minDeque.peekFirst() == left) {
                    minDeque.pollFirst();
                }
                left++;
            }
            long total = pref[right] - (left == 0 ? 0 : pref[left - 1]);
            total %= MOD;
            if (total < 0) total += MOD;

            dp[right + 1] = total;
            pref[right + 1] = (pref[right] + dp[right + 1]) % MOD;
        }

        return (int) (dp[n] % MOD);
    }
}
