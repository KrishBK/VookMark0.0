package mock.intern.LandingPage;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import java.util.List;

import mock.intern.R;


public class CardsAdapter extends RecyclerView.Adapter<CardsAdapter.ViewHolder> {

    private final List<DummyContent.Movie> mValues;

    public CardsAdapter(List<DummyContent.Movie> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_card_item, parent, false);
        return new ViewHolder(view);
    }

    @Override

    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mContentView.setImageResource((mValues.get(position)).getId());
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView mContentView;
        public ViewHolder(View view) {
            super(view);
            mView = view;
            mContentView = view.findViewById(R.id.content);
        }


        }
    }

