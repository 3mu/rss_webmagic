package com.es.core;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;

public class MeituanPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
        List<String> targets=new ArrayList<String>();
        Selectable links=page.getHtml().links();
        for(String link : links.all())
        {
           if (link.contains("tech.meituan.com")&&link.contains(".html")){
               targets.add(link);
           }
        }
        page.addTargetRequests(targets);
        System.out.println(page.getUrl());
    }

    @Override
    public Site getSite() {
//        System.out.println(site);
        return site;
    }

}
