package servico;

import excecoes.UsuarioComMultaException;
import excecoes.UsuarioNaoEncontradoException;
import excecoes.UsuarioDuplicadoException;
import excecoes.AutenticacaoInvalidaException;
import modelo.Usuario;
import interfaces.UsuarioInterface;

public class UsuarioServico {
    private final UsuarioInterface usuarioRepo;

    //construtor inicial
    public UsuarioServico(UsuarioInterface usuarioRepo) {
        this.usuarioRepo = usuarioRepo;
    }

    //cadastrar usuário
    public void cadastrarUsuario(Usuario novoUsuario) throws UsuarioDuplicadoException {
        Usuario usuarioExistente = usuarioRepo.buscarUsuarioPorCPF(novoUsuario.getCpf());
        if (usuarioExistente != null) {
            throw new UsuarioDuplicadoException("O usuário" + novoUsuario.getCpf() + "já está cadastrado.");
        }
        usuarioRepo.cadastrarUsuario(novoUsuario);
    }

    //buscar usuário por CPF
    public Usuario buscarUsuarioPorCPF(String cpf) throws UsuarioNaoEncontradoException {
        Usuario usuario = usuarioRepo.buscarUsuarioPorCPF(cpf);
        if (usuario == null) {
            throw new UsuarioNaoEncontradoException("O usuário com o CPF:" + cpf + "não foi encontrado.");
        }
        return usuario;
    }

    //buscar usuário por nome
    public Usuario buscarUsuarioPorNome(String nome) throws UsuarioNaoEncontradoException {
        Usuario usuario = usuarioRepo.buscarUsuarioPorNome(nome);
        if (usuario == null) {
            throw new UsuarioNaoEncontradoException("Não foi encontrado nenhum usuário chamado" + nome);
        }
        return usuario;
    }

    //listar usuários
    public Usuario[] listarUsuarios() {
        return usuarioRepo.listarUsuarios();
    }

    //remover usuário
    public boolean removerUsuario(String cpf) throws UsuarioNaoEncontradoException, UsuarioComMultaException {
        Usuario usuario = this.buscarUsuarioPorCPF(cpf);
        if (usuario.getMulta() > 0) {
            throw new UsuarioComMultaException("Não é possível remover o usuário " + usuario.getNome() + " pois ele possui multas pendentes.");
        }
        return usuarioRepo.removerUsuario(cpf);
    }

    //autenticar login de usuário
    public boolean autenticarUsuario(String login, String senha) throws AutenticacaoInvalidaException {
        Usuario[] usuarios = usuarioRepo.listarUsuarios();
        for (int i = 0; i < usuarios.length; i++) {
            Usuario usuario = usuarios[i];
            if (usuario != null && usuario.getLogin().equals(login)) {
                if (usuario.getSenha().equals(senha)) {
                    return true;
                }
            }
        }
        throw new AutenticacaoInvalidaException("Login ou senha inválidos.");
    }

}