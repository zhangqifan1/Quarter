package com.v.quarter.Presenter;

import com.v.quarter.API.IFindHotFragmentView;
import com.v.quarter.API.IHotFragmentModel;
import com.v.quarter.JavaBeans.FindHotBean;
import com.v.quarter.Models.FindHotModel;

/**
 * Created by Administrator on 2017/11/14.
 */

public class FindHotPresenter {
    private IFindHotFragmentView view;
    private IHotFragmentModel model;

    public FindHotPresenter(IFindHotFragmentView view) {
        this.view = view;
        model=new FindHotModel();
    }

    public  void setModelView(){
        model.getFindHotBean(new IHotFragmentModel.CallBack() {
            @Override
            public void setHotBean(FindHotBean bean) {
                view.setBean(bean);
            }
        });
    }
}
