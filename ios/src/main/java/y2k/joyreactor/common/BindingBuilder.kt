package y2k.joyreactor.common

import ios.foundation.NSIndexPath
import ios.uikit.UIActivityIndicatorView
import ios.uikit.UITableView
import ios.uikit.UITableViewCell
import ios.uikit.protocol.UITableViewDataSource
import java.util.*

/**
 * Created by y2k on 5/11/16.
 */
class BindingBuilder(init: BindingBuilder.() -> Unit) {

    init {
        init()
    }

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

class TableListBindingBuilder<T>(view: UITableView, property: ObservableProperty<List<T>>) {

    private val rules = ArrayList<Rule<T>>()

    init {
        view.setDataSource(object : UITableViewDataSource {

            override fun tableViewNumberOfRowsInSection(p0: UITableView?, p1: Long): Long {
                return property.value.size.toLong()
            }

            override fun tableViewCellForRowAtIndexPath(tableView: UITableView, index: NSIndexPath): UITableViewCell? {
                val item = property.value[index.row().toInt()]
                val rule = rules.first { it.condition(item) }

                val cell = tableView.dequeueReusableCellWithIdentifierForIndexPath(rule.identifier, index)
                rule.init(item, cell)
                return cell
            }
        })
        property.subscribe { view.reloadData() }
    }

    fun <R> item(condition: (T) -> Boolean, identifier: String, init: R.(T) -> Unit) {
        rules += Rule(condition, identifier) { item, cell ->
            (cell as R).init(item)
        }
    }

    class Rule<T>(
        val condition: (T) -> Boolean,
        val identifier: String,
        val init: (T, UITableViewCell) -> Unit)
}