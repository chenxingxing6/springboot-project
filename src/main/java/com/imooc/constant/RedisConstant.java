package com.imooc.constant;

/**
 * redis常量
 * Created by SqMax on 2018/4/1.
 */
public interface RedisConstant {

    String TOKEN_PREFIX="token_%s";

    String CODE_OPENID = "code_openid";

    Integer EXPIRE=7200;//2小时
}
