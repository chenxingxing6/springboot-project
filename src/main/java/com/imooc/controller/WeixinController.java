package com.imooc.controller;

import com.alibaba.fastjson.JSONObject;
import com.imooc.constant.RedisConstant;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

/**
 * Created by SqMax on 2018/3/23.
 */
@RestController
@RequestMapping("/weixin")
@Slf4j
public class WeixinController {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @Autowired
    private WxMpService wxMpService;

    @GetMapping("/auth")
    public void auth(@RequestParam("code") String code){
        log.info("进入auth方法.......");
        log.info("code={}",code);

        String url="https://api.weixin.qq.com/sns/oauth2/access_token?"
                +"appid=wx53719dc3afd7f1db&secret=36dd09982a14ab0f78726d24326ed325&code="+ code +"&grant_type=authorization_code";
        RestTemplate restTemplate=new RestTemplate();
        String response=restTemplate.getForObject(url,String.class);
        Integer expire= RedisConstant.EXPIRE;
        JSONObject jsonObject = JSONObject.parseObject(response);
        Object openid = jsonObject.get("openid");
        if (openid != null) {
            redisTemplate.opsForValue().set(code, openid.toString() , expire, TimeUnit.SECONDS);
        }
        log.info("response={}",response);

    }

    /**
     * 微信消息接收和token验证
     * @param model
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/check_token", method = RequestMethod.GET)
    public void hello(Model model, HttpServletRequest request, HttpServletResponse response) throws IOException {
        boolean isGet = request.getMethod().toLowerCase().equals("get");
        PrintWriter print;
        if (isGet) {
            // 微信加密签名
            String signature = request.getParameter("signature");
            // 时间戳
            String timestamp = request.getParameter("timestamp");
            // 随机数
            String nonce = request.getParameter("nonce");
            // 随机字符串
            String echostr = request.getParameter("echostr");
            //注入token的配置参数
            WxMpInMemoryConfigStorage wxConfigProvider=new WxMpInMemoryConfigStorage();
            //注入token值
            wxConfigProvider.setToken("cxx");
            wxMpService.setWxMpConfigStorage(wxConfigProvider);
            boolean flag=wxMpService.checkSignature(timestamp, nonce, signature);
            PrintWriter out=response.getWriter();
            if(flag){
                out.print(echostr);
            }
            out.close();
        }
    }

}
