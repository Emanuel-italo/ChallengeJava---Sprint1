package br.com.clyvo.pet.core.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ChavePrimaria {
    // Esta não precisa de parâmetros, serve apenas como uma "etiqueta" ou marcador
}