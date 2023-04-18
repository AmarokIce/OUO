package club.someoneice.ouo.data

data class DataBase(
    val type: String,
    val time: String,
    val once: Boolean,
    val message: String?,
    val player: String?,
    val event: Map<String, String?>?
)