package my.st.domain.match;

import my.st.domain.type.MatchType;

import java.util.Objects;

public class MatchEntry {

    private String standNo;

    private String standName;

    private MatchType type;

    public MatchEntry(String standNo, String standName,MatchType type) {
        this.standNo = standNo;
        this.standName = standName;
        this.type = type;
    }

    public MatchType getType() {
        return type;
    }

    public String getStandNo() {
        return standNo;
    }

    public void setStandNo(String standNo) {
        this.standNo = standNo;
    }

    public String getStandName() {
        return standName;
    }

    public void setStandName(String standName) {
        this.standName = standName;
    }

    @Override
    public String toString() {
        return "MatchEntry{" +
                "standNo='" + standNo + '\'' +
                ", standName='" + standName + '\'' +
                ", matchType='" + type + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MatchEntry that = (MatchEntry) o;
        return Objects.equals(standNo, that.standNo);
    }

    @Override
    public int hashCode() {
        return Objects.hash(standNo);
    }
}
