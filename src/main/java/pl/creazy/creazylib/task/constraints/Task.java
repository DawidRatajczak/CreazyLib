package pl.creazy.creazylib.task.constraints;

import pl.creazy.creazylib.part.constraints.Part;
import pl.creazy.creazylib.task.TaskRun;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Part
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Task {
  TaskRun run() default TaskRun.RUN;
  long delay() default 1;
  long period() default 1;
}
