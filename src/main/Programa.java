package main;

import cadastro.Fachada;
import interfaces.EmprestimoInterface;
import modelo.Livro;
import repositorio.LivroRepositorio;
import servico.LivroServico;
import modelo.Usuario;
import repositorio.UsuarioRepositorio;
import servico.UsuarioServico;
import modelo.Emprestimo;
import servico.EmprestimoServico;
import repositorio.EmprestimoRepositorio;

public class Programa {
    public static void main(String[] args) {

        LivroRepositorio acervo = new LivroRepositorio();
        LivroServico livroServico = new LivroServico(acervo);

        UsuarioRepositorio usuarios = new UsuarioRepositorio();
        UsuarioServico usuarioServico = new UsuarioServico(usuarios);

        EmprestimoRepositorio emprestimos = new EmprestimoRepositorio();
        EmprestimoServico emprestimoServico = new EmprestimoServico(emprestimos, usuarioServico, livroServico);

        Fachada fachada = new Fachada(livroServico, usuarioServico, emprestimoServico);

        //instanciando livros
        Livro livro1 = new Livro("As Veias Abertas da América Latina", "Eduardo Galeano", "1970", "978-8525420817", "Não-ficção, Histórico", 3, true);
        Livro livro2 = new Livro("A Hora da Estrela", "Clarice Lispector", "1977", " 978-6555320350", "Romance", 10, true);
        Livro livro3 = new Livro("Percy Jackson e O Ladrão de Raios", "Rick Riordan", "2010", "978-6555606539", "Fantasia infanto-juvenil", 5, true);
        Livro livro4 = new Livro("Dungeon Crawler Carl", "Matt Dinniman", "2024", "978-0593820247", "Lit-RPG, Fantasia", 2, true);
        Livro livro5 = new Livro("Guia do Mochileiro da Galáxia", "Douglas Adams", "1979", "978-8599296578", "Ficção científica, Comédia", 4, true);
        Livro livro6 = new Livro("Como As Democracias Morrem", "Daniel Ziblatt e Steven Levitsky", "2018", "978-8537818008", "Não-ficção, Histórico", 1, true);
        Livro livro7 = new Livro("Como As Democracias Morrem", "Daniel Ziblatt e Steven Levitsky", "2018", "978-8537818008", "Não-ficção, Histórico", 1, true);
        Livro livro8 = new Livro("Objetos Sobrenaturais", "Stacey Graham", "2020", "978-8594541581", "Terror", 1, true);


        //cadastrando livros
        System.out.println("--- Cadastrando Livros ---");
        fachada.cadastrarLivro(livro1);
        fachada.cadastrarLivro(livro2);
        fachada.cadastrarLivro(livro3);
        fachada.cadastrarLivro(livro4);
        fachada.cadastrarLivro(livro5);
        fachada.cadastrarLivro(livro6);
        fachada.cadastrarLivro(livro7);
        fachada.cadastrarLivro(livro8);

        //livros no acervo
        System.out.println("\n--- Livros no Acervo ---");
        Livro[] todosLivros = fachada.listarLivros();
        for (int i = 0; i < acervo.getContador(); i++) {
            System.out.print(todosLivros[i]);
        }

        //buscando livros
        Livro livroBusca;

        System.out.println("\n--- Buscando Livros ---");


        livroBusca = fachada.buscarLivroPorTitulo("A Hora da Estrela");
        System.out.println("Resultado: " + livroBusca);


        livroBusca = fachada.buscarLivroPorTitulo("Harry Potter e A Pedra Filosofal");
        System.out.println("Resultado: " + livroBusca);


        livroBusca = fachada.buscarLivroPorAutor("Rick Riordan");
        System.out.println("Resultado: " + livroBusca);

        livroBusca = fachada.buscarLivroPorAutor("Brandon Sanderson");
        System.out.println("Resultado: " + livroBusca);


        //removendo livro
        System.out.println("\n--- Removendo Livro ---");
        fachada.removerLivro("Objetos Sobrenaturais");

        fachada.removerLivro("Jogos Vorazes");


        //instanciando usuários
        Usuario usuario1 = new Usuario("Ash Ketchum", "123.456.789-00", "Rua A, Pallet Town", "81999887766", "ashketchum@gmail.com", "ash123", "pikachu", 0.0, true, 0, false);
        Usuario usuario2 = new Usuario("Misty Williams", "987.654.321-00", "Rua B, Cerulean City", "81955443322", "mistywilliams@gmail.com", "misty456", "togepi", 0.0, true, 0, false);
        Usuario usuario3 = new Usuario("Brock Harrison", "789.456.123-00", "Rua C, Pewter City", "81911223344", "brockharrison@gmail.com", "brock789", "onix", 0.0, true, 0, false);
        Usuario usuario4 = new Usuario("Edilson Junior", "102.xxx.xxx-13", "Rua Quarenta e Um", "819xxxx-xx49", "edilsonjunior@gmail.com", "ed000", "35790", 0.0, true, 0, true);
        Usuario usuario5 = new Usuario("Edilson Junior", "102.xxx.xxx-13", "Rua Quarenta e Um", "819xxxx-xx49", "edilsonjunior@gmail.com", "ed000", "35790", 0.0, true, 0, true);


        //cadastrando usuários
        System.out.println("\n--- Cadastrando Usuários ---");
        fachada.cadastrarUsuario(usuario1);
        fachada.cadastrarUsuario(usuario2);
        fachada.cadastrarUsuario(usuario3);
        fachada.cadastrarUsuario(usuario4);
        fachada.cadastrarUsuario(usuario5);


        //usuários no sistema
        System.out.println("\n--- Usuários no Sistema ---");
        Usuario[] todosUsuarios = fachada.listarUsuarios();
        for (int i = 0; i < usuarios.getContador(); i++) {
            System.out.print(todosUsuarios[i]);
        }

        //buscando usuários
        Usuario usuarioBusca;

        System.out.println("\n--- Buscando Usuários ---");


        usuarioBusca = fachada.buscarUsuarioPorCPF("102.xxx.xxx-13");
        System.out.println("Resultado: " + usuarioBusca);


        usuarioBusca = fachada.buscarUsuarioPorCPF("102.xxx.xxx-14");
        System.out.println("Resultado: " + usuarioBusca);


        usuarioBusca = fachada.buscarUsuarioPorNome("Ash Ketchum");
        System.out.println("Resultado: " + usuarioBusca);

        usuarioBusca = fachada.buscarUsuarioPorNome("Giovanni");
        System.out.println("Resultado: " + usuarioBusca);

        //remover usuário
        System.out.println("\n--- Removendo Usuário ---");

        fachada.removerUsuario("987.654.321-00");

        fachada.removerUsuario("000.000.000-00");


        //livros após remoção
        System.out.println("\n--- Livros no Acervo (Após Remoções) ---");
        Livro[] livrosAtualizados = fachada.listarLivros();
        for (int i = 0; i < acervo.getContador(); i++) {
            System.out.print(livrosAtualizados[i]);
        }

        //usuários após remoção
        System.out.println("\n--- Usuários no Sistema (Após Remoções) ---");
        Usuario[] usuariosAtualizados = fachada.listarUsuarios();
        for (int i = 0; i < usuarios.getContador(); i++) {
            System.out.print(usuariosAtualizados[i]);
        }


        //realizando empréstimo
        System.out.println("\n--- Realizando Empréstimos ---");

        //empréstimo realizado por usuário funcionário
        System.out.println("Tentando emprestar 'A Hora da Estrela' para Ash Ketchum.");
        fachada.registrarEmprestimo("123.456.789-00", "A Hora da Estrela", "ed000", "35790");


        //disponibilidade do livro após empréstimo
        System.out.println("\n--- Verificando Disponibilidade do Livro Após Empréstimo ---");
        Livro livroEmprestado = fachada.buscarLivroPorTitulo("A Hora da Estrela");
        if (livroEmprestado != null) {
            System.out.println("Livro: " + livroEmprestado.getTitulo());
            System.out.println("Quantidade Disponível no Estoque: " + livroEmprestado.getQuantidadeDisponivel());
            System.out.println("Está disponível para novos empréstimos? " + livroEmprestado.isDisponivel());
        }


        //limite de empréstimo atingido
        System.out.println("\n--- Limite de Empréstimos ---");
        System.out.println("Emprestando o 2º livro para Ash.");
        fachada.registrarEmprestimo("123.456.789-00", "Percy Jackson e O Ladrão de Raios", "ed000", "35790");

        System.out.println("Emprestando o 3º livro para Ash.");
        fachada.registrarEmprestimo("123.456.789-00", "Guia do Mochileiro da Galáxia", "ed000", "35790");

        System.out.println("Tentando emprestar o 4º livro para Ash.");
        fachada.registrarEmprestimo("123.456.789-00", "Dungeon Crawler Carl", "ed000", "35790");


        //usuário com multa
        System.out.println("\n--- Usuário com Multas ---");
        Usuario ash = fachada.buscarUsuarioPorCPF("123.456.789-00");
        if (ash != null) {
            ash.setMulta(15.50);
            System.out.println("Multa de R$ " + ash.getMulta() + " inserida para o usuário: " + ash.getNome());
        }
        System.out.println("Tentando realizar novo empréstimo para usuário com multa.");
        fachada.registrarEmprestimo("123.456.789-00", "As Veias Abertas da América Latina", "ed000", "35790");


        //listar empréstimos
        System.out.println("\n--- Empréstimos Registrados no Sistema ---");
        Emprestimo[] todosEmprestimos = fachada.listarEmprestimos();
        if (todosEmprestimos != null) {
            for (int i = 0; i < todosEmprestimos.length; i++) {
                if (todosEmprestimos[i] != null) {
                    System.out.println("Empréstimo " + (i + 1) + ": " +
                            todosEmprestimos[i].getLivro().getTitulo() + " -> " +
                            todosEmprestimos[i].getUsuario().getNome());
                }
            }
        }


        //empréstimo realizado por usuário leitor
        System.out.println("\nTentando emprestar livro usando login da Misty (não é administrador).");
        fachada.registrarEmprestimo("789.456.123-00", "Dungeon Crawler Carl", "misty456", "togepi");


        //emprestando livro que não existe no acervo
        System.out.println("\nTentando emprestar um livro inexistente.");
        fachada.registrarEmprestimo("123.456.789-00", "Harry Potter e A Pedra Filosofal", "ed000", "35790");


        //devolvendo
        System.out.println("\n--- Realizando Devoluções ---");
        System.out.println("Tentando devolver 'A Hora da Estrela' do Ash Ketchum.");
        fachada.devolverLivro("123.456.789-00", "A Hora da Estrela");
    }
}