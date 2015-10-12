package y2k.joyreactor;

import org.ocpsoft.prettytime.PrettyTime;
import org.robovm.apple.foundation.NSData;
import org.robovm.apple.foundation.NSIndexPath;
import org.robovm.apple.uikit.*;
import org.robovm.objc.annotation.CustomClass;
import org.robovm.objc.annotation.IBAction;
import org.robovm.objc.annotation.IBOutlet;
import y2k.joyreactor.images.ImageRequest;

import java.util.List;

/**
 * Created by y2k on 9/26/15.
 */
@CustomClass("PostListViewController")
public class PostListViewController extends UIViewController implements PostListPresenter.View {

    PostListPresenter presenter;
    PostDataSource dataSource;
    PostDelegate postDelegate;

    UITableView list;
    UIActivityIndicatorView progress;

    @Override
    public void viewDidLoad() {
        super.viewDidLoad();
        presenter = new PostListPresenter(this);
        list.setDataSource(dataSource = new PostDataSource());
        list.setDelegate(postDelegate = new PostDelegate());

        new SideMenu(this, "Menu").attach();

        getNavigationItem().getRightBarButtonItem().setOnClickListener(sender -> {
            UIAlertController alert = new UIAlertController();
            alert.addAction(new UIAlertAction("Add tag", UIAlertActionStyle.Default, s -> {
                getNavigationController().pushViewController(
                        getStoryboard().instantiateViewController("AddTag"), true);
            }));
            alert.addAction(new UIAlertAction("Profile", UIAlertActionStyle.Default, s -> {
                getNavigationController().pushViewController(
                        getStoryboard().instantiateViewController("Profile"), true);
            }));
            alert.addAction(new UIAlertAction("Messages", UIAlertActionStyle.Default, s -> {
                getNavigationController().pushViewController(
                        getStoryboard().instantiateViewController("MessageThreads"), true);
            }));
            alert.addAction(new UIAlertAction("Cancel", UIAlertActionStyle.Cancel, null));
            presentViewController(alert, true, null);
        });
    }

    @Override
    public void viewWillAppear(boolean animated) {
        super.viewWillAppear(animated);
        presenter.activate();
    }

    @Override
    public void setBusy(boolean isBusy) {
        if (isBusy) progress.startAnimating();
        else progress.stopAnimating();
    }

    @Override
    public void reloadPosts(List<Post> posts) {
        dataSource.posts = posts;
        postDelegate.posts = posts;
        list.reloadData();
    }

    @IBOutlet
    void setList(UITableView list) {
        this.list = list;
    }

    @IBOutlet
    void setProgressView(UIActivityIndicatorView progress) {
        this.progress = progress;
    }

    class PostDataSource extends UITableViewDataSourceAdapter {

        List<Post> posts;

        @Override
        public long getNumberOfRowsInSection(UITableView tableView, long section) {
            int count = posts == null ? 0 : posts.size();
            return count == 0 ? 0 : count + 1;
        }

        @Override
        public UITableViewCell getCellForRow(UITableView tableView, NSIndexPath indexPath) {
            if (indexPath.getRow() == posts.size()) {
                LoadMoreCell cell = (LoadMoreCell) tableView.dequeueReusableCell("LoadMore");
                cell.presenter = presenter;
                return cell;
            } else {
                PostCell cell = (PostCell) tableView.dequeueReusableCell("Post");
                Post post = posts.get(indexPath.getRow());
                cell.update(presenter, post);

                ((UILabel) cell.getViewWithTag(3)).setText(post.userName);
                ((UILabel) cell.getViewWithTag(4)).setText(new PrettyTime().format(post.created));

                loadImage(post.image, 300, (int) (300 / post.getAspect()), (UIImageView) cell.getViewWithTag(1));

                UIImageView userImageView = (UIImageView) cell.getViewWithTag(2);
                loadImage(post.userImage, 50, 50, userImageView);
                userImageView.getLayer().setCornerRadius(userImageView.getFrame().getWidth() / 2);

                UIView root = cell.getViewWithTag(10);
                root.getLayer().setCornerRadius(10);
                root.getLayer().setMasksToBounds(true);
                root.getLayer().setBorderColor(UIColor.lightGray().getCGColor());
                root.getLayer().setBorderWidth(1);
                return cell;
            }
        }

        void loadImage(String url, int width, int height, UIImageView iv) {
            iv.setAlpha(0);
            new ImageRequest().setUrl(url)
                    .setSize(width, height)
                    .to(data -> {
                        iv.setImage(new UIImage(new NSData(data)));
                        UIView.animate(0.3, () -> iv.setAlpha(1));
                    });
        }
    }

    class PostDelegate extends UITableViewDelegateAdapter {

        List<Post> posts;

        @Override
        public double getHeightForRow(UITableView tableView, NSIndexPath indexPath) {
            if (indexPath.getRow() == posts.size()) return -1;
            Post post = posts.get(indexPath.getRow());
            return (tableView.getFrame().getWidth() - 16) / post.getAspect() + 66 + 16;
        }
    }
}