package fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;


/**
 * Created by wolfsoft4 on 13/12/18.
 */

public class Viewpager2 extends Fragment {

    ImageView image;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.viewpager2,container,false);

//        ImageView resultImage = (ImageView)view.findViewById(R.id.mobile);
//        Bitmap resultBmp = BlurBuilder.blur(getActivity(), BitmapFactory.decodeResource(getResources(), R.drawable.img));
//        resultImage.setImageBitmap(resultBmp);


        return view;

    }
}
