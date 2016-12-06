package com.example.deam.aristocrat.fragments;



import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.view.ViewGroup.LayoutParams;
import android.widget.RelativeLayout;

import com.example.deam.aristocrat.R;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import static android.R.attr.bitmap;
import static android.R.attr.data;
import static android.app.Activity.RESULT_OK;
import static com.example.deam.aristocrat.R.id.image;

/**
 * A simple {@link Fragment} subclass.
 */
public class UploadPhotoFragment extends Fragment {
    private static int RESULT_LOAD_IMAGE = 1;
    View customView;
    View view;
    private PopupWindow mPopupWindow;
    RelativeLayout mRelativeLayout;
    public UploadPhotoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

            view = inflater.inflate(R.layout.fragment_upload_photo, container, false);
         mRelativeLayout = (RelativeLayout) view.findViewById(R.id.rl);

        customView = inflater.inflate(R.layout.custom_layout,null);
        ImageView upload = (ImageView) view.findViewById(R.id.UploadimageView);
        upload.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(final View view) {
                mPopupWindow = new PopupWindow(
                        customView,
                        LayoutParams.WRAP_CONTENT,
                        LayoutParams.WRAP_CONTENT
                );

                ImageButton btnDismiss = (ImageButton)customView.findViewById(R.id.GaleryimageButton4);
                btnDismiss.setOnClickListener(new Button.OnClickListener(){




                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Intent.ACTION_PICK,
                                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(i, RESULT_LOAD_IMAGE);

                        mPopupWindow.dismiss();
                    }

                });

                // Set an elevation value for popup window
                // Call requires API level 21
                if(Build.VERSION.SDK_INT>=21){
                    mPopupWindow.setElevation(5.0f);
                }
                mPopupWindow.showAtLocation(mRelativeLayout, Gravity.CENTER,0,0);
            }
        });


        Button button = (Button) view.findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                // do something
            }
        });
        return view;
    }

    @Override
     public void onActivityResult(int requestCode, int resultCode, Intent data) {
      /*  if (requestCode == RESULT_LOAD_IMAGE
                && resultCode == Activity.RESULT_OK) {
            String path = getPathFromCameraData(data, this.getActivity());
            if (path != null) {
                profile_img.setImageURI(Uri.parse(path));
            }
        }*/
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK
                && null != data) {

            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            Cursor cursor = customView.getContext().getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();


             Bitmap bitmap = BitmapFactory.decodeFile(picturePath);
            ImageView uploadImageBtn = (ImageView) view.findViewById(R.id.UploadimageView);
                uploadImageBtn.setImageBitmap(bitmap);

            /*if (bitmap != null) {
                ImageView rotate = (ImageView) customView.findViewById(R.id.rotate);
                rotate.setVisibility(View.VISIBLE);
            }*/

        } else {


            switch (resultCode) {
                case 0:

                    break;
                case -1:

                    break;

            }

        }

    }


}
