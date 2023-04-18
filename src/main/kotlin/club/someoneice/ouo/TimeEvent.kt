package club.someoneice.ouo

import club.someoneice.ouo.data.DataBase
import net.minecraft.block.Block
import net.minecraft.entity.Entity
import net.minecraft.entity.item.EntityItem
import net.minecraft.entity.passive.EntityAnimal
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.util.ChatComponentText
import net.minecraft.world.World


object TimeEvent {
    open class DataHelper(val date: String, val world: World) : Thread() {
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
            if (data.type == "message" && data.message != null)
                (world.playerEntities as List<EntityPlayer>).forEach() { it.addChatMessage(ChatComponentText(data.message)) }
            else if (data.type == "cleanItem") for (entity in world.loadedEntityList as List<Entity>) {
                if (entity is EntityItem) {
                    if (Data.config == null) entity.setDead()
                    if (Data.config!!.isWhiteList) if (Data.config!!.list.contains(entity.entityItem.item.unlocalizedName)) entity.setDead()
                    else if (Data.config!!.list.contains(entity.entityItem.item.unlocalizedName)) continue
                }
            }
            else if (data.type == "cleanEntity") for (entity in world.loadedEntityList as List<Entity>) {
                if (entity is EntityPlayer) continue
                if (Data.config == null) entity.setDead()

                if (entity is EntityItem) {
                    if (Data.config!!.isWhiteList) if (Data.config!!.list.contains(entity.entityItem.item.unlocalizedName)) entity.setDead()
                    else if (Data.config!!.list.contains(entity.entityItem.item.unlocalizedName)) continue
                } else if (entity is EntityAnimal) entity.setDead()
            }
            else if (data.type == "event" && data.player != null && data.event != null) {
                for (player in world.playerEntities as List<EntityPlayer>) {
                    if (player.displayName == data.player) {
                        for (eve in data.event.keys) {
                            if (eve == "kill") player.setDead()
                            else if (eve == "give" && data.event[eve] != null) player.inventory.addItemStackToInventory(findItemByText(data.event[eve]!!)?.stack())
                            else if (eve == "message" && data.event[eve] != null) player.addChatMessage(ChatComponentText(data.event[eve]))
                        }
                        break
                    }
                }
            }
        }

        fun findItemByText(itemName: String): Item? {
            return Item.itemRegistry.getObject(itemName) as Item? ?: Item.getItemFromBlock(
                Block.getBlockFromName(
                    itemName
                )
            )
        }
    }
}

fun Item.stack(): ItemStack {
    return ItemStack(this)
}