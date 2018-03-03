package android.nni.com.theredotcomandroid.dtos

/**
 * Created by Marcus Garmon on 3/2/2018.
 */
data class RegisterDTO(
        var name: String = "",
        var username: String = "",
        var email: String = "",
        var password: String = ""
) {
}