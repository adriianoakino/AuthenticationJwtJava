package com.fatec.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fatec.dto.UsuarioDto;
import com.fatec.entities.Usuario;
import com.fatec.response.Response;
import com.fatec.services.UsuarioService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class UsuarioController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody UsuarioDto signUpRequest) {
        if(usuarioService.existsByUsername(signUpRequest.getUsername())) {
            return new ResponseEntity<String>("Falha -> Username já está sendo usado !",
                    HttpStatus.BAD_REQUEST);
    }
 
       
        Usuario userUsuario = usuarioService.saveUsuario(signUpRequest);
        
        Response response = new Response();
        response.setData(userUsuario);
        
        return ResponseEntity.ok(response);
    }
}
