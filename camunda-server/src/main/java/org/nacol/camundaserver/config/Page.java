package org.nacol.camundaserver.config;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * Author: zhshuo
 * Time : 2019/1/14 13:49 星期一
 * Desc:
 **/
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
}
