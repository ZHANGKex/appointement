package com.isep.appointement.controller.Security;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
/*            ApiResponse response = new ApiResponse(403, "Access Denied");
            response.setMessage("Access Denied");
            OutputStream out = httpServletResponse.getOutputStream();
            ObjectMapper mapper = new ObjectMapper();
            mapper.writeValue(out, response);
            out.flush();*/
        httpServletResponse.sendRedirect("/access-denied");
    }
}