package com.mindhub.homebanking.configurations;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@EnableWebSecurity

@Configuration

class WebAuthorization extends WebSecurityConfigurerAdapter {

    @Override

    protected void configure(HttpSecurity http) throws Exception {



        http.authorizeRequests()

                .antMatchers("/admin/**").hasAuthority("ADMIN")

                 //  .antMatchers("/web/**").hasAuthority("CLIENT")
                //   .antMatchers("/Web/**").hasAuthority("CLIENT")
                //   .antMatchers("/wEb/**").hasAuthority("CLIENT")
                //    .antMatchers("/weB/**").hasAuthority("CLIENT")
                //    .antMatchers("/WEb/**").hasAuthority("CLIENT")
                //    .antMatchers("/WEB/**").hasAuthority("CLIENT")
                //   .antMatchers("/wEB/**").hasAuthority("CLIENT")
                .antMatchers("/web/accounts.html","/web/account.html","/web/cards.html", "/web/createCards.html").hasAuthority("CLIENT")
                .antMatchers("/web/index.html","/web/login.html","/web/register.html").permitAll()
                .antMatchers(HttpMethod.POST, "/api/clients").permitAll()
                .antMatchers(HttpMethod.POST, "/api/clients/current/**").hasAuthority("CLIENT");



        http.formLogin()

                .usernameParameter("email")

                .passwordParameter("password")

                .loginPage("/api/login");


        http.logout().logoutUrl("/api/logout").deleteCookies("JSESSIONID");

        //csrf = cross site request forgery ---------- es para confirmar el formulario del endpoint que le paso y no de
        // un formulario externo (para evitar hackeos con formularios externos)

        http.csrf().disable(); // desactiva la comprobacion de tokens



        http.headers().frameOptions().disable(); // desactivamos frameOptions para poder acceder al h2-console


        //excepcion se ejecuta cuando intentamos entrar a un punto en el cual no estamos autenticados
        http.exceptionHandling().authenticationEntryPoint((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        // si el usuario no esta autenticado, envia una respuesta de autenticacion fallida
        http.formLogin().successHandler((req, res, auth) -> clearAuthenticationAttributes(req));

        // si el login, es exitoso, te elimina las banderas que te pidan por autenticarse
        http.formLogin().failureHandler((req, res, exc) -> res.sendError(HttpServletResponse.SC_UNAUTHORIZED));

        // si se desloguea exitosamente, envia una respuesta exitosa
        http.logout().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler());

    }

    private void clearAuthenticationAttributes(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if (session != null) {

            session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);

        }

    }
    }


// esto es mi json id

