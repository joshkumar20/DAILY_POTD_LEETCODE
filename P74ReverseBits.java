

// Reverse bits of a given 32 bits signed integer.

 

// Example 1:

// Input: n = 43261596

// Output: 964176192

// Explanation:

// Integer	Binary
// 43261596	00000010100101000001111010011100
// 964176192	00111001011110000010100101000000
// Example 2:

// Input: n = 2147483644

// Output: 1073741822

// Explanation:

// Integer	Binary
// 2147483644	01111111111111111111111111111100
// 1073741822	00111111111111111111111111111110
 

// Constraints:

// 0 <= n <= 231 - 2
// n is even.


class P74ReverseBits {
    public int reverseBits(int n) { return rev(n, 32); }

    private int rev(int v, int len) {
        if (len == 1) return v & 1;

        int half = len >> 1;
        int mask = (1 << half) - 1;
        int lo = v & mask;
        int hi = v >>> half;

        return (rev(lo, half) << half) | rev(hi, half);
    }
}
