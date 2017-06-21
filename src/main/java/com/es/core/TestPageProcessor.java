package com.es.core;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.selector.Selectable;

import java.util.ArrayList;
import java.util.List;

public class TestPageProcessor implements PageProcessor {

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000);

    @Override
    public void process(Page page) {
        List<String> targeturls=new ArrayList<String>();
        Selectable links=page.getHtml().links();
        for(String link : links.all())
        {
           if (link.contains("tech.meituan.com")){
               targeturls.add(link);
           }
        }
        page.addTargetRequests(targeturls);
        System.out.println(page.getUrl());
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new TestPageProcessor()).addUrl("http://tech.meituan.com/").thread(5).run();
    }
}