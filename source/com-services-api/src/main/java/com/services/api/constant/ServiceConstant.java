package com.services.api.constant;


import com.services.api.utils.ConfigurationService;

public class ServiceConstant {


    public static final String URL_QRCODE_ACCESS = "https://hqqrcode.developteam.net"  ;
    public static final String ADMIN_USERNAME = "admin";

    public static final String ROOT_DIRECTORY =  ConfigurationService.getInstance().getString("file.upload-dir","/tmp/upload");

    public static final Integer USER_KIND_ADMIN = 1;
    public static final Integer USER_KIND_CUSTOMER = 2;
    public static final Integer USER_KIND_AGENCY = 3;

    public static final Integer STATUS_ACTIVE = 1;
    public static final Integer STATUS_PENDING = 0;
    public static final Integer STATUS_LOCK = -1;
    public static final Integer STATUS_DELETE = -2;

    public static final Integer GROUP_KIND_ADMIN = 1;
    public static final Integer GROUP_KIND_SHOP = 2;
    public static final Integer GROUP_KIND_CUSTOMER = 3;

    public static final Integer MAX_ATTEMPT_FORGET_PWD = 5;
    public static final int MAX_TIME_FORGET_PWD = 5 * 60 * 1000; //5 minutes
    public static final Integer MAX_ATTEMPT_LOGIN = 5;



    public static final String DEVICE_TYPE_ANDROID = "ANDROID";
    public static final String DEVICE_TYPE_IOS = "IOS";
    public static final String DEVICE_TYPE_WEB = "WEBSITE";
    public static final String DEVICE_TYPE_PARTNER_API = "PARTNER_API";

    private ServiceConstant(){
        throw new IllegalStateException("Utility class");
    }

}
