package y2k.joyreactor.common

import ios.coregraphics.struct.CGPoint
import ios.coregraphics.struct.CGRect

/**
 * Created by y2k on 5/15/16.
 */

fun CGRect.offset(dx: Double, dy: Double): CGRect {
    return CGRect(CGPoint(origin().x() + dx, origin().y() + dy), size())
}