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

import ios.uikit.UIButton;
import ios.uikit.UITableView;
import ios.uikit.UIViewController;

@com.intel.moe.natj.general.ann.Runtime(ObjCRuntime.class)
@ObjCClassName("MenuViewController")
@RegisterOnStartup
public class MenuViewController extends UIViewController {

    @Selector("featuredButton")
    @Property
    public native UIButton getFeaturedButton();

    @Selector("favoriteButton")
    @Property
    public native UIButton getFavoriteButton();

    @Selector("list")
    @Property
    public native UITableView getList();

    @Selector("viewDidLoad")
    @Override
    public void viewDidLoad() {
    }

    protected MenuViewController(Pointer peer) {
        super(peer);
    }

    @Generated("NatJ")
    @Owned
    @Selector("alloc")
    public static native MenuViewController alloc();

    @Generated("NatJ")
    @Owned
    @Selector("init")
    public native MenuViewController init();

    static {
        NatJ.register();
    }
}