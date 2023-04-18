package club.someoneice.ouo

import club.someoneice.ouo.data.DataBase
import club.someoneice.ouo.mixin.ServerWorldMixin
import net.minecraft.block.Block
import net.minecraft.entity.ItemEntity
import net.minecraft.entity.player.PlayerEntity
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.server.world.ServerWorld
import net.minecraft.text.LiteralText
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
import java.util.*


object TimeEvent {
    open class DataHelper(val date: String, val world: ServerWorld) : Thread() {
        override fun run() {
            val tickTasks = Data.tickTask.keys().iterator()
            while (tickTasks.hasNext()) {
                val task = tickTasks.next()
                Data.tickTask[task] = Data.tickTask[task]!! - 1
                if (Data.tickTask[task]!! <= 0) {
                    DataHandler(task)
                    if (task.once) Data.tickTask.remove(task)
                    else Data.tickTask[task] = Data.tickTaskSource[task]!!
                }
            }

            for (time in Data.timeTask.keys()) if (time.equals(date)) for (task in Data.timeTask[time]!!) {
                DataHandler(task)
                if (task.once) Data.timeTask[time]!!.remove(task)
            }
        }

        private fun DataHandler(data: DataBase) {
            if (data.type == "message" && data.message != null) (world.players as List<PlayerEntity>).forEach() { it.sendSystemMessage(LiteralText(data.message), UUID.randomUUID()) }
            else if (data.type == "cleanItem") (world as ServerWorldMixin).entityList.forEach {
                if (it is ItemEntity) {
                    if (Data.config == null) it.kill()
                    if (Data.config!!.isWhiteList) if (Data.config!!.list.contains(it.stack.item.translationKey)) it.kill()
                }
            }
            else if (data.type == "cleanEntity") for (entity in (world as ServerWorldMixin).loadedMobs) entity.kill()
            else if (data.type == "event" && data.player != null && data.event != null) {
                for (player in world.players) {
                    if (player.displayName.asString() == data.player) {
                        for (eve in data.event.keys) {
                            if (eve == "kill") player.kill()
                            else if (eve == "give" && data.event[eve] != null) player.giveItemStack(findItemByText(data.event[eve]!!)?.stack())
                            else if (eve == "message" && data.event[eve] != null) player.sendSystemMessage(LiteralText(data.event[eve]), UUID.randomUUID())
                        }
                        break
                    }
                }
            }
        }

        private fun findItemByText(itemName: String): Item? {
            if (itemName == "null") return null

            return try {
                val modid: String = itemName.substring(0, itemName.indexOf(":"))
                val name: String = itemName.substring(itemName.indexOf(":") + 1)

                Registry.ITEM.get(Identifier(modid, name))
            } catch (_: Exception) {
                null
            }
        }
    }
}

fun Item.stack(): ItemStack {
    return ItemStack(this)
}