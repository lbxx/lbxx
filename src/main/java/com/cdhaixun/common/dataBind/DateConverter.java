package com.cdhaixun.common.dataBind;

import org.springframework.core.convert.converter.Converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by geely on 2015/11/22.
 */
public class DateConverter implements Converter<String, Date> {
    @Override
    public Date convert(String source) {
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat sdfDateTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return sdfDateTime.parse(source);
        } catch (ParseException e) {
            try {
                return sdfDate.parse(source);
            } catch (ParseException e1) {
                return null;
            }
        }
    }
}
