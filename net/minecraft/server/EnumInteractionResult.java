package net.minecraft.server;

public enum EnumInteractionResult {

    SUCCESS, CONSUME, PASS, FAIL;

    private EnumInteractionResult() {}

    public boolean a() {
        return this == EnumInteractionResult.SUCCESS || this == EnumInteractionResult.CONSUME;
    }

    public boolean b() {
        return this == EnumInteractionResult.SUCCESS;
    }
}
