package com.example.tickettoto.helpers

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.transition.Fade
import androidx.transition.TransitionInflater
import androidx.transition.TransitionSet

class FragmentHandler(private val activity: AppCompatActivity, private val fragmentContainer: Int? = null) {

    companion object {
        private const val MOVE_DEFAULT_TIME: Long = 1000
        private const val FADE_DEFAULT_TIME: Long = 150
        const val NO_ADD_TO_BACK_STACK = "NO_ADD_TO_BACK_STACK"
    }

    fun add(fragment: androidx.fragment.app.Fragment, addToBackStack: Boolean = false) {

        val enterTransitionSet = TransitionSet()
        enterTransitionSet.addTransition(TransitionInflater.from(activity).inflateTransition(android.R.transition.move))
        enterTransitionSet.duration = MOVE_DEFAULT_TIME
        enterTransitionSet.startDelay = FADE_DEFAULT_TIME
        fragment.sharedElementEnterTransition = enterTransitionSet

        val enterFade = Fade()
        enterFade.startDelay = FADE_DEFAULT_TIME
        enterFade.duration = FADE_DEFAULT_TIME
        fragment.enterTransition = enterFade

        if (!addToBackStack) activity.supportFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        val fragmentTransaction = activity.supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(fragmentContainer!!, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

}