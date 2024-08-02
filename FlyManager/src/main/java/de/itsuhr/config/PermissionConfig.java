package de.itsuhr.config;

import de.itsuhr.Main;
import me.syntaxjason.ConfigurationType;
import me.syntaxjason.annotation.ConfigParam;
import me.syntaxjason.annotation.ConfigSource;

@ConfigSource(type = ConfigurationType.YAML)
public interface PermissionConfig {

    @ConfigParam(name = "flyPermission", defaultValue = "flymanager.fly.self")
    String getFlyPermission();
    @ConfigParam(name = "flyOtherPermission", defaultValue = "flymanager.fly.other")
    String getFlyOtherPermission();
    @ConfigParam(name = "flyOtherPermission", defaultValue = "flymanager.reload")
    String getFlyReloadPermission();
    @ConfigParam(name = "flyOtherPermission", defaultValue = "flymanager.help")
    String getFlyHelpPermission();

    void reload();

}
