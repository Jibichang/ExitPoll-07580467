package com.example.aomek.exitpoll.adapter;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aomek.exitpoll.R;
import com.example.aomek.exitpoll.model.PollItem;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class adapterPoll extends ArrayAdapter<PollItem> {
    private Context mContext;
    private int mResource;
    private List<PollItem> mPollItemList;

    public adapterPoll(@NonNull Context context,
                       int resource,
                       @NonNull List<PollItem>pollItemList) {
        super(context, resource, pollItemList);
        this.mContext = context;
        this.mResource = resource;
        this.mPollItemList = pollItemList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view = inflater.inflate(mResource, parent, false);

        TextView scoreTextView = view.findViewById(R.id.score_text);
        ImageView imgView = view.findViewById(R.id.score_image);

        PollItem pollItem = mPollItemList.get(position);
        String score = pollItem.score;
        String image = pollItem.img;

        scoreTextView.setText(score);
//        AssetManager am = mContext.getAssets();
//        try {
//            InputStream is = am.open(image);
//            Drawable drawable = Drawable.createFromStream(is,"");
//            imgView.setImageDrawable(drawable);
//        } catch (IOException e) {
//            File privateDir = mContext.getFilesDir();
//            File logoFile = new File(privateDir, image);
//
//            Bitmap bitmap = BitmapFactory.decodeFile(logoFile.getAbsolutePath(), null);
//            imgView.setImageBitmap(bitmap);
//
//            e.printStackTrace();
//        }


        return view;
    }
}
