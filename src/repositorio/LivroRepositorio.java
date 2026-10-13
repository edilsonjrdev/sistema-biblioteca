package repositorio;

import interfaces.LivroInterface;
import modelo.Livro;

public class LivroRepositorio implements LivroInterface {
    private int contador;
    private Livro[] livros = new Livro[1000];

    //construtor inicial
    public LivroRepositorio() {
        this.contador = 0;
        this.livros = new Livro[1000];
    }

    //métodos getters e setters
    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public Livro[] getLivros() {
        return livros;
    }

    public void setLivros(Livro[] livros) {
        this.livros = livros;
    }

    //cadastrar livro
    public void cadastrarLivro(Livro livro) {
        livros[contador] = livro;
        contador++;
    }

    //listar livros
    public Livro[] listarLivros() {
        return livros;
    }

    //buscar livro por título
    public Livro buscarLivroPorTitulo(String titulo) {
        for (int i = 0; i < contador; i++) {
            if (livros[i] != null && livros[i].getTitulo().equalsIgnoreCase(titulo)) {
                return livros[i];
            }
        }
        return null;
    }

    //buscar livro pro autor
    public Livro buscarLivroPorAutor(String autor) {
        for (int i = 0; i < contador; i++) {
            if (livros[i] != null && livros[i].getAutor().equalsIgnoreCase(autor)) {
                return livros[i];
            }
        }
        return null;
    }

    //remover livro
    public boolean removerLivro(String titulo) {
        for (int i = 0; i < contador; i++) {
            if (livros[i] != null && livros[i].getTitulo().equals(titulo)) {
                for (int j = i; j < contador - 1; j++) {
                    livros[j] = livros[j + 1];
                }
                livros[contador - 1] = null;
                contador--;
                return true;
            }
        }
        return false;
    }
}