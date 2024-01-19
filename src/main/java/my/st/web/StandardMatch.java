package my.st.web;

import my.st.domain.match.MatchResult;
import my.st.service.match.StandardMatchByNameService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/match")
public class StandardMatch {

    @Inject
    private StandardMatchByNameService matchService;

    @ResponseBody
    @PostMapping("/match")
    public List<MatchResult> match(String names){
        return matchService.doBatchMatch(Arrays.asList(names.split("\n")));
    }

    @PostMapping("/match_str")
    public String matchString(String names){
        return matchService.doBatchMatchStringResult(Arrays.asList(names.split("\n")));
    }
}
