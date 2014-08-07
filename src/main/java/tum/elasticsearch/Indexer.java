package tum.elasticsearch;

import com.google.gson.Gson;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexResponse;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.ImmutableSettings;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import tum.sathiti.model.ClickStatisticModel;
import tum.sathiti.model.PageviewStatisticsModel;

import java.util.List;

/**
 *
 */
public class Indexer {

    private static final Logger LOG = LoggerFactory.getLogger(Indexer.class);

    public void deleteIndex() {
        Settings settings = ImmutableSettings.settingsBuilder()
                .put("cluster.name", "classified_es").build();
        Client client = new TransportClient(settings).addTransportAddress(new InetSocketTransportAddress("bearing", 9300));

        client.admin().indices().delete(new DeleteIndexRequest("sathiti")).actionGet();

        client.close();
    }

    public void indexPageview(List<PageviewStatisticsModel> pageviewStatisticsModels) {
        LOG.debug("Opening connection to elasticsearch");
        Settings settings = ImmutableSettings.settingsBuilder()
                .put("cluster.name", "classified_es").build();
        Client client = new TransportClient(settings).addTransportAddress(new InetSocketTransportAddress("bearing", 9300));

        LOG.debug("Indexing pageview data");
        for(PageviewStatisticsModel each : pageviewStatisticsModels) {
            Gson gson = new Gson();
            String json = gson.toJson(each);

            IndexResponse response = client.prepareIndex("sathiti", "pageview")
                    .setSource(json)
                    .execute()
                    .actionGet();
        }

        client.close();
    }

    public void indexClick(List<ClickStatisticModel> clickStatisticModels) {
        LOG.debug("Opening connection to elasticsearch");
        Settings settings = ImmutableSettings.settingsBuilder()
                .put("cluster.name", "classified_es").build();
        Client client = new TransportClient(settings).addTransportAddress(new InetSocketTransportAddress("bearing", 9300));

        LOG.debug("Indexing pageview data");
        for(ClickStatisticModel each : clickStatisticModels) {
            Gson gson = new Gson();
            String json = gson.toJson(each);

            IndexResponse response = client.prepareIndex("sathiti", "click")
                    .setSource(json)
                    .execute()
                    .actionGet();
        }

        client.close();
    }
}
