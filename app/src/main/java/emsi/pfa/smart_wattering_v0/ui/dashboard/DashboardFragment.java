package emsi.pfa.smart_wattering_v0.ui.dashboard;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import emsi.pfa.smart_wattering_v0.databinding.FragmentDashboardBinding;
import emsi.pfa.smart_wattering_v0.ui.beans.User;
import emsi.pfa.smart_wattering_v0.ui.service.UserService;
import emsi.pfa.smart_wattering_v0.ui.session.SessionManagement;

public class DashboardFragment extends Fragment {

    private FragmentDashboardBinding binding;
    SessionManagement sessionManagement;
    UserService userService;

    @SuppressLint("SetTextI18n")
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentDashboardBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        sessionManagement = new SessionManagement(getActivity().getApplicationContext());

        final TextView textView = binding.textHome;

        //
        userService = new UserService();
        User u= userService.findById(sessionManagement.getuserconnect().getUser_id());

        textView.setText(sessionManagement.getuserconnect().getRole().getNome()+"");
        //
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}