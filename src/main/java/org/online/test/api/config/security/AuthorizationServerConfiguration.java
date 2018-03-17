package org.online.test.api.config.security;

import org.online.test.api.service.security.TokenStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;

@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfiguration extends AuthorizationServerConfigurerAdapter {

  private static String REALM = "api-security";

  @Value("${app.oauth.client-id}")
  private String clientId;

  @Value("${app.oauth.client-secret}")
  private String clientSecret;

  @Value("${app.oauth.access-token-validity}")
  private int accessTokenValidity;

  @Value("${app.oauth.refresh-token-validity}")
  private int refreshTokenValidity;

  @Autowired
  @Qualifier("tokenStore")
  private TokenStoreService tokenStore;

  @Autowired
  private UserApprovalHandler userApprovalHandler;

  @Autowired
  @Qualifier("authenticationManagerBean")
  private AuthenticationManager authenticationManager;

  @Override
  public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
    clients.inMemory()
        .withClient(clientId)
        .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
        .authorities("ROLE_USER")
        .scopes("read", "write", "trust")
        .secret(clientSecret)
        .accessTokenValiditySeconds(accessTokenValidity)
        .refreshTokenValiditySeconds(refreshTokenValidity);
  }

  @Override
  public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
    endpoints.tokenStore(tokenStore).userApprovalHandler(userApprovalHandler)
        .authenticationManager(authenticationManager);
  }

  @Override
  public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
    oauthServer.realm(REALM + "/client");
  }
}
