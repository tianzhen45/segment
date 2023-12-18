package my.st.domain.match;

import java.util.ArrayList;
import java.util.List;

public class MatchResult {

    private String sentence;

    private List<MatchEntry> list;

    public MatchResult(String sentence) {
        this.sentence = sentence;
        this.list = new ArrayList<>();
    }

    public String getSentence() {
        return sentence;
    }

    public void addEntry(MatchEntry matchEntry){ this.list.add(matchEntry);}

    @Override
    public String toString() {
        return "MatchResult{" +
                "sentence='" + sentence + '\'' +
                ", list=" + list +
                '}';
    }
}
