public abstract class Material implements Exibivel {

    private String codigo;
    private String titulo;
    private int anoPublicacao;
    private int quantidadeDisponivel;

    public Material(String codigo, String titulo, int anoPublicacao, int quantidadeDisponivel) {
        this.codigo = codigo;
        this.titulo = titulo;
        this.anoPublicacao = anoPublicacao;
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public String getCodigo()              { return codigo; }
    public String getTitulo()              { return titulo; }
    public int getAnoPublicacao()          { return anoPublicacao; }
    public int getQuantidadeDisponivel()   { return quantidadeDisponivel; }

    public void setCodigo(String codigo)                        { this.codigo = codigo; }
    public void setTitulo(String titulo)                        { this.titulo = titulo; }
    public void setAnoPublicacao(int anoPublicacao)             { this.anoPublicacao = anoPublicacao; }
    public void setQuantidadeDisponivel(int qtd)                { this.quantidadeDisponivel = qtd; }

    public boolean isDisponivel() {
        return quantidadeDisponivel > 0;
    }

    public void reduzirQuantidade() {
        this.quantidadeDisponivel--;
    }

    public void aumentarQuantidade() {
        this.quantidadeDisponivel++;
    }

    public abstract String getDetalhesEspecificos();

    @Override
    public String exibirResumo() {
        return "[" + getCodigo() + "] " + titulo + " (" + anoPublicacao + ") | Disponível: " + quantidadeDisponivel + " | " + getDetalhesEspecificos();
    }
}