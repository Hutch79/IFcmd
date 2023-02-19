package ch.hutch79;

import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

public class Command implements CommandExecutor {

    private final Main main = Main.getInstance();
    private final CommandExecuter commandExecuter = Main.getCommandExecuter();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull org.bukkit.command.Command command, @NotNull String label, @NotNull String[] args) {

        sender.sendMessage("args: " + Arrays.toString(args));
        if (args.length == 0) return false;

        if (sender instanceof ConsoleCommandSender) {
            return false;
        }

        if (args[0].equalsIgnoreCase("reload") && sender.hasPermission("fcommand.admin")) {
            Main.getCommandExecuter().loadConfig();
            sender.sendMessage("§dF-Command §8> §7Config has been reloaded");
        }
        if (args[0].equalsIgnoreCase("execute")) {
            sender.sendMessage("execute == true");
            if (Integer.parseInt(args[1]) >= 0) {
                sender.sendMessage("next true");
                sender.sendMessage("Hui");

                if (commandExecuter.doCommand(Integer.parseInt(args[1]), sender) == 0) {
                    sender.sendMessage("Judihui");
                }
                else {
                    sender.sendMessage("Och neeeee");
                }
            }



        }


        return false;
    }
}
