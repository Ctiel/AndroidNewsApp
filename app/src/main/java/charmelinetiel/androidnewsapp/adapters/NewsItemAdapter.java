package charmelinetiel.androidnewsapp.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import charmelinetiel.androidnewsapp.R;
import charmelinetiel.androidnewsapp.models.Article;

/**
 * Created by Tiel on 26-9-2017.
 */

public class NewsItemAdapter extends RecyclerView.Adapter<NewsItemAdapter.ViewHolder>  {


    private List<Article> mItems;
    private Context mContext;
    private NewsItemListener mListener;

    //listener interface
    public interface NewsItemListener {

        void onItemClick(View view, Article content);
    }

    public NewsItemAdapter(Context context, List<Article> items, NewsItemListener listener){

        mItems = items;
        mListener = listener;
       mContext = context;

    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView title;
        public TextView description;
        public ImageView image;


        ViewHolder(View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.list_item_image);
            title = itemView.findViewById(R.id.list_item_title);
            description = itemView.findViewById(R.id.list_item_description);

        }

        @Override
        public void onClick(View view) {
            //
            Log.d("RecyclerView", "CLICK");
        }
    }

    public Article getItem(int position){

        return mItems.get(position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item_view, parent, false));
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        final Article node = getItem(position);

        //fill the views in the viewholder with content
        holder.title.setText(node.getTitle());
        Glide.with(mContext).load(node.getImage()).centerCrop().crossFade().into(holder.image);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onItemClick(holder.itemView, node);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }


}
