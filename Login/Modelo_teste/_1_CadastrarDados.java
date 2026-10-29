package Login.Modelo_teste;

import Login.Modelo_basico.Cliente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class _1_CadastrarDados {

    public static void main(String[] args) {
        // Unidade de persistencia "login"
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("login");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        // 3 Registos com os 6 campos obrigatorios: Nome, Endereco, CEP, Numero, Telefone, Observacao
        Cliente pedido1 = new Cliente(
                "Carlos Silva",
                "Av. Paulista",
                "01310-100",
                "1000",
                "(11) 98765-4321",
                "Sem cebola, carne ao ponto para mal passado"
        );

        Cliente pedido2 = new Cliente(
                "Mariana Oliveira",
                "Rua Augusta",
                "01305-000",
                "500",
                "(11) 91234-5678",
                "Adicionar molho especial e bacon extra"
        );

        Cliente pedido3 = new Cliente(
                "Roberto Martins",
                "Rua Oscar Freire",
                "01426-001",
                "250",
                "(11) 99887-6655",
                "Pao sem gluten, carne bem passada"
        );

        em.persist(pedido1);
        em.persist(pedido2);
        em.persist(pedido3);

        em.getTransaction().commit();

        System.out.println("✅ Pedidos de hamburgueria cadastrados com sucesso!");

        em.close();
        emf.close();
    }
}