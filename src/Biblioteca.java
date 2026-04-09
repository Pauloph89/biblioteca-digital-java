import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Biblioteca {

    private List<Usuario> usuarios;
    private List<Material> materiais;
    private List<Emprestimo> emprestimos;
    private int contadorEmprestimo;

    // Construtor
    public Biblioteca() {
        this.usuarios = new ArrayList<>();
        this.materiais = new ArrayList<>();
        this.emprestimos = new ArrayList<>();
        this.contadorEmprestimo = 1;
    }

    // ==================== CADASTROS ====================

    public void cadastrarUsuario(Usuario u) {
        usuarios.add(u);
        System.out.println("Usuário cadastrado: " + u.getNome());
    }

    public void cadastrarMaterial(Material m) {
        materiais.add(m);
        System.out.println("Material cadastrado: " + m.getTitulo());
    }

    // ==================== BUSCAS ====================

    public Usuario buscarUsuario(String codigo) {
        for (Usuario u : usuarios) {
            if (u.getCodigo().equals(codigo)) return u;
        }
        throw new RuntimeException("Usuário não encontrado: " + codigo);
    }

    public Material buscarMaterial(String codigo) {
        for (Material m : materiais) {
            if (m.getCodigo().equals(codigo)) return m;
        }
        throw new RuntimeException("Material não encontrado: " + codigo);
    }

    public Emprestimo buscarEmprestimo(String id) {
        for (Emprestimo e : emprestimos) {
            if (e.getId().equals(id)) return e;
        }
        throw new RuntimeException("Empréstimo não encontrado: " + id);
    }

    // ==================== EMPRÉSTIMO ====================

    public Emprestimo realizarEmprestimo(String codigoUsuario, String codigoMaterial, LocalDate data) {
        Usuario usuario = buscarUsuario(codigoUsuario);
        Material material = buscarMaterial(codigoMaterial);

        // Regra 1: material deve estar disponível
        if (!material.isDisponivel()) {
            throw new RuntimeException("Material indisponível: " + material.getTitulo());
        }

        // Regra 2: usuário não pode ultrapassar o limite
        if (contarEmprestimosAtivos(codigoUsuario) >= usuario.getLimiteEmprestimos()) {
            throw new RuntimeException("Limite de empréstimos atingido para: " + usuario.getNome());
        }

        String id = "E" + contadorEmprestimo++;
        Emprestimo emp = new Emprestimo(id, usuario, material, data);
        material.reduzirQuantidade();
        emprestimos.add(emp);

        System.out.println("Empréstimo realizado: " + usuario.getNome() + " → " + material.getTitulo());
        return emp;
    }

    // ==================== DEVOLUÇÃO ====================

    public void registrarDevolucao(String idEmprestimo, LocalDate dataRealDevolucao) {
        Emprestimo emp = buscarEmprestimo(idEmprestimo);
        emp.registrarDevolucao(dataRealDevolucao);

        double multa = emp.calcularMulta();
        System.out.println("Devolução registrada: " + emp.getMaterial().getTitulo());
        if (multa > 0) {
            System.out.printf("Multa por atraso: R$ %.2f%n", multa);
        } else {
            System.out.println("Sem multa. Devolvido no prazo!");
        }
    }

    // ==================== CONTADORES ====================

    public int contarEmprestimosAtivos(String codigoUsuario) {
        int count = 0;
        for (Emprestimo e : emprestimos) {
            if (e.getUsuario().getCodigo().equals(codigoUsuario) && !e.isFinalizado()) {
                count++;
            }
        }
        return count;
    }

    // ==================== LISTAGENS ====================

    public void listarUsuarios() {
        System.out.println("\n===== USUÁRIOS =====");
        for (Usuario u : usuarios) {
            System.out.println(u.exibirResumo());
        }
    }

    public void listarMateriais() {
        System.out.println("\n===== MATERIAIS =====");
        for (Material m : materiais) {
            System.out.println(m.exibirResumo());
        }
    }

    public void listarEmprestimosEmAndamento() {
        System.out.println("\n===== EMPRÉSTIMOS EM ANDAMENTO =====");
        for (Emprestimo e : emprestimos) {
            if (!e.isFinalizado()) System.out.println(e.exibirResumo());
        }
    }

    public void listarEmprestimosFinalizados() {
        System.out.println("\n===== EMPRÉSTIMOS FINALIZADOS =====");
        for (Emprestimo e : emprestimos) {
            if (e.isFinalizado()) System.out.println(e.exibirResumo());
        }
    }
}