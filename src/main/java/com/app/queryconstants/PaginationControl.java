package com.app.queryconstants;

public class PaginationControl {
public static String getPaginationControl(String pageNo,String itemsPerPage) {
	
	String searchByOffset="";
	Integer i = 1;
	try {
		
		if(pageNo==null || pageNo.trim().equals("")||pageNo.trim().equalsIgnoreCase("NA"))
		{
			 searchByOffset="";
			 return searchByOffset+"&&"+i;
		}else
		{
			if(Integer.parseInt(pageNo) >= 2)
			{
				Integer nextpage=Integer.parseInt(itemsPerPage.trim())*Integer.parseInt(pageNo.trim())-Integer.parseInt(itemsPerPage.trim())+1;
				searchByOffset=" offset "+nextpage+" limit "+itemsPerPage+"";
				i=nextpage;
				
			}else
			{
				searchByOffset=" offset "+0+" limit "+itemsPerPage+"";
				
			}
			
		}
		
			
	}catch (Exception e) {	
		System.out.println("Exception in pagenation "+e);
	}
	
	return searchByOffset+"&&"+i;
}	

}
