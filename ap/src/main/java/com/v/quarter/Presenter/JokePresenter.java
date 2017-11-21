package com.v.quarter.Presenter;

import com.v.quarter.API.IJokeFragment;
import com.v.quarter.API.IJokeFramentModel;
import com.v.quarter.JavaBeans.JokeBean;
import com.v.quarter.Models.JokeFrgmentModel;

/**
 * Created by Administrator on 2017/11/16.
 */

public class JokePresenter {
    //http://172.17.29.27/quarter/character/select_character
    private IJokeFragment iJokeFragment;
    private IJokeFramentModel model;

    public JokePresenter(IJokeFragment iJokeFragment) {
        this.iJokeFragment = iJokeFragment;
        model = new JokeFrgmentModel();
    }

    public void CombineModelWithView() {
        model.getJokeBean(new IJokeFramentModel.CallBack() {
            @Override
            public void setJokeBean(JokeBean bean) {
                iJokeFragment.setJokeBean(bean);
            }
        });
    }
}
