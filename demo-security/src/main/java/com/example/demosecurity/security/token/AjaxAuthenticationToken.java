package com.example.demosecurity.security.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;

public class AjaxAuthenticationToken extends AbstractAuthenticationToken {

        private String principal;
        private String credentials;

        public AjaxAuthenticationToken(String principal, String credentials) {
            super(null);
            this.principal = principal;
            this.credentials = credentials;
            setAuthenticated(false);
        }

        public AjaxAuthenticationToken(Object principal, Object credentials, String secretKey) {
            super(null);
            this.principal = principal.toString();
            this.credentials = credentials.toString();
            setAuthenticated(false);
        }

        @Override
        public Object getCredentials() {
            return credentials;
        }

        @Override
        public Object getPrincipal() {
            return principal;
        }
}
