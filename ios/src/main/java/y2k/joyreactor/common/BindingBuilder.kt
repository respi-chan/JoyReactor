package y2k.joyreactor.common

import ios.uikit.UIActivityIndicatorView
import ios.uikit.UITableView

/**
 * Created by y2k on 5/11/16.
 */
class BindingBuilder(init: BindingBuilder.() -> Unit) {

    fun activityIndicator(view: UIActivityIndicatorView, property: ObservableProperty<Boolean>) {
        property.subscribe {
            if (it) view.startAnimating()
            else view.stopAnimating()
        }
    }

    fun <T> tableView(view: UITableView, property: ObservableProperty<List<T>>, init: TableListBindingBuilder<T>.() -> Unit) {
        TableListBindingBuilder(view, property).init()
    }
}

class TableListBindingBuilder<T>(
    view: UITableView,
    property: ObservableProperty<List<T>>) {

    fun item(condition: (T) -> Boolean, init: () -> Unit) {
    }
}