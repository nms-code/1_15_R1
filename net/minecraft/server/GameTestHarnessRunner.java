package net.minecraft.server;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Streams;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class GameTestHarnessRunner {

    public static GameTestHarnessITestReporter a = new GameTestHarnessLogger();

    public static void a(GameTestHarnessInfo gametestharnessinfo, GameTestHarnessTicker gametestharnessticker) {
        gametestharnessinfo.a();
        gametestharnessticker.a(gametestharnessinfo);
        gametestharnessinfo.a(new GameTestHarnessListener() {
            @Override
            public void a(GameTestHarnessInfo gametestharnessinfo1) {
                GameTestHarnessRunner.b(gametestharnessinfo1, Blocks.LIGHT_GRAY_STAINED_GLASS);
            }

            @Override
            public void c(GameTestHarnessInfo gametestharnessinfo1) {
                GameTestHarnessRunner.b(gametestharnessinfo1, gametestharnessinfo1.q() ? Blocks.RED_STAINED_GLASS : Blocks.ORANGE_STAINED_GLASS);
                GameTestHarnessRunner.b(gametestharnessinfo1, SystemUtils.d(gametestharnessinfo1.n()));
                GameTestHarnessRunner.c(gametestharnessinfo1);
            }
        });
        gametestharnessinfo.a(2);
    }

    public static Collection<GameTestHarnessInfo> a(Collection<GameTestHarnessBatch> collection, BlockPosition blockposition, WorldServer worldserver, GameTestHarnessTicker gametestharnessticker) {
        GameTestHarnessBatchRunner gametestharnessbatchrunner = new GameTestHarnessBatchRunner(collection, blockposition, worldserver, gametestharnessticker);

        gametestharnessbatchrunner.b();
        return gametestharnessbatchrunner.a();
    }

    public static Collection<GameTestHarnessInfo> b(Collection<GameTestHarnessTestFunction> collection, BlockPosition blockposition, WorldServer worldserver, GameTestHarnessTicker gametestharnessticker) {
        return a(a(collection), blockposition, worldserver, gametestharnessticker);
    }

    public static Collection<GameTestHarnessBatch> a(Collection<GameTestHarnessTestFunction> collection) {
        Map<String, Collection<GameTestHarnessTestFunction>> map = Maps.newHashMap();

        collection.forEach((gametestharnesstestfunction) -> {
            String s = gametestharnesstestfunction.e();
            Collection<GameTestHarnessTestFunction> collection1 = (Collection) map.computeIfAbsent(s, (s1) -> {
                return Lists.newArrayList();
            });

            collection1.add(gametestharnesstestfunction);
        });
        return (Collection) map.keySet().stream().flatMap((s) -> {
            Collection<GameTestHarnessTestFunction> collection1 = (Collection) map.get(s);
            Consumer<WorldServer> consumer = GameTestHarnessRegistry.c(s);
            AtomicInteger atomicinteger = new AtomicInteger();

            return Streams.stream(Iterables.partition(collection1, 100)).map((list) -> {
                return new GameTestHarnessBatch(s + ":" + atomicinteger.incrementAndGet(), collection1, consumer);
            });
        }).collect(Collectors.toList());
    }

    private static void c(GameTestHarnessInfo gametestharnessinfo) {
        Throwable throwable = gametestharnessinfo.n();
        String s = gametestharnessinfo.c() + " failed! " + SystemUtils.d(throwable);

        a(gametestharnessinfo.g(), EnumChatFormat.RED, s);
        if (throwable instanceof GameTestHarnessAssertionPosition) {
            GameTestHarnessAssertionPosition gametestharnessassertionposition = (GameTestHarnessAssertionPosition) throwable;

            a(gametestharnessinfo.g(), gametestharnessassertionposition.c(), gametestharnessassertionposition.a());
        }

        GameTestHarnessRunner.a.a(gametestharnessinfo);
    }

    private static void b(GameTestHarnessInfo gametestharnessinfo, Block block) {
        WorldServer worldserver = gametestharnessinfo.g();
        BlockPosition blockposition = gametestharnessinfo.d();
        BlockPosition blockposition1 = blockposition.b(-1, -1, -1);

        worldserver.setTypeUpdate(blockposition1, Blocks.BEACON.getBlockData());
        BlockPosition blockposition2 = blockposition1.b(0, 1, 0);

        worldserver.setTypeUpdate(blockposition2, block.getBlockData());

        for (int i = -1; i <= 1; ++i) {
            for (int j = -1; j <= 1; ++j) {
                BlockPosition blockposition3 = blockposition1.b(i, -1, j);

                worldserver.setTypeUpdate(blockposition3, Blocks.IRON_BLOCK.getBlockData());
            }
        }

    }

    private static void b(GameTestHarnessInfo gametestharnessinfo, String s) {
        WorldServer worldserver = gametestharnessinfo.g();
        BlockPosition blockposition = gametestharnessinfo.d();
        BlockPosition blockposition1 = blockposition.b(-1, 1, -1);

        worldserver.setTypeUpdate(blockposition1, Blocks.LECTERN.getBlockData());
        IBlockData iblockdata = worldserver.getType(blockposition1);
        ItemStack itemstack = a(gametestharnessinfo.c(), gametestharnessinfo.q(), s);

        BlockLectern.a((World) worldserver, blockposition1, iblockdata, itemstack);
    }

    private static ItemStack a(String s, boolean flag, String s1) {
        ItemStack itemstack = new ItemStack(Items.WRITABLE_BOOK);
        NBTTagList nbttaglist = new NBTTagList();
        StringBuffer stringbuffer = new StringBuffer();

        Arrays.stream(s.split("\\.")).forEach((s2) -> {
            stringbuffer.append(s2).append('\n');
        });
        if (!flag) {
            stringbuffer.append("(optional)\n");
        }

        stringbuffer.append("-------------------\n");
        nbttaglist.add(NBTTagString.a(stringbuffer.toString() + s1));
        itemstack.a("pages", (NBTBase) nbttaglist);
        return itemstack;
    }

    private static void a(WorldServer worldserver, EnumChatFormat enumchatformat, String s) {
        worldserver.a((entityplayer) -> {
            return true;
        }).forEach((entityplayer) -> {
            entityplayer.sendMessage((new ChatComponentText(s)).a(enumchatformat));
        });
    }

    public static void a(WorldServer worldserver) {
        PacketDebug.a(worldserver);
    }

    private static void a(WorldServer worldserver, BlockPosition blockposition, String s) {
        PacketDebug.a(worldserver, blockposition, s, -2130771968, Integer.MAX_VALUE);
    }

    public static void a(WorldServer worldserver, BlockPosition blockposition, GameTestHarnessTicker gametestharnessticker, int i) {
        gametestharnessticker.a();
        BlockPosition blockposition1 = blockposition.b(-i, 0, -i);
        BlockPosition blockposition2 = blockposition.b(i, 0, i);

        BlockPosition.b(blockposition1, blockposition2).filter((blockposition3) -> {
            return worldserver.getType(blockposition3).getBlock() == Blocks.STRUCTURE_BLOCK;
        }).forEach((blockposition3) -> {
            TileEntityStructure tileentitystructure = (TileEntityStructure) worldserver.getTileEntity(blockposition3);
            BlockPosition blockposition4 = tileentitystructure.getPosition();
            StructureBoundingBox structureboundingbox = GameTestHarnessStructures.a(blockposition4, tileentitystructure.j(), 2);

            GameTestHarnessStructures.a(structureboundingbox, blockposition4.getY(), worldserver);
        });
    }
}
