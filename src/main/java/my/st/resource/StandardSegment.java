package my.st.resource;

import my.st.domain.match.MatchResult;
import my.st.service.match.StandardMatchService;
import my.st.service.segment.StandardSegmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;


@RestController
@RequestMapping("/standard")
public class StandardSegment {

    private static final Logger log = LoggerFactory.getLogger(StandardSegment.class);

    @Inject
    private StandardSegmentService standardSegmentService;

    @Inject
    private StandardMatchService matchService;

    @PostMapping("/name")
    public String name(String names){
        return standardSegmentService.doStandardNameSeg(Arrays.asList(names.split("\n")));
    }

    @PostMapping("/type")
    public String type(String names){
        return standardSegmentService.doStandardTypeInfer(Arrays.asList(names.split("\n")));
    }

    @ResponseBody
    @PostMapping("/match")
    public List<MatchResult> match(String names){
        return matchService.doBatchMath(Arrays.asList(names.split("\n")));
    }
}
