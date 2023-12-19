import org.junit.Test;

public class NormalTest {


    @Test
    public void run1(){
        String s = "流水号XXC流水号";
        System.out.println(s.replaceFirst("流水号$",""));
        System.out.println(s.replaceAll("流水号$",""));
    }
}
