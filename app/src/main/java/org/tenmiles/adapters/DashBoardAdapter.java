package org.tenmiles.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.tenmiles.R;
import org.tenmiles.jumblr.types.Blog;

import java.io.IOException;
import java.util.List;

/**
 * Created by compe18 on 4/11/15.
 */
public class DashBoardAdapter extends RecyclerView.Adapter<DashBoardAdapter.ViewHolder> implements View.OnClickListener {

    private Context context;
    private List<Blog> blogLists;

    public DashBoardAdapter(Context context, List<Blog> blogLists) {
        this.context = context;
        this.blogLists = blogLists;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_dashboard, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        Blog getBlogs = blogLists.get(position);

        try {
            String blogTitle = getBlogs.getTitle();
            holder.blogTitle.setText("" + blogTitle);
        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.blogTitle.setTag(position);

    }

    @Override
    public int getItemCount() {
        return blogLists.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView blogTitle;

        public ViewHolder(View itemView) {
            super(itemView);

            blogTitle = (TextView) itemView.findViewById(R.id.itemDashBoard);
            blogTitle.setOnClickListener(DashBoardAdapter.this);
        }
    }

    BlogInterface blogInterface;

    public void setBlogInterface(BlogInterface blogInterface) {
        this.blogInterface = blogInterface;
    }


    public interface BlogInterface {
        public void setBlogInterface(View v, String blogName, int position) ;

    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.itemDashBoard) {
            if (blogInterface != null) {
                int pos = Integer.parseInt(v.getTag().toString());
                String blogName = blogLists.get(pos).getName();
                blogInterface.setBlogInterface(v, "" + blogName, pos);
            }
        }
    }


}
