package com.anil.htf.utility

import android.content.Context
import com.anil.htf.database.AppDatabase
import com.anil.htf.network.HTFApiService
import com.anil.htf.network.PostNetworkResource
import com.anil.htf.repository.PostRepository
import com.anil.htf.viewModel.PostViewModelFactory

object InjectUtils {

    private fun getPostRepository(
        context: Context,
        htfApiService: HTFApiService
    ): PostRepository {
        return PostRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).postsDao(),
            PostNetworkResource.getInstance(htfApiService, prefProvider(context)),
            AppExecutors.getInstance()
        )
    }

    fun providePostViewModelFactory(context: Context): PostViewModelFactory {
        val repository = getPostRepository(context, retrofitProvider())
        return PostViewModelFactory(repository)
    }

    private fun retrofitProvider(): HTFApiService = HTFApiService.create()

    private fun prefProvider(context: Context): Prefs = Prefs(context.applicationContext)
}