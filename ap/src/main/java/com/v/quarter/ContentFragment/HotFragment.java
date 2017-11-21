package com.v.quarter.ContentFragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.v.quarter.API.IFindHotFragmentView;
import com.v.quarter.Adapters.FindHotAdapter;
import com.v.quarter.JavaBeans.FindHotBean;
import com.v.quarter.Presenter.FindHotPresenter;
import com.v.quarter.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class HotFragment extends Fragment implements IFindHotFragmentView{


    private RecyclerView findHotRecy;
    private FindHotPresenter findHotPresenter;
    private FindHotAdapter findHotAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_hot, container, false);
        initView(inflate);
        findHotPresenter = new FindHotPresenter(this);
        findHotPresenter.setModelView();
        return inflate;
    }

    private void initView(View inflate) {
        findHotRecy = (RecyclerView) inflate.findViewById(R.id.findHotRecy);
    }

    @Override
    public void setBean(FindHotBean bean) {

        if(bean==null){
            findHotPresenter.setModelView();
        }else if(bean!=null){

            findHotAdapter = new FindHotAdapter(getContext(), bean);
            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
            findHotRecy.setLayoutManager(linearLayoutManager);
            findHotRecy.setAdapter(findHotAdapter);
        }

    }


    @Override
    public void onDestroy() {
        super.onDestroy();

        //调用ShareSDK关闭的方法,释放资源.
    }
}
