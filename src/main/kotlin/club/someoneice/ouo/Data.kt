package club.someoneice.ouo

import club.someoneice.ouo.data.Config
import club.someoneice.ouo.data.DataBase
import java.util.concurrent.ConcurrentHashMap
import java.util.concurrent.CopyOnWriteArrayList

object Data {
    // Data list
    val baseData = CopyOnWriteArrayList<DataBase>()

    // Config
    var config: Config? = null;

    // Main
    val timeTask = ConcurrentHashMap<String, ArrayList<DataBase>>()
    val tickTask = ConcurrentHashMap<DataBase, Int>()

    // For homing time
    val tickTaskSource = ConcurrentHashMap<DataBase, Int>()
}