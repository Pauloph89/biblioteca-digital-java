import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {

        // ===== ETAPA 1 — Criar a biblioteca =====
        Biblioteca biblioteca = new Biblioteca();

        // ===== ETAPA 2 — Cadastrar usuários =====
        System.out.println("\n===== CADASTRANDO USUÁRIOS =====");
        Aluno ana = new Aluno("U001", "Ana", "ana@email.com");
        Professor carlos = new Professor("U002", "Carlos", "carlos@email.com");
        biblioteca.cadastrarUsuario(ana);
        biblioteca.cadastrarUsuario(carlos);

        // ===== ETAPA 3 — Cadastrar materiais =====
        System.out.println("\n===== CADASTRANDO MATERIAIS =====");
        Livro livro1 = new Livro("M001", "Java Básico", 2020, 2, "João Silva");
        Livro livro2 = new Livro("M002", "Estruturas de Dados", 2019, 1, "Maria Souza");
        Revista revista = new Revista("M003", "Revista Tech", 2023, 1, "Edição 42");
        Ebook ebook = new Ebook("M004", "POO em Java", 2022, 3, "PDF", 5.2);
        biblioteca.cadastrarMaterial(livro1);
        biblioteca.cadastrarMaterial(livro2);
        biblioteca.cadastrarMaterial(revista);
        biblioteca.cadastrarMaterial(ebook);

        // ===== ETAPA 4 — Listar cadastros iniciais =====
        biblioteca.listarUsuarios();
        biblioteca.listarMateriais();

        // ===== ETAPA 5 — Realizar empréstimos válidos =====
        System.out.println("\n===== REALIZANDO EMPRÉSTIMOS =====");
        LocalDate hoje = LocalDate.now();
        biblioteca.realizarEmprestimo("U001", "M001", hoje);
        biblioteca.realizarEmprestimo("U001", "M002", hoje);
        biblioteca.realizarEmprestimo("U001", "M003", hoje);
        biblioteca.realizarEmprestimo("U002", "M004", hoje);

        // ===== ETAPA 6 — Tentativa inválida =====
        System.out.println("\n===== TENTATIVA INVÁLIDA =====");
        try {
            // Ana já tem 3 empréstimos — limite atingido!
            biblioteca.realizarEmprestimo("U001", "M004", hoje);
        } catch (RuntimeException e) {
            System.out.println("ERRO ESPERADO: " + e.getMessage());
        }

        // ===== ETAPA 7 — Listar empréstimos em andamento =====
        biblioteca.listarEmprestimosEmAndamento();

        // ===== ETAPA 8 e 9 — Devoluções e multas =====
        System.out.println("\n===== REGISTRANDO DEVOLUÇÕES =====");

        // Devolução COM atraso — Ana devolveu 5 dias depois do prazo
        LocalDate devolucaoComAtraso = hoje.plusDays(
            ana.getPrazoDevolucaoDias() + 5
        );
        biblioteca.registrarDevolucao("E1", devolucaoComAtraso);

        // Devolução SEM atraso — Carlos devolveu hoje
        biblioteca.registrarDevolucao("E4", hoje);

        // ===== ETAPA 9 — Mostrar multas =====
        System.out.println("\n===== MULTAS =====");
        Emprestimo emp1 = biblioteca.buscarEmprestimo("E1");
        Emprestimo emp4 = biblioteca.buscarEmprestimo("E4");
        System.out.printf("Multa empréstimo E1 (com atraso): R$ %.2f%n", emp1.calcularMulta());
        System.out.printf("Multa empréstimo E4 (sem atraso): R$ %.2f%n", emp4.calcularMulta());

        // ===== ETAPA 10 — Situação final =====
        biblioteca.listarMateriais();
        biblioteca.listarEmprestimosFinalizados();
        biblioteca.listarEmprestimosEmAndamento();
    }
}