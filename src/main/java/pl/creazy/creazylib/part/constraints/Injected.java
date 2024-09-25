package pl.creazy.creazylib.part.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.SOURCE)
@Target({ElementType.PARAMETER, ElementType.FIELD})
public @interface Injected {
}
