package com.comfortment.presentation.ui.main

import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.comfortment.R
import com.comfortment.R.menu.edit_navigation
import com.comfortment.R.menu.home_navigation
import com.comfortment.presentation.ui.base.BaseActivity
import com.comfortment.presentation.ui.dialog.LoadingDialog
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override val layoutId: Int
        get() = R.layout.activity_main

    val loadingDialog = LoadingDialog.getInstance()

    lateinit var navController: NavController
    private var currentFabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
    private val navOptions = NavOptions.Builder()
        .setEnterAnim(R.anim.nav_default_enter_anim)
        .setExitAnim(R.anim.nav_default_exit_anim)
        .setPopEnterAnim(R.anim.nav_default_pop_enter_anim)
        .setPopExitAnim(R.anim.nav_default_pop_exit_anim)
        .build()

    private var oldLabel = "MAIFragment"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(bottom_app_bar)

        bottom_app_bar.hideOverflowMenu()

        bottom_app_bar.setNavigationOnClickListener {
            if (currentFabAlignmentMode == BottomAppBar.FAB_ALIGNMENT_MODE_CENTER) {
                fab.hide(addVisibilityChanged)
                invalidateOptionsMenu()
                navController.navigate(R.id.nanumFragment, null, navOptions)
            }
        }

        fab.setOnClickListener {
            if (navController.currentDestination!!.id != R.id.MAIFragment) {
                if (currentFabAlignmentMode != BottomAppBar.FAB_ALIGNMENT_MODE_CENTER) {
                    fab.hide(addVisibilityChanged)
                    invalidateOptionsMenu()
                }
                navController.navigate(R.id.MAIFragment, null, navOptions)
            }
        }

        navController = findNavController(R.id.main_fragment)
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.label.toString()) {
                "nanumFragment" -> {
                    bottom_app_bar.visibility = View.VISIBLE
                    if (oldLabel != "MAIFragment")
                        fab.show()
                }
                "NanumMineFragment" -> {
                    bottom_app_bar.visibility = View.VISIBLE
                    fab.show()
                }
                "NanumWriteFragment" -> {
                    bottom_app_bar.visibility = View.INVISIBLE
                    fab.hide()
                }
                "ShowNanumDetailFragment" -> {
                    bottom_app_bar.visibility = View.INVISIBLE
                    fab.hide()
                }
                "NanumEditFragment" -> {
                    bottom_app_bar.visibility = View.INVISIBLE
                    fab.hide()
                }
            }
            oldLabel = destination.label.toString()
        }
    }

    override fun onBackPressed() =
        if (navController.currentDestination!!.id == R.id.MAIFragment) {
            finish()
        } else {
            if (navController.currentDestination!!.id == R.id.nanumFragment) {
                fab.hide(addVisibilityChanged)
                invalidateOptionsMenu()
            }
            super.onBackPressed()
        }

    private val addVisibilityChanged = object : FloatingActionButton.OnVisibilityChangedListener() {
        override fun onHidden(fab: FloatingActionButton?) {
            super.onHidden(fab)

            bottom_app_bar.toggleFabAlignment()
            bottom_app_bar.navigationIcon =
                if (currentFabAlignmentMode == BottomAppBar.FAB_ALIGNMENT_MODE_CENTER) null
                else resources.getDrawable(R.drawable.ic_shopping_basket, null)
            bottom_app_bar.replaceMenu(
                if (currentFabAlignmentMode == BottomAppBar.FAB_ALIGNMENT_MODE_CENTER)
                    edit_navigation
                else
                    home_navigation
            )
            currentFabAlignmentMode =
                if (currentFabAlignmentMode == BottomAppBar.FAB_ALIGNMENT_MODE_CENTER)
                    BottomAppBar.FAB_ALIGNMENT_MODE_END
                else
                    BottomAppBar.FAB_ALIGNMENT_MODE_CENTER
            fab?.show()
        }
    }

    fun BottomAppBar.toggleFabAlignment() {
        currentFabAlignmentMode = fabAlignmentMode
        fabAlignmentMode = currentFabAlignmentMode.xor(1)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.MAIEditFragment -> {
                if (navController.currentDestination!!.id != R.id.MAIEditFragment) {
                    navController.navigate(R.id.MAIEditFragment, null, navOptions)
                }
            }
            R.id.NanumFragment -> {
                if (navController.currentDestination!!.id != R.id.nanumFragment) {
                    navController.navigate(R.id.nanumFragment, null, navOptions)
                }
            }
            R.id.NanumMineFragment -> {
                if (navController.currentDestination!!.id != R.id.nanumMineFragment) {
                    navController.navigate(R.id.nanumMineFragment, null, navOptions)
                }
            }
            R.id.NanumWriteFragment -> {
                if (navController.currentDestination!!.id != R.id.nanumWriteFragment) {
                    navController.navigate(R.id.nanumWriteFragment, null, navOptions)
                }
            }
        }
        return false
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(home_navigation, menu)
        return true
    }
}
