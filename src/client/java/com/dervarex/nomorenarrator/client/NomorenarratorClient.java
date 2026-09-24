package com.dervarex.nomorenarrator.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.NarratorStatus;

public class NomorenarratorClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            // Narrator off
            if (client.options.narrator().get() != NarratorStatus.OFF) {
                client.options.narrator().set(NarratorStatus.OFF);
            }

            // deactivate hotkey
            if (client.options.narratorHotkey().get()) {
                client.options.narratorHotkey().set(false);
            }
        });
    }
}