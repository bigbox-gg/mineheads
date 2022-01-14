package gg.bigbox.minecraft.plugins.mineheads.minestom;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;

public class Utils {

    public static Component sendErrorMessage(Component message) {
        return Component.text("[MINEHEADS] ", NamedTextColor.RED)
                .append(message);
    }

    public static boolean stringMatch(String s1, String s2) {
        return s1.toLowerCase().contains(s2.toLowerCase());
    }

}
