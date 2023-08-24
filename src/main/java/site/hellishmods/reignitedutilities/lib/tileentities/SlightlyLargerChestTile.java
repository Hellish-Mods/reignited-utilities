package site.hellishmods.reignitedutilities.lib.tileentities;

import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.ItemStackHelper;
import net.minecraft.inventory.container.ChestContainer;
import net.minecraft.inventory.container.Container;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.tileentity.LockableLootTileEntity;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;
import site.hellishmods.reignitedutilities.init.TileEntities;

public class SlightlyLargerChestTile extends LockableLootTileEntity {
    protected NonNullList<ItemStack> items;

    public SlightlyLargerChestTile() {
        super(TileEntities.slightly_larger_chest_entity_type.get());
        fillItemsWithEmpty();
    }
    private void fillItemsWithEmpty() {
        items = NonNullList.withSize(getContainerSize(), ItemStack.EMPTY);
    }

    @Override
    protected ITextComponent getDefaultName() {
        return new TranslationTextComponent("block.reignitedutilities.slightly_larger_chest");
    }

    @Override
    public int getContainerSize() {
        return 27;
    }

    @Override
    protected NonNullList<ItemStack> getItems() {
        return items;
    }
    @Override
    protected void setItems(NonNullList<ItemStack> inputItems) {
        items = inputItems;
    }
    @Override
    public CompoundNBT save(CompoundNBT compound) {
        super.save(compound);
        if (!trySaveLootTable(compound)) ItemStackHelper.saveAllItems(compound, items);
        return compound;
    }
    @Override
    public void load(BlockState state, CompoundNBT compound) {
        super.load(state, compound);
        fillItemsWithEmpty();
        if (!tryLoadLootTable(compound)) ItemStackHelper.loadAllItems(compound, items);
    }

    @Override
    protected Container createMenu(int id, PlayerInventory playerInv) {
        return ChestContainer.threeRows(id, playerInv, this);
    }
}
