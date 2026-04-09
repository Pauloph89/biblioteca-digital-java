public class Livro extends Material {

    private String autor;

    public Livro(String codigo, String titulo, int anoPublicacao, int quantidadeDisponivel, String autor) {
        super(codigo, titulo, anoPublicacao, quantidadeDisponivel);
        this.autor = autor;
    }

    public String getAutor()          { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    @Override
    public String getDetalhesEspecificos() {
        return "Livro | Autor: " + autor;
    }
}