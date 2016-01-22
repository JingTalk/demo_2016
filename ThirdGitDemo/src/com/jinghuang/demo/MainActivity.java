package com.jinghuang.demo;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingActivity;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;
import com.jinghuang.demo.mortgage.MortgageActivity;

/**
 * Created by jing on 2016/1/8.
 */
public class MainActivity extends SlidingActivity /*SlidingFragmentActivity*/ {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
		setContentView(R.layout.activity_main);

		initLeftMenu();
	}

	/*private void initLeftMenuFragment() {

		Fragment leftMenuFragment = new MenuLeftFragment();
		setBehindContentView(R.layout.left_menu_frame);
		getSupportFragmentManager().beginTransaction()
				.replace(R.id.id_left_menu_frame, leftMenuFragment).commit();
		
		SlidingMenu menu = getSlidingMenu();
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
		menu.setShadowWidthRes(R.dimen.shadow_width);
		menu.setShadowDrawable(R.drawable.shadow);

		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);

		menu.setFadeDegree(0.35f);
	}*/
	
	private void initLeftMenu() {
		
		setBehindContentView(R.layout.left_menu);
		SlidingMenu menu = getSlidingMenu();
		menu.setMode(SlidingMenu.LEFT);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		menu.setShadowWidthRes(R.dimen.shadow_width);
		menu.setShadowDrawable(R.drawable.shadow);

		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);

		menu.setFadeDegree(0.35f);
	}

	public void showLeftMenu(View view) {
		getSlidingMenu().showMenu();
	}
	
	public void toggleLeftMenu(View view) {
		getSlidingMenu().toggle();
	}

    public void showMortgage(View view) {
        Intent target = new Intent(this, MortgageActivity.class);
        startActivity(target);
    }
}