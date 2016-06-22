//package com.groeps33.valley.net.packet;
//
//import com.groeps33.valley.entity.Monster;
//
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * Created by Bram on 6/15/2016.
// *
// * @author Bram Hoendervangers
// */
//public class MonstersUpdatePackage extends Packet {
//    private List<Monster> monsterList = new ArrayList<>()
//    public MonstersUpdatePackage(byte[] data) throws IOException {
//        super(PacketType.MONSTERS, data);
//    }
//
//    public MonstersUpdatePackage(List<Monster> monsters) {
//        super(PacketType.CONNECT);
//        this.monsterList = monsters;
//    }
//
//    @Override
//    protected void readData(DataInputStream dis) throws IOException {
//        monsterList = new ArrayList<>();
//        for (int i =0 ; i < dis.readShort(); i++) {
////            Monster monster = new Monster(
////                    dis.readInt(),
////                    dis.readFloat(),
////                    dis.readFloat(),
//////                    null,
////                    -1,-1, -1);
////            monster.setHp()
//        }
//    }
//
//    @Override
//    public void writeData(DataOutputStream dos) throws IOException {
//        dos.writeShort(monsterList.size());
//        for (Monster monster : monsterList) {
//            dos.writeInt(monster.getId());
//            dos.writeFloat(monster.getLocation().x);
//            dos.writeFloat(monster.getLocation().y);
//            dos.writeShort(monster.getCurrentHp());
//            dos.writeShort(monster.getMaxHp());
//            dos.writeUTF(monster.getTarget());
//        }
//    }
//}
