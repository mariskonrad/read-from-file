public class Pet {

    private String nome;
    private Pessoa proprietario;

    public Pet(String nome, Pessoa proprietario) {
        this.nome = nome;
        this.proprietario = proprietario;
    }

    public Pet() {
        this.setNome("");
        this.setProprietario(null);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Pessoa getProprietario() {
        return proprietario;
    }

    public void setProprietario(Pessoa proprietario) {
        this.proprietario = proprietario;
    }

    @Override
    public String toString() {
        return "Pet{" +
                "nome='" + nome + '\'' +
                ", proprietario=" + proprietario +
                '}';
    }
}
