package com.example.demoproject

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demoproject.Response.Adapter.NewsAdapter
import com.example.demoproject.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var mainViewModel: MainViewModel

    lateinit var binding : ActivityMainBinding

    @Inject
    lateinit var adapter: NewsAdapter

    private val compositeDisposable = CompositeDisposable()


    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        mainViewModel.fetchData(){
            it.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ responseData ->
                Toast.makeText(this,"Response successfully retrieved",Toast.LENGTH_SHORT).show()
                adapter.updateList(responseData.articles)
            }, { error ->
                Toast.makeText(this,"Response error retrieved",Toast.LENGTH_SHORT).show()
            })
        }

        binding.rvNewslist.apply {
            adapter = this@MainActivity.adapter
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear() // Dispose all subscriptions when activity is destroyed
    }
}