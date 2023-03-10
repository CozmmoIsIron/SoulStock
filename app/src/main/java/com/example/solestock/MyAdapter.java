package com.example.solestock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;


public class MyAdapter extends  RecyclerView.Adapter{

    List<User> dataUserList;



    public MyAdapter(List<User> dataUserList){
        this.dataUserList=dataUserList;
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        ViewHolderClass viewHolderClass= new ViewHolderClass(view);


        return viewHolderClass;
    }



    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ViewHolderClass viewHolderClass=(ViewHolderClass)holder;
        User dataUser= dataUserList.get(position);
        viewHolderClass.name.setText(dataUser.getName());
        viewHolderClass.duration.setText(dataUser.getDuration());
    }

    @Override
    public int getItemCount() {
        return dataUserList.size();
    }
    public class  ViewHolderClass extends RecyclerView.ViewHolder{
        TextView name,duration;
        public ViewHolderClass(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.tv_name);
            duration=itemView.findViewById(R.id.tv_duration);
        }
    }
}




