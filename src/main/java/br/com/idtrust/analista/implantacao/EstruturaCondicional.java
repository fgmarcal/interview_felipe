package br.com.idtrust.analista.implantacao;

public class EstruturaCondicional {
    
    private String aposentado = "APOSENTADO";
    private String aposentada = "APOSENTADA";
    private String adolescente = "ADOLESCENTE";
    private String crianca = "CRIANÇA";
    private String adulto = "ADULTO";


    void checkAging(String gender, int age){
        if(gender == "masculino" && age >= 65){
            System.out.println(this.aposentado);
        }else if(gender == "feminino" && age >= 60){
            System.out.println(this.aposentada);
        }else if((gender == "masculino" || gender == "feminino") && (age >=13 && age < 18)){
            System.out.println(this.adolescente);
        }else if ((gender == "masculino" || gender == "feminino") && (age < 13)){
            System.out.println(this.crianca);
        }else{
            System.out.println(this.adulto);
        }
    }

}
