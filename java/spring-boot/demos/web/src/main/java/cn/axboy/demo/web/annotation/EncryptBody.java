package cn.axboy.demo.web.annotation;

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface EncryptBody {

    /**
     * aes加解密秘钥
     */
    String key() default "123456";
}
