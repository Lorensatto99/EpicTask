package br.com.fiap.epictask.config;

import br.com.fiap.epictask.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Autowired
    private AuthenticationService authenticationService;


    //executa a autenticação do usuario;
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authenticationService)
                .passwordEncoder(AuthenticationService.getPasswordEncoder());
    }


    //Permissoes de rota baseado na autenticação do usuário
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //Bloqueia Acesso se não estivar autenticador os endpoints : /user, /task/**
                .authorizeRequests()

                //Precisa ter acesso de administrador para acessar o diretório /user
                .antMatchers("/user")
                .hasRole("ADMIN")

                //Precisa estar autenticado para acessar /task/**
                .antMatchers("/task/**")
                .authenticated()
                .anyRequest()
                .permitAll()

                .and()

                //permitir o formulário de login e direciona para a página de login
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/task")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/")

                //Acesso ao h2-console
                .and()
                .csrf()
                .disable()
                .headers()
                .frameOptions()
                .disable();

    }
}
