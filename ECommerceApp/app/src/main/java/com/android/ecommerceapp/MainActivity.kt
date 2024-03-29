package com.android.ecommerceapp

import android.animation.LayoutTransition
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.KeyEvent
import android.view.View
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.res.ResourcesCompat
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.android.ecommerceapp.databinding.ActivityMainBinding
import com.android.ecommerceapp.model.enums.MessageType
import com.android.ecommerceapp.util.heightRatio
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isShowingDialog = false
    private var isShowingInfo = false
    private val constraintSet = ConstraintSet()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

    }

    fun showProgress() {
        isShowingDialog = true
         binding.activityProgressbarLayout.visibility = View.VISIBLE
    }

    fun hideProgress() {
        isShowingDialog = false
        binding.activityProgressbarLayout.visibility = View.GONE
    }

    fun showMessage(message:String?,type: MessageType){
        isShowingInfo = true
        hideProgress()
        binding.wrapperLayout.visibility = View.VISIBLE

        constraintSet.apply {

            clone(binding.mainLayout)
            clear(binding.textMessage.id, ConstraintSet.BOTTOM)
            connect(
                binding.textMessage.id,
                ConstraintSet.TOP,
                ConstraintSet.PARENT_ID,
                ConstraintSet.TOP,
                0.15f.heightRatio
            )
            applyTo(binding.mainLayout)
        }
        binding.mainLayout.layoutTransition = LayoutTransition().apply {
            enableTransitionType(LayoutTransition.CHANGING)
            setDuration(300)
        }

        when (type) {
            MessageType.ERROR -> binding.textMessage.background =
                ResourcesCompat.getDrawable(resources, R.drawable.message_error, null)

            /*
               MessageType.SUCCESS -> binding.textMessage.background =
                ResourcesCompat.getDrawable(resources, R.drawable.bg_success_message, null)
            MessageType.WARNING -> binding.textMessage.background =
                ResourcesCompat.getDrawable(resources, R.drawable.bg_warning_message, null)
             */

            else -> {
                println("else")
            }
        }

        binding.textMessage.apply {
            text = message
            alpha = 1f
            postDelayed(
                { hideMessage() }, 1500
            )
        }

    }
    private fun hideMessage() {
        isShowingInfo = false
        binding.textMessage.animate().alpha(0f).setDuration(500).withEndAction {
            constraintSet.apply {
                clone(binding.mainLayout)

                clear(binding.textMessage.id, ConstraintSet.TOP)
                connect(
                    binding.textMessage.id,
                    ConstraintSet.BOTTOM,
                    ConstraintSet.PARENT_ID,
                    ConstraintSet.TOP
                )
                applyTo(binding.mainLayout)

                binding.wrapperLayout.visibility = View.INVISIBLE
            }
        }
    }
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK && isShowingDialog && isShowingInfo) {
            return true        // Dialog (progressView veya infoView) gösteriliyorken geri tuşu tıklanmasın
        }

        return super.onKeyDown(keyCode, event)
    }
}