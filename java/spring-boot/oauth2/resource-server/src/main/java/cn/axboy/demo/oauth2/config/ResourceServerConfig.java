package cn.axboy.demo.oauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.error.OAuth2AccessDeniedHandler;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/5/19 17:59
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("123456");
        return converter;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        //super.configure(resources);
        RemoteTokenServices tokenServices = new RemoteTokenServices();
        tokenServices.setAccessTokenConverter(accessTokenConverter());
        tokenServices.setRestTemplate(restTemplate());
        tokenServices.setCheckTokenEndpointUrl("http://auth.local:8080/oauth/check_token");
        tokenServices.setClientId("clientId");
        tokenServices.setClientSecret("clientSecret");

        resources.tokenStore(new JwtTokenStore(accessTokenConverter())).stateless(true);
        resources.tokenServices(tokenServices).stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        //super.configure(http);
        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                .and()
                .requestMatchers().anyRequest()
                .and()
                .anonymous()
                .and()
                .authorizeRequests().antMatchers("/o2/**").authenticated()
                .and()
                .exceptionHandling().accessDeniedHandler(new OAuth2AccessDeniedHandler());
    }

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setErrorHandler(new DefaultResponseErrorHandler() {
            @Override
            public void handleError(ClientHttpResponse response) throws IOException {
                //ignore 400
                if (response.getRawStatusCode() != 400) {
                    super.handleError(response);
                }
            }
        });
        return restTemplate;
    }
}
