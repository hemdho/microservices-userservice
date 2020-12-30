package com.omniri.assignment.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;

public class CustomDateDeserializer  extends StdDeserializer<Date> {
	
	
	SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
	
    public CustomDateDeserializer() {
       this(null);
    }
 
    public CustomDateDeserializer(Class<?> vc) {
        super(vc);
    }
    
    
 
    @Override
    public Date deserialize(JsonParser jsonparser, DeserializationContext context)
      throws IOException, JsonProcessingException {
        String date = jsonparser.getText();
        try {
        	if(!StringUtils.hasText(date)) return null;
            return formatter.parse(date);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
    }
}