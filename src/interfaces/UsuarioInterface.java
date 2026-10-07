package interfaces;

import modelo.Usuario;

public interface UsuarioInterface {
    public void cadastrarUsuario(Usuario usuario);

    public Usuario[] listarUsuarios();

    public Usuario buscarUsuarioPorNome(String nome);

    public Usuario buscarUsuarioPorCPF(String cpf);

    public boolean removerUsuario(String cpf);


}
