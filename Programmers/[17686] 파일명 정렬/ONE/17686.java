import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class File {

    String head;
    int number;
    String origin;

    public File(String file) {
        this.origin = file;
        file = file.toLowerCase();

        Pattern pattern = Pattern.compile("\\d+|\\D+");
        Matcher matcher = pattern.matcher(file);

        matcher.find();
        this.head = matcher.group(0);

        matcher.find();
        this.number = Integer.parseInt(matcher.group(0));
    }
}

class Solution {
    public String[] solution(String[] files) {
        ArrayList<File> list = new ArrayList<>();
        ArrayList<String> answer = new ArrayList<>();

        for (String file : files)
            list.add(new File(file));

        list.sort((o1, o2) -> {
            if (o1.head.compareTo(o2.head) == 0)
                return o1.number - o2.number;
            return o1.head.compareTo(o2.head);
        });

        for(File file : list)
            answer.add(file.origin);

        return answer.toArray(new String[0]);
    }
}