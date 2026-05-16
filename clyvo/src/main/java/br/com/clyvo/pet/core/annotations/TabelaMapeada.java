package br.com.clyvo.pet.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE) // Significa que esta anotação só pode ser usada em cima de classes
public @interface TabelaMapeada {
    String nome();
}