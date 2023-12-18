import com.huaban.analysis.jieba.SegToken;


import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import my.st.util.SegmentService;
import my.st.util.TranslateHelper;

import my.st.util.SegmentConstant;


public class InOutMain {
    public static void main(String[] args) throws Exception{
        SegmentService segmentService = SegmentService.getInstance();
        TranslateHelper translateHelper = TranslateHelper.getInstance();
        BufferedReader bfr = new BufferedReader(new FileReader(SegmentConstant.INOUT_PATH));
        ArrayList<String> readList = bfr.lines().collect(Collectors.toCollection(ArrayList::new));
        bfr.close();

        BufferedWriter bfw = new BufferedWriter(new FileWriter(SegmentConstant.INOUT_PATH));
        readList.forEach(l -> {
            List<SegToken> segTokens = segmentService.doSegment(TranslateHelper.replaceSign(l));
            StringBuilder sb = new StringBuilder();
            StringBuilder sb2 = new StringBuilder();
            for (SegToken segToken : segTokens) {
                sb.append(segToken.word).append("_");
                sb2.append(translateHelper.translate(segToken.word)).append("_");
            }
            try {
                bfw.write( sb.deleteCharAt(sb.length()-1).append("\t").append(sb2.deleteCharAt(sb2.length()-1)).append("\r\n").toString());
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        bfw.close();
    }
}
