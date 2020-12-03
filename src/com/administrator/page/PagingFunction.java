package com.administrator.page;

import java.util.ArrayList;
import java.util.List;

public class PagingFunction {
	public static List<Object> getPageList(List<Object> list,String page,int aPageNum){
		
		// ҳ�浱ǰҳ
		int currentPage = 0;
		Paging paging = new Paging();
		
		paging.setList(list);// �����ݿ�õ����ݴ����list����
		paging.setCount();// ��������
		paging.setPagesize(aPageNum);// һ��ҳ������ݶ�����
		paging.setPagenumber();// �ܵ�ҳ����
		paging.setEndpage();// ���һҳ
		paging.setIndexpage(1);// ��һҳ
		if (page!= null) {
			// ��ҳת�������ж����С
			int pag = Integer.parseInt(page);
			// �������㣬����������pagֵ������ǰҳpage
			if (pag >= 0) {
				currentPage = pag;
				// ���С�����ֵʱ�򣬽��䴫������ֵ��1�ڸ�ֵ����ǰҳ������һֱ�����һҳ
				if (pag > (paging.getPagenumber() - 1)) {
					currentPage = pag - 1;
				}
			}else{
				pag=0;
			}
			currentPage=pag;
		}
		
		paging.setPage(currentPage);// ����ȷ�ϵ�ǰҳ
		List<Object> list_page = new ArrayList<>(); // �ֺú������
		for (int i = paging.getPage() * paging.getPagesize(); i < (paging.getPage() + 1) * paging.getPagesize()
				&& i < list.size(); i++) {
			list_page.add(list.get(i));
		}
		
	
		
		return list_page;
	}
	
	
	public static int [] getPageNum(List<Object> list){
		//��ҳ��
		int total_page=(int)Math.ceil(list.size()/10.0);
		int []page_num=new int[total_page];
		for(int i=0;i<total_page;i++){
			page_num[i]=i+1;
		}
		return page_num;
		
	}
}
