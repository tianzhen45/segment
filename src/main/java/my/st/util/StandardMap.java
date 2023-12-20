package my.st.util;

import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

/**
 * 标准名称->标准编号的Map
 * <p> Map <standName -> StandNo>
 */
@Component
public class StandardMap {

    public final Map<String, String> ST_MAP = new HashMap<>();

    public final static Logger logger = LoggerFactory.getLogger(StandardMap.class);

    /**
     *  Map <standName -> StandNo>
     */
    @PostConstruct
    private void init() {
        try {
            for (CSVRecord l : CSVUtil.getStRecords()) {
                ST_MAP.put(l.get(1), l.get(0));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("标准加载完成,数量:{}\n", ST_MAP.size());
    }
}
