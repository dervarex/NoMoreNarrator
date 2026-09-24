package com.dervarex.nomorenarrator.mixin.client;

import com.mojang.text2speech.Narrator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Narrator.class)
public interface NarratorManagerMixin {

    @Inject(method = "active", at = @At("HEAD"), cancellable = true)
    private void disableNarrator(CallbackInfoReturnable<Boolean> cir) {
        cir.setReturnValue(false);
    }
}
