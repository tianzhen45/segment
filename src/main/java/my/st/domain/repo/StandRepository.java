package my.st.domain.repo;

import com.alibaba.fastjson.JSON;
import my.st.util.CSVUtil;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Objects;

@Service
public class StandRepository {

    public static final String REDIS_KEY = "SEGMENT_STAND";

    public final static Logger logger = LoggerFactory.getLogger(StandRepository.class);

    private RedisTemplate<String,String> redisTemplate;

    private CSVUtil csvUtil;

    public StandRepository(RedisTemplate<String, String> redisTemplate, CSVUtil csvUtil) {
        this.redisTemplate = redisTemplate;
        this.csvUtil = csvUtil;
    }

    @PostConstruct
    private void init(){
        try {
            long startTime = System.currentTimeMillis();
            List<CSVRecord> stRecords = csvUtil.getStRecords();
            for (CSVRecord stRecord : stRecords) {
                Stand stand = new Stand();
                stand.setNo(stRecord.get(0));
                stand.setCnName(stRecord.get(1));
                stand.setEnName(stRecord.get(2));
                this.redisTemplate.opsForHash().putIfAbsent(REDIS_KEY,stand.getCnName(),JSON.toJSONString(stand));
            }
            logger.info("成功加载标准{}条，耗时{}ms\n",stRecords.size(),System.currentTimeMillis()-startTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Stand get(String cnName){
        return JSON.parseObject(Objects.toString(redisTemplate.opsForHash().get(REDIS_KEY,cnName)),Stand.class);
    }

}
