package com.ws.design.coco_ecommerce_ui_kit;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;

import com.ws.design.coco_ecommerce_ui_kit.address.AddressListActivity;
import com.ws.design.coco_ecommerce_ui_kit.checkout.SuccessFragment;
import com.ws.design.coco_ecommerce_ui_kit.interfaces.IFragmentListener;
import com.ws.design.coco_ecommerce_ui_kit.home.HomeFragment;
import com.ws.design.coco_ecommerce_ui_kit.legal_policies.LegalPoliciesFragment;
import com.ws.design.coco_ecommerce_ui_kit.login.LoginActivity;
import com.ws.design.coco_ecommerce_ui_kit.my_cart.CartFragment;
import com.ws.design.coco_ecommerce_ui_kit.my_order.MyOrderActivity;
import com.ws.design.coco_ecommerce_ui_kit.my_wishlist.MyWishlistFragment;
import com.ws.design.coco_ecommerce_ui_kit.product_by_category.ProductListByCategoryFragment;
import com.ws.design.coco_ecommerce_ui_kit.shared_preference.CocoPreferences;
import com.ws.design.coco_ecommerce_ui_kit.signup.SignupActivity;
import com.ws.design.coco_ecommerce_ui_kit.utility.Constant;
import com.ws.design.coco_ecommerce_ui_kit.utility.Util;

import java.util.ArrayList;

import Adapter.RecycleAdapteNavigation;
import Model.NavigationModelClass;
import fragment.CustomItemClickListener;
import fragment.FragmentManagerUtils;

public class DrawerActivity extends AppCompatActivity implements IFragmentListener, NavigationView.OnNavigationItemSelectedListener, View.OnClickListener {

    private ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;

    private DrawerLayout drawer;
    private Toolbar toolbar;

    private ArrayList<NavigationModelClass> navigationModelClasses;

    private RecyclerView recyclerView;
    private RecycleAdapteNavigation mAdapter;
    long back_pressed = 0;


    private String title[] = {"Home", "Cart", "My Orders", "Categories", "My Wishlist", "My Account", "Trandings",
            "Address", "Help", "Contact Us", "Legal Policies"};

    private String titleWithLogout[] = {"Home", "Cart", "My Orders", "Categories", "My Wishlist", "My Account", "Trandings",
            "Address", "Help", "Contact Us", "Legal Policies", "Logout"};

    private TextView txtUserEmail;
    private TextView txtUserName;
    private TextView txtSignup;
    private TextView txtLogin;
    private LinearLayout lyLoginSignup;
    private NavigationModelClass beanClassForRecyclerView_contacts;
    private static final int MYACCOUNT_ACTION = 101;
    String[] PERMISSIONS = {Manifest.permission.CALL_PHONE};
    private final int PERMISSION_ALL = 11;
    private TextView txtTitle;
    private ImageView imgSearch;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }

        init();


    }

    private boolean hasPermissions(Context context, String... permissions) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }


    private void init() {
        txtTitle = findViewById(R.id.txtTitle);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        txtUserName = findViewById(R.id.txtUserName);
        txtUserEmail = findViewById(R.id.txtUserEmail);
        txtLogin = findViewById(R.id.txtLogin);
        txtSignup = findViewById(R.id.txtSignup);
        lyLoginSignup = findViewById(R.id.lyLoginSignup);
        txtLogin.setOnClickListener(this);
        txtSignup.setOnClickListener(this);
        setToolbar();

        setEmailName();

        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);

        navigationModelClasses = new ArrayList<>();


        if (TextUtils.isEmpty(CocoPreferences.getUserId())) {
            for (int i = 0; i < title.length; i++) {
                beanClassForRecyclerView_contacts = new NavigationModelClass(title[i]);

                navigationModelClasses.add(beanClassForRecyclerView_contacts);
            }
        } else {
            for (int i = 0; i < titleWithLogout.length; i++) {
                beanClassForRecyclerView_contacts = new NavigationModelClass(titleWithLogout[i]);

                navigationModelClasses.add(beanClassForRecyclerView_contacts);
            }
        }


        mAdapter = new RecycleAdapteNavigation(getApplicationContext(), navigationModelClasses, new CustomItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                if (position == 0) {
                    setScreenTitle(getString(R.string.home));
                    FragmentManagerUtils.replaceFragmentInRoot(getSupportFragmentManager(), new HomeFragment(), "HomeFragment", false, false);

                } else if (position == 1) {
                    if (!TextUtils.isEmpty(CocoPreferences.getUserId())) {
                        setScreenTitle(getString(R.string.my_cart));
                        FragmentManagerUtils.replaceFragmentInRoot(getSupportFragmentManager(), new CartFragment(), "CartFragment", true, false);
                    } else {
                        startActivity(new Intent(DrawerActivity.this, LoginActivity.class));
                    }
                } else if (position == 2) {
                    startActivity(new Intent(DrawerActivity.this, MyOrderActivity.class));
                } else if (position == 3) {
                    setScreenTitle(getString(R.string.categories));
                    FragmentManagerUtils.replaceFragmentInRoot(getSupportFragmentManager(), new CategoryActivity(), null, false, false);
                } else if (position == 4) {

                    if (!TextUtils.isEmpty(CocoPreferences.getUserId())) {
                        setScreenTitle(getString(R.string.my_wishlist));
                        FragmentManagerUtils.replaceFragmentInRoot(getSupportFragmentManager(), new MyWishlistFragment(), "MyWishlistFragment", true, false);
                    } else {
                        startActivity(new Intent(DrawerActivity.this, LoginActivity.class));
                    }


                } else if (position == 5) {

                    Intent intent = new Intent(DrawerActivity.this, MyAccountActivity.class);
                    startActivityForResult(intent, MYACCOUNT_ACTION);

                } else if (position == 6) {
                    setScreenTitle(getString(R.string.trandings));
                    FragmentManagerUtils.replaceFragmentInRoot(getSupportFragmentManager(), new ProductListByCategoryFragment(), "ProductByCategoryFragment", false, false);

                } else if (position == 7) {
                    if (!TextUtils.isEmpty(CocoPreferences.getUserId())) {
                        startActivity(new Intent(DrawerActivity.this, AddressListActivity.class));
                    } else {
                        startActivity(new Intent(DrawerActivity.this, LoginActivity.class));
                    }

                } else if (position == 8) {


                } else if (position == 9) {


                } else if (position == 10) {
                    setScreenTitle(getString(R.string.legal_policies));
                    FragmentManagerUtils.replaceFragmentInRoot(getSupportFragmentManager(), new LegalPoliciesFragment(), "LegalPoliciesFragment", true, false);


                } else if (position == 11) {

                    logout();

                }

                drawer.closeDrawer(GravityCompat.START);
                // do what ever you want to do with it
            }
        });

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(DrawerActivity.this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);


        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
        actionBarDrawerToggle.setDrawerIndicatorEnabled(false);

        invalidateOptionsMenu();

        setScreenTitle(getString(R.string.home));
//        FragmentManagerUtils.replaceFragmentInRoot(getSupportFragmentManager(), new HomeFragment(), "HomeFragment", false, false);
        FragmentManagerUtils.addFragmentInRoot(getSupportFragmentManager(), new HomeFragment(), "HomeFragment", false, false);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // toggle nav drawer on selecting action bar app icon/title
        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * When using the ActionBarDrawerToggle, you must call it during
     * onPostCreate() and onConfigurationChanged()...
     */

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        actionBarDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Pass any configuration change to the drawer toggls
        actionBarDrawerToggle.onConfigurationChanged(newConfig);
    }


    private Fragment getCurrentFragmentInRootLayout() {
        return getSupportFragmentManager().findFragmentById(R.id.rootLayout);
    }

    private void setToolbar() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(false);
        actionBar.setTitle("");

        toolbar.findViewById(R.id.navigation_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e("Click", "keryu");

                if (drawer.isDrawerOpen(navigationView)) {
                    drawer.closeDrawer(navigationView);
                } else {
                    drawer.openDrawer(navigationView);

                    Log.e("abc", "abc");
                }
            }
        });

        imgSearch = findViewById(R.id.imgSearch);


        toolbar.findViewById(R.id.imgSearch).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DrawerActivity.this, CocoSearch1.class));
            }
        });

        toolbar.findViewById(R.id.btn_cart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(CocoPreferences.getUserId())) {
                    setScreenTitle(getString(R.string.my_cart));
                    FragmentManagerUtils.replaceFragmentInRoot(getSupportFragmentManager(), new CartFragment(), "CartFragment", true, false);
                } else {
                    startActivity(new Intent(DrawerActivity.this, LoginActivity.class));
                }
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {


        drawer.closeDrawer(GravityCompat.START);
        return true;


    }

    @Override
    public void onClick(View view) {
        Intent intent;
        try {
            int vId = view.getId();
            switch (vId) {
                case R.id.txtLogin:

                    drawer.closeDrawer(GravityCompat.START);

                    intent = new Intent(DrawerActivity.this, LoginActivity.class);
                    startActivity(intent);

                    break;

                case R.id.txtSignup:

                    drawer.closeDrawer(GravityCompat.START);

                    intent = new Intent(DrawerActivity.this, SignupActivity.class);
                    startActivity(intent);

                    break;


                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void setScreenTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            txtTitle.setText(title);
        }
    }

    @Override
    public void isCartIconVisible(boolean isCartIconVisible) {
        toolbar.findViewById(R.id.btn_cart).setVisibility(isCartIconVisible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void isSearchIconVisible(boolean isSearchIconVisible) {

        imgSearch.setVisibility(isSearchIconVisible ? View.VISIBLE : View.GONE);
    }

    class MyOnClickListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            int itemPosition = recyclerView.indexOfChild(v);

        }
    }


    public void setEmailName() {
        if (!TextUtils.isEmpty(CocoPreferences.getFirstName()) && !TextUtils.isEmpty(CocoPreferences.getLastName())) {
            txtUserName.setVisibility(View.VISIBLE);
            txtUserName.setText(CocoPreferences.getFirstName() + " " + CocoPreferences.getLastName());
        } else if (!TextUtils.isEmpty(CocoPreferences.getFirstName())) {
            txtUserName.setVisibility(View.VISIBLE);
            txtUserName.setText(CocoPreferences.getFirstName());
        } else {
            txtUserName.setVisibility(View.GONE);

        }


        if (!TextUtils.isEmpty(CocoPreferences.getUserEmail())) {
            txtUserEmail.setVisibility(View.VISIBLE);
            txtUserEmail.setText(CocoPreferences.getUserEmail());
        } else {
            txtUserEmail.setVisibility(View.GONE);

        }


        if (TextUtils.isEmpty(CocoPreferences.getUserId())) {
            txtUserName.setVisibility(View.GONE);
            txtUserEmail.setVisibility(View.GONE);
            lyLoginSignup.setVisibility(View.VISIBLE);
        } else {
            txtUserName.setVisibility(View.VISIBLE);
            txtUserEmail.setVisibility(View.VISIBLE);
            lyLoginSignup.setVisibility(View.GONE);


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


                            txtUserName.setVisibility(View.GONE);
                            txtUserEmail.setVisibility(View.GONE);
                            lyLoginSignup.setVisibility(View.VISIBLE);

                            navigationModelClasses.remove(11);
                            mAdapter.notifyDataSetChanged();

                            Util.showCenteredToast(drawer, DrawerActivity.this, "Logout Successfully!", Constant.API_SUCCESS);

                            init();


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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == MYACCOUNT_ACTION) {
            if (resultCode == Activity.RESULT_OK) {
                setEmailName();
                navigationModelClasses.remove(11);
                mAdapter.notifyDataSetChanged();
            }
        }
    }


    @Override
    public void onBackPressed() {


        Fragment f = getCurrentFragmentInRootLayout();
        if (f instanceof SuccessFragment) {
            FragmentManagerUtils.makeRootFragment(getSupportFragmentManager(), new HomeFragment(), this, "HomeFragment");
        } else {
            if (drawer.isDrawerOpen(GravityCompat.START)) {

                drawer.closeDrawer(Gravity.LEFT); //OPEN Nav Drawer!

            } else {


                if (getFragmentManager().getBackStackEntryCount() > 0) {


                    getFragmentManager().popBackStack();
                } else {
                    super.onBackPressed();
                }
            }
        }


    }


}
