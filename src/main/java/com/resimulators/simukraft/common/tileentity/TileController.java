package com.resimulators.simukraft.common.tileentity;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.NetworkManager;
import net.minecraft.network.play.server.SPacketUpdateTileEntity;
import net.minecraft.tileentity.TileEntity;

public class TileController extends TileEntity {
    private boolean occupied;
    private String type;
    private float rent;
    private int[] simMarker;
    public TileController(){}


    public void setOccupied(boolean occupied){
        this.occupied = occupied;
    }

    public boolean isOccupied(){
        return occupied;
    }

    public void setType(String type){
        this.type = type;
    }

    public String getType(){
        return type;
    }

    public void setRent(float rent) {
        this.rent = rent;
    }

    public float getRent(){
        return rent;
    }

    public void setSimMarker(int[] simMarker){
        this.simMarker = simMarker;
    }

    public int[] getSimMarker(){
        return simMarker;
    }


    @Override
    public void readFromNBT(NBTTagCompound nbt) {
        super.readFromNBT(nbt);
        setOccupied(nbt.getBoolean("occupied"));
        setType(nbt.getString("type"));
        setRent(nbt.getFloat("rent"));
        setSimMarker(nbt.getIntArray("simmarker"));
    }


    @Override
    public NBTTagCompound writeToNBT(NBTTagCompound nbt){
        super.writeToNBT(nbt);
        nbt.setBoolean("occupied", occupied);
        nbt.setString("type",type);
        nbt.setFloat("rent",rent);
        nbt.setIntArray("simmarker",simMarker);
        return nbt;
    }

    @Override
    public NBTTagCompound getUpdateTag() {
        return writeToNBT(new NBTTagCompound());
    }

    @Override
    public SPacketUpdateTileEntity getUpdatePacket() {
        return new SPacketUpdateTileEntity(this.pos,2,writeToNBT(getUpdateTag()));
    }


    @Override
    public void onDataPacket(NetworkManager net, SPacketUpdateTileEntity packet) {
        readFromNBT(packet.getNbtCompound());

    }
}
