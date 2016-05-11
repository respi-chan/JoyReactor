package y2k.joyreactor

import ios.foundation.NSIndexPath
import ios.uikit.UITableView
import ios.uikit.UITableViewCell
import ios.uikit.protocol.UITableViewDataSource
import y2k.joyreactor.common.ServiceLocator
import y2k.joyreactor.generated.PostListViewController
import y2k.joyreactor.services.LifeCycleService

/**
 * Created by y2k on 5/11/16.
 */
class PostListController(val controller: PostListViewController) {

    // FIXME:
    val lifeCycle = ServiceLocator.resolve<LifeCycleService>()

    init {
        controller.apply {
            list.setDataSource(object : UITableViewDataSource {

                override fun tableViewNumberOfRowsInSection(p0: UITableView?, p1: Long): Long {
                    return 3
                }

                override fun tableViewCellForRowAtIndexPath(tableView: UITableView, position: NSIndexPath): UITableViewCell? {
                    val view = tableView.dequeueReusableCellWithIdentifierForIndexPath("Post", position)
                    return view
                }
            })
        }

        //        controller.apply {
        //            val vm = ServiceLocator.resolve<PostListViewModel>(lifeCycle)
        //            BindingBuilder {
        //
        //                tableView(list, vm.posts) {
        //                    // FIXME:
        //                    item({ it != null }) {
        //                        // FIXME:
        //                    }
        //                    item({ it == null }) {
        //                        // FIXME:
        //                    }
        //                }
        //
        //            }
        //        }
        //
        //        // FIXME:
        //        lifeCycle.activate()
    }
}