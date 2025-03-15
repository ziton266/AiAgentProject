package vermeg.springAI.AIAgent.config;


import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Iterator;

@Configuration
public class JacksonConfig {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.registerModule(new JodaModule());

        // Register a custom serializer for JSONObject
        SimpleModule module = new SimpleModule();
        module.addSerializer(JSONObject.class, new JsonSerializer<JSONObject>() {
            @Override
            public void serialize(JSONObject jsonObject, JsonGenerator jsonGenerator,
                                  SerializerProvider serializerProvider) throws IOException {
                jsonGenerator.writeStartObject();
                Iterator<String> keys = jsonObject.keys();
                while (keys.hasNext()) {
                    String key = keys.next();
                    try {
                        Object value = jsonObject.get(key);
                        jsonGenerator.writeObjectField(key, value);
                    } catch (JSONException e) {
                        throw new IOException("Error serializing JSONObject", e);
                    }
                }
                jsonGenerator.writeEndObject();
            }
        });
        objectMapper.registerModule(module);

        return objectMapper;
    }
}
