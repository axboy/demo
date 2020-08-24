package cn.axboy.demo.oauth2.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.store.InMemoryTokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 * @author zcw
 * @version 1.0
 * @date 2020/5/19 17:33
 */
@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey("123456");
        return converter;
    }


    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //super.configure(security);
        security.tokenKeyAccess("permitAll()")          //允许所有人请求令牌
                .checkTokenAccess("isAuthenticated()")  //允许已验证的客户端请求check_token
                .allowFormAuthenticationForClients();
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        //super.configure(clients);
        clients.inMemory().withClient("clientId")
                .authorizedGrantTypes("authorization_code", "password", "refresh_token")
                .scopes("all")
                .secret("clientSecret")
                .redirectUris("http://localhost:8088");         //随便填的，重定向只是要地址
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        //super.configure(endpoints);
        endpoints.tokenStore(new InMemoryTokenStore())
                .accessTokenConverter(accessTokenConverter())
                .authenticationManager(authenticationManager)
                .reuseRefreshTokens(false);
    }
}
