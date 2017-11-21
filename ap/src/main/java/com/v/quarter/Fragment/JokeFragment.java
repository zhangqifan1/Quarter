package com.v.quarter.Fragment;


import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.v.quarter.API.IJokeFragment;
import com.v.quarter.Adapters.JokesAdapter;
import com.v.quarter.JavaBeans.DayNightBean;
import com.v.quarter.JavaBeans.JokeBean;
import com.v.quarter.Presenter.JokePresenter;
import com.v.quarter.R;

import cn.jzvd.JZVideoPlayer;
import de.greenrobot.event.Subscribe;

/**
 * A simple {@link Fragment} subclass.
 */
public class JokeFragment extends Fragment implements IJokeFragment {


    private JokePresenter jokePresenter;
    private RecyclerView recyclerview;
    private RelativeLayout jokeRelative;

    @Subscribe(sticky = true)
    public void DayNightReceive(DayNightBean bean) {
        if (!bean.isDay()) {
            jokeRelative.setBackgroundColor(Color.BLACK);
        }
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflate = inflater.inflate(R.layout.fragment_joke, container, false);
        JZVideoPlayer.releaseAllVideos();
        initView(inflate);

        jokePresenter = new JokePresenter(this);
        jokePresenter.CombineModelWithView();

        return inflate;
    }

    @Override
    public void setJokeBean(JokeBean bean) {
        if (bean == null) {
            jokePresenter.CombineModelWithView();
        } else {
            JokesAdapter jokesAdapter = new JokesAdapter(getContext(), bean);
            LinearLayoutManager manager=new LinearLayoutManager(getContext());
            recyclerview.setLayoutManager(manager);
            recyclerview.setAdapter(jokesAdapter);
        }
    }

    private void initView(View inflate) {
        recyclerview = (RecyclerView) inflate.findViewById(R.id.recyclerview);
        jokeRelative = (RelativeLayout) inflate.findViewById(R.id.jokeRelative);
    }
}
