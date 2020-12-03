package com.administrator.page;

import java.util.ArrayList;
import java.util.List;

public class PagingFunction {
	public static List<Object> getPageList(List<Object> list,String page,int aPageNum){
		
		// 页面当前页
		int currentPage = 0;
		Paging paging = new Paging();
		
		paging.setList(list);// 从数据库得到数据存入的list集合
		paging.setCount();// 数据总数
		paging.setPagesize(aPageNum);// 一个页面的数据多少条
		paging.setPagenumber();// 总的页面数
		paging.setEndpage();// 最后一页
		paging.setIndexpage(1);// 第一页
		if (page!= null) {
			// 将页转换整型判断其大小
			int pag = Integer.parseInt(page);
			// 当大于零，将传过来的pag值赋给当前页page
			if (pag >= 0) {
				currentPage = pag;
				// 如果小于最大值时则，将其传过来的值减1在赋值给当前页，让其一直在最后一页
				if (pag > (paging.getPagenumber() - 1)) {
					currentPage = pag - 1;
				}
			}else{
				pag=0;
			}
			currentPage=pag;
		}
		
		paging.setPage(currentPage);// 最终确认当前页
		List<Object> list_page = new ArrayList<>(); // 分好后的数据
		for (int i = paging.getPage() * paging.getPagesize(); i < (paging.getPage() + 1) * paging.getPagesize()
				&& i < list.size(); i++) {
			list_page.add(list.get(i));
		}
		
	
		
		return list_page;
	}
	
	
	public static int [] getPageNum(List<Object> list){
		//总页数
		int total_page=(int)Math.ceil(list.size()/10.0);
		int []page_num=new int[total_page];
		for(int i=0;i<total_page;i++){
			page_num[i]=i+1;
		}
		return page_num;
		
	}
}
