package pl.creazy.creazylib.util.result;

public interface StatusResult<T, U> extends Result<T> {
  static <T, U> StatusResult<T, U> create(T result, U status) {
    return new StatusResult<T,U>() {
      @Override
      public T result() {
        return result;
      }

      @Override
      public U status() {
        return status;
      }
    };
  }

  U status();
}
