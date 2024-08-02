package de.itsuhr.commands;

import de.itsuhr.Main;
import de.itsuhr.config.MessageConfig;
import de.itsuhr.config.PermissionConfig;
import de.itsuhr.util.BukkitColor;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlyCommand implements CommandExecutor, TabCompleter {

    private final Main plugin;

    private final MessageConfig messageConfig;
    private final PermissionConfig permissionConfig;

    public FlyCommand(Main plugin) {
        this.plugin = plugin;
        this.messageConfig = plugin.getMessageConfig();
        this.permissionConfig = plugin.getPermissionConfig();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (!(sender instanceof Player player)) {
            sender.sendMessage("§cDieser Befehl kann nur von Spielern ausgeführt werden!");
            return true;
        }

        if (!player.hasPermission(permissionConfig.getFlyPermission())) {
            player.sendMessage(BukkitColor.apply(messageConfig.getNoPerms()));
            return false;
        }
        if (args.length == 0) {
            if (player.getAllowFlight()) {
                player.setAllowFlight(false);
                player.setFlying(false);
                sendActionBar(player, BukkitColor.apply(messageConfig.getFlyDisabled()));
                player.sendMessage(BukkitColor.apply(messageConfig.getFlyDisabled()));
                return true;
            }
            player.setAllowFlight(true);
            sendActionBar(player, BukkitColor.apply(messageConfig.getFlyEnabled()));
            player.sendMessage(BukkitColor.apply(messageConfig.getFlyEnabled()));
        }
        if (args.length == 1) {
            switch (args[0].toLowerCase()) {
                case "reload" -> {
                    if (!player.hasPermission(permissionConfig.getFlyReloadPermission())) {
                        player.sendMessage(BukkitColor.apply(messageConfig.getNoPerms()));
                        return false;
                    }
                    plugin.reloadConfig();
                    player.sendMessage(BukkitColor.apply(messageConfig.getConfigReload()));
                }
                case "help" -> {
                    if (!player.hasPermission(permissionConfig.getFlyHelpPermission())) {
                        player.sendMessage(BukkitColor.apply(messageConfig.getNoPerms()));
                        return false;
                    }
                    player.sendMessage(BukkitColor.apply( messageConfig.getHelp()));
                }
                default -> {
                    if (!player.hasPermission(permissionConfig.getFlyOtherPermission())) {
                        player.sendMessage(BukkitColor.apply(messageConfig.getNoPerms()));
                        return false;
                    }
                    Player target = Bukkit.getPlayer(args[0]);
                    if (target == null) {
                        player.sendMessage(BukkitColor.apply(messageConfig.getTargetNotFound().replace("%player%", args[0])));
                        return false;
                    }
                    if (target.getAllowFlight()) {
                        target.setAllowFlight(false);
                        target.setFlying(false);
                        sendActionBar(player, messageConfig.getTargetFlyDisable().replace("%player%", target.getName()));
                        sendActionBar(target, messageConfig.getFlyDisabled());
                        player.sendMessage(BukkitColor.apply(messageConfig.getTargetFlyDisable().replace("%player%", target.getName())));
                        target.sendMessage(BukkitColor.apply(messageConfig.getFlyDisabled()));
                        return true;
                    }
                    target.setAllowFlight(true);
                    sendActionBar(player, messageConfig.getTargetFlyEnable().replace("%player%", target.getName()));
                    sendActionBar(target, messageConfig.getFlyEnabled());
                    player.sendMessage(BukkitColor.apply(messageConfig.getTargetFlyEnable().replace("%player%", target.getName())));
                    target.sendMessage(BukkitColor.apply(messageConfig.getFlyEnabled()));
                }
            }
        }

        return true;
    }

    private void sendActionBar(Player player, String message) {
        player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(BukkitColor.apply(message)));

    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (args.length >= 1) {
            ArrayList<String> arrayList = new ArrayList<>();
            arrayList.add("reload");
            arrayList.add("help");
            arrayList.addAll(Bukkit.getOnlinePlayers().stream().map(Player::getName).collect(Collectors.toList()));
            return arrayList;
        }
        return List.of();
    }
}
