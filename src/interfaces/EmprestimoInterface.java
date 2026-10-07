package interfaces;

import modelo.Emprestimo;
import modelo.Usuario;
import modelo.Livro;

public interface EmprestimoInterface {
    public void registrarEmprestimo(Emprestimo emprestimo);

    public Emprestimo[] listarEmprestimos();

    public Emprestimo buscarEmprestimoPorLivro(Livro livro);

    public Emprestimo buscarEmprestimoPorUsuario(Usuario usuario);

    public boolean removerEmprestimo(Livro livro);
}
