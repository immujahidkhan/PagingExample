package com.paging

enum class Status {
    RUNNING,
    SUCCESS,
    FAILED
}

enum class RequestState {
    REQUESTED, DECLINE, ACCEPTED, REJECTED
}

class NetworkState(val status: Status, val msg: String) {

    companion object {
        val LOADED: NetworkState = NetworkState(Status.SUCCESS, "Success")
        val LOADING: NetworkState = NetworkState(Status.RUNNING, "Running")
        val LOADING_MORE_START: NetworkState = NetworkState(Status.RUNNING, "Running")
        val ERROR: NetworkState = NetworkState(Status.FAILED, "Something went wrong")
        val END_OF_LIST: NetworkState = NetworkState(Status.FAILED, "You have reached the end")
    }
}