package servico;

import excecoes.*;
import modelo.Livro;
import modelo.Usuario;
import modelo.Leitor;
import modelo.Funcionario;
import modelo.Emprestimo;
import interfaces.EmprestimoInterface;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class EmprestimoServico {
    private final EmprestimoInterface emprestimoRepo;
    private final UsuarioServico usuarioServico;
    private final LivroServico livroServico;

    //construtor inicial
    public EmprestimoServico(EmprestimoInterface emprestimoRepo, UsuarioServico usuarioServico, LivroServico livroServico) {
        this.emprestimoRepo = emprestimoRepo;
        this.usuarioServico = usuarioServico;
        this.livroServico = livroServico;
    }

    //listar empréstimos
    public Emprestimo[] listarEmprestimos() {
        return emprestimoRepo.listarEmprestimos();
    }

    //registrar empréstimo
    public void registrarEmprestimo(String cpf, String titulo, String login, String senha) throws Exception {
        usuarioServico.autenticarUsuario(login, senha);
        Usuario responsavel = this.identificarFuncionarioAdministrador(login);

        Usuario usuario = usuarioServico.buscarUsuarioPorCPF(cpf);
        Livro livro = livroServico.buscarLivroPorTitulo(titulo);


        this.verificarMulta(usuario);
        this.verificarDisponibilidade(livro);
        this.verificarLimiteEmprestimo(usuario);


        int novaQuantidade = livro.getQuantidadeDisponivel() - 1;
        livro.setQuantidadeDisponivel(novaQuantidade);
        if (novaQuantidade == 0) {
            livro.setDisponivel(false);
        }


        usuario.setLivrosEmprestados(usuario.getLivrosEmprestados() + 1);


        LocalDate dataHoje = LocalDate.now();
        LocalDate dataDevolucaoPrevista = dataHoje.plusDays(15);


        Funcionario funcionarioResponsavel = (responsavel instanceof Funcionario) ? (Funcionario) responsavel : null;
        Emprestimo novoEmprestimo = new Emprestimo(livro, usuario, dataHoje, dataDevolucaoPrevista, null, funcionarioResponsavel);
        emprestimoRepo.registrarEmprestimo(novoEmprestimo);
    }

    //devolver livro
    public void devolverLivro(String cpf, String titulo) throws Exception {
        Usuario usuario = usuarioServico.buscarUsuarioPorCPF(cpf);
        Livro livro = livroServico.buscarLivroPorTitulo(titulo);


        Emprestimo emprestimoAtivo = this.buscarEmprestimoAtivo(usuario, livro);


        LocalDate dataDevolucaoReal = LocalDate.now();
        double multaCalculada = this.calcularMulta(emprestimoAtivo.getDataDevolucaoPrevista(), dataDevolucaoReal);
        if (multaCalculada > 0) {
            usuario.setMulta(usuario.getMulta() + multaCalculada);
        }


        livro.setQuantidadeDisponivel(livro.getQuantidadeDisponivel() + 1);
        livro.setDisponivel(true);


        if (usuario.getLivrosEmprestados() > 0) {
            usuario.setLivrosEmprestados(usuario.getLivrosEmprestados() - 1);
        }

        emprestimoAtivo.setDataDevolucaoReal(dataDevolucaoReal);
    }

    //pagar multa
    public void pagarMulta(String cpfUsuario, double valorPago) throws UsuarioNaoEncontradoException {
        Usuario usuario = usuarioServico.buscarUsuarioPorCPF(cpfUsuario);

        if (valorPago <= 0) {
            throw new IllegalArgumentException("O valor do pagamento deve ser maior que zero.");
        }

        double multaAtual = usuario.getMulta();
        double novaMulta = multaAtual - valorPago;

        if (novaMulta < 0) {
            usuario.setMulta(0.0);
        } else {
            usuario.setMulta(novaMulta);
        }
    }

    //buscar emprestimo
    private Emprestimo buscarEmprestimoAtivo(Usuario usuario, Livro livro) throws EmprestimoNaoEncontradoException {
        Emprestimo emprestimo = emprestimoRepo.buscarEmprestimoPorLivro(livro);


        if (emprestimo == null || !emprestimo.getUsuario().getCpf().equals(usuario.getCpf())) {
            throw new EmprestimoNaoEncontradoException("Não há nenhum empréstimo ativo do livro '" + livro.getTitulo() + "' para o usuário " + usuario.getNome() + ".");
        }
        return emprestimo;
    }

    private Usuario identificarFuncionarioAdministrador(String login) throws Exception {
        Usuario responsavel = null;
        Usuario[] usuarios = usuarioServico.listarUsuarios();

        for (int i = 0; i < usuarios.length; i++) {
            if (usuarios[i] != null && usuarios[i].getLogin().equals(login)) {
                if (usuarios[i].admin()) {
                    responsavel = usuarios[i];
                }
                break;
            }
        }

        if (responsavel == null) {
            throw new SecurityException("Operação negada: Apenas funcionários administradores podem registrar empréstimos.");
        }
        return responsavel;
    }

    //calcular multa
    private double calcularMulta(LocalDate dataDevolucaoPrevista, LocalDate dataDevolucaoReal) {
        if (dataDevolucaoReal.isAfter(dataDevolucaoPrevista)) {
            long diasAtraso = ChronoUnit.DAYS.between(dataDevolucaoPrevista, dataDevolucaoReal);
            return diasAtraso * 1.50;
        }
        return 0.0;
    }

    //verificar multa
    private void verificarMulta(Usuario usuario) throws UsuarioComMultaException {
        if (usuario.getMulta() > 0) {
            throw new UsuarioComMultaException("O usuário " + usuario.getNome() + " possui multas pendentes.");
        }
    }

    //verificar disponibilidade
    private void verificarDisponibilidade(Livro livro) throws LivroIndisponivelException {
        if (!livro.isDisponivel() || livro.getQuantidadeDisponivel() <= 0) {
            throw new LivroIndisponivelException("O livro: " + livro.getTitulo() + " não se encontra disponível em nosso acervo.");
        }
    }

    //verificar empréstimo
    private void verificarLimiteEmprestimo(Usuario usuario) throws LimiteEmprestimoExcedidoException {
        if (!usuario.admin() && usuario.getLivrosEmprestados() >= 3) {
            throw new LimiteEmprestimoExcedidoException("O leitor atingiu o limite máximo de 3 empréstimos.");
        }
        if (usuario.admin() && usuario.getLivrosEmprestados() >= 5) {
            throw new LimiteEmprestimoExcedidoException("O funcionário atingiu o limite máximo de 5 empréstimos.");
        }
    }
}
