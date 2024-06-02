package com.timepet.timewithpet;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class updateTime extends AppCompatActivity {
    int launchCount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_update_time);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        TextView textView3 = findViewById(R.id.textView3);
       launchCount = getIntFromPreferences(this, "launch_count", 0);
        textView3.setText(launchCount + "");
        ImageView upTime = findViewById(R.id.upchas);
        upTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launchCount++;
                textView3.setText(launchCount + "");
                saveIntInPreferences(updateTime.this,"launch_count",launchCount);
            }
        });
        ImageView choosepet = findViewById(R.id.choosepet);
        choosepet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(updateTime.this,PetActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(updateTime.this);
                startActivity(intent, options.toBundle());
            }
        });
        ImageView img = findViewById(R.id.img);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(updateTime.this,MenuActivity.class);
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(updateTime.this);
                startActivity(intent, options.toBundle());
            }
        });
    }

    public void saveIntInPreferences(Context context, String key, int value) {
        // Получение экземпляра SharedPreferences. "app_preferences" - это имя файла настроек.
        SharedPreferences sharedPreferences = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        // Сохранение значения int
        editor.putInt(key, value);

        // Подтверждение изменений
        editor.apply();
    }
    public int getIntFromPreferences(Context context, String key, int defaultValue) {
        // Получение экземпляра SharedPreferences. "app_preferences" - это имя файла настроек.
        SharedPreferences sharedPreferences = context.getSharedPreferences("app_preferences", Context.MODE_PRIVATE);

        // Получение значения int. Если значение по данному ключу не найдено, будет возвращено defaultValue.
        return sharedPreferences.getInt(key, defaultValue);
    }


}