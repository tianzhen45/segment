package my.segment;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class CSVUtil {

    public static List<CSVRecord> getWordRecords()throws Exception{
        return  CSVParser.parse(new File(SegmentConstant.WORD_PATH), StandardCharsets.UTF_8, CSVFormat.EXCEL).getRecords();
    }


    public static List<CSVRecord> getStRecords()throws Exception{
        return  CSVParser.parse(new File(SegmentConstant.ST_PATH), StandardCharsets.UTF_8, CSVFormat.EXCEL).getRecords();
    }

}
