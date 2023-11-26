package com.gucardev.springshellexample.config;

import java.util.Arrays;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@RequiredArgsConstructor
public class SpringSecurityConfig {

  @Bean
  public UserDetailsService users() {
    UserDetails user =
        User.builder()
            .username("user")
            .password("pass")
            .password(passwordEncoder().encode("pass"))
            .roles("USER")
            .build();
    UserDetails admin =
        User.builder()
            .username("admin")
            .password(passwordEncoder().encode("pass"))
            .roles("USER", "ADMIN")
            .authorities("create", "read")
            .build();
    UserDetails mod =
        User.builder()
            .username("mod")
            .password(passwordEncoder().encode("pass"))
            .roles("MOD")
            .build();
    return new InMemoryUserDetailsManager(user, admin, mod);
  }

  @Bean
  public BCryptPasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public AuthenticationManager authenticationManager(
      UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
    DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
    authenticationProvider.setUserDetailsService(userDetailsService);
    authenticationProvider.setPasswordEncoder(passwordEncoder);

    ProviderManager authenticationManager =
        new ProviderManager(Arrays.asList(authenticationProvider));
    return authenticationManager;
  }
}
