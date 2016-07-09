package y2k.joyreactor.services

import rx.Observable
import y2k.joyreactor.common.ForegroundScheduler
import y2k.joyreactor.common.http.HttpClient
import y2k.joyreactor.common.mapNotNull
import y2k.joyreactor.common.platform.Platform
import y2k.joyreactor.common.switchIfNull
import y2k.joyreactor.model.Image
import y2k.joyreactor.services.images.DiskCache
import java.util.*

/**
 * Created by y2k on 5/15/16.
 */
class ImageService(
    private val diskCache: DiskCache,
    private val client: HttpClient,
    private val decoder: Platform,
    private val metaHolder: MetaStorage) {

    fun makeUrl(image: Image?, width: Int): String? {
        if (image == null) return null
        val height = width / image.getAspect(1f)
        return image.thumbnailUrl(width, height.toInt())
    }

    fun makeUrl(image: Image?, width: Int, height: Int): String? {
        return image?.thumbnailUrl(width, height)
    }

    fun <T> to(imageUrl: String?, target: Any): Observable<T?> {
        if (imageUrl == null) {
            metaHolder.setKey(target, null)
            return Observable.just(null)
        }

        val id = UUID.randomUUID()
        metaHolder.setKey(target, id)

        return getFromCache<T>(imageUrl)
            .switchIfNull { replaceToCache(imageUrl) }
            .observeOn(ForegroundScheduler.instance)
            .startWith(null as T?)
            .filter { metaHolder.getKey(target) == id }
    }

    private fun <T> replaceToCache(url: String): Observable<T> {
        val tmp = createTempFile(directory = diskCache.cacheDirectory)
        return client.downloadToFile(url, tmp)
            .andThen(diskCache.put(tmp, url))
            .andThen(getFromCache<T>(url).map { it!! })
    }

    private fun <T> getFromCache(url: String): Observable<T?> {
        return diskCache.get(url)
            .mapNotNull { decoder.decodeImage<T>(it) }
            .toObservable()
    }

    interface MetaStorage {

        fun setKey(target: Any, key: UUID?)
        fun getKey(target: Any): UUID?
    }
}
