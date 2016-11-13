package org.tenmiles.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.tenmiles.R;
import org.tenmiles.jumblr.types.Blog;
import org.tenmiles.jumblr.types.Post;

import java.util.List;

/**
 * Created by compe18 on 4/11/15.
 */
public class PostAdapter extends RecyclerView.Adapter<PostAdapter.ViewHolder>  {

    private Context context;
    private List<Post> posts;

    public PostAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_dashboard, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        Post getPosts = posts.get(position);

        try {
            String blogUrl = getPosts.getSlug();

            holder.blogTitle.setText("" + blogUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.blogTitle.setTag(position);

    }

    @Override
    public int getItemCount() {
        return posts.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView blogTitle;

        public ViewHolder(View itemView) {
            super(itemView);

            blogTitle = (TextView) itemView.findViewById(R.id.itemDashBoard);

        }
    }

    public void setItems( List<Post> myList) {
        this.posts.clear();
        this.posts.addAll(myList);
        notifyDataSetChanged();
    }


}
