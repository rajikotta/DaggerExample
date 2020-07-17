package com.raji.daggerexample.main;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.raji.daggerexample.auth.SessionDataManager;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

/**
 * Created by Raji on 7/17/20.
 * Golden Scent
 */
public class PostViewModel extends ViewModel {
    MainApiService mMainApiService;
    SessionDataManager mSessionDataManager;

    MediatorLiveData<NetworkResource<List<Post>>> result = new MediatorLiveData();

    @Inject
    public PostViewModel(MainApiService mainApiService, SessionDataManager sessionDataManager) {
        mMainApiService = mainApiService;
        this.mSessionDataManager = sessionDataManager;
        getPosts();
    }

    public void getPosts() {
        String id = mSessionDataManager.getAuthUser().getValue().data.getId();
        LiveData<NetworkResource<List<Post>>> response = LiveDataReactiveStreams.
                fromPublisher(mMainApiService.getPosts(id)
                        .onErrorReturn(new Function<Throwable, List<Post>>() {
                            @Override
                            public List<Post> apply(Throwable throwable) throws Throwable {
                                List<Post> posts = new ArrayList<Post>();
                                posts.add(new Post("-1"));
                                return posts;
                            }
                        })
                        .map(new Function<List<Post>, NetworkResource<List<Post>>>() {
                            @Override
                            public NetworkResource<List<Post>> apply(List<Post> posts) throws Throwable {
                                if ("-1".equalsIgnoreCase(posts.get(0).getId()))
                                    return NetworkResource.error("error");
                                else
                                    return NetworkResource.success(posts);
                            }
                        })
                        .subscribeOn(Schedulers.io()));

        result.addSource(response, new Observer<NetworkResource<List<Post>>>() {
            @Override
            public void onChanged(NetworkResource<List<Post>> listNetworkResource) {
                result.setValue(listNetworkResource);
            }
        });
    }

    public MediatorLiveData<NetworkResource<List<Post>>> getResult() {
        return result;
    }
}
