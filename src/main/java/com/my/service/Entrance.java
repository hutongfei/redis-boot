package com.my.service;

import java.util.concurrent.TimeUnit;
 
import com.gargoylesoftware.htmlunit.BrowserVersion;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.*;

public class Entrance {
 
	public static void main (String[] args ) throws Exception 
	{
		String webUrl = "https://a201911091757098011333.szwego.com/static/index.html#/shop_detail/A201911091757098011333";
		HtmlPage page = getHtmlPage(webUrl);
		DomElement container = page.getElementById("container");

		DomNodeList<HtmlElement> div = container.getElementsByTagName("div");

		for (HtmlElement htmlElement : div) {
			System.out.println(htmlElement.getTextContent());
		}

		System.err.println("查询数据成功");
	}
 
	private static HtmlPage getHtmlPage(String url) throws Exception{
		final WebClient webClient=new WebClient(BrowserVersion.CHROME);
		webClient.getOptions().setCssEnabled(false);
		webClient.getOptions().setMaxInMemory(500);
		final HtmlPage page = webClient.getPage(url);
		System.err.println("查询中，请稍候");
		TimeUnit.SECONDS.sleep(5); 	//web请求数据需要时间，必须让主线程休眠片刻
		webClient.close();
		
		return page;
	}
	
	private static void printTable(HtmlTableBody tbody) throws Exception{
//		DomNodeList<HtmlElement> trs = tbody.getElementsByTagName("tr");
//		for(int i=0;i<trs.size();i++){
//			HtmlElement node = trs.get(i);
//			DomNodeList<HtmlElement> tds = node.getElementsByTagName("td");
//			for(int j=0;j<tds.size();j++){
//				HtmlElement td = tds.get(j);
//				System.err.print(td.asText()+"\t");
//			}
//			System.err.println();
//		}
//		tbody.getele
	}
}