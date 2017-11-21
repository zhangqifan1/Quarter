package com.v.quarter.Adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.v.quarter.CustomViews.MenuView;
import com.v.quarter.JavaBeans.JokeBean;
import com.v.quarter.R;

/**
 * Created by Administrator on 2017/11/16.
 */


public class JokesAdapter extends RecyclerView.Adapter<JokesAdapter.MyViewHolder> {

    private Context context;
    private JokeBean bean;
    private SparseArray<Boolean> array = new SparseArray<>();


    public JokesAdapter(Context context, JokeBean bean) {
        this.context = context;
        this.bean = bean;
        for (int i = 0; i < bean.getCharacter().size(); i++) {
            array.put(i, false);
        }

    }


    //创建视图
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.itemjoke, null);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    //绑定视图的数据
    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        holder.tvFriendNameJ.setText(bean.getCharacter().get(position).getUser().getUserName());
        holder.tvTimeJ.setText(bean.getCharacter().get(position).getCharacter_uptime());
        holder.tvContentJ2.setText(bean.getCharacter().get(position).getCharacter_content());
        holder.menuView.imageOpenMenuJ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                array.put(position,!array.get(position));
                if(array.get(position)==true){
                    holder.menuView.openMenu();

                }else{
                    holder.menuView.closeMenu();
                }
                holder.menuView.setaBoolean(array.get(position));
            }
        });

    }


    @Override
    public int getItemCount() {
        return bean.getCharacter().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvFriendNameJ, tvTimeJ, tvContentJ2;
        private final MenuView menuView;

        public MyViewHolder(View itemView) {
            super(itemView);
            tvFriendNameJ = itemView.findViewById(R.id.tvFriendNameJ);
            tvTimeJ = itemView.findViewById(R.id.tvTimeJ);
            tvContentJ2 = itemView.findViewById(R.id.tvContentJ2);
            menuView = itemView.findViewById(R.id.MenuView);
        }
    }


}