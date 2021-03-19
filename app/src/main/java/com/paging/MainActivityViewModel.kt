package com.paging

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.paging.models.Posts
import com.paging.models.PostsItem
import io.reactivex.disposables.CompositeDisposable
import java.util.HashMap

class MainActivityViewModel() : ViewModel() {

    lateinit var usersProfileDataSourceFactory: UsersProfileDataSourceFactory
    private var compositeDisposable = CompositeDisposable()
    private val networkState: MutableLiveData<NetworkState> = MutableLiveData()
    private var userProfilesPagedList: LiveData<PagedList<PostsItem>> = MutableLiveData()

    fun requestOrderByPagedList(
        mapString: HashMap<String, String>,
        activity: Activity
    ): LiveData<PagedList<PostsItem>> {
        usersProfileDataSourceFactory =
            UsersProfileDataSourceFactory(
                RetrofitClient.api,
                compositeDisposable,
                "users",
                mapString,
                networkState, activity
            )
        val config = PagedList.Config.Builder()
            .setEnablePlaceholders(true)
            .setPrefetchDistance(15)
            .setPageSize(15)
            .build()
        userProfilesPagedList =
            LivePagedListBuilder(usersProfileDataSourceFactory, config).build()
        return userProfilesPagedList
    }

}