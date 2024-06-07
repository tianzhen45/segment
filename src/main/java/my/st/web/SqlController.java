package my.st.web;

import my.st.domain.entity.ColumnEntry;
import my.st.domain.repo.CommonCol;
import my.st.domain.repo.CommonColRepository;
import my.st.service.sql.SqlGenerateService;
import my.st.service.sql.SqlParseService;
import my.st.web.entity.ListAndObjectParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/sql")
public class SqlController {

    @Resource
    private SqlGenerateService sqlGenerateService;

    @Resource
    private SqlParseService sqlParseService;

    @Resource
    private CommonColRepository commonColRepository;

    private static final Logger log = LoggerFactory.getLogger(SqlController.class);

    @PostMapping("/DDL")
    public String genDDL(@RequestBody ListAndObjectParam<ColumnEntry,String> data){
        return sqlGenerateService.genSqlScript(data.getList(), data.getObjectParam());
    }

    @PostMapping("/commonCol")
    public List<CommonCol> commonCols(String systemCode){
        return commonColRepository.findBySystemCode(systemCode);
    }

    @PostMapping("/parse")
    public ListAndObjectParam<ColumnEntry,String> parse(String sql){
        try{
            return new ListAndObjectParam<>(sqlParseService.parseCreateTableScript(sql),"SUCCESS");
        }catch (Exception e){
            log.error("sql解析失败",e);
            return new ListAndObjectParam<>(null,"ERROR");
        }
    }
}
