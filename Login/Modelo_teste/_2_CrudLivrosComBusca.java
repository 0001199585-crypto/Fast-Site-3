package Login.Modelo_teste;

import Login.Modelo_basico.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import java.util.List;

public class _2_CrudLivrosComBusca {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("login");
        EntityManager em = emf.createEntityManager();

        String termo = "Carlos"; // Termo de pesquisa (nome ou endereco)

        // Consulta JPQL adaptada para buscar clientes/pedidos
        String jpql = "SELECT c FROM Cliente c WHERE LOWER(c.nome) LIKE LOWER(:termo) OR LOWER(c.endereco) LIKE LOWER(:termo)";
        TypedQuery<Cliente> query = em.createQuery(jpql, Cliente.class);
        query.setParameter("termo", "%" + termo + "%");

        List<Cliente> pedidos = query.getResultList();

        System.out.println("--- RESULTADO DA BUSCA DE PEDIDOS ---");
        for (Cliente p : pedidos) {
            System.out.println("ID: " + p.getId());
            System.out.println("Cliente: " + p.getNome());
            System.out.println("Telefone: " + p.getTelefone());
            System.out.println("Endereço: " + p.getEndereco() + ", Nº " + p.getNumero() + " (CEP: " + p.getCep() + ")");
            System.out.println("Observação do Hambúrguer: " + p.getObservacao());
            System.out.println("-------------------------------------");
        }

        em.close();
        emf.close();
    }
}