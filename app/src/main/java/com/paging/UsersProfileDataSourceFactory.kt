package com.paging

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.HashMap
import androidx.paging.DataSource
import com.paging.models.Posts
import com.paging.models.PostsItem
import io.reactivex.disposables.CompositeDisposable

class UsersProfileDataSourceFactory(
    private val api: ApiInterface,
    private val compositeDisposable: CompositeDisposable,
    private val endPoint: String,
    private val mapString: HashMap<String, String>,
    private val networkState: MutableLiveData<NetworkState>,
    private val activity: Activity
) : DataSource.Factory<Int, PostsItem>() {

    lateinit var profileDataSource: UsersProfileDataSource
    private val liveDataSource = MutableLiveData<UsersProfileDataSource>()

    override fun create(): DataSource<Int, PostsItem> {
        profileDataSource =
            UsersProfileDataSource(
                api,
                compositeDisposable,
                endPoint,
                mapString,
                networkState,
                activity
            )
        liveDataSource.postValue(profileDataSource)
        return profileDataSource
    }

    fun refresh() {
        if (this::profileDataSource.isInitialized) {
            profileDataSource.invalidate()
        } else {
            create()
        }
    }
}


class UsersProfileDataSource(
    private val api: ApiInterface,
    private val compositeDisposable: CompositeDisposable,
    private val endPoint: String,
    private val mapString: HashMap<String, String>,
    private val networkState: MutableLiveData<NetworkState>,
    private val activity: Activity
) : PageKeyedDataSource<Int, PostsItem>() {
    private var page = 1
    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, PostsItem>
    ) {
        mapString["page"] = page.toString()
        networkState.postValue(NetworkState.LOADING)
        compositeDisposable.add(
            api.getPaginatedProfile(endPoint, mapString)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        callback.onResult(it, page - 1, page + 1)
                        networkState.postValue(NetworkState.LOADED)
                    },
                    {
                        networkState.postValue(NetworkState.ERROR)
                    }
                )
        )
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, PostsItem>) {
        mapString["page"] = params.key.toString()
        networkState.postValue(NetworkState.LOADING_MORE_START)
        compositeDisposable.add(
            api.getPaginatedProfile(endPoint, mapString)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    {
                        callback.onResult(it, params.key + 1)
                    },
                    {
                        networkState.postValue(NetworkState.ERROR)
                    }
                )
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, PostsItem>) {

    }

    companion object {
        private const val TAG = "UsersProfileDataSourceFactory"
    }
}