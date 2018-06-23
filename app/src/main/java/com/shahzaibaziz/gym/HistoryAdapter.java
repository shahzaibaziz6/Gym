package com.shahzaibaziz.gym;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.MyViewHolder> {


    private List<String> listDatePay;
    private List<String> listAmount1;

    public HistoryAdapter(List<String> date,List<String> am)
    {
        listDatePay=date;
        listAmount1=am;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.history_custom_list,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        String datepay = listDatePay.get(position);
        String ammount= listAmount1.get(position);
        holder.tvDatePay.setText(datepay);
        holder.tvAmount.setText(ammount);
    }

    @Override
    public int getItemCount() {
        return listDatePay.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView tvDatePay,tvAmount;
        LinearLayout linearLayout;
        public MyViewHolder(View itemView) {
            super(itemView);
            tvAmount= itemView.findViewById(R.id.tv_history_custom_list_amount);
            tvDatePay=itemView.findViewById(R.id.tv__history_custom_list_date);
            linearLayout= itemView.findViewById(R.id.ll_histoy_custom_list_parent);

        }
    }
}
