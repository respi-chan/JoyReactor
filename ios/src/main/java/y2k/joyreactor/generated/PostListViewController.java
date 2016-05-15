package y2k.joyreactor.generated;

import com.intel.moe.natj.general.NatJ;
import com.intel.moe.natj.general.Pointer;
import com.intel.moe.natj.general.ann.Generated;
import com.intel.moe.natj.general.ann.Owned;
import com.intel.moe.natj.general.ann.RegisterOnStartup;
import com.intel.moe.natj.objc.ObjCRuntime;
import com.intel.moe.natj.objc.ann.ObjCClassName;
import com.intel.moe.natj.objc.ann.Property;
import com.intel.moe.natj.objc.ann.Selector;

import ios.uikit.UIActivityIndicatorView;
import ios.uikit.UIButton;
import ios.uikit.UITableView;
import ios.uikit.UIView;
import ios.uikit.UIViewController;
import y2k.joyreactor.PostListController;

@com.intel.moe.natj.general.ann.Runtime(ObjCRuntime.class)
@ObjCClassName("PostListViewController")
@RegisterOnStartup
public class PostListViewController extends UIViewController {

    @Selector("progressView")
    @Property
    public native UIActivityIndicatorView getProgressView();

    @Selector("list")
    @Property
    public native UITableView getList();

    @Selector("hasNewPosts")
    @Property
    public native UIView getHasNewPosts();

    @Selector("applyButton")
    @Property
    public native UIButton getApplyButton();

    @Selector("viewDidLoad")
    @Override
    public void viewDidLoad() {
        new PostListController(this);
    }

    protected PostListViewController(Pointer peer) {
        super(peer);
    }

    @Generated("NatJ")
    @Owned
    @Selector("alloc")
    public static native PostListViewController alloc();

    @Generated("NatJ")
    @Owned
    @Selector("init")
    public native PostListViewController init();

    static {
        NatJ.register();
    }
}