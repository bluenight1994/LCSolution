class Solution {
    public String minimizeError(String[] prices, int target) {
        double[][] m = new double[prices.length][2];
        
        List<Double> cans = new ArrayList<>();
        int k = 0;
        for (int i = 0; i < prices.length; i++) {
            double p = Double.valueOf(prices[i]);
            double f_p = Math.floor(p); double c_p = Math.ceil(p);
            m[i][0] = p - f_p; m[i][1] = c_p - p;
            target -= f_p;
            if (f_p == c_p) k++;
        }
        
        double[][] mm = new double[prices.length - k][2];
        k = 0;
        for (int i = 0; i < prices.length; i++) {
            if (m[i][0] != 0) {
                mm[k][0] = m[i][0];
                mm[k][1] = m[i][1];
                k++;
            }
        }
        
        if (target < 0) return "-1";
        
        if (target == 0) {
            double res = 0;
            for (int i = 0; i < mm.length; i++) {
                res += mm[i][0];
            }
            return String.format("%.3f", res);
        }
        
        // target > 0
        if (mm.length == 0) return "-1";
        
        double[][] dp = new double[mm.length][target+1];
        for (double[] kk : dp) {
            Arrays.fill(kk, Double.MAX_VALUE);
        }
        dp[0][0] = mm[0][0];
        dp[0][1] = mm[0][1];
        for (int i = 0; i <= target; i++) {
            for (int j = 1; j < mm.length; j++) {
                if (i >= 1 && dp[j-1][i-1] != Double.MAX_VALUE)
                    dp[j][i] = Math.min(dp[j][i], dp[j-1][i-1] + mm[j][1]);
                                        
                if (dp[j-1][i] != Double.MAX_VALUE)
                    dp[j][i] = Math.min(dp[j][i], dp[j-1][i] + mm[j][0]);
            }
        }
                                        
        if (dp[mm.length - 1][target] != Double.MAX_VALUE) {
            return String.format("%.3f", dp[mm.length-1][target]);
        } else {
            return "-1";
        }
    }
}
