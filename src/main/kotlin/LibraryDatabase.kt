class LibraryDatabase(
    var currentLibrarian: Librarian? = null,
    val availableBooks: MutableList<LibraryItem> = mutableListOf(),
    val borrowedBooks: MutableMap<LibraryItem, User> = mutableMapOf()
) {
    fun addBook(book: LibraryItem) {
        availableBooks.add(book)
    }

    fun lendBook(book: LibraryItem, user: User) {
        if (availableBooks.contains(book) && book.isAvailable()) {
            availableBooks.remove(book)
            borrowedBooks[book] = user
        } else {
            println("Book is not available")
        }
    }

    fun viewAvailableBooks() {
        println("Available Books:")
        availableBooks.forEach { println(it.title) }
    }

    fun searchForABook(ISBN: String): LibraryItem? {
        return availableBooks.find { it.ISBN == ISBN }
        //return availableBooks.filter { it.ISBN.contains(ISBN, false) }
    }

    fun receiveBookFromBorrower(book: LibraryItem) {
        borrowedBooks.remove(book)?.let {
            availableBooks.add(book)
        }
    }
}
