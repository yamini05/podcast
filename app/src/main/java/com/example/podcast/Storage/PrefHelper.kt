package com.example.podcast.Storage
import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


const val SHARED_PREF = "SHAREDPREF"
class PrefHelper(ctx: Context) {
    private var sharedPreferences : SharedPreferences = ctx.getSharedPreferences(SHARED_PREF, Context.MODE_PRIVATE)

    companion object {
        private const val STORE_SUBSCRIBERS = "SUBSCRIBERS"
        private const val STORE_COMMENTS = "COMMENTS"
    }

    fun isSubscribed(podcastId: Int?):Boolean{
        val storedHashMapString: String? = sharedPreferences.getString("subscriptionValue", "oopsDintWork")
        val type =
            object : TypeToken<HashMap<String?, String?>?>() {}.type
        if(storedHashMapString!=null && !storedHashMapString.equals("oopsDintWork")){
            val testHashMap2: HashMap<String, String> =
                Gson().fromJson(storedHashMapString, type)
            return testHashMap2.containsKey(podcastId.toString())
        }else{
            return false
        }
    }

    fun subscribedPodCast(podcastId : Int?) {
        val values =
            HashMap<String, Boolean>()
        values.put(podcastId.toString(),true)
        val gson = Gson()
        val hashMapString = gson.toJson(values)

        sharedPreferences.edit().putString("subscriptionValue", hashMapString).apply()
    }

    fun getCommentById(podcastId: Int?):ArrayList<String>?{
        val storedHashMapString: String? = sharedPreferences.getString("commentValues", "oopsDintWork")
        val type =
            object : TypeToken<HashMap<String?, ArrayList<String>?>?>() {}.type
        if(storedHashMapString!=null && !storedHashMapString.equals("oopsDintWork")){
            val testHashMap2: HashMap<String, ArrayList<String>> =
                Gson().fromJson(storedHashMapString, type)
            if(testHashMap2.containsKey(podcastId.toString())){
                    return testHashMap2.get(podcastId.toString())!!
                }else{
                return null
            }

        }else{
            return null
        }
    }

    fun saveComment(podcastId : Int?, comment:String) {
        var testHashMap2: HashMap<String, ArrayList<String>>? = null
        val storedHashMapString: String? = sharedPreferences.getString("commentValues", "oopsDintWork")
        val type =
            object : TypeToken<HashMap<String?, ArrayList<String>?>?>() {}.type
        if(storedHashMapString!=null && !storedHashMapString.equals("oopsDintWork")){
            testHashMap2 =
                Gson().fromJson(storedHashMapString, type)
        }

        if(testHashMap2!=null && testHashMap2.containsKey(podcastId.toString())){
            var list = testHashMap2.get(podcastId.toString())
            list!!.add(comment)
            val values =
                HashMap<String, List<String>>()
            values.put(podcastId.toString(),list)

            val gson = Gson()
            val hashMapString = gson.toJson(values)
            sharedPreferences.edit().putString("commentValues", hashMapString).apply()
        }else{
            var commentList = ArrayList<String>()
            commentList.add(comment)
            val values =
                HashMap<String, List<String>>()
            values.put(podcastId.toString(),commentList)

            val gson = Gson()
            val hashMapString = gson.toJson(values)
            sharedPreferences.edit().putString("commentValues", hashMapString).apply()
        }

    }

    fun getSubscribedData() : String {
        return sharedPreferences.getString(STORE_SUBSCRIBERS, "")!!
    }

    fun isCommented(string: String) {
        val editor = sharedPreferences.edit()
        editor.putString(STORE_COMMENTS, string)
        editor.apply()
    }

    fun getCommentedData() : String {
        return sharedPreferences.getString(STORE_COMMENTS, "")!!
    }
}