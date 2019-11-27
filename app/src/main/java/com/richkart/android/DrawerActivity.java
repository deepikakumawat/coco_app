package com.richkart.android;

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
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.richkart.android.address.LoginAlertOnAddressActivity;
import com.richkart.android.deals.seller.DealsProductFragment;
import com.richkart.android.legal_policies.LegalPoliciesWebPages;
import com.richkart.android.login.LoginActivity;
import com.richkart.android.my_cart.LoginAlertOnCartActivity;
import com.richkart.android.my_wishlist.LoginAlertOnWishlistActivity;
import com.richkart.android.profile.LoginAlertOnMyAccountActivity;
import com.razorpay.PaymentResultListener;
import com.richkart.android.address.AddressListActivity;
import com.richkart.android.departments.DepartmentFragment;
import com.richkart.android.checkout.CheckoutFragment;
import com.richkart.android.checkout.SuccessFragment;
import com.richkart.android.interfaces.IFragmentListener;
import com.richkart.android.home.HomeFragment;
import com.richkart.android.legal_policies.LegalPoliciesFragment;
import com.richkart.android.my_cart.CartFragment;
import com.richkart.android.my_order.LoginAlertOnMyOrderActivity;
import com.richkart.android.my_order.MyOrderFragment;
import com.richkart.android.my_wishlist.MyWishlistFragment;
import com.richkart.android.profile.MyAccountFragment;
import com.richkart.android.search.SearchFragment;
import com.richkart.android.shared_preference.CocoPreferences;
import com.richkart.android.shipping.ShippingFragment;
import com.richkart.android.signup.SignupActivity;
import com.richkart.android.utility.Constant;
import com.richkart.android.utility.Util;

import java.util.ArrayList;

import com.richkart.android.Model.NavigationModelClass;
import com.richkart.android.fragment.CustomItemClickListener;
import com.richkart.android.fragment.FragmentManagerUtils;

public class DrawerActivity extends AppCompatActivity implements IFragmentListener, NavigationView.OnNavigationItemSelectedListener, View.OnClickListener, PaymentResultListener {

    private ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;

    private DrawerLayout drawer;
    private Toolbar toolbar;

    private ArrayList<NavigationModelClass> navigationModelClasses;

    private RecyclerView recyclerView;
    private RecycleAdapteNavigation mAdapter;
    long back_pressed = 0;


    private String title[] = {"Home", "Department", "Today's Deal", "Cart", "My Order", "My Wishlist", "My Account",
            "Address", "Help", "Sell On Richkart", "Legal Policies", "Change Country"};


    private String titleWithLogout[] = {"Home", "Department", "Today's Deal", "Cart", "My Order", "My Wishlist", "My Account",
            "Address", "Help", "Sell On Richkart", "Legal Policies","Change Country", "Logout"};


    private TextView txtUserEmail;
    private TextView txtUserName;
    private TextView txtSignup;
    private TextView txtLogin;
    private LinearLayout lyLoginSignup;
    private NavigationModelClass beanClassForRecyclerView_contacts;
    private static final int MYACCOUNT_ACTION = 101;
    String[] PERMISSIONS = {Manifest.permission.CALL_PHONE,
            Manifest.permission.CAMERA,
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private final int PERMISSION_ALL = 11;
    private TextView txtTitle;
    private ImageView imgSearch;
    private ImageView imgProfileImage;
    private ImageView imgRichkarLogo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);

        if (!hasPermissions(this, PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PERMISSIONS, PERMISSION_ALL);
        }


        init();
        setMyAccountTheme();


    }

    public void setMyAccountTheme() {
        if (CocoPreferences.isNightMode()) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }


        Fragment fragment = getCurrentFragmentInRootLayout();
        if (fragment instanceof MyAccountFragment) {
//            darkMode();
            FragmentManagerUtils.replaceFragmentInRoot(getSupportFragmentManager(), new MyAccountFragment(), "MyAccountFragment", false, false);

        }


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
        imgRichkarLogo = findViewById(R.id.imgRichkarLogo);
        txtTitle = findViewById(R.id.txtTitle);
        drawer = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.navigation_view);
        txtUserName = findViewById(R.id.txtUserName);
        txtUserEmail = findViewById(R.id.txtUserEmail);
        txtLogin = findViewById(R.id.txtLogin);
        txtSignup = findViewById(R.id.txtSignup);
        lyLoginSignup = findViewById(R.id.lyLoginSignup);
        imgProfileImage = findViewById(R.id.imgProfileImage);
        txtLogin.setOnClickListener(this);
        txtSignup.setOnClickListener(this);
        imgRichkarLogo.setOnClickListener(this);
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
                    FragmentManagerUtils.replaceFragmentInRoot(getSupportFragmentManager(), new HomeFragment(), "HomeFragment", false, false);

                } else if (position == 1) {

                    FragmentManagerUtils.replaceFragmentInRoot(getSupportFragmentManager(), new DepartmentFragment(), null, true, false);

                } else if (position == 2) {
                    FragmentManagerUtils.replaceFragmentInRoot(getSupportFragmentManager(), new DealsProductFragment(), null, true, false);


                } else if (position == 3) {
                    if (!TextUtils.isEmpty(CocoPreferences.getUserId())) {
                        FragmentManagerUtils.replaceFragmentInRoot(getSupportFragmentManager(), new CartFragment(), "CartFragment", true, false);
                    } else {
                        startActivity(new Intent(DrawerActivity.this, LoginAlertOnCartActivity.class));
                    }

                } else if (position == 4) {

                    if (!TextUtils.isEmpty(CocoPreferences.getUserId())) {
                        FragmentManagerUtils.replaceFragmentInRoot(getSupportFragmentManager(), new MyOrderFragment(), null, true, false);

                    } else {
                        startActivity(new Intent(DrawerActivity.this, LoginAlertOnMyOrderActivity.class));
                    }


                } else if (position == 5) {


                    if (!TextUtils.isEmpty(CocoPreferences.getUserId())) {
                        FragmentManagerUtils.replaceFragmentInRoot(getSupportFragmentManager(), new MyWishlistFragment(), "MyWishlistFragment", true, false);
                    } else {
                        startActivity(new Intent(DrawerActivity.this, LoginAlertOnWishlistActivity.class));
                    }


                } else if (position == 6) {

                    if (!TextUtils.isEmpty(CocoPreferences.getUserId())) {

                        FragmentManagerUtils.replaceFragmentInRoot(getSupportFragmentManager(), new MyAccountFragment(), "MyAccountFragment", true, false);

                    } else {
                        startActivity(new Intent(DrawerActivity.this, LoginAlertOnMyAccountActivity.class));
                    }


//                    FragmentManagerUtils.replaceFragmentInRoot(getSupportFragmentManager(), new ProductListByCategoryFragment(), "ProductByCategoryFragment", false, false);

                } else if (position == 7) {
                    if (!TextUtils.isEmpty(CocoPreferences.getUserId())) {
                        startActivity(new Intent(DrawerActivity.this, AddressListActivity.class));
                    } else {
                        startActivity(new Intent(DrawerActivity.this, LoginAlertOnAddressActivity.class));
                    }

                } else if (position == 8) {

                   /* Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://support.richkart.com"));
                    startActivity(browserIntent);*/

                    openWebView("https://support.richkart.com/portal/home");

                } else if (position == 9) {
                    openWebView("https://sellercenter.richkart.com");


                }else if (position == 10) {
                    FragmentManagerUtils.replaceFragmentInRoot(getSupportFragmentManager(), new LegalPoliciesFragment(), "LegalPoliciesFragment", true, false);


                } else if (position == 11) {



                    FragmentManagerUtils.replaceFragmentInRoot(getSupportFragmentManager(), new ChangeCountryFragment(), "ChangeCountryFragment", true, false);


                } else if (position == 12) {
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

        FragmentManagerUtils.replaceFragmentInRoot(getSupportFragmentManager(), new HomeFragment(), "HomeFragment", false, false);
//        FragmentManagerUtils.addFragmentInRoot(getSupportFragmentManager(), new HomeFragment(), "HomeFragment", false, false);

    }


    private void openWebView(String webUrl) {
        Bundle bundle = new Bundle();
        bundle.putString("webUrl", webUrl);
        LegalPoliciesWebPages legalPoliciesWebPages = new LegalPoliciesWebPages();
        legalPoliciesWebPages.setArguments(bundle);
        FragmentManagerUtils.replaceFragmentInRoot(getSupportFragmentManager(), legalPoliciesWebPages, "LegalPoliciesWebPages", true, false);
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
//                startActivity(new Intent(DrawerActivity.this, SearchFragment.class));
                FragmentManagerUtils.replaceFragmentInRoot(getSupportFragmentManager(), new SearchFragment(), "SearchFragment", true, false);

            }
        });

        toolbar.findViewById(R.id.btn_cart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(CocoPreferences.getUserId())) {
                    FragmentManagerUtils.replaceFragmentInRoot(getSupportFragmentManager(), new CartFragment(), "CartFragment", true, false);
                } else {
                    startActivity(new Intent(DrawerActivity.this, LoginAlertOnCartActivity.class));

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
                case R.id.imgRichkarLogo:

                    Fragment f = getCurrentFragmentInRootLayout();
                    if (!(f instanceof HomeFragment)) {
                        FragmentManagerUtils.replaceFragmentInRoot(getSupportFragmentManager(), new HomeFragment(), "HomeFragment", false, false);

                    }

                    break;


                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
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

    @Override
    public void onPaymentSuccess(String s) {
        try {
            Fragment f = getCurrentFragmentInRootLayout();
            ((ShippingFragment) f).onPaymentSuccess(s);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onPaymentError(int i, String s) {
        try {
            Fragment f = getCurrentFragmentInRootLayout();
            ((ShippingFragment) f).onPaymentError(i, s);
        } catch (Exception e) {
            e.printStackTrace();
        }
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


        String thumbnail = CocoPreferences.getProfilePic();
//        Glide.with(this).load(thumbnail).placeholder(R.drawable.user_dp).into(imgProfileImage);
        Glide.with(imgProfileImage.getContext()).load(thumbnail).placeholder(R.drawable.sidebar_non_loginimg).dontAnimate().into(imgProfileImage);


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

    public void logout() {
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
                            CocoPreferences.removeValueForKey("UserPic");

                            txtUserName.setVisibility(View.GONE);
                            txtUserEmail.setVisibility(View.GONE);
                            lyLoginSignup.setVisibility(View.VISIBLE);

                            navigationModelClasses.remove(12);
                            mAdapter.notifyDataSetChanged();

                            Util.showCenteredToast(drawer, DrawerActivity.this, "Logout Successfully!", Constant.API_SUCCESS);
                            init();

                        }
                    })
                    .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });

            AlertDialog dialog = builder.create();
            dialog.show();

            Button nButton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
            nButton.setTextColor(getColor(R.color.appgray));

            Button pButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
            pButton.setTextColor(getColor(R.color.yellow));

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
                navigationModelClasses.remove(12);
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


    public void darkMode() {

        Fragment currentFragment = getCurrentFragmentInRootLayout();
        if (currentFragment instanceof MyAccountFragment) {
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.detach(currentFragment);
            fragmentTransaction.attach(currentFragment);
            fragmentTransaction.commit();
        }

    }
}
