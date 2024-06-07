package my.st.web.entity;

import java.util.List;

public class ListAndObjectParam<T,S>{

    private List<T> list;

    private S objectParam;

    public ListAndObjectParam(List<T> list, S objectParam) {
        this.list = list;
        this.objectParam = objectParam;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public S getObjectParam() {
        return objectParam;
    }

    public void setObjectParam(S objectParam) {
        this.objectParam = objectParam;
    }
}
