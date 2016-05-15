package y2k.joyreactor

import y2k.joyreactor.common.BindingBuilder
import y2k.joyreactor.common.ServiceLocator
import y2k.joyreactor.common.setImageLink
import y2k.joyreactor.generated.MenuViewController
import y2k.joyreactor.generated.TagCell
import y2k.joyreactor.services.LifeCycleService
import y2k.joyreactor.viewmodel.MenuViewModel

/**
 * Created by y2k on 5/15/16.
 */
class MenuController(val controller: MenuViewController) {

    // FIXME:
    val lifeCycle = ServiceLocator.resolve<LifeCycleService>()

    init {
        controller.apply {
            val vm = ServiceLocator.resolve<MenuViewModel>(lifeCycle)
            // FIXME:
            BindingBuilder {
                tableView(list, vm.tags) {
                    item<TagCell>({ true }, "Tag") {
                        // FIXME:
                        icon.setImageLink(it.image)
                        title.setText(it.title)
                    }
                }
            }
        }
        lifeCycle.activate()
    }
}