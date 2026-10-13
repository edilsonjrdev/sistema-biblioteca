package servico;

import modelo.Livro;
import interfaces.LivroInterface;
import excecoes.LivroNaoEncontradoException;
import excecoes.LivroIndisponivelException;
import excecoes.LivroDuplicadoException;
import repositorio.LivroRepositorio;


public class LivroServico {
    private LivroInterface livroInterface;

    //construtor inicial
    public LivroServico(LivroInterface livroInterface) {
        this.livroInterface = livroInterface;
    }

    //cadastrar livro
    public void cadastrarLivro(Livro novoLivro) throws LivroDuplicadoException {
        Livro livroExistente = livroInterface.buscarLivroPorTitulo(novoLivro.getTitulo());
        if (livroExistente != null) {
            throw new LivroDuplicadoException("O livro: " + novoLivro.getTitulo() + " já está cadastrado.");
        }
        livroInterface.cadastrarLivro(novoLivro);
    }

    //buscar livro por título
    public Livro buscarLivroPorTitulo(String titulo) throws LivroNaoEncontradoException {
        Livro livro = livroInterface.buscarLivroPorTitulo(titulo);
        if (livro == null) {
            throw new LivroNaoEncontradoException("O livro: " + titulo + " não foi encontrado.");
        }
        return livro;
    }

    //buscar livro por autor
    public Livro buscarLivroPorAutor(String autor) throws LivroNaoEncontradoException {
        Livro livro = livroInterface.buscarLivroPorAutor(autor);
        if (livro == null) {
            throw new LivroNaoEncontradoException("Não foi encontrado nenhum livro do autor " + autor);
        }
        return livro;
    }

    //listar livros
    public Livro[] listarLivros() {
        return livroInterface.listarLivros();
    }

    //remover livro
    public boolean removerLivro(String titulo) throws LivroNaoEncontradoException {
        this.buscarLivroPorTitulo(titulo);
        return livroInterface.removerLivro(titulo);
    }
}
