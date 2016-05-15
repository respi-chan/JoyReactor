package y2k.joyreactor

import y2k.joyreactor.common.BindingBuilder
import y2k.joyreactor.common.ServiceLocator
import y2k.joyreactor.generated.PostListViewController
import y2k.joyreactor.services.LifeCycleService
import y2k.joyreactor.viewmodel.PostListViewModel

/**
 * Created by y2k on 5/11/16.
 */
class PostListController(val controller: PostListViewController) {

    // FIXME:
    val lifeCycle = ServiceLocator.resolve<LifeCycleService>()

    init {
        controller.apply {
            val vm = ServiceLocator.resolve<PostListViewModel>(lifeCycle)
            BindingBuilder {

                activityIndicator(progressView, vm.isBusy)

                tableView(list, vm.posts) {
                    // FIXME:
                    item({ it != null }) {
                        // FIXME:
                    }
                    item({ it == null }) {
                        // FIXME:
                    }
                }

            }
        }

        // FIXME:
        lifeCycle.activate()
    }
}