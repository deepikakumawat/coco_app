package com.ws.design.coco_ecommerce_ui_kit.profile;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ContentUris;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.ChangePasswordActivity;
import com.ws.design.coco_ecommerce_ui_kit.address.AddAddressActivity;
import com.ws.design.coco_ecommerce_ui_kit.address.AddressListActivity;
import com.ws.design.coco_ecommerce_ui_kit.my_order.MyOrderActivity;
import com.ws.design.coco_ecommerce_ui_kit.shared_preference.CocoPreferences;
import com.ws.design.coco_ecommerce_ui_kit.utility.Constant;
import com.ws.design.coco_ecommerce_ui_kit.utility.MarshMallowPermissions;
import com.ws.design.coco_ecommerce_ui_kit.utility.Util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.ws.design.coco_ecommerce_ui_kit.utility.Util.showCenteredToast;

public class MyAccountActivity extends AppCompatActivity implements View.OnClickListener, UpdateView {

    //    private TextView title, edit, test, edit_txt;
    private TextView txtLogout;
    private TextView txtUserName;
    //    private TextView txtUserEmail;
    private TextView txtMyOrder;
    private TextView txtChangePassword;
    private TextView txtAddAddress;
    private TextView txtYourAddress;
    private TextView txtEditProfile;
    private ImageView imgProfileImage;
    private Dialog addProfileImage;
    private MarshMallowPermissions marshMallowPermissions = new MarshMallowPermissions(this);
    private ScrollView svParent;
    private Uri fileUri = null;
    private String imagePath;
    private String fileName;
    private TextView txtCamera;
    private TextView txtGallery;
    private TextView txtCancel;
    private UpdateProfilePresenter updateProfilePresenter;
//    private String mCurrentPhotoPath;

//    private RelativeLayout ryParent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        updateProfilePresenter = new UpdateProfilePresenter(this);


        svParent = findViewById(R.id.svParent);
        imgProfileImage = findViewById(R.id.imgProfileImage);
        txtLogout = findViewById(R.id.txtLogout);
        txtChangePassword = findViewById(R.id.txtChangePassword);
        txtAddAddress = findViewById(R.id.txtAddAddress);
        txtUserName = findViewById(R.id.txtUserName);
        txtYourAddress = findViewById(R.id.txtYourAddress);
        txtEditProfile = findViewById(R.id.txtEditProfile);
        txtMyOrder = findViewById(R.id.txtMyOrder);
        txtLogout.setOnClickListener(this);
        txtMyOrder.setOnClickListener(this);
        txtChangePassword.setOnClickListener(this);
        txtAddAddress.setOnClickListener(this);
        txtYourAddress.setOnClickListener(this);
        txtEditProfile.setOnClickListener(this);
        imgProfileImage.setOnClickListener(this);

        txtUserName.setText(CocoPreferences.getFirstName() + " " + CocoPreferences.getLastName());
        String picUrl = CocoPreferences.getProfilePic();
        Glide.with(this).load(CocoPreferences.getProfilePic()).placeholder(R.drawable.user_dp).into(imgProfileImage);

    }

    @Override
    public void onClick(View view) {
        try {
            int vId = view.getId();
            switch (vId) {
                case R.id.imgBack:
                    finish();
                    break;
                case R.id.txtEditProfile:
                    startActivity(new Intent(MyAccountActivity.this, ProfileActivity.class));

                    break;
                case R.id.txtChangePassword:
                    startActivity(new Intent(MyAccountActivity.this, ChangePasswordActivity.class));

                    break;
                case R.id.txtAddAddress:
                    startActivity(new Intent(MyAccountActivity.this, AddAddressActivity.class));

                    break;
                case R.id.txtYourAddress:
                    startActivity(new Intent(MyAccountActivity.this, AddressListActivity.class));

                    break;

                case R.id.txtLogout:
                    logout();
                    break;
                case R.id.txtMyOrder:
                    startActivity(new Intent(MyAccountActivity.this, MyOrderActivity.class));
                    break;
                case R.id.imgProfileImage:
                    setProfileImage();
                    break;
                case R.id.txtCamera:
                    openCameraOrGallery(Constant.PICK_FROM_CAMERA);
                    break;
                case R.id.txtGallery:
                    openCameraOrGallery(Constant.PICK_FROM_GALLERY);

                    break;
                case R.id.txtCancel:
                    openCameraOrGallery(Constant.CANCEL_PICK_IMAGE);

                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void logout() {
        try {
            AlertDialog.Builder builder;
            builder = new AlertDialog.Builder(this);
            builder.setMessage(getString(R.string.do_you_want_to_logout))
                    .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                          /*  CocoPreferences.removeValueForKey(LoginResponse.KEY_USERID);
                            CocoPreferences.removeValueForKey(LoginResponse.KEY_USEREMAIL);
                            CocoPreferences.removeValueForKey(LoginResponse.KEY_USERPHONE);
                            CocoPreferences.removeValueForKey(LoginResponse.KEY_FIRST_NAME);
                            CocoPreferences.removeValueForKey(LoginResponse.KEY_LAST_NAME);*/


                            CocoPreferences.removeValueForKey("UserID");
                            CocoPreferences.removeValueForKey("UserEmail");
                            CocoPreferences.removeValueForKey("UserPhone");
                            CocoPreferences.removeValueForKey("FirstName");
                            CocoPreferences.removeValueForKey("LastName");


                            txtUserName.setText("");
//                            txtUserEmail.setText("");

//                            Util.showCenteredToast(ryParent,MyAccountActivity.this, "Logout Successfully!", Constant.API_SUCCESS);


                            Intent data = new Intent();
                            setResult(Activity.RESULT_OK, data);
                            finish();



                         /*   android.app.Fragment f = getFragmentManager().findFragmentById(R.id.frame_container);
                            if (f instanceof FragmentProfile) {
                                getFragmentManager().popBackStack();
                            }
*/
                        }
                    })
                    .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void setProfileImage() {
        try {
            addProfileImage = new Dialog(this);
            addProfileImage.requestWindowFeature(Window.FEATURE_NO_TITLE);
            addProfileImage.setContentView(R.layout.dialog_pic_profile_image);

            //get widgets
            txtCamera = addProfileImage.findViewById(R.id.txtCamera);
            txtGallery = addProfileImage.findViewById(R.id.txtGallery);
            txtCancel = addProfileImage.findViewById(R.id.txtCancel);

            txtCamera.setOnClickListener(this);
            txtGallery.setOnClickListener(this);
            txtCancel.setOnClickListener(this);


            Window window = addProfileImage.getWindow();
            window.setGravity(Gravity.CENTER);
            window.setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            window.setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            addProfileImage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void openCameraOrGallery(int galleryOrCamera) {
        if (addProfileImage != null) {
            addProfileImage.dismiss();
        }
        if (galleryOrCamera == Constant.PICK_FROM_GALLERY) {
            if (!marshMallowPermissions.checkPermissionForExternalStorage()) {
                marshMallowPermissions.requestPermissionForExternalStorage(svParent);
            } else {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_PICK);
                this.startActivityForResult(intent, Constant.PICK_FROM_GALLERY);
            }
        } else if (galleryOrCamera == Constant.PICK_FROM_CAMERA) {
            Intent intent = new Intent();
            if (!marshMallowPermissions.checkPermissionForCamera()) {
                marshMallowPermissions.requestPermissionForCamera(svParent);
            } else {
               intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
                File directory = Util.getPhotoDirectory(this);



                fileUri = null;
                String imageName = "Image_";

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    fileUri = FileProvider.getUriForFile(MyAccountActivity.this, MyAccountActivity.this.getApplicationContext().getPackageName() + ".provider", new File(directory.getPath(), imageName + new SimpleDateFormat("yyyyMMdd_HHmmssSSS").format(new Date()) + ".jpg"));
                } else {
                    fileUri = Uri.fromFile(new File(directory.getPath(), imageName + new SimpleDateFormat("yyyyMMdd_HHmmssSSS").format(new Date()) + ".jpg"));
                }

                intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
                startActivityForResult(intent, Constant.PICK_FROM_CAMERA);


//              captureImage();

            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {

                try {
                    imagePath = String.valueOf(fileUri);
                    fileName = imagePath.substring(imagePath.lastIndexOf("/") + 1, imagePath.length());
                    String path = Environment.getExternalStorageDirectory() + fileUri.getPath().replaceFirst("/external_files", "");
                    File imgFile = new File(path);
                    imgProfileImage.setImageURI(Uri.fromFile(imgFile));
                    callAPI(imgFile, fileName);

                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        } else if (requestCode == 2) {
            if (resultCode == Activity.RESULT_OK) {
                Uri extras = data.getData();
                if (extras != null) {
                    Bitmap bitmap;
                    try {
                        String img_path = "";
                        img_path = getPath(MyAccountActivity.this, extras);
                        assert img_path != null;
                        fileName = img_path.substring(img_path.lastIndexOf("/") + 1, img_path.length());
                        File imgFile = new File(img_path);
                        if (imgFile.exists()) {
                            BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                            bitmap = BitmapFactory.decodeFile(img_path, bmOptions);
                            if (bitmap != null) {
                                imgProfileImage.setImageBitmap(bitmap);
                                Uri fileUri = Uri.fromFile(new File(img_path));
                                imagePath = String.valueOf(fileUri);

                                callAPI(imgFile, fileName);


                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    Bundle bundle = data.getExtras();
                    Bitmap image = bundle.getParcelable("data");
                    String img_path = "";
                    img_path = getPath(Uri.parse(MediaStore.Images.Media.insertImage(MyAccountActivity.this.getContentResolver(), image, "profile_image" + ".jpg", "Temp image for profile")), MyAccountActivity.this);
                    Uri fileUri = Uri.fromFile(new File(img_path));
                    imagePath = String.valueOf(fileUri);
                    fileName = imagePath.substring(imagePath.lastIndexOf("/") + 1, imagePath.length());
                    if (!(image == null)) {
                        imgProfileImage.setImageBitmap(image);

                        callAPI(new File(img_path), fileName);


                    }
                }

            }
        }
    }

    public String getPath(Context context, Uri uri) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                String uri_authority = uri.getAuthority();
                String selection = "_id=?";
                if (uri_authority.equals("com.android.providers.media.documents")) {
                    String docId = DocumentsContract.getDocumentId(uri);
                    String[] split = docId.split(":");
                    String type = split[0];
                    Uri contentUri = null;
                    if ("image".equals(type)) {
                        contentUri = MediaStore.Images.Media.EXTERNAL_CONTENT_URI;
                    } else if ("video".equals(type)) {
                        contentUri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                    } else if ("audio".equals(type)) {
                        contentUri = MediaStore.Audio.Media.EXTERNAL_CONTENT_URI;
                    }
                    String[] selectionArgs = new String[]{split[1]};
                    return getDataColumn(context, contentUri, selection, selectionArgs);
                } else if (uri_authority.equals("media")) {
                    String docId = uri.getLastPathSegment();
                    String[] selectionArgs = new String[]{docId};
                    return getDataColumn(context, uri, selection, selectionArgs);
                } else if (uri_authority.equals("com.android.providers.downloads.documents")) {

                    String id = DocumentsContract.getDocumentId(uri);
                    Uri contentUri = ContentUris.withAppendedId(Uri.parse("content://downloads/public_downloads"), Long.valueOf(id));
                    return getDataColumn(context, contentUri, null, null);
                } else if ("com.google.android.apps.docs.storage".equals(uri_authority) || uri_authority.equals("com.dropbox.android.FileCache")
                        || uri_authority.equals("com.google.android.apps.docs.storage.legacy")
                        || uri_authority.equals("com.microsoft.skydrive.content.external")
                        || uri_authority.equals("com.google.android.apps.photos.contentprovider")
                        || uri_authority.equals("cn.wps.moffice_eng.fileprovider")) {
                    Cursor returnCursor = context.getContentResolver().query(uri, null, null, null, null);
                    returnCursor.moveToFirst();
                    String file_name = returnCursor.getString(returnCursor.getColumnIndex(OpenableColumns.DISPLAY_NAME));
                    long file_size = returnCursor.getLong(returnCursor.getColumnIndex(OpenableColumns.SIZE));
                    if (!(file_size > Constant.ATTACHMENT_FILE_SIZE)) {
                        FileInputStream in = (FileInputStream) context.getContentResolver().openInputStream(uri);
                        String path = Environment.getExternalStorageDirectory() + File.separator + Constant.APP_FOLDER_NAME + File.separator + Constant.ATTACHMENTS_FOLDER_NAME;
                        File file = new File(path);
                        if (!file.exists()) {
                            file.mkdirs();
                        }
                        FileOutputStream out = new FileOutputStream(new File(path, file_name));
                        FileChannel inChannel = in.getChannel();
                        FileChannel outChannel = out.getChannel();
                        inChannel.transferTo(0, inChannel.size(), outChannel);
                        in.close();
                        out.close();
                        return path + File.separator + file_name;
                    } else {
                        Util.showCenteredToast(svParent, MyAccountActivity.this, "Can't Attach file more than 10mb in size", "");
                    }

                } else {
                    return uri.getPath();
                }
            } else if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.JELLY_BEAN_MR2 && Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
                String[] proj = {MediaStore.Images.Media.DATA};
                String result = null;
                CursorLoader cursorLoader = new CursorLoader(context, uri, proj, null, null, null);
                Cursor cursor = cursorLoader.loadInBackground();
                if (cursor != null) {
                    int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                    cursor.moveToFirst();
                    result = cursor.getString(column_index);
                }
                return result;
            }
        } catch (Exception e) {
            Util.dismissProDialog();
            e.printStackTrace();
        }
        return null;
    }

    private String getDataColumn(Context context, Uri uri, String selection, String[] selectionArgs) {
        Cursor cursor = null;
        String column = "_data";
        String[] projection = {column};
        try {
            cursor = context.getContentResolver().query(uri, projection, selection, selectionArgs, null);
            if (cursor != null && cursor.moveToFirst()) {
                int column_index = cursor.getColumnIndexOrThrow(column);
                return cursor.getString(column_index);
            }
        } finally {
            if (cursor != null)
                cursor.close();
        }
        return null;
    }

    /**
     * get path of picked image from camera or gallery
     *
     * @param uri
     * @param context
     * @return
     */
    public static String getPath(Uri uri, Context context) {
        if (uri != null) {
            String[] projection = {MediaStore.Images.Media.DATA};
            @SuppressWarnings("deprecation")
            Cursor cursor = ((Activity) context).managedQuery(uri, projection, null, null, null);
            // IN CASE OF SELECTING IMAGE FROM FILE MANAGER CUSSER IS NULL
            if (cursor != null) {
                int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
                cursor.moveToFirst();
                return cursor.getString(column_index);
            } else {
                return null;
            }
        } else {
            return null;
        }
    }

    @Override
    public void showWait() {
        Util.showProDialog(this);
    }

    @Override
    public void removeWait() {
        Util.dismissProDialog();
    }

    @Override
    public void onFailure(String appErrorMessage) {
        showCenteredToast(svParent, this, appErrorMessage, "");
    }


    @Override
    public void onUpdateProfileSuccess(UpdateProfileResponse updateProfileResponse) {
// do nothing
    }

    @Override
    public void changeProfileImages(ChangeProfileImageResponse changeProfileImageResponse) {
        try {
            if (!TextUtils.isEmpty(changeProfileImageResponse.getmStatus()) && ("1".equalsIgnoreCase(changeProfileImageResponse.getmStatus()))) {

                if (changeProfileImageResponse.getmData() != null) {
                    showCenteredToast(svParent, this, getString(R.string.profile_updated_succesfully), Constant.API_SUCCESS);
                    CocoPreferences.setProfilePic(changeProfileImageResponse.getmData().getmProfilePic());
                    CocoPreferences.savePreferencese();
                }


            } else {
//                showCenteredToast(svParent, this, changeProfileImageResponse.getmMessage(), "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void callAPI(File file, String fileName) {
        updateProfilePresenter.changeProfilePic(file, fileName);

    }



}
