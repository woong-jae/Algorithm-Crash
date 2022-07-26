
class StoneBridge {
    public StoneBridge() {
    }

    public int crossBridge(int[] stones, int k) {
        int friends = 0;
        int min = Integer.MAX_VALUE;
        int max = 0;

        // min과 max 사이에 건널 수 있는 최대 인원 수가 있음
        for (int stone : stones) {
            if (stone < min) min = stone;
            if (stone > max) max = stone;
        }

        while (min <= max) {
            int mid = (min + max)/2;

            // 건널 수 있다면, min을 1 증가시키고 반복
            if (canCrossOver(stones, k, mid)) {
                min = mid + 1;
                friends = mid;
            } else {
                max = mid - 1;
            }
        }

        return friends;
    }

    private boolean canCrossOver(int[] stones, int k, int n) {
        int cantOver = 0;

        for (int stone : stones) {
            // 건널 수 없는 돌
            if (stone < n) {
                cantOver++;
            } else cantOver = 0;

            // 건널 수 없는 돌다리가 k개 연속이라면 건널 수 없음!
            if (cantOver == k) return false;
        }

        return true;
    }
}

class Solution {
    public int solution(int[] stones, int k) {
        StoneBridge sb = new StoneBridge();
        
        return sb.crossBridge(stones, k);
    }
}