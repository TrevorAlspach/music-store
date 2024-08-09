package com.example.musicstore.security.auth0;

import com.example.musicstore.entities.User;
import com.example.musicstore.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;

public class PrincipalJwtConvertor implements Converter<Jwt, AbstractAuthenticationToken> {


    private Converter<Jwt, Collection<GrantedAuthority>> jwtGrantedAuthoritiesConverter = new JwtGrantedAuthoritiesConverter();

    private UserService userService;
    private String principalClaimName = "sub";

    @Autowired
    public PrincipalJwtConvertor(UserService userService) {
        this.userService = userService;
    }
    public AbstractAuthenticationToken convert(Jwt jwt) {
        Collection<GrantedAuthority> authorities = (Collection)this.jwtGrantedAuthoritiesConverter.convert(jwt);
        // Extract claims from JWT
        String sub = jwt.getClaimAsString("sub");
        String username = jwt.getClaimAsString("preferred_username");
        String email = jwt.getClaimAsString("email");

        // Use your custom service to handle user creation or updating
        User user = userService.createOrFindUser(email, sub,username ,authorities);
        System.out.println(userService.findUserByEmail(email));

        String principalClaimValue = jwt.getClaimAsString(this.principalClaimName);

        UserPrincipal principal = new UserPrincipal(username, email, authorities);

        // Continue with the default JWT processing
        return new UserJwtAuthenticationToken(jwt, authorities, user);
    }
}
