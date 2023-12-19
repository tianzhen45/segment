package my.st.service.analysis;

import my.st.domain.type.StandardType;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import static my.st.util.SegmentConstant.*;
/**
 * 数据标准类型推断
 */
@Service
public class StandardTypeInferService {

    /**
     * 根据字段中文大致推断映射标准类型
     * 根据已对标字段中文映射的标准类型,统计最后两个字和数量（作为权重）
     */
    public StandardType inferStandTypeByName(String sentence) {
        Map<Integer, StandardType> result = new HashMap<>();
        MONEY_TYPE_SUFFIX.keySet().forEach(k -> {
            if (sentence.endsWith(k)) result.put(MONEY_TYPE_SUFFIX.get(k), StandardType.MONEY);
        });
        CODE_TYPE_SUFFIX.keySet().forEach(k -> {
            if (sentence.endsWith(k)) result.put(CODE_TYPE_SUFFIX.get(k), StandardType.CODE);
        });
        FLAG_TYPE_SUFFIX.keySet().forEach(k -> {
            if (sentence.endsWith(k)) result.put(FLAG_TYPE_SUFFIX.get(k), StandardType.FLAG);
        });
        FLAG_TYPE_CONTAIN.keySet().forEach(k -> {
            if (sentence.contains(k)) result.put(CODE_TYPE_SUFFIX.get(k), StandardType.FLAG);
        });
        NO_TYPE_SUFFIX.keySet().forEach(k -> {
            if (sentence.endsWith(k)) result.put(NO_TYPE_SUFFIX.get(k), StandardType.NO);
        });
        NUM_TYPE_SUFFIX.keySet().forEach(k -> {
            if (sentence.endsWith(k)) result.put(NUM_TYPE_SUFFIX.get(k), StandardType.NUM);
        });
        DATE_TYPE_SUFFIX.keySet().forEach(k -> {
            if (sentence.endsWith(k)) result.put(DATE_TYPE_SUFFIX.get(k), StandardType.DATE);
        });
        TEXT_TYPE_SUFFIX.keySet().forEach(k -> {
            if (sentence.endsWith(k)) result.put(TEXT_TYPE_SUFFIX.get(k), StandardType.TEXT);
        });
        return result.getOrDefault(result.keySet().stream().filter(Objects::nonNull).max(Integer::compareTo).orElse(null),StandardType.TEXT);
    }

    /**
     * todo 根据数据类型推断标准类型
     */
    public StandardType inferStandTypeByDT(String sentence) {
        return StandardType.TEXT;
    }
}
