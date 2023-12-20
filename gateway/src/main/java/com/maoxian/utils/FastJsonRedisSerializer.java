package com.maoxian.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * Redis使用FastJson序列化
 *
 * @param <T>
 * @author Lin
 * @date 2023/9/21 15:30
 */
public class FastJsonRedisSerializer<T> implements RedisSerializer<T> {

    //默认字符集
    public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;

    //序列化的对象类型
    private final Class<T> clazz;

    static {
        //启用FastJson的自动类型支持，以便在序列化时能够存储对象的类名信息
        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
    }

    public FastJsonRedisSerializer(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }

    @Override
    public byte[] serialize(T t) throws SerializationException {
        if (t == null) {
            return new byte[0];
        }
        return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null || bytes.length == 0) {
            return null;
        }
        String str = new String(bytes, DEFAULT_CHARSET);
        return JSON.parseObject(str, clazz);
    }

    protected JavaType getJavaType(Class<T> clazz) {
        return TypeFactory.defaultInstance().constructType(clazz);
    }
}
