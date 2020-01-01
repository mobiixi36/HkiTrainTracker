package com.mobiixi.hkitraintracker.ui

import androidx.fragment.app.Fragment
import butterknife.Unbinder

abstract class BaseFragment: Fragment() {
    lateinit var unbinder: Unbinder

    override fun onDestroyView() {
        super.onDestroyView()
        if(unbinder != null) {
            unbinder.unbind()
        }
    }
}