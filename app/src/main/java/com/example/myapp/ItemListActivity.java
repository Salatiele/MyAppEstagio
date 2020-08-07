package com.example.myapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.appcompat.widget.Toolbar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.myapp.page.PageContent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Essa activity representa a lista de páginas, se apresentando de forma
 * diferente dependendo se está em Portrait ou Landscape.
 * Coloquei aqui também a tabela de funcionários, de forma estática
 */

/**
 * On handsets, the activity presents a list of items, which when touched,
 * lead to a {@link ItemDetailActivity} representing
 * item details (não programado). On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes (programado).
 */

public class ItemListActivity extends AppCompatActivity {

    /** String referente ao .json com os dados dos funcionários */
    public static final String jsonFuncionario = "{\n" +
            "    \"funcionarios\":[\n" +
            "        {\n" +
            "            \"id\":0,\n" +
            "            \"nome\":\"Marcelo\",\n" +
            "            \"sobrenome\":\"Silva\",\n" +
            "            \"salario\":3200.00,\n" +
            "            \"area\":\"SM\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\":1,\n" +
            "            \"nome\":\"Washington\",\n" +
            "            \"sobrenome\":\"Ramos\",\n" +
            "            \"salario\":2700.00,\n" +
            "            \"area\":\"UD\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\":2,\n" +
            "            \"nome\":\"Sergio\",\n" +
            "            \"sobrenome\":\"Pinheiro\",\n" +
            "            \"salario\":2450.00,\n" +
            "            \"area\":\"SD\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\":3,\n" +
            "            \"nome\":\"Bernardo\",\n" +
            "            \"sobrenome\":\"Costa\",\n" +
            "            \"salario\":3700.00,\n" +
            "            \"area\":\"SM\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\":4,\n" +
            "            \"nome\":\"Cleverton\",\n" +
            "            \"sobrenome\":\"Farias\",\n" +
            "            \"salario\":2750.00,\n" +
            "            \"area\":\"SD\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\":5,\n" +
            "            \"nome\":\"Abraão\",\n" +
            "            \"sobrenome\":\"Campos\",\n" +
            "            \"salario\":2550.00,\n" +
            "            \"area\":\"SD\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\":6,\n" +
            "            \"nome\":\"Letícia\",\n" +
            "            \"sobrenome\":\"Farias\",\n" +
            "            \"salario\":2450.00,\n" +
            "            \"area\":\"UD\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\":7,\n" +
            "            \"nome\":\"Fernando\",\n" +
            "            \"sobrenome\":\"Ramos\",\n" +
            "            \"salario\":2450.00,\n" +
            "            \"area\":\"SD\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\":8,\n" +
            "            \"nome\":\"Marcelo\",\n" +
            "            \"sobrenome\":\"Farias\",\n" +
            "            \"salario\":2550.00,\n" +
            "            \"area\":\"UD\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\":9,\n" +
            "            \"nome\":\"Fabio\",\n" +
            "            \"sobrenome\":\"Souza\",\n" +
            "            \"salario\":2750.00,\n" +
            "            \"area\":\"SD\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"id\":10,\n" +
            "            \"nome\":\"Clederson\",\n" +
            "            \"sobrenome\":\"Oliveira\",\n" +
            "            \"salario\":2500.00,\n" +
            "            \"area\":\"SD\"\n" +
            "        }\n" +
            "    ],\n" +
            "    \"areas\":[\n" +
            "        {\n" +
            "            \"codigo\":\"SD\",\n" +
            "            \"nome\":\"Desenvolvimento de Software\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"codigo\":\"SM\",\n" +
            "            \"nome\":\"Gerenciamento de Software\"\n" +
            "        },\n" +
            "        {\n" +
            "            \"codigo\":\"UD\",\n" +
            "            \"nome\":\"Designer de UI/UX\"\n" +
            "        }\n" +
            "    ]\n" +
            "}";

    /** Lista com os 'Funcionarios' e 'Areas de atuação' que serão extraídos do .json */
    public final List<Funcionario> LISTA = new ArrayList<Funcionario>();
    public final List<Area> AREAS = new ArrayList<Area>();

    /** Boolean que é true se o modo é Landscape */
    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());

        if (findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        /** Parser para leitura do .json */
        try {
            JSONObject funcionarios = new JSONObject(jsonFuncionario);
            JSONArray arrayFuncionarios = funcionarios.getJSONArray("funcionarios");
            for(int i = 0; i < arrayFuncionarios.length(); i++){
                JSONObject funcionario = arrayFuncionarios.getJSONObject(i);
                LISTA.add(new Funcionario(
                        (Integer) funcionario.get("id"),
                        funcionario.getString("nome"),
                        funcionario.getString("sobrenome"),
                        funcionario.getDouble("salario"),
                        funcionario.getString("area"))
                );
            }

            JSONArray arrayAreas = funcionarios.getJSONArray("areas");
            for(int i = 0; i < arrayFuncionarios.length(); i++) {
                JSONObject areas = arrayAreas.getJSONObject(i);
                AREAS.add(new Area(areas.getString("codigo"), areas.getString("nome")));
            }
        }        catch (JSONException e){
            e.printStackTrace();
        };

        /** O primeiro recyclerView já veio no código e trata o listView com as páginas */
        /** O recyclerView2 foi criado por mim, para tratar a tabela, que coloquei estática */
        View recyclerView = findViewById(R.id.item_list);
        View recyclerView2 = findViewById(R.id.row_table);
        assert recyclerView != null;
        assert recyclerView2 != null;
        setupRecyclerView((RecyclerView) recyclerView);
        setupRecyclerView2((RecyclerView) recyclerView2);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(this, PageContent.ITEMS, mTwoPane));
    }

    private void setupRecyclerView2(@NonNull RecyclerView recyclerView) {
        recyclerView.setAdapter(new SecondAdapter(LISTA, this));
    }

    /** Adapter chamado pelo recyclerView2 */
    public static class SecondAdapter
        extends RecyclerView.Adapter<SecondAdapter.ViewHolder2>{

        private final List<Funcionario> funcs;
        private final ItemListActivity mParentActivity;

        public SecondAdapter(List<Funcionario> funcs, ItemListActivity mParentActivity) {
            this.funcs = funcs;
            this.mParentActivity = mParentActivity;
        }

        @NonNull
        @Override
        public ViewHolder2 onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.row_table_content, parent, false);

            return new ViewHolder2(view);
        }

        /** Preenche a tabela com os funcionários e respectivas área de atuação*/
        @Override
        public void onBindViewHolder(@NonNull ViewHolder2 holder, int position) {
            //Preenche o ID do funcionário
            holder.colTable1.setText(Integer.toString(funcs.get(position).id));

            //Preenche o nome completo do funcionário
            String nomeCompleto = funcs.get(position).nome + " " + funcs.get(position).sobrenome;
            holder.colTable2.setText(nomeCompleto);

            //Preenche com a área de atuação, de acordo com o código
            String area = funcs.get(position).area;
            if(area.equals("SD")){
                holder.colTable3.setText("Desenvolvimento de Software");
            } else if(area.equals("SM")){
                holder.colTable3.setText("Gerenciamento de Software");
            } else {
                holder.colTable3.setText("Designer de UI/UX");
            }
        }

        @Override
        public int getItemCount() {
            return funcs.size();
        }

        /** Representa os textViews de cada linha da tabela*/
        class ViewHolder2 extends RecyclerView.ViewHolder {
            final TextView colTable1;
            final TextView colTable2;
            final TextView colTable3;

            /** Construtor do ViewHolder2 */
            ViewHolder2(View view) {
                super(view);
                colTable1 = (TextView) view.findViewById(R.id.ID_TABLE);
                colTable2 = (TextView) view.findViewById(R.id.Nome_TABLE);
                colTable3 = (TextView) view.findViewById(R.id.Area_TABLE);

            }
        }
    }

    /** Recycler View do modelo, referente ao listView*/
    public static class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final ItemListActivity mParentActivity;
        private final List<PageContent.PageItem> mValues;
        private final boolean mTwoPane;
        private final View.OnClickListener mOnClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                PageContent.PageItem item = (PageContent.PageItem) view.getTag();
                if (mTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putString(ItemDetailFragment.ARG_ITEM_ID, item.id);
                    ItemDetailFragment fragment = new ItemDetailFragment();
                    fragment.setArguments(arguments);
                    mParentActivity.getSupportFragmentManager().beginTransaction()
                            .replace(R.id.item_detail_container, fragment)
                            .commit();
                } else {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, ItemDetailActivity.class);
                    intent.putExtra(ItemDetailFragment.ARG_ITEM_ID, item.id);

                    context.startActivity(intent);
                }
            }
        };

        SimpleItemRecyclerViewAdapter(ItemListActivity parent,
                                      List<PageContent.PageItem> items,
                                      boolean twoPane) {
            mValues = items;
            mParentActivity = parent;
            mTwoPane = twoPane;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.item_list_content, parent, false);

            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mContentView.setText(mValues.get(position).content);
            holder.itemView.setTag(mValues.get(position));
            holder.itemView.setOnClickListener(mOnClickListener);
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        class ViewHolder extends RecyclerView.ViewHolder {
            final TextView mContentView;

            @SuppressLint("ResourceType")
            ViewHolder(View view) {
                super(view);
                mContentView = (TextView) view.findViewById(R.id.content);
            }
        }
    }
}