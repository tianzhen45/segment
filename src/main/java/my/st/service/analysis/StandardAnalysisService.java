package my.st.service.analysis;

import my.st.domain.type.StandardType;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class StandardAnalysisService {


    /**
     * 根据字段中文大致推断映射标准类型
     * //todo 标准类型推断规则待完善
     */
    public StandardType inferStandType(String sentence) {
        if (Stream.of("额", "款", "价格", "利息").anyMatch(sentence::endsWith)) {
            return StandardType.MONEY;
        } else if (Stream.of("代码", "类型", "方式", "状态").anyMatch(sentence::endsWith)) {
            return StandardType.CODE;
        } else if (Stream.of("号", "编码", "号码", "ID", "id", "主键","标识").anyMatch(sentence::endsWith)) {
            return StandardType.NO;
        } else if (sentence.endsWith("标志") || sentence.contains("是否")) {
            return StandardType.FLAG;
        } else if (Stream.of("日", "日期", "时间", "年月", "年份", "月份", "期限", "有效期").anyMatch(sentence::endsWith)) {
            return StandardType.DATE;
        } else if (Stream.of("率", "数量", "大小", "数", "值", "比例").anyMatch(sentence::endsWith)) {
            return StandardType.NUM;
        } else {
            return StandardType.OTHER;
        }
    }
}
