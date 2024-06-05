package my.st.service.sql;

import my.st.domain.entity.ColumnEntry;
import net.sf.jsqlparser.parser.CCJSqlParserUtil;
import net.sf.jsqlparser.statement.create.table.ColumnDefinition;
import net.sf.jsqlparser.statement.create.table.CreateTable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SqlParseService {


    public static final String NAME_PARCEL_REG = "`|'|\\\"";

    public static final String LEN_PARCEL_REG = "\\[|\\]";

    public List<ColumnEntry> parseCreateTableScript(String sqlText) throws Exception {
        List<ColumnEntry> list = new ArrayList<>();
        for (String sql : sqlText.split(";")) {
            CreateTable createTable = (CreateTable) CCJSqlParserUtil.parse(sql);
            String tbName = createTable.getTable().getName();
            String tbCnName = fetchTbComment(createTable);
            for (ColumnDefinition columnDefinition : createTable.getColumnDefinitions()) {
                String columnName = columnDefinition.getColumnName();
                String colDataType = columnDefinition.getColDataType().getDataType();
                List<String> argumentsStringList = columnDefinition.getColDataType().getArgumentsStringList();
                String length = argumentsStringList == null ? "" : argumentsStringList.toString().replaceAll(LEN_PARCEL_REG,"");
                String comment = fetchColComment(columnDefinition);
                ColumnEntry columnEntry = new ColumnEntry();
                columnEntry.setTbName(tbName.replaceAll(NAME_PARCEL_REG,""));
                columnEntry.setTbCnName(tbCnName);
                columnEntry.setColName(columnName.replaceAll(NAME_PARCEL_REG,""));
                columnEntry.setColCnName(comment);
                columnEntry.setType(colDataType);
                columnEntry.setLength(length);
                list.add(columnEntry);
            }
        }
        return list;
    }

    public String fetchColComment(ColumnDefinition cd) {
        boolean flag = false;
        for (String columnSpec : cd.getColumnSpecs()) {
            if (flag) return columnSpec.replaceAll(NAME_PARCEL_REG,"");
            if ("comment".equalsIgnoreCase(columnSpec)) {
                flag = true;
            }
        }
        return "";
    }

    public String fetchTbComment(CreateTable createTable) {
        boolean flag = false;
        for (String tableOptionsString : createTable.getTableOptionsStrings()) {
            if (flag && !"=".equals(tableOptionsString)) return tableOptionsString.replaceAll(NAME_PARCEL_REG,"");
            if ("comment".equalsIgnoreCase(tableOptionsString)) {
                flag = true;
            }
        }
        return "";
    }

}
