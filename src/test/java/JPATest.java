import my.st.SegmentApplication;
import my.st.domain.repo.CommonCol;
import my.st.domain.repo.CommonColRepository;
import my.st.domain.repo.Word;
import my.st.domain.repo.WordRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest(classes = SegmentApplication.class)
@RunWith(SpringRunner.class)
public class JPATest {

    @Resource
    CommonColRepository repository;

    @Resource
    WordRepository wordRepository;



    @Test
    public void testCommonCol02(){
        List<CommonCol> cols = repository.findBySystemCode("TY");
        System.out.println(cols.size());
    }

    @Test
    public void testCommonCol03(){
        List<CommonCol> cols = repository.findAll();
        System.out.println(cols.size());
    }

    @Test
    public void testWord(){
        List<Word> cols = wordRepository.findAll();
        System.out.println(cols.size());
    }
}
