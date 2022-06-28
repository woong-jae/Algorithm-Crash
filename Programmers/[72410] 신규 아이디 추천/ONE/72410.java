import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Solution {
    public String solution(String new_id) {

        new_id = step1(new_id);
        new_id = step2(new_id);
        new_id = step3(new_id);
        new_id = step4(new_id);
        new_id = step5(new_id);
        new_id = step6(new_id);
        new_id = step7(new_id);

        return new_id;
    }

    private String step1(String new_id) {
        return new_id.toLowerCase();
    }

    private String step2(String new_id) {
        StringBuilder sb = new StringBuilder();
        Pattern pattern = Pattern.compile("\\w+|(-)+|(_)+|(\\.)+");
        Matcher matcher = pattern.matcher(new_id);

        while (matcher.find())
            sb.append(matcher.group(0));

        return sb.toString();
    }

    private String step3(String new_id) {
        return new_id.replaceAll("\\.+", ".");
    }

    private String step4(String new_id) {
        if(new_id.length() > 0 && new_id.charAt(0) == '.')
            new_id = new_id.substring(1);
        if (new_id.length() > 0 && new_id.charAt(new_id.length() - 1) == '.')
            new_id = new_id.substring(0, new_id.length() - 1);
        return new_id;
    }

    private String step5(String new_id) {
        if(new_id.equals(""))
            new_id = "a";
        return new_id;
    }

    private String step6(String new_id) {
        if (new_id.length() >= 16) {
            new_id = new_id.substring(0, 15);
            if (new_id.charAt(14) == '.')
                new_id = new_id.substring(0, 14);
        }
        return new_id;
    }

    private String step7(String new_id) {
        if (new_id.length() <= 2) {
            char last = new_id.charAt(new_id.length() - 1);

            StringBuilder new_idBuilder = new StringBuilder(new_id);
            do {
                new_idBuilder.append(last);
            } while (new_idBuilder.length() < 3);

            new_id = new_idBuilder.toString();
        }
        return new_id;
    }
}