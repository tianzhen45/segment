package my.st.service.bigmodel;

import my.st.SegmentApplication;
import my.st.web.QuestionController;
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

    @Inject
    QuestionController questionController;



    @Test
    public void answer() throws Exception{
        System.out.println(xfSparkModel.answer("请用一句话回答“换出仓单价值”的业务含义"));
    }


    @Test
    public void concurrentAnswer() throws Exception{
        for (int i = 0; i < 4; i++) {
            new Thread(new myThread(xfSparkModel,"请用一句话回答“换出仓单价值”的业务含义")).start();
        }
        Thread.sleep(60000);
    }

    @Test
    public void concurrentControllerAnswer() throws Exception{
        for (int i = 0; i < 3; i++) {
            new Thread(new myThread2(questionController,"请用一句话回答“换出仓单价值”的业务含义")).start();
        }
        Thread.sleep(60000);
    }



    class myThread2 implements Runnable{
        QuestionController questionController;
        String message;

        public myThread2(QuestionController questionController,String message){
            this.questionController = questionController;
            this.message = message;
        }

        @Override
        public void run() {
            try {
                System.out.println(questionController.askQuestion(message));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    class myThread implements Runnable{
        XFSparkModel xfSparkModel;
        String message;

        public myThread(XFSparkModel xfSparkModel,String message){
            this.xfSparkModel = xfSparkModel;
            this.message = message;
        }

        @Override
        public void run() {
            try {
                System.out.println(xfSparkModel.answer(message));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }


}