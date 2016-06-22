package com.groeps33.valley.net.packet;

/**
 * Created by Bram on 6/15/2016.
 *
 * Indicates what kind of content a packet includes.
 *
 * @author Bram Hoendervangers
 */
public enum PacketType {
    CONNECT, DISCONNECT, PROJECTILES, PLAYER_HIT, REQUEST_UPDATE, MONSTERS, MONSTER_TARGET_UPDATE, NEW_WAVE, MOVE
}
