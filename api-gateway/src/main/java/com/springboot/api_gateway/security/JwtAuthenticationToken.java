//package com.springboot.api_gateway.security;
//
//import org.springframework.security.authentication.AbstractAuthenticationToken;
//import org.springframework.security.core.GrantedAuthority;
//
//import java.util.Collection;
//
//public class JwtAuthenticationToken extends AbstractAuthenticationToken {
//
//    private final Object principal;
//    private final Object credentials;
//
//    public JwtAuthenticationToken(Object principal, Object credentials, Collection<GrantedAuthority> authorities) {
//        super(authorities);
//        this.principal = principal;
//        this.credentials = credentials;
//        super.setAuthenticated(true); // Must use constructor with 'authorities'
//    }
//
//    @Override
//    public Object getCredentials() {
//        return credentials;
//    }
//
//    @Override
//    public Object getPrincipal() {
//        return principal;
//    }
//}
//
