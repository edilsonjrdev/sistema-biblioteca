package modelo;

public class Funcionario extends Usuario {
    private String cargo;

    //construtor inicial
    public Funcionario(String nome, String cpf, String endereco, String telefone, String email, String login, String senha, double multa, boolean ativo, int livrosEmprestados, String cargo, boolean admin) {
        super(nome, cpf, endereco, telefone, email, login, senha, multa, ativo, livrosEmprestados, admin);
        this.cargo = cargo;
    }


    //métodos getters e setters
    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }
}