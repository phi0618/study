package com.iusername.base;

import android.text.TextUtils;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        String url = "www.baidu.com?word=name";
        Map<String, String> map = urlToParam(url);
        System.out.println(map.size());

        assertEquals(4, 2 + 2);
    }

    /**
     * 从url中获取参数
     *
     * @param url
     * @return
     */
    private Map<String, String> urlToParam(String url) {
//        if (TextUtils.isEmpty(url) || url.contains("?")) {
//            return null;
//        }
        int index = url.indexOf('?');
        String paramsString = url.substring(index + 1);
        String[] params = paramsString.split("&");

        Map<String, String> map = new HashMap<>(params.length);
        String[] p;
        for (String param : params) {
            p = param.split("=");
            try {
                map.put(p[0], URLDecoder.decode(p[1], "utf-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

}