package com.richkart.android.base_fragment

import android.content.Context
import com.richkart.android.DrawerActivity
import com.richkart.android.interfaces.IFragmentListener
import com.richkart.android.fragment.ToolbarBaseFragment

abstract class BaseFragment : ToolbarBaseFragment() {

    private var mListener: IFragmentListener? = null

    override fun onResume() {
        super.onResume()
        if (this.mListener != null) {
            this.mListener!!.setScreenTitle(getActionbarTitle())
            this.mListener!!.isCartIconVisible(isCartIconVisible())
            this.mListener!!.isSearchIconVisible(isSearchIconVisible())

        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        mListener = context as DrawerActivity?
    }

    protected open fun isSearchIconVisible(): Boolean {
        return true
    }

    protected open fun isCartIconVisible(): Boolean {
        return true
    }

    protected open fun getActionbarTitle(): String? {
        return ""
    }


}