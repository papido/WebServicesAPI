package edu.utem.ftmk.webservicesapi.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import edu.utem.ftmk.webservicesapi.R;
import edu.utem.ftmk.webservicesapi.databinding.FragmentGetUniversityBinding;

public class GetUniversityFrag extends Fragment {

    FragmentGetUniversityBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentGetUniversityBinding.inflate(inflater,container,false);
        binding.btnSearchU.setOnClickListener(this::fnSearchUni);
        return binding.getRoot();

    }

    private void fnSearchUni(View view) {
        String strURL = "http://universities.hipolabs.com/search?country=" +
                binding.edtFindUniversity.getText().toString();
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, strURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                binding.results.setText(response);
                binding.results.setMovementMethod(new ScrollingMovementMethod());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Unable to connect to the university list!" +
                        error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);
    }
}