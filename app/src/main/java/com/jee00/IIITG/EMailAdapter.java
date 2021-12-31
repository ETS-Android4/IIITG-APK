package com.jee00.IIITG;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.jee00.IIITG.Model.Faculty;

public class EMailAdapter extends FirebaseRecyclerAdapter<Faculty,EMailAdapter.myViewHolder> {

    public EMailAdapter(@NonNull FirebaseRecyclerOptions<Faculty> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myViewHolder holder, int position, @NonNull Faculty model) {
        holder.title.setText(model.getName());
        holder.sub.setText(model.getSub());
        holder.subject.setText(model.getEmail());
        Glide.with(holder.pic.getContext()).load(model.getPic()).into(holder.pic);

        holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManager=(ClipboardManager) v.getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                ClipData clip=ClipData.newPlainText("text", model.getEmail());
                clipboardManager.setPrimaryClip(clip);
                Toast.makeText(v.getContext(), "Email Copied", Toast.LENGTH_SHORT).show();
            }
        });


    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_lay,parent,false);
        return new myViewHolder(v);
    }

    static class  myViewHolder extends RecyclerView.ViewHolder{
        TextView title;
        TextView subject;
        TextView sub;
        ImageView pic;
        RelativeLayout relativeLayout;

        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            pic=itemView.findViewById(R.id.img1);
            relativeLayout=itemView.findViewById(R.id.whole_btn);
            title=itemView.findViewById(R.id.date);
            subject=itemView.findViewById(R.id.shift);
            sub=itemView.findViewById(R.id.sub);

        }
    }
}