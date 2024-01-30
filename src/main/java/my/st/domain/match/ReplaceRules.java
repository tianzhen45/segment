package my.st.domain.match;

import my.st.util.CSVUtil;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 *  标准匹配字符串转换规则
 */
@Component
public class ReplaceRules {
    //尾部替换规则
    private  List<Rule> endReplaceRules;

    //同义词替换规则
    private  List<Rule> synonymsRules;

    private  final static Logger logger = LoggerFactory.getLogger(ReplaceRules.class);

    @Autowired
    CSVUtil csvUtil;


    @PostConstruct
    public void init(){
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
        endReplaceRules.add(new Rule("编码$", "号"));
        endReplaceRules.add(new Rule("号$", "编号"));
        endReplaceRules.add(new Rule("号$", "号码"));

        synonymsRules = new ArrayList<>();

        try {
            for (CSVRecord synRecord : csvUtil.getSynRecords()) {
                synonymsRules.add(new Rule(synRecord.get(0),synRecord.get(1)));
                synonymsRules.add(new Rule(synRecord.get(1),synRecord.get(0)));
            }
        } catch (Exception e) {
            logger.error("读取文件[同义词.csv]失败",e);
        }
        logger.info("匹配规则加载完成，尾部替换规则:{}条，同义词替换规则:{}条\n",endReplaceRules.size(),synonymsRules.size());
    }

    public ReplaceRules(){

    }


    public List<Rule> getEndReplaceRules() {
        return endReplaceRules;
    }

    public List<Rule> getSynonymsRules() {
        return synonymsRules;
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
