package my.st.domain.match;

import my.st.domain.type.MatchType;

public class MatchEntry {

    private String standNo;

    private String standName;

    private MatchType type;

    public MatchEntry(String standNo, String standName,MatchType type) {
        this.standNo = standNo;
        this.standName = standName;
        this.type = type;
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
}
