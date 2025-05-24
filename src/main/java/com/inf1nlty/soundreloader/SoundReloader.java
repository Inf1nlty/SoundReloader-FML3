package com.inf1nlty.soundreloader;

import net.minecraft.Minecraft;
import org.lwjgl.input.Keyboard;
import java.lang.reflect.Method;

public class SoundReloader {
    private static boolean isKeyPressed = false;
    private static final int RELOAD_KEY = Keyboard.KEY_H;

    public static void tick() {
        try {
            boolean currentlyPressed = Keyboard.isKeyDown(RELOAD_KEY);
            if (currentlyPressed && !isKeyPressed) {
                isKeyPressed = true;
                reloadSound();
            } else if (!currentlyPressed) {
                isKeyPressed = false;
            }
        } catch (Exception e) {}
    }

    private static void reloadSound() {
        try {
            Minecraft mc = Minecraft.getMinecraft();
            if (mc == null || mc.sndManager == null) return;

            mc.sndManager.cleanup();

            Method method = mc.sndManager.getClass().getDeclaredMethod("tryToSetLibraryAndCodecs");
            method.setAccessible(true);
            method.invoke(mc.sndManager);
        } catch (Exception e) {}
    }
}