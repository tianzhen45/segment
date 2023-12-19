package my.st.service.analysis;

import my.st.domain.type.StandardType;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * 数据标准类型推断
 */
@Service
public class StandardTypeInferService {

    //todo 数据库读取后统计
    public final static Map<String, Integer> MONEY_TYPE_SUFFIX = new HashMap<>() {{
        put("PR",2);
        put("上限",1);
        put("交款",4);
        put("付款",24);
        put("价值",288);
        put("价格",9);
        put("价物",7);
        put("估价",5);
        put("估算",2);
        put("余额",413);
        put("保费",1);
        put("信息",1);
        put("信额",4);
        put("借款",21);
        put("债券",10);
        put("债务",2);
        put("值税",1);
        put("偿费",2);
        put("储备",8);
        put("元)",12);
        put("元）",22);
        put("先股",4);
        put("公积",24);
        put("净值",6);
        put("净额",38);
        put("准备",3);
        put("分）",6);
        put("利息",8);
        put("利润",91);
        put("利费",5);
        put("加额",7);
        put("动额",4);
        put("单价",30);
        put("占比",3);
        put("原价",9);
        put("发费",2);
        put("合计",84);
        put("售价",3);
        put("商誉",4);
        put("地产",4);
        put("地价",1);
        put("场价",60);
        put("均)",6);
        put("均价",5);
        put("均数",5);
        put("均额",4);
        put("均）",10);
        put("基金",12);
        put("增加",2);
        put("备费",1);
        put("复利",2);
        put("存款",4);
        put("存股",4);
        put("存货",7);
        put("定价",121);
        put("实收",1);
        put("小计",28);
        put("工程",8);
        put("工资",4);
        put("差额",8);
        put("市价",6);
        put("开支",6);
        put("得税",6);
        put("总值",1);
        put("总计",38);
        put("总额",85);
        put("情况",3);
        put("成本",36);
        put("投入",2);
        put("投资",35);
        put("折旧",6);
        put("护费",1);
        put("押率",8);
        put("损失",25);
        put("摊销",6);
        put("支出",41);
        put("收)",4);
        put("收入",137);
        put("收款",18);
        put("收益",66);
        put("数量",3);
        put("数额",2);
        put("日均",4);
        put("期限",7);
        put("本金",7);
        put("本）",6);
        put("权益",7);
        put("权额",4);
        put("欠息",3);
        put("款项",8);
        put("比例",2);
        put("比率",4);
        put("气费",2);
        put("津贴",1);
        put("流量",1);
        put("测算",1);
        put("润率",7);
        put("清理",4);
        put("现金",80);
        put("用率",1);
        put("益金",6);
        put("础值",1);
        put("票据",14);
        put("租金",13);
        put("积金",1);
        put("程费",6);
        put("税收",1);
        put("税费",10);
        put("税金",7);
        put("等）",1);
        put("算量",4);
        put("算）",13);
        put("累计",3);
        put("级）",2);
        put("结)",4);
        put("续债",8);
        put("罚息",4);
        put("置费",2);
        put("股利",20);
        put("薪酬",4);
        put("融资",7);
        put("行额",2);
        put("补贴",1);
        put("见费",1);
        put("警线",6);
        put("调整",1);
        put("负债",61);
        put("账款",98);
        put("购价",3);
        put("贴款",4);
        put("贷款",6);
        put("费用",52);
        put("资产",96);
        put("资本",15);
        put("资金",16);
        put("资额",2);
        put("转入",4);
        put("运输",1);
        put("返回",3);
        put("还款",6);
        put("退税",1);
        put("部分",2);
        put("配置",2);
        put("金额",701);
        put("金）",1);
        put("附加",8);
        put("限额",2);
        put("险费",1);
        put("险金",2);
        put("额度",212);
        put("高额",2);
    }};

    public final static Map<String, Integer> CODE_TYPE_SUFFIX = new HashMap<>() {{
        put("主体", 4);
        put("代码", 3720);
        put("别)", 2);
        put("单位", 3);
        put("原因", 3);
        put("周期", 5);
        put("子类", 4);
        put("小类", 23);
        put("币种", 6);
        put("形式", 5);
        put("性别", 5);
        put("情况", 1);
        put("手段", 2);
        put("方式", 86);
        put("来源", 12);
        put("标志", 53);
        put("标识", 3);
        put("状况", 8);
        put("状态", 35);
        put("用途", 6);
        put("种类", 5);
        put("等级", 41);
        put("类别", 8);
        put("类型", 147);
        put("范围", 2);
        put("行业", 2);
        put("角色", 1);
        put("选项", 3);
    }};

    public final static Map<String, Integer> FLAG_TYPE_SUFFIX = new HashMap<>() {{
        put("标志", 626);
    }};

    public final static Map<String, Integer> FLAG_TYPE_CONTAIN = new HashMap<>() {{
        put("是否", 3000);
    }};

    public final static Map<String, Integer> NO_TYPE_SUFFIX = new HashMap<>() {{
        put("ID", 2);
        put("万元", 4);
        put("产品", 5);
        put("代码", 19);
        put("位号", 51);
        put("办人", 21);
        put("卡号", 3);
        put("号码", 99);
        put("名称", 30);
        put("品号", 2);
        put("工号", 24);
        put("帐号", 2);
        put("形式", 3);
        put("户号", 129);
        put("据号", 28);
        put("机构", 39);
        put("查人", 1);
        put("标志", 1);
        put("案号", 2);
        put("次号", 5);
        put("水号", 117);
        put("状态", 3);
        put("理号", 5);
        put("目号", 1);
        put("码2", 2);
        put("种类", 17);
        put("类型", 8);
        put("经理", 1);
        put("结果", 1);
        put("编号", 2507);
        put("编码", 115);
        put("行号", 8);
        put("订员", 5);
        put("记人", 6);
        put("证号", 4);
        put("详情", 3);
        put("账号", 227);
        put("账户", 2);
    }};

    public final static Map<String, Integer> NUM_TYPE_SUFFIX = new HashMap<>() {{
        put("PR", 12);
        put("下限", 44);
        put("乘数", 1);
        put("人口", 22);
        put("人数", 22);
        put("低分", 1);
        put("余量", 3);
        put("倍数", 3);
        put("债率", 12);
        put("值率", 1);
        put("入比", 1);
        put("入量", 2);
        put("减少", 2);
        put("利率", 112);
        put("化)", 7);
        put("化率", 1);
        put("占比", 21);
        put("发货", 27);
        put("口数", 19);
        put("口量", 2);
        put("合率", 1);
        put("合计", 1);
        put("同数", 2);
        put("增加", 2);
        put("增量", 97);
        put("增长", 6);
        put("天数", 33);
        put("年份", 2);
        put("年限", 1);
        put("序号", 24);
        put("得分", 18);
        put("总量", 17);
        put("息)", 1);
        put("息率", 27);
        put("户数", 88);
        put("押率", 33);
        put("收期", 2);
        put("收率", 1);
        put("数量", 243);
        put("月数", 1);
        put("期数", 3);
        put("期次", 9);
        put("期限", 162);
        put("条件", 15);
        put("条数", 8);
        put("构数", 12);
        put("概率", 1);
        put("次)", 1);
        put("次数", 4);
        put("比例", 275);
        put("比率", 37);
        put("流量", 1);
        put("润率", 13);
        put("用)", 2);
        put("用率", 12);
        put("益率", 2);
        put("票据", 8);
        put("积率", 1);
        put("笔数", 106);
        put("系数", 2);
        put("编号", 2);
        put("股量", 1);
        put("行率", 2);
        put("贷比", 2);
        put("费率", 86);
        put("赎货", 27);
        put("转率", 3);
        put("选项", 18);
        put("金率", 1);
        put("长率", 7);
        put("间数", 2);
        put("限期", 28);
        put("面积", 75);
        put("顺序", 2);
        put("额度", 1);
        put("高分", 1);
    }};

    public final static Map<String, Integer> DATE_TYPE_SUFFIX = new HashMap<>() {{
        put("始年", 1);
        put("始日", 19);
        put("年份", 12);
        put("年度", 1);
        put("年月", 2);
        put("息日", 1);
        put("效期", 19);
        put("日期", 1194);
        put("时点", 3);
        put("时间", 281);
        put("期日", 31);
        put("款日", 3);
        put("止日", 15);
    }};

    public final static Map<String, Integer> TEXT_TYPE_SUFFIX = new HashMap<>(){{
        put("厂家",159);
        put("原因",180);
        put("名称",3462);
        put("地址",222);
        put("备注",141);
        put("姓名",63);
        put("情况",69);
        put("意见",187);
        put("户数",55);
        put("户行",172);
        put("描述",52);
        put("机构",53);
        put("电话",120);
        put("结果",78);
        put("规格",101);
        put("说明",61);
    }};


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
     * 根据数据类型推断标准类型
     */
    public StandardType inferStandTypeByDT(String sentence) {
        return StandardType.TEXT;
    }
}
