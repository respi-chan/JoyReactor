package y2k.joyreactor

import ios.uikit.UITableViewCell
import y2k.joyreactor.common.BindingBuilder
import y2k.joyreactor.common.ServiceLocator
import y2k.joyreactor.common.setImageLink
import y2k.joyreactor.generated.PostCell
import y2k.joyreactor.generated.PostListViewController
import y2k.joyreactor.services.LifeCycleService
import y2k.joyreactor.viewmodel.PostListViewModel

/**
 * Created by y2k on 5/11/16.
 */
class PostListController(controller: PostListViewController) {

    // FIXME:
    val lifeCycle = ServiceLocator.resolve<LifeCycleService>()

    init {
        controller.apply {
            val vm = ServiceLocator.resolve<PostListViewModel>(lifeCycle)
            BindingBuilder {

                activityIndicator(progressView, vm.isBusy)

                tableView(list, vm.posts) {
                    item<PostCell>({ it != null }, "Post") {
                        if (it == null) return@item

                        // FIXME:
                        userName.setText(it.userName)
                        ratingView.setText("" + it.rating)
                        image.setImageLink(it.image)
                    }
                    item<UITableViewCell>({ it == null }, "LoadMore") {
                        // FIXME:
                    }
                }

            }
        }

        // FIXME:
        lifeCycle.activate()
    }
}