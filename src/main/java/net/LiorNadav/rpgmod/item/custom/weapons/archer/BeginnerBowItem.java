package net.LiorNadav.rpgmod.item.custom.weapons.archer;


import net.LiorNadav.rpgmod.entity.ModEntityTypes;
import net.LiorNadav.rpgmod.item.ModItems;
import net.LiorNadav.rpgmod.world.entity.projectile.TorchArrow;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;

import java.util.function.Predicate;

public class BeginnerBowItem extends ModBow{
    public BeginnerBowItem(Properties properties) {
        super(properties);
    }

    protected double getArrowDamage(ItemStack bowStack, AbstractArrow arrowEntity) {
        int powerLevel = EnchantmentHelper.getItemEnchantmentLevel(Enchantments.POWER_ARROWS, bowStack);
            return (double)powerLevel * 0.5D + 0.5D;
    }

    @Override
    public Predicate<ItemStack> getAllSupportedProjectiles() {
        return (ammoStack) -> {
            if(ammoStack.getItem() == ModItems.TORCH_ARROW.get()){
                return ammoStack.getItem() == ModItems.TORCH_ARROW.get();
            }
            return ammoStack.getItem() == Items.ARROW;
        };
    }

    @Override
    protected AbstractArrow createArrow(Level level, ItemStack ammoStack, Player player) {
        if(ammoStack.getItem() == ModItems.TORCH_ARROW.get()) {
            return new TorchArrow(ModEntityTypes.TORCH_ARROW.get(), player, level);
        }
        return new Arrow(level,player);
    }
}
