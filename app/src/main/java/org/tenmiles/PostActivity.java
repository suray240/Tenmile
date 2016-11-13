package org.tenmiles;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.tenmiles.adapters.DashBoardAdapter;
import org.tenmiles.adapters.PostAdapter;
import org.tenmiles.fragment.DashBoardFragment;
import org.tenmiles.jumblr.JumblrClient;
import org.tenmiles.jumblr.types.Blog;
import org.tenmiles.jumblr.types.Post;
import org.tenmiles.jumblr.types.TextPost;
import org.tenmiles.jumblr.types.User;
import org.tenmiles.utils.API;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Collections.sort;

public class PostActivity extends AppCompatActivity {

    public static List<Post> posts;
    public static String blogName;
    private RecyclerView postRecyclerView;
    public static PostAdapter postAdapter;

    // Toolbar
    private Toolbar toolbar;

    private JumblrClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        setActionBar();

        try {
            client = new JumblrClient(API.CONSUMER_KEY, API.CONSUMER_SECRET);
            client.setToken(API.TOKEN, API.TOKEN_SECRET);
        } catch (Exception e) {
            e.printStackTrace();
        }
        postRecyclerView = (RecyclerView) findViewById(R.id.postRecyclerview);
        postRecyclerView.setLayoutManager(new LinearLayoutManager(PostActivity.this));



        Collections.sort(posts, new Comparator<Post>(){
            public int compare(Post c1, Post c2) {
                return c2.getSlug().compareToIgnoreCase(c1.getSlug());
            }
        });

        postAdapter = new PostAdapter(PostActivity.this, posts);
        postRecyclerView.setAdapter(postAdapter);


        FloatingActionButton floatingActionButton = (FloatingActionButton) findViewById(R.id.postFloatingButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(),AddPostActivity.class);
                startActivityForResult(intent, 1);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if (requestCode == 1) {
            try {
                postAdapter.setItems(posts);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void setActionBar() {
        toolbar = (Toolbar) findViewById(R.id.postToolbar);
        toolbar.setTitle(R.string.app_Posts);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);

    }
}
