package com.topcom.cms.yuqing.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 
 * @ClassName: UrlDomain
 * @Description:截取网站地址域名
 * @author: 邵磊
 * @date: 2014年7月10日 下午3:37:14
 */
public class UrlDomain {

	public static String urlDamain(String url) {

		Pattern p = Pattern.compile("(?<=//|)((\\w)+\\.)+\\w+");

		Matcher m = p.matcher(url);

		String domainUrl = null;
		if (m.find()) {

			domainUrl = m.group();
		}

		return domainUrl;
	}

	public static void main(String[] args) {
		// String url = "http://www.cn-cg.com/index.php/newslist/index/";
//		String url = "http://news.sogou.com/site:ifeng.com";
		String url = "http://www.anhuimj.gov.cn/master/webchannel!detail_new.action?cmsContent.infoId=14997&channel.channelId=50&parent.channelId=-9999";
		System.out.println(urlDamain(url));
	}

}
