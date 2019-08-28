package com.fatec.security.filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import com.fatec.security.utils.JwtTokenUtil;

public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

	private static final String AUTH_HEADER = "Authorization";
	private static final String BEARER_PREFIX = "Bearer ";
	
	/**
	 * cria uma instancia da classe UserDetailsService com auto injeção de dependencia,
	 * ou seja o mesmo que UserDetailsService userDetailsService = new UserDetailsService(); 
	 */
	@Autowired
    private UserDetailsService userDetailsService;
	
	/**
	 * cria uma instancia da classe JwtTokenUtil com auto injeção de dependencia,
	 * ou seja o mesmo que JwtTokenUtil jwtTokenUtil = new JwtTokenUtil(); 
	 */
	@Autowired
    private JwtTokenUtil jwtTokenUtil;

	
	/**
	 * Este método é responsável por criar um filtro para quando o @Bean JwtAuthenticationTokenFilter 
	 * criado na classe WebSecurityConfig executar a cada autenticação
	 */
	@Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String token = request.getHeader(AUTH_HEADER);
        
        if (token != null && token.startsWith(BEARER_PREFIX)) {
        	token = token.substring(7);
        }
        
        String username = jwtTokenUtil.getUsernameFromToken(token);

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
        	
            UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
            
            if (jwtTokenUtil.tokenValido(token)) {
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }

        chain.doFilter(request, response);
    }

}