package com.nsn.demo.web.listener;

import com.nsn.demo.kit.ConsoleTextArea;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author donghao
 * @since 1.0
 */
@WebListener
public class Listener implements ServletContextListener{
    public void contextInitialized(ServletContextEvent sce) {
        try{
            //截取控制台输出流
            new ConsoleTextArea().start();
            //打开浏览器访问项目
            String url = "http://localhost:8080/testQuick4j";
            java.net.URI uri = java.net.URI.create(url);
            // 获取当前系统桌面扩展
            java.awt.Desktop dp = java.awt.Desktop.getDesktop();
            // 判断系统桌面是否支持要执行的功能
            if (dp.isSupported(java.awt.Desktop.Action.BROWSE)) {
                dp.browse(uri);// 获取系统默认浏览器打开链接
            }
        }catch (Exception e){

        }

    }

    public void contextDestroyed(ServletContextEvent sce) {

    }
}
