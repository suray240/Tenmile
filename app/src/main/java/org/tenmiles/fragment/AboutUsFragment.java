package org.tenmiles.fragment;

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
import android.webkit.WebView;
import android.widget.Toast;

import org.tenmiles.PostActivity;
import org.tenmiles.R;
import org.tenmiles.adapters.DashBoardAdapter;
import org.tenmiles.jumblr.JumblrClient;
import org.tenmiles.jumblr.types.Blog;
import org.tenmiles.jumblr.types.Post;
import org.tenmiles.jumblr.types.User;
import org.tenmiles.utils.API;
import org.tenmiles.utils.ConnectionDetector;

import java.util.List;


public class AboutUsFragment extends Fragment {

    private View rootView;
    private WebView webView;
    ConnectionDetector cd;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_webview, null);
        cd = new ConnectionDetector(getActivity());
        setView();
        return rootView;
    }

    private void setView() {
        webView = (WebView) rootView.findViewById(R.id.webview);
        webView.loadUrl("http://tenmiles.com/blog/");
    }
}
