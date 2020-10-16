import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class ErrorFromApi(
    var code: Int? = 0,
    var message: String,
    var body: String? = null
) : Parcelable {

    constructor(message: String, code: Int, body: String?) : this(
        code,
        message,
        body
    )

    constructor(message: String, code: Int): this(
        code,
        message,
        null
    )

    companion object {
        var NO_INTERNET = 8080
    }
}