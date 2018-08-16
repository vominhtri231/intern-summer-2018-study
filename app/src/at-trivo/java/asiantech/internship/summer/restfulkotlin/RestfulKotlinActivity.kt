package asiantech.internship.summer.restfulkotlin

import android.app.Activity
import android.content.ContentResolver
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.ResolveInfo
import android.database.Cursor
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.support.v4.app.ActivityCompat
import android.support.v4.content.ContextCompat
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ProgressBar
import asiantech.internship.summer.R
import asiantech.internship.summer.restfulkotlin.imagerecyclerview.ImageAdapter
import asiantech.internship.summer.restfulkotlin.model.Image
import asiantech.internship.summer.restfulkotlin.model.QueryImage
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File

fun getImageIntent(context: Context): Intent? {
    fun getIntentsFromAction(intent: Intent): MutableList<Intent> {
        val resolveInfos: List<ResolveInfo> = context.packageManager.queryIntentActivities(intent, 0)
        val result: MutableList<Intent> = mutableListOf()
        for (resolveInfo in resolveInfos) {
            val targetIntent = Intent(intent)
            targetIntent.setClassName(resolveInfo.activityInfo.packageName, resolveInfo.activityInfo.name)
            result.add(targetIntent)
        }
        return result
    }

    var chooserIntent: Intent? = null
    val intents = mutableListOf<Intent>()
    intents.addAll(getIntentsFromAction(Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)))
    intents.addAll(getIntentsFromAction(Intent(MediaStore.ACTION_IMAGE_CAPTURE)))
    if (intents.size > 0) {
        chooserIntent = Intent.createChooser(intents.removeAt(0), "")
        chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS, intents.toTypedArray())
    }
    return chooserIntent
}

fun getRealPathFromUri(contextResolver: ContentResolver, uri: Uri): String {
    val projections: Array<String> = arrayOf(MediaStore.Images.Media.DATA)
    val cursor: Cursor = contextResolver.query(uri, projections, null, null, null)
    cursor.moveToFirst()
    val result: String = cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA))
    cursor.close()
    return result
}

class RestfulKotlinActivity : AppCompatActivity() {

    companion object {
        const val ASK_PERMISSION_REQUEST_CODE = 1
        const val UPLOAD_IMAGE_REQUEST_CODE = 4
    }

    private val images: MutableList<Image> = mutableListOf()
    private val layoutManager: GridLayoutManager = GridLayoutManager(this, 2)
    private val adapter: ImageAdapter = ImageAdapter(images, this)

    private lateinit var recyclerView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var downloadProgressBar: ProgressBar
    private lateinit var uploadProgressBar: ProgressBar

    private var uploadIntent: Intent? = null
    private var isLoading: Boolean = false
    private var lastPage: Int = 0
    private var lastImageNumber: Int = ImageAPI.PER_PAGE
    private val downloadImagesCallBack: Callback<List<QueryImage>> = object : Callback<List<QueryImage>> {
        override fun onFailure(call: Call<List<QueryImage>>?, t: Throwable?) {
            downloadComplete()
        }

        override fun onResponse(call: Call<List<QueryImage>>?, response: Response<List<QueryImage>>?) {
            response?.isSuccessful?.let {
                if (it) {
                    val addImages = response.body()
                    addImages?.let {
                        images.addAll(addImages)
                        adapter.notifyDataSetChanged()
                        isLoading = true
                        lastPage += 1
                        lastImageNumber = addImages.size
                    }
                }
            }
            downloadComplete()
        }

        fun downloadComplete() {
            if (lastPage == 1) {
                swipeRefreshLayout.isRefreshing = false
            } else {
                downloadProgressBar.visibility = View.GONE
            }
        }
    }

    private val imagesAPI: ImageAPI

    init {
        val gson: Gson = GsonBuilder().setLenient().create()
        val getImagesRetrofit: Retrofit = Retrofit.Builder()
                .baseUrl(ImageAPI.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        imagesAPI = getImagesRetrofit.create(ImageAPI::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restful)
        initView()
        setUpRecyclerView()
    }

    private fun initView() {
        recyclerView = findViewById(R.id.recyclerView)
        swipeRefreshLayout = findViewById(R.id.swipeRefresh)
        downloadProgressBar = findViewById(R.id.progressBar)
        uploadProgressBar = findViewById(R.id.progressBarUpload)
    }

    private fun setUpRecyclerView() {
        recyclerView.adapter = adapter
        recyclerView.layoutManager = layoutManager
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (!isLoading && lastImageNumber == ImageAPI.PER_PAGE
                        && layoutManager.itemCount == layoutManager.findLastVisibleItemPosition() + 1) {
                    downloadProgressBar.visibility = View.VISIBLE
                    downloadImages()
                }
            }
        })

        swipeRefreshLayout.setOnRefreshListener {
            lastPage = 0
            swipeRefreshLayout.isRefreshing = true
            images.clear()
            downloadImages()
        }
    }

    private fun downloadImages() {
        isLoading = true
        imagesAPI.getImages(ImageAPI.TOKEN, lastPage + 1, ImageAPI.PER_PAGE).enqueue(downloadImagesCallBack)
    }

    fun uploadImage(view: View) {
        fun checkPermission(permission: String): Boolean {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(permission), ASK_PERMISSION_REQUEST_CODE)
                return false
            }
            return true
        }

        uploadIntent = getImageIntent(this)
        uploadIntent?.let {
            if (checkPermission(android.Manifest.permission.READ_EXTERNAL_STORAGE)) {
                startActivityForResult(uploadIntent, UPLOAD_IMAGE_REQUEST_CODE)
            }
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        if (requestCode == ASK_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startActivityForResult(uploadIntent, UPLOAD_IMAGE_REQUEST_CODE)
            }
        } else {
            super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == UPLOAD_IMAGE_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            val uri: Uri? = data?.data
            uri?.let {
                val file = File(getRealPathFromUri(contentResolver, uri))
                val image: MultipartBody.Part = MultipartBody.Part.createFormData(
                        "imagedata",
                        file.name,
                        RequestBody.create(MediaType.parse("multipart/form-data"), file)
                )
                val token = RequestBody.create(MediaType.parse("text/plain"), ImageAPI.TOKEN)
                imagesAPI.uploadImage(ImageAPI.UPLOAD_URL, token, image)
                uploadProgressBar.visibility = View.VISIBLE
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

}
