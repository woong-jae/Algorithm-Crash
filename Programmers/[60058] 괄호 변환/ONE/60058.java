import java.util.*;

class Solution {
    public String solution(String p) {
        int len = p.length();

        if(checkCorrect(p))
            return p;

        return recursion(p);
    }

    private String recursion(String p) {
        if (p.equals(""))
            return p;

        String u = "";
        String v = "";

        int countL = 0;
        int countR = 0;

        for (int i = 0; i < p.length(); i++) {
            char c = p.charAt(i);

            if(c == '(')
                countL++;
            else
                countR++;

            if (countL > 0 && countL == countR) {
                u = p.substring(0, i + 1);
                if (i != p.length() - 1)
                    v = p.substring(i + 1);
                break;
            }
        }

        if (!checkCorrect(u)) {
            String tmp = "(" + recursion(v);

            tmp += ")";

            u = u.substring(1, u.length() - 1);
            u = u.replace("(", "-");
            u = u.replace(")", "(");
            u = u.replace("-", ")");

            tmp += u;

            return tmp;
        }
        else
            return u + recursion(v);
    }

    private boolean checkCorrect(String s) {
        int len = s.length();
        int count = 0;

        for (int i = 0; i < len; i++) {
            if(s.charAt(i) == '(')
                count++;
            else
                count--;

            if (count < 0)
                return false;
        }
        return true;
    }
}