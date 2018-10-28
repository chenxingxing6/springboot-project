###基于SpringBoot的微信点餐系统

---
## 总结
1. 怎么接入微信公众号，appId,appSecret,token,openId
2. 微信支付
3. 微信扫码登陆
4. websocket消息推送
5. 微信网页授权的整个过程
6. 微信菜单怎么生成
7. springboot redis怎么用
8. redis分布式锁
9. java8 lambda表达式
10. ResultVO<OrderDTO>

---
微信公众平台
```xml
<dependency>
    <groupId>com.github.binarywang</groupId>
    <artifactId>weixin-java-mp</artifactId>
    <version>2.7.0</version>
</dependency>
```

微信支付
```xml
<dependency>
    <groupId>cn.springboot</groupId>
    <artifactId>best-pay-sdk</artifactId>
    <version>1.1.0</version>
</dependency>
```

```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-data-redis</artifactId>
</dependency>
```

webSocket
```xml
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-websocket</artifactId>
</dependency>
```


```HTML
@Autowired
private StringRedisTemplate redisTemplate;

redisTemplate.opsForValue().set(code, openid.toString() , expire, TimeUnit.SECONDS);
String openId = redisTemplate.opsForValue().get(code); 
```



RestTemplate
```HTML
RestTemplate restTemplate=new RestTemplate();
String response=restTemplate.getForObject(url,String.class);
```

