package org.testcloud.modules.provider.DemoProvider.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class CommonConfig {

    @ExceptionHandler
    public void exceptionHandler(ProviderException e, HttpServletResponse response) {
        try {
            System.out.println(e.getMessage());
            response.getWriter().print("error");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

}
