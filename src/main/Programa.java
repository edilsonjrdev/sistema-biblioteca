package main;

//classes básicas

import java.util.Scanner;

import modelo.Livro;
import modelo.Usuario;
import modelo.Leitor;
import modelo.Funcionario;
import modelo.Emprestimo;

//interfaces
import interfaces.LivroInterface;
import interfaces.UsuarioInterface;
import interfaces.LeitorInterface;
import interfaces.FuncionarioInterface;
import interfaces.EmprestimoInterface;

//repositórios
import repositorio.LivroRepositorio;
import repositorio.UsuarioRepositorio;
import repositorio.LeitorRepositorio;
import repositorio.FuncionarioRepositorio;
import repositorio.EmprestimoRepositorio;

public class Programa {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        //arrays
        Livro[] arrayLivros = new Livro[1000];
        Usuario[] arrayUsuarios = new Usuario[1500];
        Leitor[] arrayLeitores = new Leitor[1000];
        Funcionario[] arrayFuncionarios = new Funcionario[500];
        Emprestimo[] arrayEmprestimos = new Emprestimo[1000];

        LivroInterface livroRepo = new LivroRepositorio(0, arrayLivros);
        UsuarioInterface usuarioRepo = new UsuarioRepositorio(0, arrayUsuarios);
        LeitorInterface leitorRepo = new LeitorRepositorio(0, arrayLeitores);
        FuncionarioInterface funcionarioRepo = new FuncionarioRepositorio(0, arrayFuncionarios);
        EmprestimoInterface emprestimoRepo = new EmprestimoRepositorio(0, arrayEmprestimos);
    }
}
