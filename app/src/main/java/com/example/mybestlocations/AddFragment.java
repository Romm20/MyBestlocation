package com.example.mybestlocations;

import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.mybestlocations.databinding.FragmentAddBinding;
import com.example.mybestlocations.databinding.FragmentHomeBinding;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


public class AddFragment extends Fragment {

    FragmentAddBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddBinding.inflate(inflater, container, false);

        binding.btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AjoutPosition ajout = new AjoutPosition();
                ajout.execute();
            }
        });

        return binding.getRoot();
    }
    class AjoutPosition extends AsyncTask {
        int success;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Object doInBackground(Object[] objects) {
            HashMap<String, String> params = new HashMap<>();
            params.put("pseudo", binding.edpseudo.getText().toString());
            params.put("numero", binding.edNumero.getText().toString());
            params.put("longitude", binding.edLongitude.getText().toString());
            params.put("latitude", binding.edLatitude.getText().toString());
            JSONParser parser = new JSONParser();
            JSONObject result=parser.makeHttpRequest(Utilities.URL_ADDPOSITION,"GET",params);

            try {
                success=result.getInt("success");
            } catch (JSONException e) {
                throw new RuntimeException(e);
            }
            return null;

        }

        @Override
        protected void onPostExecute(Object o) {
            if(success==1){
                Toast.makeText(AddFragment.this.getActivity(), "succecful", Toast.LENGTH_LONG).show();
            }
            else Toast.makeText(AddFragment.this.getActivity(), "failed", Toast.LENGTH_LONG).show();
        }
    }
}