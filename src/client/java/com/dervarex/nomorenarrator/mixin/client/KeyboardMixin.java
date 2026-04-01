package com.dervarex.nomorenarrator.mixin.client;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.Keyboard;
import org.lwjgl.glfw.GLFW;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Keyboard.class)
public class KeyboardMixin {

    @Inject(method = "onKey", at = @At("HEAD"), cancellable = true)
    private void blockNarratorShortcut(long window, int key, int scancode, int action, int modifiers, CallbackInfo ci) {
        MinecraftClient client = MinecraftClient.getInstance();
        if (client == null || client.getWindow().getHandle() != window) {
            return;
        }

        boolean ctrlDown = (modifiers & GLFW.GLFW_MOD_CONTROL) != 0;
        if (key == GLFW.GLFW_KEY_B && ctrlDown && (action == GLFW.GLFW_PRESS || action == GLFW.GLFW_REPEAT)) {
            ci.cancel();
        }
    }
}
