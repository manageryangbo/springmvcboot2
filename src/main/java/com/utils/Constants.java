package com.utils;


/**
 * 常量表
 *
 * @author gingerjie
 * @version $Id: Constants.java, v 0.1 2014-2-28 上午11:18:28 gingerjie Exp $
 */
public interface Constants {
    /**
     * 异常信息统一头信息<br>
     * 非常遗憾的通知您,程序发生了异常
     */
    String Exception_Head = "OH,MY GOD! SOME ERRORS OCCURED! AS FOLLOWS :";
    /** 缓存键值 */
//    static final Map<Class<?>, CacheKey> cacheKeyMap = InstanceUtil.newHashMap();
    /** 操作名称 */
    String OPERATION_NAME = "OPERATION_NAME";
    /** 客户端语言 */
    String USERLANGUAGE = "userLanguage";
    /** 客户端主题 */
    String WEBTHEME = "webTheme";
    /** 当前用户 */
    String CURRENT_USER = "CURRENT_USER";
    /** 客户端信息 */
    String USER_AGENT = "USER-AGENT";
    /** 客户端信息 */
    String USER_IP = "USER_IP";
    /** 登录地址 */
    String LOGIN_URL = "/login.html";
    /** 缓存命名空间 */
    String CACHE_NAMESPACE = "wastons:";
    /** 缓存命名空间 */
    String SYSTEM_CACHE_NAMESPACE = "S:wastons:";
    /** 缓存命名空间 */
    String CACHE_NAMESPACE_LOCK = "L:wastons:";
    /** 上次请求地址 */
    String PREREQUEST = CACHE_NAMESPACE + "PREREQUEST";
    /** 上次请求时间 */
    String PREREQUEST_TIME = CACHE_NAMESPACE + "PREREQUEST_TIME";
    /** 非法请求次数 */
    String MALICIOUS_REQUEST_TIMES = CACHE_NAMESPACE + "MALICIOUS_REQUEST_TIMES";
    /** 在线用户数量 */
    String ALLUSER_NUMBER = SYSTEM_CACHE_NAMESPACE + "ALLUSER_NUMBER";
    /** TOKEN */
    String TOKEN_KEY = SYSTEM_CACHE_NAMESPACE + "TOKEN_KEY:";
    /** shiro cache */
    String REDIS_SHIRO_CACHE = SYSTEM_CACHE_NAMESPACE + "SHIRO-CACHE:";
    /** SESSION */
    String REDIS_SHIRO_SESSION = SYSTEM_CACHE_NAMESPACE + "SHIRO-SESSION:";
    /** CACHE */
    String MYBATIS_CACHE = "D:wastons:MYBATIS:";
    /** 默认数据库密码加密key */
    String DB_KEY = "90139119";
    /** 临时目录 */
    String TEMP_DIR = "/temp/";
    /** 请求报文体 */
    String REQUEST_BODY = "wastons.requestBody";

    /** 日志表状态 */
    interface JOBSTATE {
        /**
         * 日志表状态，初始状态，插入
         */
        String INIT_STATS = "I";
        /**
         * 日志表状态，成功
         */
        String SUCCESS_STATS = "S";
        /**
         * 日志表状态，失败
         */
        String ERROR_STATS = "E";
        /**
         * 日志表状态，未执行
         */
        String UN_STATS = "N";
    }

    /** 短信验证码类型 */
    interface MSGCHKTYPE {
        /** 注册 */
        String REGISTER = CACHE_NAMESPACE + "REGISTER:";
        /** 登录 */
        String LOGIN = CACHE_NAMESPACE + "LOGIN:";
        /** 修改密码验证码 */
        String CHGPWD = CACHE_NAMESPACE + "CHGPWD:";
        /** 身份验证验证码 */
        String VLDID = CACHE_NAMESPACE + "VLDID:";
        /** 信息变更验证码 */
        String CHGINFO = CACHE_NAMESPACE + "CHGINFO:";
        /** 活动确认验证码 */
        String AVTCMF = CACHE_NAMESPACE + "AVTCMF:";
    }

    interface TIMES {
        long SECOND = 1000; // 1秒 java已毫秒为单位
        long MINUTE = SECOND * 60; // 一分钟
        long HOUR = MINUTE * 60; // 一小时
        long DAY = HOUR * 24; // 一天
        long WEEK = DAY * 7; // 一周
        long YEAR = DAY * 365; // 一年
    }
}
