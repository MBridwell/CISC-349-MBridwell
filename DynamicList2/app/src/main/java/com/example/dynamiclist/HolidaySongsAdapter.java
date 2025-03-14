package com.example.dynamiclist;

import android.content.Context;
import android.content.Intent;
import android.util.LruCache;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.ArrayList;
import android.graphics.Bitmap;

public class HolidaySongsAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {


    public HolidaySongsAdapter(Context context, ArrayList<HolidaySongs> arrayList, RequestQueue queue){
        this.context = context;
        this.arrayList = arrayList;
        this.queue = queue;
        this.imageLoader = new ImageLoader(queue, new ImageLoader.ImageCache() {
            private final LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(20);
            @Override
            public Bitmap getBitmap(String url) {
                return mCache.get(url);
            }
            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url, bitmap);
            }});
    }
    private Context context;
    private ArrayList<HolidaySongs> arrayList;
    private TextView  album_name, artist_name, playlist_img, duration_ms, danceability;
    private RequestQueue queue;
    private NetworkImageView album_image;

    private ImageLoader imageLoader;
    public static final String EXTRA_SELECTED_ITEM  = "edu.harrisburgu.cisc349.dynamiclist.selecteditem";


    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = LayoutInflater.from(context).inflate(R.layout.activity_view_songs,parent,false);
        album_image = (NetworkImageView) convertView.findViewById(R.id.album_image);
        album_name = convertView.findViewById(R.id.album_name);
        artist_name = convertView.findViewById(R.id.artist_name);
        duration_ms = convertView.findViewById(R.id.duration_ms);
        danceability = convertView.findViewById(R.id.danceability);






        HolidaySongs song = arrayList.get(position);

        Integer duration = song.getduration_ms(); // assuming it's a long or Integer

        Integer totalSeconds = duration / 1000;


        Integer minutes = totalSeconds / 60;
        Integer seconds = totalSeconds % 60;

        Double dance = song.getdanceability();


        String formattedDuration = String.format("%d:%02d", minutes, seconds);

        String formattedDancability = String.format("Danceability: %.3f", dance);

        album_name.setText(song.getalbum_name());
        artist_name.setText(song.getartist_name());
        //playlist_img.setText(song.getplaylist_img());
        duration_ms.setText(formattedDuration);
        danceability.setText(formattedDancability);


        album_image.setImageUrl(song.getalbum_image(), imageLoader);

        return convertView;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent = ViewSongs.newIntent(parent.getContext(),this);
        intent.putExtra(EXTRA_SELECTED_ITEM, position);
        parent.getContext().startActivity(intent);

    }

    public void populateView(View view, int index){
        HolidaySongs album = arrayList.get(index);
        TextView tv = view.findViewById(R.id.album_title);
        tv.setText(album.getalbum_name());

        NetworkImageView image = (NetworkImageView) view.findViewById(R.id.AlbumImage);
        image.setImageUrl(album.getplaylist_img(), imageLoader);}
    //   tv = view.findViewById(R.id.artistDisplayName);
    //   tv.setText(album.getArtist());
    //    NetworkImageView image = (NetworkImageView) view.findViewById(R.id.albumDisplayImageView);
    //    image.setImageUrl(album.getImage(), imageLoader);}
}


