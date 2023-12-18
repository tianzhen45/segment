package my.st.util;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * 读取CSV文件的工具类
 *  todo 替换为数据源获取
 */
public class CSVUtil {

    /**
     *  读取最新词根文件
     */
    public static List<CSVRecord> getWordRecords()throws Exception{
        return  CSVParser.parse(new File(SegmentConstant.WORD_PATH), StandardCharsets.UTF_8, CSVFormat.EXCEL).getRecords();
    }

    /**
     *  读取最新标准文件
     */
    public static List<CSVRecord> getStRecords()throws Exception{
        return  CSVParser.parse(new File(SegmentConstant.ST_PATH), StandardCharsets.UTF_8, CSVFormat.EXCEL).getRecords();
    }


}
