package com.smlnskgmail.jaman.androidaccessibility.fragments

import android.content.Context
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.smlnskgmail.jaman.androidaccessibility.R

class DetailFragment : BaseFragment() {

    private lateinit var animationListener: AnimationListener

    @SuppressWarnings("SwallowedException")
    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            animationListener = context as AnimationListener
        } catch (e: ClassCastException) {
            throw ClassCastException(
                "$context must implement the AnimationListener interface"
            )
        }
    }

    override fun onCreateAnimation(
        transit: Int,
        enter: Boolean,
        nextAnim: Int
    ): Animation? {
        val animation = AnimationUtils.loadAnimation(
            activity,
            nextAnim
        )
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {
                // NO-OP
            }

            override fun onAnimationEnd(animation: Animation?) {
                animationListener.onAnimationEnd()
            }

            override fun onAnimationStart(animation: Animation?) {
                // NO-OP
            }
        })
        return animation
    }

    override fun getLayoutResId(): Int {
        return R.layout.fragment_detail
    }

    companion object {

        fun newInstance(): DetailFragment {
            return DetailFragment()
        }

    }

    interface AnimationListener {

        fun onAnimationEnd()

    }

}
