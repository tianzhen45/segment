package my.st.domain.match;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ReplaceRules {

    private final List<Rule> endReplaceRules;

    private final List<Rule> containReplaceRules;

    public ReplaceRules(){
        endReplaceRules = new ArrayList<>();
        endReplaceRules.add(new Rule("号码$", "编号"));
        endReplaceRules.add(new Rule("编码$", "编号"));
        endReplaceRules.add(new Rule("流水号$", "编号"));
        endReplaceRules.add(new Rule("工号$", "编号"));
        endReplaceRules.add(new Rule("ID$", "编号"));
        endReplaceRules.add(new Rule("人$", "人编号"));
        endReplaceRules.add(new Rule("员$", "员编号"));
        endReplaceRules.add(new Rule("机构$", "机构编号"));
        endReplaceRules.add(new Rule("行号$", "银行编号"));
        endReplaceRules.add(new Rule("日$", "日期"));
        endReplaceRules.add(new Rule("姓名$", "名称"));
        endReplaceRules.add(new Rule("标识$", "标志"));
        endReplaceRules.add(new Rule("人$", "人工号"));
        endReplaceRules.add(new Rule("员$", "员工号"));
        endReplaceRules.add(new Rule("户名$", "账户名称"));
        endReplaceRules.add(new Rule("账户账号$", "账号"));
        containReplaceRules = new ArrayList<>();
    }


    public List<Rule> getEndReplaceRules() {
        return endReplaceRules;
    }

    public List<Rule> getContainReplaceRules() {
        return containReplaceRules;
    }

    public final static class Rule{
        private String regex;
        private String replaceWord;

         public Rule(String regex, String replaceWord) {
             this.regex = regex;
             this.replaceWord = replaceWord;
         }

         public String getRegex() {
             return regex;
         }

         public void setRegex(String regex) {
             this.regex = regex;
         }

         public String getReplaceWord() {
             return replaceWord;
         }

         public void setReplaceWord(String replaceWord) {
             this.replaceWord = replaceWord;
         }
     }
}
