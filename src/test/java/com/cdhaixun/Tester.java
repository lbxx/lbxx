package com.cdhaixun;

import org.apache.commons.codec.digest.DigestUtils;

/**
 * @Author tanggm
 * @Date 2017/7/2 21:25
 */
public class Tester {
    public static void main(String[] args){
        String password = "123456";
       String mdPass = DigestUtils.sha512Hex(password);
       System.out.print(mdPass);
    }
}
