package com.sci.torcherino.blocks.blocks;

import com.sci.torcherino.Torcherino;
import com.sci.torcherino.blocks.tiles.TileTorcherino;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTorch;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockTorcherino extends BlockTorch
{
    public BlockTorcherino()
    {
        this.setLightLevel(0.9375F);
        this.setSoundType(SoundType.WOOD);
        this.setUnlocalizedName("torcherino.torcherino");
    }
    @Override
    public void onBlockAdded(World world, BlockPos pos, IBlockState state)
    {
        if(!world.isRemote)
        {
            TileEntity tile = world.getTileEntity(pos);
            if(tile != null && tile instanceof TileTorcherino) ((TileTorcherino) tile).setPoweredByRedstone(world.isBlockIndirectlyGettingPowered(pos) > 0);
        }
        super.onBlockAdded(world, pos, state);
        if(Torcherino.logPlacement) Torcherino.logger.info(this.getClass().getName().substring(30) + " was placed at " + pos.getX() + ", " + pos.getY() + ", " + pos.getZ());
    }
    @Override
    public void neighborChanged(IBlockState state, World world, BlockPos pos, Block block, BlockPos fromPos)
    {
        if(!world.isRemote)
        {
            TileEntity tile = world.getTileEntity(pos);
            if(tile != null && tile instanceof TileTorcherino) ((TileTorcherino) tile).setPoweredByRedstone(world.isBlockIndirectlyGettingPowered(pos) > 0);
        }
        super.neighborChanged(state, world, pos, block, fromPos);
    }
    
    @Override
    public boolean hasTileEntity(IBlockState state){return true;}
    
    @Override
    public TileEntity createTileEntity(World world, IBlockState state){return new TileTorcherino();}
}