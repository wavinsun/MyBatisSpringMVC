package cn.mutils.web.mybatis.common.page;

import java.io.Serializable;
import java.util.List;

/**
 * Created by wenhua.ywh on 2016/11/21.
 */
public class Page<T> implements Serializable {

    public static final int PAGE_SIZE_DEFAULT = 10;

    private int pageNo = 1; // 当前页
    private int pageSize = PAGE_SIZE_DEFAULT; // 每页数量
    private int totalCount; // 总记录数
    private int size; // 当前页数量
    private int startRow; // 当前页第一个元素在数据库的行号，默认为0，如果有数据则从1开始
    private int endRow; // 当前页最后一个元素在数据库的行号
    private List<T> resultList; // 返回结果集
    private int totalPageCount; // 总页数
    private int previousPage; // 上一页
    private int nextPage = 2; // 下一页
    private boolean hasPreviousPage; //是否有上一页
    private boolean hasNextPage; // 是否有下一页
    private boolean isFirstPage = true; // 是否是第一页
    private boolean isLastPage; // 是否是最后一页

    public Page() {

    }

    public Page(int pageNo, int pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getStartRow() {
        return startRow;
    }

    public void setStartRow(int startRow) {
        this.startRow = startRow;
    }

    public int getEndRow() {
        return endRow;
    }

    public void setEndRow(int endRow) {
        this.endRow = endRow;
    }

    public List<T> getResultList() {
        return resultList;
    }

    public void setResultList(List<T> resultList) {
        this.resultList = resultList;
    }

    public int getTotalPageCount() {
        return totalPageCount;
    }

    public void setTotalPageCount(int totalPageCount) {
        this.totalPageCount = totalPageCount;
    }

    public int getPreviousPage() {
        return previousPage;
    }

    public void setPreviousPage(int previousPage) {
        this.previousPage = previousPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public boolean isHasPreviousPage() {
        return hasPreviousPage;
    }

    public void setHasPreviousPage(boolean hasPreviousPage) {
        this.hasPreviousPage = hasPreviousPage;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public boolean isFirstPage() {
        return isFirstPage;
    }

    public void setFirstPage(boolean firstPage) {
        isFirstPage = firstPage;
    }

    public boolean isLastPage() {
        return isLastPage;
    }

    public void setLastPage(boolean lastPage) {
        isLastPage = lastPage;
    }
}
