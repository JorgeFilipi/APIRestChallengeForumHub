package br.com.alura.ForumHub.controller;

import br.com.alura.ForumHub.domain.autores.Autor;
import br.com.alura.ForumHub.domain.autores.AutorRepository;
import br.com.alura.ForumHub.domain.autores.DadosAutenticacao;
import br.com.alura.ForumHub.domain.autores.DadosRegistroToken;
import br.com.alura.ForumHub.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {

    @Autowired
    private AutorRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;


    @PostMapping()
    public ResponseEntity efetuarLogin(@RequestBody @Valid DadosAutenticacao dados) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(dados.email(), dados.senha());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        var token = tokenService.generateToken((Autor) auth.getPrincipal());
        return ResponseEntity.ok(new DadosRegistroToken(token));
    }

}
