package y2k.joyreactor

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.Toolbar
import android.view.View
import y2k.joyreactor.common.*
import y2k.joyreactor.model.Image
import y2k.joyreactor.viewmodel.GalleryViewModel
import y2k.joyreactor.widget.WebImageView

/**
 * Created by y2k on 2/7/16.
 */
class GalleryActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

        val vm = ServiceLocator.resolve<GalleryViewModel>()
        bindingBuilder(this) {
            recyclerView(R.id.list, vm.images) {
                viewHolder {
                    VH(it.inflate(R.layout.item_image)).apply {
                        setOnClick(R.id.image) { vm.openImage(it) }
                    }
                }
            }
        }
    }

    class VH(view: View) : ListViewHolder<Image>(view) {

        val image = view.find<WebImageView>(R.id.image)

        override fun update(item: Image) {
            image.image = item
        }
    }
}