package my.st.domain.repo;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name = "t_word")
public class Word {

    @Id
    private String cnName;

    private String enName;

    public Word() {
    }

    public Word(String cnName, String enName) {
        this.cnName = cnName;
        this.enName = enName;
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
        return "Word{" +
                "cnName='" + cnName + '\'' +
                ", enName='" + enName + '\'' +
                '}';
    }
}
