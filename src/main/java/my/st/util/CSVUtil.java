package my.st.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 读取CSV文件的工具类
 *  todo 替换为数据源获取
 */
@Component
public class CSVUtil {


    @Value("${myapp.word-path}")
    private String wordPath;

    @Value("${myapp.st-path}")
    private String stPath;

    @Value("${myapp.synonyms-path}")
    private String synonymsPath;

    /**
     * 读取最新词根文件
     */
    public List<CSVRecord> getWordRecords() throws Exception {
        return CSVParser.parse(new File(wordPath), StandardCharsets.UTF_8, CSVFormat.EXCEL).getRecords();
    }

    /**
     * 读取最新标准文件
     */
    public List<CSVRecord> getStRecords() throws Exception {
        return CSVParser.parse(new File(stPath), StandardCharsets.UTF_8, CSVFormat.EXCEL).getRecords();
    }

    /**
     * 读取同义词文件
     */
    public List<CSVRecord> getSynRecords() throws Exception {
        return CSVParser.parse(new File(synonymsPath), StandardCharsets.UTF_8, CSVFormat.EXCEL).getRecords();
    }
}
