import com.huaban.analysis.jieba.SegToken;
import my.st.SegmentApplication;
import my.st.service.segment.SegmentService;
import my.st.util.*;
import my.st.service.segment.StandardSegmentService;
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
