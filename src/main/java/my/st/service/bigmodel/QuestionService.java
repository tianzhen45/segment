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
        // 假设有一个callAIAnswer函数用于调用AI接口，返回答案字符串  
        return callAIAnswer(question);  
    }

    private String callAIAnswer(String question)throws Exception {
        return agent.answer(question);
    }
}