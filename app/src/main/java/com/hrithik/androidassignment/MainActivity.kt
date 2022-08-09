package com.hrithik.androidassignment

import android.os.Bundle
import android.view.View.GONE
import android.view.View.VISIBLE
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayoutMediator
import com.hrithik.androidassignment.adapter.CategoryAdapter
import com.hrithik.androidassignment.databinding.ActivityMainBinding
import com.hrithik.androidassignment.models.DTO
import com.hrithik.androidassignment.viewModel.ThemeViewModel


class MainActivity : AppCompatActivity() {

    private lateinit var b : ActivityMainBinding
    private val themeVM : ThemeViewModel by lazy { ViewModelProvider(this)
        .get(ThemeViewModel::class.java) }
    private var adapter: CategoryAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        displayLoadingScreen()
        themeVM.fetchThemes()

        themeVM
            .fetchSuccessful
            .observe(this) {
                // Continue when variable becomes non-null
                it?.let {
                    themeVM.apply {
                        if (it) displayData(themeDto)
                        else displayErrorMsg(errorMessage)
                    }
                }
            }

    }

    private fun displayLoadingScreen() {
        b.apply {
            errorLayout.noConnectionLayout.visibility = GONE
            viewpager.visibility = GONE
            tabsLayout.visibility = GONE
            progressBar.visibility = VISIBLE
        }
    }

    private fun displayErrorMsg(errorMessage: String) {
        b.apply{
            progressBar.visibility = GONE
            viewpager.visibility = GONE
            tabsLayout.visibility = GONE
            errorLayout.noConnectionLayout.visibility = VISIBLE
            errorLayout.errorMessage.text = errorMessage
        }
    }

    private fun displayData(themeDto: DTO) {
        b.apply {
            errorLayout.noConnectionLayout.visibility = GONE
            progressBar.visibility = GONE
            viewpager.visibility = VISIBLE
            tabsLayout.visibility = VISIBLE
            initViews(themeDto)
            adapter!!.setCardGroupData(themeDto)
        }
    }

    private fun initViews(themeDto: DTO) {

        val list = createCategoryList(themeDto)

        adapter = if(adapter == null) CategoryAdapter(this) else adapter
        b.viewpager.adapter = adapter
        b.viewpager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }

            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
            }
            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        })

        TabLayoutMediator(b.tabsLayout,b.viewpager){ tab, position ->
            tab.text = list[position]
        }.attach()

    }

    private fun createCategoryList(themeDto: DTO): List<String> {
        val list = mutableListOf<String>()
        for(data in themeDto.data){
            list.add(data.Cat_Name)
        }
        return list
    }

}