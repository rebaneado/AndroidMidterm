package edu.uncc.midtermapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import edu.uncc.midtermapp.databinding.FragmentTriviaBinding;
import edu.uncc.midtermapp.models.Answer;
import edu.uncc.midtermapp.models.Question;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TriviaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TriviaFragment extends Fragment {
    private ArrayList<Question> mTriviaQuestions = new ArrayList<Question>();
    private static final String ARG_PARAM_QUESTIONS = "ARG_PARAM_QUESTIONS";

    ArrayList<Answer> answers = new ArrayList<Answer>();
    ArrayAdapter<Answer> adapter;
    int currentQuestionIndex = 0;
    Question currentQuestion;

    public TriviaFragment() {
        // Required empty public constructor
    }

    public static TriviaFragment newInstance(ArrayList<Question> questions) {
        TriviaFragment fragment = new TriviaFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM_QUESTIONS, questions);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mTriviaQuestions = (ArrayList<Question>) getArguments().getSerializable(ARG_PARAM_QUESTIONS);
        }
    }

    FragmentTriviaBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentTriviaBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Welcome to Trivia");


        adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, answers);
        binding.listViewAnswers.setAdapter(adapter);

        if (mTriviaQuestions.size() > 0) {
            currentQuestionIndex = 0;
            currentQuestion = mTriviaQuestions.get(0);
            displayQuestion();
        }
        else{
            Toast.makeText(getActivity(), "Trivia has no questions ", Toast.LENGTH_SHORT).show();
        }
    }
    void displayQuestion(){
        binding.textViewTriviaQuestion.setText(currentQuestion.getQuestion_text());
        binding.textViewTriviaTopStatus.setText("Question " + (currentQuestionIndex + 1) + " of " + mTriviaQuestions.size());
        answers.addAll(currentQuestion.getAnswers());
        adapter.notifyDataSetChanged();

        if (currentQuestion.getQuestion_text() != null) {
            Picasso.get().load("https://i.imgur.com/DvpvklR.png").into(imageView);
        } else {

        }
    }
}