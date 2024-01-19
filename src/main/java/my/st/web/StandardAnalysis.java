package my.st.web;

import my.st.service.analysis.StandardTypeInferService;
import org.springframework.web.bind.annotation.PostMapping;

import javax.inject.Inject;
import java.util.Arrays;

public class StandardAnalysis {

    @Inject
    private StandardTypeInferService typeInferService;


    @PostMapping("/type")
    public String type(String names){
        return typeInferService.doStandardTypeInfer(Arrays.asList(names.split("\n")));
    }
}
