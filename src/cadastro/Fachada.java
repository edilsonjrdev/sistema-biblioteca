package cadastro;

import excecoes.*;
import modelo.Livro;
import modelo.Usuario;
import modelo.Emprestimo;
import servico.EmprestimoServico;
import servico.LivroServico;
import servico.UsuarioServico;

public class Fachada {
    private LivroServico livroservico;
    private UsuarioServico usuarioservico;
    private EmprestimoServico emprestimoservico;

    public Fachada(LivroServico livroservico, UsuarioServico usuarioservico, EmprestimoServico emprestimoservico) {
        this.livroservico = livroservico;
        this.usuarioservico = usuarioservico;
        this.emprestimoservico = emprestimoservico;
    }

    //cadastrar livro
    public void cadastrarLivro(Livro novoLivro) {
        try {
            livroservico.cadastrarLivro(novoLivro);
            System.out.println("Livro cadastrado com sucesso!");
        } catch (LivroDuplicadoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    //buscar livro por título
    public Livro buscarLivroPorTitulo(String titulo) {
        try {
            return livroservico.buscarLivroPorTitulo(titulo);
        } catch (LivroNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }

    //buscar livro por autor
    public Livro buscarLivroPorAutor(String autor) {
        try {
            return livroservico.buscarLivroPorAutor(autor);
        } catch (LivroNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }

    //listar livros
    public Livro[] listarLivros() {
        return livroservico.listarLivros();
    }

    //remover livro
    public boolean removerLivro(String titulo) {
        try {
            boolean removido = livroservico.removerLivro(titulo);
            System.out.println("O livro \"" + titulo + "\" foi removido com sucesso!");
            return removido;
        } catch (LivroNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
            return false;
        }
    }


    //cadastrar usuário
    public void cadastrarUsuario(Usuario novoUsuario) {
        try {
            usuarioservico.cadastrarUsuario(novoUsuario);
            System.out.println("Usuário cadastrado com sucesso!");
        } catch (UsuarioDuplicadoException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    //buscar usuário por cpf
    public Usuario buscarUsuarioPorCPF(String cpf) {
        try {
            return usuarioservico.buscarUsuarioPorCPF(cpf);
        } catch (UsuarioNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }

    //buscar usuário por nome
    public Usuario buscarUsuarioPorNome(String nome) {
        try {
            return usuarioservico.buscarUsuarioPorNome(nome);
        } catch (UsuarioNaoEncontradoException e) {
            System.out.println("Erro: " + e.getMessage());
            return null;
        }
    }

    //listar usuários
    public Usuario[] listarUsuarios() {
        return usuarioservico.listarUsuarios();
    }

    //remover usuário
    public boolean removerUsuario(String cpf) {
        try {
            boolean removido = usuarioservico.removerUsuario(cpf);
            System.out.println("O usuário com CPF \"" + cpf + "\" foi removido com sucesso!");
            return removido;
        } catch (UsuarioNaoEncontradoException | UsuarioComMultaException e) {
            System.out.println("Erro: " + e.getMessage());
            return false;
        }
    }


    //autenticar Login de usuário
    public boolean autenticarUsuario(String login, String senha) {
        try {
            boolean autenticado = usuarioservico.autenticarUsuario(login, senha);
            if (autenticado) {
                System.out.println("Usuário autenticado com sucesso!");
                return true;
            }
            return false;
        } catch (AutenticacaoInvalidaException e) {
            System.out.println("Erro: " + e.getMessage());
            return false;
        }
    }

    //registrar empréstimo
    public void registrarEmprestimo(String cpf, String titulo, String login, String senha) {
        try {
            emprestimoservico.registrarEmprestimo(cpf, titulo, login, senha);
            System.out.println("Sucesso: Empréstimo registrado com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao registrar empréstimo: " + e.getMessage());
        }
    }

    //devolver livro
    public void devolverLivro(String cpf, String titulo) {
        try {
            emprestimoservico.devolverLivro(cpf, titulo);
            System.out.println("Sucesso: Livro devolvido com sucesso!");
        } catch (Exception e) {
            System.out.println("Erro ao devolver livro: " + e.getMessage());
        }
    }

    //pagar multa
    public void pagarMulta(String cpfUsuario, double valorPago) {
        try {
            emprestimoservico.pagarMulta(cpfUsuario, valorPago);
            System.out.println("Pagamento de multa processado!");
        } catch (UsuarioNaoEncontradoException | IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    //listar empréstimos
    public Emprestimo[] listarEmprestimos() {
        return emprestimoservico.listarEmprestimos();
    }
}
