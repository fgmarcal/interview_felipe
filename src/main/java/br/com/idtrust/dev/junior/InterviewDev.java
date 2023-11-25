package br.com.idtrust.dev.junior;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class InterviewDev {

    private static final Set<Recebivel> RECEBIVEIS;

    public static void main(String[] args) throws Exception {

        System.out.println("Faça os exercícios abaixo usando os dados pré-criados na variável `RECEBIVEIS`.");
        System.out.println("Peço que prepare a resolução logo abaixo de cada enunciado.");
        System.out.println("");

        System.out.println("1 - Print a soma agrupando as mesmas datas de vencimentos");
        Map<LocalDate, BigDecimal> somaPorDataVencimento = RECEBIVEIS.stream()
                .collect(Collectors.groupingBy(
                        Recebivel::getDataVencimento,
                        Collectors.mapping(Recebivel::getValor, Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))
                ));

        somaPorDataVencimento.forEach((data, soma) ->
                System.out.println("Data de Vencimento: " + data + " - Soma: " + soma));
        System.out.println("#############################################################################################");
        System.out.println("2 - Print a soma dos recebiveis ja vencidos");
        BigDecimal somaRecebiveisVencidos = RECEBIVEIS.stream()
                .filter(recebivel -> LocalDate.now().isAfter(recebivel.getDataVencimento()))
                .map(Recebivel::getValor)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("Soma dos recebíveis já vencidos: " + somaRecebiveisVencidos);
        System.out.println("#############################################################################################");
        System.out.println("3 - Formate para moeda Real o valor do recebivel com vencimento 25/07/2023");

        LocalDate dataVencimentoDesejada = LocalDate.parse("2023-07-25");
        RECEBIVEIS.stream()
                .filter(recebivel -> recebivel.getDataVencimento().equals(dataVencimentoDesejada))
                .map(Recebivel::getValor)
                .findFirst()
                .ifPresent(valor -> {
                    NumberFormat formatoMoeda = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
                    String valorFormatado = formatoMoeda.format(valor);
                    System.out.println("Valor do recebível em 25/07/2023: " + valorFormatado);
                });
        System.out.println("#############################################################################################");
        System.out.println("4 - Print o prazo em dias entre emissao e vencimento do recebivel com vencimento 12/10/2023");
        LocalDate dataVencimentoDesejada2 = LocalDate.parse("2023-10-12");
        RECEBIVEIS.stream()
                .filter(recebivel -> recebivel.getDataVencimento().equals(dataVencimentoDesejada2))
                .findFirst()
                .ifPresent(recebivel -> {
                    long prazoEmDias = ChronoUnit.DAYS.between(recebivel.getDataEmissao(), recebivel.getDataVencimento());
                    System.out.println("Prazo em dias entre emissão e vencimento: " + prazoEmDias + " dias");
                });
        System.out.println("#############################################################################################");
        System.out.println("5 - Print a concatenação de todos os campos do recebivel separando por ;");

        RECEBIVEIS.forEach(recebivel -> {
                String concatenacao = recebivel.getCodigo() + ";"
                        + recebivel.getDataEmissao() + ";"
                        + recebivel.getDataVencimento() + ";"
                        + recebivel.getValor();
                System.out.println(concatenacao);
            });
        System.out.println("#############################################################################################");

        System.out.println("6 - Formate a data 2023-06-25 do recebivel para o formato dd/MM/yyyy");
        RECEBIVEIS.forEach(recebivel -> {
            String dataFormatada = recebivel.getDataVencimento().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            System.out.println("Data formatada: " + dataFormatada);
        });

        System.out.println("");
        System.out.println("Exercício extra:");
        System.out.println(
                "7 - Dado uma lista da variável `valores` abaixo, acrescente um novo valor de acordo com as regras a seguir:");
        System.out.println("    -  R$5,90 para valores menor e igual que R$100,00");
        System.out.println("    -  R$15,00 para valores menor que R$20,00");
        System.out.println("    -  R$4,33 para valores maior que R$100,00");
        System.out.println("    -  R$2,10 para valores maior que R$200,00");
        System.out.println("    -  R$3,55 para valores igual que R$150,00");
        System.out.println(
                "    Print o novo resultado na saída da condição de validação e no final print a soma de todos os novos valores da lista");
        List<BigDecimal> valores = Arrays.asList(new BigDecimal("88.88"), new BigDecimal("17.01"),
                new BigDecimal("20.00"), new BigDecimal("150.00"), new BigDecimal("124.21"), new BigDecimal("247.09"),
                new BigDecimal("100.00"), new BigDecimal("4.99"));

        BigDecimal somaNovosValores = valores.stream()
                .map(valor -> {
                        if (valor.compareTo(new BigDecimal("100.00")) <= 0) {
                        BigDecimal novoValor = valor.add(new BigDecimal("5.90"));
                        System.out.println("Novo valor para " + valor + ": " + novoValor);
                        return novoValor;
                        } else if (valor.compareTo(new BigDecimal("20.00")) < 0) {
                        BigDecimal novoValor = valor.add(new BigDecimal("15.00"));
                        System.out.println("Novo valor para " + valor + ": " + novoValor);
                        return novoValor;
                        } else if (valor.compareTo(new BigDecimal("100.00")) > 0 && valor.compareTo(new BigDecimal("200.00")) <= 0) {
                        BigDecimal novoValor = valor.add(new BigDecimal("4.33"));
                        System.out.println("Novo valor para " + valor + ": " + novoValor);
                        return novoValor;
                        } else if (valor.compareTo(new BigDecimal("200.00")) > 0) {
                        BigDecimal novoValor = valor.add(new BigDecimal("2.10"));
                        System.out.println("Novo valor para " + valor + ": " + novoValor);
                        return novoValor;
                        } else if (valor.compareTo(new BigDecimal("150.00")) == 0) {
                        BigDecimal novoValor = valor.add(new BigDecimal("3.55"));
                        System.out.println("Novo valor para " + valor + ": " + novoValor);
                        return novoValor;
                        }
                        return valor; // Caso nenhuma regra seja aplicada
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        
                System.out.println("Soma total dos novos valores: " + somaNovosValores);

        System.out.println("Boa Sorte!");

    }

    static {
        Set<Recebivel> rs = new HashSet<>();
        rs.add(Recebivel.create("1H01R6HA1", LocalDate.parse("2023-05-02"), LocalDate.parse("2023-08-09"),
                new BigDecimal("146.99")));
        rs.add(Recebivel.create("1H01R6HB1", LocalDate.parse("2023-05-02"), LocalDate.parse("2023-05-08"),
                new BigDecimal("592.18")));
        rs.add(Recebivel.create("1H01R6HC1", LocalDate.parse("2023-05-03"), LocalDate.parse("2023-06-28"),
                new BigDecimal("98.20")));
        rs.add(Recebivel.create("1H01R6HD1", LocalDate.parse("2023-05-06"), LocalDate.parse("2023-09-19"),
                new BigDecimal("726.01")));
        rs.add(Recebivel.create("1H01R6HE1", LocalDate.parse("2023-05-02"), LocalDate.parse("2023-05-08"),
                new BigDecimal("81.88")));
        rs.add(Recebivel.create("1H01R6HF1", LocalDate.parse("2023-05-03"), LocalDate.parse("2023-07-15"),
                new BigDecimal("221.34")));
        rs.add(Recebivel.create("1H01R6HG1", LocalDate.parse("2023-05-02"), LocalDate.parse("2023-07-25"),
                new BigDecimal("711.98")));
        rs.add(Recebivel.create("1H01R6HH1", LocalDate.parse("2023-05-05"), LocalDate.parse("2023-10-10"),
                new BigDecimal("100.27")));
        rs.add(Recebivel.create("1H01R6HI1", LocalDate.parse("2023-05-02"), LocalDate.parse("2023-10-12"),
                new BigDecimal("3021.83")));
        rs.add(Recebivel.create("1H01R6HJ1", LocalDate.parse("2023-05-03"), LocalDate.parse("2023-09-19"),
                new BigDecimal("1930.76")));

        RECEBIVEIS = Collections.unmodifiableSet(rs);
    }

    public static class Recebivel {

        public static Recebivel create(String codigo, LocalDate dataEmissao, LocalDate dataVencimento,
                BigDecimal valor) {
            Recebivel r = new Recebivel();
            r.codigo = codigo;
            r.dataEmissao = dataEmissao;
            r.dataVencimento = dataVencimento;
            r.valor = valor;
            return r;
        }

        private String codigo;
        private LocalDate dataEmissao;
        private LocalDate dataVencimento;
        private BigDecimal valor;

        public BigDecimal getValor() {
            return valor;
        }
        public LocalDate getDataVencimento() {
            return dataVencimento;
        }

        public LocalDate getDataEmissao() {
            return dataEmissao;
        }

        public String getCodigo() {
            return codigo;
        }

    }

}
