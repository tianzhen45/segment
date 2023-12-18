package my.st.util;

import org.apache.commons.csv.CSVRecord;

import java.util.HashMap;
import java.util.Map;

public class StandardMap {

    public final Map<String, String> ST_MAP = new HashMap<>();

    private static final StandardMap map = new StandardMap();

    public static StandardMap getInstance() {
        return map;
    }


    private StandardMap() {
        try {
            for (CSVRecord l : CSVUtil.getStRecords()) {
                ST_MAP.put(l.get(1), l.get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.printf("标准加载完成,数量:%d", ST_MAP.size());
    }
}
