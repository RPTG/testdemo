package com.wentnet.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.json.JsonReadFeature;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.TimeZone;

public class JsonUtil {

    private static final Logger log = LoggerFactory.getLogger(JsonUtil.class);
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    public JsonUtil() {
    }

    public static String encode(Object obj) {
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException var2) {
            throw new RuntimeException(var2);
        }
    }

    public static <T> T decode(String json, Class<T> valueType) {
        try {
            return OBJECT_MAPPER.readValue(json, valueType);
        } catch (JsonProcessingException var3) {
            throw new RuntimeException(var3);
        }
    }

    public static <T> T decode(String json, JavaType javaType) {
        try {
            return OBJECT_MAPPER.readValue(json, javaType);
        } catch (JsonProcessingException var3) {
            throw new RuntimeException(var3);
        }
    }

    public static <T> T decode(String json, TypeReference<T> valueTypeRef) {
        try {
            return OBJECT_MAPPER.readValue(json, valueTypeRef);
        } catch (JsonProcessingException var3) {
            throw new RuntimeException(var3);
        }
    }

    public static byte[] encodeToBytes(Object obj) {
        try {
            return OBJECT_MAPPER.writeValueAsBytes(obj);
        } catch (JsonProcessingException var2) {
            throw new RuntimeException(var2);
        }
    }

    public static <T> T decode(byte[] bytes, Class<T> valueType) {
        try {
            return OBJECT_MAPPER.readValue(bytes, valueType);
        } catch (IOException var3) {
            throw new RuntimeException(var3);
        }
    }

    public static <T> T decode(byte[] bytes, JavaType javaType) {
        try {
            return OBJECT_MAPPER.readValue(bytes, javaType);
        } catch (IOException var3) {
            throw new RuntimeException(var3);
        }
    }

    public static <T> T decode(byte[] bytes, TypeReference<T> valueTypeRef) {
        try {
            return OBJECT_MAPPER.readValue(bytes, valueTypeRef);
        } catch (IOException var3) {
            throw new RuntimeException(var3);
        }
    }

    public static <T> T decode(InputStream inputStream, Class<T> valueType) {
        try {
            return OBJECT_MAPPER.readValue(inputStream, valueType);
        } catch (IOException var3) {
            throw new RuntimeException(var3);
        }
    }

    public static <T> T decode(InputStream inputStream, JavaType javaType) {
        try {
            return OBJECT_MAPPER.readValue(inputStream, javaType);
        } catch (IOException var3) {
            throw new RuntimeException(var3);
        }
    }

    public static <T> T decode(InputStream inputStream, TypeReference<T> valueTypeRef) {
        try {
            return OBJECT_MAPPER.readValue(inputStream, valueTypeRef);
        } catch (IOException var3) {
            throw new RuntimeException(var3);
        }
    }

    public static void encodeToOutput(Object obj, OutputStream outputStream) {
        try {
            OBJECT_MAPPER.writeValue(outputStream, obj);
        } catch (IOException var3) {
            throw new RuntimeException(var3);
        }
    }

    public static void encodeToOutput(Object obj, OutputStream outputStream, String charsetName) {
        String str = encode(obj);

        try {
            IOUtils.write(str, outputStream, charsetName);
        } catch (IOException var5) {
            throw new RuntimeException(var5);
        }
    }

    public static void encodeToFile(Object obj, File file) {
        try {
            OBJECT_MAPPER.writeValue(file, obj);
        } catch (IOException var3) {
            throw new RuntimeException(var3);
        }
    }

    public static void encodeToFile(Object obj, File file, String charsetName) {
        String str = encode(obj);

        try {
            FileUtils.writeStringToFile(file, str, charsetName);
        } catch (IOException var5) {
            throw new RuntimeException(var5);
        }
    }

    public static <T> T decode(File file, Class<T> valueType) {
        try {
            InputStream inputStream = Files.newInputStream(file.toPath());
            Throwable var3 = null;

            Object var4;
            try {
                var4 = decode(inputStream, valueType);
            } catch (Throwable var14) {
                var3 = var14;
                throw var14;
            } finally {
                if (inputStream != null) {
                    if (var3 != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable var13) {
                            var3.addSuppressed(var13);
                        }
                    } else {
                        inputStream.close();
                    }
                }

            }

            return (T) var4;
        } catch (IOException var16) {
            throw new RuntimeException(var16);
        }
    }

    public static <T> T decode(File file, JavaType javaType) {
        try {
            InputStream inputStream = Files.newInputStream(file.toPath());
            Throwable var3 = null;

            Object var4;
            try {
                var4 = decode(inputStream, javaType);
            } catch (Throwable var14) {
                var3 = var14;
                throw var14;
            } finally {
                if (inputStream != null) {
                    if (var3 != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable var13) {
                            var3.addSuppressed(var13);
                        }
                    } else {
                        inputStream.close();
                    }
                }

            }

            return (T) var4;
        } catch (IOException var16) {
            throw new RuntimeException(var16);
        }
    }

    public static <T> T decode(File file, TypeReference<T> valueTypeRef) {
        try {
            InputStream inputStream = Files.newInputStream(file.toPath());
            Throwable var3 = null;

            Object var4;
            try {
                var4 = decode(inputStream, valueTypeRef);
            } catch (Throwable var14) {
                var3 = var14;
                throw var14;
            } finally {
                if (inputStream != null) {
                    if (var3 != null) {
                        try {
                            inputStream.close();
                        } catch (Throwable var13) {
                            var3.addSuppressed(var13);
                        }
                    } else {
                        inputStream.close();
                    }
                }

            }

            return (T) var4;
        } catch (IOException var16) {
            throw new RuntimeException(var16);
        }
    }

    public static <T> T decode(Object obj, Class<T> valueType) {
        return decode(encode(obj), valueType);
    }

    public static <T> T decode(Object obj, JavaType javaType) {
        return decode(encode(obj), javaType);
    }

    public static <T> T decode(Object obj, TypeReference<T> valueTypeRef) {
        return decode(encode(obj), valueTypeRef);
    }

    static {
        OBJECT_MAPPER.configure(JsonReadFeature.ALLOW_UNESCAPED_CONTROL_CHARS.mappedFeature(), true);
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        OBJECT_MAPPER.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        OBJECT_MAPPER.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        OBJECT_MAPPER.setTimeZone(TimeZone.getTimeZone("GMT+8"));
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
        OBJECT_MAPPER.registerModule(javaTimeModule);
        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addSerializer(Long.class, new ToStringSerializer());
        OBJECT_MAPPER.registerModule(simpleModule);
    }

}
