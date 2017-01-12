package com.yj.listformat;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

//import com.bit2016.myapp.R;
//import com.bit2016.myapp.core.domain.User;
//import com.nostra13.universalimageloader.core.DisplayImageOptions;
//import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.yj.vo.UsersVo;
import com.yj.yjapp.R;

import java.util.List;

/**
 * Created by bit-user on 2016-12-02.
 */

public class UsersListArrayAdapter extends ArrayAdapter<UsersVo> {

    private LayoutInflater layoutInflater;

    DisplayImageOptions displayImageOptions = new DisplayImageOptions.Builder()
            // .showImageOnLoading( R.drawable.ic_default_profile )// resource or drawable
            .showImageForEmptyUri(R.drawable.ic_default_profile)// resource or drawable
            .showImageOnFail(R.drawable.ic_default_profile)// resource or drawable
            //.resetViewBeforeLoading( false )// default
            .delayBeforeLoading(0)
            //.cacheInMemory( false )// default
            .cacheOnDisc(true)// false is default
            //.preProcessor(...)
            //.postProcessor(...)
            //.extraForDownloader(...)
            //.considerExifParams( false )// default
            //.imageScaleType( ImageScaleType.IN_SAMPLE_POWER_OF_2 )// default
            //.bitmapConfig( Bitmap.Config.ARGB_8888 )// default
            //.decodingOptions(...)
            //.displayer( new SimpleBitmapDisplayer() )// default
            //.handler( new Handler() )// default
            .build();


    public UsersListArrayAdapter(Context context) {

        super(context, R.layout.activity_list);
        layoutInflater = layoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.row_users_list, parent, false);
        }
        //내부 ArrayList에서 해당 포지션의 User 객체를 받아옴

        UsersVo usersVo = getItem(position);

        //프로필 세팅
        ImageLoader.getInstance().displayImage(usersVo.getUsersImage(), (ImageView) view.findViewById(R.id.image_users), displayImageOptions);

    //이름 세팅
        TextView textViewId = (TextView) view.findViewById(R.id.textView_id);
        textViewId.setText(usersVo.getId());

        //비밀번호 세팅
        TextView textViewPw = (TextView) view.findViewById(R.id.textView_password);
        textViewPw.setText(usersVo.getPassword());

        return view;
    }

    public void add(List<UsersVo> usersVos) {
        if (usersVos == null || usersVos.size() == 0) {
            return;
        }
        for (UsersVo usersVo : usersVos) {
            add(usersVo);
        }
        //add에 이미 있음
        //notifyDataSetChanged();
    }
}
