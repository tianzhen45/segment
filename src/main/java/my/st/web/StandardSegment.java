package my.st.web;

import my.st.service.segment.StandardSegmentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Arrays;


@RestController
@RequestMapping("/segment")
public class StandardSegment {

    private static final Logger log = LoggerFactory.getLogger(StandardSegment.class);

    @Inject
    private StandardSegmentService standardSegmentService;



    @PostMapping("/name")
    public String name(String names){
        return standardSegmentService.doStandardNameSeg(Arrays.asList(names.split("\n")));
    }




}
