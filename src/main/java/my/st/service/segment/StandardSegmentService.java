package my.st.service.segment;


import com.huaban.analysis.jieba.SegToken;
import my.st.segment.SegmentService;
import my.st.segment.TranslateHelper;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@Service
public class StandardSegmentService {

    @Inject
    private SegmentService segmentService;

    @Inject
    private TranslateHelper translateHelper;


    public String doStandardNameSeg(List<String> readList) {
        StringBuilder builder = new StringBuilder();
        readList.forEach(l -> {
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
}
