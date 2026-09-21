package modelo;

public class Leitor extends Usuario {
    private String matricula;

    //construtor inicial
    public Leitor(String nome, String cpf, String endereco, String telefone, String email, String login, String senha, String matricula) {
        super(nome, cpf, endereco, telefone, email, login, senha);
        this.matricula = matricula;
    }

    //métodos getters e setters
    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}