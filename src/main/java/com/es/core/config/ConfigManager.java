package com.es.core.config;

import sun.net.www.content.text.Generic;

import java.lang.reflect.Type;

import static javafx.scene.input.KeyCode.T;

@Properties("123123")
public  class ConfigManager{

    public  ConfigManager(){


    }

   public static <T> T GetConfig(Class<T> tclass){
       try {
           T t=   tclass.newInstance();
         return  t;
       }
       catch (InstantiationException e) {

           e.printStackTrace();
       } catch (IllegalAccessException e) {
           e.printStackTrace();
       }
       return null;
    }
}

