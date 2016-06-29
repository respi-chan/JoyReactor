package y2k.joyreactor.model

import com.j256.ormlite.field.DataType
import com.j256.ormlite.field.DatabaseField
import y2k.joyreactor.services.repository.Dto
import java.io.Serializable

/**
 * Created by y2k on 07/12/15.
 */
data class Attachment(
    @DatabaseField val postId: Long = 0,
    @DatabaseField(dataType = DataType.SERIALIZABLE) val image: Image? = null,
    @DatabaseField(generatedId = true) override val id: Long = 0
) : Dto, Serializable {

    override fun identify(newId: Long) = copy(id = newId)
}