package repositorio;

import interfaces.FuncionarioInterface;
import modelo.Funcionario;

public class FuncionarioRepositorio implements FuncionarioInterface {
    private int contador;
    private Funcionario[] funcionarios = new Funcionario[500];

    //construtor inicial
    public FuncionarioRepositorio(int contador, Funcionario[] funcionarios) {
        this.contador = contador;
        this.funcionarios = new Funcionario[500];
    }

    //métodos getters e setters
    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public Funcionario[] getFuncionarios() {
        return funcionarios;
    }

    public void setFuncionarios(Funcionario[] funcionarios) {
        this.funcionarios = funcionarios;
    }

    //adicionar funcionário
    public void adicionarFuncionario(Funcionario funcionario) {
        funcionarios[contador] = funcionario;
        contador++;
    }

    //listar funcionários
    public Funcionario[] listarFuncionarios() {
        return funcionarios;
    }

    //buscar funcionários por nome
    public Funcionario buscarFuncionarioPorNome(String nome) {
        for (int i = 0; i < contador; i++) {
            if (funcionarios[i] != null && funcionarios[i].getNome().equalsIgnoreCase(nome)) {
                return funcionarios[i];
            }
        }
        return null;
    }

    //buscar funcionário por CPF
    public Funcionario buscarFuncionarioPorCPF(String cpf) {
        for (int i = 0; i < contador; i++) {
            if (funcionarios[i] != null && funcionarios[i].getCpf().equals(cpf)) {
                return funcionarios[i];
            }
        }
        return null;
    }

    //remover funcionário
    public boolean removerFuncionario(String cpf) {
        for (int i = 0; i < contador; i++) {
            if (funcionarios[i] != null && funcionarios[i].getCpf().equals(cpf)) {
                for (int j = i; j < contador - 1; j++) {
                    funcionarios[j] = funcionarios[j + 1];
                }
                funcionarios[contador - 1] = null;
                contador--;
                return true;
            }
        }
        return false;
    }
}
