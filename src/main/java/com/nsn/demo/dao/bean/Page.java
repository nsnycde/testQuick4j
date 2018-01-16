package com.nsn.demo.dao.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author donghao
 * @since 1.0
 */
public class Page<T> {

    private List<T> rows = new ArrayList<T>();
    private int results;
    private int startNum;
    private int pageSize;

    public List<T> getRows() {
        return rows;
    }

    public void setRows(List<T> rows) {
        this.rows = rows;
    }

    public int getResults() {
        return results;
    }

    public void setResults(int results) {
        this.results = results;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getStartNum() {
        return startNum;
    }

    public void setStartNum(int startNum) {
        this.startNum = startNum;
    }
}
