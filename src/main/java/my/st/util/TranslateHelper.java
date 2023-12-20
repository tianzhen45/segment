package my.st.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Component
public class TranslateHelper {

    public final Map<String, String> WORD_MAP = new HashMap<>();

    public static final String NON_WORD_REX ="[\"“”，,。.—\\-\\_<>\\(\\)（）？\\?:\\\\/]";

    public final static Logger logger = LoggerFactory.getLogger(TranslateHelper.class);

    @PostConstruct
    private void init(){
        try {
            CSVUtil.getWordRecords().forEach(l -> {
                WORD_MAP.put(l.get(1),l.get(3));
            });
        }catch (Exception e){
            e.printStackTrace();
        }
        logger.info("翻译组件加载完成,加载单词数量:{}\n", WORD_MAP.size());
    }



    /**
     * 中文单词翻译成英文
     * 1. 英文不翻译
     * 2. 未收录的单词翻译为？
     * @param cnWord
     * @return
     */
    public String translate(String cnWord) {
        return cnWord.matches("[a-zA-Z]+") ?
                cnWord :
                WORD_MAP.getOrDefault(cnWord, "?");
    }

    /**
     * 替换特殊符号 \/，,。."“”?:？：_——<>（）()
     * @param word
     * @return
     */
    public static String replaceSign(String word){
        return word.replaceAll(NON_WORD_REX,"");
    }
}
