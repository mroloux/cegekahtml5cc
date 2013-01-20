package be.cegeka.eventualizr.web.api;

import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;

@Provider
public class ObjectMapperProvider implements ContextResolver<ObjectMapper>
{
   ObjectMapper mapper;

   public ObjectMapperProvider(){
       mapper = new ObjectMapper();
       mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
       mapper.registerModule(new JodaModule());
   }

   @Override
   public ObjectMapper getContext(Class<?>  type) {
       return mapper;
   }
}
