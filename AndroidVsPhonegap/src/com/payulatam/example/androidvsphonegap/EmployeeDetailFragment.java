package com.payulatam.example.androidvsphonegap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.payulatam.example.androidvsphonegap.dal.EmployeeDAO;
import com.payulatam.example.androidvsphonegap.model.Employee;

public class EmployeeDetailFragment extends Fragment {

	private Bundle extras;
	private Employee selectedEmployee;
	private EmployeeDAO employeeDAO = new EmployeeDAO();
	
	private ImageView mEmployeeIv;
	private TextView mEmployeeNameTv;
	private TextView mEmployeeEmailTv;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.employee_detail, container, false);
		
		mEmployeeIv = (ImageView)rootView.findViewById(R.id.employeeImageView);
		mEmployeeNameTv = (TextView) rootView.findViewById(R.id.detailNameTv);
		mEmployeeEmailTv = (TextView) rootView.findViewById(R.id.detailEmailTv);
		
		Button backButton = (Button) rootView.findViewById(R.id.backButton);
		backButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View view) {
				backAction();
			}
		});

		return rootView;
	}

	@Override
	public void onResume() {
		super.onResume();

		extras = getArguments();

		if (extras != null) {
			String id = extras.getString(BroadcastMessages.EMPLOYEE_ID_SELECTED_KEY);
			selectedEmployee = employeeDAO.findById(id);
			renderEmployee();
		} else {
			Toast.makeText(getActivity(), "Error while loading selected employee", Toast.LENGTH_LONG).show();
			backAction();
		}
	}

	private void renderEmployee() {
		mEmployeeIv.setImageResource(selectedEmployee.getPictureResourceId());
		mEmployeeNameTv.setText(selectedEmployee.getFirstName() + " " + selectedEmployee.getLastName());
		mEmployeeEmailTv.setText(selectedEmployee.getEmail());
	}

	private void backAction() {
		Intent i = new Intent(BroadcastMessages.CHANGE_PAGE_MESSAGE);
		i.putExtra(BroadcastMessages.PAGE_ID_KEY, R.layout.fragment_main);
		getActivity().sendBroadcast(i);
	}

}
