package y2k.joyreactor.common

import ios.uikit.UITableView

/**
 * Created by y2k on 5/11/16.
 */
class BindingBuilder(init: BindingBuilder.() -> Unit) {

    fun <T> tableView(view: UITableView, property: ObservableProperty<List<T>>, init: TableListBindingBuilder<T>.() -> Unit) {
    }
}

class TableListBindingBuilder<T> {

    fun item(condition: (T) -> Boolean, init: () -> Unit) {
    }
}