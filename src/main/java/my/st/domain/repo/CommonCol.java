package my.st.domain.repo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "t_common_col")
public class CommonCol {
    @Id
    private Integer id;

    private String colName;

    private String colEnName;

    private String type;

    private String len;

    private String systemCode;

    public String getSystemCode() {
        return systemCode;
    }

    public void setSystemCode(String systemCode) {
        this.systemCode = systemCode;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getColName() {
        return colName;
    }

    public void setColName(String colName) {
        this.colName = colName;
    }

    public String getColEnName() {
        return colEnName;
    }

    public void setColEnName(String colEnName) {
        this.colEnName = colEnName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public String getLen() {
        return len;
    }

    public void setLen(String len) {
        this.len = len;
    }
}
