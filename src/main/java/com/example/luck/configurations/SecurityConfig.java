//1
//package com.example.luck.configurations;
//
//import com.example.luck.service.CustomSuccessHandler;
//import com.example.luck.service.CustomUserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Autowired
//    CustomSuccessHandler customSuccessHandler;
//
//    @Autowired
//    CustomUserDetailsService customUserDetailsService;
//
//    @Bean
//    public static PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//        http.csrf(c -> c.disable())
//
//                .authorizeHttpRequests(request -> request.requestMatchers("/admin-page")
//                        .hasAuthority("ADMIN").requestMatchers("/user-page").hasAuthority("USER")
//                        .requestMatchers("/registration", "/css/**").permitAll()
//                        .anyRequest().authenticated())
//
//                .formLogin(form -> form.loginPage("/login").loginProcessingUrl("/login")
//                        .successHandler(customSuccessHandler).permitAll())
//
//                .logout(form -> form.invalidateHttpSession(true).clearAuthentication(true)
//                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                        .logoutSuccessUrl("/login?logout").permitAll());
//
//        return http.build();
//
//    }
//
//    @Autowired
//    public void configure (AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
//    }

//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http
//                .authorizeRequests()
//                .requestMatchers("/tasks").hasRole("USER")  // Только пользователи с ролью USER или ADMIN могут просматривать задачи
//                .anyRequest().authenticated()
//                .and();
//                //.formLogin()
//                //.permitAll();
//    }


//}

//package com.example.luck.configurations;
//
//import com.example.luck.service.CustomSuccessHandler;
//import com.example.luck.service.CustomUserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Autowired
//    private CustomSuccessHandler customSuccessHandler;
//
//    @Autowired
//    private CustomUserDetailsService customUserDetailsService;
//
//    @Bean
//    public static PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/admin-page").hasAuthority("ADMIN")
//                        .requestMatchers("/user-page").hasAuthority("USER")
//                        .requestMatchers("/admin/**").hasRole("ADMIN")
//                        .requestMatchers("/tasks/add", "/categories/**").hasRole("ADMIN")
//                        .requestMatchers("/tasks/**").hasAnyRole("USER", "ADMIN")
//                        .requestMatchers("/profile/**").authenticated()
//                        .requestMatchers("/registration", "/css/**").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .formLogin(form -> form
//                        .loginPage("/login")
//                        .loginProcessingUrl("/login")
//                        .successHandler(customSuccessHandler)
//                        .defaultSuccessUrl("/tasks")
//                        .permitAll()
//                )
//                .logout(logout -> logout
//                        .invalidateHttpSession(true)
//                        .clearAuthentication(true)
//                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                        .logoutSuccessUrl("/login?logout")
//                        .permitAll()
//                );
//
//        return http.build();
//    }
//
//    @Autowired
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(customUserDetailsService)
//                .passwordEncoder(passwordEncoder());
//    }
//}

//3
//package com.example.luck.configurations;
//
//import com.example.luck.service.CustomSuccessHandler;
//import com.example.luck.service.CustomUserDetailsService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
//
//@Configuration
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Autowired
//    private CustomSuccessHandler customSuccessHandler;
//
//    @Autowired
//    private CustomUserDetailsService customUserDetailsService;
//
//    @Bean
//    public static PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable()
//                .authorizeHttpRequests()
//                .requestMatchers("/admin-page/**").hasAuthority("ADMIN")  // Restrict to admin role
//                .requestMatchers("/user-page/**").hasAuthority("USER")   // Restrict to user role
//                .requestMatchers("/registration", "/css/**").permitAll() // Allow public access to these paths
//                .anyRequest().authenticated()                            // Require authentication for all other requests
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .loginProcessingUrl("/login")
//                .successHandler(customSuccessHandler)
//                .permitAll()
//                .and()
//                .logout()
//                .invalidateHttpSession(true)
//                .clearAuthentication(true)
//                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//                .logoutSuccessUrl("/login?logout")
//                .permitAll();
//
//        return http.build();
//    }
//
//    @Autowired
//    public void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth.userDetailsService(customUserDetailsService).passwordEncoder(passwordEncoder());
//    }
//}

//second version
package com.example.luck.configurations;

import com.example.luck.service.CustomSuccessHandler;
import com.example.luck.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomSuccessHandler customSuccessHandler;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    // Определяем PasswordEncoder как Bean
    @Bean
    public static PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Настройка SecurityFilterChain для защиты маршрутов
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Для тестов отключаем CSRF, в боевом окружении настройте!
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/admin/**").hasAuthority("ADMIN") // Админский доступ
                        .requestMatchers("/user/**").hasAuthority("USER")   // Пользовательский доступ
                        .requestMatchers("/registration", "/css/**", "/js/**", "/images/**").permitAll() // Открытые ресурсы
                        .anyRequest().authenticated()) // Остальные запросы требуют авторизации
                .formLogin(form -> form
                        .loginPage("/login") // Кастомная страница логина
                        .loginProcessingUrl("/login") // Обработчик логина
                        .successHandler(customSuccessHandler) // Успешный логин
                        .permitAll())
                .logout(logout -> logout
                        .invalidateHttpSession(true) // Удаление сессии
                        .clearAuthentication(true)   // Очистка аутентификации
                        .logoutRequestMatcher(new AntPathRequestMatcher("/logout")) // Маршрут для логаута
                        .logoutSuccessUrl("/login?logout") // Успешный логаут
                        .permitAll());

        return http.build();
    }

    // Конфигурация AuthenticationManager для работы с CustomUserDetailsService
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class)
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder())
                .and()
                .build();
    }
}
