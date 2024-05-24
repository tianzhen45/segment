package my.st.web.entity;

public class PageRequestParam<T extends RequestParam> {

    private T param;

    private RequestPage page;

    public PageRequestParam(T param, RequestPage page) {
        this.param = param;
        this.page = page;
    }

    public T getParam() {
        return param;
    }

    public void setParam(T param) {
        this.param = param;
    }

    public RequestPage getPage() {
        return page;
    }

    public void setPage(RequestPage page) {
        this.page = page;
    }
}
