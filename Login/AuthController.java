package Login; // Ajuste o pacote conforme a estrutura do seu projeto

import Login.Modelo_basico.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // 1. Endpoint de Autenticação (Login)
    @PostMapping("/login")
    public ResponseEntity<?> autenticar(@RequestBody Map<String, String> loginData) {
        String usuario = loginData.get("usuario");
        String senha = loginData.get("senha");

        Optional<Usuario> userOpt = usuarioRepository.findByUsuario(usuario);

        if (userOpt.isPresent() && passwordEncoder.matches(senha, userOpt.get().getSenha())) {
            return ResponseEntity.ok(Map.of("message", "Login efetuado com sucesso!"));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("message", "Usuário ou senha incorretos."));
    }

    // 2. NOVO ENDPOINT: Criar Novo Login / Cadastrar Usuário
    @PostMapping("/usuarios")
    public ResponseEntity<?> criarUsuario(@RequestBody Map<String, String> userData) {
        String usuario = userData.get("usuario");
        String senha = userData.get("senha");

        if (usuario == null || usuario.trim().isEmpty() || senha == null || senha.trim().isEmpty()) {
            return ResponseEntity.badRequest().body(Map.of("message", "Preencha o usuário e a senha."));
        }

        // Verifica se o login já existe no banco de dados
        if (usuarioRepository.findByUsuario(usuario.trim()).isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body(Map.of("message", "Este nome de usuário já está cadastrado."));
        }

        // Salva com a senha encriptada em BCrypt
        Usuario novoUsuario = new Usuario();
        novoUsuario.setUsuario(usuario.trim());
        novoUsuario.setSenha(passwordEncoder.encode(senha));

        usuarioRepository.save(novoUsuario);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(Map.of("message", "Login criado com sucesso!"));
    }
}