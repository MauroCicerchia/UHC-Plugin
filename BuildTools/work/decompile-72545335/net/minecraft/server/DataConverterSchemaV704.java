package net.minecraft.server;

import com.google.common.collect.Maps;
import com.mojang.datafixers.DSL;
import com.mojang.datafixers.DataFixUtils;
import com.mojang.datafixers.Dynamic;
import com.mojang.datafixers.DSL.TypeReference;
import com.mojang.datafixers.schemas.Schema;
import com.mojang.datafixers.types.DynamicOps;
import com.mojang.datafixers.types.Type;
import com.mojang.datafixers.types.templates.TypeTemplate;
import com.mojang.datafixers.types.templates.Hook.HookFunction;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

public class DataConverterSchemaV704 extends Schema {

    protected static final Map<String, String> a = (Map) DataFixUtils.make(Maps.newHashMap(), (hashmap) -> {
        hashmap.put("minecraft:furnace", "minecraft:furnace");
        hashmap.put("minecraft:lit_furnace", "minecraft:furnace");
        hashmap.put("minecraft:chest", "minecraft:chest");
        hashmap.put("minecraft:trapped_chest", "minecraft:chest");
        hashmap.put("minecraft:ender_chest", "minecraft:ender_chest");
        hashmap.put("minecraft:jukebox", "minecraft:jukebox");
        hashmap.put("minecraft:dispenser", "minecraft:dispenser");
        hashmap.put("minecraft:dropper", "minecraft:dropper");
        hashmap.put("minecraft:sign", "minecraft:sign");
        hashmap.put("minecraft:mob_spawner", "minecraft:mob_spawner");
        hashmap.put("minecraft:noteblock", "minecraft:noteblock");
        hashmap.put("minecraft:brewing_stand", "minecraft:brewing_stand");
        hashmap.put("minecraft:enhanting_table", "minecraft:enchanting_table");
        hashmap.put("minecraft:command_block", "minecraft:command_block");
        hashmap.put("minecraft:beacon", "minecraft:beacon");
        hashmap.put("minecraft:skull", "minecraft:skull");
        hashmap.put("minecraft:daylight_detector", "minecraft:daylight_detector");
        hashmap.put("minecraft:hopper", "minecraft:hopper");
        hashmap.put("minecraft:banner", "minecraft:banner");
        hashmap.put("minecraft:flower_pot", "minecraft:flower_pot");
        hashmap.put("minecraft:repeating_command_block", "minecraft:command_block");
        hashmap.put("minecraft:chain_command_block", "minecraft:command_block");
        hashmap.put("minecraft:shulker_box", "minecraft:shulker_box");
        hashmap.put("minecraft:white_shulker_box", "minecraft:shulker_box");
        hashmap.put("minecraft:orange_shulker_box", "minecraft:shulker_box");
        hashmap.put("minecraft:magenta_shulker_box", "minecraft:shulker_box");
        hashmap.put("minecraft:light_blue_shulker_box", "minecraft:shulker_box");
        hashmap.put("minecraft:yellow_shulker_box", "minecraft:shulker_box");
        hashmap.put("minecraft:lime_shulker_box", "minecraft:shulker_box");
        hashmap.put("minecraft:pink_shulker_box", "minecraft:shulker_box");
        hashmap.put("minecraft:gray_shulker_box", "minecraft:shulker_box");
        hashmap.put("minecraft:silver_shulker_box", "minecraft:shulker_box");
        hashmap.put("minecraft:cyan_shulker_box", "minecraft:shulker_box");
        hashmap.put("minecraft:purple_shulker_box", "minecraft:shulker_box");
        hashmap.put("minecraft:blue_shulker_box", "minecraft:shulker_box");
        hashmap.put("minecraft:brown_shulker_box", "minecraft:shulker_box");
        hashmap.put("minecraft:green_shulker_box", "minecraft:shulker_box");
        hashmap.put("minecraft:red_shulker_box", "minecraft:shulker_box");
        hashmap.put("minecraft:black_shulker_box", "minecraft:shulker_box");
        hashmap.put("minecraft:bed", "minecraft:bed");
        hashmap.put("minecraft:light_gray_shulker_box", "minecraft:shulker_box");
        hashmap.put("minecraft:banner", "minecraft:banner");
        hashmap.put("minecraft:white_banner", "minecraft:banner");
        hashmap.put("minecraft:orange_banner", "minecraft:banner");
        hashmap.put("minecraft:magenta_banner", "minecraft:banner");
        hashmap.put("minecraft:light_blue_banner", "minecraft:banner");
        hashmap.put("minecraft:yellow_banner", "minecraft:banner");
        hashmap.put("minecraft:lime_banner", "minecraft:banner");
        hashmap.put("minecraft:pink_banner", "minecraft:banner");
        hashmap.put("minecraft:gray_banner", "minecraft:banner");
        hashmap.put("minecraft:silver_banner", "minecraft:banner");
        hashmap.put("minecraft:cyan_banner", "minecraft:banner");
        hashmap.put("minecraft:purple_banner", "minecraft:banner");
        hashmap.put("minecraft:blue_banner", "minecraft:banner");
        hashmap.put("minecraft:brown_banner", "minecraft:banner");
        hashmap.put("minecraft:green_banner", "minecraft:banner");
        hashmap.put("minecraft:red_banner", "minecraft:banner");
        hashmap.put("minecraft:black_banner", "minecraft:banner");
        hashmap.put("minecraft:standing_sign", "minecraft:sign");
        hashmap.put("minecraft:wall_sign", "minecraft:sign");
        hashmap.put("minecraft:piston_head", "minecraft:piston");
        hashmap.put("minecraft:daylight_detector_inverted", "minecraft:daylight_detector");
        hashmap.put("minecraft:unpowered_comparator", "minecraft:comparator");
        hashmap.put("minecraft:powered_comparator", "minecraft:comparator");
        hashmap.put("minecraft:wall_banner", "minecraft:banner");
        hashmap.put("minecraft:standing_banner", "minecraft:banner");
        hashmap.put("minecraft:structure_block", "minecraft:structure_block");
        hashmap.put("minecraft:end_portal", "minecraft:end_portal");
        hashmap.put("minecraft:end_gateway", "minecraft:end_gateway");
        hashmap.put("minecraft:sign", "minecraft:sign");
        hashmap.put("minecraft:shield", "minecraft:banner");
    });
    protected static final HookFunction b = new HookFunction() {
        public <T> T apply(DynamicOps<T> dynamicops, T t0) {
            return DataConverterSchemaV99.a(new Dynamic(dynamicops, t0), DataConverterSchemaV704.a, "ArmorStand");
        }
    };

    public DataConverterSchemaV704(int i, Schema schema) {
        super(i, schema);
    }

    protected static void a(Schema schema, Map<String, Supplier<TypeTemplate>> map, String s) {
        schema.register(map, s, () -> {
            return DSL.optionalFields("Items", DSL.list(DataConverterTypes.ITEM_STACK.in(schema)));
        });
    }

    public Type<?> getChoiceType(TypeReference typereference, String s) {
        return Objects.equals(typereference.typeName(), DataConverterTypes.j.typeName()) ? super.getChoiceType(typereference, DataConverterSchemaNamed.a(s)) : super.getChoiceType(typereference, s);
    }

    public Map<String, Supplier<TypeTemplate>> registerBlockEntities(Schema schema) {
        HashMap hashmap = Maps.newHashMap();

        a(schema, hashmap, "minecraft:furnace");
        a(schema, hashmap, "minecraft:chest");
        schema.registerSimple(hashmap, "minecraft:ender_chest");
        schema.register(hashmap, "minecraft:jukebox", (s) -> {
            return DSL.optionalFields("RecordItem", DataConverterTypes.ITEM_STACK.in(schema));
        });
        a(schema, hashmap, "minecraft:dispenser");
        a(schema, hashmap, "minecraft:dropper");
        schema.registerSimple(hashmap, "minecraft:sign");
        schema.register(hashmap, "minecraft:mob_spawner", (s) -> {
            return DataConverterTypes.r.in(schema);
        });
        schema.registerSimple(hashmap, "minecraft:noteblock");
        schema.registerSimple(hashmap, "minecraft:piston");
        a(schema, hashmap, "minecraft:brewing_stand");
        schema.registerSimple(hashmap, "minecraft:enchanting_table");
        schema.registerSimple(hashmap, "minecraft:end_portal");
        schema.registerSimple(hashmap, "minecraft:beacon");
        schema.registerSimple(hashmap, "minecraft:skull");
        schema.registerSimple(hashmap, "minecraft:daylight_detector");
        a(schema, hashmap, "minecraft:hopper");
        schema.registerSimple(hashmap, "minecraft:comparator");
        schema.register(hashmap, "minecraft:flower_pot", (s) -> {
            return DSL.optionalFields("Item", DSL.or(DSL.constType(DSL.intType()), DataConverterTypes.q.in(schema)));
        });
        schema.registerSimple(hashmap, "minecraft:banner");
        schema.registerSimple(hashmap, "minecraft:structure_block");
        schema.registerSimple(hashmap, "minecraft:end_gateway");
        schema.registerSimple(hashmap, "minecraft:command_block");
        return hashmap;
    }

    public void registerTypes(Schema schema, Map<String, Supplier<TypeTemplate>> map, Map<String, Supplier<TypeTemplate>> map1) {
        super.registerTypes(schema, map, map1);
        schema.registerType(false, DataConverterTypes.j, () -> {
            return DSL.taggedChoiceLazy("id", DSL.namespacedString(), map);
        });
        schema.registerType(true, DataConverterTypes.ITEM_STACK, () -> {
            return DSL.hook(DSL.optionalFields("id", DataConverterTypes.q.in(schema), "tag", DSL.optionalFields("EntityTag", DataConverterTypes.n.in(schema), "BlockEntityTag", DataConverterTypes.j.in(schema), "CanDestroy", DSL.list(DataConverterTypes.p.in(schema)), "CanPlaceOn", DSL.list(DataConverterTypes.p.in(schema)))), DataConverterSchemaV704.b, HookFunction.IDENTITY);
        });
    }
}
