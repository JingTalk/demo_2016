package com.jinghuang.demo;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MenuLeftFragment extends Fragment {
	
	private View mView;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		if (mView == null) {
			mView = inflater.inflate(R.layout.left_menu, container, false);
		}
		return mView;
	}
	

}
