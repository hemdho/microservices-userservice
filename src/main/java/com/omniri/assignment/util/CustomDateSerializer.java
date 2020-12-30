package com.omniri.assignment.util;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

public class CustomDateSerializer extends StdSerializer<Date> {

	private static final Logger logger = LoggerFactory.getLogger(CustomDateSerializer.class);
	
	//com.ecompp.util.DateFormatter dateFormatter;
		
    private static final long serialVersionUID = -2894356342227378312L;
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
    
    //private DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME;
    
    
  // private SimpleDateFormat formatter = new SimpleDateFormat(DateFormatter.getCurrentDateFormat());
    
    public CustomDateSerializer() {
   // public CustomDateSerializer() {
    	 //Class<Date> t=null;
    	this(null);
    	
    	
   
        //this(null);
        logger.debug("custome Date serialize created");
    }

    public CustomDateSerializer(final Class<Date> t) {
        super(t);
      //  formatter= new SimpleDateFormat(dateFormatter.getCurrentDateFormat());
       // logger.debug(" cuser Date Serialize created " + dateFormatter.getCurrentDateFormat());
        logger.debug("custome Date serialize created " +  t);
    }

	@Override
	public void serialize(Date value, JsonGenerator gen, SerializerProvider provider) throws IOException {
		gen.writeString(formatter.format(value));		
	}
}