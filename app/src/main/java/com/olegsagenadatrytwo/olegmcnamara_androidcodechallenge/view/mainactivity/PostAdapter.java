package com.olegsagenadatrytwo.olegmcnamara_androidcodechallenge.view.mainactivity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.olegsagenadatrytwo.olegmcnamara_androidcodechallenge.R;
import com.olegsagenadatrytwo.olegmcnamara_androidcodechallenge.entities.Child;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder>{

    private List<Child> posts = new ArrayList<>();
    private Context context;

    public PostAdapter(List<Child> posts, Context context) {
        this.posts = posts;
        this.context = context;
    }

    public void setPosts(List<Child> posts){
        this.posts = posts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.posts_initial_info_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        //set the text of each text view accordingly
        final String author = posts.get(position).getData().getAuthor();
        if(author != null) {
            holder.tvUserID.setText(author.toUpperCase());
        }
        if(posts.get(position).getData().getTitle() != null) {
            holder.tvPost.setText(posts.get(position).getData().getTitle());
        }
        if(posts.get(position).getData().getNumComments() != null) {
            holder.tvComments.setText(context.getString(R.string.comments_message, String.valueOf(posts.get(position).getData().getNumComments())));
        }
        if(posts.get(position).getData().getUps() != null) {
            holder.tvUps.setText(context.getString(R.string.ups_message, String.valueOf(posts.get(position).getData().getNumComments())));
        }
        if(posts.get(position).getData().getDowns() != null) {
            holder.tvDowns.setText(context.getString(R.string.downs_message, String.valueOf(posts.get(position).getData().getNumComments())));
        }

        final String imageURL = posts.get(position).getData().getThumbnail();
        if(imageURL != null){
            Glide.with(context).load(imageURL).into(holder.ivImage);
        }else{
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                holder.ivImage.setImageDrawable(context.getResources().getDrawable(R.drawable.defaultimage, context.getApplicationContext().getTheme()));
            } else {
                holder.ivImage.setImageDrawable(context.getResources().getDrawable(R.drawable.defaultimage));
            }
        }

        //onClick
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent sendIntent = new Intent();
                sendIntent.setAction(android.content.Intent.ACTION_SEND);
                String message = "Check out what " + author + " said on Reddit: " +
                        posts.get(position).getData().getTitle();
                sendIntent.putExtra(Intent.EXTRA_TEXT, message);
                sendIntent.setType("text/plain");

                if (sendIntent.resolveActivity(context.getPackageManager()) != null) {
                    context.startActivity(sendIntent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvUserID;
        private TextView tvPost;
        private TextView tvComments;
        private TextView tvUps;
        private TextView tvDowns;
        private ImageView ivImage;

        ViewHolder(View itemView) {
            super(itemView);
            tvUserID = (TextView) itemView.findViewById(R.id.tvUserID);
            tvPost = (TextView) itemView.findViewById(R.id.tvPost);
            tvComments = (TextView) itemView.findViewById(R.id.tvComments);
            tvUps = (TextView) itemView.findViewById(R.id.tvUps);
            tvDowns = (TextView) itemView.findViewById(R.id.tvDowns);
            ivImage = (ImageView) itemView.findViewById(R.id.ivImage);
        }
    }

}
