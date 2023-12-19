package my.st.domain.match;

import java.util.ArrayList;
import java.util.List;

public class MatchResult {

    private String sentence;

    public List<MatchEntry> getList() {
        return list;
    }


    private final List<MatchEntry> list;

    public MatchResult(String sentence) {
        this.sentence = sentence;
        this.list = new ArrayList<>();
    }

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    public boolean hasEntry(MatchEntry matchEntry) {
        return this.list.stream().anyMatch(matchEntry::equals);
    }

    public String getSentence() {
        return sentence;
    }

    public void addEntry(MatchEntry matchEntry) {
        if(!hasEntry(matchEntry)) this.list.add(matchEntry);
    }

    @Override
    public String toString() {
        return "MatchResult{" +
                "sentence='" + sentence + '\'' +
                ", list=" + list +
                '}';
    }
}
