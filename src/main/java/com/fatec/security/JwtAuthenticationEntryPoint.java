package com.fatec.security;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
	
	/**
	 * Este método é responsável para verificar se o usuário pode ou não acessar determinada rota criada,
	 * caso ele não tenha a autorização o JWT válido ele retorna uma excessão para o WebConfig e uma mensagem. 
	 */
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException {
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Acesso negado. Você deve estar autenticado no sistema para acessar a URL solicitada.");
	}

}