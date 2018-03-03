package android.nni.com.theredotcomandroid.entities

import android.nni.com.theredotcomandroid.dtos.RegisterDTO

/**
 * Created by Marcus Garmon on 2/24/2018.
 */
class Account : GsonEntity() {
     var id: Long? = null
     var user: User? = null
     var email: String = ""
     var firstName : String = ""
     var lastName : String = ""


     fun setName(registerDTO: RegisterDTO){
          var names = registerDTO.name.split(" ")
          firstName = names[0]
          lastName = names[1]
     }

}