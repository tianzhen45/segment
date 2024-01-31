package my.st.domain.repo;

public class Stand {

    private String no;

    private String cnName;

    private String enName;

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public String getCnName() {
        return cnName;
    }

    public void setCnName(String cnName) {
        this.cnName = cnName;
    }

    public String getEnName() {
        return enName;
    }

    public void setEnName(String enName) {
        this.enName = enName;
    }

    @Override
    public String toString() {
        return "Stand{" +
                "no='" + no + '\'' +
                ", cnName='" + cnName + '\'' +
                ", enName='" + enName + '\'' +
                '}';
    }
}
