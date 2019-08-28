package com.fatec.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fatec.security.JwtAuthenticationEntryPoint;
import com.fatec.security.filter.JwtAuthenticationTokenFilter;


/**
 * 
 * @author aasantos9
 * 
 * a Anotação @EnableWebSecurity cria um filtro e sempre que for executado algum método 
 * ele ira passar pelo @Bean JwtAuthenticationTokenFilter
 * 
 * a Anotação @EnableGlobalMethodSecurity prePostEnabled sempre que houver um post ele habilita metodos 
 * de security a mais.
 *
 */

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    
	/**
	 * cria uma instancia da classe JwtAuthenticationEntryPoint com auto injeção de dependencia,
	 * ou seja o mesmo que JwtAuthenticationEntryPoint unauthorizedHandler = new JwtAuthenticationEntryPoint(); 
	 */
	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;

	/**
	 * cria uma instancia da classe UserDetailsService com auto injeção de dependencia,
	 * ou seja o mesmo que UserDetailsService userDetailsService = new UserDetailsService(); 
	 */
	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired//anotação do spring para auto injeção de dependencia
	public void configureAuthentication(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
		authenticationManagerBuilder.userDetailsService(this.userDetailsService).passwordEncoder(passwordEncoder());
	}
	
	 @Bean
	    @Override
	    public AuthenticationManager authenticationManagerBean() throws Exception {
	        return super.authenticationManagerBean();
	    }
	
	/**
	 * Este método retonar uma instancia de BCryptPasswordEncoder, responsável para criar hash das senhas.
	 * e a injeção de dependencia @Bean serve para possibilitar a criação de componentes encapsulados que, 
	 * seguindo alguns padrões da implementação java.io.Serializable e se deve acessar dos através get e set
	 * 
	 * @return BCryptPasswordEncoder
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public JwtAuthenticationTokenFilter authenticationTokenFilterBean() throws Exception {
		return new JwtAuthenticationTokenFilter();
	}
	
	/**
	 * ese método sobrescreve as configurações do WebSecurityConfigurerAdapter
	 * de acordo com as configurações que estiver abaixo será executado
	 */
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		/**
		 * HttpSecurity desabilita o cross-site request ou mais conhecido como ataque do clique e 
		 * adiciona um execption para autenticação com a classe JwtAuthenticationEntryPoint
		 * e cria uma sessão com as politicas HTTPSession do SpringSecurity e depois autoriza as rotas públicas
		 * e fora essas rotas todas as outras devem estar autenticada de acordo com  os filtros adicionados.
		 */
		httpSecurity.csrf().disable().exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
				.antMatchers("/auth/**", "/swagger-resources/**", "/configuration/security", "/swagger-ui.html", "/webjars/**")
				.permitAll().anyRequest().authenticated();
		httpSecurity.addFilterBefore(authenticationTokenFilterBean(), UsernamePasswordAuthenticationFilter.class);
		httpSecurity.headers().cacheControl();
	}
}
