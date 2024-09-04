package pl.creazy.creazylib.data.persistence.nbt;

public interface SaveableNbtEditor<T extends SaveableNbtEditor<T>> extends NbtEditor<T> {
  void save();
}