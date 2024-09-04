package pl.creazy.creazylib.exception.permission;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class NoPermissionException extends Exception {
  private final String permissionDenied;
}
