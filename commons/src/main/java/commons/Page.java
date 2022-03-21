package commons;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class Page<T, R> implements Serializable {

    private static final long serialVersionUID = -8581690994715874239L;

    private T filter;

    private int pageNumber;

    private int pageSize;

    private List<R> list;

    private T getFilter(){
        return null;
    }

    private long count;

    public T filter(){
        return filter;
    }

    public int getPageNumber() {
        return this.pageNumber - 1;
    }
}
