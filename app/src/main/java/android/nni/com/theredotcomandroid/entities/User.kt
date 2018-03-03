package android.nni.com.theredotcomandroid.entities

import android.nni.com.theredotcomandroid.dtos.LoginDto
import android.nni.com.theredotcomandroid.dtos.RegisterDTO

/**
 * Created by Marcus Garmon on 2/25/2018.
 */
class User : GsonEntity() {

    var username: String? = null
    var password: String? = null

    fun convertRegisterDro(registerDto: RegisterDTO){
        username = registerDto.username
        password = registerDto.password
    }

    fun convertLoginDto(loginDto: LoginDto){
        username = loginDto.username
        password = loginDto.password
    }
}