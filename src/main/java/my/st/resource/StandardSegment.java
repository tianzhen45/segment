package my.st.resource;

import my.st.service.segment.StandardSegmentService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Arrays;


@RestController
@RequestMapping("/standard")
public class StandardSegment {

    @Inject
    private StandardSegmentService standardSegmentService;

    @PostMapping("/name")
    public String name(String names){
        return standardSegmentService.doStandardNameSeg(Arrays.asList(names.split("\n")));
    }

    @PostMapping("/type")
    public String type(String names){
        return standardSegmentService.doStandardTypeInfer(Arrays.asList(names.split("\n")));
    }
}
