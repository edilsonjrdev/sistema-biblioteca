package servico;

import modelo.Livro;
import interfaces.LivroInterface;
import excecoes.LivroNaoEncontradoException;
import excecoes.LivroIndisponivelException;
import excecoes.LivroDuplicadoException;
import repositorio.LivroRepositorio;


public class LivroServico {
    private final LivroInterface livroRepo;

    //construtor inicial
    public LivroServico(LivroInterface livroRepo) {
        this.livroRepo = livroRepo;
    }

    //cadastrar livro
    public void cadastrarLivro(Livro novoLivro) throws LivroDuplicadoException {
        Livro livroExistente = livroRepo.buscarLivroPorTitulo(novoLivro.getTitulo());
        if (livroExistente != null) {
            throw new LivroDuplicadoException("O livro" + novoLivro.getTitulo() + "já está cadastrado.");
        }
        livroRepo.cadastrarLivro(novoLivro);
    }

    //buscar livro pro título
    public Livro buscarLivroPorTitulo(String titulo) throws LivroNaoEncontradoException {
        Livro livro = livroRepo.buscarLivroPorTitulo(titulo);
        if (livro == null) {
            throw new LivroNaoEncontradoException("O livro" + titulo + "não foi encontrado.");
        }
        return livro;
    }

    //buscar livro por autor
    public Livro buscarLivroPorAutor(String autor) throws LivroNaoEncontradoException {
        Livro livro = livroRepo.buscarLivroPorAutor(autor);
        if (livro == null) {
            throw new LivroNaoEncontradoException("Não foi encontrado nenhum livro do autor" + autor);
        }
        return livro;
    }

    //listar livros
    public Livro[] listarLivros() {
        return livroRepo.listarLivros();
    }

    //remover livro
    public boolean removerLivro(String titulo) throws LivroNaoEncontradoException {
        this.buscarLivroPorTitulo(titulo);
        return livroRepo.removerLivro(titulo);
    }
}
