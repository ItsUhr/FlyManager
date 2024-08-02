package de.itsuhr;

import de.itsuhr.commands.FlyCommand;
import de.itsuhr.config.MessageConfig;
import de.itsuhr.config.PermissionConfig;
import me.syntaxjason.ConfigurationFactory;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class Main extends JavaPlugin {

    private static Main instance;

    private MessageConfig messageConfig;
    private PermissionConfig permissionConfig;
    private String prefix = "§7[§eFly§7]";

    @Override
    public void onEnable() {

        instance = this;

        if(!getDataFolder().exists()) getDataFolder().mkdir();
        try {
        if(!new File(getDataFolder(), "messages.yml").exists()) {
            new File(getDataFolder(), "messages.yml").createNewFile();
        }
        if(!new File(getDataFolder(), "permissions.yml").exists()) {
                new File(getDataFolder(), "permissions.yml").createNewFile();
        }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            this.messageConfig = ConfigurationFactory.createConfig(new File(getDataFolder(), "messages.yml"), MessageConfig.class);
            this.permissionConfig = ConfigurationFactory.createConfig(new File(getDataFolder(), "permissions.yml"), PermissionConfig.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        FlyCommand flyCommand = new FlyCommand(this);
        this.getCommand("fly").setExecutor(flyCommand);

        messageEnable();
    }

    @Override
    public void onDisable() {
        instance = null;
        messageDisable();
    }

    private void messageEnable() {
        getServer().getConsoleSender().sendMessage("§f[]==============[ §4§l" + getDescription().getName() + " §f]==============[]");
        getServer().getConsoleSender().sendMessage("§f[]===[ §aDas Plugin wurde erfolgreich aktiviert.");
        getServer().getConsoleSender().sendMessage("§f[]===[ §aDer Plugin Author ist §bItsUhr");
        getServer().getConsoleSender().sendMessage("§f[]===[ §aDas Plugin ist in der Version §51.20");
        getServer().getConsoleSender().sendMessage("§f[]==============[ §4§l" + getDescription().getName() + " §f]==============[]");
    }

    private void messageDisable() {
        getServer().getConsoleSender().sendMessage("§f[]==============[ §4§l" + getDescription().getName() + " §f]==============[]");
        getServer().getConsoleSender().sendMessage("§f[]===[ §cDas Plugin wurde erfolgreich deaktiviert.");
        getServer().getConsoleSender().sendMessage("§f[]===[ §cDer Plugin Author ist §bItsUhr");
        getServer().getConsoleSender().sendMessage("§f[]===[ §cDas Plugin ist in der Version §51.20");
        getServer().getConsoleSender().sendMessage("§f[]==============[ §4§l" + getDescription().getName() + " §f]==============[]");
    }

    @Override
    public void reloadConfig() {
        messageConfig.reload();
        permissionConfig.reload();
    }

    public static Main getInstance() {
        return instance;
    }

    public MessageConfig getMessageConfig() {
        return messageConfig;
    }

    public PermissionConfig getPermissionConfig() {
        return permissionConfig;
    }

    public String getPrefix() {
        return prefix;
    }
}
