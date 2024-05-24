import com.huaban.analysis.jieba.JiebaSegmenter;
import my.st.SegmentApplication;
import my.st.service.segment.SegmentService;
import my.st.service.segment.StandardSegmentService;
import my.st.util.TranslateHelper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

@SpringBootTest(classes = SegmentApplication.class)
@RunWith(SpringRunner.class)
public class SpringTest {


    @Inject
    private SegmentService segmentService;

    @Inject
    private TranslateHelper translateHelper;


    @Inject
    private StandardSegmentService standardSegmentService;




    @Test
    public void testSegService() {
        List<String> list = Arrays.asList("联网行账号", "核心客户号");
        String s = standardSegmentService.doStandardNameSeg(list);
        System.out.println(s);
    }

    @Test
    public void testInput() {
        String s = standardSegmentService.doStandardNameSeg(Arrays.asList(INPUT.split("\r\n")));
        System.out.println(s);
    }

    @Test
    public void testStMap(){
        System.out.println(standardMap.ST_MAP.get("特别委托授权对象"));
    }

    @Test
    public void testSegService2() {
        System.out.println(segmentService.doSegment("上期平均数主营产品", JiebaSegmenter.SegMode.INDEX));
    }

    private static String INPUT = "股金证编号\r\n" +
            "交收数量\r\n" +
            "到期还款金额\r\n" +
            "债券成交编号\r\n" +
            "结算金额\r\n" +
            "债券结算日期\r\n" +
            "审查报告审核意见\r\n" +
            "审查报告审核意见说明\r\n" +
            "产品细分编号\r\n" +
            "还款账户账号\r\n" +
            "应还日期\r\n" +
            "第二还款账号名\r\n" +
            "总成本\r\n" +
            "申请原因\r\n" +
            "第一还款来源审查\r\n" +
            "报告时段\r\n" +
            "柜员密码\r\n" +
            "回款方式代码\r\n" +
            "监管公司名称\r\n" +
            "重置日期\r\n" +
            "回收标志\r\n" +
            "转账金额\r\n" +
            "购置日期\r\n" +
            "抵质押人编号\r\n" +
            "购房合同编号\r\n" +
            "质押物价值\r\n" +
            "货款支付方式代码\r\n" +
            "监管企业名称\r\n" +
            "开始营业时间\r\n" +
            "交易类型代码\r\n" +
            "交易所对应的产品编号\r\n" +
            "产品段编号\r\n" +
            "审核日期\r\n" +
            "结算日期\r\n" +
            "还款途径\r\n" +
            "总资产\r\n" +
            "总负债\r\n" +
            "短期贷款\r\n" +
            "电话分机号\r\n" +
            "每天开业时间\r\n" +
            "中文名\r\n" +
            "受理人编号\r\n" +
            "员工名称\r\n" +
            "适用币种代码\r\n" +
            "基金登记日期\r\n" +
            "产品币种代码\r\n" +
            "费用总额\r\n" +
            "限额编号\r\n" +
            "发布人\r\n" +
            "产品到期日期\r\n" +
            "卖方名称\r\n" +
            "应收账款总笔数\r\n" +
            "已入库数量\r\n" +
            "接收方名称\r\n" +
            "流转日期\r\n" +
            "对账日\r\n" +
            "换出仓单价值\r\n" +
            "换入仓单价值\r\n" +
            "个人电话分机号\r\n" +
            "组织电话分机号\r\n" +
            "总价值\r\n" +
            "风险原因\r\n" +
            "使用机构编号\r\n" +
            "引入财报日期\r\n" +
            "出售人名称\r\n" +
            "受托机构编号\r\n" +
            "花费总价格\r\n" +
            "报告期\r\n" +
            "换证原因\r\n" +
            "待摊费用期末值\r\n" +
            "递延收益期初值\r\n" +
            "递延收益期末值\r\n" +
            "递延税款贷项期初值\r\n" +
            "递延税款贷项期末值\r\n" +
            "递延税款借项期初值\r\n" +
            "递延税款借项期末值\r\n" +
            "递延税项期初值\r\n" +
            "递延税项期末值\r\n" +
            "递延所得税负债期初值\r\n" +
            "递延所得税负债期末值\r\n" +
            "递延所得税资产期初值\r\n" +
            "递延所得税资产期末值\r\n" +
            "短期借款期初值\r\n" +
            "短期借款期末值\r\n" +
            "短期投资期初值\r\n" +
            "短期投资期末值\r\n" +
            "非流动负债期初值\r\n" +
            "非流动负债期末值\r\n" +
            "非流动负债期初值合计\r\n" +
            "非流动负债期末值合计\r\n" +
            "非流动资产期初值\r\n" +
            "非流动资产期末值\r\n" +
            "非流动资产期初值合计\r\n" +
            "非流动资产期末值合计\r\n" +
            "负债期初值合计\r\n" +
            "负债期末值合计\r\n" +
            "工程物资期初值\r\n" +
            "工程物资期末值\r\n" +
            "固定资产期初值\r\n" +
            "固定资产期末值\r\n" +
            "固定资产期初值合计\r\n" +
            "固定资产期末值合计\r\n" +
            "固定资产净额期初值\r\n" +
            "固定资产清理期初值\r\n" +
            "固定资产清理期末值\r\n" +
            "固定资产原价期初值\r\n" +
            "固定资产原价期末值\r\n" +
            "持有待售的负债期初值\r\n" +
            "持有待售的负债期末值\r\n" +
            "持有待售的资产期初值\r\n" +
            "持有待售的资产期末值\r\n" +
            "货币资金期初值\r\n" +
            "固定资产减值准备期初值\r\n" +
            "固定资产减值准备期末值\r\n" +
            "减库存股期初值\r\n" +
            "减库存股期末值\r\n" +
            "减累计折旧期初值\r\n" +
            "减累计折旧期末值\r\n" +
            "减已归还投资期初值\r\n" +
            "减已归还投资期末值\r\n" +
            "开发支出期初值\r\n" +
            "开发支出期末值\r\n" +
            "可供出售金融资产期初值\r\n" +
            "可供出售金融资产期末值\r\n" +
            "流动负债期初值\r\n" +
            "流动负债期末值\r\n" +
            "流动负债期初值合计\r\n" +
            "流动负债期末值合计\r\n" +
            "流动资产期初值\r\n" +
            "流动资产期初值合计\r\n" +
            "流动资产期末值合计\r\n"
   ;
}
