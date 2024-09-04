package pl.creazy.creazylib.util.result;

public interface Result<T> {
  static <T> Result<T> create(T result) {
    return () -> result;
  }

  T result();
}
