public class Professor extends Usuario {

    public Professor(String codigo, String nome, String email) {
        super(codigo, nome, email);
    }

    @Override
    public int getLimiteEmprestimos() { return 5; }

    public int getPrazoDevolucaoDias() { return 14; }

    public double getMultaDiaria() { return 1.00; }

    public String getTipoUsuario() { return "Professor"; }
    
}
