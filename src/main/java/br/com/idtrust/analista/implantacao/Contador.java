package br.com.idtrust.analista.implantacao;

public class Contador {
    
public static void main(String[] args) {
    int contador = 3;
    int soma = 57;

    while(contador <= 10){
        if (contador < 5 || contador == 8){
            soma -= contador;
        }else{
            soma += contador;
        }
        contador++;
    }
    System.out.println("O valor da soma é " + soma); // Resposta c) O valor da soma é 79
}

}
