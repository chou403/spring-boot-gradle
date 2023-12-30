package io.chou401.framework.config;

import cn.hutool.core.date.DatePattern;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import io.chou401.framework.jackson.deserializer.JacksonDateDeserializer;
import io.chou401.framework.jackson.deserializer.JacksonStringDeserializer;
import io.chou401.framework.jackson.serializer.JacksonStringSerializer;
import io.chou401.framework.xss.XssJacksonDeserializer;
import io.chou401.framework.xss.XssJacksonSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.ZoneId;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

/**
 * {@code @author}  chou401
 * {@code @date} 2022/4/13
 **/
@Configuration
public class JacksonConfig {

    @Value("${xss.enable}")
    private boolean enableXss;

    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer() {
        return builder -> {
            builder.locale(Locale.CHINA);
            builder.timeZone(TimeZone.getTimeZone(ZoneId.systemDefault()));
            builder.simpleDateFormat(DatePattern.NORM_DATETIME_PATTERN);
            // 反序列化(处理请求参数)
            // 去掉请求参数中字符串左右两边的空格
            builder.deserializerByType(String.class, JacksonStringDeserializer.INSTANCE);
            builder.deserializerByType(Date.class, JacksonDateDeserializer.INSTANCE);
            // 序列化(处理响应结果)
            // 避免long类型精度丢失，将long类型序列化成字符串
            builder.serializerByType(Long.class, ToStringSerializer.instance);
            // 去掉响应结果中字符串左右两边的空格
            builder.serializerByType(String.class, JacksonStringSerializer.INSTANCE);

            // XSS序列化
            if (enableXss) {
                builder.serializerByType(String.class, new XssJacksonSerializer());
                builder.deserializerByType(String.class, new XssJacksonDeserializer());
            }

        };
    }

}
