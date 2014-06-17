package com.payulatam.example.androidvsphonegap;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.payulatam.example.androidvsphonegap.dal.EmployeeDAO;
import com.payulatam.example.androidvsphonegap.model.Employee;

public class SearchEmployeesFragment extends Fragment {

	private EmployeeDAO employeeDAO = new EmployeeDAO();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_main, container, false);
		
		final ListView mEmployeesListView = (ListView) rootView.findViewById(R.id.employeesListView);
		final EmployeeAdapter adapter = new EmployeeAdapter(getActivity(), R.layout.employee_row);
		mEmployeesListView.setAdapter(adapter);
		
		final EditText mSearchText = (EditText) rootView.findViewById(R.id.searchText);
		mSearchText.addTextChangedListener(new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {

			}

			@Override
			public void afterTextChanged(Editable s) {
				String text = mSearchText.getText().toString();
				List<Employee> employees = employeeDAO.searchEmployee(text);
				adapter.clear();
				adapter.addAll(employees);
			}
		});

		return rootView;
	}
	
	private class EmployeeAdapter extends ArrayAdapter<Employee> {
		
		private int resourceId;
		
		public EmployeeAdapter(Context context, int resource) {
			super(context, resource);
			this.resourceId = resource;
		}
		
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View rowView = inflater.inflate(resourceId, parent, false);
			
			final Employee employee = getItem(position);
			
			TextView mNameTv = (TextView) rowView.findViewById(R.id.detailNameTv);
			mNameTv.setText(employee.getFirstName() + " " + employee.getLastName());
			
			TextView mEmailTv = (TextView) rowView.findViewById(R.id.detailEmailTv);
			mEmailTv.setText(employee.getEmail());
			
			rowView.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					Intent intent = new Intent(BroadcastMessages.CHANGE_PAGE_MESSAGE);
					intent.putExtra(BroadcastMessages.PAGE_ID_KEY, R.layout.employee_detail);
					intent.putExtra(BroadcastMessages.EMPLOYEE_ID_SELECTED_KEY, employee.getId());
					getContext().sendBroadcast(intent);
				}
			});
			
			return rowView;
		}
		
	}

}
