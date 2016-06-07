package org.vicykie.framework.myApp.common.serializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;

/**
 * Created by vicykie on 2016/6/7.
 */
public class EnumDeserializer extends JsonDeserializer<Enum> {

    @Override
    public Enum deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        Enum e = Enum.valueOf(Enum.class, p.getValueAsString());
        return e;
    }
}
