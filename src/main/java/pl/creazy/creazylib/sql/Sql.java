package pl.creazy.creazylib.sql;

import lombok.experimental.UtilityClass;
import org.jetbrains.annotations.NotNull;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@UtilityClass
public class Sql {
  public static int executeUpdate(
      @NotNull Connection connection,
      @NotNull String query,
      @NotNull Object... values) {
    try (var sql = connection.prepareStatement(query)) {
      setValues(sql, values);
      return sql.executeUpdate();
    } catch (SQLException exception) {
      throw new RuntimeException(exception);
    }
  }

  public static @NotNull ResultSet executeQuery(
      @NotNull Connection connection,
      @NotNull String query,
      @NotNull Object... values) {
    try  {
      var sql = connection.prepareStatement(query);
      setValues(sql, values);
      return sql.executeQuery();
    } catch (SQLException exception) {
      throw new RuntimeException(exception);
    }
  }

  private static void setValues(@NotNull PreparedStatement sql, @NotNull Object... values) throws SQLException {
    var index = 1;
    for (Object value : values) {
      sql.setObject(index, value);
      index++;
    }
  }
}
