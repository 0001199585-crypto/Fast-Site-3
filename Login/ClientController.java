package Login;

import Login.Modelo_basico.Cliente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cadastros")
@CrossOrigin(origins = "*")
public class ClientController {

    @Autowired
    private ClienteRepository clienteRepository;

    // POST /api/cadastros - Inserção de novos cadastros/pedidos
    @PostMapping
    public ResponseEntity<Cliente> criarCadastro(@RequestBody Cliente cliente) {
        Cliente novoCliente = clienteRepository.save(cliente);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCliente);
    }

    // GET /api/cadastros - Consulta de todos os registros
    @GetMapping
    public ResponseEntity<List<Cliente>> listarCadastros() {
        return ResponseEntity.ok(clienteRepository.findAll());
    }

    // GET /api/cadastros/{id} - Consulta individual por ID
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarPorId(@PathVariable Long id) {
        return clienteRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
}