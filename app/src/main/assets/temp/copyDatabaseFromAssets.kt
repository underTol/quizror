package temp

import android.content.Context
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import java.io.OutputStream

object DatabaseCopier {

    fun copyDatabase(context: Context, dbName: String) {
        val dbPath = context.getDatabasePath(dbName).path
        val dbFile = File(dbPath)

        if (!dbFile.exists()) {
            try {
                val inputStream: InputStream = context.assets.open(dbName)
                val outputStream: OutputStream = FileOutputStream(dbPath)
                val buffer = ByteArray(1024)
                var length: Int

                while (inputStream.read(buffer).also { length = it } > 0) {
                    outputStream.write(buffer, 0, length)
                }

                outputStream.flush()
                outputStream.close()
                inputStream.close()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
