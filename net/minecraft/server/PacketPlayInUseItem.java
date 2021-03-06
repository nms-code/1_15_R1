package net.minecraft.server;

import java.io.IOException;

public class PacketPlayInUseItem implements Packet<PacketListenerPlayIn> {

    private MovingObjectPositionBlock a;
    private EnumHand b;
    public long timestamp;

    public PacketPlayInUseItem() {}

    @Override
    public void a(PacketDataSerializer packetdataserializer) throws IOException {
        this.timestamp = System.currentTimeMillis(); // Spigot
        this.b = (EnumHand) packetdataserializer.a(EnumHand.class);
        this.a = packetdataserializer.q();
    }

    @Override
    public void b(PacketDataSerializer packetdataserializer) throws IOException {
        packetdataserializer.a((Enum) this.b);
        packetdataserializer.a(this.a);
    }

    public void a(PacketListenerPlayIn packetlistenerplayin) {
        packetlistenerplayin.a(this);
    }

    public EnumHand b() {
        return this.b;
    }

    public MovingObjectPositionBlock c() {
        return this.a;
    }
}
