package my.st.service.segment;


import com.huaban.analysis.jieba.SegToken;
import my.st.util.TranslateHelper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.inject.Inject;
import java.util.List;

/**
 * 标准分词并翻译服务
 */
@Service
public class StandardSegmentService {

    @Resource
    private SegmentService segmentService;

    @Resource
    private TranslateHelper translateHelper;



    /**
     * 批量执行分词并翻译
     *  <p>Name -> SEG1_SEG2'\t'ENG1_ENG2
     */
    public String doStandardNameSeg(List<String> readList) {
        StringBuilder builder = new StringBuilder();
        readList.stream().filter(StringUtils::isNotEmpty).forEach(l -> {
            builder.append(l).append("\t");
            List<SegToken> segTokens = segmentService.doSegment(TranslateHelper.replaceSign(l));
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            for (SegToken segToken : segTokens) {
                sb.append(segToken.word.toUpperCase()).append("_");
                sb2.append(translateHelper.translate(segToken.word.toUpperCase())).append("_");
            }
            sb.deleteCharAt(sb.length() - 1);
            sb2.deleteCharAt(sb2.length() - 1);
            builder.append(sb).append("\t")
                    .append(sb2).append("\t")
                    .append(verifyColEnName(sb2.toString()))
                    .append("\r\n");
        });
        return builder.toString();
    }


    public String verifyColEnName(String colEnName){
        String msg = "";
        if(colEnName.contains("?")){
            msg += "包含未收录的词根;";
        }
        if(colEnName.split("_").length > 4){
            msg+="分词数量过长;";
        }
        return msg;
    }

}
