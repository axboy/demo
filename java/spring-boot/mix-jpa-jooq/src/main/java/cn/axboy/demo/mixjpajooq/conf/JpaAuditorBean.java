package cn.axboy.demo.mixjpajooq.conf;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;

/**
 * @author zcw
 * @version 1.0.0
 * @date 2018/8/16 12:14
 */
@Configuration
public class JpaAuditorBean implements AuditorAware<String> {

    @Override
    public String getCurrentAuditor() {
        return "test-jpa";
    }
}