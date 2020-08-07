package com.example.myapp.page;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Classe com referente às páginas no listView
 */
public class PageContent {

    /** Array das páginas no listView*/
    public static final List<PageItem> ITEMS = new ArrayList<PageItem>();

    /** Map de páginas*/
    public static final Map<String, PageItem> ITEM_MAP = new HashMap<String, PageItem>();

    /** Contador com o número de páginas no listView */
    private static final int COUNT = 7;

    /** Lista com os nomes das páginas (listView) */
    public static final List<String> NAMES = new ArrayList<String>();
    static {
        NAMES.add("Dashboard");
        NAMES.add("Shortcuts");
        NAMES.add("Overview");
        NAMES.add("Events");
        NAMES.add("About");
        NAMES.add("Services");
        NAMES.add("Contact");
    }

    /** Chama o método que cria e adiciona as páginas a lista e mapa*/
    static {
        // Add some sample items.
        for (int i = 0; i < COUNT; i++) {
            addItem(createPageItem(i));
        }
    }

    /** Adiciona a página recebida como parâmetro na lista e mapa de páginas*/
    private static void addItem(PageItem item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /** Cria uma página, usando um ID e a lista de Nomes das páginas*/
    private static PageItem createPageItem(int position) {
        return new PageItem(String.valueOf(position), NAMES.get(position), makeDetails(position));
    }

    /** Gera uma string que detalha cada página*/
    private static String makeDetails(int position) {
        StringBuilder builder = new StringBuilder();
        builder.append("Detalhes sobre ").append(NAMES.get(position));
        return builder.toString();
    }

    /** Representa uma página (linha do listView) */
    public static class PageItem {
        public final String id;
        public final String content;
        public final String details;

        public PageItem(String id, String content, String details) {
            this.id = id;
            this.content = content;
            this.details = details;
        }

        @Override
        public String toString() {
            return content;
        }
    }
}