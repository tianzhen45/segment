package my.st.service.sql;

import my.st.domain.entity.Column;
import my.st.domain.entity.ColumnEntry;
import my.st.domain.entity.Table;
import my.st.domain.repo.CommonCol;
import my.st.domain.repo.CommonColRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

@Service
public class SqlGenerateService {

    public static final boolean IS_VALID_SQL = true;

    public static final boolean IS_CLEAR_SQL = false;

    public static final Set<String> DATE_TYPE_STRING_SET = new HashSet<>();
    static {
        DATE_TYPE_STRING_SET.add("DATE");
        DATE_TYPE_STRING_SET.add("DATETIME");
        DATE_TYPE_STRING_SET.add("TIMESTAMP");
        DATE_TYPE_STRING_SET.add("TEXT");
    }

    @Resource
    private CommonColRepository commonColRepository;


    public  String genDropScript(Table table) {
        return "DROP TABLE IF EXISTS "+table.getTableName()+";\r\n\r\n";
    }

    public  String genSqlScript(Table table){
        StringBuilder sql = new StringBuilder();
        sql.append("CREATE TABLE ").append(table.getTableName()).append(" (\r\n");

        for(Column column : table.getColList()){
            sql.append("\t").append(column.getColumnName()).append(" ").append(column.getDataType());

            if(DATE_TYPE_STRING_SET.contains(column.getDataType().toUpperCase()) || "/".equals(column.getLength())){
                sql.append(" ");
            }else{
                sql.append("(").append(column.getLength()).append(") ");
            }

            sql.append("COMMENT '").append(column.getComment()).append("',\r\n");
        }

        sql.delete(sql.length()-3,sql.length()-1);
        if(table.getComment().equals("/")){
            sql.append(");\r\n\r\n");
        }else{
            sql.append(") COMMENT '").append(table.getComment()).append("';\r\n\r\n");
        }
        return sql.toString();
    }

    public String genSqlScript(List<ColumnEntry> list,String system_code) {
        List<CommonCol> commonCols = commonColRepository.findBySystemCode(system_code);
        StringBuilder sb = new StringBuilder();
        for (Table table : toTables(list)) {
            for (CommonCol commonCol : commonCols) {
                Column column = new Column();
                column.setColumnName(commonCol.getColName());
                column.setComment(commonCol.getColCnName());
                column.setLength(commonCol.getLen());
                column.setDataType(commonCol.getType());
                table.addColumn(column);
            }
            sb.append(genSqlScript(table));
        }
        return sb.toString();
    }

    public Collection<Table> toTables(List<ColumnEntry> list){
        Map<String, Table> map = new HashMap<>();
        for (ColumnEntry columnEntry : list) {
            String tableName = columnEntry.getTbName();
            String tableComment = columnEntry.getTbCnName();
            String colName = columnEntry.getColName();
            String colComment = columnEntry.getColCnName();
            String colDt = columnEntry.getType();
            String colLength = columnEntry.getLength();
            Table table = map.getOrDefault(tableName,new Table());
            table.setTableName(tableName);
            table.setComment(tableComment);
            Column column = new Column();
            column.setColumnName(colName);
            column.setComment(colComment);
            column.setDataType(colDt);
            column.setLength(colLength);
            table.getColList().add(column);
            map.put(tableName,table);
        }
        return map.values();
    }
}
