/**
 * https://leetcode.com/problems/number-of-equivalent-domino-pairs/
 */

class Solution {
    public int numEquivDominoPairs(int[][] dominoes) {
        if (dominoes.length <= 1) return 0;
        int sum = 0;
        int[] dp = new int[dominoes.length];
        for (int i = 1; i < dominoes.length; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (help(dominoes[i], dominoes[j])) {
                    dp[i] = dp[j] + 1;
                    sum += dp[i];
                    break;
                }
            }
        }
        return sum;
    }

    private boolean help(int[] a, int[] b) {
        return (a[0] == b[0] && a[1] == b[1]) || (a[0] == b[1] && a[1] == b[0]);
    }
}
