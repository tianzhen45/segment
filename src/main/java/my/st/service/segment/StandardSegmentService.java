package my.st.service.segment;


import com.huaban.analysis.jieba.SegToken;
import my.st.service.analysis.StandardTypeInferService;
import my.st.util.TranslateHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

/**
 * 标准分词并翻译服务
 */
@Service
public class StandardSegmentService {

    @Inject
    private SegmentService segmentService;

    @Inject
    private TranslateHelper translateHelper;

    @Inject
    private StandardTypeInferService analysisService;


    /**
     * 批量执行分词并翻译
     *  <p>Name -> SEG1_SEG2'\t'ENG1_ENG2
     */
    public String doStandardNameSeg(List<String> readList) {
        StringBuilder builder = new StringBuilder();
        readList.stream().filter(StringUtils::isNotEmpty).forEach(l -> {
            List<SegToken> segTokens = segmentService.doSegment(TranslateHelper.replaceSign(l));
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            for (SegToken segToken : segTokens) {
                sb.append(segToken.word).append("_");
                sb2.append(translateHelper.translate(segToken.word)).append("_");
            }
            builder.append(sb.deleteCharAt(sb.length() - 1).append("\t").append(sb2.deleteCharAt(sb2.length() - 1)).append("\r\n"));
        });
        return builder.toString();
    }

    /**
     * 批量推断标准类型
     */
    public String doStandardTypeInfer(List<String> list) {
        StringBuilder builder = new StringBuilder();
        list.stream().filter(StringUtils::isNotEmpty).forEach(l ->
                builder.append(l).append("\t").append(analysisService.inferStandTypeByName(l)).append("\n"));
        return builder.toString();
    }
}
