import java.util.*;

public class Exercise1 {

    public static int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        int[] lastCoinUsed = new int[amount + 1]; // כדי לשחזר את המטבעות
        Arrays.fill(dp, amount + 1);
        dp[0] = 0;

        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) {
                if (dp[i - coin] + 1 < dp[i]) {
                    dp[i] = dp[i - coin] + 1;
                    lastCoinUsed[i] = coin;
                }
            }
        }

        // הדפסה של המטבעות שנבחרו בפועל
        if (dp[amount] <= amount) {
            System.out.print("🔹 Coins used: ");
            int curr = amount;
            while (curr > 0) {
                int coin = lastCoinUsed[curr];
                System.out.print(coin + " ");
                curr -= coin;
            }
            System.out.println();
        }

        return (dp[amount] > amount) ? -1 : dp[amount];
    }
}
