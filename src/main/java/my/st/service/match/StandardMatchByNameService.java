package my.st.service.match;


import com.huaban.analysis.jieba.JiebaSegmenter;
import my.st.domain.match.MatchEntry;
import my.st.domain.match.MatchResult;
import my.st.domain.match.ReplaceRules;
import my.st.domain.repo.StandRepository;
import my.st.domain.type.MatchType;
import my.st.service.segment.SegmentService;
import my.st.util.StandardMap;
import my.st.util.TranslateHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 根据字段中文对标匹配
 */
@Service
public class StandardMatchByNameService {

    private static final Logger log = LoggerFactory.getLogger(StandardMatchByNameService.class);

    //模糊匹配数量
    private static final int VAGUE_MATCH_COUNT = 5;

    //是否启用模糊匹配
    private static final boolean VAGUE_ACTIVE = false;

    @Inject
    private StandardMap standardMap;

    @Inject
    private StandRepository standRepository;

    @Inject
    private SegmentService segmentService;

    @Inject
    private ReplaceRules replaceRules;

    /**
     * 批量匹配，返回格式化csv字符串
     */
    public String doBatchMatchStringResult(List<String> list) {
        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            MatchResult matchResult = doMatch(s);
            if (matchResult.getList().isEmpty()) {
                sb.append(matchResult.getSentence()).append("\r\n");
            }
            for (MatchEntry entry : matchResult.getList()) {
                sb.append(matchResult.getSentence()).append("\t")
                        .append(entry.getStandNo()).append("\t")
                        .append(entry.getStandName()).append("\t")
                        .append(entry.getType()).append("\r\n");
            }
        }
        return sb.toString();
    }

    /**
     * 批量匹配，返回MatchResult对象
     */
    public List<MatchResult> doBatchMatch(List<String> list) {
        return list.stream().map(this::doMatch).collect(Collectors.toList());
    }

    /**
     * 核心匹配流程
     */
    public MatchResult doMatch(String sentence) {
        MatchResult matchResult = new MatchResult(sentence);

        strictMatch(matchResult);

        if (matchResult.getList().isEmpty()) {

            computeMatch(matchResult);

            if (matchResult.getList().isEmpty() && VAGUE_ACTIVE) {
                vagueMatch(matchResult);
            }
        }
        return matchResult;
    }

    /*
     *  与标准完全相等匹配
     */
    public void strictMatch(MatchResult matchResult) {
        equalMatch(matchResult, matchResult.getSentence(), MatchType.STRICT);
    }

    /**
     * 转换匹配
     */
    public void computeMatch(MatchResult matchResult) {
        String sentence = preReplace(matchResult.getSentence());
        baseMatch(matchResult, sentence);
        synMatch(matchResult, sentence);
    }


    /**
     * 对转换后的字符串进行相等匹配
     */
    public void equalMatch(MatchResult matchResult, String str, MatchType type) {
        if (standardMap.ST_MAP.containsKey(str)) {
            matchResult.addEntry(new MatchEntry(standardMap.ST_MAP.get(str), str, type));
        }
    }

    /**
     * 对转换后的字符串进行包含匹配
     */
    public void containsMatch(MatchResult matchResult, String str,int count) {
        standardMap.ST_MAP.keySet().forEach(
                k -> {
                    if (k.contains(str) && matchResult.getList().size() < count) {
                        matchResult.addEntry(new MatchEntry(standardMap.ST_MAP.get(k), k, MatchType.VAGUE));
                    }
                }
        );
    }


    /**
     * 对转换后的字符串进行尾部匹配
     */
    public void endsWithMatch(MatchResult matchResult, String str, MatchType type) {
        standardMap.ST_MAP.keySet().stream().filter(k -> k.endsWith(str)).findAny().ifPresent(k ->
                matchResult.addEntry(new MatchEntry(standardMap.ST_MAP.get(k), k, type))
        );
    }


    /**
     * 同义词转换匹配
     */
    private void synMatch(MatchResult matchResult, String sentence) {
        for (ReplaceRules.Rule r : replaceRules.getSynonymsRules()) {
            if (sentence.contains(r.getRegex())) {
                baseMatch(matchResult, sentence.replace(r.getRegex(), r.getReplaceWord()));
            }
        }
    }


    /*
     * 相等匹配一次，尾部替换规则匹配
     */
    private void baseMatch(MatchResult matchResult, String sentence) {

        equalMatch(matchResult, sentence, MatchType.COMPUTE);

        for (ReplaceRules.Rule r : replaceRules.getEndReplaceRules()) {
            if (sentence.endsWith(r.getRegex().replace("$", ""))) {
                equalMatch(matchResult, sentence.replaceFirst(r.getRegex(), r.getReplaceWord()), MatchType.COMPUTE);
            }
        }

        // 加[编号、名称、代码]匹配
        equalMatch(matchResult, sentence + "编号", MatchType.COMPUTE);
        equalMatch(matchResult, sentence + "名称", MatchType.COMPUTE);
        equalMatch(matchResult, sentence + "代码", MatchType.COMPUTE);

        // 标志类
        if (sentence.contains("是否")) {
            equalMatch(matchResult, sentence.replace("是否", "") + "标志", MatchType.COMPUTE);
        }
        if (sentence.contains("有无")) {
            equalMatch(matchResult, sentence.replace("有无", "") + "标志", MatchType.COMPUTE);
        }
    }


    /**
     * 模糊匹配，分词后核心词匹配
     */
    public void vagueMatch(MatchResult matchResult) {
        segmentService.doSegment(matchResult.getSentence(), JiebaSegmenter.SegMode.INDEX)
                .stream().map(c -> new Object[]{c.word, c.toString().length()})
                .max(Comparator.comparingInt(o -> (int) o[1]))
                .ifPresent(s -> containsMatch(matchResult, s[0].toString(), VAGUE_MATCH_COUNT));
    }

    /**
     * 转换匹配前替换
     * 替换特殊字符、尾部数字和错误词语
     */
    public String preReplace(String str) {
        return str.replace("帐", "账")
                .replace("）", ")")
                .replace("（", "(")
                .replaceAll("\\(.*?\\)", "")
                .replaceFirst("[0-9]+$", "")
                .replaceFirst(TranslateHelper.NON_WORD_REX, "")
                .toUpperCase();
    }
}
