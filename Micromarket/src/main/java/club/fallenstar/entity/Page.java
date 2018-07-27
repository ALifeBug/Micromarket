package club.fallenstar.entity;

import java.util.List;

/**
 * Created by sccy on 2018/3/19/0019.
 */
public class Page<E>{
    //结果集
    private List<E> list;
    //查询记录总数
    private int totalRecords;
    //每页多少记录
    private int pageSize;
    //第几页
    private int pageNo;

    /**
     *
     * @return当前页开始记录号
     */
    public int getOffset(){
        return (pageNo-1)*pageSize;
    }

    /**
     *
     * @return总页数
     */
    public int getTotalPages(){
        return (totalRecords+pageSize-1)/pageSize;
    }

    /**
     *
     * @return上一页
     */
    public int getPreviousPageNo(){
        if(pageNo<=1)
            return 1;
        return pageNo-1;
    }

    /**
     *
     * @return下一页
     */
    public int getNextPageNo(){
        if(pageNo>=getTotalPages())
            return getTotalPages();
        return pageNo+1;
    }

    public List<E> getList() {
        return list;
    }

    public void setList(List<E> list) {
        this.list = list;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public Page(int pageSize, int pageNo) {
        this.pageSize = pageSize;
        this.pageNo = pageNo;
    }

    public Page() {
    }

    @Override
    public String toString() {
        return "Page{" +
                "list=" + list +
                ", totalRecords=" + totalRecords +
                ", pageSize=" + pageSize +
                ", pageNo=" + pageNo +
                '}';
    }
}
