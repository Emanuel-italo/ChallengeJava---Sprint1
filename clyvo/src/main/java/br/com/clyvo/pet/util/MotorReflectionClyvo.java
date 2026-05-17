package br.com.clyvo.pet.util;

import br.com.clyvo.pet.core.annotations.ChavePrimaria;
import br.com.clyvo.pet.core.annotations.ColunaMapeada;
import br.com.clyvo.pet.core.annotations.TabelaMapeada;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class MotorReflectionClyvo {


    public static void analisarEImprimirEstrutura(Object entidade) {
        Class<?> classe = entidade.getClass();


        if (!classe.isAnnotationPresent(TabelaMapeada.class)) {
            System.out.println("Aviso: A classe " + classe.getSimpleName() + " não possui a anotação @TabelaMapeada.");
            return;
        }

        TabelaMapeada tabela = classe.getAnnotation(TabelaMapeada.class);
        System.out.println("\n========================================================");
        System.out.println("⚙️ MOTOR REFLECTION CLYVO VET - ANÁLISE DE ENTIDADE ⚙️");
        System.out.println("Classe Analisada: " + classe.getSimpleName());
        System.out.println("Tabela Mapeada no Banco: " + tabela.nome());


        Field[] campos = classe.getDeclaredFields();
        List<String> colunas = new ArrayList<>();
        String chavePrimaria = "Nenhuma Chave Definida";

        for (Field campo : campos) {
            campo.setAccessible(true);


            if (campo.isAnnotationPresent(ChavePrimaria.class)) {
                if (campo.isAnnotationPresent(ColunaMapeada.class)) {
                    chavePrimaria = campo.getAnnotation(ColunaMapeada.class).nome();
                } else {
                    chavePrimaria = campo.getName();
                }
            }


            if (campo.isAnnotationPresent(ColunaMapeada.class)) {
                ColunaMapeada coluna = campo.getAnnotation(ColunaMapeada.class);
                try {
                    Object valor = campo.get(entidade);
                    colunas.add(" ├─ Coluna BD: " + coluna.nome() + " | Valor Atual: " + (valor != null ? valor.toString() : "NULL"));
                } catch (IllegalAccessException e) {
                    colunas.add(" ├─ Coluna BD: " + coluna.nome() + " | Valor Atual: [Acesso Negado]");
                }
            }
        }

        System.out.println("Chave Primária da Tabela: " + chavePrimaria);
        System.out.println("Mapeamento de Colunas:");
        for (String col : colunas) {
            System.out.println(col);
        }
        System.out.println("========================================================\n");
    }
}