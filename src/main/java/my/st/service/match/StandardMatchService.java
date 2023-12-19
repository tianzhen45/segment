package my.st.service.match;


import my.st.domain.match.MatchEntry;
import my.st.domain.match.MatchResult;
import my.st.domain.type.MatchType;
import my.st.domain.type.StandardType;
import my.st.service.analysis.StandardTypeInferService;
import my.st.util.StandardMap;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 字段中文对标匹配
 */
@Service
public class StandardMatchService {

    private static final Logger log = LoggerFactory.getLogger(StandardMatchService.class);

    @Inject
    private StandardMap standardMap;

    @Inject
    private StandardTypeInferService typeInferService;


    private List<MatchResult> logScope(List<MatchResult> list) {
        StringBuilder sb = new StringBuilder();
        for (MatchResult matchResult : list) {
            if(matchResult.getList().isEmpty()){
                sb.append(matchResult.getSentence()).append("\r\n");
            }
            for (MatchEntry entry : matchResult.getList()) {
                sb.append(matchResult.getSentence()).append("\t")
                        .append(entry.getStandNo()).append("\t")
                        .append(entry.getStandName()).append("\t")
                        .append(entry.getType()).append("\r\n");
            }
        }
        log.debug(sb.toString());
        return list;
    }

    public List<MatchResult> doBatchMath(List<String> list) {
        return logScope(list.stream().map(this::doMatch).collect(Collectors.toList()));
    }


    public MatchResult doMatch(String sentence) {
        MatchResult matchResult = new MatchResult(preReplace(sentence));
        strictMatch(matchResult);
        computeMatch(matchResult);
        return matchResult;
    }


    public void strictMatch(MatchResult matchResult) {
        equalMatch(matchResult, matchResult.getSentence(), MatchType.STRICT);
    }

    /**
     * 对转换后的字符串进行相等匹配
     */
    public void equalMatch(MatchResult matchResult, String str, MatchType type) {
        Optional<String> stringOptional = standardMap.ST_MAP.keySet().stream().filter(k -> k.equals(str)).findAny();
        stringOptional.ifPresent(k ->
                matchResult.addEntry(new MatchEntry(standardMap.ST_MAP.get(k), k, type))
        );
    }

    public void computeMatch(MatchResult matchResult) {
        StandardType type = typeInferService.inferStandTypeByName(matchResult.getSentence());
        switch (type) {
            case NO:
                String ori = matchResult.getSentence()
                        .replaceFirst("号码$", "")
                        .replaceFirst("编号$", "")
                        .replaceFirst("编码$", "")
                        .replaceFirst("流水号$", "")
                        .replaceFirst("工号$", "")
                        .replaceFirst("ID$", "")
                        .replaceFirst("id$", "")
                        .replaceFirst("号$", "");
                equalMatch(matchResult, ori + "编号", MatchType.COMPUTE);
        }
    }


    public void vagueMatch(MatchResult matchResult) {

    }

    /**
     * 替换
     */
    public String preReplace(String str) {
        return str.replace("帐", "账")
                .replaceFirst("[0-9]$", "");
    }
}
