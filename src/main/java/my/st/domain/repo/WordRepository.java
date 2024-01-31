package my.st.domain.repo;

import my.st.util.CSVUtil;
import my.st.util.StandardMap;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;

@Service
public class WordRepository{

    public static final String REDIS_KEY = "SEGMENT_WORD";

    public final static Logger logger = LoggerFactory.getLogger(WordRepository.class);

    private RedisTemplate<String,String> redisTemplate;

    private CSVUtil csvUtil;

    public WordRepository(RedisTemplate<String, String> redisTemplate, CSVUtil csvUtil) {
        this.redisTemplate = redisTemplate;
        this.csvUtil = csvUtil;
    }

    @PostConstruct
    private void init(){
        try {
            long startTime = System.currentTimeMillis();
            List<CSVRecord> wordRecords = csvUtil.getWordRecords();
            for (CSVRecord wordRecord : wordRecords) {
                 redisTemplate.opsForHash().putIfAbsent(REDIS_KEY,wordRecord.get(1),wordRecord.get(3));
            }
            logger.info("完成加载词语{}条，耗时{}ms\n", wordRecords.size(),System.currentTimeMillis()-startTime);
        } catch (Exception e) {
           e.printStackTrace();
        }
    }

    public String getEnName(String cnName){
        return Objects.toString(redisTemplate.opsForHash().get(REDIS_KEY, cnName),"?");
    }

}
