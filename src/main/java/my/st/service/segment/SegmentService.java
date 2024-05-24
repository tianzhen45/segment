package my.st.service.segment;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;
import com.huaban.analysis.jieba.WordDictionary;
import my.st.domain.repo.Word;
import my.st.domain.repo.WordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
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

    @Resource
    private WordRepository wordRepository;

    @Value("${myapp.dict-path}")
    private String dictPath;

    @Value("${myapp.config-path}")
    private String configPath;


    /**
     * 从数据库加载单词到dict
     */
    @PostConstruct
    private void init(){
        try {
            FileWriter fileWriter = new FileWriter(dictPath);
            List<Word> wordList = wordRepository.findAll();
            for (Word word : wordList) {
                try {
                    fileWriter.write(String.format("%s %s %s\n", word.getCnName(), "300", "n"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            fileWriter.close();
            WordDictionary.getInstance().init(Paths.get(configPath));
            logger.info("词根库初始化完成，加载数量:{}\n",wordList.size());
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
     * 重新加载DB中的词根到词典文件中
     */
    public void reloadDBDict(){
        resetDictCache();
        init();
    }
}
