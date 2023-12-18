package my.segment;

import org.springframework.stereotype.Service;

import javax.inject.Inject;

@Service
public class StandardMatch {

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
