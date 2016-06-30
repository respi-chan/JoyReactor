package y2k.joyreactor.common

import ios.uikit.UIImage
import ios.uikit.UIImageView
import y2k.joyreactor.model.Image
import y2k.joyreactor.services.ImageService
import y2k.joyreactor.services.LinksPool

/**
 * Created by y2k on 5/15/16.
 */

fun UIImageView.setImageLink(image: Image?) {
    val imageService = ServiceLocator.resolve<ImageService>()
    val url = imageService.makeUrl(image, 200)?.replace("https://", "http://")

    println("REQUEST | " + url)
    imageService
        .to<UIImage>(LinksPool.default, url, this)
        .subscribe {
            println("NEXT | " + it)
            setImage(it)
        }
}