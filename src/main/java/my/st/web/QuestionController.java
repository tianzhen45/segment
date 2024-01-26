package my.st.web;

import my.st.service.bigmodel.QuestionService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class QuestionController {  
  
    private final QuestionService questionService;
  
    public QuestionController(QuestionService questionService) {  
        this.questionService = questionService;  
    }

  
    @PostMapping("/ask")
    public String askQuestion(@RequestBody String question) throws Exception {
        return questionService.getAnswer(question);
    }  
}