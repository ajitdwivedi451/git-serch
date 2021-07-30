package com.ajit.github;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RepositoryAdaptor extends RecyclerView.Adapter<RepoViewHolder>  {
    Context mcontext;
    Repository[] repoarray;

    public RepositoryAdaptor(Context mcontext, Repository[] repoarray) {
        this.mcontext = mcontext;
        this.repoarray = repoarray;
    }

    @NonNull
    @Override
    public RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View itemView=inflater.inflate(R.layout.itemrepo,parent,false);
        RepoViewHolder viewHolder=new RepoViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RepoViewHolder holder, int position) {
        Repository repo=repoarray[position];
        holder.reponame.setText(repo.name);
        holder.description.setText(repo.description);
        holder.language.setText(repo.language);

    }

    @Override
    public int getItemCount() {
        return repoarray.length;
    }
}
   class RepoViewHolder extends RecyclerView.ViewHolder{
    TextView reponame,language,description;

    public RepoViewHolder(@NonNull View itemView) {
        super(itemView);
        reponame=(TextView)itemView.findViewById(R.id.name);
        language=(TextView)itemView.findViewById(R.id.lang);
        description=(TextView)itemView.findViewById(R.id.desc);


    }
}
