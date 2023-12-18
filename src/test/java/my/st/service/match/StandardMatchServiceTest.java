package my.st.service.match;

import my.st.SegmentApplication;

import my.st.service.analysis.StandardAnalysisService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.util.stream.Stream;


@SpringBootTest(classes = SegmentApplication.class)
@RunWith(SpringRunner.class)
public class StandardMatchServiceTest {

    @Inject
    StandardMatchService matchService;

    @Inject
    StandardAnalysisService  analysisService;


    @Test
    public void doMatch() {
        System.out.println(matchService.doMatch("账户币种代码"));
    }

    @Test
    public void inferType(){
        Stream.of("业务管户客户经理编号","主担保方式代码","违约标识","解除时间","机构编码")
                .forEach(s -> System.out.println(s+" -> "+analysisService.inferStandType(s)));
    }
}