package my.st.service.bigmodel;

import my.st.SegmentApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
@SpringBootTest(classes = SegmentApplication.class)
@RunWith(SpringRunner.class)
public class XFSparkModelTest {

    @Inject
    XFSparkModel xfSparkModel;

    @Test
    public void answer() throws Exception{
        System.out.println(xfSparkModel.answer("请用一句话回答“换出仓单价值”的业务含义"));
    }
}