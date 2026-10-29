package Login;

import Login.Modelo_basico.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (usuarioRepository.findByUsuario("admin").isEmpty()) {
            Usuario admin = new Usuario();
            admin.setUsuario("admin");
            admin.setSenha(passwordEncoder.encode("123456"));
            usuarioRepository.save(admin);
        }
    }
}
