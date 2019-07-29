package fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;


public final class FragmentManagerUtils {

    public static boolean sDisableFragmentAnimations = false;

    private static void runOnResume(Runnable runnable) {
        runnable.run();
    }

    private static void replaceFragment(
            final FragmentManager fragmentManager, final Fragment fragment, final String tag,
            final int containerId, final boolean addToBackStack, final boolean animate) {
        runOnResume(() -> {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if (addToBackStack) {
                transaction.addToBackStack(tag);
            }
            if (animate) {
                transaction.setCustomAnimations(R.anim.push_left_in, R.anim.push_left_out, R.anim.push_right_in, R.anim.push_right_out);
            }
            transaction.replace(containerId, fragment, tag).commit();
        });
    }





    public static void replaceFragmentInRoot(FragmentManager fragmentManager, Fragment fragment, String tag, boolean addToBackStack, boolean animate) {
        replaceFragment(fragmentManager, fragment, tag, R.id.rootLayout, addToBackStack, animate);
    }

    public static void clearBackStack(final FragmentManager fragmentManager) {
        runOnResume(() -> {
            sDisableFragmentAnimations = true;
            fragmentManager.popBackStackImmediate(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
            sDisableFragmentAnimations = false;
        });
    }

    public static void popBackStack(final FragmentManager fragmentManager, int id, int flags) {
        runOnResume(() ->
                fragmentManager.popBackStack(id, flags)
        );
    }

    public static void popBackStack(final FragmentManager fragmentManager, String tag, int flags) {
        runOnResume(() ->
                fragmentManager.popBackStack(tag, flags)
        );
    }

    public static void addFragmentInRoot(FragmentManager fragmentManager, Fragment fragment, String tag, boolean addToBackStack, boolean animate) {
        addFragment(fragmentManager, fragment, tag, R.id.rootLayout, addToBackStack, animate);
    }


    private static void addFragment(
            final FragmentManager fragmentManager, final Fragment fragment, final String tag,
            final int containerId, final boolean addToBackStack, final boolean animate) {
        runOnResume(() -> {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            if (addToBackStack) {
                transaction.addToBackStack(tag);
            }
            if (animate) {
                transaction.setCustomAnimations(R.anim.push_left_in, R.anim.push_left_out, R.anim.push_right_in, R.anim.push_right_out);
            }
            transaction.add(containerId, fragment, tag).commit();
        });
    }


}

