package cn.axboy.demo.web.config;

import cn.axboy.demo.web.annotation.EncryptBody;
import cn.axboy.demo.web.util.AesUtil;
import cn.axboy.demo.web.util.CodecUtil;
import cn.axboy.demo.web.util.JsonUtil;
import cn.axboy.demo.web.util.StreamUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@Slf4j
@Component
public class DecodeArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        log.debug("Check supportsParameter");
        EncryptBody encrypt = parameter.getMethod().getAnnotation(EncryptBody.class);
        Class<?>[] params = parameter.getMethod().getParameterTypes();
        if (encrypt != null && params.length != 1) {
            log.error("加密接口参数错误: {}", parameter.getMethod().getName());
            throw new RuntimeException("加密接口参数错误");
        }
        if (encrypt == null) {
            encrypt = parameter.getParameterAnnotation(EncryptBody.class);
        }
        return encrypt != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        log.debug("Check resolveArgument");
        Class<?>[] params = parameter.getMethod().getParameterTypes();
        EncryptBody encrypt = parameter.getMethod().getAnnotation(EncryptBody.class);
        if (encrypt == null) {
            encrypt = parameter.getParameterAnnotation(EncryptBody.class);
        }

        Class<?> targetClass = params[0];
        String body = CodecUtil.decodeURL(StreamUtil.getString(((ServletWebRequest) webRequest).getRequest().getInputStream()));
        if (targetClass == String.class) {
            return body;
        }

        if (body == null || body.trim().length() == 0) {
            return null;
        }
        String json = AesUtil.decryptHexString(body, encrypt.key());
        return JsonUtil.fromJson(json, targetClass);
    }
}
