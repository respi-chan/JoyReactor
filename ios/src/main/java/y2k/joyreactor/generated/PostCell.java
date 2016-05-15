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

import ios.NSObject;
import ios.uikit.NSLayoutConstraint;
import ios.uikit.UIButton;
import ios.uikit.UIImageView;
import ios.uikit.UILabel;
import ios.uikit.UITableViewCell;
import ios.uikit.UIView;

@com.intel.moe.natj.general.ann.Runtime(ObjCRuntime.class)
@ObjCClassName("PostCell")
@RegisterOnStartup
public class PostCell extends UITableViewCell {

    @Selector("created")
    @Property
    public native UILabel getCreated();

    @Selector("image")
    @Property
    public native UIImageView getImage();

    @Selector("playButton")
    @Property
    public native UIButton getPlayButton();

    @Selector("rateButton")
    @Property
    public native UIButton getRateButton();

    @Selector("ratingView")
    @Property
    public native UILabel getRatingView();

    @Selector("replyCountView")
    @Property
    public native UILabel getReplyCountView();

    @Selector("root")
    @Property
    public native UIView getRoot();

    @Selector("userImage")
    @Property
    public native UIImageView getUserImage();

    @Selector("userName")
    @Property
    public native UILabel getUserName();

    @Selector("height")
    @Property
    public native NSLayoutConstraint getHeight();

    @Selector("play:")
    public void playAction(NSObject sender){
        // FIXME:
    }

    @Selector("rate:")
    public void rateActoin(NSObject sender){
        // FIXME:
    }

    static {
        NatJ.register();
    }

    protected PostCell(Pointer peer) {
        super(peer);
    }

    @Generated("NatJ")
    @Owned
    @Selector("alloc")
    public static native PostCell alloc();

    @Generated("NatJ")
    @Owned
    @Selector("init")
    public native PostCell init();
}