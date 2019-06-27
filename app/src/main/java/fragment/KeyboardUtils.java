package fragment;

import android.app.Activity;
import android.content.Context;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public class KeyboardUtils {

    /**
     * Hides the soft input window from the context of the Activity that is currently accepting input.
     * @param activity Current Activity.
     */
    public static void hideKeyboard(Activity activity) {
        View currentFocus = activity.getWindow().getCurrentFocus();
        if (currentFocus == null) {
            currentFocus = activity.findViewById(android.R.id.content);
        }
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
    }
}

