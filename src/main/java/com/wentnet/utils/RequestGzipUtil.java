package com.wentnet.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

public class RequestGzipUtil {
    private static final Logger log = LoggerFactory.getLogger(RequestGzipUtil.class);
    public static final String HEADER_USE_MY_COMPRESS = "use-my-compress";
    public static final String HEADER_USE_MY_CACHE = "use-my-cache";

    public RequestGzipUtil() {
    }

    public static boolean useCompress() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String useCompress = request.getHeader("use-my-compress");
        return "true".equals(useCompress);
    }

    public static boolean useCache() {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
        HttpServletRequest request = servletRequestAttributes.getRequest();
        String useCache = request.getHeader("use-my-cache");
        return "true".equals(useCache);
    }

    public static Object compress(Supplier<?> requestSupplier) throws Exception {
        Object data = requestSupplier.get();
        if (data == null) {
            return null;
        } else if (data instanceof Exception) {
            throw (Exception)data;
        } else if (!useCompress()) {
            return data;
        } else {
            String json = JsonUtil.encode(data);
            if (json == null) {
                throw new Exception("编码数据失败");
            } else {
                String compressData = GzipUtil.compress(json);
                if (compressData == null) {
                    throw new Exception("压缩数据失败");
                } else {
                    return compressData;
                }
            }
        }
    }

    public static void setCache(long cacheExpiration, TimeUnit cacheTimeUnit) {
        if (useCache()) {
            setCacheManually(cacheExpiration, cacheTimeUnit);
        }

    }

    public static void setCacheManually(long cacheExpiration, TimeUnit cacheTimeUnit) {
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes)Objects.requireNonNull(RequestContextHolder.getRequestAttributes());
        HttpServletResponse response = servletRequestAttributes.getResponse();

        assert response != null;

        long maxAge = 31536000L;
        if (cacheExpiration > 0L) {
            maxAge = cacheTimeUnit.toSeconds(cacheExpiration);
        }

        response.setHeader("Cache-Control", "max-age=" + maxAge);
    }
}

