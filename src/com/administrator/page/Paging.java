package com.administrator.page;

import java.util.List;
/*
 * ��ҳ��ʵ����
 */
public class Paging {
	private int page;//��ǰҳ
    private int pagesize;//ҳ����������
    private int indexpage=1;//��ҳ
    private int endpage;//βҳ
    private int count;//����������
    private int pagenumber;//��ҳ����
    private List<Object> list;//�õ������ݷ���list������
    public int getPage() {
        return page;
    }
    public void setPage(int page) {
        this.page = page;
    }
    public int getPagesize() {
        return pagesize;
    }
    public void setPagesize(int pagesize) {
        this.pagesize = pagesize;
    }
    public int getIndexpage() {
        return indexpage;
    }
    public void setIndexpage(int indexpage) {
        this.indexpage = indexpage;
    }
    public int getEndpage() {
        
        return endpage;
    }
    public void setEndpage() {
        this.endpage=pagenumber;
    }
    public int getCount() {
        this.count=list.size();
        return count;
    }
    public void setCount() {
        this.count=list.size();
    }
    
    public int getPagenumber() {
        return pagenumber;
    }
    public void setPagenumber() {
        this.pagenumber=(count%pagesize==0)?count/pagesize:count/pagesize+1;
    }
    public List<Object> getList() {
        return list;
    }
    public void setList(List<Object> list) {
        this.list = list;
    }
    
}
