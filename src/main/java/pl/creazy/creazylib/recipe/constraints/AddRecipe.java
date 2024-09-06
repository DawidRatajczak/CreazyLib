package pl.creazy.creazylib.recipe.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AddRecipe {
  boolean includeForSmoker() default false;
  int divideTime() default 2;
}
