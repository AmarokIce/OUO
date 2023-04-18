package club.someoneice.ouo

import club.someoneice.ouo.data.Config
import club.someoneice.ouo.data.DataBase
import com.google.common.collect.Lists
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.File
import java.nio.file.Files

class DataProcessor {
    init {
        jsonReader()
        dataHandle()
    }

    private fun dataHandle() {
        for (data in Data.baseData) {
            if (data.time.contains(':')) {
                if (!Data.timeTask.containsKey(data.time)) Data.timeTask[data.time] = Lists.newArrayList()
                Data.timeTask[data.time]!!.add(data)
            }
            else Data.tickTask[data] = data.time.toInt()
        }

        Data.tickTaskSource.putAll(Data.tickTask)
    }

    private fun jsonReader() {
        val configTag = object: TypeToken<Config>() {}.type
        val dataBase = object: TypeToken<DataBase>() {}.type

        val file = File("${System.getProperty("user.dir")}\\ouo")
        if (!file.isDirectory) return
        for (jo in file.listFiles()!!) {
            val reader = Files.newInputStream(jo.toPath())
            val byte = ByteArray(jo.length().toInt())
            reader.read(byte)
            reader.close()

            if (jo.name.equals("config.json")) {
                Data.config = gson.fromJson(String(byte), configTag) as Config
                continue
            }

            Data.baseData.add(gson.fromJson(String(byte), dataBase) as DataBase)
        }
    }
}

val gson: Gson = GsonBuilder().setPrettyPrinting().disableHtmlEscaping().create()
/*
fun Any.gson(): Gson {
    return gson
}
*/