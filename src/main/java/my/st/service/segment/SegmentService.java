package my.st.service.segment;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.SegToken;
import com.huaban.analysis.jieba.WordDictionary;
import my.st.util.CSVUtil;
import my.st.util.SegmentConstant;
import org.apache.commons.csv.CSVRecord;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

public class SegmentService {

    private final static SegmentService segmentService = new SegmentService();

    public static SegmentService getInstance(){
        return segmentService;
    }

    private SegmentService(){
        init();
    }

    public JiebaSegmenter getSegmenter() {
        return segmenter;
    }

    private static JiebaSegmenter segmenter;

    /**
     * 加载csv词根文件到dict
     */
    private void init(){
        try {
            List<CSVRecord> records = CSVUtil.getWordRecords();
            FileWriter fileWriter = new FileWriter(SegmentConstant.DICT_PATH);
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
            segmenter = new JiebaSegmenter();
            WordDictionary.getInstance().init(Paths.get(SegmentConstant.CONFIG_PATH));
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
}
