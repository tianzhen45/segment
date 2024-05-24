package my.st.domain.entity;

import java.util.ArrayList;
import java.util.List;

public class Table {

    private String tableName;

    private String comment;

    private List<Column> colList = new ArrayList<>();

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public List<Column> getColList() {
        return colList;
    }

    public void setColList(List<Column> colList) {
        this.colList = colList;
    }
}
