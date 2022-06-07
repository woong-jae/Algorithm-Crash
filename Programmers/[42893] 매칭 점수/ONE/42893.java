import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Page {
    int index;
    double score;
    double linkScore;
    String url;
    List<String> externalLink = new ArrayList<>();

    public Page(int index, String url) {
        this.index = index;
        this.url = url;
        this.score = 0;
        this.linkScore = 0;
    }

    public void addLink(String link) {
        this.externalLink.add(link);
    }

    public void setLinkScore() {
        this.linkScore = this.score / externalLink.size();
    }
}

class Solution {
    public int solution(String word, String[] pages) {
        int answer = 0, index = 0;
        Pattern homeUrl = Pattern.compile("<meta property=\"og:url\" content=\"\\S+>");
        Pattern externalUrl = Pattern.compile("<a href=\"\\S+\">");
        Pattern wordPattern = Pattern.compile("\\b"+word.toLowerCase()+"\\b");
        Matcher matcher;
        Map<String, Page> pageMap = new HashMap<>();

        for (String page : pages) {
            matcher = homeUrl.matcher(page);
            matcher.find();
            Page newPage = new Page(index++, matcher.group(0).split("\"")[3]);

            matcher = externalUrl.matcher(page);
            while (matcher.find())
                newPage.addLink(matcher.group(0).split("\"")[1]);

            String body = page.toLowerCase().split("<body>")[1].split("</body>")[0].replaceAll("[^a-z]", " ");
            matcher = wordPattern.matcher(body);
            while (matcher.find())
                newPage.score++;

            newPage.setLinkScore();
            pageMap.put(newPage.url, newPage);
        }

        for (Page p : pageMap.values())
            for (String e : p.externalLink)
                if(pageMap.containsKey(e))
                    pageMap.get(e).score += p.linkScore;

        double max = Double.MIN_VALUE;
        for (Page p : pageMap.values()) {
            if(p.score == max && p.index < answer)
                answer = p.index;
            else if (p.score > max) {
                answer = p.index;
                max = p.score;
            }
        }

        return answer;
    }
}
