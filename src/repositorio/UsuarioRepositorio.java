package repositorio;

import interfaces.UsuarioInterface;
import modelo.Usuario;

public class UsuarioRepositorio implements UsuarioInterface {
    private int contador = 0;
    private Usuario[] usuarios = new Usuario[1000];

    //construtor inicial
    public UsuarioRepositorio(int contador, Usuario[] usuarios) {
        this.contador = 0;
        this.usuarios = new Usuario[1000];
    }

    //metodos getters e setters
    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public Usuario[] getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(Usuario[] usuarios) {
        this.usuarios = usuarios;
    }

    //adicionar usuário
    public void adicionarUsuario(Usuario usuario) {
        usuarios[contador] = usuario;
        contador++;
    }

    //listar usuários
    public Usuario[] listarUsuarios() {
        return usuarios;
    }

    //buscar usuário por nome
    public Usuario buscarUsuarioPorNome(String nome) {
        for (int i = 0; i < contador; i++) {
            if (usuarios[i] != null && usuarios[i].getNome().equalsIgnoreCase(nome)) {
                return usuarios[i];
            }
        }
        return null;
    }

    //buscar usuário por cpf
    public Usuario buscarUsuarioPorCPF(String cpf) {
        for (int i = 0; i < contador; i++) {
            if (usuarios[i] != null && usuarios[i].getCpf().equals(cpf)) {
                return usuarios[i];
            }
        }
        return null;
    }

    //remover usuário
    public boolean removerUsuario(String cpf) {
        for (int i = 0; i < contador; i++) {
            if (usuarios[i] != null && usuarios[i].getCpf().equals(cpf)) {
                for (int j = i; j < contador - 1; j++) {
                    usuarios[j] = usuarios[j + 1];
                }
                usuarios[contador - 1] = null;
                contador--;
                return true;
            }
        }
        return false;
    }

}
