package edu.utem.ftmk.webservicesapi.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import edu.utem.ftmk.webservicesapi.databinding.FragmentGetJokeActivityBinding;

public class GetJokeActivityFrag extends Fragment {
    FragmentGetJokeActivityBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentGetJokeActivityBinding.inflate(inflater, container, false);
        binding.btnGetJoke.setOnClickListener(this::fnGetJoke);
        return binding.getRoot();
    }

    private void fnGetJoke(View view) {

        String strURL = "https://official-joke-api.appspot.com/random_joke";
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        StringRequest stringRequest = new StringRequest(Request.Method.GET, strURL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                JSONObject jsonObject = null;
                try {
                    jsonObject = new JSONObject(response);
                    binding.txtVwJokeType.setText(jsonObject.getString("type"));
                    binding.txtVwSetup.setText(jsonObject.getString("setup"));
                    binding.txtVwPunchline.setText(jsonObject.getString("punchline"));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getContext(), "Unable to connect to the joke!" +
                        error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
        requestQueue.add(stringRequest);
    }

    public void OnDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
