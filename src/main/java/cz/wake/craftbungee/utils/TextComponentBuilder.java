package cz.wake.craftbungee.utils;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

import java.util.UUID;

public class TextComponentBuilder {

    private TextComponent component;

    public TextComponentBuilder(String message) {
        this.component = new TextComponent(message);
    }

    public TextComponentBuilder(String message, boolean centered) {
        this.component = new TextComponent(centered ? Utils.getCenteredMessage(message) : message);
    }

    public TextComponentBuilder setPerformedCommand(String command) {
        if (!command.startsWith("/")) command = "/" + command;

        this.component.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, command));
        return this;
    }

    public TextComponentBuilder setTooltip(String tooltip) {
        this.component.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, TextComponent.fromLegacyText(tooltip)));
        return this;
    }

    public TextComponentBuilder suggestCommand(String command) {
        if (!command.startsWith("/")) command = "/" + command;
        if (!command.endsWith(" ")) command = command + " ";

        this.component.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, command));

        return this;
    }

    public void send(ProxiedPlayer player){
        player.sendMessage(component);
    }

    public void send(CommandSender sender) {
        sender.sendMessage(component);
    }

    public TextComponent getComponent() {
        return component;
    }
}

