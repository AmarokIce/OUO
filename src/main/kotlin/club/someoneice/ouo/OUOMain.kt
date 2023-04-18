@file:JvmMultifileClass
@file:JvmName("ExtUtils")
package club.someoneice.ouo

import alexsocol.patcher.KotlinAdapter
import cpw.mods.fml.common.FMLCommonHandler
import cpw.mods.fml.common.Mod
import cpw.mods.fml.common.event.FMLInitializationEvent
import cpw.mods.fml.common.event.FMLServerStartingEvent
import cpw.mods.fml.common.eventhandler.SubscribeEvent
import cpw.mods.fml.common.gameevent.TickEvent
import net.minecraft.command.CommandBase
import net.minecraft.command.ICommandSender
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.util.ChatComponentText
import net.minecraftforge.common.MinecraftForge
import org.apache.logging.log4j.LogManager
import java.text.SimpleDateFormat
import java.util.*

@Mod(modid = OUOMain.MODID, version = OUOMain.VERSION, acceptableRemoteVersions = "*", modLanguageAdapter = KotlinAdapter.className)
class OUOMain {
    companion object {
        const val MODID = "ouo"
        const val VERSION = "1.0.0"
        val LOGGER = LogManager.getLogger(MODID)
        private var dateNow: String = "00:00"
    }

    @Mod.EventHandler
    fun init(event: FMLInitializationEvent) {
        LOGGER.info("OUO is start running!")

        DataProcessor()
        MinecraftForge.EVENT_BUS.register(this)
        FMLCommonHandler.instance().bus().register(this)
    }

    private var i = 20 * 60

    @SubscribeEvent
    fun tickEvent(event: TickEvent.WorldTickEvent) {
        if (event.phase == TickEvent.Phase.END) i--;
        if (event.side.isServer && i <= 0) {
            i = 20 * 60
            TimeEvent.DataHelper(SimpleDateFormat("HH:mm").format(Date()), event.world).start()
        }
    }

    @Mod.EventHandler
    fun serverStarting(event: FMLServerStartingEvent) {
        event.registerServerCommand(command())
    }

    class command: CommandBase() {
        override fun getCommandName(): String {
            return "ouoreload"
        }

        override fun getCommandUsage(p_71518_1_: ICommandSender?): String {
            return "ouoreload"
        }

        override fun processCommand(sender: ICommandSender, list: Array<out String>) {
            DataProcessor()
            sender.addChatMessage(ChatComponentText("Success!"))
        }
    }
}