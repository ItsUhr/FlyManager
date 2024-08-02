package de.itsuhr.config;

import java.util.ArrayList;
import java.util.List;

@ConfigSource(type = ConfigurationType.YAML)
public interface MessageConfig {

    @ConfigParam(name = "noPerms", defaultValue = "&7[&eFly&7] &cDu hast keine Berechtigung, dies zu tun!")
    String getNoPerms();

    @ConfigParam(name = "flyDisabled", defaultValue = "&7[&eFly&7] &7Dein &e&lFlugmodus &7wurde leider &c&lDeaktiviert&7.")
    String getFlyDisabled();

    @ConfigParam(name = "flyEnabled", defaultValue = "&7[&eFly&7] &7Dein &e&lFlugmodus &7wurde erfolgreich &a&lAktiviert&7.")
    String getFlyEnabled();

    @ConfigParam(name = "help", defaultValue = """
                - "&6=========[ Fly Plugin Hilfe ]========="
                - "&e/fly          - Aktiviert/Deaktiviert den Flugmodus."
                - "&e/fly <Spieler>"
                - "&e/fly help     - Zeigt diese Hilfemeldung an."
                - "&e/fly reload - startet das plugin neu."
                - "&6====================================="
            """)
    String getHelp();

    @ConfigParam(name = "playerOffline", defaultValue = "&7[&eFly&7] &4Fehler: &cDer Spieler &b&l%player% &cist nicht vorhanden!")
    String getPlayerOffline();

    @ConfigParam(name = "targetNotFound", defaultValue = "&7[&eFly&7] &7Der Spieler &b&l%player% &f&lexistiert &c&lnicht&7!")
    String getTargetNotFound();

    @ConfigParam(name = "targetFlyDisable", defaultValue = "&7[&eFly&7] &7Der Spieler &b&l%player% &e&lFlugmodus &7wurde &c&lDeaktiviert&7.")
    String getTargetFlyDisable();

    @ConfigParam(name = "targetFlyEnable", defaultValue = "&7[&eFly&7] &7Der Spieler &b&l%player% &e&lFlugmodus &7wurde &a&lAktiviert&7.")
    String getTargetFlyEnable();

    @ConfigParam(name = "configReload", defaultValue = "&7[&eFly&7] &aKonfiguration wurde neugeladen!")
    String getConfigReload();

    void reload();




}
