package com.wentnet.utils;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

public class GzipUtil {

    private static final Logger log = LoggerFactory.getLogger(GzipUtil.class);

    public GzipUtil() {
    }

    public static String compress(String uncompressStr) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        GZIPOutputStream gzip = null;

        try {
            gzip = new GZIPOutputStream(out);
            gzip.write(uncompressStr.getBytes());
            gzip.finish();
            String var3 = Base64.getEncoder().encodeToString(out.toByteArray());
            return var3;
        } catch (IOException var7) {
            var7.printStackTrace();
        } finally {
            IOUtils.closeQuietly(gzip);
            IOUtils.closeQuietly(out);
        }

        return null;
    }

    public static String uncompress(String compressedStr) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = null;
        GZIPInputStream ginzip = null;
        String decompressed = null;

        try {
            byte[] compressed = Base64.getDecoder().decode(compressedStr);
            in = new ByteArrayInputStream(compressed);
            ginzip = new GZIPInputStream(in);
            byte[] buffer = new byte[1024];

            int offset;
            while((offset = ginzip.read(buffer)) != -1) {
                out.write(buffer, 0, offset);
            }

            decompressed = out.toString();
        } catch (IOException var11) {
            var11.printStackTrace();
        } finally {
            IOUtils.closeQuietly(ginzip);
            IOUtils.closeQuietly(in);
            IOUtils.closeQuietly(out);
        }

        return decompressed;
    }
}
