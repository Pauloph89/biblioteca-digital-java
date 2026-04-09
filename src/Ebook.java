public class Ebook extends Material {

    private String formato;
    private double tamanhoArquivoMB;

    public Ebook(String codigo, String titulo, int anoPublicacao, int quantidadeDisponivel, String formato, double tamanhoArquivoMB) {
        super(codigo, titulo, anoPublicacao, quantidadeDisponivel);
        this.formato = formato;
        this.tamanhoArquivoMB = tamanhoArquivoMB;
    }

    public String getFormato()                         { return formato; }
    public double getTamanhoArquivoMB()                { return tamanhoArquivoMB; }
    public void setFormato(String formato)             { this.formato = formato; }
    public void setTamanhoArquivoMB(double tamanho)    { this.tamanhoArquivoMB = tamanho; }

    @Override
    public String getDetalhesEspecificos() {
        return "Ebook | Formato: " + formato + " | Tamanho: " + tamanhoArquivoMB + " MB";
    }
}