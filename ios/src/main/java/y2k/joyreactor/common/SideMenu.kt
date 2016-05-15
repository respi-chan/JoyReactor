package y2k.joyreactor.common

import ios.uikit.*

/**
 * Created by y2k on 5/15/16.
 */
class SideMenu(private val parent: UIViewController, menuStoryboardId: String) {
    private val parentView: UIView

    private val closeButton: UIButton
    private val menuView: UIView

    private val menuController: UIViewController

    init {
        parentView = parent.navigationController().view()
        menuController = parent.storyboard().instantiateViewControllerWithIdentifier(menuStoryboardId)
        menuView = menuController.view()

        closeButton = UIButton.alloc().initWithFrame(parentView.frame())
//        closeButton.addOnTouchUpInsideListener { sender, e -> closeButtonClicked() }
    }

    internal fun closeButtonClicked() {
        UIView.animateWithDurationAnimationsCompletion(0.3, {
            restoreViewPosition()
        }, {
            removeMenuViews()
            menuController.viewDidDisappear(false)
        })
    }

    fun attach() {
        val menuButton = UIBarButtonItem.alloc().init()
        menuButton.setImage(UIImage.imageNamed("MenuIcon.png"))
//        menuButton.setOnClickListener { sender -> menuButtonClicked() }

//        parent.navigationItem.leftBarButtonItem = menuButton

// TODO:
//        val edgeGesture = UIScreenEdgePanGestureRecognizer { s -> menuButtonClicked() }
//        edgeGesture.edges = UIRectEdge.Left
//        parent.view.addGestureRecognizer(edgeGesture)
    }

    internal fun menuButtonClicked() {
        if (menuView.superview() != null) return

        val menuFrame = parentView.frame()
        menuFrame.size().setWidth(PanelWidth.toDouble())

        menuView.setFrame(menuFrame)
        parentView.addSubview(menuView)
        parentView.sendSubviewToBack(menuView)
        menuFrame.origin().setX((-PanelWidth).toDouble())

        parentView.addSubview(closeButton)

        UIView.animateWithDurationAnimationsCompletion(0.3, {
            menuView.setFrame(menuFrame)
            for (s in parentView.subviews()) {
                val f = s.frame()
                s.setFrame(f.offset(PanelWidth.toDouble(), 0.0))
            }
        }, {
            menuController.viewWillAppear(false)
        })
    }

    fun deactive() {
        if (closeButton.superview() == null) return
        restoreViewPosition()
        removeMenuViews()
    }

    internal fun restoreViewPosition() {
        for (s in parentView.subviews()) {
            if (s === menuView) continue
            val f = s.frame()
            s.setFrame(f.offset(-PanelWidth, 0.0))
        }
    }

    internal fun removeMenuViews() {
        closeButton.removeFromSuperview()
        menuView.removeFromSuperview()
    }

    companion object {

        internal val PanelWidth = 270.0
    }
}