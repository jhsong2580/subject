package subject.blog.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class UrlEncoder {

    public static String encode(String val, String charset) {
        try {
            if (val.trim() == "") {
                return "%20";
            }
            return URLEncoder.encode(val, charset);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

    public static String decode(String val, String charset) {
        try {
            String decodeResult = URLDecoder.decode(val, charset);
            if (decodeResult == null) {
                return "";
            }
            return decodeResult;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}
