package my.st.service.match;


import my.st.segment.StandardMap;
import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class StandardMatchService {

    @Inject
    private StandardMap standardMap;



    public String preciseMatch(String sentence){
        String stIndex = null;

        standardMap.ST_MAP.keySet().stream().filter(l ->
           l.equals(sentence) || l.equals(sentence+"代码")
        );

        return stIndex;
    }


    public String vagueMatch(String sentence){

        return null;
    }
}
