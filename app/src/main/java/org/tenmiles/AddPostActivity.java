package org.tenmiles;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.tenmiles.adapters.PostAdapter;
import org.tenmiles.jumblr.JumblrClient;
import org.tenmiles.jumblr.types.Post;
import org.tenmiles.jumblr.types.TextPost;
import org.tenmiles.utils.API;
import org.tenmiles.utils.ConnectionDetector;

import java.util.List;

import static org.tenmiles.PostActivity.blogName;

public class AddPostActivity extends AppCompatActivity {

    public static List<Post> posts;

    // Toolbar
    private Toolbar toolbar;

    private EditText edtTitle, edtBody, edtSlug;
    private TextInputLayout txtTitle, txtBody, txtSlug;
    private JumblrClient client;

    private Intent intent;
    String ActivityResult;
    private ConnectionDetector cd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_post);

        setActionBar();
        cd = new ConnectionDetector(AddPostActivity.this);

        intent = getIntent();
        Intent intent = getIntent();
        ActivityResult = getIntent().getStringExtra("name");

        edtTitle = (EditText) findViewById(R.id.txtTitle);
        edtBody = (EditText) findViewById(R.id.txtBody);
        edtSlug = (EditText) findViewById(R.id.txtSlug);

        txtTitle = (TextInputLayout) findViewById(R.id.input_title);
        txtBody = (TextInputLayout) findViewById(R.id.input_body);
        txtSlug = (TextInputLayout) findViewById(R.id.input_slug);

        edtTitle.addTextChangedListener(new MyTextWatcher(edtTitle));
        edtBody.addTextChangedListener(new MyTextWatcher(txtBody));
        edtSlug.addTextChangedListener(new MyTextWatcher(edtSlug));

        try {
            client = new JumblrClient(API.CONSUMER_KEY, API.CONSUMER_SECRET);
            client.setToken(API.TOKEN, API.TOKEN_SECRET);
        } catch (Exception e) {
            e.printStackTrace();
        }


        ((Button) findViewById(R.id.btnSubmit)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!Validate(edtTitle, txtTitle)) {
                    return;
                } else if (!Validate(edtBody, txtBody)) {
                    return;
                } else if (!Validate(edtSlug, txtSlug)) {
                    return;
                } else {

                    try {
                        if (cd.isConnectingToInternet()) {
                            TextPost textPost = client.newPost(blogName, TextPost.class);
                            textPost.setTitle("" + edtTitle.getText().toString());
                            textPost.setBody("" + edtBody.getText().toString());
                            textPost.setSlug("" + edtSlug.getText().toString());
                            textPost.save();

                            List<Post> allPosts = client.blogPosts(blogName);
                            PostActivity.posts = allPosts;
                        }else{
                            Toast.makeText(AddPostActivity.this, "Check Internet Connection", Toast.LENGTH_SHORT).show();
                        }


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Intent BackIntent = new Intent();
                    setResult(1,BackIntent);
                    finish();

                    Toast.makeText(AddPostActivity.this, "Post Added Successfully", Toast.LENGTH_SHORT).show();
                }

            }
        });

        ((Button) findViewById(R.id.btnCancel)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private boolean Validate(EditText editText, TextInputLayout textInputLayout) {
        if (editText.getText().toString().trim().isEmpty()) {
            textInputLayout.setError("Error in Title");
            requestFocus(editText);
            return false;
        } else {
            textInputLayout.setErrorEnabled(false);
        }


        return true;
    }

    private void requestFocus(View view) {
        if (view.requestFocus()) {
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
        }
    }

    private class MyTextWatcher implements TextWatcher {

        private View view;

        private MyTextWatcher(View view) {
            this.view = view;
        }

        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
        }

        public void afterTextChanged(Editable editable) {
            switch (view.getId()) {
                case R.id.txtTitle:
                    Validate(edtTitle, txtTitle);
                    break;
                case R.id.txtBody:
                    Validate(edtBody, txtBody);
                    break;
                case R.id.txtSlug:
                    Validate(edtSlug, txtSlug);
                    break;
            }
        }
    }

    private void setActionBar() {
        toolbar = (Toolbar) findViewById(R.id.addPostToolbar);
        toolbar.setTitle(R.string.app_add_Posts);
        toolbar.setTitleTextColor(getResources().getColor(R.color.white));
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setHomeButtonEnabled(true);

    }
}
