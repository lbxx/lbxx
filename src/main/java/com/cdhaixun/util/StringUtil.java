package com.cdhaixun.util;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具类
 */
public class StringUtil {
    /**
     * 是否为半角字符串
     * @param param
     * @return true为半角，反正为全角
     * @author HuangCheng
     * @date 2012-5-12
     */
    public static boolean isAllHalf(String param) {
        char[] chs = param.toCharArray();
        for (int i = 0; i < chs.length; i++) {
            if (!(('\uFF61' <= chs[i]) && (chs[i] <= '\uFF9F'))
                    && !(('\u0020' <= chs[i]) && (chs[i] <= '\u007E'))) {
                return false;
            }
        }
        return true;
    }
    /**
     * 用户账号正则表达式判断 1、必须以半角的字母开头 2、不能包括任何标点符号 3、长度不超过10位 4、不能为空 5、不能有中文
     * @param value
     * @return 通过规则为true, 反之为false
     * @author HuangCheng
     * @date 2012-5-12
     */
    public static Boolean isUserIdOk(String value) {
        if (isAllHalf(value) && value.length() > 0 && value.length() < 11) {
            String userRegEx = "^[a-z]+[0-9a-z]*";
            Pattern useridPattern = Pattern.compile(userRegEx);
            Matcher m = useridPattern.matcher(value);
            return m.matches();
        }
        return false;
    }
    /**
     * 判断用户真实姓名 1.只能是中文 2.中文个数只能是2-6个
     * @param value
     * @return Boolean
     * @author HuangCheng
     * @date 2012-5-18
     */
    public static Boolean isUserRealNameOk(String value) {
        String regEx = "[\\u4e00-\\u9fa5]{2,6}";
        Pattern sChinese = Pattern.compile(regEx);
        Matcher m = sChinese.matcher(value);
        return m.matches();
    }
    /**
     * 替换掉多余的空格
     * @param value
     * @return
     */
    public static String replaceBlank(String value) {
        return value.replaceAll("\\s*", "");
    }
    /**
     * 手机号码正则判断,只能是11位数字
     * @param value
     * @return
     */
    public static Boolean isPhoneNumber(String value) {
        String userRegEx = "^1([0-9]{10})";
        Pattern useridPattern = Pattern.compile(userRegEx);
        Matcher m = useridPattern.matcher(value);
        return m.matches();
    }
    /**
     * 根据key从cookieValue中取出value
     * @param cookieValue
     *            (key1_value1:key2_value2)
     * @param key
     * @return
     */
    public static Integer getValueInCookieByKey(String cookieValue, Integer key) {
        String[] strs = cookieValue.split("_");
        for (int i = 0; i < strs.length; i++) {
            if (strs[i] != null && !strs[i].equals("")) {
                String[] res = strs[i].split(":");
                if (res.length == 2) {
                    int bId = Integer.parseInt(res[0]);
                    int value = Integer.parseInt(res[1]);
                    if (bId == key) {
                        return value;
                    }
                }
            }
        }
        return null;
    }
    /**
     * 不是手机号码返回true
     * @param phone
     * @return
     */
    public static Boolean isNotPhoneNumber(String phone) {
        return !isPhoneNumber(phone);
    }
    /**
     * 字符串为空判断
     */
    public static boolean isBlank(String str) {
        int strLen;
        if (str == null || (strLen = str.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if ((Character.isWhitespace(str.charAt(i)) == false)) {
                return false;
            }
        }
        return true;
    }
    /**
     * 字符串不为空判断
     */
    public static boolean isNotBlank(String str) {
        return !isBlank(str);
    }
    public static String trim(String str) {
        return str == null ? null : str.trim();
    }

    /**
     * 根据数字，返回中文大写
     * @param level
     * @return
     */
    public static String getLevelView(Integer level) {
        switch (level) {
            case 1:
                return "一";
            case 2:
                return "二";
            case 3:
                return "三";
            case 4:
                return "四";
            case 5:
                return "五";
            case 6:
                return "六";
            case 7:
                return "日";
            case 8:
                return "八";
            case 9:
                return "九";
            default:
                return "十";
        }
    }
    
    static long seed = System.currentTimeMillis();
    static long skip = Long.parseLong("187649984473770");
    static char ch[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
            'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N',
            'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a',
            'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n',
            'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', };
    /**
     * 随机生成数字和字符串组成的字符串
     */
    public static String getString(int length) {
        if (length > 62 || length <= 0) {
            throw new IllegalArgumentException();
        } else if (length == 62) {
            length--;
        }
        Random r = new Random(seed);
        int rNum;
        char temp;
        for (int i = 0; i < length; i++) {
            rNum = r.nextInt(62);
            seed += skip;
            r.setSeed(seed);
            if (rNum < i) {
                rNum = i + 1;
            }
            temp = ch[i];
            ch[i] = ch[rNum];
            ch[rNum] = temp;
        }
        return new String(ch, 0, length);
    }
    static char num[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
    /**
     * 最长只能10个数字
     * @param length
     * @return
     */
    public static String getStringOnlyNum(int length) {
        if (length > 10 || length <= 0) {
            throw new IllegalArgumentException();
        } else if (length == 10) {
            length--;
        }
        Random r = new Random(seed);
        int rNum;
        char temp;
        for (int i = 0; i < length; i++) {
            rNum = r.nextInt(10);
            seed += skip;
            r.setSeed(seed);
            if (rNum < i) {
                rNum = i + 1;
            }
            temp = num[i];
            num[i] = num[rNum];
            num[rNum] = temp;
        }
        return new String(num, 0, length);
    }
    /**
     * 随机生成数字和字符串组成的字符串
     */
    public static String getRandomString(int length) {
        if (length > 62 || length <= 0) {
            throw new IllegalArgumentException();
        } else if (length == 62) {
            length--;
        }
        Random r = new Random(seed);
        int rNum;
        char temp;
        for (int i = 0; i < length; i++) {
            rNum = r.nextInt(62);
            seed += skip;
            r.setSeed(seed);
            if (rNum < i) {
                rNum = i + 1;
            }
            temp = ch[i];
            ch[i] = ch[rNum];
            ch[rNum] = temp;
        }
        return new String(ch, 0, length);
    }
    public static String randomUUIDString(){
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replaceAll("-", "");
        return uuid;
    }

      /**
       * 生成特定位数的随机数字 
       * @param length 
       * @return 
       */  
      public static String getRandomNum(int length) {  
          String val = "";  
          Random random = new Random();  
          for (int i = 0; i < length; i++) {  
              val += String.valueOf(random.nextInt(10));  
          }  
          return val;  
      }
}