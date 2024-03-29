/*
 * Copyright (c) 2019. Dylan Cai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.dylanc.loadingstateview.sample.java.delegate;

import android.app.Activity;
import android.os.Build;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;

import com.dylanc.loadingstateview.LoadingStateView;
import com.dylanc.loadingstateview.ViewType;
import com.dylanc.loadingstateview.sample.java.R;

import org.jetbrains.annotations.NotNull;

import kotlin.jvm.functions.Function1;

/**
 * @author Dylan Cai
 */
public class ToolbarViewDelegate extends LoadingStateView.ViewDelegate {

  private final String title;
  private final NavIconType type;
  private final int menuId;
  private final Function1<? super MenuItem, Boolean> onMenuItemClick;

  public ToolbarViewDelegate(String title, NavIconType type) {
    this(title, type, 0, null);
  }

  public ToolbarViewDelegate(String title, NavIconType type, int menuId, Function1<? super MenuItem, Boolean> onMenuItemClick) {
    super(ViewType.TITLE);
    this.title = title;
    this.type = type;
    this.menuId = menuId;
    this.onMenuItemClick = onMenuItemClick;
  }

  @NonNull
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
    View view = inflater.inflate(R.layout.layout_toolbar, parent, false);
    Activity activity = (Activity) view.getContext();
    Toolbar toolbar = view.findViewById(R.id.toolbar);
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
      activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
    }

    if (!TextUtils.isEmpty(title)) {
      toolbar.setTitle(title);
    }

    if (type == NavIconType.BACK) {
      toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black);
      toolbar.setNavigationOnClickListener(v -> activity.finish());
    } else {
      toolbar.setNavigationIcon(null);
    }

    if (menuId > 0 && onMenuItemClick != null) {
      toolbar.inflateMenu(menuId);
      toolbar.setOnMenuItemClickListener(onMenuItemClick::invoke);
    }
    return view;
  }
}
