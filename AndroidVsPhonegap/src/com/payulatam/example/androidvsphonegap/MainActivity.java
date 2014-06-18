package com.payulatam.example.androidvsphonegap;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends ActionBarActivity {
	
	private int currentFragment;

	@SuppressLint("UseSparseArrays")
	private final Map<Integer, Fragment> fragments = new HashMap<Integer, Fragment>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		if (savedInstanceState == null) {
			changePage(R.layout.fragment_main, new Bundle());
		}
	}

	@Override
	protected void onResume() {
		super.onResume();
		this.registerReceiver(mChangePageBR, new IntentFilter(BroadcastMessages.CHANGE_PAGE_MESSAGE));
	}

	@Override
	protected void onPause() {
		super.onPause();
		this.unregisterReceiver(mChangePageBR);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private void changePage(int pageId, Bundle extras) {
		System.out.println("Broadcast Received!");
		
		Fragment pageFragment = getFragment(pageId);
		if(pageFragment == null) {
			Toast.makeText(this, "Error while changing page", Toast.LENGTH_SHORT).show();
		} else {
			removeFragments();
			pageFragment.setArguments(extras);
			getSupportFragmentManager().beginTransaction().add(R.id.container, pageFragment).commit();
			currentFragment = pageId;
		}
	}

	private BroadcastReceiver mChangePageBR = new BroadcastReceiver() {
		@Override
		public void onReceive(Context context, Intent intent) {
			Bundle b = intent.getExtras();
			int pageId = b.getInt(BroadcastMessages.PAGE_ID_KEY);
			changePage(pageId, b);
		}

	};
	
	private void removeFragments() {
		for (Entry<Integer, Fragment> entry : fragments.entrySet()) {
			getSupportFragmentManager().beginTransaction().remove(entry.getValue()).commit();
		}
	}

	private Fragment getFragment(int fragmentId) {
		Fragment result = null;
		if (fragments.containsKey(fragmentId)) {
			result = fragments.get(fragmentId);
		} else {
			switch (fragmentId) {
				case R.layout.fragment_main:
					result = new SearchEmployeesFragment();
					break;
				case R.layout.employee_detail:
					result = new EmployeeDetailFragment();
					break;
			}
			fragments.put(fragmentId, result);
		}
		
		return  result;
	}
	
	@Override
	public void onBackPressed() {
		if(currentFragment == R.layout.fragment_main) {
			super.onBackPressed();
		} else if(currentFragment == R.layout.employee_detail) {
			changePage(R.layout.fragment_main, new Bundle());
		}
	}

}
