package com.dervarex.nomorenarrator.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.option.NarratorMode;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class NomorenarratorClient implements ClientModInitializer {
    private boolean narratorShortcutHeld;

    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            // Narrator off
            if (client.options.getNarrator().getValue() != NarratorMode.OFF) {
                client.options.getNarrator().setValue(NarratorMode.OFF);
            }

            // deactivate hotkey
            if (client.options.getNarratorHotkey().getValue()) {
                client.options.getNarratorHotkey().setValue(false);
            }
        });
    }
}
