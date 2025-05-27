package com.example.newsfeed.config;


import com.googlecode.jsonrpc4j.DefaultHttpStatusCodeProvider;
import com.googlecode.jsonrpc4j.HttpStatusCodeProvider;

import jakarta.servlet.http.HttpServletResponse;

/**
 * Кастомная реализация провайдера HTTP кодов
 *
 * Created by Aleksey Danilov on 30.11.16.
 *
 * @see DefaultHttpStatusCodeProvider
 */
public class JsonRpcHttpStatusCodeProvider implements HttpStatusCodeProvider {

    @Override
    public int getHttpStatusCode(int resultCode) {

        // Всегда успех
        return HttpServletResponse.SC_OK;
    }

    @Override
    public Integer getJsonRpcCode(int httpStatusCode) {

        return 0;
    }
}

