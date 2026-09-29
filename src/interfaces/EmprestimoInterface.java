package interfaces;

import modelo.Emprestimo;
import modelo.Leitor;
import modelo.Livro;

public interface EmprestimoInterface {
    public void adicionarEmprestimo(Emprestimo emprestimo);

    public Emprestimo[] listarEmprestimos();

    public Emprestimo buscarEmprestimoPorLivro(Livro livro);

    public Emprestimo buscarEmprestimoPorLeitor(Leitor leitor);

    public boolean removerEmprestimo(Livro livro);
}
