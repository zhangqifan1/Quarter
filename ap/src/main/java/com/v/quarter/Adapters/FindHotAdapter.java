package com.v.quarter.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.v.quarter.CustomViews.MenuView;
import com.v.quarter.JavaBeans.FindHotBean;
import com.v.quarter.R;
import com.v.quarter.Utils.GlideImageLoader;
import com.youth.banner.Banner;

import java.util.ArrayList;
import java.util.List;

import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerStandard;
import cn.sharesdk.onekeyshare.OnekeyShare;

/**
 * Created by Administrator on 2017/11/13.
 */


public class FindHotAdapter extends RecyclerView.Adapter<FindHotAdapter.MyViewHolder> {
//    private HashMap<Integer, Boolean> hashMap = new HashMap<>();
//    private HashMap<Integer, Boolean> hashMap2 = new HashMap<>();

    private SparseArray<Boolean> array=new SparseArray<>();
    private SparseArray<Boolean> arrayIcons=new SparseArray<>();
    private Context context;
    private FindHotBean bean;
    private final List<Integer> listImages = new ArrayList<>();
    private int width;



    public FindHotAdapter(Context context, FindHotBean bean) {
        this.context = context;
        this.bean = bean;
        listImages.add(R.drawable.banner1);
        listImages.add(R.drawable.banner2);
        for (int i = 0; i < bean.getResource().size(); i++) {
            array.put(i, false);
        }
        for (int i = 0; i < 4; i++) {
            arrayIcons.put(i, false);
        }
    }


    //创建视图
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view0 = View.inflate(context, R.layout.item0, null);
        View viewother = View.inflate(context, R.layout.itemother, null);

        MyViewHolder myViewHolder = new MyViewHolder(view0);
        MyViewHolder myViewHolderOther = new MyViewHolder(viewother);

        if (viewType == a) {
            return myViewHolder;
        } else {
            return myViewHolderOther;
        }

    }

    //绑定视图的数据

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        int itemViewType = getItemViewType(position);

        switch (itemViewType) {
            case a:
                holder.banner.setImageLoader(new GlideImageLoader())
                        .setImages(listImages)
                        .setDelayTime(2000)
                        .start();
                break;
            case b:
//                String userHead = bean.getResource().get(position).getUser().getUserHead();
//                if(userHead!=null && ! userHead.equals("")){
//                    Glide.with(context).load(userHead).into(holder.imageUserIcon);
//                }

                holder.tvFriendName.setText(bean.getResource().get(position).getUser().getUserName());
                holder.tvTime.setText(bean.getResource().get(position).getUptime());


                //图片点击事件
                ImageIconClicks(holder);
                holder.tvContent.setText(bean.getResource().get(position).getContent());
                holder.tvHeart.setText(bean.getResource().get(position).getCommentNum() + "");
                holder.tvStart.setText(bean.getResource().get(position).getNiceNum() + "");
                holder.imageContent.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        holder.imageicons.setVisibility(View.GONE);
                        holder.imagePlayer.setVisibility(View.VISIBLE);
                    }
                });
                holder.imagePlayer.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        JZVideoPlayer.releaseAllVideos();
                        holder.imagePlayer.setVisibility(View.GONE);
                        holder.media.setVisibility(View.VISIBLE);
                        holder.media.setUp("http://jzvd.nathen.cn/c6e3dc12a1154626b3476d9bf3bd7266/6b56c5f0dc31428083757a45764763b0-5287d2089db37e62345123a1be272f8b.mp4"
                                , JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "饺子闭眼睛");
                    }
                });


                break;


            default:
                break;
        }

    }

    private void ImageIconClicks(final MyViewHolder holder) {
        holder.imageStart.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                if (arrayIcons.get(1) == false) {
                    arrayIcons.put(1, true);
                    holder.imageStart.setImageResource(R.drawable.xing2);
                } else {
                    holder.imageStart.setImageResource(R.drawable.start);

                    arrayIcons.put(1, false);
                }
            }
        });
        holder.imageHeart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (arrayIcons.get(0) == false) {
                    arrayIcons.put(0, true);
                    holder.imageHeart.setImageResource(R.drawable.xin2);
                } else {
                    holder.imageHeart.setImageResource(R.drawable.heart);
                    arrayIcons.put(0, false);
                }
            }
        });
        holder.imageShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShare();
            }
        });



    }


    @Override
    public int getItemCount() {
        return bean.getResource().size();
    }

    private static final int a = 643;
    private static final int b = 267;

    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            return a;
        } else {
            return b;
        }

    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        private Banner banner;
        private final ImageView  imageContent, imageUserIcon;
        private final TextView tvFriendName, tvTime, tvContent;
        private final ImageView imageStart, imagePlayer, imageHeart, imageShare, imageMessage;
        private final TextView tvHeart;
        private final TextView tvShare;
        private final TextView tvStart;
        private final TextView tvMessage;
        private final LinearLayout imageicons;
        private final MenuView mm;
        private final JZVideoPlayerStandard media;

        public MyViewHolder(View itemView) {
            super(itemView);
            banner = itemView.findViewById(R.id.banner);
            tvFriendName = itemView.findViewById(R.id.tvFriendName);
            tvTime = itemView.findViewById(R.id.tvTime);
            tvContent = itemView.findViewById(R.id.tvContent);
            imageUserIcon = itemView.findViewById(R.id.imageUserIcon);
            imageContent = itemView.findViewById(R.id.imageContent);


            imageicons = itemView.findViewById(R.id.imageicons);
            imagePlayer = itemView.findViewById(R.id.imagePlayer2);
            //图片和字u
            imageStart = itemView.findViewById(R.id.imageStart);
            imageHeart = itemView.findViewById(R.id.imageHeart);
            imageShare = itemView.findViewById(R.id.imageShare);
            imageMessage = itemView.findViewById(R.id.imageMessage);

            tvHeart = itemView.findViewById(R.id.tvHeart);
            tvShare = itemView.findViewById(R.id.tvShare);
            tvStart = itemView.findViewById(R.id.tvStart);
            tvMessage = itemView.findViewById(R.id.tvMessage);

            media = itemView.findViewById(R.id.videoplayer);
            mm = itemView.findViewById(R.id.mm);
        }
    }




    private void showShare() {
        OnekeyShare oks = new OnekeyShare();
        //关闭sso授权
        oks.disableSSOWhenAuthorize();

        // 分享时Notification的图标和文字  2.5.9以后的版本不     调用此方法
        //oks.setNotification(R.drawable.ic_launcher, getString(R.string.app_name));
        // title标题，印象笔记、邮箱、信息、微信、人人网和QQ空间使用
        oks.setTitle("分享");
        // titleUrl是标题的网络链接，仅在人人网和QQ空间使用
        oks.setTitleUrl("http://sharesdk.cn");
        // text是分享文本，所有平台都需要这个字段
        oks.setText("瞧一瞧看一看");
        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
        // url仅在微信（包括好友和朋友圈）中使用
        oks.setUrl("http://sharesdk.cn");
        // comment是我对这条分享的评论，仅在人人网和QQ空间使用
        oks.setComment("我是测试评论文本");
        // site是分享此内容的网站名称，仅在QQ空间使用
        oks.setSite(context.getString(R.string.app_name));
        // siteUrl是分享此内容的网站地址，仅在QQ空间使用
        oks.setSiteUrl("http://sharesdk.cn");

        // 启动分享GUI
        oks.show(context);
    }


}

