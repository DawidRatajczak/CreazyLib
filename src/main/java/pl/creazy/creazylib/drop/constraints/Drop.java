package pl.creazy.creazylib.drop.constraints;

import pl.creazy.creazylib.part.constraints.Part;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Part
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Drop {
}
