package com.github.rogerli.utils;

import org.apache.shiro.cache.CacheException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public final class SerializeStringUtils {

    public SerializeStringUtils() {

    }

    /**
     * @param object
     * @return
     */
    public static byte[] serialize(String object) {
        try {
            return object.getBytes("UTF-8");
        } catch (Exception e) {
            throw new CacheException(e);
        }
    }

    /**
     * @param bytes
     * @return
     */
    public static String deserialize(byte[] bytes) {
        if (bytes == null) {
            return null;
        } else {
            try {
                return new String(bytes);
            } catch (Exception e) {
                throw new CacheException(e);
            }
        }
    }
}