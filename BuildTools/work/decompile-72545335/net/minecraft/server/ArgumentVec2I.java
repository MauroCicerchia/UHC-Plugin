package net.minecraft.server;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
import com.mojang.brigadier.suggestion.Suggestions;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.CompletableFuture;

public class ArgumentVec2I implements ArgumentType<IVectorPosition> {

    private static final Collection<String> b = Arrays.asList(new String[] { "0 0", "~ ~", "~1 ~-2", "^ ^", "^-1 ^0"});
    public static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("argument.pos2d.incomplete", new Object[0]));

    public ArgumentVec2I() {}

    public static ArgumentVec2I a() {
        return new ArgumentVec2I();
    }

    public static ArgumentVec2I.a a(CommandContext<CommandListenerWrapper> commandcontext, String s) {
        BlockPosition blockposition = ((IVectorPosition) commandcontext.getArgument(s, IVectorPosition.class)).c((CommandListenerWrapper) commandcontext.getSource());

        return new ArgumentVec2I.a(blockposition.getX(), blockposition.getZ());
    }

    public IVectorPosition parse(StringReader stringreader) throws CommandSyntaxException {
        int i = stringreader.getCursor();

        if (!stringreader.canRead()) {
            throw ArgumentVec2I.a.createWithContext(stringreader);
        } else {
            ArgumentParserPosition argumentparserposition = ArgumentParserPosition.a(stringreader);

            if (stringreader.canRead() && stringreader.peek() == 32) {
                stringreader.skip();
                ArgumentParserPosition argumentparserposition1 = ArgumentParserPosition.a(stringreader);

                return new VectorPosition(argumentparserposition, new ArgumentParserPosition(true, 0.0D), argumentparserposition1);
            } else {
                stringreader.setCursor(i);
                throw ArgumentVec2I.a.createWithContext(stringreader);
            }
        }
    }

    public <S> CompletableFuture<Suggestions> listSuggestions(CommandContext<S> commandcontext, SuggestionsBuilder suggestionsbuilder) {
        if (!(commandcontext.getSource() instanceof ICompletionProvider)) {
            return Suggestions.empty();
        } else {
            String s = suggestionsbuilder.getRemaining();
            Object object;

            if (!s.isEmpty() && s.charAt(0) == 94) {
                object = Collections.singleton(ICompletionProvider.a.a);
            } else {
                object = ((ICompletionProvider) commandcontext.getSource()).a(false);
            }

            return ICompletionProvider.b(s, (Collection) object, suggestionsbuilder, CommandDispatcher.a(this::parse));
        }
    }

    public Collection<String> getExamples() {
        return ArgumentVec2I.b;
    }

    public static class a {

        public final int a;
        public final int b;

        public a(int i, int j) {
            this.a = i;
            this.b = j;
        }

        public String toString() {
            return "[" + this.a + ", " + this.b + "]";
        }
    }
}
