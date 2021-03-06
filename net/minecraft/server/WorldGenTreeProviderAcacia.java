package net.minecraft.server;

import java.util.Random;
import javax.annotation.Nullable;

public class WorldGenTreeProviderAcacia extends WorldGenTreeProvider {

    public WorldGenTreeProviderAcacia() {}

    @Nullable
    @Override
    protected WorldGenFeatureConfigured<WorldGenFeatureSmallTreeConfigurationConfiguration, ?> a(Random random, boolean flag) {
        return WorldGenerator.ACACIA_TREE.b((WorldGenFeatureConfiguration) BiomeDecoratorGroups.ACACIA_TREE);
    }
}
