package ch.hutch79;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class CommandExecuter {

    private Main main;
    public List<String> commandOptions;
    private boolean debug = false;

    public void CommandExecuterInit(Main main) {
        this.main = main;
    }

    public void loadConfig() {
        main.reloadConfig();
        Set<String> commandOptions2 = Objects.requireNonNull(main.getConfig().getConfigurationSection("command")).getKeys(false);
        commandOptions = new ArrayList<>(commandOptions2.size());
        commandOptions.addAll(commandOptions2);

        debug = main.getConfig().getBoolean("debug");

        if (debug) {Bukkit.getConsoleSender().sendMessage("§cFcmd-debug §8> §7commandOptions (Total: " + commandOptions.size() + ") list: §e" + commandOptions);}

        Bukkit.getConsoleSender().sendMessage("§dF-Command §8> §7Loaded Commands: " + commandOptions);

    }

    public String getInfo(int count, String value){

        String result = main.getConfig().getString("command." + commandOptions.get(count) + "." + value);

        if(result == null) {
            main.getLogger().warning("The Value " + value + " for the Command " + commandOptions.get(count) + " is not set!");
            if (debug) {Bukkit.getConsoleSender().sendMessage("§cFcmd-debug §8> §cgetInfo failed. §7 count: " + count + "value: " + value);}
            return "defaultValue";
        }
        if (debug) {Bukkit.getConsoleSender().sendMessage("§cFcmd-debug §8> §7getInfo Success");}

        return result;
    }

    public int doCommand(int commandNr, CommandSender player) {

        if (debug) {Bukkit.getConsoleSender().sendMessage("§cFcmd-debug §8> §7Executed doCommand. CommandNr: " + commandNr);}

        if (commandNr > commandOptions.size()) { // Impossible to execute
            if (debug) {Bukkit.getConsoleSender().sendMessage("§cFcmd-debug §8> §7return. commandNr < commandOption size");}

            return -1;
        }
        if (debug) {Bukkit.getConsoleSender().sendMessage("§cFcmd-debug §8> §7Not returned. commandNr < commandOption size");}



        if (getInfo(commandNr, "type").equalsIgnoreCase("advanced")) {
            if (debug) {Bukkit.getConsoleSender().sendMessage("§cFcmd-debug §8> §7Entered advanced command handler");}

            try {
                if (true) { // ToDo: Advanced option. Convert String to Statement for if else
                    main.getLogger().info("§cSuccess!!!!");
                }
                else {
                    main.getLogger().info("§cFailed!!!!");
                }
                return 0;
            } catch (Exception exception) {
                main.getLogger().warning("The condition of command " + commandOptions.get(commandNr) + " does not make any sense. Please correct your condition and try again.");
                exception.printStackTrace();
                return -1;
            }
        }



        if (debug) {Bukkit.getConsoleSender().sendMessage("§cFcmd-debug §8> §7Entered common command handler");}
        switch (getInfo(commandNr, "type")) {

            case "<":
                if (debug) {Bukkit.getConsoleSender().sendMessage("§cFcmd-debug §8> §7Entered command handler <");}

                try {
                    if (Integer.parseInt(main.replacePlaceholders((Player) player, getInfo(commandNr, "condition1"))) < Integer.parseInt(main.replacePlaceholders((Player) player, getInfo(commandNr, "condition2")))) {
                        main.getLogger().info("§cSuccess!!!!");

                    }
                    else {
                        main.getLogger().info("§cFailed!!!!");

                    }
                    return 0;
                } catch (Exception exception) {
                    main.getLogger().warning("The condition of command " + commandOptions.get(commandNr) + " does not make any sense. Please correct your condition and try again.");
                    exception.printStackTrace();
                    return -1;
                }

            case "<=":

                break;

            case ">":

                break;

            case ">=":

                break;

            case "==":

                break;

            case "equalsignorecase":

                break;

            case "equals":

                break;

        }

        return 0;
    }
}
