package my.st.service.segment;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;
import com.huaban.analysis.jieba.WordDictionary;
import my.st.util.CSVUtil;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

/**
 * 分词服务
 */
@Service
public class SegmentService {


    public JiebaSegmenter getSegmenter() {
        return segmenter;
    }

    private final static JiebaSegmenter segmenter = new JiebaSegmenter();

    public final static Logger logger = LoggerFactory.getLogger(SegmentService.class);

    @Autowired
    CSVUtil csvUtil;

    @Value("${myapp.dict-path}")
    private String dictPath;

    @Value("${myapp.config-path}")
    private String configPath;


    /**
     * 加载csv词根文件到dict
     */
    @PostConstruct
    private void init(){
        try {
            List<CSVRecord> records = csvUtil.getWordRecords();
            FileWriter fileWriter = new FileWriter(dictPath);
            records.forEach( r ->
                    {
                        try {
                            fileWriter.write(String.format("%s %s %s\n", r.get(1), "300", "n"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
            );
            fileWriter.close();
            WordDictionary.getInstance().init(Paths.get(configPath));
            logger.info("词根库初始化完成，加载数量:{}\n",records.size());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<SegToken> doSegment(String sentence) {
        return segmenter.process(Optional.ofNullable(sentence).orElse(""), JiebaSegmenter.SegMode.SEARCH);
    }

    public List<SegToken> doSegment(String sentence,JiebaSegmenter.SegMode mode) {
        return segmenter.process(Optional.ofNullable(sentence).orElse(""), mode);
    }

    /**
     * 清空内存中的词典缓存
     */
    private void resetDictCache(){
        WordDictionary.getInstance().freqs.clear();
        WordDictionary.getInstance().loadDict();
        logger.info("清空内存中的词典缓存完成\n");
    }

    /**
     * 重新加载csv中的词典文件
     */
    public void reloadCsvDict(){
        resetDictCache();
        init();
    }
}
