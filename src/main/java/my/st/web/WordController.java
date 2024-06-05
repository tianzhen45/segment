package my.st.web;

import my.st.domain.repo.Word;
import my.st.domain.repo.WordRepository;
import my.st.service.segment.SegmentService;
import my.st.util.TranslateHelper;
import my.st.web.entity.ListAndObjectParam;
import my.st.web.entity.PageRequestParam;
import my.st.web.entity.WordParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/word")
public class WordController {

    @Resource
    private WordRepository wordRepository;

    @Resource
    private SegmentService segmentService;

    @Resource
    private TranslateHelper translateHelper;

    private static final Logger log = LoggerFactory.getLogger(WordController.class);

    @PostMapping("/listByPage")
    public Page<Word> listByPage(@RequestBody PageRequestParam<WordParam> requestParam) {
        WordParam param = requestParam.getParam();
        String cnName = param.getCnName();
        String enName = param.getEnName();
        if (cnName.isEmpty() && enName.isEmpty()) {
           return wordRepository.findAll(requestParam.getPage());
        }else if (!enName.isEmpty() && cnName.isEmpty()){
            return wordRepository.findWordsByEnNameIsLike("%"+enName+"%",requestParam.getPage());
        }else if ( enName.isEmpty()){
            return wordRepository.findWordsByCnNameIsLike("%"+cnName+"%",requestParam.getPage());
        }else {
            return wordRepository.findWordsByCnNameIsLikeAndEnNameIsLike("%"+cnName+"%","%"+enName+"%",requestParam.getPage());
        }
    }

    @PostMapping("/import")
    public String importWords(@RequestBody ListAndObjectParam<Word,Integer> data){
        try{
            if(data.getObjectParam() == 2){
                wordRepository.deleteAll();
            }
            wordRepository.saveAll(data.getList());
            // 重新加载词典
            segmentService.reloadDBDict();
            // 重新加载翻译
            translateHelper.init();
        }catch (Exception e){
            log.error("导入词根失败",e);
            return "导入失败";
        }
        return "成功导入！";
    }

    @GetMapping("/queryWord")
    public List<Word> queryWord(String cnName){
        return wordRepository.findByCnName(cnName);
    }

}
