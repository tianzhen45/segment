package my.st.service.bigmodel;

import org.springframework.stereotype.Service;
  
@Service  
public class QuestionService {

    private AIAgent agent;


    public QuestionService(AIAgent agent){
        this.agent = agent;
    }
  
    public String getAnswer(String question)throws Exception {
        // 与AI问答接口通信获取答案的逻辑
        return agent.answer(question);
    }

}