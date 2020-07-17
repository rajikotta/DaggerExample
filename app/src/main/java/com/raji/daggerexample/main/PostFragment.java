package com.raji.daggerexample.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.raji.daggerexample.R;
import com.raji.daggerexample.databinding.FragmentPostBinding;
import com.raji.daggerexample.di.ViewModelFactory;

import java.util.List;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

/**
 * Created by Raji on 7/17/20.
 * Golden Scent
 */
public class PostFragment extends DaggerFragment {
    FragmentPostBinding databinding;

    @Inject
    ViewModelFactory mViewModelFactory;

    @Inject
    PostRecyclerAdapter mPostRecyclerAdapter;

    PostViewModel mPostViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        databinding = DataBindingUtil.inflate(inflater, R.layout.fragment_post, container, false);
        return databinding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mPostViewModel = ViewModelProviders.of(this, mViewModelFactory).get(PostViewModel.class);

        databinding.recyclerView.setAdapter(mPostRecyclerAdapter);
        mPostViewModel.result.observe(this, new Observer<NetworkResource<List<Post>>>() {
            @Override
            public void onChanged(NetworkResource<List<Post>> listNetworkResource) {
                if (listNetworkResource.status == NetworkResource.ResourceStatus.SUCCESS && listNetworkResource.data != null) {
                    mPostRecyclerAdapter.setPosts(listNetworkResource.data);
                } else if (listNetworkResource.status == NetworkResource.ResourceStatus.ERROR) {
                    Toast.makeText(getActivity(), "Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mPostViewModel.getPosts();
    }
}
