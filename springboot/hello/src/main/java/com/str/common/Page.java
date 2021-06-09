package com.str.common;

public class Page {
    public static final int DEFAULT_PAGE_SIZE = 10;

    private int pageIndex;
    private int pageSize;
    private int pageCount;
    private int totalCount;

    public Page(int pageIndex, int pageSize) {
        if (pageIndex < 1) {
            pageIndex = 1;
        }
        if (pageSize < 1) {
            pageSize = 1;
        }
        this.pageIndex = pageIndex;
        this.pageSize = pageSize;
    }

    public Page(int pageIndex) {
        this(pageIndex, DEFAULT_PAGE_SIZE);
    }

    public int getPageIndex() {
        return pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getFirstResult() {
        return (pageIndex - 1) * pageSize;
    }

    public boolean hasPrevious() {
        return pageIndex > 1;
    }

    public boolean hasNext() {
        return pageIndex < pageCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
        pageCount = totalCount / pageSize + (totalCount % pageSize == 0 ? 0 : 1);
        if (totalCount == 0) {
            if (pageIndex != 1) {
                throw new IndexOutOfBoundsException("Page index out of range.");
            }
        } else {    // not isEmpty
            if (pageIndex > pageCount) {
                throw new IndexOutOfBoundsException("Page index out of range.");
            }
        }

    }

    public boolean isEmpty() {
        return totalCount == 0;
    }
}
