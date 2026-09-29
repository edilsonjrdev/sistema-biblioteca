package interfaces;

import modelo.Leitor;

public interface LeitorInterface {
    public void adicionarLeitor(Leitor leitor);

    public Leitor[] listarLeitores();

    public Leitor buscarLeitorPorNome(String nome);

    public Leitor buscarLeitorPorCPF(String cpf);

    public boolean removerLeitor(String cpf);
}
