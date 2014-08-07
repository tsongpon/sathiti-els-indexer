package tum.elasticsearch;

import org.elasticsearch.action.search.SearchRequestBuilder;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.index.query.FilterBuilders;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;

/**
 *
 */
public class ESSearch {
    public void searchClick() {
        Settings settings = ImmutableSettings.settingsBuilder()
                .put("cluster.name", "classified_es").build();
        Client client = new TransportClient(settings).addTransportAddress(new InetSocketTransportAddress("bearing", 9300));


        SearchResponse response = client.prepareSearch("sathiti")
                .setTypes("click")
                .setSearchType(SearchType.DFS_QUERY_THEN_FETCH)
                .setQuery(QueryBuilders.matchQuery("prospectTitle", "3-roms"))
                .setPostFilter(FilterBuilders.rangeFilter("statisticsCount").from(12).to(18))
                .setFrom(0).setSize(10).setExplain(true)
                .execute()
                .actionGet();

        client.close();

        SearchHit[] hits = response.getHits().getHits();
        for(SearchHit each : hits) {
            System.out.println("prospectTitle : " + each.getSource().get("prospectTitle"));
            System.out.println("postArea : " + each.getSource().get("postArea"));
            System.out.println("statisticsCount : " + each.getSource().get("statisticsCount"));
            System.out.println("====================================");
        }
    }
}
