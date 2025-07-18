package onlineshopapp.activity;

import android.os.Bundle;
import android.view.Window;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.salmakhd.android.androidwithjavaandxml.R;
import com.salmakhd.android.androidwithjavaandxml.databinding.ActivityOnlineShopBinding;
import com.salmakhd.android.androidwithjavaandxml.intents.MainActivity;

import java.util.ArrayList;

import onlineshopapp.adapor.PopularAdaptor;
import onlineshopapp.domain.PopularDomain;

public class OnlineShopActivity extends AppCompatActivity {
    ActivityOnlineShopBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_online_shop);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        statusBarColor();
        binding = ActivityOnlineShopBinding.inflate(getLayoutInflater());
        initRecyclerView();
    }

    private void statusBarColor() {
        Window window = OnlineShopActivity.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(OnlineShopActivity.this, R.color.purple_Dark));
    }

    private void initRecyclerView() {
        ArrayList<PopularDomain> items = new ArrayList<>();
        items.add(new PopularDomain("", "item_1", 5, 3.1, 4, 12.9));
        items.add(new PopularDomain("", "item_2", 5, 3.1, 4, 12.9));
        binding.onlineShopRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        binding.onlineShopRecyclerView.setAdapter(new PopularAdaptor(items));
    }
}