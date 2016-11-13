package org.tenmiles.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import org.tenmiles.Home;
import org.tenmiles.PostActivity;
import org.tenmiles.R;
import org.tenmiles.adapters.DashBoardAdapter;
import org.tenmiles.jumblr.JumblrClient;
import org.tenmiles.jumblr.types.Blog;
import org.tenmiles.jumblr.types.Photo;
import org.tenmiles.jumblr.types.PhotoPost;
import org.tenmiles.jumblr.types.Post;
import org.tenmiles.jumblr.types.TextPost;
import org.tenmiles.jumblr.types.User;
import org.tenmiles.utils.API;
import org.tenmiles.utils.ConnectionDetector;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class DashBoardFragment extends Fragment implements  DashBoardAdapter.BlogInterface {

    private View rootView;
    private RecyclerView recyclerView;
    private DashBoardAdapter dashBoardAdapter;

    private Context context = getActivity();
    private JumblrClient client;

    ConnectionDetector cd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_dashboard, null);
        cd = new ConnectionDetector(getActivity());
        setView();
        return rootView;
    }

    private void setView() {
        recyclerView = (RecyclerView) rootView.findViewById(R.id.dashboardRecyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        try {
            if (cd.isConnectingToInternet()) {

                client = new JumblrClient(API.CONSUMER_KEY, API.CONSUMER_SECRET);
                client.setToken(API.TOKEN, API.TOKEN_SECRET);

                User userInformation = client.user();
                List<Blog> blogList = userInformation.getBlogs();

                dashBoardAdapter = new DashBoardAdapter(getActivity(), blogList);
                recyclerView.setAdapter(dashBoardAdapter);
                dashBoardAdapter.setBlogInterface(DashBoardFragment.this);
            }else{


                AlertDialog.Builder builder1 = new AlertDialog.Builder(getActivity());
                builder1.setMessage("Check Internet Connection and Come Again");
                builder1.setCancelable(true);

                builder1.setPositiveButton(
                        "Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                                getActivity().finish();
                            }
                        });


                AlertDialog alert11 = builder1.create();
                alert11.show();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }



    @Override
    public void setBlogInterface(View v, String blogName, int position) {

        if (cd.isConnectingToInternet()) {
            if (blogName != null && blogName.length() > 0) {
                List<Post> allPosts = client.blogPosts(blogName);
                PostActivity.posts = allPosts;
                PostActivity.blogName = blogName;
                startActivity(new Intent(getActivity(), PostActivity.class));

            }else{
                Toast.makeText(context, "Problem in getting Posts", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(getActivity(), "Check Interne Connection", Toast.LENGTH_SHORT).show();
        }
    }

}
