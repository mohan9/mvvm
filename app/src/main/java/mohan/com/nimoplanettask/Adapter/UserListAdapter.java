package mohan.com.nimoplanettask.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;
import mohan.com.nimoplanettask.Entity.User;
import mohan.com.nimoplanettask.R;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserViewHolder> {

    private final LayoutInflater mInflater;
    private List<User> users_data;


    public UserListAdapter(Context context) {
        mInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = mInflater.inflate(R.layout.recyclerview_item, viewGroup, false);
        return new UserViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UserViewHolder userViewHolder, int i) {
        if (users_data != null) {
            User user = users_data.get(i);
            userViewHolder.txt_name.setText(user.getName());
            userViewHolder.txt_time.setText(user.getTime());
            userViewHolder.txt_content.setText(user.getContent());
            Picasso.get().load(user.getImage()).placeholder(R.drawable.ic_user).into(userViewHolder.user_pic);
        }
    }


    @Override
    public int getItemCount() {
        if (users_data != null)
            return users_data.size();
        else return 0;
    }

    public void setUsers(List<User> users) {
        users_data = users;
        notifyDataSetChanged();
    }

    class UserViewHolder extends RecyclerView.ViewHolder {
        private TextView txt_name, txt_time, txt_content;
        private CircleImageView user_pic;

        private UserViewHolder(View itemView) {
            super(itemView);
            txt_name = itemView.findViewById(R.id.txt_name);
            txt_time = itemView.findViewById(R.id.txt_time);
            txt_content = itemView.findViewById(R.id.txt_content);
            user_pic = itemView.findViewById(R.id.user_pic);
        }
    }
}
