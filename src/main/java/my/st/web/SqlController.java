package my.st.web;

import my.st.domain.entity.ColumnEntry;
import my.st.domain.repo.CommonCol;
import my.st.domain.repo.CommonColRepository;
import my.st.service.sql.SqlGenerateService;
import my.st.web.entity.ListAndObjectParam;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/sql")
public class SqlController {

    @Resource
    private SqlGenerateService sqlGenerateService;

    @Resource
    private CommonColRepository commonColRepository;

    @PostMapping("/DDL")
    public String importWords(@RequestBody ListAndObjectParam<ColumnEntry,Integer> data){
        return sqlGenerateService.genSqlScript(data.getList());
    }

    @PostMapping("/commonCol")
    public List<CommonCol> commonCols(String systemCode){
        return commonColRepository.findBySystemCode(systemCode);
    }
}
