package tum.sathiti.dao;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import tum.sathiti.model.PageviewStatisticsModel;

import java.util.List;

/**
 *
 */

@Repository
public class ClickDao {

    @Autowired(required = true)
    private MongoTemplate mongoTemplate;

    public List<PageviewStatisticsModel> queryClickStatistics(DateTime from, DateTime to) {

        Query query = new Query();
        query.addCriteria(Criteria.where("statisticsDate").gte(from.withTimeAtStartOfDay()).lte(to.withTimeAtStartOfDay()));
        return mongoTemplate.find(query, PageviewStatisticsModel.class);
    }
}
