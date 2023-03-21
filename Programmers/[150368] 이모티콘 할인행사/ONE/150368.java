import java.util.*;

class Solution {
    static class User {
        private final int purchaseRate;
        private final int purchasePrice;

        public User(int purchaseRate, int purchasePrice) {
            this.purchaseRate = purchaseRate;
            this.purchasePrice = purchasePrice;
        }

        public int purchasePrice() {
            return purchasePrice;
        }

        public int getPurchasePriceOfEmoticons(List<Emoticon> emoticons) {
            return emoticons.stream()
                    .filter(emoticon -> emoticon.discountRate() >= this.purchaseRate)
                    .mapToInt(Emoticon::getDiscountedPrice)
                    .sum();
        }
    }

    static class Emoticon {
        private final int price;
        private int discountRate;

        public Emoticon(int price) {
            this.price = price;
        }

        public int discountRate() {
            return discountRate;
        }

        public void changeRate(int discountRate) {
            this.discountRate = discountRate;
        }

        public int getDiscountedPrice() {
            return this.price * (100 - this.discountRate) / 100;
        }
    }

    static class Result {
        private final int subscribers;
        private final int sales;

        public Result(int subscribers, int sales) {
            this.subscribers = subscribers;
            this.sales = sales;
        }

        public int subscribers() {
            return subscribers;
        }

        public int sales() {
            return sales;
        }
    }

    private final List<User> users = new ArrayList<>();
    private final List<Emoticon> emoticons = new ArrayList<>();
    private final Queue<Result> results = new PriorityQueue<>(
            Comparator.comparing(Result::subscribers, Comparator.reverseOrder())
                    .thenComparing(Result::sales, Comparator.reverseOrder()));
    private final List<Integer> rates = List.of(10, 20, 30, 40);

    public int[] solution(int[][] users, int[] emoticons) {
        for (int[] user : users) {
            this.users.add(new User(user[0], user[1]));
        }
        for (int price : emoticons) {
            this.emoticons.add(new Emoticon(price));
        }

        simulate(0, emoticons.length);

        assert results.peek() != null;
        return new int[]{results.peek().subscribers(), results.peek().sales()};
    }

    private void simulate(int depth, int n) {
        if (depth == n) {
            results.add(getResult());
            return;
        }
        for (int rate : rates) {
            emoticons.get(depth).changeRate(rate);
            simulate(depth + 1, n);
        }
    }

    private Result getResult() {
        int subscreibers = 0;
        int sales = 0;
        for (User user : users) {
            int purchasePriceOfEmoticons = user.getPurchasePriceOfEmoticons(emoticons);
            if (user.purchasePrice() <= purchasePriceOfEmoticons) {
                subscreibers++;
            } else {
                sales += purchasePriceOfEmoticons;
            }
        }
        return new Result(subscreibers, sales);
    }
}