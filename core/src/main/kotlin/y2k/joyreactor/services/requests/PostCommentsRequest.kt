package y2k.joyreactor.services.requests

import org.jsoup.nodes.Document
import y2k.joyreactor.model.Comment
import y2k.joyreactor.model.Image
import java.util.*
import java.util.regex.Pattern

/**
 * Created by y2k on 9/29/15.
 */
class PostCommentsRequest {

    var comments: List<Comment> = ArrayList()
        private set

    fun request(doc: Document, postId: Long) {
        val result = ArrayList<Comment>()
        for (node in doc.select("div.comment")) {
            val parent = node.parent()
            val parentId = if ("comment_list" == parent.className()) NumberExtractor[parent.id()] else 0

            val imgElement = node.select("div.image > img").first()

            val comment = Comment(
                node.select("div.txt > div").first().text(),
                node.select("img.avatar").attr("src"),
                parentId,
                java.lang.Float.parseFloat(node.select("span.comment_rating").text().trim { it <= ' ' }),
                postId,
                attachment = imgElement?.let {
                    Image(
                        clearImageUrl(imgElement.absUrl("src")),
                        imgElement.attr("width").toInt(),
                        imgElement.attr("height").toInt())
                },
                id = (node.select("span.comment_rating").attr("comment_id")).toLong())

            result.add(comment)
        }

        ChildrenCounter.compute(result)
        comments = result
    }

    private fun clearImageUrl(url: String): String {
        return url.replace("(/comment/).+(-\\d+\\.[\\w\\d]+)$".toRegex(), "$1$2")
    }

    private object NumberExtractor {

        internal val NUMBER = Pattern.compile("\\d+")

        internal operator fun get(text: String): Long {
            val m = NUMBER.matcher(text)
            return if (m.find()) m.group().toLong() else 0L
        }
    }

    private object ChildrenCounter {

        internal fun compute(comments: List<Comment>) {
            // TODO: оптимизировать
            for (i in 0..comments.size - 1 - 1) {
                val c = comments[i]
                for (n in i + 1..comments.size - 1)
                    if (comments[n].parentId == c.id) c.replies++
            }
        }
    }
}