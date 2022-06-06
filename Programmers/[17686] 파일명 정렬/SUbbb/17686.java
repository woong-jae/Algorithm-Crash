import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class File {
    // 변환하지 않은 원본 파일명
    String file;
    String head;
    int number;

    public File(String file) {
        this.file = file;
        file = file.toLowerCase();

        // 숫자가 연속이거나, 숫자가 아닌 문자가 연속인 경우 찾기
        Pattern p = Pattern.compile("\\d+|\\D+");
        Matcher matcher = p.matcher(file);

        matcher.find();
        this.head = matcher.group(0);
        matcher.find();
        this.number = Integer.parseInt(matcher.group(0));
    }
}

class Solution {
    public String[] solution(String[] files) {
        ArrayList<File> fileList = new ArrayList<>();

        for (String file : files)
            fileList.add(new File(file));

        fileList.sort(((o1, o2) -> {
            // head가 동일하다면, number 기준 오름차순 정렬
            if (o1.head.compareTo(o2.head) == 0) return o1.number - o2.number;
            // head 기준 사전순 정렬
            return o1.head.compareTo(o2.head);
        }));
        
        String[] answer = new String[files.length];
        
        for (int i = 0; i < fileList.size(); i++) 
            answer[i] = fileList.get(i).file;
        
        return answer;
    }
}