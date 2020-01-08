package geeko.app.wecankode.view


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle

import android.view.Menu
import android.view.MenuInflater
import geeko.app.wecankode.model.DataModel
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.SearchView
import geeko.app.wecankode.R

import geeko.app.wecankode.databinding.ActivityMainBinding
import geeko.app.wecankode.model.DataInfo
import geeko.app.wecankode.viewmodel.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main.view.*


class MainActivity : AppCompatActivity(), CustomAdapter.OnCategoryClickListener, SwipeRefreshLayout.OnRefreshListener {
    override fun onRefresh() {
        subscribeDataCallBack()
    }

    override fun onCategoryClick(position: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var viewModel: MainViewModel
    lateinit var binding: ActivityMainBinding
    var dataList: ArrayList<DataInfo> = ArrayList()
    lateinit var searchView: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        binding.viewmodel = viewModel
        binding.swipeRefreshLayout.setOnRefreshListener(this)

        setRecyclerView(dataList)   //send empty list initially

        subscribeDataCallBack()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_layout, menu)

        val item = menu?.findItem(R.id.search)
        searchView = item?.getActionView() as SearchView
        searchView.setOnQueryTextListener(viewModel.getOnQueryTextChange(binding.root.recyclerView.adapter as CustomAdapter))
        return super.onCreateOptionsMenu(menu)

    }

    private fun subscribeDataCallBack() {

        viewModel.getProjectList()?.observe(this, Observer<List<DataInfo>> {
            if (it != null) {
                customAdapter.setAppList(it)
            }
            if (swipeRefreshLayout.isRefreshing){
                swipeRefreshLayout.isRefreshing = false
            }
        })

    }

    private lateinit var customAdapter: CustomAdapter

    private fun setRecyclerView(dataList: ArrayList<DataInfo>) {
        customAdapter = CustomAdapter(this)
        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.root.recyclerView.layoutManager = linearLayoutManager
        customAdapter.setAppList(dataList)
        binding.root.recyclerView.adapter = customAdapter


    }
}
