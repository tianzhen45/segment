import com.huaban.analysis.jieba.SegToken;
import my.st.SegmentApplication;
import my.segment.*;
import my.service.segment.StandardSegmentService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest(classes = SegmentApplication.class)
@RunWith(SpringRunner.class)
public class SpringTest {


    @Inject
    private SegmentService segmentService;

    @Inject
    private TranslateHelper translateHelper;


    @Inject
    private StandardSegmentService standardSegmentService;

    @Inject
    private StandardMap standardMap;

    @Test
    public void run1() throws Exception {
        BufferedReader bfr = new BufferedReader(new FileReader(SegmentConstant.INOUT_PATH));
        ArrayList<String> readList = bfr.lines().collect(Collectors.toCollection(ArrayList::new));
        bfr.close();

        BufferedWriter bfw = new BufferedWriter(new FileWriter(SegmentConstant.INOUT_PATH));
        readList.forEach(l -> {
            List<SegToken> segTokens = segmentService.doSegment(TranslateHelper.replaceSign(l));
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            for (SegToken segToken : segTokens) {
                sb.append(segToken.word).append("_");
                sb2.append(translateHelper.translate(segToken.word)).append("_");
            }
            try {
                bfw.write(sb.deleteCharAt(sb.length() - 1).append("\t").append(sb2.deleteCharAt(sb2.length() - 1)).append("\r\n").toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bfw.close();
    }

    @Test
    public void testSegService() {
        List<String> list = Arrays.asList("资金监管账户帐号", "核心客户号");
        String s = standardSegmentService.doStandardNameSeg(list);
        System.out.println(s);
    }

    @Test
    public void testStMap(){
        System.out.println(standardMap.ST_MAP.get("特别委托授权对象"));
    }
}
