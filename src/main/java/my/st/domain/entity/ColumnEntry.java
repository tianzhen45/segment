package my.st.domain.entity;

public class ColumnEntry {

    private String tbName;

    private String tbCnName;

    private String colName;

    private String colCnName;

    private String type;

    private String length;

    public String getTbName() {
        return tbName;
    }

    public void setTbName(String tbName) {
        this.tbName = tbName;
    }

    public String getTbCnName() {
        return tbCnName;
    }

    public void setTbCnName(String tbCnName) {
        this.tbCnName = tbCnName;
    }

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public String getColCnName() {
        return colCnName;
    }

    public void setColCnName(String colCnName) {
        this.colCnName = colCnName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }
}
