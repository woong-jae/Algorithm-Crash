import java.util.ArrayList;
import java.util.List;

class Solution {
    public int solution(String str1, String str2) {
        List<String> A = makeSet(str1.toLowerCase());
        List<String> B = makeSet(str2.toLowerCase());

        if(A.size() == 0 && B.size() == 0)
            return 65536;

        List<String> intersection;
        List<String> union = new ArrayList<>(A);

        if(A.size() >= B.size())
            intersection = makeIntersection(A, B);
        else
            intersection = makeIntersection(B, A);

        union.addAll(B);
        for(String i : intersection)
            union.remove(i);

        return (int) ((double) intersection.size() / union.size() * 65536);
    }

    private List<String> makeSet(String str) {
        List<String> set = new ArrayList<>();
        int len = str.length();
        for (int i = 0; i < len - 1; i++){
            String sub = str.substring(i, i + 2);
            if (sub.matches("^[a-zA-Z]*$"))
                set.add(sub);
        }
        return set;
    }

    private List<String> makeIntersection(List<String> A, List<String> B) {
        List<String> intersection = new ArrayList<>();
        List<String> copiedA = new ArrayList<>(A);

        for (String b : B)
            if (copiedA.contains(b)) {
                intersection.add(b);
                copiedA.remove(b);
            }

        return intersection;
    }
}