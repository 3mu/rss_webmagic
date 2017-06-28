package com.es.core;

import com.es.core.config.ConfigManager;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.JedisPool;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.scheduler.RedisScheduler;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public  class  App{
    public static void main(String[] args) {

//        Properties s= ConfigManager.GetConfig(Properties.class);
        InputStream stream=App.class.getClassLoader().getResourceAsStream("app.properties");
        Properties properties=new Properties();
        try {
            properties.load(stream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String host=properties.getProperty("redis.config.host");
        int port= Integer.parseInt(properties.getProperty("redis.config.port"));
        Spider spider=Spider.create(new MeituanPageProcessor());
        GenericObjectPoolConfig config=new GenericObjectPoolConfig();
        JedisPool pool=new JedisPool(config,host,port,10000,"grasp#123",false);
        spider.setScheduler(new RedisScheduler(pool));
        spider.addUrl("http://tech.meituan.com/");
        spider.thread(5);
        spider.run();
    }

}

