package interfaces;

import modelo.Livro;

public interface LivroInterface {
    public void cadastrarLivro(Livro livro);

    public Livro[] listarLivros();

    public Livro buscarLivroPorTitulo(String titulo);

    public Livro buscarLivroPorAutor(String autor);

    public boolean removerLivro(String titulo);
}
