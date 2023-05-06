package by.bntu.fitr.profilerclient.api.filter;

import by.bntu.fitr.profilerclient.api.exception.CommonException;
import by.bntu.fitr.profilerclient.api.service.UserService;
import by.bntu.fitr.profilerclient.core.constant.CommonConstant;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.servlet.HandlerExceptionResolver;

import java.io.IOException;

/*
@Component
public class AuthorizationFilter extends OncePerRequestFilter {
    private HandlerExceptionResolver handlerExceptionResolver;
    private UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authenticationHeaderParam = request.getHeader(CommonConstant.AUTHORIZATION_HEADER_NAME);
        if (authenticationHeaderParam == null || authenticationHeaderParam.isEmpty() || !userService.isValidUser(authenticationHeaderParam)) {
            handlerExceptionResolver.resolveException(request, response, null, new CommonException("Permission Denied"));
        } else {
            filterChain.doFilter(request, response);
        }
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        String path = request.getServletPath();
        return path.equals("/api/v1/users/login");
    }

    @Autowired
    @Qualifier("handlerExceptionResolver")
    public void setHandlerExceptionResolver(HandlerExceptionResolver handlerExceptionResolver) {
        this.handlerExceptionResolver = handlerExceptionResolver;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}

 */
