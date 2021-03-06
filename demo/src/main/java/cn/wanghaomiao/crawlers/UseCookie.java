package cn.wanghaomiao.crawlers;

import cn.wanghaomiao.seimi.annotation.Crawler;
import cn.wanghaomiao.seimi.http.HttpMethod;
import cn.wanghaomiao.seimi.struct.Request;
import cn.wanghaomiao.seimi.struct.Response;
import cn.wanghaomiao.seimi.def.BaseSeimiCrawler;
import cn.wanghaomiao.xpath.model.JXDocument;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用注解进行配置
 * 启用了cookie后全程请求将会使用同一个cookiestore，也就是能保持延续请求的各种状态，包括需要登录的场景等等，默认不开启。
 * @author 汪浩淼 [et.tw@163.com]
 * @since 2015/10/21.
 */
@Crawler(name = "usecookie",useCookie = true)
public class UseCookie extends BaseSeimiCrawler {
    @Override
    public String[] startUrls() {
        //用于触发第一个回调函数
        return new String[]{"http://www.douban.com"};
    }

    @Override
    public void start(Response response) {
        //提交登陆请求
        Request login = Request.build("https://accounts.douban.com/login","afterLogin");

        Map<String,String> params = new HashMap<>();
        params.put("source","main");
        params.put("redir","https://www.douban.com/mine/");
        params.put("form_email","xx");
        params.put("form_password","xx");
        params.put("login","登录");
        login.setHttpMethod(HttpMethod.POST);
        login.setParams(params);
        push(login);
    }

    public void afterLogin(Response response){
        logger.info(response.getContent());
//        push(Request.build("https://www.douban.com/mine/","minePage"));
    }

//    public void minePage(Response response){
//        logger.info(response.getContent());
//    }

}
