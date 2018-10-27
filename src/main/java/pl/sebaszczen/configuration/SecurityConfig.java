package pl.sebaszczen.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import pl.sebaszczen.services.UserService;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity //włączamy ustawienia bezpieczeństwa
public class SecurityConfig extends WebSecurityConfigurerAdapter {  //rozszerzany klasę o WebSecurityConfigurerAdapter
    @Autowired
    private UserService userService;
    @Autowired
    private DataSource dataSource;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    private String[] permitAll = {
            "/css/**", "/webjars/**", "/menu", "/registration", "/user/**", "/js/**"
            , "/fonts/**", "/images/**", "/login/**", "/activate-account/**", "/sw", "/swagger-ui.html/**", "/user/**"
            , "/v2/api-docs/**", "/configuration/ui/**", "/swagger-resources/**", "/configuration/security/**", "/swagger-ui.html/**", "/webjars/**"};//swagger

    //umozliwia konfiguracje uslug szczegolow uzytkownika
    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userService)
                .passwordEncoder(passwordEncoder());
//        auth
//                .inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER");
    }

    //duzo domyslnych ustawien spring security
    //configureGlobal

    //pozwala na konfiguracje sposobu zabezpieczania zadan za pomoca interceptorow

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //nadpisac domyslne ustawienie jak sie logowac/wylogowac, filtry, odpornosc na ataki
        http
                .csrf().disable()
                .authorizeRequests() //authorizeRequest() i anyRequest() ustawia konieczność uwierzytelniania wszystkich zadan http przychodzacych do aplikacji, rowniez ustawia Spring security aby prowadzil do formularza logowania
                .antMatchers(permitAll).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/login")
                .permitAll()
                .successHandler(loginSuccessHandler()).failureHandler(authenticationFailureHandler())
                //po logowaniu sa dwa wyjscia-udalo sie zalogowac lub nie tutaj nastepuje obsluga tych dwoch wydarzen
                .and().logout().permitAll()
                .and().rememberMe().tokenRepository(persistentTokenRepository());
    }

    private AuthenticationFailureHandler authenticationFailureHandler() {
        return (request, response, authentication) -> {
            if (authentication.getMessage().equals("User is disabled")) {
                response.sendRedirect("/login?disabled");
            } else {
                response.sendRedirect("/login?error");
            }
        };
//                request.getUserPrincipal().getName();  response.sendRedirect( "/logged");
    }

    private AuthenticationSuccessHandler loginSuccessHandler() {
        //poniewaz AuthenticationSuccessHandler ma tylko jedna metode mozna uzyc lambda
        //parametry tej metody to:request,response,authentication
        return (request, response, authentication) -> response.sendRedirect("/logged");
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository() {
        final JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }
}
