
import com.example.podcast.interfaces.DiscoverPod
import com.example.podcast.interfaces.RestClientBuilder
import com.example.podcast.model.*
import retrofit2.Response

object DiscoverPodRequest {

    private var coRoutineRequest: DiscoverPod? = null

    suspend fun getBannersList(): Response<ListData<BannerList>> {
        return coRoutineRequest!!.getBannersList()
    }

    suspend fun getEpisodes(bannerId: String): Response<ListData<DataList>>{
        return coRoutineRequest!!.getEpisodes(bannerId)
    }

    suspend fun getComments(bannerId: String):Response<ListData<Comment>> {
        return coRoutineRequest!!.getComments(bannerId)

    }
    init {
        coRoutineRequest = RestClientBuilder.createServiceForCoRoutineInApp(DiscoverPod::class.java)

    }
}