package br.com.idtrust.analista.implantacao;

public class TesteEstrutura {
    public static void main(String[] args) {
        
        EstruturaCondicional teste = new EstruturaCondicional();

        teste.checkAging("masculino", 74);
        teste.checkAging("feminino", 4);
        teste.checkAging("feminino", 13);
        teste.checkAging("masculino", 60);
        teste.checkAging("masculino", 19);
        teste.checkAging("feminino", 60);
        
    }
}
