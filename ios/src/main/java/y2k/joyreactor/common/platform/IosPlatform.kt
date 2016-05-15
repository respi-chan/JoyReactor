package y2k.joyreactor.common.platform

import android.database.sqlite.SQLiteDatabase
import com.j256.ormlite.android.AndroidConnectionSource
import com.j256.ormlite.support.ConnectionSource
import ios.c.Globals
import ios.foundation.c.Foundation
import ios.foundation.enums.NSSearchPathDirectory
import ios.foundation.enums.NSSearchPathDomainMask
import ios.uikit.UIImage
import rx.Observable
import rx.schedulers.Schedulers
import y2k.joyreactor.common.ForegroundScheduler
import java.io.File

/**
 * Created by y2k on 5/11/16.
 */
class IosPlatform : Platform {

    init {
        ForegroundScheduler.instance = Schedulers.from { action ->
            Globals.dispatch_async(Globals.dispatch_get_main_queue()) { action.run() }
        }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T> decodeImage(path: File): T {
        return UIImage.imageWithContentsOfFile(path.absolutePath) as T
    }

    override val currentDirectory: File
        get() {
            val dirs = Foundation.NSSearchPathForDirectoriesInDomains(
                NSSearchPathDirectory.DocumentDirectory,
                NSSearchPathDomainMask.UserDomainMask, true)
            return File(dirs[0])
        }

    override val navigator: NavigationService
        get() = StoryboardNavigationService()

    override fun loadFromBundle(name: String, ext: String): ByteArray {
        throw UnsupportedOperationException()
    }

    override fun saveToGallery(imageFile: File): Observable<*> {
        throw UnsupportedOperationException()
    }

    override fun buildConnection(file: File): ConnectionSource {
        val database = SQLiteDatabase.openDatabase(
            file.absolutePath, null,
            SQLiteDatabase.OPEN_READWRITE or SQLiteDatabase.CREATE_IF_NECESSARY)
        return AndroidConnectionSource(database)
    }
}