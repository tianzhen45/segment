package my.st.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 读取CSV文件的工具类
 */
@Component
public class CSVUtil {


    @Value("${myapp.word-path}")
    private String wordPath;

    @Value("${myapp.st-path}")
    private String stPath;

    @Value("${myapp.synonyms-path}")
    private String synonymsPath;

    private static final Logger logger = LoggerFactory.getLogger(CSVUtil.class);



    public List<CSVRecord> getRecords(String path){
        List<CSVRecord> list = null;
        try(CSVParser parse = CSVParser.parse(new File(path), StandardCharsets.UTF_8, CSVFormat.EXCEL)){
            list =  parse.getRecords();
        }catch (IOException e){
            logger.error("读取路径{}文件出错",path);
        }
        return list;
    }


    /**
     * 读取最新词根文件
     */
    public List<CSVRecord> getWordRecords()  {
        return getRecords(wordPath);
    }

    /**
     * 读取最新标准文件
     */
    public List<CSVRecord> getStRecords()  {
        return getRecords(stPath);
    }

    /**
     * 读取同义词文件
     */
    public List<CSVRecord> getSynRecords() {
        return getRecords(synonymsPath);
    }
}
