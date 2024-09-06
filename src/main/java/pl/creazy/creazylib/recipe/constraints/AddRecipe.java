package pl.creazy.creazylib.recipe.constraints;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AddRecipe {
  boolean includeForSmoker() default false;
  boolean includeForBlastFurnace() default false;
  int divideTimeForSmoker() default 2;
  int divideTimeForBlastFurnace() default 2;
}
