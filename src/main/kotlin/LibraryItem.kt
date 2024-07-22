open class LibraryItem(
    val title: String,
    val ISBN: String,
    val publication: String,
    val numberOfPages: Int
) {
    open fun isAvailable(): Boolean {
        return true
    }
}