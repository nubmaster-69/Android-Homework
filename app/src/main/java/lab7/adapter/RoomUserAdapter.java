package lab7.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hisu.myapplication.R;

import java.util.List;

import lab7.dao.UserDAO;
import lab7.database.MyRoomDatabase;
import lab7.entity.User;

public class RoomUserAdapter extends RecyclerView.Adapter<RoomUserAdapter.UserViewHolder> {

    private List<User> userList;
    private Context context;
    private UserDAO userDAO;

    public RoomUserAdapter(List<User> userList, Context context) {
        this.userList = userList;
        this.context = context;
        userDAO = MyRoomDatabase.getInstance(context).userDao();
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UserViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View userView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_user, parent, false);
        return new UserViewHolder(userView);
    }

    @Override
    public void onBindViewHolder(@NonNull RoomUserAdapter.UserViewHolder holder, int position) {
        User user = userList.get(position);

        holder.txtUser.setText(user.getId() + ". " + user.getFullName());

        holder.btnEdit.setOnClickListener(view -> {
            user.setFullName(user.getFullName() + " Updated!");

            userDAO.updateUser(user);

            Toast.makeText(context, "Updated!", Toast.LENGTH_SHORT).show();
            notifyDataSetChanged();
        });

        holder.btnDel.setOnClickListener(view -> {
            new AlertDialog.Builder(context)
                    .setTitle("Delete User")
                    .setMessage("Do you really want to remove this dude?")
                    .setPositiveButton(android.R.string.yes,
                            (dialogInterface, i) -> {
                                userDAO.deleteUserByID(user);
                                Toast.makeText(context, "Deleted!", Toast.LENGTH_SHORT).show();
                                userList.remove(user);
                                notifyDataSetChanged();
                            })
                    .setNegativeButton(android.R.string.no, null).show();
        });
    }

    @Override
    public int getItemCount() {
        if (userList == null)
            return 0;
        return userList.size();
    }

    public class UserViewHolder extends RecyclerView.ViewHolder {

        private TextView txtUser;
        private ImageButton btnEdit, btnDel;

        public UserViewHolder(@NonNull View itemView) {
            super(itemView);
            txtUser = itemView.findViewById(R.id.txt_user_info);
            btnEdit = itemView.findViewById(R.id.btn_sql_edit);
            btnDel = itemView.findViewById(R.id.btn_sql_del);
        }
    }
}