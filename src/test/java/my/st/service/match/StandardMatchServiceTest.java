package my.st.service.match;

import my.st.SegmentApplication;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;


@SpringBootTest(classes = SegmentApplication.class)
@RunWith(SpringRunner.class)
public class StandardMatchServiceTest {

    @Inject
    StandardMatchService matchService;

    @Test
    public void doMatch() {
        System.out.println(matchService.doMatch("账户币种代码"));
    }
}