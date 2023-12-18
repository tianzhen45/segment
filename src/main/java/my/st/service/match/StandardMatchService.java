package my.st.service.match;


import my.st.domain.match.MatchEntry;
import my.st.domain.match.MatchResult;
import my.st.domain.type.MatchType;
import my.st.util.StandardMap;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Optional;

@Service
public class StandardMatchService {

    @Inject
    private StandardMap standardMap;



    public MatchResult doMatch(String sentence){
        MatchResult matchResult = new MatchResult(sentence);
        strictMatch(matchResult);
        return matchResult;
    }


    public void strictMatch(MatchResult matchResult){
        Optional<String> stringOptional = standardMap.ST_MAP.keySet().stream().filter(k -> k.equals(matchResult.getSentence())).findAny();
        stringOptional.ifPresent( k -> matchResult.addEntry(new MatchEntry(standardMap.ST_MAP.get(k),k, MatchType.STRICT)));
    }




    public void computeMatch(MatchResult matchResult){

    }



    public void vagueMatch(MatchResult matchResult){


    }
}
