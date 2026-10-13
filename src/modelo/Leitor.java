package modelo;

public class Leitor extends Usuario {
    private String matricula;

    //construtor inicial
    public Leitor(String nome, String cpf, String endereco, String telefone, String email, String login, String senha, double multa, boolean ativo, int livrosEmprestados, String matricula, boolean admin) {
        super(nome, cpf, endereco, telefone, email, login, senha, multa, ativo, livrosEmprestados, admin);
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