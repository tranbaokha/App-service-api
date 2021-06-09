package com.services.api.jwt;


import com.services.api.utils.ZipUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Slf4j
public class UserJwt implements Serializable {

    public static final String DELIM = "::";
    public static final String EMPTY_STRING = "<>";
    private Long accountId = -1L;
    private Long orgId = -1L;
    private String username = EMPTY_STRING;
    private String pemission = EMPTY_STRING;
    private String deviceId = EMPTY_STRING;
    private Integer userKind = -1; //loại user là admin hay là gì
    private String kind = EMPTY_STRING;//token kind

    public void setUserKind(int userKind) {
        this.userKind = userKind;
    }
    public int getUserKind() {
        return userKind;
    }
    public Long getAccountId() {
        return accountId;
    }
    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }
    public Long getOrgId() {
        return orgId;
    }
    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }
    public String getKind() {
        return kind;
    }
    public void setKind(String kind) {
        this.kind = kind;
    }
    public String getPemission() {
        return pemission;
    }
    public void setPemission(String pemission) {
        this.pemission = pemission;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }
    public String getDeviceId() {
        return deviceId;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public String toClaim(){
        return ZipUtils.zipString(accountId+DELIM+ orgId +DELIM+kind+DELIM+pemission+DELIM+deviceId+DELIM+userKind+DELIM+username) ;
    }

    public static UserJwt decode(String input){
        UserJwt result = null;
        try {
            String[] items = ZipUtils.unzipString(input).split(DELIM);
            if(items.length == 7){
                result = new UserJwt();
                result.setAccountId(parserLong(items[0]));
                result.setOrgId(parserLong(items[1]));
                result.setKind(checkString(items[2]));
                result.setPemission(checkString(items[3]));
                result.setDeviceId(checkString(items[4]));
                result.setUserKind(parserInt(items[5]));
                result.setUsername(checkString(items[6]));
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return  result;
    }

    private static Long parserLong(String input){
        try{
            Long out = Long.parseLong(input);
            if(out > 0){
                return  out;
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return null;
    }

    private static Integer parserInt(String input){
        try{
            Integer out = Integer.parseInt(input);
            if(out > 0){
                return  out;
            }
        }catch (Exception e){
            log.error(e.getMessage(),e);
        }
        return null;
    }

    private static String checkString(String input){
        if(!input.equals(EMPTY_STRING)){
            return  input;
        }
        return  null;
    }
}
