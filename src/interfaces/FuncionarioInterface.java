package interfaces;

import modelo.Funcionario;

public interface FuncionarioInterface {
    public void adicionarFuncionario(Funcionario funcionario);

    public Funcionario[] listarFuncionarios();

    public Funcionario buscarFuncionarioPorNome(String nome);

    public Funcionario buscarFuncionarioPorCPF(String cpf);

    public boolean removerFuncionario(String cpf);
}
