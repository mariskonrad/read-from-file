public class Pessoa {
    private String documento;
    private String nome;
    private String endereco;

    public Pessoa(String documento, String nome, String endereco) {
        this.documento = documento;
        this.nome = nome;
        this.endereco = endereco;
    }

    public Pessoa() {
        this.setDocumento("");
        this.setNome("");
        this.setEndereco("");
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "documento='" + documento + '\'' +
                ", nome='" + nome + '\'' +
                ", endereco='" + endereco + '\'' +
                '}';
    }
}
