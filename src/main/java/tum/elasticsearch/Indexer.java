package tum.elasticsearch;

import com.google.gson.Gson;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import tum.sathiti.model.PageviewStatisticsModel;

import java.util.List;

/**
 *
 */
public class Indexer {

    public void index(List<PageviewStatisticsModel> pageviewStatisticsModels) {
        Client client = new TransportClient()
                .addTransportAddress(new InetSocketTransportAddress("127.0.0.1", 9300));

        for(PageviewStatisticsModel each : pageviewStatisticsModels) {
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
