public class Revista extends Material {

    private String edicao;

    public Revista(String codigo, String titulo, int anoPublicacao, int quantidadeDisponivel, String edicao) {
        super(codigo, titulo, anoPublicacao, quantidadeDisponivel);
        this.edicao = edicao;
    }

    public String getEdicao()           { return edicao; }
    public void setEdicao(String edicao) { this.edicao = edicao; }

    @Override
    public String getDetalhesEspecificos() {
        return "Revista | Edição: " + edicao;
    }
}