package pl.creazy.creazylib.item;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class EnchantedBook {
  private final ItemBuilder builder = new ItemBuilder(Material.ENCHANTED_BOOK);

  public static EnchantedBook create(Enchantment enchant, int level) {
    var book = new EnchantedBook();
    book.builder.addEnchant(enchant, level);
    return book;
  }

  public EnchantedBook with(Enchantment enchantment, int level) {
    builder.addEnchant(enchantment, level);
    return this;
  }

  public ItemStack build() {
    return builder.build();
  }
}
