package com.comfortment.presentation.ui.main

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.navigation.NavController
import androidx.navigation.NavOptions
import androidx.navigation.findNavController
import com.comfortment.R
import com.comfortment.R.menu.edit_navigation
import com.comfortment.R.menu.home_navigation
import com.comfortment.presentation.ui.base.BaseActivity
import com.google.android.material.bottomappbar.BottomAppBar
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    override val layoutId: Int
        get() = R.layout.activity_main

    lateinit var navController: NavController
    private var currentFabAlignmentMode = BottomAppBar.FAB_ALIGNMENT_MODE_CENTER

    private val navOptions = NavOptions.Builder()
        .setEnterAnim(R.anim.nav_default_enter_anim)
        .setExitAnim(R.anim.nav_default_exit_anim)
        .setPopEnterAnim(R.anim.nav_default_pop_enter_anim)
        .setPopExitAnim(R.anim.nav_default_pop_exit_anim)
        .build()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(bottom_app_bar)

        bottom_app_bar.setNavigationOnClickListener {
            if (currentFabAlignmentMode == BottomAppBar.FAB_ALIGNMENT_MODE_CENTER) {
                fab.hide(addVisibilityChanged)
                invalidateOptionsMenu()
                navController.navigate(R.id.AOSFragment, null, navOptions)
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
    }

    override fun onBackPressed() =
        if (navController.currentDestination!!.id == R.id.MAIFragment) {
            finish()
        } else {
            if (navController.currentDestination!!.id == R.id.AOSFragment) {
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

    private fun BottomAppBar.toggleFabAlignment() {
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
        }
        return false
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(home_navigation, menu)
        return true
    }
}
