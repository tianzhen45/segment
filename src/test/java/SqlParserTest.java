import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import org.junit.Test;

public class SqlParserTest {

    @Test
    public void test() throws Exception{
        String sql = "Create Table ploa_prerpy_tsk_iou_inf_temp_tbl   (\n" +
                "`ID` varchar(64) NOT NULL COMMENT '主键',\n" +
                "  `PRERP_TSK_SRLNO` varchar(60) DEFAULT NULL COMMENT '提前还款任务流水号',\n" +
                "  `PRERP_TSK_CTR_INF_SRLNO` varchar(60) DEFAULT NULL COMMENT '提前还款任务合同信息流水号',\n" +
                "  `PRERP_TSK_LNRECPT_INF_SRLNO` varchar(60) DEFAULT NULL COMMENT '提前还款任务借据信息流水号',\n" +
                "  `PMNTNO` varchar(60) DEFAULT NULL COMMENT '借据编号',\n" +
                "  `LN_RPYMT_MNR_CD` varchar(4) DEFAULT NULL COMMENT '贷款还款方式代码',\n" +
                "  `INADV_RPYMT_TPCD` varchar(4) DEFAULT NULL COMMENT '提前还款类型代码') comment '提前还款任务借据信息临时表';\n";

        CreateTable createTable = (CreateTable)CCJSqlParserUtil.parse(sql);
        System.out.println(createTable.getTable());
        System.out.println(createTable.getColumns());
        System.out.println(createTable.getColumnDefinitions());

    }
}
