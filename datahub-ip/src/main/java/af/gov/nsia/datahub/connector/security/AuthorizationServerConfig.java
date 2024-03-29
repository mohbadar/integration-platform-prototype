/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package af.gov.nsia.datahub.connector.security;

import af.gov.nsia.datahub.connector.util.ParamConstant;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;

/**
 *
 * @author Dell
 */
@Configuration
@EnableAuthorizationServer
public class AuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    private static String REALM = "CRM_REALM";

    @Autowired
    private DataSource dataSource;

    @Autowired
    private TokenStore tokenStore;

    @Autowired
    private JwtAccessTokenConverter jwtTokenEnhancer;

    @Autowired
    private UserApprovalHandler userApprovalHandler;

    @Autowired
    private UserDetailsService customUserDetailsService;

    /**
     * Setting up the endpointsconfigurer authentication manager. The
     * AuthorizationServerEndpointsConfigurer defines the authorization and
     * token endpoints and the token services.
     *
     * @param endpoints
     * @throws Exception
     */
//    @Override
//    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
////        endpoints
////                .authenticationManager(authenticationManager);
//
//        endpoints.tokenStore(tokenStore).userApprovalHandler(userApprovalHandler)
//                .authenticationManager(authenticationManager)
//                .userDetailsService(customUserDetailsService);
//    }
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(tokenStore).tokenEnhancer(jwtTokenEnhancer).userApprovalHandler(userApprovalHandler)
                .authenticationManager(authenticationManager)
                .userDetailsService(customUserDetailsService);
    }

    /**
     * Setting up the clients with a clientId, a clientSecret, a scope, the
     * grant types and the authorities.
     *
     * @param clients
     * @throws Exception
     */
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients
                .inMemory()
                .withClient(ParamConstant.TRUSTED_CLIENT_ID)
                .authorizedGrantTypes(ParamConstant.OAUTH_AUTHORIZATION_GRANT_TYPE)
                .authorities(ParamConstant.OAUTH_SECURITY_AUTHORITIES)
                .scopes(ParamConstant.OAUTH_SECURITY_SCOPE)
                .resourceIds(ParamConstant.OAUTH_SECURITY_RESOURCE_ID)
                .accessTokenValiditySeconds(ParamConstant.ACCESS_TOKEN_VALDITION_TIME)
                .secret(passwordEncoder.encode(ParamConstant.TRUSTED_CLIENT_SECRET))
                .resourceIds(ParamConstant.RESOURCE_ID);
//        clients.jdbc(dataSource);
    }

    /**
     * We here defines the security constraints on the token endpoint. We set it
     * up to isAuthenticated, which returns true if the user is not anonymous
     *
     * @param security the AuthorizationServerSecurityConfigurer.
     * @throws Exception
     */
//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
//        security
//                .checkTokenAccess("isAuthenticated()");
//    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        oauthServer.realm(REALM);
    }

    
    		/**
		 * Obtaining information about the current user
		 */
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        System.out.println("logged in user name:: " + authentication.getName());
}
