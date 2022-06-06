import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Page {
    String url;
    double score = 0;
    double linkScore = 0;
    ArrayList<String> extPages;

    public Page(String word, String page) {
        this.url = getUrl(page);
        extPages = new ArrayList<>();

        setScore(page, word);
        setExtPages(page);
        setLinkScore();
    }

    private String getUrl(String page) {
        Pattern pattern = Pattern.compile("<meta property=\"og:url\" content=\"(\\S*)\"");
        Matcher matcher = pattern.matcher(page);

        if (matcher.find())
            return matcher.group().split("=")[2].replaceAll("\"", "");
        return "";
    }
    
    public void addScore(double score) {
        this.score += score;
    }
    
    public double getScore() {
        return score;
    }

    private void setScore(String page, String word) {
        // body 태그 내 모든 숫자들을 공백으로 replace하여, word를 찾기 쉽도록 함
        String body = page.split("<body>")[1].split("</body>")[0].replaceAll("\\d", " ");
        Pattern pattern = Pattern.compile("\\b(?i)" + word + "\\b");
        Matcher matcher = pattern.matcher(body);

        while(matcher.find())
            score++;
    }

    private void setExtPages(String page) {
        Pattern pattern = Pattern.compile("<a href=\"https://(\\S*)\"");
        Matcher matcher = pattern.matcher(page);

        while(matcher.find())
            extPages.add(matcher.group().split("\"")[1]);
    }
    
    public double getLinkScore() {
        return linkScore;
    }

    private void setLinkScore() {
        this.linkScore = this.score / extPages.size();
    }
}

class Solution {
    public int solution(String word, String[] pages) {
        word = word.toLowerCase();

        Map<String, Page> pageInfo = new LinkedHashMap<>();

        for (String page : pages) {
            Page p = new Page(word, page);
            // url을 키로 하여 맵 추가
            pageInfo.put(p.url, p);
        }

        for (Page p : pageInfo.values()) {
            for (String extPage : p.extPages) {
                if (pageInfo.containsKey(extPage))
                    pageInfo.get(extPage).addScore(p.getLinkScore());
            }
        }

        double maxScore = 0;
        int maxIdx = 0;
        int idx = 0;
        for (Map.Entry<String, Page> entry : pageInfo.entrySet()) {
            double score = entry.getValue().getScore();
            if (score > maxScore) {
                maxScore = score;
                maxIdx = idx;
            }
            idx++;
        }
        
        return maxIdx;
    }
}