package com.talo.loadmore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.AbsListView;

import com.talo.loadmore.databinding.ActivityMainBinding;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private MainAdapter adapter;
    private ArrayList<Integer> list = new ArrayList<>();
    private LinearLayoutManager manager;

    /**判斷是否滑動*/
    private Boolean isScrolling = false;

    /**顯示最多數量、總數、欲載入數量
     * 總數算法:顯示最多數量 + 欲載入數量*/

    private int currentItem, totalItems, scrollOutItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        test();
        manager = new LinearLayoutManager(this);
        binding.recycler.setLayoutManager(manager);
        binding.recycler.setHasFixedSize(true);
        adapter = new MainAdapter(getBaseContext(), list);
        binding.recycler.setAdapter(adapter);


        /**載入更多*/
        binding.recycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (newState == AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL) {
                    isScrolling = true;
                }
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                currentItem = manager.getChildCount();
                totalItems = manager.getItemCount();
                scrollOutItem = manager.findFirstVisibleItemPosition();

                if (isScrolling && (currentItem + scrollOutItem == totalItems)) {
                    //更新數據
                    isScrolling = false;
                    fetchData();
                }

            }
        });

    }

    private void fetchData() {
        binding.progress.setVisibility(View.VISIBLE);
        new Handler().postDelayed(() -> {
            for (int i = 0; i < 5; i++) {
                list.add(i);
                adapter.notifyDataSetChanged();
                binding.progress.setVisibility(View.GONE);
            }
        }, 1000);
    }

    private void test() {
        for (int i = 0; i < 13; i++) {
            list.add(i);
        }
    }
}