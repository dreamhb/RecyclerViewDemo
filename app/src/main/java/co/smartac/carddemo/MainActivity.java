package co.smartac.carddemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private String[] datasets = new String[]{"First", "Second", "Third", "Four", "Five", "Six", "Seven", "Nine", "Ten"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rView = (RecyclerView) findViewById(R.id.recycler_view);

        mLayoutManager = new LinearLayoutManager(this);
        rView.setLayoutManager(mLayoutManager);

        mAdapter = new TAdapter(datasets, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(), "Which one clicked !", Toast.LENGTH_SHORT).show();
            }
        });
        rView.setAdapter(mAdapter);

    }


    static class TAdapter extends RecyclerView.Adapter<TAdapter.MViewHolder> implements View.OnClickListener{
        private String[] dataSets;

        private View.OnClickListener mListener;

        public TAdapter(String[] dataSets, View.OnClickListener listener){
            mListener = listener;
            this.dataSets = dataSets;
        }

        @Override
        public MViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            RelativeLayout view = (RelativeLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.item, null);
            view.setOnClickListener(this);
            MViewHolder holder = new MViewHolder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(MViewHolder holder, int position) {
            String item = dataSets[position];
            holder.tv.setText(item);
        }

        @Override
        public int getItemCount() {
            return dataSets.length;
        }

        @Override
        public void onClick(View v) {
            if(mListener != null){
                mListener.onClick(v);
            }
        }


        class MViewHolder extends RecyclerView.ViewHolder{

            public RelativeLayout ll;
            public TextView tv;

            public MViewHolder(RelativeLayout ll) {
                super(ll);
                this.ll = ll;
                tv = (TextView)ll.findViewById(R.id.card_view_tv);
            }
        }

    }
}
