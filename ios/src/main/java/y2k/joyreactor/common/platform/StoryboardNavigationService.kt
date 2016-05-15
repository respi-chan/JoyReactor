package y2k.joyreactor.common.platform

import kotlin.reflect.KClass

/**
 * Created by y2k on 5/15/16.
 */
class StoryboardNavigationService : NavigationService {

    override fun openBrowser(url: String) {
        throw UnsupportedOperationException()
    }

    override fun close() {
        throw UnsupportedOperationException()
    }

    override val argument: String
        get() = throw UnsupportedOperationException()

    override fun <T : Any> open(vmType: KClass<T>, argument: String) {
        throw UnsupportedOperationException()
    }
}