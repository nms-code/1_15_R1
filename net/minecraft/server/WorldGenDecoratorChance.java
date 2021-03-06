package net.minecraft.server;

import com.mojang.datafixers.Dynamic;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Stream;

public class WorldGenDecoratorChance extends WorldGenDecorator<WorldGenDecoratorDungeonConfiguration> {

    public WorldGenDecoratorChance(Function<Dynamic<?>, ? extends WorldGenDecoratorDungeonConfiguration> function) {
        super(function);
    }

    public Stream<BlockPosition> a(GeneratorAccess generatoraccess, ChunkGenerator<? extends GeneratorSettingsDefault> chunkgenerator, Random random, WorldGenDecoratorDungeonConfiguration worldgendecoratordungeonconfiguration, BlockPosition blockposition) {
        if (random.nextFloat() < 1.0F / (float) worldgendecoratordungeonconfiguration.a) {
            int i = random.nextInt(16) + blockposition.getX();
            int j = random.nextInt(16) + blockposition.getZ();
            int k = generatoraccess.a(HeightMap.Type.MOTION_BLOCKING, i, j);

            return Stream.of(new BlockPosition(i, k, j));
        } else {
            return Stream.empty();
        }
    }
}
