package net.minecraft.server;

public class DebugReportFluidTags extends DebugReportTags<FluidType> {

    public DebugReportFluidTags(DebugReportGenerator debugreportgenerator) {
        super(debugreportgenerator, IRegistry.FLUID);
    }

    protected void b() {
        this.a(TagsFluid.WATER).a((Object[])(FluidTypes.c, FluidTypes.b));
        this.a(TagsFluid.LAVA).a((Object[])(FluidTypes.e, FluidTypes.d));
    }

    protected java.nio.file.Path a(MinecraftKey minecraftkey) {
        return this.b.b().resolve("data/" + minecraftkey.b() + "/tags/fluids/" + minecraftkey.getKey() + ".json");
    }

    public String a() {
        return "Fluid Tags";
    }

    protected void a(Tags<FluidType> tags) {
        TagsFluid.a(tags);
    }
}
