package modelo;

public class Funcionario extends Usuario {
    private String cargo;
    private boolean admin;

    //construtor inicial
    public Funcionario(String nome, String cpf, String endereco, String telefone, String email, String login, String senha, String cargo, boolean admin) {
        super(nome, cpf, endereco, telefone, email, login, senha);
        this.cargo = cargo;
        this.admin = admin;
    }

    //métodos getters e setters

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
}
