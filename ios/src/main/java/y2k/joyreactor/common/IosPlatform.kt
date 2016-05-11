package y2k.joyreactor.common

import com.j256.ormlite.support.ConnectionSource
import rx.Observable
import y2k.joyreactor.platform.NavigationService
import y2k.joyreactor.platform.Platform
import java.io.File

/**
 * Created by y2k on 5/11/16.
 */
class IosPlatform : Platform() {

    override val currentDirectory: File
        get() = throw UnsupportedOperationException()

    override val navigator: NavigationService
        get() = throw UnsupportedOperationException()

    override fun loadFromBundle(name: String, ext: String): ByteArray {
        throw UnsupportedOperationException()
    }

    override fun saveToGallery(imageFile: File): Observable<*> {
        throw UnsupportedOperationException()
    }

    override fun buildConnection(file: File): ConnectionSource {
        throw UnsupportedOperationException()
    }
}