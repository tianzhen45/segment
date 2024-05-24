package my.st.web.entity;

import my.st.domain.repo.Word;


public class WordParam implements RequestParam {

    private String cnName;

    private String enName;

    public Word toWord(){
        return new Word(this.cnName,this.enName);
    }

    public WordParam(String cnName, String enName) {
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
}
