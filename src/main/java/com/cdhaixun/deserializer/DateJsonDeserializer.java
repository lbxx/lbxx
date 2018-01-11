package com.cdhaixun.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.commons.lang3.time.DateUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
public class DateJsonDeserializer extends JsonDeserializer<Date>
{

    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JsonProcessingException {

        try {
            return DateUtils.parseDate(jsonParser.getText(),"yyyy-MM-dd HH:mm:ss","yyyy-MM-dd");
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
