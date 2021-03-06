package net.minecraft.server;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
import java.util.Arrays;
import java.util.Collection;
import java.util.function.Function;

public class ArgumentNBTTag implements ArgumentType<NBTTagCompound> {

    private static final Collection<String> b = Arrays.asList(new String[] { "{}", "{foo=bar}"});
    public static final DynamicCommandExceptionType a = new DynamicCommandExceptionType((object) -> {
        return new ChatMessage("argument.nbt.invalid", new Object[] { object});
    });

    private ArgumentNBTTag() {}

    public static ArgumentNBTTag a() {
        return new ArgumentNBTTag();
    }

    public static <S> NBTTagCompound a(CommandContext<S> commandcontext, String s) {
        return (NBTTagCompound) commandcontext.getArgument(s, NBTTagCompound.class);
    }

    public NBTTagCompound parse(StringReader stringreader) throws CommandSyntaxException {
        return (new MojangsonParser(stringreader)).f();
    }

    public Collection<String> getExamples() {
        return ArgumentNBTTag.b;
    }
}
