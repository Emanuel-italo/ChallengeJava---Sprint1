package br.com.clyvo.pet.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD) // Significa que só pode ser usada em atributos/variáveis
public @interface ColunaMapeada {
    String nome();
}