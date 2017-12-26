package com.cdhaixun.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class DateJsonDeserializer extends JsonDeserializer<Date>
{
    private static final SimpleDateFormat simpleDateFormatDateTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat simpleDateFormatDate=new SimpleDateFormat("yyyy-MM-dd");
    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {
        try {
            return simpleDateFormatDateTime.parse(jsonParser.getText());
        } catch (ParseException e) {
            try {
                return simpleDateFormatDate.parse(jsonParser.getText());
            } catch (ParseException e1) {
                e1.printStackTrace();
            }
        }
        return null;
    }
}
