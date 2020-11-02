import androidx.paging.PagingSource
import com.mylearning.unsplashphotoapp.api.UnsplashApi
import com.mylearning.unsplashphotoapp.data.UnsplashPhoto
import retrofit2.HttpException
import java.io.IOException

/**
 * This Class knows how to load data from the remote source
 * No dependency injection because we can only know the query term at run time and now at compile time...
 */

private const val UNSLASH_STARTING_PAGE_INDEX = 1
class UnsplashPagingSource (
    private val unsplashApi: UnsplashApi,
    private val query : String
) : PagingSource<Int, UnsplashPhoto>() {
    /**
     * load function is responsible in triggering the api request and turn the data to pages
     * Load is a suspend func...paging 3 takes cares of launching the necessary coroutine
     */
    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, UnsplashPhoto> {
        // Current page we are
        val position = params.key ?: UNSLASH_STARTING_PAGE_INDEX

        return try {
            // Api call
            val response = unsplashApi.searchPhotos(query, position, params.loadSize)
            val photos = response.results

            // If every thing goes well
            LoadResult.Page(
                data = photos,
                prevKey = if (position == UNSLASH_STARTING_PAGE_INDEX) null else position - 1,
                nextKey = if (photos.isEmpty()) null else position + 1
            )
        } catch (exception: IOException) {
            // When there is internet connection when we make the request
            LoadResult.Error(exception)

        } catch (exception: HttpException) {
            // Http request will be made when we make the request but there is a problem with the server || forgot the access key || or no data
            LoadResult.Error(exception)
        }
    }
}