package repositorio;

import interfaces.EmprestimoInterface;
import modelo.Emprestimo;
import modelo.Leitor;
import modelo.Livro;

public class EmprestimoRepositorio implements EmprestimoInterface {
    private int contador;
    private Emprestimo[] emprestimos = new Emprestimo[1000];

    //construtor inicial
    public EmprestimoRepositorio(int contador, Emprestimo[] emprestimos) {
        this.contador = 0;
        this.emprestimos = new Emprestimo[1000];
    }

    //métodos getters e setters
    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public Emprestimo[] getEmprestimos() {
        return emprestimos;
    }

    public void setEmprestimos(Emprestimo[] emprestimos) {
        this.emprestimos = emprestimos;
    }

    //adicionar empréstimo
    public void adicionarEmprestimo(Emprestimo emprestimo) {
        emprestimos[contador] = emprestimo;
        contador++;
    }

    //listar empréstimos
    public Emprestimo[] listarEmprestimos() {
        return emprestimos;
    }

    //buscar empréstimo por livro
    public Emprestimo buscarEmprestimoPorLivro(Livro livro) {
        for (int i = 0; i < contador; i++) {
            if (emprestimos[i] != null && emprestimos[i].getLivro().equals(livro)) {
                return emprestimos[i];
            }
        }
        return null;
    }

    //buscar empréstimo por leitor
    public Emprestimo buscarEmprestimoPorLeitor(Leitor leitor) {
        for (int i = 0; i < contador; i++) {
            if (emprestimos[i] != null && emprestimos[i].getLeitor().equals(leitor)) {
                return emprestimos[i];
            }
        }
        return null;
    }

    //remover empréstimo
    public boolean removerEmprestimo(Livro livro) {
        for (int i = 0; i < contador; i++) {
            if (emprestimos[i] != null && emprestimos[i].getLivro().equals(livro)) {
                for (int j = i; j < contador - 1; j++) {
                    emprestimos[j] = emprestimos[j + 1];
                }
                emprestimos[contador - 1] = null;
                contador--;
                return true;
            }
        }
        return false;
    }
}
