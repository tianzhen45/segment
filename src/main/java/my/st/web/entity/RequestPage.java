package my.st.web.entity;

import org.springframework.data.domain.AbstractPageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

public class RequestPage extends AbstractPageRequest {

    public RequestPage(int page, int size) {
        super(page, size);
    }

    @Override
    public Sort getSort() {
        return Sort.unsorted();
    }

    @Override
    public Pageable next() {
        return new RequestPage(getPageNumber() + 1, getPageSize());
    }

    @Override
    public Pageable previous() {
        return new RequestPage(getPageNumber() - 1, getPageSize());
    }

    @Override
    public Pageable first() {
        return new RequestPage(0, getPageSize());
    }
}
