package com.salmakhd.android.androidwithjavaandxml.cardView;

import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.salmakhd.android.androidwithjavaandxml.R;
import com.salmakhd.android.androidwithjavaandxml.databinding.ActivityCardViewBinding;

import java.util.ArrayList;
import java.util.List;

public class CardViewActivity extends AppCompatActivity {
    private List<AppModel> appModelList;
    private AppsAdaptor adaptor;
    private RecyclerView recyclerView;

    private ActivityCardViewBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityCardViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Toolbar toolbar = binding.toolbar;
        setSupportActionBar(toolbar);
        CollapsingToolbarLayout toolBarLayout = binding.toolbarLayout;
        toolBarLayout.setTitle(getTitle());


        FloatingActionButton fab = binding.fab;
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null)
                        .setAnchorView(R.id.fab).show();
            }
        });

        recyclerView = findViewById(R.id.recyclerView);
        appModelList = new ArrayList<>();
        adaptor = new AppsAdaptor(this, appModelList);

        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(layoutManager);

        // Item Declarations
        recyclerView.addItemDecoration(new GrindSpacingItemDecoration(2, dpToPx(10), true));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        insertDataIntoCards();
    }

    private void insertDataIntoCards() {
        // Add card data and display them
        int[] appsCovers = new int[] {
                R.drawable.biking,
                R.drawable.bmichart
        };

        AppModel appModel1 = new AppModel(
                "Master Android App",
                800000,
                appsCovers[0]
        );
        AppModel appModel2 = new AppModel(
                "Master Android App",
                800000,
                appsCovers[1]
        );
        appModelList.add(appModel1);
        appModelList.add(appModel2);
        adaptor.notifyDataSetChanged();
    }

    private class GrindSpacingItemDecoration extends RecyclerView.ItemDecoration {
        private int spaceCount;
        private int spacing;
        private boolean includeSpacing;

        public GrindSpacingItemDecoration(int spaceCount, int spacing, boolean includeSpacing) {
            this.spaceCount = spaceCount;
            this.spacing = spacing;
            this.includeSpacing = includeSpacing;
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);
            int position = parent.getChildAdapterPosition(view);
            int column = position%spaceCount;

            if(includeSpacing) {
                outRect.left = spacing - column * spacing / spaceCount;
                outRect.right = (column+1) * spacing / spaceCount;

                if(position < spaceCount) {
                    outRect.top = spacing;
                }
                outRect.bottom = spacing;
            } else {
                outRect.left = column * spacing / spaceCount;
                outRect.right = spacing - (column +1) *spacing;
                if(position >= spaceCount) {
                    outRect.top = spacing;
                }
            }
        }
    }

    private int dpToPx(int dp) {
        Resources r = getResources();
        return Math.round(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dp, r.getDisplayMetrics()));
    }
}