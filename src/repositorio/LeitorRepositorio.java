package repositorio;

import interfaces.LeitorInterface;
import modelo.Leitor;

public class LeitorRepositorio implements LeitorInterface {
    private int contador;
    private Leitor[] leitores = new Leitor[1000];

    //construtor inicial
    public LeitorRepositorio(int contador, Leitor[] leitores) {
        this.contador = 0;
        this.leitores = new Leitor[1000];
    }

    //métodos getters e setters
    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public Leitor[] getLeitores() {
        return leitores;
    }

    public void setLeitores(Leitor[] leitores) {
        this.leitores = leitores;
    }

    //adicionar leitor
    public void adicionarLeitor(Leitor leitor) {
        leitores[contador] = leitor;
        contador++;
    }

    //listar leitores
    public Leitor[] listarLeitores() {
        return leitores;
    }

    //buscar leitor por nome
    public Leitor buscarLeitorPorNome(String nome) {
        for (int i = 0; i < contador; i++) {
            if (leitores[i] != null && leitores[i].getNome().equalsIgnoreCase(nome)) {
                return leitores[i];
            }
        }
        return null;
    }

    //buscar leitor por CPF
    public Leitor buscarLeitorPorCPF(String cpf) {
        for (int i = 0; i < contador; i++) {
            if (leitores[i] != null && leitores[i].getCpf().equals(cpf)) {
                return leitores[i];
            }
        }
        return null;
    }

    //remover leitor
    public boolean removerLeitor(String cpf) {
        for (int i = 0; i < contador; i++) {
            if (leitores[i] != null && leitores[i].getCpf().equals(cpf)) {
                for (int j = i; j < contador - 1; j++) {
                    leitores[j] = leitores[j + 1];
                }
                leitores[contador - 1] = null;
                contador--;
                return true;
            }
        }
        return false;
    }
}
