package com.example.passwordgenerator.activity

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.passwordgenerator.databinding.ActivityHomeBinding
import kotlin.math.floor

/**
 * Created by Rajdeep Sarkar on 09/02/2023
 * */

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val masterKey = MasterKey.Builder(this)
            .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
            .build()

        val sharedPreferences = EncryptedSharedPreferences.create(
            applicationContext,
            "oldPasswordPreference",
            masterKey,
            EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
            EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
        )

        binding.oldPassword.text = sharedPreferences.getString("old_password", "").toString()
        binding.newPassword.text = sharedPreferences.getString("new_password", "").toString()

        binding.generatepasswordBtn.setOnClickListener {
            val editor: SharedPreferences.Editor = sharedPreferences.edit()
            editor.putString("old_password", binding.newPassword.text.toString())
            editor.apply()
            binding.oldPassword.text = sharedPreferences.getString("old_password", "").toString()
            binding.newPassword.text = generatePassword()
            editor.putString("new_password", binding.newPassword.text.toString())
            editor.apply()
        }
    }

    private fun generateNumbers(size: Int): Int {
        var size = size
        var generatedNumbers = 0
        while (size-- > 0) {
            val randomNumbers = floor(Math.random() * 9).toInt() + 1
            generatedNumbers = generatedNumbers * 10 + randomNumbers
        }
        return generatedNumbers
    }

    private fun generateCharacters(size: Int): String {
        var size = size
        var generatedCharacters = ""
        while (size-- > 0) {
            val randomCharacters = floor(Math.random() * 14).toInt() + 33
            val ran2 = floor(Math.random() * 7).toInt() + 58
            generatedCharacters =
                if (size % 2 == 0) generatedCharacters + randomCharacters.toChar() else generatedCharacters + ran2.toChar()
        }
        return generatedCharacters
    }

    private fun generateRandomString(arr: Array<String>): String {
        val arraySize = arr.size
        val randomString = floor(Math.random() * arraySize).toInt()
        return arr[randomString]
    }

    private fun generatePassword(): String {
        val stringOfKeywords =
            "able acid acre aged aide akin alas ally also alto amid anal anna anti apex arch area army atom atop aunt aura auto avid away axis baby bach back bail bait bake bald ball band bang bank bare bark barn base bass bath bats beam bean bear beat beck beef been beer bell belt bend bent best beta beth bias bike bill bind bird bite blew bloc blog blow blue blur boat body boil bold bolt bomb bond bone book boom boon boot bore born boss both bout bowl brad bred brew brow buck bulb bulk bull bump burn bury bush bust busy butt buzz cafe cage cake calf call calm came camp cane cape card care carl carr cart case cash cast cave cell cent chad chap chat chef chic chin chip chop cite city clad clan clay clip club clue coal coat coca code coil coin coke cola cold cole come cone conn cook cool cope copy cord core cork corn cost coup cove crap crew crop crow cube cult curb cure cute dale dame damn damp dare dark dash data date dawn days dead deaf deal dean dear debt deck deed deep deer dell demo dent deny desk dial dice diet dire dirt disc dish disk dive dock does dole doll dome done doom door dose dove down drag draw drew drop drug drum dual duck duff duke dull duly dumb dump dusk dust duty each earl earn ease east easy eats echo edge edit else envy epic euro even ever evil exam exit expo eyed face fact fade fail fair fake fall fame fare farm fast fate fear feat feed feel feet fell felt file fill film find fine fire firm fish fist five flag flat fled flee flew flex flip flow flux foam foil fold folk fond font food fool foot ford fore fork form fort foul four free frog from fuck fuel lion full fund fury fuse fuss gain gala gale gall game gang gate gave gaze gear gene gift gill girl give glad glen glow glue goal goat goes gold golf gone good gore gown grab gram gray grew grey grid grim grin grip grow gulf guru hail hair hale half hall halt hand hang hank hard harm hart hate haul have hawk head heal heap hear heat heel heir held hell helm help herb herd here hero hers hide high hike hill hint hire hold hole holt holy home hood hook hope horn hose host hour huge hull hung hunt hurt hype icon idea idle idol inch info into iris iron isle item jack jail jake jane java jazz jean pile jeep jill joey john join joke josh jump junk jury just keen keep kemp kent kept khan kick kill mark kind king kirk kite knee knew knit knot know kohl song Kyle lace lack lady laid lake lamb lamp land lane lang last late lava lawn lazy lead leaf leak lean leap left lend lens lent less lest levy lied lien life lift"
        val array = stringOfKeywords.split(" ".toRegex()).dropLastWhile { it.isEmpty() }
            .toTypedArray()
        val map: MutableMap<Char, Char> = HashMap()
        map['a'] = '@'
        map['s'] = '$'
        map['i'] = '!'
        map['o'] = '0'
        var generatedPassword =
            generateRandomString(array) + generateCharacters(1) + generateRandomString(array) + generateNumbers(
                3
            )
        var temp = ""
//        var con=0
        for (element in generatedPassword) {
            //            if(con==0 && !map.containsKey(ch))
//                ch= ch.uppercaseChar()
            temp = if (map.containsKey(element)) temp + map[element] else temp + element
        }
        generatedPassword = temp
        return generatedPassword
    }
}