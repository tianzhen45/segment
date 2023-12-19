import org.junit.Test;

public class NormalTest {


    @Test
    public void run1(){
        String s = "流水号XXC流水号";
        System.out.println(s.replaceFirst("流水号$",""));
        System.out.println(s.replaceAll("流水号$",""));
    }

    @Test
    public void run2(){
        String s = "流水号(（）（）流水号";
        System.out.println(s.replace("（","("));
    }

    @Test
    public void run3(){
        String s = "流(ABC)水号(ABC)";
        System.out.println(s.replaceAll("\\(.*?\\)",""));
    }

    @Test
    public void run4(){
        String s = "流(ABC)水号(A,.af+s-s，s。w+B-C)";
        System.out.println(s.replaceAll("[\\,\\.\\+\\-，。]",""));
    }
}

