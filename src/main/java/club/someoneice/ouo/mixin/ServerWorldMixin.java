package club.someoneice.ouo.mixin;

import net.minecraft.entity.mob.MobEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.EntityList;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import java.util.Set;

@Mixin(ServerWorld.class)
public interface ServerWorldMixin {
    @Accessor
    Set<MobEntity> getLoadedMobs();

    @Accessor
    EntityList getEntityList();

}
