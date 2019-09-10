package io.github.luizgrp.sectionedrecyclerviewadapter.demo.example9;

import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionAdapter;
import io.github.luizgrp.sectionedrecyclerviewadapter.SectionedRecyclerViewAdapter;
import io.github.luizgrp.sectionedrecyclerviewadapter.demo.R;

public class Example9Fragment extends Fragment {

    private final Handler handler = new Handler();
    private final Locale locale = Locale.US;
    private final NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(locale);
    private final String deltaPositiveFormat = "%+.2f ▲";
    private final String deltaNegativeFormat = "%+.2f ▼";
    private final String holdingFormat = "%.2f";

    private SectionedRecyclerViewAdapter sectionedAdapter;
    private PortfolioSection portfolioSection;
    private WatchListSection watchListSection;

    private Runnable portfolioUpdatesRunnable = new Runnable() {
        @Override
        public void run() {
            getPortfolioUpdates();
            handler.postDelayed(portfolioUpdatesRunnable, TimeUnit.SECONDS.toMillis(1));
        }
    };

    private Runnable watchListUpdatesRunnable = new Runnable() {
        @Override
        public void run() {
            getWatchListUpdates();
            handler.postDelayed(watchListUpdatesRunnable, TimeUnit.SECONDS.toMillis(1));
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_ex9, container, false);

        sectionedAdapter = new SectionedRecyclerViewAdapter();
        portfolioSection = new PortfolioSection(getPortfolio());
        watchListSection = new WatchListSection(getWatchList());

        sectionedAdapter.addSection(portfolioSection);
        sectionedAdapter.addSection(watchListSection);

        final RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(sectionedAdapter);

        handler.post(portfolioUpdatesRunnable);
        handler.post(watchListUpdatesRunnable);

        return view;
    }

    @Override
    public void onDetach() {
        handler.removeCallbacksAndMessages(null);

        super.onDetach();
    }

    private List<PortfolioItem> getPortfolio() {
        final List<PortfolioItem> list = new ArrayList<>();

        for (final String item : getResources().getStringArray(R.array.portfolio_coins)) {
            final String[] array = item.split("\\|");

            final String code = array[0];
            final double holdingQty = Double.parseDouble(array[2]);
            final double price = getPrice();
            final String priceText = currencyFormat.format(price);

            final double delta = getDelta();
            final int deltaColor;
            final String deltaText;
            if (delta == 0) {
                deltaText = String.valueOf(delta);
                deltaColor = ResourcesCompat.getColor(getResources(), R.color.black, null);
            } else if (delta > 0) {
                deltaText = String.format(locale, deltaPositiveFormat, delta);
                deltaColor = ResourcesCompat.getColor(getResources(), R.color.green, null);
            } else {
                deltaText = String.format(locale, deltaNegativeFormat, delta);
                deltaColor = ResourcesCompat.getColor(getResources(), R.color.red, null);
            }

            final String holdingQtyText = String.format(locale, holdingFormat, holdingQty);
            final String holdingPriceText = currencyFormat.format(holdingQty * price);

            list.add(new PortfolioItem(code, priceText, deltaText, deltaColor, holdingQtyText, holdingPriceText));
        }

        return list;
    }

    private List<WatchItem> getWatchList() {
        final List<WatchItem> list = new ArrayList<>();

        for (String item : getResources().getStringArray(R.array.watchlist_coins)) {
            String[] array = item.split("\\|");

            final String code = array[0];
            final double price = getPrice();
            final String priceText = currencyFormat.format(price);

            final double delta = getDelta();
            final int deltaColor;
            final String deltaText;
            if (delta == 0) {
                deltaText = String.valueOf(delta);
                deltaColor = ResourcesCompat.getColor(getResources(), R.color.black, null);
            } else if (delta > 0) {
                deltaText = String.format(locale, deltaPositiveFormat, delta);
                deltaColor = ResourcesCompat.getColor(getResources(), R.color.green, null);
            } else {
                deltaText = String.format(locale, deltaNegativeFormat, delta);
                deltaColor = ResourcesCompat.getColor(getResources(), R.color.red, null);
            }

            list.add(new WatchItem(code, priceText, deltaText, deltaColor));
        }

        return list;
    }

    private void getPortfolioUpdates() {
        final List<PortfolioItem> list = getPortfolio();
        final int index = new Random().nextInt(list.size());
        final PortfolioItem portfolioItem = list.get(index);

        portfolioSection.updateItemPrice(index, portfolioItem.price, portfolioItem.delta, portfolioItem.deltaColor, portfolioItem.holdingPrice);

        SectionAdapter sectionAdapter = sectionedAdapter.getAdapterForSection(portfolioSection);

        sectionAdapter.notifyHeaderChanged(currencyFormat.format(getHoldingsTotalPrice()));
        sectionAdapter.notifyItemChanged(index, new PortfolioSection.ItemPriceUpdate());
    }

    private void getWatchListUpdates() {
        final List<WatchItem> list = getWatchList();

        for (int i = 0; i < list.size(); i++) {
            final WatchItem watchItem = list.get(i);
            watchListSection.updateItemPrice(i, watchItem.price, watchItem.delta, watchItem.deltaColor);
        }

        sectionedAdapter.getAdapterForSection(watchListSection).notifyAllItemsChanged(
                new WatchListSection.ItemPriceUpdate());
    }

    private double getHoldingsTotalPrice() {
        final double min = 1;
        final double max = 100_000;
        return getRandomDouble(min, max);
    }

    private double getPrice() {
        final double min = 1;
        final double max = 100;
        return getRandomDouble(min, max);
    }

    private double getDelta() {
        final double min = -2;
        final double max = 2;
        return getRandomDouble(min, max);
    }

    private double getRandomDouble(final double min, final double max) {
        return min + (max - min) * new Random().nextDouble();
    }
}
