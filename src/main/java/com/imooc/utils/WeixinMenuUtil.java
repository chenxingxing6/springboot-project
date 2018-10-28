package com.imooc.utils;

import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.bean.menu.WxMenuButton;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpMenuServiceImpl;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: cxx
 * @Date: 2018/10/28 12:18
 */
public class WeixinMenuUtil {
    public static void main(String[] args) {

        //创建菜单
     /*   //创建一级菜单
        WxMenuButton button1=new WxMenuButton();
        button1.setType("click"); //点击事件按钮
        button1.setName("点击菜单");
        button1.setKey("key1"); //根据标志获取点击菜单*/

        //创建一个复合菜单
      /*  WxMenuButton button2=new WxMenuButton();
        button2.setName("多级菜单");

        WxMenuButton button2_1=new WxMenuButton();
        button2_1.setType("click"); //点击事件按钮
        button2_1.setName("子菜单一");
        button2_1.setKey("key2"); //根据标志获取点击菜单

        WxMenuButton button2_2=new WxMenuButton();
        button2_2.setType("click"); //点击事件按钮
        button2_2.setName("子菜单二");
        button2_2.setKey("key3"); //根据标志获取点击菜单*/


     /*   WxMenuButton button3=new WxMenuButton();
        button3.setName("url菜单");
        button3.setType("view");
        button3.setUrl("http://www.baidu.com");  //必须添加http*/


       /* List<WxMenuButton> subButtons=new ArrayList<WxMenuButton>();
        subButtons.add(button2_1);
        subButtons.add(button2_2);
        button2.setSubButtons(subButtons);*/


        WxMenuButton button1=new WxMenuButton();
        button1.setName("进入应用");
        button1.setType("view");
        button1.setUrl("http://192.168.1.45");  //必须添加http

        WxMenuButton button2=new WxMenuButton();
        button2.setName("使用教程");
        button2.setType("view");
        button2.setUrl("http://www.baidu.com");  //必须添加http

        WxMenuButton button3=new WxMenuButton();
        button3.setName("关于我们");
        button3.setType("view");
        button3.setUrl("http://www.baidu.com");  //必须添加http


        List<WxMenuButton> buttons=new ArrayList<WxMenuButton>();
        buttons.add(button1);
        buttons.add(button2);
        buttons.add(button3);

        WxMenu menu=new WxMenu();
        menu.setButtons(buttons);


        //发送请求 创建菜单

        WxMpService service=new WxMpServiceImpl();
        WxMpInMemoryConfigStorage wxConfigProvider=new WxMpInMemoryConfigStorage();
        wxConfigProvider.setAppId("wx53719dc3afd7f1db");
        wxConfigProvider.setSecret("36dd09982a14ab0f78726d24326ed325");
        service.setWxMpConfigStorage(wxConfigProvider);
        WxMpMenuServiceImpl wxMpMenuService = new WxMpMenuServiceImpl(service);
        try {
            wxMpMenuService.menuCreate(menu);
        } catch (WxErrorException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
}
