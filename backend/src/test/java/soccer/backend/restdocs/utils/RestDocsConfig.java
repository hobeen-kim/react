package soccer.backend.restdocs.utils;

import org.springframework.restdocs.snippet.Attributes;

public class RestDocsConfig {

    public static Attributes.Attribute field(
            final String key,
            final String value){
        return new Attributes.Attribute(key,value);
    }
}
