package br.com.alura.ForumHub.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthenticationController {


//    @PostMapping()
//    public ResponseEntity efetuarLogin(@RequestBody DadosAutenticacao dados) {
//        var usernamePassword = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
//        var auth = this.authenticationManager.authenticate(usernamePassword);
////        var token = tokenService.generateToken((Usuario) auth.getPrincipal());
//        return ResponseEntity.ok(new DadosRegistroToken(token));
//    }

//    @PostMapping("/register")
//    public ResponseEntity efetuarRegistro(@RequestBody @Valid DadosRegistro dados) {
//        if(this.repository.findByLogin(dados.login()) != null) return ResponseEntity.badRequest().build();
//        String encryptedPassword = new BCryptPasswordEncoder().encode(dados.senha());
//        Usuario newUser = new Usuario(dados.login(), encryptedPassword, dados.role());
//        this.repository.save(newUser);
//        return ResponseEntity.ok().build();
//    }
}
