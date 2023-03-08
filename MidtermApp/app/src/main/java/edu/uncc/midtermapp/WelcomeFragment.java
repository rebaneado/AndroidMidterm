package edu.uncc.midtermapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import edu.uncc.midtermapp.databinding.FragmentWelcomeBinding;
import edu.uncc.midtermapp.models.Question;
import edu.uncc.midtermapp.models.TriviaResponse;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class WelcomeFragment extends Fragment {
    private ArrayList<Question> mTriviaQuestions = new ArrayList<Question>();


    public WelcomeFragment() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    FragmentWelcomeBinding binding;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getQuestions();
            }
        });
        getActivity().setTitle("Welcome");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentWelcomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    //HTTP trivia
    private final OkHttpClient client = new OkHttpClient();

    void getQuestions(){
        Request request = new Request.Builder()
                .url("https://www.theappsdr.com/api/trivia")
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NonNull Call call, @NonNull IOException e) {
                e.printStackTrace();
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getActivity(), "Error loading questions !!!", Toast.LENGTH_SHORT).show();

                    }
                });

            }

            @Override
            public void onResponse(@NonNull Call call, @NonNull Response response) throws IOException {
                if(response.isSuccessful()){
                    String body = response.body().string();

                    Gson gson = new Gson();
                    TriviaResponse triviaResponse = gson.fromJson(body, TriviaResponse.class);
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            mlistener.sendQuestionsToTrivia(triviaResponse.getQuestions());

                        }
                    });
/*//MARK: this below is parcing not using GSON.... i hve to copy the get activity on UI Threwad ruanable void run over up top
                    try {
                        JSONObject root = new JSONObject(body);

                        JSONArray questionsJSONArray = root.getJSONArray("questions");
                        mTriviaQuestions.clear();

                        for (int i = 0; i < questionsJSONArray.length(); i++) {
                            JSONObject questionsJSON = questionsJSONArray.getJSONObject(i);
                            Question question = new Question(questionsJSON);
                            mTriviaQuestions.add(question);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
//                    getActivity().runOnUiThread(new Runnable() {
//                        @Override
//                        public void run() {
//                            mlistener.sendTriviaQuestionsToTrivia(mTriviaQuestions);
//
//                        }
//                    });
*/
                }
                else{
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), "Error loading questions !!!", Toast.LENGTH_SHORT).show();

                        }
                    });
                }
            }
        });
    }


    WelcomeListener mlistener;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mlistener = (WelcomeListener) context;
    }

    interface WelcomeListener{
        void sendQuestionsToTrivia(ArrayList<Question> questions);
    }
}