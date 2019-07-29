package com.ws.design.coco_ecommerce_ui_kit.legal_policies;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.wolfsoft2.coco_ecommerce_ui_kit.R;
import com.ws.design.coco_ecommerce_ui_kit.base_fragment.BaseFragment;

import fragment.FragmentManagerUtils;

public class LegalPoliciesFragment extends BaseFragment implements View.OnClickListener {

    private LegalPoliciesAdapter legalPoliciesAdapter;
    private RecyclerView rvLegalPoliciesList;

    private View mView;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mView = inflater.inflate(R.layout.fragment_legal_policies, container, false);

        return mView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {




        rvLegalPoliciesList =  view.findViewById(R.id.rvLegalPoliciesList);




       LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        rvLegalPoliciesList.setLayoutManager(layoutManager);

        String[] legalPoliciesArray = getResources().getStringArray(R.array.legal_policies_array);


        legalPoliciesAdapter = new LegalPoliciesAdapter(getActivity(), legalPoliciesArray, LegalPoliciesFragment.this);
        rvLegalPoliciesList.setAdapter(legalPoliciesAdapter);

    }





    @Override
    public void onClick(View view) {
        try {
            int vId = view.getId();
            switch (vId) {
                case R.id.txtLegalPolicies:
                    FragmentManagerUtils.replaceFragmentInRoot(getActivity().getSupportFragmentManager(), new TermsConditionFragment(), null, true, false);

                    break;


                default:
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected String getActionbarTitle() {
        return getString(R.string.legal_policies);
    }

    @Override
    protected boolean isCartIconVisible() {
        return  false;
    }

    @Override
    protected boolean isSearchIconVisible() {
        return false;
    }
}
