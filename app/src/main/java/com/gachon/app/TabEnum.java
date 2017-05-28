package com.gachon.app;

import android.content.res.Resources;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ogaclejapan.smarttablayout.SmartTabLayout;

public enum TabEnum {
  CUSTOM_TAB_ICONS1(R.string.demo_title_custom_tab_icons1, R.layout.demo_custom_tab_icons1) {
    @Override
    public int[] tabs() {
      return new int[] {
          R.string.demo_tab_no_title,
          R.string.demo_tab_no_title,
          R.string.demo_tab_no_title,
          R.string.demo_tab_no_title
      };
    }

    @Override
    public void setup(SmartTabLayout layout) {
      super.setup(layout);
      final LayoutInflater inflater = LayoutInflater.from(layout.getContext());
      final Resources res = layout.getContext().getResources();

      //create tab view 를 하지 못함
      layout.setCustomTabView(new SmartTabLayout.TabProvider() {
        @Override
        public View createTabView(ViewGroup container, int position, PagerAdapter adapter) {
          ImageView icon = (ImageView) inflater.inflate(R.layout.custom_tab_icon1, container,
              false);
          switch (position) {
            case 0:
              icon.setImageDrawable(res.getDrawable(R.drawable.icon_learn));
              break;
            case 1:
              //17.4.3 : case 1의 아이콘을 안쓰는 3으로 보냄
              icon.setImageDrawable(res.getDrawable(R.drawable.icon_rank));
              break;
            case 2:
              icon.setImageDrawable(res.getDrawable(R.drawable.icon_menu_user_settings));
              break;
            case 3:
              icon.setImageDrawable(res.getDrawable(R.drawable.icon_menu_cycling));
              break;
            default:
              throw new IllegalStateException("Invalid position: " + position);
          }
          return icon;
        }
      });
    }
  };


  public final int titleResId;
  public final int layoutResId;

  TabEnum(int titleResId, int layoutResId) {
    this.titleResId = titleResId;
    this.layoutResId = layoutResId;
  }
//  public void startActivity(Context context) {
//    MainActivity.startActivity(context, this);
//  }

  //17.2.7 : dummy
  public void setup(final SmartTabLayout layout) {
    //Do nothing.
  }

  //17.2.7 : dummy
  public int[] tabs() {
    //do nothing
    return null;
  }

}
