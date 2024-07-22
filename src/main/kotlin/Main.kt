fun main() {
    val libraryDatabase = LibraryDatabase()

    while (true) {
        println("Welcome to the Library Management System")
        println("Please login as:")
        println("1. Librarian")
        println("2. User")
        println("3. Exit")

        val loginChoice = readLine()?.toIntOrNull()

        when (loginChoice) {
            1 -> {
                println("Enter Librarian Name:")
                val librarianName = readLine() ?: ""
                println("Enter Librarian ID:")
                val librarianId = readLine() ?: ""
                println("Enter Librarian Password:")
                val librarianPassword = readLine() ?: ""

                val librarian = Librarian(librarianName, librarianId, librarianPassword)
                libraryDatabase.currentLibrarian = librarian

                librarianActions(libraryDatabase)
            }
            2 -> {
                println("Enter User Name:")
                val userName = readLine() ?: ""
                println("Enter User ID:")
                val userId = readLine() ?: ""
                println("Enter User Role:")
                val userRole = readLine() ?: ""

                val user = User(userName, userId, userRole)

                userActions(libraryDatabase, user)
            }
            3 -> {
                println("Goodbye!")
                return
            }
            else -> {
                println("Invalid choice. Please try again.")
            }
        }
    }
}

fun librarianActions(libraryDatabase: LibraryDatabase) {
    while (true) {
        println("Librarian Menu:")
        println("1. Add a new book")
        println("2. View available books")
        println("3. Search for a book by ISBN")
        println("4. Logout")

        when (readLine()?.toIntOrNull()) {
            1 -> {
                println("Enter book title:")
                val title = readLine() ?: ""
                println("Enter book ISBN:")
                val isbn = readLine() ?: ""
                println("Enter book publication:")
                val publication = readLine() ?: ""
                println("Enter number of pages:")
                val numberOfPages = readLine()?.toIntOrNull() ?: 0

                val book = Book(title, isbn, publication, numberOfPages)
                libraryDatabase.addBook(book)
            }
            2 -> {
                libraryDatabase.viewAvailableBooks()
            }
            3 -> {
                println("Enter book ISBN to search:")
                val isbn = readLine() ?: ""
                val book = libraryDatabase.searchForABook(isbn)
                if (book != null) {
                    println("Book found: ${book.title}")
                } else {
                    println("Book not found!")
                }
            }
            4 -> {
                println("Logged out.")
                break
            }
            else -> {
                println("Invalid option, please try again.")
            }
        }
    }
}

fun userActions(libraryDatabase: LibraryDatabase, user: User) {
    while (true) {
        println("User Menu:")
        println("1. View available books")
        println("2. Lend a book")
        println("3. Return a book")
        println("4. Search for a book by ISBN")
        println("5. Logout")

        when (readLine()?.toIntOrNull()) {
            1 -> {
                libraryDatabase.viewAvailableBooks()
            }
            2 -> {
                println("Enter book ISBN to lend:")
                val isbn = readLine() ?: ""
                val book = libraryDatabase.searchForABook(isbn)
                if (book != null) {
                    libraryDatabase.lendBook(book, user)
                    println("Book lent successfully!")
                } else {
                    println("Book not found!")
                }
            }
            3 -> {
                println("Enter book ISBN to return:")
                val isbn = readLine() ?: ""
                val book = libraryDatabase.borrowedBooks.keys.find { it.ISBN == isbn }
                if (book != null) {
                    libraryDatabase.receiveBookFromBorrower(book)
                    println("Book returned successfully!")
                } else {
                    println("Book not found in borrowed books!")
                }
            }
            4 -> {
                println("Enter book ISBN to search:")
                val isbn = readLine() ?: ""
                val book = libraryDatabase.searchForABook(isbn)
                if (book != null) {
                    println("Book found: ${book.title}")
                } else {
                    println("Book not found!")
                }
            }
            5 -> {
                println("Logged out.")
                break
            }
            else -> {
                println("Invalid option, please try again.")
            }
        }
    }
}