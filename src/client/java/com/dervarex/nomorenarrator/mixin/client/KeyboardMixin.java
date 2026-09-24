package com.dervarex.nomorenarrator.mixin.client;

import net.minecraft.client.Keyboard;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.input.KeyInput;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class KeyboardMixin {

    @Inject(method = "onKey", at = @At("HEAD"), cancellable = true)
    private void blockNarratorShortcut(long window, int action, KeyInput input, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.getWindow().getHandle() != window) {
            return;
        }

        boolean ctrlDown = input.hasCtrlOrCmd();
        if (input.key() == GLFW.GLFW_KEY_B && ctrlDown && (action == GLFW.GLFW_PRESS || action == GLFW.GLFW_REPEAT)) {
            ci.cancel();
        }
    }
}