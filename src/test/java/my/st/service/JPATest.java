package my.st.service;

import my.st.SegmentApplication;
import my.st.domain.repo.WordRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.util.List;

@SpringBootTest(classes = SegmentApplication.class)
@RunWith(SpringRunner.class)
public class JPATest {

    @Inject
    WordRepository wordRepository;

    @Test
    public void test01(){
        List all = wordRepository.findAll();
        System.out.println(all.size());
    }

    @Test
    public void test02(){
        System.out.println(wordRepository.findByCnName("六个月"));
        System.out.println(wordRepository.findByEnName("6MO"));
    }

}
