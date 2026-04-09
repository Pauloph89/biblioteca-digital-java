public class Aluno extends Usuario {
    public Aluno(String codigo, String nome, String email){
        super(codigo, nome, email);
    }

@Override
public int getLimiteEmprestimos() { return 3;}
public int getPrazoDevolucaoDias() { return 7; }
public double getMultaDiaria() { return 2.50; }
public String getTipoUsuario() { return "Aluno"; }
}