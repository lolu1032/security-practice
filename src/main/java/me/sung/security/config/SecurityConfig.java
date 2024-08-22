package me.sung.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {

        return new BCryptPasswordEncoder();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((auth) -> auth
                .requestMatchers("/","login").permitAll() // 아무조건없이 다 접속가능
                .requestMatchers("/admin").hasAnyRole("ADMIN") // admin이라는 룰이 있어야 접속가능
                .requestMatchers("my/**").hasAnyRole("ADMIN","USER")
                .anyRequest().authenticated()
        );


        http.formLogin((auth)->auth.loginPage("/login")
                // 시큐리티가 클라이언트에서 로그인 데이터받으면 시큐리티가 오픈돼서 로그인처리 진행함
                .loginProcessingUrl("/loginProc")
                .permitAll()
        );

        http.csrf((auth)->auth.disable());
        return http.build();
    }

}
