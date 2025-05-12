package med.voll.api.infra.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class SecurityFilter extends OncePerRequestFilter { // Classe extendida garante que um filtro seja executado apenas uma vez por requisição

    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        var tokenJWT = recuperarToken(request); // Recuperar o token

        var subject = tokenService.getSubject(tokenJWT); // Validar o token (ver se está certo e recuperar login)

        filterChain.doFilter(request, response); // Sem a linha abaixo, ele não responderia a requisição, apenas seria chamado, mas sem retornar os valores no json
    }

    private String recuperarToken(HttpServletRequest request) {
        var authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader == null) {
            throw new RuntimeException("TOKEN NÃO ENVIADO NO CABEÇALHO AUTHORIZATION");
        }

        return authorizationHeader.replace("Bearer ", "");
    }
}
