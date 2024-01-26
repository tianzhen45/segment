package my.st.web;

import my.st.service.bigmodel.QuestionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Semaphore;

@RestController
public class QuestionController {  
  
    private final QuestionService questionService;
  
    public QuestionController(QuestionService questionService) {  
        this.questionService = questionService;  
    }

    private final static Semaphore semaphore = new Semaphore(2);
  
    @PostMapping("/ask")
    public String askQuestion(@RequestBody String question) throws Exception {
        String answer = "";
        if(semaphore.tryAcquire()){
            try {
                answer = questionService.getAnswer(question);
            } finally {
                semaphore.release();
            }
        } else {
            answer = "正在处理问题，请稍后再试";
        }
        return answer;
    }  
}