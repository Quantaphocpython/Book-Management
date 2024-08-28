package com.devteria.identity.configuration;

import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
// @Component // Feign client sẽ tụ động quét bean này và add vào để sử dụng, nhưng mà ko phải lúc nào cũng cần thêm tại
// có những request sẽ được chỉa ra bên ngoài, chứ ko phải chỉ internal
// cho nên ta cần config thủ công cho từng client.
public class AuthenticationRequestInterceptor implements RequestInterceptor {
    @Override
    public void apply(RequestTemplate requestTemplate) {
        ServletRequestAttributes servletRequestAttributes =
                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        var authHeader = servletRequestAttributes.getRequest().getHeader("Authorization");
        log.info("Header: {}", authHeader);

        if (StringUtils.hasText(authHeader)) {
            requestTemplate.header("Authorization", authHeader);
            // và kể từ bây gio bat ki request nao tu feignclient deu se apply cac header nay
        }
    }
}
