import com.huaban.analysis.jieba.JiebaSegmenter;
import my.st.SegmentApplication;
import my.st.service.segment.SegmentService;
import my.st.service.segment.StandardSegmentService;
import my.st.util.StandardMap;
import my.st.util.TranslateHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

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
    public void testSegService() {
        List<String> list = Arrays.asList("资金监管账户帐号", "核心客户号");
        String s = standardSegmentService.doStandardNameSeg(list);
        System.out.println(s);
    }

    @Test
    public void testStMap(){
        System.out.println(standardMap.ST_MAP.get("特别委托授权对象"));
    }

    @Test
    public void testSegService2() {
        System.out.println(segmentService.doSegment("上期平均数主营产品", JiebaSegmenter.SegMode.INDEX));
    }
}
