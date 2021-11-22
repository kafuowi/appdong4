package com.example.tourspot

import android.app.Dialog
import android.graphics.Color
import android.graphics.Point
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.*
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    lateinit var toolbar: androidx.appcompat.widget.Toolbar
    private val fragmentManager: FragmentManager = supportFragmentManager
    lateinit var searchDialog:Dialog
    var homeOrDashboard:Int = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        replaceFragment(HomeFragment())

        /* tool bar */
        toolbar = findViewById(R.id.include) // 툴바 생성
        setSupportActionBar(toolbar) // 우리가 만든 툴바를 ActionBar로서 사용
        val actionBar:ActionBar = supportActionBar!!
        actionBar.setDisplayShowCustomEnabled(true) //커스터마이징 하기 위해 필요
        actionBar.setDisplayShowTitleEnabled(false) //기본 제목(앱 이름) 삭제



        /* bottom navigationView */
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.navigationView)
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when(item.itemId){
                R.id.home -> {
                    findViewById<TextView>(R.id.toolBarTitle).text = "둘러보기"
                    homeOrDashboard = 0
                    replaceFragment(HomeFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.dashboard -> {
                    findViewById<TextView>(R.id.toolBarTitle).text = "게시판"
                    homeOrDashboard = 1
                    replaceFragment(DashboardFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                else -> {
                    return@setOnNavigationItemSelectedListener false
                }
            }
        }
    }

    private fun replaceFragment(fragment:Fragment){
        /* 다른 프래그먼트로 이동 */
        val transaction:FragmentTransaction = fragmentManager.beginTransaction()
        transaction.replace(R.id.frameLayout, fragment).commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        /* 커스텀 툴바 적용하기 */
        val menuInflater:MenuInflater = menuInflater
        menuInflater.inflate(R.menu.actionbar, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        /* 툴바 버튼 클릭 리스너 정의 */
        return when(item.itemId){
            R.id.search_button -> {
                Log.d("툴바", "돋보기 클릭")
                showDialogSearch() //커스텀 다이얼로그 띄우기
                true
            }
            else -> {
                super.onOptionsItemSelected(item)
            }
        }

    }


    private fun showDialogSearch(){
        /* dialog_search.xml 보여주기 */
        searchDialog = Dialog(this)
        searchDialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        searchDialog.requestWindowFeature(Window.FEATURE_NO_TITLE) // 타이틀 제거
        searchDialog.setContentView(R.layout.dialog_search) // xml 레이아웃 파일과 연결
        /* 제목 설정 */
        if(homeOrDashboard == 0){
            searchDialog.findViewById<TextView>(R.id.searchDialogTitle).text = "관광지 검색"
        }else if(homeOrDashboard == 1){
            searchDialog.findViewById<TextView>(R.id.searchDialogTitle).text = "리뷰 검색"
        }
        /* 크기 설정 */
        val display:Display = windowManager.defaultDisplay
        val size: Point = Point()
        display.getSize(size)
        val window:Window = searchDialog.window!!
        val x:Int = (size.x * 0.9f).toInt()
        window.attributes.height = WindowManager.LayoutParams.WRAP_CONTENT
        window.setLayout(x, window.attributes.height)
        /*  */

            val selectCity = findViewById<Spinner>(R.id.selectCitySpinner) //지역선택





            //val selectCityDetail = findViewById<Spinner>(R.id.selectCityDetail) //시군구 선택




        searchDialog.findViewById<Button>(R.id.dialogSearchButton).setOnClickListener {
            Log.d("dialog_search", "검색")
            /*if(homeOrDashboard == 0){
                //HomeFragment로 선택한 값 넘기기
            }else if(homeOrDashboard==1){
                //DashboardFragment로 선택한 값 넘기기
            }*/
        }

        searchDialog.show()

    }


}